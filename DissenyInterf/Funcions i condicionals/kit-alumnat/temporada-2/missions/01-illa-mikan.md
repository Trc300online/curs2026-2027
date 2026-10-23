# Etapa 1. Illa Mikan: placeholders i `@extend`

**Temps:** 10 minuts. **Fitxer:** `src/scss/arxipelag/_mikan.scss`. **Insígnia:** Ull de Corall. [Tornar al mapa](../README.md)

Temari: apartats 7.1 i 7.2.1.

---

## Història

> *"Sóc la Cissy, líder del Gimnàs de l'Illa Mikan. Els botons dels meus combats d'aigua tenen el mateix farciment, la mateixa vora i el mateix tipus de lletra. L'any passat ho vaig fer amb un mixin i el CSS va quedar ple de blocs repetits. Vull que tots els botons comparteixin la base sense copiar-la ni una sola vegada."*

---

## Preparació (no dona XP)

1. Creeu `src/scss/arxipelag/_mikan.scss`.
2. Afegiu-lo a `src/scss/arxipelag.scss`:
   ```scss
   @use 'arxipelag/mikan';
   ```

---

## Concepte: placeholders i `@extend`

Un **placeholder** és un selector que comença per `%`. S'escriu com una classe, però **no surt mai al CSS per si sol**: només existeix perquè altres selectors l'estenguin.

Amb **`@extend`**, una classe diu "jo també tinc aquests estils". Sass no copia les propietats: **afegeix el selector** de la classe a la regla del placeholder.

Exemple d'una botiga:

```scss
%etiqueta-base {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.etiqueta-nou {
  @extend %etiqueta-base;
  background: #2ecc71;
}

.etiqueta-oferta {
  @extend %etiqueta-base;
  background: #e74c3c;
}
```

CSS que genera:

```css
.etiqueta-oferta, .etiqueta-nou {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
}

.etiqueta-nou {
  background: #2ecc71;
}

.etiqueta-oferta {
  background: #e74c3c;
}
```

Fixeu-vos que les propietats comunes només s'escriuen **una vegada** i que `%etiqueta-base` no apareix enlloc. L'ordre dels selectors agrupats (`.etiqueta-oferta, .etiqueta-nou`) el decideix Sass i no té importància.

Compareu-ho amb un mixin: `@include` **copia** les propietats dins de cada classe; `@extend` **agrupa** els selectors.

---

## Missió 1.1. El placeholder invisible (100 XP)

A `_mikan.scss`, creeu el placeholder `%boto-base` amb aquestes propietats:

```
display: inline-block;
padding: 10px 20px;
border: none;
border-radius: 4px;
font-weight: bold;
cursor: pointer;
```

**Encara no l'estengueu des de cap classe.**

**Abans de desar**, escriviu a la fitxa de progrés:
- a) Què apareixerà a `arxipelag.css`?

Deseu i comproveu-ho.

Ara, a sota, creeu **temporalment** una classe normal `.boto-base` amb les mateixes sis propietats.
- b) Què apareixerà ara al CSS?

Deseu, comproveu-ho i **esborreu la classe `.boto-base`**. Deixeu només el placeholder.

### Autocomprovació

- [ ] Heu escrit les dues prediccions abans de desar.
- [ ] Sabeu explicar per què `%boto-base` no surt i `.boto-base` sí.
- [ ] Al final de la missió, al fitxer només hi queda `%boto-base`.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Un placeholder s'escriu exactament com una classe, però canviant el punt per un altre símbol. Quin?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

`%boto-base { ... }` amb les sis propietats a dins. Si el CSS és buit, la missió va bé: un placeholder que ningú no estén no genera res.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
___boto-base {
  display: inline-block;
  padding: 10px 20px;
  // ... les altres quatre propietats
}
```
</details>

---

## Missió 1.2. Tres botons, una base (100 XP)

Creeu aquestes tres classes. Totes han d'**estendre** `%boto-base` i, a més, tenir els seus colors i el seu estat `:hover` (amb `&:hover`, com al capítol 3):

| Classe | `background` | `color` | `background` a `:hover` |
|---|---|---|---|
| `.boto-aigua` | `#6890f0` | `#fff` | `#4a6fc4` |
| `.boto-gel` | `#98d8d8` | `#1d3b3b` | `#6fbcbc` |
| `.boto-normal` | `#a8a878` | `#fff` | `#8a8a5c` |

