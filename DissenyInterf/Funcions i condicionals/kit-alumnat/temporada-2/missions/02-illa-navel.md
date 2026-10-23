# Etapa 2. Illa Navel: extend múltiple i estats

**Temps:** 12 minuts. **Fitxer:** `src/scss/arxipelag/_navel.scss`. **Insígnia:** Robí Marí. [Tornar al mapa](../README.md)

Temari: apartats 7.2.2 i 7.2.3.

Només podeu entrar-hi si teniu l'insígnia Ull de Corall.

---

## Història

> *"Sóc en Danny, líder del Gimnàs de l'Illa Navel. Per arribar al cim del far cal passar per enllaços, targetes i botons, i tots s'han de poder fer servir amb el teclat. Alguns elements són enllaços, d'altres són clicables i tots necessiten un anell de focus. Si cada element ha d'heretar de més d'un patró, com ho fareu sense repetir res?"*

---

## Preparació (no dona XP)

1. Creeu `src/scss/arxipelag/_navel.scss`.
2. Afegiu `@use 'arxipelag/navel';` a `arxipelag.scss`.

---

## Concepte: estendre més d'un placeholder i heretar estats

**Una classe pot estendre tants placeholders com calgui.** Cada `@extend` afegeix el selector de la classe a la regla d'aquell placeholder:

```scss
%amb-ombra {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

%cantonades-rodones {
  border-radius: 8px;
}

.finestra {
  @extend %amb-ombra;
  @extend %cantonades-rodones;
  padding: 20px;
}
```

**Els estats també s'hereten.** Si el placeholder té regles imbricades amb `&` (pseudo-classes o pseudo-elements, com al capítol 3), la classe que l'estén les rep totes:

```scss
%ressaltable {
  transition: background 0.3s;

  &:hover {
    background: #fff4e6;
  }
}

.fila-taula {
  @extend %ressaltable;
}
```

CSS que genera:

```css
.fila-taula {
  transition: background 0.3s;
}
.fila-taula:hover {
  background: #fff4e6;
}
```

---

## Missió 2.1. Tres placeholders (100 XP)

A `_navel.scss`, creeu aquests tres placeholders:

| Placeholder | Què ha de contenir |
|---|---|
| `%element-clicable` | `cursor: pointer;` i `user-select: none;` |
| `%focus-visible` | **Només** una regla `&:focus-visible` amb `outline: 3px solid #005fcc;` i `outline-offset: 2px;` |
| `%enllac-base` | `color: #1a5fb4;` i `text-decoration: none;`, una regla `&:hover` amb `text-decoration: underline;` i una regla `&:visited` amb `color: #613583;` |

### Autocomprovació

- [ ] Compila sense errors.
- [ ] El CSS **no ha canviat** (encara ningú no estén aquests placeholders).

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

`%focus-visible` no té cap propietat directa: només té un estat. Com escrivíeu els estats amb el selector pare al capítol 3?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Dins de `%focus-visible` només hi va un bloc `&:focus-visible { ... }`. Dins de `%enllac-base` hi van dues propietats i dos blocs: `&:hover` i `&:visited`.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
%focus-visible {
  ___ {
    outline: 3px solid #005fcc;
    outline-offset: 2px;
  }
}

%enllac-base {
  color: #1a5fb4;
  text-decoration: none;

  &:hover {
    ___
  }

  ___ {
    color: #613583;
  }
}
```
</details>

---

## Missió 2.2. Estendre'n més d'un (100 XP)

Creeu aquestes tres classes:

| Classe | Estén | Propietats pròpies |
|---|---|---|
| `.enllac-ruta` | `%enllac-base` i `%focus-visible` | `font-weight: bold;` |
| `.enllac-mapa` | `%enllac-base` i `%focus-visible` | `font-size: 14px;` |
| `.targeta-illa` | `%element-clicable` i `%focus-visible` | `display: block;`, `padding: 16px;` i `border: 1px solid #ccc;` |

