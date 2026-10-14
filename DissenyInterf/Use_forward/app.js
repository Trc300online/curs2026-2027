// ==========================================================
// SCSS ESCAPE ROOM - lògica del joc
// (el compilador és a scss-compiler.js i els reptes a challenges.js)
// ==========================================================

const STORAGE_KEY = 'scss-escape-room';
const TEAM_KEY = 'scss-escape-room-team';
const GAME_DURATION_MS = 3 * 60 * 60 * 1000;
const HINT_PENALTY = 5;
const PREVIEW_BASE_CSS = 'body { font-family: system-ui, sans-serif; margin: 0; padding: 12px; }';

const el = id => document.getElementById(id);

let state = loadState();
let currentTeam = null;
let currentChallenge = 0;
let currentCSS = '';
let liveTimeout = null;
let timerInterval = null;

// ----------------------------------------------------------
// Estat (localStorage). Cada pestanya només escriu el seu equip,
// així diversos equips poden jugar al mateix navegador.
// ----------------------------------------------------------
function newTeam() {
  return { points: 0, current: 0, completed: {}, hints: {}, code: {} };
}
function defaultState() {
  return { startTime: null, finished: false, teams: { 1: newTeam(), 2: newTeam(), 3: newTeam() } };
}
function loadState() {
  try {
    const s = JSON.parse(localStorage.getItem(STORAGE_KEY));
    if (s && s.teams) return s;
  } catch (e) { /* estat corrupte: es comença de nou */ }
  return defaultState();
}
function saveState() {
  const stored = loadState();
  stored.startTime = stored.startTime || state.startTime;
  stored.finished = stored.finished || state.finished;
  if (currentTeam) stored.teams[currentTeam] = state.teams[currentTeam];
  state = stored;
  localStorage.setItem(STORAGE_KEY, JSON.stringify(state));
}
const team = () => state.teams[currentTeam];

// ----------------------------------------------------------
// Consola
// ----------------------------------------------------------
function log(message, type = 'info', asCode = false) {
  const out = el('console-output');
  const div = document.createElement('div');
  div.className = `console-message ${type}`;
  if (asCode) {
    const pre = document.createElement('pre');
    pre.textContent = message;
    div.appendChild(pre);
  } else {
    div.textContent = message;
  }
  out.appendChild(div);
  out.scrollTop = out.scrollHeight;
}

// ----------------------------------------------------------
// Compilació, vista prèvia i validacions
// ----------------------------------------------------------
function compileCurrent() {
  const code = el('code-editor').value;
  let result;
  try {
    result = compileSCSS(code);
  } catch (e) {
    result = { css: '', errors: [`Error intern del compilador: ${e.message}`], warnings: [] };
  }
  currentCSS = result.css;
  updatePreview(challenges[currentChallenge].html, result.css);
  return result;
}

function updatePreview(html, css) {
  const safeCss = css.replace(/<\/style/gi, '<\\/style');
  el('preview-iframe').srcdoc = html.replace('BODY_STYLES', () => PREVIEW_BASE_CSS + '\n' + safeCss);
}

function runChecks(tests, input) {
  return tests.map(t => {
    let ok = false;
    try { ok = !!t.test(input); } catch (e) { ok = false; }
    return { ok, text: ok ? t.success : t.msg };
  });
}

function evaluateChallenge() {
  const challenge = challenges[currentChallenge];
  const result = compileCurrent();
  const code = stripComments(el('code-editor').value);
  const scss = runChecks(challenge.validation.scss, code);
  const css = runChecks(challenge.validation.css, result.css);
  const allPassed = !result.errors.length && scss.every(c => c.ok) && css.every(c => c.ok);
  renderValidation(result, scss, css);
  el('validate-btn').classList.toggle('ready', allPassed);
  return { result, scss, css, allPassed };
}

function renderValidation(result, scss, css) {
  const panel = el('validation-panel');
  panel.innerHTML = '';
  const section = (title, items, failClass = 'failed') => {
    const s = document.createElement('div');
    s.className = 'validation-section';
    const h = document.createElement('h4');
    h.textContent = title;
    s.appendChild(h);
    items.forEach(({ ok, text }) => {
      const item = document.createElement('div');
      item.className = `validation-item ${ok ? 'passed' : failClass}`;
      item.textContent = text;
      s.appendChild(item);
    });
    panel.appendChild(s);
  };
  if (result.errors.length) section('🛑 Errors de compilació', result.errors.map(e => ({ ok: false, text: '❌ ' + e })));
  if (result.warnings.length) section('⚠️ Avisos', result.warnings.map(w => ({ ok: false, text: '⚠️ ' + w })), 'warning');
  const count = list => `${list.filter(c => c.ok).length}/${list.length}`;
  section(`📝 Codi SCSS (${count(scss)})`, scss);
  section(`🎨 CSS compilat (${count(css)})`, css);
}

