# Etapa 4. Illa Kumquat: errors i bones pràctiques

**Temps:** 13 minuts. **Fitxer:** `src/scss/arxipelag/_kumquat.scss`. **Insígnia:** Estrella de Jade. [Tornar al mapa](../README.md)

Temari: apartats 7.4 i 7.6.

Només podeu entrar-hi si teniu l'insígnia Petxina Punxeguda.

---

## Història

> *"Sóc la Luana, líder del Gimnàs de l'Illa Kumquat. Aquí es combat per parelles i, com en tot equip, el problema no és el que fa cadascú: és el que un fa sense saber que afecta l'altre. `@extend` és potent, però mal fet crea dependències que ningú no veu fins que és tard. Avui en provocareu tres de cara i aprendreu a evitar-les."*

---

## Preparació (no dona XP)

1. Creeu `src/scss/arxipelag/_kumquat.scss` i afegiu `@use 'arxipelag/kumquat';` a `arxipelag.scss`.

---

## Concepte: les tres regles de la Luana

1. **Només s'estenen placeholders**, mai classes reals. Una classe real pot rebre més regles en el futur (de context, de media queries, d'un company) i totes passarien a la classe que l'estén sense que ningú se n'adoni.
2. **`@extend` no travessa media queries.** Des de dins d'una media query no es pot estendre un placeholder definit fora. En aquest cas, la base ha de ser un **mixin**.
3. **Els placeholders van a dalt.** La regla agrupada surt al CSS al lloc on hi ha el placeholder. Si el placeholder és a sota, les seves propietats poden trepitjar les de la classe.

En aquesta etapa no us demanem que les apreneu de memòria: les provocareu i en veureu les conseqüències al CSS.

---

## Missió 4.1. L'acoblament amagat (100 XP)

**Pas 1.** Escriviu aquest codi a `_kumquat.scss` tal com està:

```scss
.boto-primari {
  padding: 10px 20px;
  background: #3b4cca;
  color: #fff;

  &:hover {
    background: #2a3a9e;
  }
}

.boto-promocio {
  @extend .boto-primari;
  font-size: 20px;
}
```

Deseu i mireu el CSS: quins selectors té la regla del `:hover`?

**Pas 2.** Un company, que no sap que existeix `.boto-promocio`, afegeix al final del fitxer:

```scss
.capcalera .boto-primari {
  margin-left: auto;
}
```

**Abans de desar**, escriviu a la fitxa de progrés: afectarà aquesta regla `.boto-promocio`?

Deseu i comproveu-ho al CSS i a la secció "Missió 4.1" de `arxipelag.html`.

**Pas 3.** Refactoritzeu-ho perquè `.boto-primari` i `.boto-promocio` comparteixin la base **sense** que la regla de la capçalera afecti el botó de promoció. La regla del company s'ha de quedar tal com està.

### Autocomprovació

- [ ] Heu escrit la predicció abans de desar.
- [ ] Al final, al fitxer no hi ha cap `@extend` seguit d'un punt (busqueu `@extend .` amb `Ctrl + F`).
- [ ] Al CSS, la regla `.capcalera .boto-primari` només té aquest selector.
- [ ] Al navegador, dins de la capçalera, Promoció queda a l'esquerra i Primari, a la dreta. (Al pas 2, Promoció sortia al mig: això era l'acoblament.)

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

El problema és que `.boto-promocio` hereta **tot** el que li passi a `.boto-primari`, també el que s'escrigui en el futur. Què podríeu estendre que ningú no faci servir mai directament a l'HTML ni en altres regles?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Moveu les propietats i el `&:hover` de `.boto-primari` a un placeholder `%boto-primari-base`. Després, feu que **totes dues** classes estenguin el placeholder.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
___boto-primari-base {
  padding: 10px 20px;
  background: #3b4cca;
  color: #fff;

  &:hover {
    background: #2a3a9e;
  }
}

.boto-primari {
  @extend ___;
}

.boto-promocio {
  @extend ___;
  font-size: 20px;
}

.capcalera .boto-primari {
  margin-left: auto;
}
```
</details>

---

## Missió 4.2. Extend i media queries (100 XP)

**Pas 1.** Afegiu aquest codi i deseu:

```scss
%boto-menu {
  padding: 8px 12px;
  border: 1px solid #ccc;
}

.menu-escriptori {
  @extend %boto-menu;
}

