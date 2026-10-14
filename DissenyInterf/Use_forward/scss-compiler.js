// ==========================================================
// MINI-COMPILADOR SCSS → CSS (simulador per a l'escape room)
// Suporta: variables (!default), operacions, math.div, nesting amb &,
// BEM, mapes i llistes (map-get / list.nth, també niats), interpolació #{ },
// lighten / darken / rgba, i mòduls simulats amb @use (as, with) i @forward (show / hide).
// Els "mòduls" s'escriuen al mateix fitxer: les seves variables es declaren
// al codi i s'hi accedeix amb el namespace (colors.$color-primari).
// ==========================================================

const NAMED_COLORS = {
  white:'#ffffff', black:'#000000', red:'#ff0000', green:'#008000', blue:'#0000ff',
  yellow:'#ffff00', orange:'#ffa500', purple:'#800080', navy:'#000080', teal:'#008080',
  brown:'#a52a2a', gray:'#808080', grey:'#808080', silver:'#c0c0c0', gold:'#ffd700',
  beige:'#f5f5dc', coral:'#ff7f50', skyblue:'#87ceeb', cyan:'#00ffff', aqua:'#00ffff', pink:'#ffc0cb',
  maroon:'#800000', wheat:'#f5deb3', lime:'#00ff00', crimson:'#dc143c', indigo:'#4b0082',
  violet:'#ee82ee', turquoise:'#40e0d0', salmon:'#fa8072', darkorange:'#ff8c00',
  forestgreen:'#228b22', dodgerblue:'#1e90ff', lightblue:'#add8e6', darkred:'#8b0000',
  darkblue:'#00008b', darkgray:'#a9a9a9', lightgray:'#d3d3d3', snow:'#fffafa',
  burlywood:'#deb887', tan:'#d2b48c', midnightblue:'#191970', cadetblue:'#5f9ea0',
  darkcyan:'#008b8b', mediumpurple:'#9370db', paleturquoise:'#afeeee', firebrick:'#b22222',
  goldenrod:'#daa520', tomato:'#ff6347', slategray:'#708090', dimgray:'#696969',
  dimgrey:'#696969', lemonchiffon:'#fffacd', khaki:'#f0e68c', olive:'#808000', magenta:'#ff00ff',
  fuchsia:'#ff00ff', orchid:'#da70d6', plum:'#dda0dd', chocolate:'#d2691e', sienna:'#a0522d',
  seagreen:'#2e8b57', steelblue:'#4682b4', royalblue:'#4169e1', hotpink:'#ff69b4'
};

let colorCtx = null;
try {
  if (typeof document !== 'undefined') colorCtx = document.createElement('canvas').getContext('2d');
} catch (e) { colorCtx = null; }

function rgbToHex(r, g, b) {
  return '#' + [r, g, b].map(v => Math.max(0, Math.min(255, Math.round(v))).toString(16).padStart(2, '0')).join('');
}
const hexToRgb = h => { const n = parseInt(h.slice(1), 16); return [(n >> 16) & 255, (n >> 8) & 255, n & 255]; };