function scheduleLiveCheck() {
  clearTimeout(liveTimeout);
  liveTimeout = setTimeout(() => {
    team().code[challenges[currentChallenge].id] = el('code-editor').value;
    saveState();
    evaluateChallenge();
  }, 350);
}

function onCompile() {
  const { result } = evaluateChallenge();
  result.errors.forEach(e => log('❌ ' + e, 'error'));
  result.warnings.forEach(w => log('⚠️ ' + w, 'warning'));
  if (!result.errors.length) {
    log('⚡ Compilat correctament. CSS generat:', 'success');
    log(result.css || '/* (cap regla CSS) */', 'info', true);
  }
}

function onValidate() {
  if (state.finished) return;
  const { result, scss, css, allPassed } = evaluateChallenge();
  if (allPassed) { completeChallenge(); return; }
  if (result.errors.length) {
    log(`❌ El codi té ${result.errors.length} error(s) de compilació. Corregeix-los primer:`, 'error');
    result.errors.forEach(e => log('   ' + e, 'error'));
    return;
  }
  const failed = [...scss, ...css].filter(c => !c.ok);
  log(`🔒 Encara falten ${failed.length} validacions:`, 'warning');
  failed.slice(0, 5).forEach(c => log('   ' + c.text, 'warning'));
  if (failed.length > 5) log(`   ... i ${failed.length - 5} més (mira el panell de validacions).`, 'warning');
}

// ----------------------------------------------------------
// Reptes
// ----------------------------------------------------------
function loadChallenge(index) {
  currentChallenge = Math.min(index, challenges.length - 1);
  const challenge = challenges[currentChallenge];
  const t = team();

  el('challenge-title').textContent = challenge.title;
  el('challenge-badge').textContent = challenge.badge;
  el('challenge-description').textContent = challenge.description;
  el('hint-text').textContent = challenge.hint;
  const hintUsed = !!t.hints[challenge.id];
  el('challenge-hint').classList.toggle('hidden', !hintUsed);
  el('show-hint-btn').disabled = hintUsed;
  el('show-hint-btn').textContent = hintUsed ? 'Pista utilitzada' : `Mostrar pista (-${HINT_PENALTY} punts)`;

  el('code-editor').value = t.code[challenge.id] ?? challenge.starterCode;
  el('code-editor').readOnly = !!state.finished;
  updateProgress();
  evaluateChallenge();
  log(`📜 ${challenge.title} (${challenge.points} punts)`, 'info');
}

function updateProgress() {
  const done = Object.keys(team().completed).length;
  el('current-challenge').textContent = currentChallenge + 1;
  el('total-challenges').textContent = challenges.length;
  el('progress-fill').style.width = `${(done / challenges.length) * 100}%`;
}

function showHint() {
  const challenge = challenges[currentChallenge];
  const t = team();
  if (t.hints[challenge.id]) return;
  if (!confirm(`Segur que vols veure la pista? Perdreu ${HINT_PENALTY} punts.`)) return;
  t.hints[challenge.id] = true;
  t.points -= HINT_PENALTY;
  saveState();
  updateScores();
  el('challenge-hint').classList.remove('hidden');
  el('show-hint-btn').disabled = true;
  el('show-hint-btn').textContent = 'Pista utilitzada';
  log(`💡 Pista desbloquejada (-${HINT_PENALTY} punts)`, 'warning');
}

function completeChallenge() {
  const challenge = challenges[currentChallenge];
  const t = team();
  t.code[challenge.id] = el('code-editor').value;
  let earned = 0;
  if (!t.completed[challenge.id]) {
    earned = challenge.points;
    t.points += earned;
    t.completed[challenge.id] = true;
  }
  t.current = Math.max(t.current, currentChallenge + 1);
  saveState();
  updateScores();
  updateProgress();

  const isLast = currentChallenge >= challenges.length - 1;
  el('points-earned').textContent = earned;
  el('victory-message').textContent = isLast
    ? 'Heu completat tots els reptes de l\'escape room!'
    : 'Has desbloquejat el següent repte!';
  el('next-challenge-btn').textContent = isLast ? 'Veure puntuacions 🏆' : 'Següent Repte →';
  el('victory-modal').classList.remove('hidden');
  log(`🎉 ${challenge.title} completat! +${earned} punts`, 'success');
}

function nextChallenge() {
  el('victory-modal').classList.add('hidden');
  if (currentChallenge >= challenges.length - 1) { showEndModal('all'); return; }
  loadChallenge(currentChallenge + 1);
}

