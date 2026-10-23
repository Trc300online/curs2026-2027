# Etapa 3. Illa Trovita: `@extend` o mixin?

**Temps:** 15 minuts. **Fitxers:** `src/scss/arxipelag/_trovita.scss` i `src/scss/proves/`. **Insígnia:** Petxina Punxeguda. [Tornar al mapa](../README.md)

Temari: apartat 7.3 i exercici 7.3.

Només podeu entrar-hi si teniu l'insígnia Robí Marí.

---

## Història

> *"Sóc en Rudy, líder del Gimnàs de l'Illa Trovita. El meu equip vol passar tots els botons de mixins a placeholders "perquè ocupa menys". Abans de canviar res, vull dues coses: saber en quins casos té sentit i dades reals de quant s'estalvia. Les opinions no guanyen combats; les proves, sí."*

---

## Preparació (no dona XP)

1. Creeu `src/scss/arxipelag/_trovita.scss` i afegiu `@use 'arxipelag/trovita';` a `arxipelag.scss`.
2. Creeu la carpeta `src/scss/proves/`. Ho farem servir a la missió 3.3.

---

## Concepte: quan triar cadascun

| | `@extend` + placeholder | Mixin |
|---|---|---|
| Què fa al CSS | **Agrupa selectors**: les propietats s'escriuen una vegada | **Copia** les propietats a cada classe |
| Admet arguments | No | Sí |
| Admet lògica (`@if`) segons qui el fa servir | No | Sí |
| Funciona dins d'una media query | Només si el placeholder és a la mateixa media query | Sí |
| Ideal per a | Codi **idèntic** que es repeteix: resets, anells de focus, text ocult, centrats | Patrons amb **variacions**: colors, mides, punts de ruptura |

I no són excloents. Un patró molt habitual és combinar-los: **la part fixa** en un placeholder i **la part que canvia** en un mapa amb un bucle (capítol 6):

```scss
@use 'sass:map';

%avis-base {
  padding: 12px;
  border-radius: 4px;
}

$avisos: (
  'exit': #2ecc71,
  'error': #e74c3c,
);

@each $nom, $color in $avisos {
  .avis-#{$nom} {
    @extend %avis-base;
    border-left: 4px solid $color;
  }
}
```

---

## Missió 3.1. Extend o mixin? (100 XP)

Per a cada cas, decidiu si faríeu servir **extend** o **mixin** i justifiqueu-ho en una frase a la taula de la fitxa de progrés:

1. Treure els estils per defecte de les llistes (`list-style: none; margin: 0; padding: 0;`) a cinc menús diferents.
2. Aplicar estils a partir d'un punt de ruptura que canvia segons on es fa servir.
3. Amagar text visualment però deixar-lo per als lectors de pantalla, a deu llocs.
4. Una ombra que pot tenir una intensitat de l'1 al 5.
5. Centrar el contingut amb flexbox exactament igual a vuit components.
6. Una graella en què cada component tria el nombre de columnes.
7. El mateix anell de focus per a tots els elements interactius de la web.
8. Uns estils base que també cal aplicar **dins** d'una media query, però que estan definits fora.

### Autocomprovació