function colorToHex(value) {
  if (!value) return null;
  const v = String(value).trim().toLowerCase();
  if (NAMED_COLORS[v]) return NAMED_COLORS[v];
  if (/^#[0-9a-f]{6}$/.test(v)) return v;
  if (/^#[0-9a-f]{3}$/.test(v)) return '#' + v.slice(1).split('').map(c => c + c).join('');
  const rgb = v.match(/^rgba?\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)/);
  if (rgb) return rgbToHex(+rgb[1], +rgb[2], +rgb[3]);
  if (colorCtx && /^[a-z]+$/.test(v)) {
    // Qualsevol altre nom de color CSS vàlid el resol el navegador
    colorCtx.fillStyle = '#000000'; colorCtx.fillStyle = v; const a = colorCtx.fillStyle;
    colorCtx.fillStyle = '#ffffff'; colorCtx.fillStyle = v; const b = colorCtx.fillStyle;
    if (a === b && a.startsWith('#')) return a;
  }
  return null;
}

function rgbToHsl(r, g, b) {
  r /= 255; g /= 255; b /= 255;
  const mx = Math.max(r, g, b), mn = Math.min(r, g, b), l = (mx + mn) / 2, d = mx - mn;
  let h = 0, s = 0;
  if (d) {
    s = l > 0.5 ? d / (2 - mx - mn) : d / (mx + mn);
    if (mx === r) h = (g - b) / d + (g < b ? 6 : 0);
    else if (mx === g) h = (b - r) / d + 2;
    else h = (r - g) / d + 4;
    h *= 60;
  }
  return [h, s * 100, l * 100];
}
function hslToRgb(h, s, l) {
  h = ((h % 360) + 360) % 360; s /= 100; l /= 100;
  const c = (1 - Math.abs(2 * l - 1)) * s, x = c * (1 - Math.abs((h / 60) % 2 - 1)), m = l - c / 2;
  let r = 0, g = 0, b = 0;
  if (h < 60) { r = c; g = x; } else if (h < 120) { r = x; g = c; } else if (h < 180) { g = c; b = x; }
  else if (h < 240) { g = x; b = c; } else if (h < 300) { r = x; b = c; } else { r = c; b = x; }
  return [(r + m) * 255, (g + m) * 255, (b + m) * 255];
}
function adjustColor(color, pct, dir) {
  const hex = colorToHex(color);
  if (!hex) return null;
  const [h, s, l] = rgbToHsl(...hexToRgb(hex));
  return rgbToHex(...hslToRgb(h, s, Math.max(0, Math.min(100, l + dir * pct))));
}

// Un argument de color: un nom, un hex o rgb()/hsl(). No pot contenir una altra funció,
// així s'avaluen primer les funcions més internes: darken(lighten(orange, 20%), 10%).
const COLOR_ARG = '([a-z]+|#[0-9a-f]{3,8}|(?:rgba?|hsla?)\\([^()]*\\))';

function evalColorFunctions(value, ctx) {
  const reAdjust = new RegExp(`\\b(lighten|darken)\\(\\s*${COLOR_ARG}\\s*,\\s*(-?[\\d.]+)%?\\s*\\)`, 'gi');
  const reRgba = new RegExp(`\\brgba?\\(\\s*${COLOR_ARG}\\s*,\\s*([\\d.]+%?)\\s*\\)`, 'gi');
  value = value.replace(reAdjust, (m, fn, col, pct) => {
    const r = adjustColor(col, parseFloat(pct), fn.toLowerCase() === 'lighten' ? 1 : -1);
    if (!r && ctx) ctx.errors.add(`${fn}(): "${col}" no és un color vàlid.`);
    return r || m;
  });
  value = value.replace(reRgba, (m, col, a) => {
    const hex = colorToHex(col);
    if (!hex) return m;
    const [r, g, b] = hexToRgb(hex);
    return `rgba(${r}, ${g}, ${b}, ${a})`;
  });
  return value;
}

// ----- utilitats de text -----
function stripComments(src) {
  let out = '', i = 0, quote = null;
  while (i < src.length) {
    const c = src[i], n = src[i + 1];
    if (quote) { out += c; if (c === '\\') { out += n || ''; i += 2; continue; } if (c === quote) quote = null; i++; continue; }
    if (c === '"' || c === "'") { quote = c; out += c; i++; continue; }
    if (c === '/' && n === '*') { const end = src.indexOf('*/', i + 2); i = end === -1 ? src.length : end + 2; continue; }
    if (c === '/' && n === '/' && src[i - 1] !== ':') { while (i < src.length && src[i] !== '\n') i++; continue; }
    out += c; i++;
  }
  return out;
}

function splitTop(text, sepRe) {
  const out = []; let depth = 0, cur = '', quote = null;
  for (const ch of text) {
    if (quote) { cur += ch; if (ch === quote) quote = null; continue; }
    if (ch === '"' || ch === "'") { quote = ch; cur += ch; continue; }
    if ('([{'.includes(ch)) depth++;
    else if (')]}'.includes(ch)) depth--;
    if (depth === 0 && sepRe.test(ch)) { if (cur.trim()) out.push(cur.trim()); cur = ''; continue; }
    cur += ch;
  }
  if (cur.trim()) out.push(cur.trim());
  return out;
}

const unquote = s => String(s).trim().replace(/^(['"])([\s\S]*)\1$/, '$2');
const isMap = v => v !== null && typeof v === 'object' && !Array.isArray(v);

function isWrapped(raw) {
  if (raw[0] !== '(' || raw[raw.length - 1] !== ')') return false;
  let depth = 0;
  for (let i = 0; i < raw.length; i++) {
    if (raw[i] === '(') depth++;
    else if (raw[i] === ')') { depth--; if (depth === 0 && i < raw.length - 1) return false; }
  }
  return true;
}

const MAP_KEY_RE = /^(['"][^'"]*['"]|\$?[\w\u00C0-\uFFFF-]+)\s*:/;

// Converteix el text d'un valor en string, llista (Array) o mapa (objecte)
function parseValue(raw) {
  raw = raw.trim();
  if (isWrapped(raw)) {
    const inner = raw.slice(1, -1);
    const parts = splitTop(inner, /,/);
    if (parts.length && parts.every(p => MAP_KEY_RE.test(p))) {
      const map = {};
      parts.forEach(p => {
        const i = p.indexOf(':');
        map[unquote(p.slice(0, i))] = parseValue(p.slice(i + 1));
      });
      return map;
    }
    if (parts.length > 1) return parts.map(parseValue);
    if (parts.length === 1 && /[\s,]/.test(parts[0]) && !/^[\w-]+\(/.test(parts[0])) return parseValue(parts[0]);
  }
  return raw;
}

function asList(v) {
  if (Array.isArray(v)) return v;
  if (isMap(v)) return Object.entries(v).map(([k, val]) => [k, val]);
  const s = String(v);
  const byComma = splitTop(s, /,/);
  if (byComma.length > 1) return byComma.map(parseValue);
  return splitTop(s, /\s/).map(parseValue);
}

function toCss(v) {
  if (Array.isArray(v)) return v.map(x => Array.isArray(x) ? x.map(toCss).join(' ') : toCss(x)).join(', ');
  return String(v);
}

function mapLeaves(v, fn) {
  if (Array.isArray(v)) return v.map(x => mapLeaves(x, fn));
  if (isMap(v)) { const o = {}; for (const k in v) o[k] = mapLeaves(v[k], fn); return o; }
  return fn(v);
}

// ----- variables, map-get i list.nth -----
function resolveString(str, ctx) {
  for (let pass = 0; pass < 15; pass++) {
    const before = str;
    str = str.replace(/(map-get|map\.get|list\.nth|nth)\(\s*\$([\w-]+)\s*,\s*([^()]*?)\s*\)/g, (m, fn, name, args) => {
      const v = ctx.vars[name];
      if (v === undefined) return m;
      let result;
      if (fn.endsWith('nth')) {
        const list = asList(v);
        const i = parseInt(args, 10);
        if (isNaN(i) || i < 1 || i > list.length) {
          ctx.errors.add(`${fn}($${name}, ${args}): l'índex ha d'estar entre 1 i ${list.length} (a Sass les llistes comencen per 1).`);
          return m;
        }
        result = list[i - 1];
      } else {
        result = v;
        for (const key of splitTop(args, /,/).map(unquote)) {
          if (!isMap(result)) { ctx.errors.add(`${fn}(): $${name.startsWith('__') ? 'valor' : name} no és un mapa.`); return m; }
          if (!(key in result)) {
            ctx.errors.add(`${fn}(): la clau '${key}' no existeix al mapa. Claus disponibles: ${Object.keys(result).map(k => `'${k}'`).join(', ')}.`);
            return m;
          }
          result = result[key];
        }
      }
      if (typeof result === 'string') return result;
      const t = '__t' + (ctx.temp++);
      ctx.vars[t] = result;
      return '$' + t;
    });
    str = str.replace(/\$([\w-]+)/g, (m, name, offset, whole) => {
      const v = ctx.vars[name];
      if (v === undefined) return m;
      if (typeof v === 'string') return v;
      // Els mapes i llistes es deixen com a referència si són l'argument d'una funció
      if (/(map-get|map\.get|list\.nth|nth|length|list\.length)\(\s*$/.test(whole.slice(0, offset))) return m;
      if (Array.isArray(v)) return toCss(v);
      return m;
    });
    if (str === before) break;
  }
  return str;
}

function resolveRaw(raw, ctx) {
  const t = raw.trim();
  const direct = /^\$([\w-]+)$/.exec(t);
  if (direct && ctx.vars[direct[1]] !== undefined && typeof ctx.vars[direct[1]] !== 'string') return ctx.vars[direct[1]];
  const resolved = mapLeaves(parseValue(t), s => resolveString(s, ctx));
  if (typeof resolved === 'string') {
    const tmp = /^\$(__t\d+)$/.exec(resolved.trim());
    if (tmp) return ctx.vars[tmp[1]];
  }
  return resolved;
}

// ----- operacions matemàtiques (només dins dels valors) -----
const UNITS = 'px|rem|em|%|vh|vw|vmin|vmax|deg|turn|ms|s|fr|pt|ch|ex|cm|mm|in';
const NUM = `((?<![\\w#.\\-])-?(?:\\d+\\.?\\d*|\\.\\d+)(?:${UNITS})?(?![\\w%.]))`;

function parseNum(s) {
  const m = /^(-?(?:\d+\.?\d*|\.\d+))([a-z%]*)$/i.exec(String(s).trim());
  return m ? { n: parseFloat(m[1]), u: m[2].toLowerCase() } : null;
}
const fmt = n => String(parseFloat(n.toFixed(5)));

function numOp(a, b, op, ctx) {
  const A = parseNum(a), B = parseNum(b);
  if (!A || !B) return null;
  if ((op === '+' || op === '-') && A.u && B.u && A.u !== B.u) return null; // p. ex. 33% - 20px es queda com a calc
  if (op === '*' && A.u && B.u) { if (ctx) ctx.errors.add(`No es poden multiplicar dues mides amb unitats (${a} * ${b}).`); return null; }
  if (op === '/' && B.n === 0) { if (ctx) ctx.errors.add('Divisió per zero a math.div().'); return null; }
  const r = op === '*' ? A.n * B.n : op === '/' ? A.n / B.n : op === '+' ? A.n + B.n : A.n - B.n;
  let unit = A.u || B.u;
  if (op === '/') unit = A.u === B.u ? '' : (A.u || '');
  return fmt(r) + unit;
}

function evalMath(value, ctx) {
  value = value.replace(/\bmath\.div\(\s*([^,()]+?)\s*,\s*([^,()]+?)\s*\)/g, (m, a, b) => numOp(a, b, '/', ctx) ?? m);
  value = value.replace(/\b(?:math\.)?percentage\(\s*([^,()]+?)\s*\)/g, (m, a) => {
    const A = parseNum(a); return A && !A.u ? fmt(A.n * 100) + '%' : m;
  });
  value = value.replace(/\bmath\.(round|ceil|floor|abs)\(\s*([^,()]+?)\s*\)/g, (m, fn, a) => {
    const A = parseNum(a); return A ? fmt(Math[fn](A.n)) + A.u : m;
  });
  return value;
}

function evalArithmetic(value, ctx) {
  const mul = new RegExp(`${NUM}\\s*\\*\\s*${NUM}`, 'gi');
  const add = new RegExp(`${NUM}\\s*\\+\\s*${NUM}`, 'gi');
  const sub = new RegExp(`${NUM}\\s+-\\s+${NUM}`, 'gi');
  const paren = new RegExp(`(?<![\\w-])\\(\\s*${NUM}\\s*\\)`, 'gi');
  for (let k = 0; k < 10; k++) {
    const before = value;
    value = value.replace(mul, (m, a, b) => numOp(a, b, '*', ctx) ?? m);
    if (value === before) {
      value = value.replace(add, (m, a, b) => numOp(a, b, '+', ctx) ?? m);
      value = value.replace(sub, (m, a, b) => numOp(a, b, '-', ctx) ?? m);
    }
    value = value.replace(paren, (m, a) => a);
    if (value === before) break;
  }
  return value;
}

function evalValue(v, ctx) {
  for (let i = 0; i < 12; i++) {
    const before = v;
    v = evalMath(v, ctx);
    v = evalArithmetic(v, ctx);
    v = evalColorFunctions(v, ctx);
    if (v === before) break;
  }
  return v.replace(/\s+/g, ' ').trim();
}

// ----- @use / @forward -----
function moduleName(url) {
  if (url.startsWith('sass:')) return url.slice(5);
  return url.split('/').pop().replace(/^_/, '').replace(/\.s[ac]ss$/, '');
}

function extractModuleRules(code, mod, ctx) {
  let out = '', i = 0;
  while (i < code.length) {
    const m = /@(use|forward|import)\b/g;
    m.lastIndex = i;
    const found = m.exec(code);
    if (!found) { out += code.slice(i); break; }
    out += code.slice(i, found.index);
    let j = found.index + found[0].length, depth = 0, quote = null, endedBySemicolon = false;
    for (; j < code.length; j++) {
      const c = code[j];
      if (quote) { if (c === quote) quote = null; continue; }
      if (c === '"' || c === "'") { quote = c; continue; }
      if (c === '(') depth++;
      else if (c === ')') depth--;
      else if (depth <= 0 && c === ';') { endedBySemicolon = true; break; }
      else if (depth <= 0 && (c === '\n' || c === '{' || c === '}')) break;
    }
    const stmt = code.slice(found.index, j).trim();
    if (!endedBySemicolon) ctx.errors.add(`Falta el ';' al final de: ${stmt}`);
    parseModuleRule(found[1], stmt.slice(found[0].length).trim(), stmt, mod, ctx);
    i = endedBySemicolon ? j + 1 : j;
  }
  return out;
}

function parseModuleRule(kind, rest, stmt, mod, ctx) {
  if (kind === 'import') {
    ctx.warnings.add(`@import està obsolet. Utilitza @use (${stmt}).`);
    return;
  }
  const um = /^(['"])([^'"]+)\1\s*([\s\S]*)$/.exec(rest);
  if (!um) {
    ctx.errors.add(`@${kind} mal escrit: "${stmt}". El nom del mòdul va entre cometes, p. ex. @${kind} 'colors';`);
    return;
  }
  const url = um[2], opts = um[3].trim(), name = moduleName(url);
  if (kind === 'use') {
    const as = /^as\s+([\w-]+|\*)/.exec(opts);
    const ns = as ? as[1] : name;
    if (url.startsWith('sass:')) mod.sass[ns] = name;
    else mod.uses[ns] = name;
    const w = /\bwith\s*(\([\s\S]*\))\s*$/.exec(opts);
    if (w) {
      const parsed = parseValue(w[1]);
      if (!isMap(parsed)) ctx.errors.add(`El "with" de @use '${url}' ha de ser un mapa: with ($variable: valor, ...)`);
      else for (const k in parsed) {
        if (!k.startsWith('$')) ctx.warnings.add(`Dins de with ( ) les claus han de ser variables amb $: "${k}" → "$${k}"`);
        mod.config[k.replace(/^\$/, '')] = parsed[k];
      }
    }
    const leftover = opts.replace(/^as\s+([\w-]+|\*)/, '').replace(/\bwith\s*\([\s\S]*\)\s*$/, '').trim();
    if (leftover) ctx.errors.add(`No s'entén "${leftover}" a: ${stmt}`);
  } else {
    const sh = /^(show|hide)\s+([\s\S]+)$/.exec(opts);
    if (opts && !sh) ctx.errors.add(`No s'entén "${opts}" a: ${stmt}. Utilitza show o hide.`);
    mod.forwards[name] = {
      mode: sh ? sh[1] : null,
      names: sh ? new Set(splitTop(sh[2], /,/).map(s => s.trim().replace(/^\$/, ''))) : null
    };
  }
}

// ----- aplanar el nesting -----
function flattenScss(src, ctx) {
  let pos = 0;

  function resolveSel(sel, parents) {
    const parts = splitTop(sel, /,/);
    if (!parents.length) {
      if (parts.some(p => p.includes('&'))) ctx.errors.add(`"${sel}": & només es pot fer servir dins d'un altre selector.`);
      return parts;
    }
    const out = [];
    parents.forEach(par => parts.forEach(p => out.push(p.includes('&') ? p.replace(/&/g, par) : par + ' ' + p)));
    return out;
  }
  function emit(sels, decls) {
    if (!decls.length) return '';
    if (!sels.length) {
      decls.forEach(d => ctx.warnings.add(`Declaració fora de cap selector: "${d}"`));
      return '';
    }
    return sels.join(',\n') + ' {\n' + decls.map(d => '  ' + d + ';').join('\n') + '\n}\n\n';
  }
  function evalDecl(d) {
    const i = d.indexOf(':');
    if (i === -1) {
      if (/^@/.test(d)) ctx.warnings.add(`"${d}" no està suportat en aquest simulador.`);
      else ctx.errors.add(`Declaració incorrecta (falta ':'?): "${d}"`);
      return null;
    }
    const prop = d.slice(0, i).trim();
    const missing = /\n\s*([a-z-]+)\s*:/i.exec(d.slice(i + 1));
    if (missing) ctx.errors.add(`Sembla que falta un ';' al final de "${d.slice(0, d.indexOf('\n', i)).trim()}"`);
    const value = evalValue(d.slice(i + 1).trim(), ctx);
    if (!value) { ctx.errors.add(`La propietat "${prop}" no té valor.`); return null; }
    return `${prop}: ${value}`;
  }
  function readRaw() {
    let depth = 1, start = pos;
    while (pos < src.length && depth > 0) {
      if (src[pos] === '{') depth++;
      else if (src[pos] === '}') depth--;
      pos++;
    }
    return src.slice(start, pos - 1).trim();
  }
  function parseBlock(sels, depth) {
    let out = '', decls = [], buf = '', quote = null, paren = 0;
    const flush = () => { const d = buf.trim(); buf = ''; if (d) { const r = evalDecl(d); if (r) decls.push(r); } };
    while (pos < src.length) {
      const c = src[pos];
      if (quote) { buf += c; if (c === quote) quote = null; pos++; continue; }
      if (c === '"' || c === "'") { quote = c; buf += c; pos++; continue; }
      if (c === '(') paren++;
      else if (c === ')') paren--;
      if (c === '{' && paren <= 0) {
        const lines = buf.trim().split('\n');
        const sel = buf.trim().replace(/\s+/g, ' ');
        buf = ''; pos++;
        if (lines.length > 1 && /^[a-z-]+\s*:\s+\S/i.test(lines[0].trim())) {
          ctx.errors.add(`Sembla que falta un ';' després de "${lines[0].trim()}"`);
        }
        out += emit(sels, decls); decls = [];
        if (/^@(media|supports|container)\b/.test(sel)) {
          const inner = parseBlock(sels, depth + 1);
          if (inner.trim()) out += sel + ' {\n' + inner.trim().split('\n').map(l => l ? '  ' + l : l).join('\n') + '\n}\n\n';
        } else if (sel.startsWith('@')) {
          out += sel + ' {\n  ' + readRaw() + '\n}\n\n';
        } else {
          if (!sel) ctx.errors.add("Hi ha un bloc { } sense selector.");
          out += parseBlock(resolveSel(sel, sels), depth + 1);
        }
      } else if (c === '}') {
        pos++;
        if (depth === 0) { ctx.errors.add("Hi ha una clau '}' de més."); continue; }
        flush();
        return out + emit(sels, decls);
      } else if (c === ';' && paren <= 0) {
        pos++;
        flush();
      } else { buf += c; pos++; }
    }
    if (depth > 0) ctx.errors.add("Falta tancar alguna clau '}'.");
    flush();
    return out + emit(sels, decls);
  }
  return parseBlock([], 0);
}

// ----- funció principal -----
function compileSCSS(source) {
  const ctx = { vars: {}, errors: new Set(), warnings: new Set(), temp: 0 };
  const mod = { uses: {}, sass: {}, forwards: {}, config: {} };

  let code = stripComments(source);
  code = extractModuleRules(code, mod, ctx);

  // 1) namespaces: colors.$x → $x (els mòduls es simulen al mateix fitxer)
  code = code.replace(/(^|[^\w$-])([\w-]+)\.\$([\w-]+)/g, (m, pre, ns, v) => {
    const modName = mod.uses[ns];
    if (!modName) {
      if (mod.forwards[ns]) ctx.warnings.add(`Per fer servir ${ns}.$${v} cal carregar el mòdul amb @use '${ns}'; (el @forward només el reexporta).`);
      else ctx.warnings.add(`El namespace "${ns}" no existeix: cal @use '${ns}'; (o @use '...' as ${ns};) abans de fer servir ${ns}.$${v}.`);
    }
    const fw = mod.forwards[modName || ns];
    if (fw && fw.names) {
      const listed = fw.names.has(v);
      if ((fw.mode === 'show' && !listed) || (fw.mode === 'hide' && listed))
        ctx.warnings.add(`$${v} no està exposada pel @forward '${modName || ns}' ${fw.mode} ... En Sass real donaria error.`);
    }
    return pre + '$' + v;
  });

  // 2) mòduls integrats (sass:math, sass:list, sass:map) i els seus àlies
  for (const [ns, name] of Object.entries(mod.sass)) {
    if (ns !== name && ns !== '*') code = code.replace(new RegExp(`(^|[^\\w-])${ns}\\.(?=[\\w-]+\\()`, 'g'), `$1${name}.`);
  }
  const loaded = new Set(Object.values(mod.sass));
  for (const [name, example] of [['math', 'math.div()'], ['list', 'list.nth()'], ['map', 'map.get()']]) {
    if (new RegExp(`(^|[^\\w-])${name}\\.[\\w-]+\\(`).test(code) && !loaded.has(name))
      ctx.warnings.add(`Per fer servir ${example} cal afegir @use 'sass:${name}'; al principi.`);
  }

  // 3) variables (amb !default i configuració "with")
  const defaults = new Set(), declared = new Set();
  Object.assign(ctx.vars, mod.config);
  const declRe = /\$([\w-]+)\s*:\s*([^;{}]+?)((?:\s*!(?:default|global))*)\s*;/g;
  code = code.replace(declRe, (m, name, raw, flags) => {
    const isDefault = /!default/.test(flags);
    declared.add(name);
    if (name in mod.config) {
      if (isDefault) defaults.add(name);
      else ctx.warnings.add(`$${name} es configura amb "with", però al mòdul no té !default. En Sass real només es poden configurar les variables amb !default.`);
      return '';
    }
    if (isDefault && ctx.vars[name] !== undefined) return '';
    ctx.vars[name] = resolveRaw(raw, ctx);
    return '';
  });
  for (const k of Object.keys(mod.config)) {
    if (!declared.has(k)) ctx.warnings.add(`with ( ) configura $${k}, però aquesta variable no està definida al mòdul.`);
  }

  // 4) substituir variables, map-get, list.nth i interpolació #{ }
  code = resolveString(code, ctx);
  for (let i = 0; i < 5; i++) {
    const before = code;
    code = code.replace(/#\{([^{}]*)\}/g, (m, inner) => inner.includes('$') ? m : unquote(evalValue(inner, ctx)));
    if (code === before) break;
  }

  const leftovers = new Set(code.match(/\$[\w-]+/g) || []);
  const hadFunctionErrors = ctx.errors.size > 0;
  leftovers.forEach(v => {
    const val = ctx.vars[v.slice(1)];
    if (val === undefined) ctx.errors.add(`Variable no definida: ${v}`);
    else if (isMap(val) || Array.isArray(val)) {
      if (!hadFunctionErrors) ctx.errors.add(`${v} és un mapa o una llista: no es pot fer servir directament com a valor (utilitza map-get / list.nth).`);
    }
    else ctx.errors.add(`No s'ha pogut resoldre ${v}.`);
  });
  if (/#\{/.test(code)) ctx.errors.add('Hi ha una interpolació #{ } que no s\'ha pogut resoldre.');

  const unsupported = code.match(/@(mixin|include|each|for|if|else|function|return|extend|while)\b/g);
  if (unsupported) ctx.warnings.add(`${[...new Set(unsupported)].join(', ')} no està suportat en aquest simulador.`);

  // 5) aplanar nesting i avaluar valors
  const css = flattenScss(code, ctx).replace(/\n{3,}/g, '\n\n').trim();

  return { css, errors: [...ctx.errors], warnings: [...ctx.warnings] };
}

if (typeof module !== 'undefined') module.exports = { compileSCSS, stripComments };