### Autocomprovació

- [ ] Al CSS hi ha **una sola regla** amb `padding: 10px 20px`, i té tres selectors.
- [ ] La propietat `font-weight: bold` apareix **una sola vegada** a tot el fitxer (comproveu-ho amb `Ctrl + F`).
- [ ] Cada botó té una regla pròpia amb els seus colors i una altra per al `:hover`.
- [ ] Al CSS no apareix enlloc el text `%boto-base`.
- [ ] A `arxipelag.html`, els tres botons de l'Etapa 1 tenen estil i canvien de color en passar-hi el ratolí.

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Cada classe necessita dues coses: una línia que digui "hereto la base" i les propietats que només són seves. Quina directiva fa la primera?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

A dins de cada classe: `@extend %boto-base;` a la primera línia, després `background` i `color`, i després un bloc `&:hover { ... }`.
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

```scss
.boto-aigua {
  ___ %boto-base;
  background: #6890f0;
  color: #fff;

  ___ {
    background: ___;
  }
}

// ... .boto-gel i .boto-normal igual
```
</details>

---

## Missió 1.3. Un botó més (100 XP)

La Cissy vol un botó molt gran. Abans d'escriure-hi res, mireu el vostre CSS i compteu quantes vegades apareix `cursor: pointer`.

**Predicció:** si afegiu aquesta classe, quantes vegades hi apareixerà després?

```scss
.boto-gegant {
  @extend %boto-base;
  font-size: 24px;
}
```

Afegiu-la, deseu i comproveu-ho.

### Autocomprovació

- [ ] La predicció és a la fitxa de progrés.
- [ ] La regla de la base ara té **quatre** selectors.
- [ ] Podeu explicar què hauria passat amb un mixin: quantes vegades hi hauria `cursor: pointer`?

<details>
<summary>Pista 1 (suau). La missió valdrà 70 XP</summary>

Amb `@extend`, les propietats de la base es copien o s'hi afegeix un selector?
</details>

<details>
<summary>Pista 2 (mitjana). La missió valdrà 50 XP</summary>

Busqueu la regla que comença per `.boto-...` i té `padding: 10px 20px`. Quants selectors té? I quantes propietats `cursor` hi ha a tot el fitxer?
</details>

<details>
<summary>Pista 3 (forta). La missió valdrà 30 XP</summary>

Abans de `.boto-gegant`: `cursor: pointer` apareix ___ vegada. Després: ___ vegada. Amb un mixin a quatre botons hauria aparegut ___ vegades.
</details>

---

## Repte bonus (+50 XP)

Afegiu a `_mikan.scss` un placeholder `%insignia-mikan` (fons `#ff7f7f`, `padding: 4px 10px` i `border-radius: 999px`) que **ningú no estengui**. Compileu i comproveu que no en surt res. Després escriviu a la fitxa de progrés **dos avantatges** de tenir patrons base que no surten al CSS fins que algú els fa servir.

---

## Pregunta d'insígnia: Ull de Corall

Responeu-la a la fitxa de progrés amb les vostres paraules i aixequeu la mà:

> **"Quina diferència hi ha entre escriure la base dels botons com a classe (`.boto-base`) o com a placeholder (`%boto-base`)? Què arriba al navegador en cada cas?"**

---

## Quadern de bitàcola

Escriviu una línia per a *Placeholder*, *`@extend`* i *Agrupació de selectors*.

> *"Els meus botons ja comparteixen la base sense repetir-la. Ull de Corall per a vosaltres. A l'Illa Navel, en Danny us farà pujar al far: allà les coses tenen més d'un estat."*

Següent: [02-illa-navel.md](02-illa-navel.md)