- [ ] Heu omplert els vuit casos amb una justificació (no n'hi ha prou amb "extend" o "mixin").
- [ ] Les justificacions fan servir paraules com *idèntic*, *arguments*, *variacions* o *media query*.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Per a cada cas, feu-vos una sola pregunta: el codi és **exactament el mateix** cada vegada o **canvia** segons on es fa servir?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Si cal passar un valor (una intensitat, un nombre de columnes, un punt de ruptura), cal un argument, i els placeholders no en tenen. Reviseu també la fila "Funciona dins d'una media query" de la taula del concepte per al cas 8.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

Quatre dels vuit casos són per a extend i quatre per a mixin. Els casos 2, 4 i 6 necessiten arguments.
</details>

---

## Missió 3.2. Botons per tipus (100 XP)

A `_trovita.scss`:

1. Creeu el placeholder `%boto-pokemon` amb la part fixa de tots els botons:
   ```
   display: inline-block;
   padding: 8px 16px;
   border: none;
   border-radius: 999px;
   font-weight: bold;
   color: #222;
   cursor: pointer;
   ```
2. Creeu el mapa `$tipus-botons`. Cada tipus té un mapa amb el color `'base'` i el color `'hover'`:

| Tipus | `'base'` | `'hover'` |
|---|---|---|
| `'foc'` | `#f08030` | `#c0601c` |
| `'aigua'` | `#6890f0` | `#4a6fc4` |
| `'planta'` | `#78c850` | `#5a9a3a` |
| `'electric'` | `#f8d030` | `#c9a81f` |

3. Amb un `@each`, genereu `.boto-tipus-foc`, `.boto-tipus-aigua`, etc. Cada classe ha d'estendre `%boto-pokemon` i tenir el seu `background` i el seu `background` a `:hover`, llegits del mapa amb `map.get`.

### Autocomprovació

- [ ] Hi ha **una sola** regla amb `border-radius: 999px`, i té quatre selectors.
- [ ] `.boto-tipus-planta` té `background: #78c850` i el seu `:hover`, `#5a9a3a`.
- [ ] Si afegiu un cinquè tipus al mapa, l'única línia que cal tocar és la del mapa. Proveu-ho i després traieu-lo.
- [ ] A `arxipelag.html`, els quatre botons de l'Etapa 3 tenen estil.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

És com els mapes imbricats de l'Illa de Planta de la primera temporada. A cada volta de l'`@each` teniu el nom del tipus i un mapa de dos colors. Com en treieu un color concret?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

`@use 'sass:map';` a dalt. `@each $tipus, $tons in $tipus-botons`, i a dins: `.boto-tipus-#{$tipus} { @extend %boto-pokemon; background: map.get($tons, 'base'); &:hover { ... } }`.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
@use 'sass:map';

%boto-pokemon {
  // ...
}

$tipus-botons: (
  'foc': ('base': #f08030, 'hover': #c0601c),
  // ...
);

@each $tipus, $tons in ___ {
  .boto-tipus-#{___} {
    @extend ___;
    background: map.get(___, 'base');

    &:hover {
      background: ___;
    }
  }
}
```
</details>

---

## Missió 3.3. Comparativa de mida (100 XP)

En Rudy vol dades. Creeu dos fitxers **d'entrada** (sense guió baix) a `src/scss/proves/`:

- `comparativa-mixin.scss`: un mixin `boto-base` amb les 15 propietats de sota i un `@for` que generi `.boto-1` fins a `.boto-50`. Cada classe inclou el mixin i té `order: $i;`.
- `comparativa-extend.scss`: el mateix, però amb un placeholder `%boto-base` i `@extend`.

Les 15 propietats:

```
display: inline-flex;
align-items: center;
justify-content: center;
gap: 8px;
padding: 10px 20px;
border: none;
border-radius: 6px;
font-family: inherit;
font-size: 16px;
font-weight: bold;
line-height: 1.2;
cursor: pointer;
box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
transition: background 0.3s, transform 0.2s;
user-select: none;
```

**Abans de compilar**, escriviu a la fitxa de progrés quin fitxer creieu que ocuparà menys i quina diferència aproximada hi haurà.

Compileu-los en un **segon terminal** (el watch de l'arxipèlag ha de continuar obert) i mesureu-ne la mida:

```powershell
npx sass src/scss/proves/comparativa-mixin.scss dist/css/proves/comparativa-mixin.css --style=expanded --no-source-map
npx sass src/scss/proves/comparativa-extend.scss dist/css/proves/comparativa-extend.css --style=expanded --no-source-map
(Get-Item dist/css/proves/comparativa-mixin.css).Length
(Get-Item dist/css/proves/comparativa-extend.css).Length
```

Repetiu-ho amb `--style=compressed` i completeu la taula de la fitxa de progrés. L'estalvi en percentatge es calcula així: `(mida mixin - mida extend) / mida mixin × 100`.

Per acabar, escriviu una **conclusió tècnica** de 3-4 línies per al Rudy: val la pena canviar els mixins per placeholders? En quins casos sí i en quins no?

### Autocomprovació

- [ ] Heu escrit la predicció abans de compilar.
- [ ] La taula té les quatre mides i els dos percentatges.
- [ ] A `comparativa-extend.css`, la base surt una sola vegada i té 50 selectors.
- [ ] La conclusió respon aquesta pregunta: si cada botó necessités un color diferent passat com a argument, podríeu fer servir només `@extend`?

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Els dos fitxers són gairebé iguals: només canvien la primera línia del patró (`@mixin` o `%`) i la línia de dins del bucle (`@include` o `@extend`).
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

`@for $i from 1 through 50 { .boto-#{$i} { ...; order: $i; } }`. Si la comanda `npx sass` dona error de ruta, comproveu que l'executeu des de la carpeta del projecte (on hi ha el `package.json`).
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
// comparativa-extend.scss
%boto-base {
  // les 15 propietats
}

@for $i from 1 ___ 50 {
  .boto-#{___} {
    ___ %boto-base;
    order: $i;
  }
}
```
</details>

---

## Repte bonus (+50 XP)

Repetiu la comparativa amb **5 botons** en lloc de 50 (canvieu només el número del bucle). L'estalvi en percentatge és el mateix? Per què? Escriviu-ho a la conclusió.

---

## Pregunta d'insígnia: Petxina Punxeguda

Responeu-la a la fitxa de progrés amb les vostres paraules i aixequeu la mà:

> **"Quan triaríeu `@extend` i quan un mixin? Doneu un exemple de cada cas fet servir en el vostre codi d'aquesta temporada o de l'anterior."**

---

## Quadern de bitàcola

Escriviu una línia per a *Quan faig servir `@extend`* i *Quan faig servir un mixin*.

> *"Dades, no opinions. Així m'agrada. Petxina Punxeguda. L'última illa abans de la Lliga és Kumquat: la Luana us ensenyarà les trampes de l'herència, que n'hi ha unes quantes."*

Següent: [04-illa-kumquat.md](04-illa-kumquat.md)