**Abans de desar**, escriviu a la fitxa de progrés:
- a) Quins selectors tindrà la regla de `:focus-visible`?
- b) Quantes regles amb `:hover` hi haurà al CSS?

### Autocomprovació

- [ ] Hi ha **una sola** regla `:focus-visible`, amb tres selectors.
- [ ] Hi ha **una sola** regla `:hover` (per als dos enllaços). `.targeta-illa` no en té.
- [ ] `.targeta-illa` apareix en diverses regles diferents: localitzeu-les totes i digueu de quin placeholder ve cadascuna.
- [ ] A `arxipelag.html`, si premeu la tecla Tab, els dos enllaços i la targeta mostren l'anell blau.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Cada classe necessita **dos** `@extend`. Es poden posar un darrere l'altre?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Primer els dos `@extend`, un per línia, i després les propietats pròpies de la classe.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
.enllac-ruta {
  @extend ___;
  @extend ___;
  font-weight: bold;
}

.targeta-illa {
  @extend ___;
  @extend %focus-visible;
  display: block;
  // ...
}
```
</details>

---

## Missió 2.3. El títol que no arriba (100 XP)

En Danny té fitxes amb un títol. Algú ha pensat que, si el títol és dins del placeholder, totes les fitxes el tindran automàticament. Escriviu aquest codi tal com està:

```scss
%fitxa-base {
  padding: 12px;
  border: 2px solid #f08030;
  border-radius: 8px;

  &__titol {
    font-weight: bold;
  }
}

.fitxa {
  @extend %fitxa-base;
}
```

**Abans de desar**, escriviu a la fitxa de progrés: sortirà alguna regla per a `.fitxa__titol` al CSS?

Deseu i comproveu-ho. Mireu també la fitxa de l'Etapa 2 a `arxipelag.html`.

**Ara arregleu-ho**: feu que `.fitxa__titol` tingui `font-weight: bold;` i `text-transform: uppercase;`, sense treure el `@extend` de `.fitxa`.

### Autocomprovació

- [ ] Heu escrit la predicció abans de desar.
- [ ] Podeu explicar per què, al principi, no sortia `.fitxa__titol`.
- [ ] Ara `.fitxa__titol` surt al CSS amb les dues propietats i, al navegador, el títol es veu en negreta i majúscules.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Recordeu el capítol 3: `&__titol` enganxa `__titol` al selector pare. Si el pare és `%fitxa-base`, quin selector es forma? És el mateix nom que el que esteneu?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Dins del placeholder, `&__titol` crea **un altre placeholder**: `%fitxa-base__titol`. `@extend %fitxa-base` només hereta les regles de `%fitxa-base` i els seus estats (`:hover`, `::after`...), no les d'altres noms. Els elements BEM s'han d'escriure a la classe real.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
.fitxa {
  @extend %fitxa-base;

  ___ {
    font-weight: bold;
    text-transform: uppercase;
  }
}
```

I traieu el bloc `&__titol` del placeholder, perquè no fa res.
</details>

---

## Repte bonus (+50 XP)

Creeu un placeholder `%amb-fletxa` amb una regla `&::after` que posi `content: " >";` i `margin-left: 4px;`. Feu que `.enllac-ruta` també l'estengui. Comproveu al CSS que el pseudo-element s'ha generat amb el selector de la classe i, al navegador, que l'enllaç té la fletxa.

---

## Pregunta d'insígnia: Robí Marí

Responeu-la a la fitxa de progrés amb les vostres paraules i aixequeu la mà:

> **"Si un placeholder té una regla `&:hover`, què reben les classes que l'estenen? I si té una regla `&__titol`? Per què hi ha aquesta diferència?"**

---

## Quadern de bitàcola

Escriviu una línia per a *Extend múltiple* i *Herència d'estats*.

> *"Heu arribat al cim del far. Robí Marí. A l'Illa Trovita, en Rudy us farà una pregunta que tot desenvolupador es fa algun dia: extend o mixin?"*

Següent: [03-illa-trovita.md](03-illa-trovita.md)
