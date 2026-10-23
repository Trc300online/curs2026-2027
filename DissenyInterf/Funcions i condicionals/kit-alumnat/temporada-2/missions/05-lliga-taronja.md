# Etapa final. Illa Pummelo: la Lliga Taronja

**Temps:** 25-30 minuts. **Fitxers:** `src/scss/arxipelag/_accessibilitat.scss`, `_layout.scss` i `_lliga.scss`. **Trofeu:** Lliga Taronja. [Tornar al mapa](../README.md)

Temari: apartat 7.5 i exercicis 7.1 i 7.2.

Només podeu entrar-hi si teniu les quatre insígnies de l'Arxipèlag.

---

## Història

> *"Sóc en Drake, campió de la Lliga Taronja. Els entrenadors que arriben fins aquí saben fer servir `@extend`. El que vull veure és si saben **organitzar** l'herència: una biblioteca de patrons que qualsevol company pugui fer servir sense haver-la de llegir sencera, uns components que l'aprofitin i ni una sola dependència amagada. Cada vegada que acabeu una part, us faré una pregunta."*

---

## Concepte: placeholders en un altre fitxer

Les biblioteques de patrons es guarden en fitxers que **només contenen placeholders**. Com que un placeholder no genera res fins que algú l'estén, aquests fitxers no afegeixen ni un byte al CSS si ningú no els fa servir.

Perquè un fitxer pugui estendre un placeholder d'un altre fitxer, **ha de carregar-lo amb `@use`**. Els placeholders no porten espai de noms: s'estenen pel seu nom, sense prefix.

```scss
// _patrons.scss
%sense-estils-llista {
  list-style: none;
  margin: 0;
  padding: 0;
}

// _menu.scss
@use 'patrons';

.menu {
  @extend %sense-estils-llista;
}
```

Si `_menu.scss` no tingués el `@use 'patrons';`, Sass donaria l'error `The target selector was not found.`, encara que `arxipelag.scss` carregués tots dos fitxers.

Un placeholder també pot estendre un altre placeholder. Així es construeixen patrons a partir d'altres de més simples.

---

## Reglament de la Lliga

| Part | Fitxer | Què ha de contenir |
|---|---|---|
| **L.1** | `_accessibilitat.scss` | **Només placeholders:** `%ocult-visualment`, `%focus-visible`, `%reduir-moviment` i `%saltar-navegacio` |
| **L.2** | `_layout.scss` | **Només placeholders:** `%contenidor-centrat`, `%flex-centrat`, `%graella-basica` i `%centrat-absolut` |
| **L.3** | `_lliga.scss` | Els components de la pàgina, que estenen els placeholders de L.1 i L.2 |
| **L.4** | `_lliga.scss` | Les insígnies, generades amb un mapa i un `@each` |

Afegiu **només** `@use 'arxipelag/lliga';` a `arxipelag.scss`. Les dues biblioteques les carrega `_lliga.scss`.

Els **noms de les classes** estan fixats perquè la secció "Lliga Taronja" de `arxipelag.html` funcioni. La manera d'organitzar els placeholders és cosa vostra.

---

### L.1. Biblioteca d'accessibilitat (100 XP)

| Placeholder | Comportament que ha de tenir |
|---|---|
| `%ocult-visualment` | L'element no es veu a la pantalla, però un lector de pantalla el llegeix. No pot fer servir `display: none` ni `visibility: hidden` (amagarien el text també als lectors). Feu servir la tècnica del *clip rect* (temari, 7.2.1). |
| `%focus-visible` | Un anell de focus de `3px` de color `#005fcc` amb `outline-offset: 2px`, **només** quan s'arriba a l'element amb el teclat. |
| `%reduir-moviment` | Si l'usuari ha demanat menys moviment al sistema (`prefers-reduced-motion: reduce`), les animacions i transicions duren `0.01ms`. Feu servir `!important`, com a l'exercici 7.1 del temari. |
| `%saltar-navegacio` | Ha d'estendre `%ocult-visualment`. Quan rep el focus (`&:focus`), apareix fixat a dalt a l'esquerra, amb fons `#005fcc` i text blanc. |

Cada placeholder ha de tenir un **comentari** a sobre que expliqui què fa i quan s'ha de fer servir.

**Autocomprovació**
- [ ] Si a `arxipelag.scss` hi poseu temporalment `@use 'arxipelag/accessibilitat';`, el CSS no canvia (només hi ha placeholders). Després traieu la prova.
- [ ] Cada placeholder té el seu comentari.

**Pregunta del campió:** *"`%saltar-navegacio` estén `%ocult-visualment`. Quan `.enllac-saltar` estengui `%saltar-navegacio`, en quines regles del CSS apareixerà `.enllac-saltar`?"*

<details>
<summary>Pista 1 (suau). 70 XP</summary>

