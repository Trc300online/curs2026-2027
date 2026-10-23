# La Forja de Components. Segona temporada: l'Arxipèlag Taronja

> *"Heu guanyat la Lliga i la vostra Pokédex s'escriu sola. Però si obriu el CSS compilat, hi trobareu una cosa que un bon entrenador no pot deixar passar: blocs de propietats repetits una vegada i una altra. Els mixins són molt útils, però copien el codi a cada lloc on els incloeu. A l'Arxipèlag Taronja aprendreu una altra manera de reutilitzar estils: en lloc de copiar les propietats, els selectors les comparteixen. Agafeu la motxilla, que el vaixell surt ara."*
> — Professor Pi

---

## Què aprendreu

Aquesta temporada treballa el **capítol 7 del temari: herència amb `@extend` i placeholders**. En acabar, heu de ser capaços de:

1. Crear placeholders (`%nom`) i estendre'ls amb `@extend`.
2. Llegir el CSS compilat i explicar com Sass **agrupa els selectors** que comparteixen estils.
3. Estendre diversos placeholders alhora i heretar estats com `:hover` o `:focus-visible`.
4. Decidir, amb arguments, quan convé `@extend` i quan convé un mixin, i mesurar-ne la diferència de mida.
5. Reconèixer els errors habituals: estendre classes reals, estendre dins de media queries i l'ordre dels placeholders.
6. Construir una petita biblioteca de patrons (accessibilitat i layout) repartida en mòduls.

Només fareu servir el que heu treballat als capítols 1 a 7: variables, nesting i BEM, `@use`, mixins i `@content`, `@if`, `@for`, `@each`, mapes amb `map.get`, `@function` i `@warn`.

---

## Mapa de l'Arxipèlag

| Sessió | Etapa | Illa | Concepte | Fitxer | Temps | Fitxa |
|---|---|---|---|---|---|---|
| 1 | 0 | Illa València | Preparació | `src/scss/arxipelag.scss` | 5 min | [00-illa-valencia.md](missions/00-illa-valencia.md) |
| 1 | 1 | Illa Mikan | Placeholders i `@extend` | `arxipelag/_mikan.scss` | 10 min | [01-illa-mikan.md](missions/01-illa-mikan.md) |
| 1 | 2 | Illa Navel | Extend múltiple i estats | `arxipelag/_navel.scss` | 12 min | [02-illa-navel.md](missions/02-illa-navel.md) |
| 1 | 3 | Illa Trovita | `@extend` o mixin? | `arxipelag/_trovita.scss` i `proves/` | 15 min | [03-illa-trovita.md](missions/03-illa-trovita.md) |
| 1 | 4 | Illa Kumquat | Errors i bones pràctiques | `arxipelag/_kumquat.scss` | 13 min | [04-illa-kumquat.md](missions/04-illa-kumquat.md) |
| 2 | Final | Illa Pummelo | Lliga Taronja: biblioteca de patrons | `arxipelag/` | 25-30 min | [05-lliga-taronja.md](missions/05-lliga-taronja.md) |

Al principi de la sessió 2, rellegiu el vostre `progres-temporada-2.md` i acabeu el que us hagi quedat pendent de l'etapa 4.

---

## Normes del joc

Les normes són **les mateixes que a la primera temporada** (vegeu el [README de la temporada 1](../README.md)):

- Una missió cada vegada i en ordre.
- Primer es llegeix el concepte i després s'escriu el codi.
- Quan una missió demana una **predicció**, s'escriu a la fitxa de progrés **abans** de compilar.
- Les pistes s'obren d'una en una (suau, mitjana i forta) i s'apunta la més alta que s'ha obert.
- La solució la té el professor i la missió val 20 XP.
- L'error forma part del joc: llegiu-lo abans de tocar res.
- Sempre es compila i es mira el CSS.
- Res de copiar codi.

L'XP de cada missió també és la mateixa: 100 sense pistes, 70 / 50 / 30 segons la pista més alta oberta, 20 amb la solució, +10 si compila al primer intent i +50 per cada repte bonus.

**Nivells de la segona temporada** (l'XP torna a començar des de zero):

| Nivell | XP necessària |
|---|---|
| Mariner | 0 |
| Grumet | 200 |
| Timoner | 450 |
| Contramestre | 700 |
| Capità | 950 |
| Almirall | 1200 |
| Campió de la Lliga Taronja | 1400 |

**Insígnies:** cada illa amb gimnàs té la seva pregunta d'insígnia. Es respon a la fitxa de progrés i es demana la validació al professor, que us donarà el codi de l'insígnia. Sense insígnia no es pot continuar cap a l'illa següent.

| Illa | Insígnia |
|---|---|
| Mikan | Ull de Corall |
| Navel | Robí Marí |
| Trovita | Petxina Punxeguda |
| Kumquat | Estrella de Jade |
| Pummelo | Trofeu de la Lliga Taronja |

---

## Compilar aquesta temporada

La segona temporada té el seu propi fitxer d'entrada i el seu propi CSS (ho preparareu a l'etapa 0):

| Ordre | Per a què |
|---|---|
| `npm run arxipelag:watch` | Recompila cada vegada que deseu. El CSS surt expandit a `dist/css/arxipelag.css`. |
| `npm run arxipelag:build` | Genera el CSS comprimit, per mesurar quant ocupa. |

---

## Errors nous d'aquesta temporada

Completen la [guia d'errors de la primera temporada](../guia-errors.md).

| Missatge de Sass | Què vol dir |
|---|---|
| `The target selector was not found.` | Esteneu un placeholder que Sass no troba. O bé el nom està mal escrit (`%boto-bse`), o bé el placeholder és en un altre fitxer que **aquest** fitxer no carrega amb `@use`. Sass també suggereix `!optional`: no el feu servir, perquè amagaria l'error en lloc de resoldre'l. |
| `You may not @extend selectors across media queries.` | Hi ha un `@extend` **dins** d'una media query que intenta estendre un placeholder definit **fora**. |

**Trampes silencioses (no donen error):**

| Què passa | Per què |
|---|---|
| Un placeholder no surt al CSS | És normal: només surt si alguna classe l'estén. |
| Els selectors agrupats surten en un ordre diferent del que esperàveu | Sass els ordena a la seva manera. No afecta el resultat. |
| Una propietat de la classe queda "trepitjada" per la del placeholder | El placeholder està definit **després** de la classe al fitxer. Els placeholders van sempre a dalt. |
| Els estils d'un `&__element` escrit dins d'un placeholder no arriben a la classe | `&__element` dins d'un placeholder crea un placeholder nou, amb un altre nom. Ho veureu a l'etapa 2. |

---

## Estructura del projecte en acabar la temporada

```
Funcions i condicionals/
├── package.json                  (amb els scripts arxipelag:watch i arxipelag:build)
├── progres-temporada-2.md
├── dist/css/arxipelag.css
└── src/
    ├── arxipelag.html
    └── scss/
        ├── arxipelag.scss        (només @use)
        ├── arxipelag/
        │   ├── _mikan.scss
        │   ├── _navel.scss
        │   ├── _trovita.scss
        │   ├── _kumquat.scss
        │   ├── _accessibilitat.scss   (Lliga Taronja: només placeholders)
        │   ├── _layout.scss           (Lliga Taronja: només placeholders)
        │   └── _lliga.scss            (Lliga Taronja: components)
        └── proves/
            ├── comparativa-mixin.scss
            └── comparativa-extend.scss
```

Comenceu per [missions/00-illa-valencia.md](missions/00-illa-valencia.md).