// ----------------------------------------------------------
// Equips, puntuacions i temporitzador
// ----------------------------------------------------------
function selectTeam(n) {
  currentTeam = String(n);
  sessionStorage.setItem(TEAM_KEY, currentTeam);
  if (!state.startTime) state.startTime = Date.now();
  saveState();

  el('team-selector').classList.add('hidden');
  el('game-main').classList.remove('hidden');
  el('console-output').innerHTML = '';
  log(`Benvinguts, Equip ${currentTeam}! Escriviu el codi SCSS i premeu ✅ Validar quan totes les validacions estiguin en verd.`, 'info');

  updateScores();
  startTimer();
  loadChallenge(team().current);
  if (team().current >= challenges.length) showEndModal('all');
}

function updateScores() {
  [1, 2, 3].forEach(n => {
    el(`team${n}-points`).textContent = state.teams[n].points;
    document.querySelector(`.team-${n}`).classList.toggle('active', String(n) === currentTeam);
  });
}

function startTimer() {
  if (timerInterval) return;
  const tick = () => {
    const remaining = state.startTime ? state.startTime + GAME_DURATION_MS - Date.now() : GAME_DURATION_MS;
    const s = Math.max(0, Math.floor(remaining / 1000));
    const pad = v => String(v).padStart(2, '0');
    el('time-remaining').textContent = `${pad(Math.floor(s / 3600))}:${pad(Math.floor(s / 60) % 60)}:${pad(s % 60)}`;
    if (remaining <= 0 && state.startTime) {
      clearInterval(timerInterval);
      if (!state.finished) { state.finished = true; saveState(); }
      el('code-editor').readOnly = true;
      showEndModal('time');
    }
  };
  tick();
  timerInterval = setInterval(tick, 1000);
}

function showEndModal(reason) {
  el('victory-modal').classList.add('hidden');
  document.querySelector('#end-modal h2').textContent =
    reason === 'time' ? '🏆 Temps Completat!' : '🏆 Reptes Completats!';
  const points = [1, 2, 3].map(n => state.teams[n].points);
  [1, 2, 3].forEach(n => { el(`final-team${n}`).textContent = state.teams[n].points; });
  const max = Math.max(...points);
  const winners = [1, 2, 3].filter(n => state.teams[n].points === max);
  el('winner-announcement').textContent = max <= 0
    ? 'Encara no hi ha cap guanyador'
    : winners.length > 1
      ? `🤝 Empat entre els equips ${winners.join(' i ')} amb ${max} punts`
      : `👑 Guanya l'Equip ${winners[0]} amb ${max} punts!`;
  el('end-modal').classList.remove('hidden');
}

function restartGame() {
  if (!confirm('Segur que vols reiniciar el joc? S\'esborraran totes les puntuacions i el codi de TOTS els equips.')) return;
  localStorage.removeItem(STORAGE_KEY);
  sessionStorage.removeItem(TEAM_KEY);
  location.reload();
}

// ----------------------------------------------------------
// Editor: Tab insereix espais
// ----------------------------------------------------------
function onEditorKeydown(e) {
  if (e.key !== 'Tab') return;
  e.preventDefault();
  const ed = e.target;
  const { selectionStart: s, selectionEnd: end, value } = ed;
  ed.value = value.slice(0, s) + '  ' + value.slice(end);
  ed.selectionStart = ed.selectionEnd = s + 2;
  scheduleLiveCheck();
}

// ----------------------------------------------------------
// Inicialització
// ----------------------------------------------------------
function init() {
  el('total-challenges').textContent = challenges.length;
  document.querySelectorAll('.btn-team').forEach(btn =>
    btn.addEventListener('click', () => selectTeam(btn.dataset.team)));
  document.querySelectorAll('.teams-score .team').forEach((box, i) =>
    box.addEventListener('click', () => {
      const n = String(i + 1);
      if (!currentTeam || n === currentTeam) return;
      if (confirm(`Canviar a l'Equip ${n}?`)) selectTeam(n);
    }));

  el('compile-btn').addEventListener('click', onCompile);
  el('validate-btn').addEventListener('click', onValidate);
  el('show-hint-btn').addEventListener('click', showHint);
  el('clear-console').addEventListener('click', () => { el('console-output').innerHTML = ''; });
  el('next-challenge-btn').addEventListener('click', nextChallenge);
  el('restart-btn').addEventListener('click', restartGame);
  el('code-editor').addEventListener('input', scheduleLiveCheck);
  el('code-editor').addEventListener('keydown', onEditorKeydown);

  window.addEventListener('storage', e => {
    if (e.key !== STORAGE_KEY) return;
    const fresh = loadState();
    if (currentTeam) fresh.teams[currentTeam] = state.teams[currentTeam];
    state = fresh;
    updateScores();
  });

  updateScores();
  if (state.startTime) startTimer();
  const savedTeam = sessionStorage.getItem(TEAM_KEY);
  if (savedTeam && state.teams[savedTeam]) selectTeam(savedTeam);
}

init();