Mireu els exemples dels apartats 7.2.1 i 7.5.1 del temari: hi trobareu la tècnica per amagar text. Per al focus, recordeu la diferència entre `:focus` i `:focus-visible`.
</details>

<details>
<summary>Pista 2 (mitjana). 50 XP</summary>

- `%ocult-visualment`: `position: absolute`, mida de `1px`, `margin: -1px`, `overflow: hidden`, `clip: rect(0, 0, 0, 0)`, `white-space: nowrap` i sense vora ni padding.
- `%reduir-moviment`: a dins, una `@media (prefers-reduced-motion: reduce) { ... }` amb les tres propietats.
- `%saltar-navegacio`: primera línia `@extend %ocult-visualment;` i després un bloc `&:focus` que desfaci la mida, el `clip` i el `white-space`.
</details>

<details>
<summary>Pista 3 (forta). 30 XP</summary>

```scss
// %saltar-navegacio
// Enllaç invisible que apareix en rebre el focus. Primer element de la pàgina.
%saltar-navegacio {
  @extend ___;

  &:focus {
    position: fixed;
    top: 10px;
    left: 10px;
    width: auto;
    height: auto;
    margin: 0;
    padding: 10px 20px;
    clip: ___;
    white-space: ___;
    background: #005fcc;
    color: #fff;
    z-index: 9999;
  }
}
```
</details>

---

### L.2. Patrons de layout (100 XP)

| Placeholder | Comportament |
|---|---|
| `%contenidor-centrat` | Amplada màxima de `1100px`, centrat amb marges automàtics i amb `20px` de padding a esquerra i dreta. |
| `%flex-centrat` | Flexbox que centra el contingut en horitzontal i en vertical. |
| `%graella-basica` | `display: grid` amb un `gap` de `20px`. **No** decideix les columnes. |
| `%centrat-absolut` | Posició absoluta centrada respecte del contenidor (`top`, `left` i `transform`). |

Escriviu els noms dels placeholders **sense accents**. Cada placeholder ha de tenir el seu comentari.

**Autocomprovació**
- [ ] El fitxer només conté placeholders i comentaris.
- [ ] Cada placeholder té el seu comentari.

**Pregunta del campió:** *"Per què `%graella-basica` no inclou `grid-template-columns`? On s'ha de posar?"*

<details>
<summary>Pista 1 (suau). 70 XP</summary>

L'apartat 7.5.2 del temari té patrons molt semblants. Penseu què és idèntic a tots els llocs on es farà servir i què canviarà.
</details>

<details>
<summary>Pista 2 (mitjana). 50 XP</summary>

Per a `%centrat-absolut`: `position: absolute; top: 50%; left: 50%;` i un `transform` que desplaci l'element la meitat de la seva mida cap enrere.
</details>

<details>
<summary>Pista 3 (forta). 30 XP</summary>

```scss
// %centrat-absolut
// Centra un element posicionat respecte del seu contenidor.
%centrat-absolut {
  position: ___;
  top: 50%;
  left: ___;
  transform: translate(___, ___);
}
```
</details>

---

### L.3. Components (100 XP)

A `_lliga.scss`, carregueu les dues biblioteques amb `@use` i creeu aquests components:

| Classe | Estén | Propietats pròpies |
|---|---|---|
| `.pagina-lliga` | `%contenidor-centrat` | — |
| `.enllac-saltar` | `%saltar-navegacio` | — |
| `.text-alternatiu` | `%ocult-visualment` | — |
| `.boto-lliga` | `%focus-visible` i `%reduir-moviment` | `padding: 10px 20px`, sense vora, `border-radius: 4px`, fons `#e3350d`, text blanc, `cursor: pointer`, `transition: transform 0.2s`. A `:hover`, `transform: translateY(-2px)`. |
| `.galeria-illes` | `%graella-basica` | `grid-template-columns: repeat(auto-fill, minmax(200px, 1fr))` |
| `.escenari` | — | `position: relative`, `height: 200px`, fons `#f5f5f5` |
| `.escenari__trofeu` | `%centrat-absolut` | `padding: 20px 30px`, fons blanc, `border-radius: 8px` |
| `.banner-final` | `%flex-centrat` | `min-height: 120px`, fons `#fff4e6` |

I la **targeta**, amb BEM (exercici 7.2 del temari):
- Un placeholder `%targeta-base` amb fons blanc, `border-radius: 8px`, `padding: 20px` i `box-sizing: border-box`.
- `.targeta` estén `%targeta-base` i `%focus-visible`, i té `box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1)`.
- Els elements `.targeta__titol` (`margin: 0 0 10px`, `font-size: 18px`, color `#333`) i `.targeta__contingut` (`font-size: 14px`, `line-height: 1.5`, color `#666`).
- Els modificadors `.targeta--destacada` (vora de `3px` sòlida `#f08030`) i `.targeta--compacta` (`padding: 10px`).