@media (min-width: 768px) {
  .menu-mobil {
    @extend %boto-menu;
  }
}
```

Llegiu l'error del terminal amb calma. Què diu? Quina línia marca com a *extension* i quina com a *extended selector*?

**Pas 2.** Arregleu-ho perquè `.menu-escriptori` i `.menu-mobil` (dins de la media query) tinguin les dues propietats. Feu servir la tècnica que el temari recomana per a aquests casos.

**Pas 3.** Ara un cas diferent. **Abans de desar**, escriviu a la fitxa de progrés si aquest codi donarà error:

```scss
%panell {
  padding: 10px;

  @media (min-width: 768px) {
    padding: 20px;
  }
}

.panell-illa {
  @extend %panell;
}
```

Deseu i comproveu-ho. Compareu-lo amb el pas 1: on és el `@extend` en cada cas?

### Autocomprovació

- [ ] Heu copiat a la fitxa de progrés la primera línia de l'error del pas 1.
- [ ] El pas 2 compila i `.menu-mobil` surt dins de `@media (min-width: 768px)` amb les dues propietats.
- [ ] Al pas 3, `.panell-illa` surt dues vegades al CSS: una fora i una dins de la media query.
- [ ] Podeu explicar en una frase quan dona error i quan no.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Al capítol 7 hi ha una pregunta freqüent sobre `@extend` i media queries. Què recomana fer servir en lloc de `@extend`?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Convertiu `%boto-menu` en `@mixin boto-menu { ... }` i canvieu els dos `@extend` per `@include boto-menu;`. Un mixin copia les propietats allà on es posa, també dins d'una media query.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
@mixin ___ {
  padding: 8px 12px;
  border: 1px solid #ccc;
}

.menu-escriptori {
  ___ boto-menu;
}

@media (min-width: 768px) {
  .menu-mobil {
    ___ boto-menu;
  }
}
```

Per al pas 3: l'error només apareix quan el `@extend` és **dins** d'una media query i el placeholder és **fora**.
</details>

---

## Missió 4.3. L'ordre importa (100 XP)

Afegiu aquest codi **al final** de `_kumquat.scss`, en aquest ordre:

```scss
.boto-tard {
  @extend %boto-tard-base;
  background: #e3350d;
}

%boto-tard-base {
  padding: 10px;
  background: #999;
}
```

**Abans de desar**, escriviu a la fitxa de progrés de quin color serà el fons de `.boto-tard`: vermell (`#e3350d`) o gris (`#999`)?

Deseu i comproveu-ho al CSS i al navegador. Mireu en quin ordre surten les regles.

Després arregleu-ho perquè el botó sigui vermell **sense tocar cap propietat**: només canviant coses de lloc.

### Autocomprovació

- [ ] Heu escrit la predicció abans de desar.
- [ ] Podeu explicar per què ha guanyat el color que ha guanyat (pista: especificitat i ordre al CSS).
- [ ] Al final, el botó és vermell i no heu canviat cap valor.
- [ ] Tots els placeholders de `_kumquat.scss` són a dalt del fitxer.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Les dues regles tenen el mateix selector (`.boto-tard`), així que tenen la mateixa especificitat. Quan passa això, quina regla guanya?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

La regla agrupada del placeholder surt al CSS **al lloc on és el placeholder**. Si el placeholder és a sota de la classe, el seu `background` surt després i guanya.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

Moveu el bloc `%boto-tard-base { ... }` a dalt de tot del fitxer, amb la resta de placeholders.
</details>

---

## Repte bonus (+50 XP)

Escriviu malament el nom d'un placeholder en un `@extend` (per exemple `@extend %boto-tard-bse;`). Llegiu l'error, copieu-ne la primera línia a la fitxa de progrés i expliqueu per què **no** heu de fer servir la solució que suggereix Sass (`!optional`). Després, torneu-lo a deixar bé.

---

## Pregunta d'insígnia: Estrella de Jade

Responeu-la a la fitxa de progrés amb les vostres paraules i aixequeu la mà:

> **"Per què no s'ha de fer `@extend` d'una classe real? Expliqueu-ho amb el que ha passat a la missió 4.1."**

---

## Quadern de bitàcola

Escriviu una línia per a *Per què no estenc classes reals* i *`@extend` i media queries*.

> *"Ara ja sabeu on són les trampes. Estrella de Jade. Teniu les quatre insígnies: en Drake us espera a l'Illa Pummelo. La Lliga Taronja no es guanya amb un sol patró, sinó amb una biblioteca sencera."*

Següent: [05-lliga-taronja.md](05-lliga-taronja.md)