**Autocomprovació**
- [ ] A `arxipelag.html`, si recarregueu la pàgina i premeu Tab una vegada, apareix l'enllaç "Salta al contingut principal" a dalt a l'esquerra.
- [ ] El text "contra el campió Drake" no es veu.
- [ ] Les targetes tenen títol, contingut i modificadors, i mostren l'anell blau amb Tab.
- [ ] El trofeu surt centrat a l'escenari i les insígnies, centrades al bàner (quan feu la L.4).
- [ ] Al CSS, `.targeta__titol` i `.targeta__contingut` tenen les seves regles (recordeu l'Illa Navel).

**Pregunta del campió:** *"Mireu el CSS: la regla de `prefers-reduced-motion` surt abans o després de la regla de `.boto-lliga` amb la `transition`? Per què, doncs, `%reduir-moviment` necessita `!important`?"*

<details>
<summary>Pista 1 (suau). 70 XP</summary>

Comenceu pels `@use` de les dues biblioteques. Si Sass diu `The target selector was not found`, reviseu el concepte d'aquesta etapa.
</details>

<details>
<summary>Pista 2 (mitjana). 50 XP</summary>

- A dalt de `_lliga.scss`: `@use 'accessibilitat';` i `@use 'layout';` (són a la mateixa carpeta).
- La targeta: `.targeta { @extend %targeta-base; @extend %focus-visible; ...; &__titol { ... } &__contingut { ... } &--destacada { ... } &--compacta { ... } }`.
</details>

<details>
<summary>Pista 3 (forta). 30 XP</summary>

```scss
@use 'accessibilitat';
@use 'layout';

.boto-lliga {
  @extend ___;
  @extend ___;
  padding: 10px 20px;
  // ...
  transition: transform 0.2s;

  &:hover {
    transform: ___;
  }
}

.escenari__trofeu {
  @extend ___;
  padding: 20px 30px;
  background: #fff;
  border-radius: 8px;
}
```
</details>

---

### L.4. Insígnies amb un bucle (100 XP)

A `_lliga.scss`:
1. Un placeholder `%insignia-base` amb `display: inline-block`, `padding: 6px 14px`, `border-radius: 999px`, `font-weight: bold` i color `#222`.
2. Un mapa `$insignies` amb `'corall': #ff7f7f`, `'robi': #f06292`, `'petxina': #c9a66b` i `'jade': #00a86b`.
3. Un `@each` que generi `.insignia-corall`, `.insignia-robi`, etc. Cada classe estén `%insignia-base` i té el seu `background`.

**Autocomprovació**
- [ ] Hi ha **una** regla amb `border-radius: 999px` i quatre selectors `.insignia-...`.
- [ ] Cada insígnia té la seva regla amb el seu color.

**Pregunta del campió:** *"Quantes regles genera el vostre bucle d'insígnies? Quantes en generaria si `%insignia-base` fos un mixin? Quan deixaria de valer la pena el placeholder?"*

<details>
<summary>Pista 1 (suau). 70 XP</summary>

És la mateixa idea que els botons per tipus de l'Illa Trovita, però amb un mapa més senzill: una clau i un color.
</details>

<details>
<summary>Pista 2 (mitjana). 50 XP</summary>

`@each $nom, $color in $insignies { .insignia-#{$nom} { @extend %insignia-base; background: $color; } }`
</details>

<details>
<summary>Pista 3 (forta). 30 XP</summary>

```scss
@each ___, $color in ___ {
  .insignia-#{___} {
    @extend %insignia-base;
    background: ___;
  }
}
```
</details>

---

## Restriccions de la Lliga (+50 XP cadascuna)

| Restricció | Com es comprova |
|---|---|
| **Cap `@extend` de classes reals.** | `Ctrl + Shift + F` a `src/scss/arxipelag` i busqueu `@extend .` (no hi ha d'haver cap resultat). |
| **Cap `@extend` dins d'una media query.** | Reviseu cada `@media` dels vostres fitxers. |
| **Biblioteques documentades.** | Tots els placeholders de `_accessibilitat.scss` i `_layout.scss` tenen un comentari que diu què fan i quan s'han de fer servir. |
| **`arxipelag.scss` net.** | Només conté línies `@use`. |

---

## Combat final contra en Drake

Quan tingueu L.1 a L.4 fetes, aixequeu la mà. En Drake (el professor) us farà **tres preguntes orals**: una sobre placeholders i agrupació de selectors, una sobre extend o mixin i una sobre els errors de l'Illa Kumquat. Si les supereu, rebreu el codi del trofeu.

> *"Una biblioteca neta, components que no depenen els uns dels altres i ni un byte repetit. Benvinguts al Saló dels Campions de l'Arxipèlag Taronja."*

---

## Tancament de la temporada

A la fitxa de progrés:
1. Sumeu l'XP i escriviu el vostre nivell.
2. Completeu el quadern de bitàcola.
3. Responeu breument: **quin canvi faríeu ara a la Pokédex CSS de la primera temporada, després del que heu après?**
