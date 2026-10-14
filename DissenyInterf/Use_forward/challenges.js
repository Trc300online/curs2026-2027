const challenges = [
  // ============================================
  // REPTES 1-5: INTRODUCIÓ (Fàcil)
  // ============================================

  // REPT 1: Variables + Nesting bàsic
  {
    id: 1,
    title: "🔓 Repte 1: La Targeta Bàsica",
    badge: "Nivell 1 - Inicial",
    description: "Crea una targeta utilitzant variables i nesting. Defineix $color-fons (beige), $color-text (brown), $mida-base (20px) i $radi (10px). La classe .targeta ha de contenir imbricats .targeta__titol (color text, font-size doble de la base) i .targeta__contingut (color text clarificat 20%).",
    hint: "Utilitza .targeta { .targeta__titol { } } per al nesting. Multiplica $mida-base * 2 per al font-size.",
    points: 10,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta"><h2 class="targeta__titol">Títol</h2><p class="targeta__contingut">Contingut de la targeta</p></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-fons\s*:/.test(code), msg: "❌ Has de definir $color-fons", success: "✅ Has definit $color-fons" },
        { test: (code) => /\$color-text\s*:/.test(code), msg: "❌ Has de definir $color-text", success: "✅ Has definit $color-text" },
        { test: (code) => /\$mida-base\s*:/.test(code), msg: "❌ Has de definir $mida-base", success: "✅ Has definit $mida-base" },
        { test: (code) => /\$radi\s*:/.test(code), msg: "❌ Has de definir $radi", success: "✅ Has definit $radi" },
        { test: (code) => /\.targeta\s*\{[\s\S]*\.targeta__titol/.test(code), msg: "❌ .targeta__titol ha d'estar imbricat dins de .targeta", success: "✅ .targeta__titol està imbricat" },
        { test: (code) => /\.targeta\s*\{[\s\S]*\.targeta__contingut/.test(code), msg: "❌ .targeta__contingut ha d'estar imbricat dins de .targeta", success: "✅ .targeta__contingut està imbricat" },
        { test: (code) => /\$mida-base\s*\*\s*2/.test(code), msg: "❌ Has de multiplicar $mida-base * 2", success: "✅ Has multiplicat $mida-base * 2" },
        { test: (code) => /lighten\s*\(\s*\$color-text/.test(code), msg: "❌ Has d'utilitzar lighten($color-text, 20%)", success: "✅ Has utilitzat lighten()" }
      ],
      css: [
        { test: (css) => /\.targeta[\s\S]*background[\s\S]*(beige|wheat|burlywood|tan)/i.test(css), msg: "❌ .targeta ha de tenir fons beige", success: "✅ .targeta té fons beige" },
        { test: (css) => /\.targeta[\s\S]*border-radius\s*:\s*10px/.test(css), msg: "❌ .targeta ha de tenir border-radius: 10px", success: "✅ .targeta té border-radius: 10px" },
        { test: (css) => /\.targeta__titol[\s\S]*font-size\s*:\s*40px/.test(css), msg: "❌ .targeta__titol ha de tenir font-size: 40px", success: "✅ .targeta__titol té font-size: 40px" }
      ]
    },
    starterCode: `// Defineix les variables


// Crea la targeta amb nesting
.targeta {
  
}
`
  },

  // REPT 2: Operacions + Selector &
  {
    id: 2,
    title: "🔓 Repte 2: El Botó Escalable",
    badge: "Nivell 2 - Operacions",
    description: "Crea un sistema de botons amb $mida-base: 16px. .boto ha de tenir font-size: $mida-base, padding: $mida-base * 1.5. Crea &--petit amb font-size: math.div($mida-base, 2) i &--gran amb font-size: $mida-base * 2. Afegeix &:hover amb darken i &:active amb scale(0.95).",
    hint: "Utilitza @use 'sass:math' per a math.div(). El selector &--petit es fa amb &--petit { } dins de .boto.",
    points: 15,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><button class="boto boto--petit">Petit</button><button class="boto">Normal</button><button class="boto boto--gran">Gran</button></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$mida-base\s*:\s*16px/.test(code), msg: "❌ Has de definir $mida-base: 16px", success: "✅ Has definit $mida-base: 16px" },
        { test: (code) => /font-size\s*:\s*\$mida-base/.test(code), msg: "❌ .boto ha de tenir font-size: $mida-base", success: "✅ .boto té font-size: $mida-base" },
        { test: (code) => /padding\s*:\s*\$mida-base\s*\*\s*1\.5/.test(code), msg: "❌ Has de calcular padding: $mida-base * 1.5", success: "✅ Has calculat padding" },
        { test: (code) => /&--petit[\s\S]*math\.div\s*\(\s*\$mida-base\s*,\s*2\s*\)/.test(code), msg: "❌ &--petit ha d'utilitzar math.div($mida-base, 2)", success: "✅ &--petit utilitza math.div()" },
        { test: (code) => /&--gran[\s\S]*\$mida-base\s*\*\s*2/.test(code), msg: "❌ &--gran ha d'utilitzar $mida-base * 2", success: "✅ &--gran utilitza multiplicació" },
        { test: (code) => /&:hover[\s\S]*darken/.test(code), msg: "❌ &:hover ha d'utilitzar darken()", success: "✅ &:hover utilitza darken()" },
        { test: (code) => /&:active[\s\S]*scale\s*\(\s*0\.95\s*\)/.test(code), msg: "❌ &:active ha de tenir transform: scale(0.95)", success: "✅ &:active té scale(0.95)" }
      ],
      css: [
        { test: (css) => /\.boto[\s\S]*font-size\s*:\s*16px/.test(css), msg: "❌ .boto ha de tenir font-size: 16px", success: "✅ .boto té font-size: 16px" },
        { test: (css) => /\.boto--petit[\s\S]*font-size\s*:\s*8px/.test(css), msg: "❌ .boto--petit ha de tenir font-size: 8px", success: "✅ .boto--petit té font-size: 8px" },
        { test: (css) => /\.boto--gran[\s\S]*font-size\s*:\s*32px/.test(css), msg: "❌ .boto--gran ha de tenir font-size: 32px", success: "✅ .boto--gran té font-size: 32px" },
        { test: (css) => /\.boto:hover/.test(css), msg: "❌ El CSS ha de tenir .boto:hover", success: "✅ El CSS té .boto:hover" },
        { test: (css) => /\.boto:active/.test(css), msg: "❌ El CSS ha de tenir .boto:active", success: "✅ El CSS té .boto:active" }
      ]
    },
    starterCode: `@use 'sass:math';

// Defineix la mida base


// Crea el botó amb modificadors i estats
.boto {
  
}
`
  },

  // REPT 3: Funcions de color + BEM
  {
    id: 3,
    title: "🔓 Repte 3: L'Alerta de Colors",
    badge: "Nivell 3 - Colors",
    description: "Crea un sistema d'alertes BEM. Defineix $color-base: green. .alerta ha de tenir &__icona (background: $color-base), &__text (color: darken($color-base, 30%)). Crea modificadors: &--exit amb background: lighten($color-base, 30%), &--perill amb background: darken($color-base, 20%).",
    hint: "BEM: &__ per elements, &-- per modificadors. Utilitza lighten() i darken() amb $color-base.",
    points: 15,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="alerta alerta--exit"><span class="alerta__icona">✓</span><p class="alerta__text">Tot correcte!</p></div><div class="alerta alerta--perill"><span class="alerta__icona">⚠</span><p class="alerta__text">Perill!</p></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(green|lime|forestgreen)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color verd", success: "✅ Has definit $color-base" },
        { test: (code) => /\.alerta\s*\{[\s\S]*&__icona/.test(code), msg: "❌ Has d'utilitzar &__icona dins de .alerta", success: "✅ Has utilitzat &__icona" },
        { test: (code) => /\.alerta\s*\{[\s\S]*&__text/.test(code), msg: "❌ Has d'utilitzar &__text dins de .alerta", success: "✅ Has utilitzat &__text" },
        { test: (code) => /\.alerta\s*\{[\s\S]*&--exit/.test(code), msg: "❌ Has d'utilitzar &--exit dins de .alerta", success: "✅ Has utilitzat &--exit" },
        { test: (code) => /\.alerta\s*\{[\s\S]*&--perill/.test(code), msg: "❌ Has d'utilitzar &--perill dins de .alerta", success: "✅ Has utilitzat &--perill" },
        { test: (code) => /&__icona[\s\S]*background\s*:\s*\$color-base/.test(code), msg: "❌ &__icona ha de tenir background: $color-base", success: "✅ &__icona té background: $color-base" },
        { test: (code) => /&__text[\s\S]*color\s*:\s*darken\s*\(\s*\$color-base\s*,\s*30%\s*\)/.test(code), msg: "❌ &__text ha de tenir color: darken($color-base, 30%)", success: "✅ &__text utilitza darken()" },
        { test: (code) => /&--exit[\s\S]*lighten\s*\(\s*\$color-base\s*,\s*30%\s*\)/.test(code), msg: "❌ &--exit ha d'utilitzar lighten($color-base, 30%)", success: "✅ &--exit utilitza lighten()" },
        { test: (code) => /&--perill[\s\S]*darken\s*\(\s*\$color-base\s*,\s*20%\s*\)/.test(code), msg: "❌ &--perill ha d'utilitzar darken($color-base, 20%)", success: "✅ &--perill utilitza darken()" }
      ],
      css: [
        { test: (css) => /\.alerta__icona/.test(css), msg: "❌ El CSS ha de tenir .alerta__icona", success: "✅ El CSS té .alerta__icona" },
        { test: (css) => /\.alerta__text/.test(css), msg: "❌ El CSS ha de tenir .alerta__text", success: "✅ El CSS té .alerta__text" },
        { test: (css) => /\.alerta--exit/.test(css), msg: "❌ El CSS ha de tenir .alerta--exit", success: "✅ El CSS té .alerta--exit" },
        { test: (css) => /\.alerta--perill/.test(css), msg: "❌ El CSS ha de tenir .alerta--perill", success: "✅ El CSS té .alerta--perill" }
      ]
    },
    starterCode: `// Defineix el color base


// Crea l'alerta amb BEM
.alerta {
  
}
`
  },

  // REPT 4: Mapes amb map-get + Nesting
  {
    id: 4,
    title: "🔓 Repte 4: L'Espaiat Intel·ligent",
    badge: "Nivell 4 - Mapes",
    description: "Crea un mapa $espaiats: ('petit': 8px, 'mitja': 16px, 'gran': 24px). Utilitza map-get($espaiats, 'petit') per definir el padding de .contenidor, map-get($espaiats, 'mitja') per al margin-bottom de .contenidor__titol, i map-get($espaiats, 'gran') per al gap de .contenidor__grid (amb display: flex).",
    hint: "Utilitza map-get($mapa, 'clau') per accedir als valors del mapa. Exemple: map-get($espaiats, 'petit') retorna 8px.",
    points: 20,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="contenidor"><h2 class="contenidor__titol">Títol</h2><div class="contenidor__grid"><div>Item 1</div><div>Item 2</div></div></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$espaiats\s*:\s*\(/.test(code), msg: "❌ Has de definir $espaiats com a mapa", success: "✅ Has definit $espaiats" },
        { test: (code) => /'petit'\s*:\s*8px/.test(code), msg: "❌ El mapa ha de tenir 'petit': 8px", success: "✅ El mapa té 'petit': 8px" },
        { test: (code) => /'mitja'\s*:\s*16px/.test(code), msg: "❌ El mapa ha de tenir 'mitja': 16px", success: "✅ El mapa té 'mitja': 16px" },
        { test: (code) => /'gran'\s*:\s*24px/.test(code), msg: "❌ El mapa ha de tenir 'gran': 24px", success: "✅ El mapa té 'gran': 24px" },
        { test: (code) => /map-get\s*\(\s*\$espaiats\s*,\s*'petit'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get($espaiats, 'petit')", success: "✅ Has utilitzat map-get per 'petit'" },
        { test: (code) => /map-get\s*\(\s*\$espaiats\s*,\s*'mitja'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get($espaiats, 'mitja')", success: "✅ Has utilitzat map-get per 'mitja'" },
        { test: (code) => /map-get\s*\(\s*\$espaiats\s*,\s*'gran'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get($espaiats, 'gran')", success: "✅ Has utilitzat map-get per 'gran'" },
        { test: (code) => /\.contenidor\s*\{[\s\S]*\.contenidor__titol/.test(code), msg: "❌ .contenidor__titol ha d'estar imbricat dins de .contenidor", success: "✅ .contenidor__titol està imbricat" },
        { test: (code) => /\.contenidor\s*\{[\s\S]*\.contenidor__grid/.test(code), msg: "❌ .contenidor__grid ha d'estar imbricat dins de .contenidor", success: "✅ .contenidor__grid està imbricat" }
      ],
      css: [
        { test: (css) => /\.contenidor[\s\S]*padding\s*:\s*8px/.test(css), msg: "❌ .contenidor ha de tenir padding: 8px", success: "✅ .contenidor té padding: 8px" },
        { test: (css) => /\.contenidor__titol[\s\S]*margin-bottom\s*:\s*16px/.test(css), msg: "❌ .contenidor__titol ha de tenir margin-bottom: 16px", success: "✅ .contenidor__titol té margin-bottom: 16px" },
        { test: (css) => /\.contenidor__grid[\s\S]*gap\s*:\s*24px/.test(css), msg: "❌ .contenidor__grid ha de tenir gap: 24px", success: "✅ .contenidor__grid té gap: 24px" },
        { test: (css) => /\.contenidor__grid[\s\S]*display\s*:\s*flex/.test(css), msg: "❌ .contenidor__grid ha de tenir display: flex", success: "✅ .contenidor__grid té display: flex" }
      ]
    },
    starterCode: `// Defineix el mapa d'espaiats


// Utilitza map-get per aplicar els valors
.contenidor {
  
}
`
  },

  // REPT 5: Interpolació + @use bàsic
  {
    id: 5,
    title: "🔓 Repte 5: Les Etiquetes Dinàmiques",
    badge: "Nivell 5 - Interpolació",
    description: "Simula un mòdul _colors.scss amb @use 'colors' que contingui $color-primari: blue, $color-secundari: purple, $color-alerta: red. Utilitza interpolació #{ } per crear classes .tag-primari, .tag-secundari i .tag-alerta amb els colors corresponents del mòdul (colors.$color-primari, etc.).",
    hint: "Escriu les variables del mòdul, després @use 'colors'; i accedeix amb colors.$color-primari. Per a la interpolació guarda el nom en una variable: $nom-primari: primari; i crea la classe amb .tag-#{$nom-primari} { ... }. Com que no pots usar @each, fes les tres classes una a una.",
    points: 20,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><span class="tag tag-primari">Primari</span><span class="tag tag-secundari">Secundari</span><span class="tag tag-alerta">Alerta</span></body></html>`,
    validation: {
      scss: [
        { test: (code) => /@use\s+['"]colors['"]/.test(code), msg: "❌ Has d'utilitzar @use 'colors'", success: "✅ Has utilitzat @use 'colors'" },
        { test: (code) => /colors\.\$color-primari/.test(code), msg: "❌ Has d'accedir a colors.$color-primari", success: "✅ Has accedit a colors.$color-primari" },
        { test: (code) => /colors\.\$color-secundari/.test(code), msg: "❌ Has d'accedir a colors.$color-secundari", success: "✅ Has accedit a colors.$color-secundari" },
        { test: (code) => /colors\.\$color-alerta/.test(code), msg: "❌ Has d'accedir a colors.$color-alerta", success: "✅ Has accedit a colors.$color-alerta" },
        { test: (code) => /\.tag-[\w-]*#\{/.test(code), msg: "❌ Has d'utilitzar interpolació #{ } al nom de la classe (.tag-#{$nom})", success: "✅ Has utilitzat interpolació" },
        { test: (code) => /(blue|dodgerblue|navy)/i.test(code), msg: "❌ $color-primari ha de ser blue", success: "✅ $color-primari és blue" },
        { test: (code) => /(purple|mediumpurple|violet)/i.test(code), msg: "❌ $color-secundari ha de ser purple", success: "✅ $color-secundari és purple" },
        { test: (code) => /(red|crimson|firebrick)/i.test(code), msg: "❌ $color-alerta ha de ser red", success: "✅ $color-alerta és red" }
      ],
      css: [
        { test: (css) => /\.tag-primari\s*\{/.test(css), msg: "❌ El CSS ha de tenir .tag-primari", success: "✅ El CSS té .tag-primari" },
        { test: (css) => /\.tag-secundari\s*\{/.test(css), msg: "❌ El CSS ha de tenir .tag-secundari", success: "✅ El CSS té .tag-secundari" },
        { test: (css) => /\.tag-alerta\s*\{/.test(css), msg: "❌ El CSS ha de tenir .tag-alerta", success: "✅ El CSS té .tag-alerta" },
        { test: (css) => /\.tag-primari[\s\S]*background/.test(css), msg: "❌ .tag-primari ha de tenir background", success: "✅ .tag-primari té background" },
        { test: (css) => /\.tag-secundari[\s\S]*background/.test(css), msg: "❌ .tag-secundari ha de tenir background", success: "✅ .tag-secundari té background" },
        { test: (css) => /\.tag-alerta[\s\S]*background/.test(css), msg: "❌ .tag-alerta ha de tenir background", success: "✅ .tag-alerta té background" }
      ]
    },
    starterCode: `// Simula el mòdul _colors.scss
// @use 'colors';

// Crea les etiquetes amb interpolació


`
  },

  // ============================================
  // REPTES 6-10: INTERMEDI
  // ============================================

  // REPT 6: Llistes amb nth + Selectors & complexos
  {
    id: 6,
    title: "🔓 Repte 6: La Llista Dinàmica",
    badge: "Nivell 6 - Llistes",
    description: "Crea una llista $mides: (50px, 100px, 150px). Utilitza list.nth($mides, 1), list.nth($mides, 2) i list.nth($mides, 3) per definir l'amplada de .item-1, .item-2 i .item-3. Afegeix &:first-child amb border-radius a l'esquerra, &:last-child amb border-radius a la dreta, i &:nth-child(2) amb transform: scale(1.1).",
    hint: "Utilitza @use 'sass:list' per a list.nth(). El selector &:first-child es fa dins del bloc de .item.",
    points: 25,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="item item-1">Item 1</div><div class="item item-2">Item 2</div><div class="item item-3">Item 3</div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$mides\s*:/.test(code), msg: "❌ Has de definir $mides", success: "✅ Has definit $mides" },
        { test: (code) => /list\.nth\s*\(\s*\$mides\s*,\s*1\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth($mides, 1)", success: "✅ Has utilitzat list.nth($mides, 1)" },
        { test: (code) => /list\.nth\s*\(\s*\$mides\s*,\s*2\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth($mides, 2)", success: "✅ Has utilitzat list.nth($mides, 2)" },
        { test: (code) => /list\.nth\s*\(\s*\$mides\s*,\s*3\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth($mides, 3)", success: "✅ Has utilitzat list.nth($mides, 3)" },
        { test: (code) => /\.item[\s\S]*&:first-child/.test(code), msg: "❌ Has d'utilitzar &:first-child dins de .item", success: "✅ Has utilitzat &:first-child" },
        { test: (code) => /\.item[\s\S]*&:last-child/.test(code), msg: "❌ Has d'utilitzar &:last-child dins de .item", success: "✅ Has utilitzat &:last-child" },
        { test: (code) => /\.item[\s\S]*&:nth-child\s*\(\s*2\s*\)/.test(code), msg: "❌ Has d'utilitzar &:nth-child(2) dins de .item", success: "✅ Has utilitzat &:nth-child(2)" },
        { test: (code) => /&:first-child[\s\S]*border-radius/.test(code), msg: "❌ &:first-child ha de tenir border-radius", success: "✅ &:first-child té border-radius" },
        { test: (code) => /&:nth-child\s*\(\s*2\s*\)[\s\S]*transform\s*:\s*scale\s*\(\s*1\.1\s*\)/.test(code), msg: "❌ &:nth-child(2) ha de tenir transform: scale(1.1)", success: "✅ &:nth-child(2) té scale(1.1)" }
      ],
      css: [
        { test: (css) => /\.item-1[\s\S]*width\s*:\s*50px/.test(css), msg: "❌ .item-1 ha de tenir width: 50px", success: "✅ .item-1 té width: 50px" },
        { test: (css) => /\.item-2[\s\S]*width\s*:\s*100px/.test(css), msg: "❌ .item-2 ha de tenir width: 100px", success: "✅ .item-2 té width: 100px" },
        { test: (css) => /\.item-3[\s\S]*width\s*:\s*150px/.test(css), msg: "❌ .item-3 ha de tenir width: 150px", success: "✅ .item-3 té width: 150px" },
        { test: (css) => /\.item:first-child/.test(css), msg: "❌ El CSS ha de tenir .item:first-child", success: "✅ El CSS té .item:first-child" },
        { test: (css) => /\.item:last-child/.test(css), msg: "❌ El CSS ha de tenir .item:last-child", success: "✅ El CSS té .item:last-child" },
        { test: (css) => /\.item:nth-child\(2\)/.test(css), msg: "❌ El CSS ha de tenir .item:nth-child(2)", success: "✅ El CSS té .item:nth-child(2)" }
      ]
    },
    starterCode: `@use 'sass:list';

// Defineix la llista de mides


// Crea els items amb selectors & complexos
.item {
  
}
`
  },

  // REPT 7: @use amb configuració + Operacions
  {
    id: 7,
    title: "🔓 Repte 7: El Mòdul Configurable",
    badge: "Nivell 7 - Configuració",
    description: "Simula un mòdul _config.scss amb $color-base: blue !default i $mida-base: 20px !default. Configura'l amb @use 'config' with ($color-base: purple, $mida-base: 30px). Crea .element amb width: config.$mida-base * 3, height: config.$mida-base * 2, background: config.$color-base i border-radius: math.div(config.$mida-base, 2).",
    hint: "Utilitza !default a les variables del mòdul i @use 'config' with ($color-base: purple) per configurar-lo.",
    points: 25,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="element">Element</div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(blue|dodgerblue|navy)\s*!default/i.test(code), msg: "❌ Has de definir $color-base amb !default", success: "✅ Has definit $color-base amb !default" },
        { test: (code) => /\$mida-base\s*:\s*20px\s*!default/.test(code), msg: "❌ Has de definir $mida-base: 20px !default", success: "✅ Has definit $mida-base amb !default" },
        { test: (code) => /@use\s+['"]config['"]\s+with/.test(code), msg: "❌ Has d'utilitzar @use 'config' with", success: "✅ Has utilitzat @use 'config' with" },
        { test: (code) => /\$color-base\s*:\s*(purple|mediumpurple|violet)/i.test(code), msg: "❌ Has de configurar $color-base amb un color purple", success: "✅ Has configurat $color-base" },
        { test: (code) => /\$mida-base\s*:\s*30px/.test(code), msg: "❌ Has de configurar $mida-base: 30px", success: "✅ Has configurat $mida-base: 30px" },
        { test: (code) => /config\.\$mida-base\s*\*\s*3/.test(code), msg: "❌ Has de calcular config.$mida-base * 3", success: "✅ Has calculat config.$mida-base * 3" },
        { test: (code) => /config\.\$mida-base\s*\*\s*2/.test(code), msg: "❌ Has de calcular config.$mida-base * 2", success: "✅ Has calculat config.$mida-base * 2" },
        { test: (code) => /math\.div\s*\(\s*config\.\$mida-base\s*,\s*2\s*\)/.test(code), msg: "❌ Has de calcular math.div(config.$mida-base, 2)", success: "✅ Has calculat math.div()" },
        { test: (code) => /config\.\$color-base/.test(code), msg: "❌ Has d'utilitzar config.$color-base", success: "✅ Has utilitzat config.$color-base" }
      ],
      css: [
        { test: (css) => /\.element[\s\S]*width\s*:\s*90px/.test(css), msg: "❌ .element ha de tenir width: 90px", success: "✅ .element té width: 90px" },
        { test: (css) => /\.element[\s\S]*height\s*:\s*60px/.test(css), msg: "❌ .element ha de tenir height: 60px", success: "✅ .element té height: 60px" },
        { test: (css) => /\.element[\s\S]*border-radius\s*:\s*15px/.test(css), msg: "❌ .element ha de tenir border-radius: 15px", success: "✅ .element té border-radius: 15px" },
        { test: (css) => /\.element[\s\S]*background[\s\S]*(purple|mediumpurple|violet)/i.test(css), msg: "❌ .element ha de tenir fons purple", success: "✅ .element té fons purple" }
      ]
    },
    starterCode: `@use 'sass:math';

// Simula el mòdul _config.scss amb !default


// Configura el mòdul amb @use 'config' with


// Crea .element utilitzant les variables configurades
.element {
  
}
`
  },

  // REPT 8: Nesting profund + Variables calculades
  {
    id: 8,
    title: "🔓 Repte 8: El Formulari Imbricat",
    badge: "Nivell 8 - Nesting",
    description: "Crea .formulari amb nesting de 3 nivells. Defineix $espaiat-base: 10px, $color-base: teal, $radi: 5px. .formulari__camp ha de tenir padding: $espaiat-base * 2. Dins, .formulari__camp__etiqueta ha de tenir margin-bottom: $espaiat-base. .formulari__camp--error ha de tenir border-color: red i background: lighten($color-base, 40%). .formulari__boto ha de tenir width: $espaiat-base * 20.",
    hint: "Fes nesting de 3 nivells: .formulari { .formulari__camp { .formulari__camp__etiqueta { } } }",
    points: 25,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><form class="formulari"><div class="formulari__camp"><label class="formulari__camp__etiqueta">Nom</label><input type="text" class="formulari__camp__input"></div><div class="formulari__camp formulari__camp--error"><label class="formulari__camp__etiqueta">Email</label><input type="email" class="formulari__camp__input"></div><button class="formulari__boto">Enviar</button></form></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$espaiat-base\s*:\s*10px/.test(code), msg: "❌ Has de definir $espaiat-base: 10px", success: "✅ Has definit $espaiat-base: 10px" },
        { test: (code) => /\$color-base\s*:\s*(teal|darkcyan|cadetblue)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color teal", success: "✅ Has definit $color-base" },
        { test: (code) => /\$radi\s*:\s*5px/.test(code), msg: "❌ Has de definir $radi: 5px", success: "✅ Has definit $radi: 5px" },
        { test: (code) => /\.formulari\s*\{[\s\S]*\.formulari__camp\s*\{[\s\S]*\.formulari__camp__etiqueta/.test(code), msg: "❌ Has de fer nesting de 3 nivells", success: "✅ Has fet nesting de 3 nivells" },
        { test: (code) => /\.formulari__camp[\s\S]*padding\s*:\s*\$espaiat-base\s*\*\s*2/.test(code), msg: "❌ .formulari__camp ha de tenir padding: $espaiat-base * 2", success: "✅ .formulari__camp té padding calculat" },
        { test: (code) => /\.formulari__camp__etiqueta[\s\S]*margin-bottom\s*:\s*\$espaiat-base/.test(code), msg: "❌ .formulari__camp__etiqueta ha de tenir margin-bottom: $espaiat-base", success: "✅ .formulari__camp__etiqueta té margin-bottom" },
        { test: (code) => /(\.formulari__camp--error|&--error)/.test(code), msg: "❌ Has de crear .formulari__camp--error", success: "✅ Has creat .formulari__camp--error" },
        { test: (code) => /(\.formulari__camp--error|&--error)[\s\S]*(red|crimson|firebrick)/i.test(code), msg: "❌ .formulari__camp--error ha de tenir border-color red", success: "✅ .formulari__camp--error té border-color red" },
        { test: (code) => /(\.formulari__camp--error|&--error)[\s\S]*lighten\s*\(\s*\$color-base\s*,\s*40%\s*\)/.test(code), msg: "❌ .formulari__camp--error ha d'utilitzar lighten($color-base, 40%)", success: "✅ .formulari__camp--error utilitza lighten()" },
        { test: (code) => /\.formulari__boto[\s\S]*width\s*:\s*\$espaiat-base\s*\*\s*20/.test(code), msg: "❌ .formulari__boto ha de tenir width: $espaiat-base * 20", success: "✅ .formulari__boto té width calculat" }
      ],
      css: [
        { test: (css) => /\.formulari__camp[\s\S]*padding\s*:\s*20px/.test(css), msg: "❌ .formulari__camp ha de tenir padding: 20px", success: "✅ .formulari__camp té padding: 20px" },
        { test: (css) => /\.formulari__camp__etiqueta[\s\S]*margin-bottom\s*:\s*10px/.test(css), msg: "❌ .formulari__camp__etiqueta ha de tenir margin-bottom: 10px", success: "✅ .formulari__camp__etiqueta té margin-bottom: 10px" },
        { test: (css) => /\.formulari__boto[\s\S]*width\s*:\s*200px/.test(css), msg: "❌ .formulari__boto ha de tenir width: 200px", success: "✅ .formulari__boto té width: 200px" }
      ]
    },
    starterCode: `// Defineix les variables


// Crea el formulari amb nesting profund
.formulari {
  
}
`
  },

  // REPT 9: BEM amb modificadors + Funcions de color
  {
    id: 9,
    title: "🔓 Repte 9: Les Targetes de Preu",
    badge: "Nivell 9 - BEM",
    description: "Crea .targeta-preu amb BEM estricte. Defineix $color-base: orange, $color-text: white, $radi: 8px. &__nom ha de tenir color: $color-text. &__preu ha de tenir font-size: $radi * 5. &__boto ha de tenir background: $color-base i &:hover amb darken($color-base, 15%). Crea modificadors: &--basic amb background: lighten($color-base, 30%), &--pro amb background: $color-base, &--empresari amb background: darken($color-base, 20%).",
    hint: "Utilitza &__ per elements i &-- per modificadors. Cada modificador ha de sobreescriure el background.",
    points: 30,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta-preu targeta-preu--basic"><h3 class="targeta-preu__nom">Basic</h3><p class="targeta-preu__preu">9€</p><button class="targeta-preu__boto">Triar</button></div><div class="targeta-preu targeta-preu--pro"><h3 class="targeta-preu__nom">Pro</h3><p class="targeta-preu__preu">29€</p><button class="targeta-preu__boto">Triar</button></div><div class="targeta-preu targeta-preu--empresari"><h3 class="targeta-preu__nom">Enterprise</h3><p class="targeta-preu__preu">99€</p><button class="targeta-preu__boto">Triar</button></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(orange|darkorange)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color orange", success: "✅ Has definit $color-base" },
        { test: (code) => /\$color-text\s*:\s*(white|snow)/i.test(code), msg: "❌ Has de definir $color-text amb un nom de color white", success: "✅ Has definit $color-text" },
        { test: (code) => /\$radi\s*:\s*8px/.test(code), msg: "❌ Has de definir $radi: 8px", success: "✅ Has definit $radi: 8px" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&__nom/.test(code), msg: "❌ Has d'utilitzar &__nom dins de .targeta-preu", success: "✅ Has utilitzat &__nom" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&__preu/.test(code), msg: "❌ Has d'utilitzar &__preu dins de .targeta-preu", success: "✅ Has utilitzat &__preu" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .targeta-preu", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&--basic/.test(code), msg: "❌ Has d'utilitzar &--basic dins de .targeta-preu", success: "✅ Has utilitzat &--basic" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&--pro/.test(code), msg: "❌ Has d'utilitzar &--pro dins de .targeta-preu", success: "✅ Has utilitzat &--pro" },
        { test: (code) => /\.targeta-preu\s*\{[\s\S]*&--empresari/.test(code), msg: "❌ Has d'utilitzar &--empresari dins de .targeta-preu", success: "✅ Has utilitzat &--empresari" },
        { test: (code) => /&__preu[\s\S]*font-size\s*:\s*\$radi\s*\*\s*5/.test(code), msg: "❌ &__preu ha de tenir font-size: $radi * 5", success: "✅ &__preu té font-size calculat" },
        { test: (code) => /&__boto[\s\S]*&:hover[\s\S]*darken\s*\(\s*\$color-base\s*,\s*15%\s*\)/.test(code), msg: "❌ &__boto &:hover ha d'utilitzar darken($color-base, 15%)", success: "✅ &__boto &:hover utilitza darken()" },
        { test: (code) => /&--basic[\s\S]*lighten\s*\(\s*\$color-base\s*,\s*30%\s*\)/.test(code), msg: "❌ &--basic ha d'utilitzar lighten($color-base, 30%)", success: "✅ &--basic utilitza lighten()" },
        { test: (code) => /&--empresari[\s\S]*darken\s*\(\s*\$color-base\s*,\s*20%\s*\)/.test(code), msg: "❌ &--empresari ha d'utilitzar darken($color-base, 20%)", success: "✅ &--empresari utilitza darken()" }
      ],
      css: [
        { test: (css) => /\.targeta-preu__nom/.test(css), msg: "❌ El CSS ha de tenir .targeta-preu__nom", success: "✅ El CSS té .targeta-preu__nom" },
        { test: (css) => /\.targeta-preu__preu[\s\S]*font-size\s*:\s*40px/.test(css), msg: "❌ .targeta-preu__preu ha de tenir font-size: 40px", success: "✅ .targeta-preu__preu té font-size: 40px" },
        { test: (css) => /\.targeta-preu__boto:hover/.test(css), msg: "❌ El CSS ha de tenir .targeta-preu__boto:hover", success: "✅ El CSS té .targeta-preu__boto:hover" },
        { test: (css) => /\.targeta-preu--basic/.test(css), msg: "❌ El CSS ha de tenir .targeta-preu--basic", success: "✅ El CSS té .targeta-preu--basic" },
        { test: (css) => /\.targeta-preu--pro/.test(css), msg: "❌ El CSS ha de tenir .targeta-preu--pro", success: "✅ El CSS té .targeta-preu--pro" },
        { test: (css) => /\.targeta-preu--empresari/.test(css), msg: "❌ El CSS ha de tenir .targeta-preu--empresari", success: "✅ El CSS té .targeta-preu--empresari" }
      ]
    },
    starterCode: `// Defineix les variables


// Crea la targeta de preu amb BEM
.targeta-preu {
  
}
`
  },

  // REPT 10: @forward + Mapes + Nesting
  {
    id: 10,
    title: "🔓 Repte 10: La Biblioteca Selectiva",
    badge: "Nivell 10 - @forward",
    description: "Simula un mòdul _variables.scss amb $color-primari: blue, $color-secundari: green, $color-intern: red (aquest últim no s'ha d'exposar). Utilitza @forward 'variables' show $color-primari, $color-secundari per exposar només els dos primers. Crea un mapa $mides: ('sm': 100px, 'md': 200px, 'lg': 300px). Dins de .contenidor amb nesting, crea .contenidor__item amb width: map-get($mides, 'md') i background: variables.$color-primari.",
    hint: "Utilitza @forward amb show per exposar només algunes variables. Després @use 'variables' per accedir-hi.",
    points: 30,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="contenidor"><div class="contenidor__item">Item</div></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-primari\s*:\s*(blue|dodgerblue|navy)/i.test(code), msg: "❌ Has de definir $color-primari amb un nom de color blue", success: "✅ Has definit $color-primari" },
        { test: (code) => /\$color-secundari\s*:\s*(green|lime|forestgreen)/i.test(code), msg: "❌ Has de definir $color-secundari amb un nom de color green", success: "✅ Has definit $color-secundari" },
        { test: (code) => /\$color-intern\s*:\s*(red|crimson|firebrick)/i.test(code), msg: "❌ Has de definir $color-intern amb un nom de color red", success: "✅ Has definit $color-intern" },
        { test: (code) => /@forward\s+['"]variables['"]\s+show/.test(code), msg: "❌ Has d'utilitzar @forward 'variables' show", success: "✅ Has utilitzat @forward amb show" },
        { test: (code) => /@forward[\s\S]*\$color-primari[\s\S]*\$color-secundari/.test(code), msg: "❌ @forward ha d'exposar $color-primari i $color-secundari", success: "✅ @forward exposa les variables correctes" },
        { test: (code) => /\$mides\s*:\s*\(/.test(code), msg: "❌ Has de definir $mides com a mapa", success: "✅ Has definit $mides" },
        { test: (code) => /'sm'\s*:\s*100px/.test(code), msg: "❌ El mapa ha de tenir 'sm': 100px", success: "✅ El mapa té 'sm': 100px" },
        { test: (code) => /'md'\s*:\s*200px/.test(code), msg: "❌ El mapa ha de tenir 'md': 200px", success: "✅ El mapa té 'md': 200px" },
        { test: (code) => /'lg'\s*:\s*300px/.test(code), msg: "❌ El mapa ha de tenir 'lg': 300px", success: "✅ El mapa té 'lg': 300px" },
        { test: (code) => /map-get\s*\(\s*\$mides\s*,\s*'md'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get($mides, 'md')", success: "✅ Has utilitzat map-get($mides, 'md')" },
        { test: (code) => /variables\.\$color-primari/.test(code), msg: "❌ Has d'utilitzar variables.$color-primari", success: "✅ Has utilitzat variables.$color-primari" },
        { test: (code) => /\.contenidor\s*\{[\s\S]*\.contenidor__item/.test(code), msg: "❌ .contenidor__item ha d'estar imbricat dins de .contenidor", success: "✅ .contenidor__item està imbricat" }
      ],
      css: [
        { test: (css) => /\.contenidor__item[\s\S]*width\s*:\s*200px/.test(css), msg: "❌ .contenidor__item ha de tenir width: 200px", success: "✅ .contenidor__item té width: 200px" },
        { test: (css) => /\.contenidor__item[\s\S]*background[\s\S]*(blue|dodgerblue|navy)/i.test(css), msg: "❌ .contenidor__item ha de tenir fons blue", success: "✅ .contenidor__item té fons blue" }
      ]
    },
    starterCode: `// Simula el mòdul _variables.scss


// Utilitza @forward per exposar selectivament


// Defineix el mapa $mides


// Crea .contenidor amb nesting
.contenidor {
  
}
`
  },

  // ============================================
  // REPTES 11-15: AVANÇAT
  // ============================================

  // REPT 11: Selectors & avançats + Variables
  {
    id: 11,
    title: "🔓 Repte 11: El Menú Complex",
    badge: "Nivell 11 - Selectors &",
    description: "Crea .menu amb nesting i selectors & avançats. Defineix $color-base: navy, $color-hover: skyblue, $color-focus: gold, $color-actiu: coral. &__item ha de tenir &:hover amb background: $color-hover, &:focus-within amb outline: 2px solid $color-focus, &.actiu amb background: $color-actiu, & + & amb margin-left: 10px, &:first-child amb border-radius a l'esquerra, &:last-child amb border-radius a la dreta, &:not(.desactivat) amb cursor: pointer, &::after amb content: '>' i color: $color-base.",
    hint: "Utilitza tots els selectors &:hover, &:focus-within, &.actiu, & + &, &:first-child, &:last-child, &:not(), &::after dins de &__item.",
    points: 35,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><nav class="menu"><a class="menu__item" href="#">Inici</a><a class="menu__item actiu" href="#">Productes</a><a class="menu__item" href="#">Contacte</a><a class="menu__item desactivat" href="#">Admin</a></nav></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(navy|darkblue|midnightblue)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color navy", success: "✅ Has definit $color-base" },
        { test: (code) => /\$color-hover\s*:\s*(skyblue|lightblue|paleturquoise)/i.test(code), msg: "❌ Has de definir $color-hover amb un nom de color skyblue", success: "✅ Has definit $color-hover" },
        { test: (code) => /\$color-focus\s*:\s*(gold|goldenrod|yellow)/i.test(code), msg: "❌ Has de definir $color-focus amb un nom de color gold", success: "✅ Has definit $color-focus" },
        { test: (code) => /\$color-actiu\s*:\s*(coral|tomato|salmon)/i.test(code), msg: "❌ Has de definir $color-actiu amb un nom de color coral", success: "✅ Has definit $color-actiu" },
        { test: (code) => /\.menu\s*\{[\s\S]*&__item/.test(code), msg: "❌ Has d'utilitzar &__item dins de .menu", success: "✅ Has utilitzat &__item" },
        { test: (code) => /&__item[\s\S]*&:hover[\s\S]*\$color-hover/.test(code), msg: "❌ &__item &:hover ha d'utilitzar $color-hover", success: "✅ &__item &:hover utilitza $color-hover" },
        { test: (code) => /&__item[\s\S]*&:focus-within[\s\S]*\$color-focus/.test(code), msg: "❌ &__item &:focus-within ha d'utilitzar $color-focus", success: "✅ &__item &:focus-within utilitza $color-focus" },
        { test: (code) => /&__item[\s\S]*&\.actiu[\s\S]*\$color-actiu/.test(code), msg: "❌ &__item &.actiu ha d'utilitzar $color-actiu", success: "✅ &__item &.actiu utilitza $color-actiu" },
        { test: (code) => /&__item[\s\S]*&\s*\+\s*&/.test(code), msg: "❌ &__item ha de tenir & + &", success: "✅ &__item té & + &" },
        { test: (code) => /&__item[\s\S]*&:first-child/.test(code), msg: "❌ &__item ha de tenir &:first-child", success: "✅ &__item té &:first-child" },
        { test: (code) => /&__item[\s\S]*&:last-child/.test(code), msg: "❌ &__item ha de tenir &:last-child", success: "✅ &__item té &:last-child" },
        { test: (code) => /&__item[\s\S]*&:not\s*\(\s*\.desactivat\s*\)/.test(code), msg: "❌ &__item ha de tenir &:not(.desactivat)", success: "✅ &__item té &:not(.desactivat)" },
        { test: (code) => /&__item[\s\S]*&::after[\s\S]*content\s*:/.test(code), msg: "❌ &__item ha de tenir &::after amb content", success: "✅ &__item té &::after amb content" }
      ],
      css: [
        { test: (css) => /\.menu__item:hover/.test(css), msg: "❌ El CSS ha de tenir .menu__item:hover", success: "✅ El CSS té .menu__item:hover" },
        { test: (css) => /\.menu__item:focus-within/.test(css), msg: "❌ El CSS ha de tenir .menu__item:focus-within", success: "✅ El CSS té .menu__item:focus-within" },
        { test: (css) => /\.menu__item\.actiu/.test(css), msg: "❌ El CSS ha de tenir .menu__item.actiu", success: "✅ El CSS té .menu__item.actiu" },
        { test: (css) => /\.menu__item\s*\+\s*\.menu__item/.test(css), msg: "❌ El CSS ha de tenir .menu__item + .menu__item", success: "✅ El CSS té .menu__item + .menu__item" },
        { test: (css) => /\.menu__item:first-child/.test(css), msg: "❌ El CSS ha de tenir .menu__item:first-child", success: "✅ El CSS té .menu__item:first-child" },
        { test: (css) => /\.menu__item:last-child/.test(css), msg: "❌ El CSS ha de tenir .menu__item:last-child", success: "✅ El CSS té .menu__item:last-child" },
        { test: (css) => /\.menu__item:not\(\.desactivat\)/.test(css), msg: "❌ El CSS ha de tenir .menu__item:not(.desactivat)", success: "✅ El CSS té .menu__item:not(.desactivat)" },
        { test: (css) => /\.menu__item::after/.test(css), msg: "❌ El CSS ha de tenir .menu__item::after", success: "✅ El CSS té .menu__item::after" }
      ]
    },
    starterCode: `// Defineix les variables de color


// Crea el menú amb selectors & avançats
.menu {
  
}
`
  },

  // REPT 12: @use múltiple + Operacions + BEM
  {
    id: 12,
    title: "🔓 Repte 12: El Component Híbrid",
    badge: "Nivell 12 - @use múltiple",
    description: "Simula dos mòduls: _colors.scss amb $color-primari: purple i $color-secundari: teal, i _mides.scss amb $mida-petita: 10px i $mida-gran: 30px. Utilitza @use 'colors' as c i @use 'mides' as m. Crea .targeta amb BEM: &__titol amb font-size: m.$mida-gran * 2 i color: c.$color-primari, &__contingut amb padding: m.$mida-petita * 3 i color: c.$color-secundari, &__boto amb width: m.$mida-gran * 5 i background: c.$color-primari.",
    hint: "Utilitza @use 'colors' as c i @use 'mides' as m per crear àlies curts. Després accedeix amb c.$color-primari i m.$mida-gran.",
    points: 35,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta"><h2 class="targeta__titol">Títol</h2><p class="targeta__contingut">Contingut</p><button class="targeta__boto">Acció</button></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-primari\s*:\s*(purple|mediumpurple|violet)/i.test(code), msg: "❌ Has de definir $color-primari amb un nom de color purple", success: "✅ Has definit $color-primari" },
        { test: (code) => /\$color-secundari\s*:\s*(teal|darkcyan|cadetblue)/i.test(code), msg: "❌ Has de definir $color-secundari amb un nom de color teal", success: "✅ Has definit $color-secundari" },
        { test: (code) => /\$mida-petita\s*:\s*10px/.test(code), msg: "❌ Has de definir $mida-petita: 10px", success: "✅ Has definit $mida-petita: 10px" },
        { test: (code) => /\$mida-gran\s*:\s*30px/.test(code), msg: "❌ Has de definir $mida-gran: 30px", success: "✅ Has definit $mida-gran: 30px" },
        { test: (code) => /@use\s+['"]colors['"]\s+as\s+c/.test(code), msg: "❌ Has d'utilitzar @use 'colors' as c", success: "✅ Has utilitzat @use 'colors' as c" },
        { test: (code) => /@use\s+['"]mides['"]\s+as\s+m/.test(code), msg: "❌ Has d'utilitzar @use 'mides' as m", success: "✅ Has utilitzat @use 'mides' as m" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&__titol/.test(code), msg: "❌ Has d'utilitzar &__titol dins de .targeta", success: "✅ Has utilitzat &__titol" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&__contingut/.test(code), msg: "❌ Has d'utilitzar &__contingut dins de .targeta", success: "✅ Has utilitzat &__contingut" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .targeta", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /&__titol[\s\S]*font-size\s*:\s*m\.\$mida-gran\s*\*\s*2/.test(code), msg: "❌ &__titol ha de tenir font-size: m.$mida-gran * 2", success: "✅ &__titol té font-size calculat" },
        { test: (code) => /&__titol[\s\S]*color\s*:\s*c\.\$color-primari/.test(code), msg: "❌ &__titol ha de tenir color: c.$color-primari", success: "✅ &__titol té color: c.$color-primari" },
        { test: (code) => /&__contingut[\s\S]*padding\s*:\s*m\.\$mida-petita\s*\*\s*3/.test(code), msg: "❌ &__contingut ha de tenir padding: m.$mida-petita * 3", success: "✅ &__contingut té padding calculat" },
        { test: (code) => /&__contingut[\s\S]*color\s*:\s*c\.\$color-secundari/.test(code), msg: "❌ &__contingut ha de tenir color: c.$color-secundari", success: "✅ &__contingut té color: c.$color-secundari" },
        { test: (code) => /&__boto[\s\S]*width\s*:\s*m\.\$mida-gran\s*\*\s*5/.test(code), msg: "❌ &__boto ha de tenir width: m.$mida-gran * 5", success: "✅ &__boto té width calculat" },
        { test: (code) => /&__boto[\s\S]*background\s*:\s*c\.\$color-primari/.test(code), msg: "❌ &__boto ha de tenir background: c.$color-primari", success: "✅ &__boto té background: c.$color-primari" }
      ],
      css: [
        { test: (css) => /\.targeta__titol[\s\S]*font-size\s*:\s*60px/.test(css), msg: "❌ .targeta__titol ha de tenir font-size: 60px", success: "✅ .targeta__titol té font-size: 60px" },
        { test: (css) => /\.targeta__titol[\s\S]*color[\s\S]*(purple|mediumpurple|violet)/i.test(css), msg: "❌ .targeta__titol ha de tenir color purple", success: "✅ .targeta__titol té color purple" },
        { test: (css) => /\.targeta__contingut[\s\S]*padding\s*:\s*30px/.test(css), msg: "❌ .targeta__contingut ha de tenir padding: 30px", success: "✅ .targeta__contingut té padding: 30px" },
        { test: (css) => /\.targeta__contingut[\s\S]*color[\s\S]*(teal|darkcyan|cadetblue)/i.test(css), msg: "❌ .targeta__contingut ha de tenir color teal", success: "✅ .targeta__contingut té color teal" },
        { test: (css) => /\.targeta__boto[\s\S]*width\s*:\s*150px/.test(css), msg: "❌ .targeta__boto ha de tenir width: 150px", success: "✅ .targeta__boto té width: 150px" }
      ]
    },
    starterCode: `// Simula els mòduls _colors.scss i _mides.scss


// Importa'ls amb àlies


// Crea .targeta amb BEM
.targeta {
  
}
`
  },

  // REPT 13: Mapes niats + map-get + Nesting
  {
    id: 13,
    title: "🔓 Repte 13: El Sistema de Temes",
    badge: "Nivell 13 - Mapes niats",
    description: "Crea un mapa niuat $temes: ('clar': ('fons': white, 'text': black), 'fosc': ('fons': navy, 'text': white)). Utilitza map-get(map-get($temes, 'clar'), 'fons') per accedir als valors. Crea .targeta amb nesting: &--clar amb background: map-get(map-get($temes, 'clar'), 'fons') i color: map-get(map-get($temes, 'clar'), 'text'), &--fosc amb background: map-get(map-get($temes, 'fosc'), 'fons') i color: map-get(map-get($temes, 'fosc'), 'text').",
    hint: "Per accedir a un mapa niuat, utilitza map-get(map-get($temes, 'clar'), 'fons'). El primer map-get retorna el sub-mapa, el segon retorna el valor.",
    points: 40,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta targeta--clar">Tema Clar</div><div class="targeta targeta--fosc">Tema Fosc</div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$temes\s*:\s*\(/.test(code), msg: "❌ Has de definir $temes com a mapa", success: "✅ Has definit $temes" },
        { test: (code) => /'clar'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'clar' com a sub-mapa", success: "✅ El mapa té 'clar'" },
        { test: (code) => /'fosc'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'fosc' com a sub-mapa", success: "✅ El mapa té 'fosc'" },
        { test: (code) => /'fons'\s*:\s*(white|snow)/i.test(code), msg: "❌ 'clar' ha de tenir 'fons': white", success: "✅ 'clar' té 'fons': white" },
        { test: (code) => /'text'\s*:\s*(black|navy)/i.test(code), msg: "❌ Has de definir 'text' amb black o navy", success: "✅ Has definit 'text'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$temes\s*,\s*'clar'\s*\)\s*,\s*'fons'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get(map-get($temes, 'clar'), 'fons')", success: "✅ Has utilitzat map-get niuat per 'clar' 'fons'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$temes\s*,\s*'clar'\s*\)\s*,\s*'text'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get(map-get($temes, 'clar'), 'text')", success: "✅ Has utilitzat map-get niuat per 'clar' 'text'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$temes\s*,\s*'fosc'\s*\)\s*,\s*'fons'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get(map-get($temes, 'fosc'), 'fons')", success: "✅ Has utilitzat map-get niuat per 'fosc' 'fons'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$temes\s*,\s*'fosc'\s*\)\s*,\s*'text'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get(map-get($temes, 'fosc'), 'text')", success: "✅ Has utilitzat map-get niuat per 'fosc' 'text'" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&--clar/.test(code), msg: "❌ Has d'utilitzar &--clar dins de .targeta", success: "✅ Has utilitzat &--clar" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&--fosc/.test(code), msg: "❌ Has d'utilitzar &--fosc dins de .targeta", success: "✅ Has utilitzat &--fosc" }
      ],
      css: [
        { test: (css) => /\.targeta--clar[\s\S]*background[\s\S]*(white|snow)/i.test(css), msg: "❌ .targeta--clar ha de tenir fons white", success: "✅ .targeta--clar té fons white" },
        { test: (css) => /\.targeta--clar[\s\S]*color[\s\S]*(black|navy)/i.test(css), msg: "❌ .targeta--clar ha de tenir text black o navy", success: "✅ .targeta--clar té text correcte" },
        { test: (css) => /\.targeta--fosc[\s\S]*background[\s\S]*(navy|darkblue|midnightblue)/i.test(css), msg: "❌ .targeta--fosc ha de tenir fons navy", success: "✅ .targeta--fosc té fons navy" },
        { test: (css) => /\.targeta--fosc[\s\S]*color[\s\S]*(white|snow)/i.test(css), msg: "❌ .targeta--fosc ha de tenir text white", success: "✅ .targeta--fosc té text white" }
      ]
    },
    starterCode: `// Defineix el mapa niuat $temes


// Crea .targeta amb modificadors que utilitzin map-get niuat
.targeta {
  
}
`
  },

  // REPT 14: Llistes multidimensionals + nth + Selectors &
  {
    id: 14,
    title: "🔓 Repte 14: La Configuració Complexa",
    badge: "Nivell 14 - Llistes multidimensionals",
    description: "Crea una llista multidimensional $config: ((100px, red), (200px, green), (300px, blue)). Utilitza list.nth(list.nth($config, 1), 1) per accedir al primer valor de la primera sub-llista. Crea .item-1 amb width: list.nth(list.nth($config, 1), 1) i background: list.nth(list.nth($config, 1), 2), .item-2 amb width: list.nth(list.nth($config, 2), 1) i background: list.nth(list.nth($config, 2), 2), .item-3 amb width: list.nth(list.nth($config, 3), 1) i background: list.nth(list.nth($config, 3), 2). Afegeix &:hover amb transform: scale(1.05) i &:active amb opacity: 0.8.",
    hint: "Per accedir a una llista multidimensional, utilitza list.nth(list.nth($config, 1), 1). El primer list.nth retorna la sub-llista, el segon retorna el valor.",
    points: 40,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="item item-1">Item 1</div><div class="item item-2">Item 2</div><div class="item item-3">Item 3</div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$config\s*:/.test(code), msg: "❌ Has de definir $config", success: "✅ Has definit $config" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*1\s*\)\s*,\s*1\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 1), 1)", success: "✅ Has utilitzat list.nth niuat per (1, 1)" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*1\s*\)\s*,\s*2\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 1), 2)", success: "✅ Has utilitzat list.nth niuat per (1, 2)" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*2\s*\)\s*,\s*1\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 2), 1)", success: "✅ Has utilitzat list.nth niuat per (2, 1)" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*2\s*\)\s*,\s*2\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 2), 2)", success: "✅ Has utilitzat list.nth niuat per (2, 2)" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*3\s*\)\s*,\s*1\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 3), 1)", success: "✅ Has utilitzat list.nth niuat per (3, 1)" },
        { test: (code) => /list\.nth\s*\(\s*list\.nth\s*\(\s*\$config\s*,\s*3\s*\)\s*,\s*2\s*\)/.test(code), msg: "❌ Has d'utilitzar list.nth(list.nth($config, 3), 2)", success: "✅ Has utilitzat list.nth niuat per (3, 2)" },
        { test: (code) => /\.item[\s\S]*&:hover[\s\S]*transform\s*:\s*scale\s*\(\s*1\.05\s*\)/.test(code), msg: "❌ .item &:hover ha de tenir transform: scale(1.05)", success: "✅ .item &:hover té scale(1.05)" },
        { test: (code) => /\.item[\s\S]*&:active[\s\S]*opacity\s*:\s*0\.8/.test(code), msg: "❌ .item &:active ha de tenir opacity: 0.8", success: "✅ .item &:active té opacity: 0.8" },
        { test: (code) => /(red|crimson|firebrick)/i.test(code), msg: "❌ La primera sub-llista ha de tenir red", success: "✅ La primera sub-llista té red" },
        { test: (code) => /(green|lime|forestgreen)/i.test(code), msg: "❌ La segona sub-llista ha de tenir green", success: "✅ La segona sub-llista té green" },
        { test: (code) => /(blue|dodgerblue|navy)/i.test(code), msg: "❌ La tercera sub-llista ha de tenir blue", success: "✅ La tercera sub-llista té blue" }
      ],
      css: [
        { test: (css) => /\.item-1[\s\S]*width\s*:\s*100px/.test(css), msg: "❌ .item-1 ha de tenir width: 100px", success: "✅ .item-1 té width: 100px" },
        { test: (css) => /\.item-1[\s\S]*background[\s\S]*(red|crimson|firebrick)/i.test(css), msg: "❌ .item-1 ha de tenir fons red", success: "✅ .item-1 té fons red" },
        { test: (css) => /\.item-2[\s\S]*width\s*:\s*200px/.test(css), msg: "❌ .item-2 ha de tenir width: 200px", success: "✅ .item-2 té width: 200px" },
        { test: (css) => /\.item-2[\s\S]*background[\s\S]*(green|lime|forestgreen)/i.test(css), msg: "❌ .item-2 ha de tenir fons green", success: "✅ .item-2 té fons green" },
        { test: (css) => /\.item-3[\s\S]*width\s*:\s*300px/.test(css), msg: "❌ .item-3 ha de tenir width: 300px", success: "✅ .item-3 té width: 300px" },
        { test: (css) => /\.item-3[\s\S]*background[\s\S]*(blue|dodgerblue|navy)/i.test(css), msg: "❌ .item-3 ha de tenir fons blue", success: "✅ .item-3 té fons blue" },
        { test: (css) => /\.item:hover/.test(css), msg: "❌ El CSS ha de tenir .item:hover", success: "✅ El CSS té .item:hover" },
        { test: (css) => /\.item:active/.test(css), msg: "❌ El CSS ha de tenir .item:active", success: "✅ El CSS té .item:active" }
      ]
    },
    starterCode: `@use 'sass:list';

// Defineix la llista multidimensional $config


// Crea els items utilitzant list.nth niuat
.item {
  
}
`
  },

  // REPT 15: @forward amb show/hide + Configuració + BEM
  {
    id: 15,
    title: "🔓 Repte 15: L'API Selectiva",
    badge: "Nivell 15 - @forward avançat",
    description: "Simula un mòdul _config.scss amb $color-primari: blue !default, $color-secundari: green, $color-intern: red, $mida-base: 20px !default, $mida-interna: 5px (només es poden configurar amb with les variables que tenen !default). Utilitza @forward 'config' show $color-primari, $color-secundari, $mida-base per exposar només aquestes tres. Configura el mòdul amb @use 'config' with ($color-primari: purple, $mida-base: 30px). Crea .targeta amb BEM: &__titol amb font-size: config.$mida-base * 2 i color: config.$color-primari, &__contingut amb padding: config.$mida-base i color: config.$color-secundari, &--destacat amb background: config.$color-primari.",
    hint: "Posa !default a les variables que vols configurar. Utilitza @forward 'config' show ... per exposar només algunes variables i després @use 'config' with ($color-primari: purple, $mida-base: 30px); per configurar-lo.",
    points: 45,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta"><h2 class="targeta__titol">Títol</h2><p class="targeta__contingut">Contingut</p></div><div class="targeta targeta--destacat"><h2 class="targeta__titol">Destacat</h2><p class="targeta__contingut">Contingut destacat</p></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-primari\s*:/.test(code), msg: "❌ Has de definir $color-primari", success: "✅ Has definit $color-primari" },
        { test: (code) => /\$color-secundari\s*:/.test(code), msg: "❌ Has de definir $color-secundari", success: "✅ Has definit $color-secundari" },
        { test: (code) => /\$color-intern\s*:/.test(code), msg: "❌ Has de definir $color-intern", success: "✅ Has definit $color-intern" },
        { test: (code) => /\$mida-base\s*:/.test(code), msg: "❌ Has de definir $mida-base", success: "✅ Has definit $mida-base" },
        { test: (code) => /\$mida-interna\s*:/.test(code), msg: "❌ Has de definir $mida-interna", success: "✅ Has definit $mida-interna" },
        { test: (code) => /@forward\s+['"]config['"]\s+show/.test(code), msg: "❌ Has d'utilitzar @forward 'config' show", success: "✅ Has utilitzat @forward amb show" },
        { test: (code) => /@forward[\s\S]*\$color-primari[\s\S]*\$color-secundari[\s\S]*\$mida-base/.test(code), msg: "❌ @forward ha d'exposar $color-primari, $color-secundari i $mida-base", success: "✅ @forward exposa les variables correctes" },
        { test: (code) => /@use\s+['"]config['"]\s+with/.test(code), msg: "❌ Has d'utilitzar @use 'config' with", success: "✅ Has utilitzat @use 'config' with" },
        { test: (code) => /\$color-primari\s*:\s*(purple|mediumpurple|violet)/i.test(code), msg: "❌ Has de configurar $color-primari amb purple", success: "✅ Has configurat $color-primari amb purple" },
        { test: (code) => /\$mida-base\s*:\s*30px/.test(code), msg: "❌ Has de configurar $mida-base: 30px", success: "✅ Has configurat $mida-base: 30px" },
        { test: (code) => /config\.\$mida-base\s*\*\s*2/.test(code), msg: "❌ Has de calcular config.$mida-base * 2", success: "✅ Has calculat config.$mida-base * 2" },
        { test: (code) => /config\.\$color-primari/.test(code), msg: "❌ Has d'utilitzar config.$color-primari", success: "✅ Has utilitzat config.$color-primari" },
        { test: (code) => /config\.\$color-secundari/.test(code), msg: "❌ Has d'utilitzar config.$color-secundari", success: "✅ Has utilitzat config.$color-secundari" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&__titol/.test(code), msg: "❌ Has d'utilitzar &__titol dins de .targeta", success: "✅ Has utilitzat &__titol" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&__contingut/.test(code), msg: "❌ Has d'utilitzar &__contingut dins de .targeta", success: "✅ Has utilitzat &__contingut" },
        { test: (code) => /\.targeta\s*\{[\s\S]*&--destacat/.test(code), msg: "❌ Has d'utilitzar &--destacat dins de .targeta", success: "✅ Has utilitzat &--destacat" }
      ],
      css: [
        { test: (css) => /\.targeta__titol[\s\S]*font-size\s*:\s*60px/.test(css), msg: "❌ .targeta__titol ha de tenir font-size: 60px", success: "✅ .targeta__titol té font-size: 60px" },
        { test: (css) => /\.targeta__titol[\s\S]*color[\s\S]*(purple|mediumpurple|violet)/i.test(css), msg: "❌ .targeta__titol ha de tenir color purple", success: "✅ .targeta__titol té color purple" },
        { test: (css) => /\.targeta__contingut[\s\S]*padding\s*:\s*30px/.test(css), msg: "❌ .targeta__contingut ha de tenir padding: 30px", success: "✅ .targeta__contingut té padding: 30px" },
        { test: (css) => /\.targeta__contingut[\s\S]*color[\s\S]*(green|lime|forestgreen)/i.test(css), msg: "❌ .targeta__contingut ha de tenir color green", success: "✅ .targeta__contingut té color green" },
        { test: (css) => /\.targeta--destacat[\s\S]*background[\s\S]*(purple|mediumpurple|violet)/i.test(css), msg: "❌ .targeta--destacat ha de tenir fons purple", success: "✅ .targeta--destacat té fons purple" }
      ]
    },
    starterCode: `// Simula el mòdul _config.scss


// Utilitza @forward per exposar selectivament


// Configura el mòdul amb @use 'config' with


// Crea .targeta amb BEM
.targeta {
  
}
`
  },

  // ============================================
  // REPTES 16-19: DIFÍCIL
  // ============================================

  // REPT 16 (DIFÍCIL): Sistema de notificacions
  {
    id: 16,
    title: "⚠️ Repte 16: El Sistema de Notificacions",
    badge: "Nivell 16 - DIFÍCIL",
    description: "Crea un mapa niuat $tipus: ('info': ('color': blue, 'icona': 'ℹ'), 'exit': ('color': green, 'icona': '✓'), 'alerta': ('color': orange, 'icona': '⚠'), 'perill': ('color': red, 'icona': '⛔')). Utilitza map-get niuat per accedir als valors. Crea .notificacio amb nesting: &__icona amb &::before { content: map-get(map-get($tipus, 'info'), 'icona') }, &__text amb color heretat. Crea modificadors: &--info amb background: lighten(map-get(map-get($tipus, 'info'), 'color'), 40%) i border-left: 4px solid map-get(map-get($tipus, 'info'), 'color'), &--exit amb els seus valors, &--alerta amb els seus valors, &--perill amb els seus valors. Afegeix &__boto amb &:hover { opacity: 0.8 }.",
    hint: "Utilitza map-get(map-get($tipus, 'info'), 'color') per accedir al color de cada tipus. Fes servir lighten() per al background i el color directe per al border-left.",
    points: 50,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="notificacio notificacio--info"><span class="notificacio__icona"></span><p class="notificacio__text">Informació</p><button class="notificacio__boto">Tancar</button></div><div class="notificacio notificacio--exit"><span class="notificacio__icona"></span><p class="notificacio__text">Èxit</p><button class="notificacio__boto">Tancar</button></div><div class="notificacio notificacio--alerta"><span class="notificacio__icona"></span><p class="notificacio__text">Alerta</p><button class="notificacio__boto">Tancar</button></div><div class="notificacio notificacio--perill"><span class="notificacio__icona"></span><p class="notificacio__text">Perill</p><button class="notificacio__boto">Tancar</button></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$tipus\s*:\s*\(/.test(code), msg: "❌ Has de definir $tipus com a mapa", success: "✅ Has definit $tipus" },
        { test: (code) => /'info'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'info' com a sub-mapa", success: "✅ El mapa té 'info'" },
        { test: (code) => /'exit'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'exit' com a sub-mapa", success: "✅ El mapa té 'exit'" },
        { test: (code) => /'alerta'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'alerta' com a sub-mapa", success: "✅ El mapa té 'alerta'" },
        { test: (code) => /'perill'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'perill' com a sub-mapa", success: "✅ El mapa té 'perill'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$tipus\s*,\s*'info'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'info' 'color'", success: "✅ Has utilitzat map-get niuat per 'info'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$tipus\s*,\s*'exit'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'exit' 'color'", success: "✅ Has utilitzat map-get niuat per 'exit'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$tipus\s*,\s*'alerta'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'alerta' 'color'", success: "✅ Has utilitzat map-get niuat per 'alerta'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$tipus\s*,\s*'perill'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'perill' 'color'", success: "✅ Has utilitzat map-get niuat per 'perill'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$tipus\s*,\s*'info'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'info' 'icona'", success: "✅ Has utilitzat map-get niuat per 'icona'" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&__icona/.test(code), msg: "❌ Has d'utilitzar &__icona dins de .notificacio", success: "✅ Has utilitzat &__icona" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&__text/.test(code), msg: "❌ Has d'utilitzar &__text dins de .notificacio", success: "✅ Has utilitzat &__text" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .notificacio", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&--info/.test(code), msg: "❌ Has d'utilitzar &--info dins de .notificacio", success: "✅ Has utilitzat &--info" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&--exit/.test(code), msg: "❌ Has d'utilitzar &--exit dins de .notificacio", success: "✅ Has utilitzat &--exit" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&--alerta/.test(code), msg: "❌ Has d'utilitzar &--alerta dins de .notificacio", success: "✅ Has utilitzat &--alerta" },
        { test: (code) => /\.notificacio\s*\{[\s\S]*&--perill/.test(code), msg: "❌ Has d'utilitzar &--perill dins de .notificacio", success: "✅ Has utilitzat &--perill" },
        { test: (code) => /&__icona[\s\S]*&::before[\s\S]*content/.test(code), msg: "❌ &__icona ha de tenir &::before amb content", success: "✅ &__icona té &::before amb content" },
        { test: (code) => /&__boto[\s\S]*&:hover[\s\S]*opacity\s*:\s*0\.8/.test(code), msg: "❌ &__boto &:hover ha de tenir opacity: 0.8", success: "✅ &__boto &:hover té opacity: 0.8" },
        { test: (code) => /&--info[\s\S]*lighten/.test(code), msg: "❌ &--info ha d'utilitzar lighten()", success: "✅ &--info utilitza lighten()" },
        { test: (code) => /&--info[\s\S]*border-left\s*:\s*4px\s+solid/.test(code), msg: "❌ &--info ha de tenir border-left: 4px solid", success: "✅ &--info té border-left: 4px solid" }
      ],
      css: [
        { test: (css) => /\.notificacio__icona::before/.test(css), msg: "❌ El CSS ha de tenir .notificacio__icona::before", success: "✅ El CSS té .notificacio__icona::before" },
        { test: (css) => /\.notificacio--info[\s\S]*border-left/.test(css), msg: "❌ .notificacio--info ha de tenir border-left", success: "✅ .notificacio--info té border-left" },
        { test: (css) => /\.notificacio--exit/.test(css), msg: "❌ El CSS ha de tenir .notificacio--exit", success: "✅ El CSS té .notificacio--exit" },
        { test: (css) => /\.notificacio--alerta/.test(css), msg: "❌ El CSS ha de tenir .notificacio--alerta", success: "✅ El CSS té .notificacio--alerta" },
        { test: (css) => /\.notificacio--perill/.test(css), msg: "❌ El CSS ha de tenir .notificacio--perill", success: "✅ El CSS té .notificacio--perill" },
        { test: (css) => /\.notificacio__boto:hover/.test(css), msg: "❌ El CSS ha de tenir .notificacio__boto:hover", success: "✅ El CSS té .notificacio__boto:hover" }
      ]
    },
    starterCode: `// Defineix el mapa niuat $tipus


// Crea .notificacio amb nesting i modificadors
.notificacio {
  
}
`
  },

  // REPT 17 (DIFÍCIL): Formulari avançat amb validació visual
  {
    id: 17,
    title: "⚠️ Repte 17: El Formulari Intel·ligent",
    badge: "Nivell 17 - DIFÍCIL",
    description: "Crea un formulari avançat amb validació visual. Defineix $espaiat-base: 12px, $color-base: teal, $color-valid: green, $color-invalid: red, $color-focus: blue, $radi: 6px. .formulari amb nesting: &__camp amb padding: $espaiat-base * 2, border: 2px solid lighten($color-base, 30%), border-radius: $radi, transition: all 0.3s. Dins de &__camp: &__etiqueta amb display: block, margin-bottom: $espaiat-base, font-weight: bold. &__input amb width: 100%, padding: $espaiat-base, border: none, &:focus amb outline: none. &__camp &:focus-within amb border-color: $color-focus, box-shadow: 0 0 0 3px rgba($color-focus, 0.2). &__camp--valid amb border-color: $color-valid, background: lighten($color-valid, 40%). &__camp--invalid amb border-color: $color-invalid, background: lighten($color-invalid, 40%). &__missatge-error amb color: $color-invalid, font-size: $espaiat-base. &__boto amb background: $color-base, color: white, padding: $espaiat-base * 2, border: none, border-radius: $radi, cursor: pointer, &:hover amb darken($color-base, 15%), &:active amb transform: scale(0.98).",
    hint: "Fes nesting profund: .formulari { &__camp { &__etiqueta { } &__input { } } &__camp--valid { } &__camp--invalid { } }",
    points: 55,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><form class="formulari"><div class="formulari__camp"><label class="formulari__camp__etiqueta">Nom</label><input type="text" class="formulari__camp__input" placeholder="El teu nom"></div><div class="formulari__camp formulari__camp--valid"><label class="formulari__camp__etiqueta">Email</label><input type="email" class="formulari__camp__input" value="correcte@email.com"></div><div class="formulari__camp formulari__camp--invalid"><label class="formulari__camp__etiqueta">Contrasenya</label><input type="password" class="formulari__camp__input" value="123"><span class="formulari__missatge-error">Contrasenya massa curta</span></div><button class="formulari__boto">Enviar</button></form></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$espaiat-base\s*:\s*12px/.test(code), msg: "❌ Has de definir $espaiat-base: 12px", success: "✅ Has definit $espaiat-base: 12px" },
        { test: (code) => /\$color-base\s*:\s*(teal|darkcyan|cadetblue)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color teal", success: "✅ Has definit $color-base" },
        { test: (code) => /\$color-valid\s*:\s*(green|lime|forestgreen)/i.test(code), msg: "❌ Has de definir $color-valid amb un nom de color green", success: "✅ Has definit $color-valid" },
        { test: (code) => /\$color-invalid\s*:\s*(red|crimson|firebrick)/i.test(code), msg: "❌ Has de definir $color-invalid amb un nom de color red", success: "✅ Has definit $color-invalid" },
        { test: (code) => /\$color-focus\s*:\s*(blue|dodgerblue|navy)/i.test(code), msg: "❌ Has de definir $color-focus amb un nom de color blue", success: "✅ Has definit $color-focus" },
        { test: (code) => /\$radi\s*:\s*6px/.test(code), msg: "❌ Has de definir $radi: 6px", success: "✅ Has definit $radi: 6px" },
        { test: (code) => /\.formulari\s*\{[\s\S]*&__camp\s*\{/.test(code), msg: "❌ Has d'utilitzar &__camp dins de .formulari", success: "✅ Has utilitzat &__camp" },
        { test: (code) => /&__camp[\s\S]*padding\s*:\s*\$espaiat-base\s*\*\s*2/.test(code), msg: "❌ &__camp ha de tenir padding: $espaiat-base * 2", success: "✅ &__camp té padding calculat" },
        { test: (code) => /&__camp[\s\S]*border\s*:\s*2px\s+solid\s+lighten\s*\(\s*\$color-base\s*,\s*30%\s*\)/.test(code), msg: "❌ &__camp ha de tenir border: 2px solid lighten($color-base, 30%)", success: "✅ &__camp té border calculat" },
        { test: (code) => /&__camp[\s\S]*border-radius\s*:\s*\$radi/.test(code), msg: "❌ &__camp ha de tenir border-radius: $radi", success: "✅ &__camp té border-radius: $radi" },
        { test: (code) => /&__camp[\s\S]*transition\s*:\s*all\s+0\.3s/.test(code), msg: "❌ &__camp ha de tenir transition: all 0.3s", success: "✅ &__camp té transition: all 0.3s" },
        { test: (code) => /&__camp\s*\{[\s\S]*&__etiqueta/.test(code), msg: "❌ &__etiqueta ha d'estar imbricat dins de &__camp", success: "✅ &__etiqueta està imbricat" },
        { test: (code) => /&__etiqueta[\s\S]*display\s*:\s*block/.test(code), msg: "❌ &__etiqueta ha de tenir display: block", success: "✅ &__etiqueta té display: block" },
        { test: (code) => /&__etiqueta[\s\S]*margin-bottom\s*:\s*\$espaiat-base/.test(code), msg: "❌ &__etiqueta ha de tenir margin-bottom: $espaiat-base", success: "✅ &__etiqueta té margin-bottom: $espaiat-base" },
        { test: (code) => /&__etiqueta[\s\S]*font-weight\s*:\s*bold/.test(code), msg: "❌ &__etiqueta ha de tenir font-weight: bold", success: "✅ &__etiqueta té font-weight: bold" },
        { test: (code) => /&__camp\s*\{[\s\S]*&__input/.test(code), msg: "❌ &__input ha d'estar imbricat dins de &__camp", success: "✅ &__input està imbricat" },
        { test: (code) => /&__input[\s\S]*width\s*:\s*100%/.test(code), msg: "❌ &__input ha de tenir width: 100%", success: "✅ &__input té width: 100%" },
        { test: (code) => /&__input[\s\S]*padding\s*:\s*\$espaiat-base/.test(code), msg: "❌ &__input ha de tenir padding: $espaiat-base", success: "✅ &__input té padding: $espaiat-base" },
        { test: (code) => /&__input[\s\S]*border\s*:\s*none/.test(code), msg: "❌ &__input ha de tenir border: none", success: "✅ &__input té border: none" },
        { test: (code) => /&__input[\s\S]*&:focus[\s\S]*outline\s*:\s*none/.test(code), msg: "❌ &__input &:focus ha de tenir outline: none", success: "✅ &__input &:focus té outline: none" },
        { test: (code) => /&__camp[\s\S]*&:focus-within[\s\S]*border-color\s*:\s*\$color-focus/.test(code), msg: "❌ &__camp &:focus-within ha de tenir border-color: $color-focus", success: "✅ &__camp &:focus-within té border-color: $color-focus" },
        { test: (code) => /&__camp[\s\S]*&:focus-within[\s\S]*box-shadow\s*:\s*0\s+0\s+0\s+3px\s+rgba\s*\(\s*\$color-focus\s*,\s*0\.2\s*\)/.test(code), msg: "❌ &__camp &:focus-within ha de tenir box-shadow: 0 0 0 3px rgba($color-focus, 0.2)", success: "✅ &__camp &:focus-within té box-shadow calculat" },
        { test: (code) => /\.formulari\s*\{[\s\S]*&__camp--valid/.test(code), msg: "❌ Has d'utilitzar &__camp--valid dins de .formulari", success: "✅ Has utilitzat &__camp--valid" },
        { test: (code) => /&__camp--valid[\s\S]*border-color\s*:\s*\$color-valid/.test(code), msg: "❌ &__camp--valid ha de tenir border-color: $color-valid", success: "✅ &__camp--valid té border-color: $color-valid" },
        { test: (code) => /&__camp--valid[\s\S]*background\s*:\s*lighten\s*\(\s*\$color-valid\s*,\s*40%\s*\)/.test(code), msg: "❌ &__camp--valid ha de tenir background: lighten($color-valid, 40%)", success: "✅ &__camp--valid té background calculat" },
        { test: (code) => /\.formulari\s*\{[\s\S]*&__camp--invalid/.test(code), msg: "❌ Has d'utilitzar &__camp--invalid dins de .formulari", success: "✅ Has utilitzat &__camp--invalid" },
        { test: (code) => /&__camp--invalid[\s\S]*border-color\s*:\s*\$color-invalid/.test(code), msg: "❌ &__camp--invalid ha de tenir border-color: $color-invalid", success: "✅ &__camp--invalid té border-color: $color-invalid" },
        { test: (code) => /&__camp--invalid[\s\S]*background\s*:\s*lighten\s*\(\s*\$color-invalid\s*,\s*40%\s*\)/.test(code), msg: "❌ &__camp--invalid ha de tenir background: lighten($color-invalid, 40%)", success: "✅ &__camp--invalid té background calculat" },
        { test: (code) => /\.formulari\s*\{[\s\S]*&__missatge-error/.test(code), msg: "❌ Has d'utilitzar &__missatge-error dins de .formulari", success: "✅ Has utilitzat &__missatge-error" },
        { test: (code) => /&__missatge-error[\s\S]*color\s*:\s*\$color-invalid/.test(code), msg: "❌ &__missatge-error ha de tenir color: $color-invalid", success: "✅ &__missatge-error té color: $color-invalid" },
        { test: (code) => /&__missatge-error[\s\S]*font-size\s*:\s*\$espaiat-base/.test(code), msg: "❌ &__missatge-error ha de tenir font-size: $espaiat-base", success: "✅ &__missatge-error té font-size: $espaiat-base" },
        { test: (code) => /\.formulari\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .formulari", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /&__boto[\s\S]*background\s*:\s*\$color-base/.test(code), msg: "❌ &__boto ha de tenir background: $color-base", success: "✅ &__boto té background: $color-base" },
        { test: (code) => /&__boto[\s\S]*color\s*:\s*(white|snow)/i.test(code), msg: "❌ &__boto ha de tenir color white", success: "✅ &__boto té color white" },
        { test: (code) => /&__boto[\s\S]*padding\s*:\s*\$espaiat-base\s*\*\s*2/.test(code), msg: "❌ &__boto ha de tenir padding: $espaiat-base * 2", success: "✅ &__boto té padding calculat" },
        { test: (code) => /&__boto[\s\S]*border\s*:\s*none/.test(code), msg: "❌ &__boto ha de tenir border: none", success: "✅ &__boto té border: none" },
        { test: (code) => /&__boto[\s\S]*border-radius\s*:\s*\$radi/.test(code), msg: "❌ &__boto ha de tenir border-radius: $radi", success: "✅ &__boto té border-radius: $radi" },
        { test: (code) => /&__boto[\s\S]*cursor\s*:\s*pointer/.test(code), msg: "❌ &__boto ha de tenir cursor: pointer", success: "✅ &__boto té cursor: pointer" },
        { test: (code) => /&__boto[\s\S]*&:hover[\s\S]*darken\s*\(\s*\$color-base\s*,\s*15%\s*\)/.test(code), msg: "❌ &__boto &:hover ha d'utilitzar darken($color-base, 15%)", success: "✅ &__boto &:hover utilitza darken()" },
        { test: (code) => /&__boto[\s\S]*&:active[\s\S]*transform\s*:\s*scale\s*\(\s*0\.98\s*\)/.test(code), msg: "❌ &__boto &:active ha de tenir transform: scale(0.98)", success: "✅ &__boto &:active té scale(0.98)" }
      ],
      css: [
        { test: (css) => /\.formulari__camp[\s\S]*padding\s*:\s*24px/.test(css), msg: "❌ .formulari__camp ha de tenir padding: 24px", success: "✅ .formulari__camp té padding: 24px" },
        { test: (css) => /\.formulari__camp__etiqueta[\s\S]*display\s*:\s*block/.test(css), msg: "❌ .formulari__camp__etiqueta ha de tenir display: block", success: "✅ .formulari__camp__etiqueta té display: block" },
        { test: (css) => /\.formulari__camp__input[\s\S]*width\s*:\s*100%/.test(css), msg: "❌ .formulari__camp__input ha de tenir width: 100%", success: "✅ .formulari__camp__input té width: 100%" },
        { test: (css) => /\.formulari__camp:focus-within/.test(css), msg: "❌ El CSS ha de tenir .formulari__camp:focus-within", success: "✅ El CSS té .formulari__camp:focus-within" },
        { test: (css) => /\.formulari__camp--valid/.test(css), msg: "❌ El CSS ha de tenir .formulari__camp--valid", success: "✅ El CSS té .formulari__camp--valid" },
        { test: (css) => /\.formulari__camp--invalid/.test(css), msg: "❌ El CSS ha de tenir .formulari__camp--invalid", success: "✅ El CSS té .formulari__camp--invalid" },
        { test: (css) => /\.formulari__missatge-error/.test(css), msg: "❌ El CSS ha de tenir .formulari__missatge-error", success: "✅ El CSS té .formulari__missatge-error" },
        { test: (css) => /\.formulari__boto:hover/.test(css), msg: "❌ El CSS ha de tenir .formulari__boto:hover", success: "✅ El CSS té .formulari__boto:hover" },
        { test: (css) => /\.formulari__boto:active/.test(css), msg: "❌ El CSS ha de tenir .formulari__boto:active", success: "✅ El CSS té .formulari__boto:active" }
      ]
    },
    starterCode: `// Defineix les variables


// Crea el formulari amb nesting profund
.formulari {
  
}
`
  },

  // REPT 18 (DIFÍCIL): Targetes de producte amb variants
  {
    id: 18,
    title: "⚠️ Repte 18: Les Targetes de Producte",
    badge: "Nivell 18 - DIFÍCIL",
    description: "Crea un sistema complet de targetes de producte. Defineix $color-base: orange, $color-text: white, $color-preu: gold, $mida-base: 15px, $radi: 10px. Crea un mapa $productes: ('basic': ('preu': 9, 'color': lighten($color-base, 20%)), 'pro': ('preu': 29, 'color': $color-base), 'empresari': ('preu': 99, 'color': darken($color-base, 20%))). Utilitza map-get niuat per accedir als valors. .targeta-producte amb nesting: &__nom amb font-size: $mida-base * 1.5, color: $color-text, margin-bottom: $mida-base. &__preu amb font-size: $mida-base * 3, color: $color-preu, font-weight: bold, margin-bottom: $mida-base * 2. &__caracteristiques amb list-style: none, padding: 0, margin-bottom: $mida-base * 2, li amb padding: $mida-base * 0.5 0, border-bottom: 1px solid rgba($color-text, 0.2), &:last-child amb border-bottom: none. &__boto amb background: map-get(map-get($productes, 'basic'), 'color'), color: $color-text, padding: $mida-base * 1.5 $mida-base * 3, border: none, border-radius: $radi, cursor: pointer, font-size: $mida-base, transition: all 0.3s, &:hover amb darken el color 15%, &:active amb transform: scale(0.95). Crea modificadors: &--basic amb background: map-get(map-get($productes, 'basic'), 'color'), i a dins .targeta-producte__boto amb background: darken(map-get(map-get($productes, 'basic'), 'color'), 10%), &--pro amb background: map-get(map-get($productes, 'pro'), 'color'), i a dins .targeta-producte__boto amb background: darken(map-get(map-get($productes, 'pro'), 'color'), 10%), &--empresari amb background: map-get(map-get($productes, 'empresari'), 'color'), i a dins .targeta-producte__boto amb background: darken(map-get(map-get($productes, 'empresari'), 'color'), 10%).",
    hint: "Utilitza map-get(map-get($productes, 'basic'), 'color') per accedir al color de cada producte. Fes servir darken() per als estats hover dels botons. Compte: dins de &--basic, &__boto generaria .targeta-producte--basic__boto; escriu .targeta-producte__boto per obtenir .targeta-producte--basic .targeta-producte__boto.",
    points: 60,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta-producte targeta-producte--basic"><h3 class="targeta-producte__nom">Basic</h3><p class="targeta-producte__preu">9€</p><ul class="targeta-producte__caracteristiques"><li>1 Usuari</li><li>5GB</li></ul><button class="targeta-producte__boto">Triar</button></div><div class="targeta-producte targeta-producte--pro"><h3 class="targeta-producte__nom">Pro</h3><p class="targeta-producte__preu">29€</p><ul class="targeta-producte__caracteristiques"><li>10 Usuaris</li><li>50GB</li><li>Suport</li></ul><button class="targeta-producte__boto">Triar</button></div><div class="targeta-producte targeta-producte--empresari"><h3 class="targeta-producte__nom">Enterprise</h3><p class="targeta-producte__preu">99€</p><ul class="targeta-producte__caracteristiques"><li>Il·limitats</li><li>500GB</li><li>24/7</li></ul><button class="targeta-producte__boto">Triar</button></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(orange|darkorange)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color orange", success: "✅ Has definit $color-base" },
        { test: (code) => /\$color-text\s*:\s*(white|snow)/i.test(code), msg: "❌ Has de definir $color-text amb un nom de color white", success: "✅ Has definit $color-text" },
        { test: (code) => /\$color-preu\s*:\s*(gold|goldenrod|yellow)/i.test(code), msg: "❌ Has de definir $color-preu amb un nom de color gold", success: "✅ Has definit $color-preu" },
        { test: (code) => /\$mida-base\s*:\s*15px/.test(code), msg: "❌ Has de definir $mida-base: 15px", success: "✅ Has definit $mida-base: 15px" },
        { test: (code) => /\$radi\s*:\s*10px/.test(code), msg: "❌ Has de definir $radi: 10px", success: "✅ Has definit $radi: 10px" },
        { test: (code) => /\$productes\s*:\s*\(/.test(code), msg: "❌ Has de definir $productes com a mapa", success: "✅ Has definit $productes" },
        { test: (code) => /'basic'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'basic' com a sub-mapa", success: "✅ El mapa té 'basic'" },
        { test: (code) => /'pro'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'pro' com a sub-mapa", success: "✅ El mapa té 'pro'" },
        { test: (code) => /'empresari'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'empresari' com a sub-mapa", success: "✅ El mapa té 'empresari'" },
        { test: (code) => /'preu'\s*:\s*9/.test(code), msg: "❌ 'basic' ha de tenir 'preu': 9", success: "✅ 'basic' té 'preu': 9" },
        { test: (code) => /'preu'\s*:\s*29/.test(code), msg: "❌ 'pro' ha de tenir 'preu': 29", success: "✅ 'pro' té 'preu': 29" },
        { test: (code) => /'preu'\s*:\s*99/.test(code), msg: "❌ 'empresari' ha de tenir 'preu': 99", success: "✅ 'empresari' té 'preu': 99" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$productes\s*,\s*'basic'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'basic' 'color'", success: "✅ Has utilitzat map-get niuat per 'basic'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$productes\s*,\s*'pro'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'pro' 'color'", success: "✅ Has utilitzat map-get niuat per 'pro'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$productes\s*,\s*'empresari'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'empresari' 'color'", success: "✅ Has utilitzat map-get niuat per 'empresari'" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&__nom/.test(code), msg: "❌ Has d'utilitzar &__nom dins de .targeta-producte", success: "✅ Has utilitzat &__nom" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&__preu/.test(code), msg: "❌ Has d'utilitzar &__preu dins de .targeta-producte", success: "✅ Has utilitzat &__preu" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&__caracteristiques/.test(code), msg: "❌ Has d'utilitzar &__caracteristiques dins de .targeta-producte", success: "✅ Has utilitzat &__caracteristiques" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .targeta-producte", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&--basic/.test(code), msg: "❌ Has d'utilitzar &--basic dins de .targeta-producte", success: "✅ Has utilitzat &--basic" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&--pro/.test(code), msg: "❌ Has d'utilitzar &--pro dins de .targeta-producte", success: "✅ Has utilitzat &--pro" },
        { test: (code) => /\.targeta-producte\s*\{[\s\S]*&--empresari/.test(code), msg: "❌ Has d'utilitzar &--empresari dins de .targeta-producte", success: "✅ Has utilitzat &--empresari" },
        { test: (code) => /&__nom[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*1\.5/.test(code), msg: "❌ &__nom ha de tenir font-size: $mida-base * 1.5", success: "✅ &__nom té font-size calculat" },
        { test: (code) => /&__preu[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*3/.test(code), msg: "❌ &__preu ha de tenir font-size: $mida-base * 3", success: "✅ &__preu té font-size calculat" },
        { test: (code) => /&__preu[\s\S]*color\s*:\s*\$color-preu/.test(code), msg: "❌ &__preu ha de tenir color: $color-preu", success: "✅ &__preu té color: $color-preu" },
        { test: (code) => /&__caracteristiques[\s\S]*list-style\s*:\s*none/.test(code), msg: "❌ &__caracteristiques ha de tenir list-style: none", success: "✅ &__caracteristiques té list-style: none" },
        { test: (code) => /&__caracteristiques[\s\S]*li[\s\S]*padding\s*:\s*\$mida-base\s*\*\s*0\.5\s+0/.test(code), msg: "❌ li ha de tenir padding: $mida-base * 0.5 0", success: "✅ li té padding calculat" },
        { test: (code) => /&__caracteristiques[\s\S]*li[\s\S]*border-bottom\s*:\s*1px\s+solid\s+rgba\s*\(\s*\$color-text\s*,\s*0\.2\s*\)/.test(code), msg: "❌ li ha de tenir border-bottom: 1px solid rgba($color-text, 0.2)", success: "✅ li té border-bottom calculat" },
        { test: (code) => /&__caracteristiques[\s\S]*li[\s\S]*&:last-child[\s\S]*border-bottom\s*:\s*none/.test(code), msg: "❌ li &:last-child ha de tenir border-bottom: none", success: "✅ li &:last-child té border-bottom: none" },
        { test: (code) => /&__boto[\s\S]*padding\s*:\s*\$mida-base\s*\*\s*1\.5\s+\$mida-base\s*\*\s*3/.test(code), msg: "❌ &__boto ha de tenir padding: $mida-base * 1.5 $mida-base * 3", success: "✅ &__boto té padding calculat" },
        { test: (code) => /&__boto[\s\S]*border-radius\s*:\s*\$radi/.test(code), msg: "❌ &__boto ha de tenir border-radius: $radi", success: "✅ &__boto té border-radius: $radi" },
        { test: (code) => /&__boto[\s\S]*transition\s*:\s*all\s+0\.3s/.test(code), msg: "❌ &__boto ha de tenir transition: all 0.3s", success: "✅ &__boto té transition: all 0.3s" },
        { test: (code) => /&__boto[\s\S]*&:hover[\s\S]*darken/.test(code), msg: "❌ &__boto &:hover ha d'utilitzar darken()", success: "✅ &__boto &:hover utilitza darken()" },
        { test: (code) => /&__boto[\s\S]*&:active[\s\S]*transform\s*:\s*scale\s*\(\s*0\.95\s*\)/.test(code), msg: "❌ &__boto &:active ha de tenir transform: scale(0.95)", success: "✅ &__boto &:active té scale(0.95)" }
      ],
      css: [
        { test: (css) => /\.targeta-producte__nom[\s\S]*font-size\s*:\s*22\.5px/.test(css), msg: "❌ .targeta-producte__nom ha de tenir font-size: 22.5px", success: "✅ .targeta-producte__nom té font-size: 22.5px" },
        { test: (css) => /\.targeta-producte__preu[\s\S]*font-size\s*:\s*45px/.test(css), msg: "❌ .targeta-producte__preu ha de tenir font-size: 45px", success: "✅ .targeta-producte__preu té font-size: 45px" },
        { test: (css) => /\.targeta-producte__preu[\s\S]*color[\s\S]*(gold|goldenrod|yellow)/i.test(css), msg: "❌ .targeta-producte__preu ha de tenir color gold", success: "✅ .targeta-producte__preu té color gold" },
        { test: (css) => /\.targeta-producte__caracteristiques\s+li:last-child/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte__caracteristiques li:last-child", success: "✅ El CSS té li:last-child" },
        { test: (css) => /\.targeta-producte__boto:hover/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte__boto:hover", success: "✅ El CSS té .targeta-producte__boto:hover" },
        { test: (css) => /\.targeta-producte__boto:active/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte__boto:active", success: "✅ El CSS té .targeta-producte__boto:active" },
        { test: (css) => /\.targeta-producte--basic/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte--basic", success: "✅ El CSS té .targeta-producte--basic" },
        { test: (css) => /\.targeta-producte--pro/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte--pro", success: "✅ El CSS té .targeta-producte--pro" },
        { test: (css) => /\.targeta-producte--empresari/.test(css), msg: "❌ El CSS ha de tenir .targeta-producte--empresari", success: "✅ El CSS té .targeta-producte--empresari" }
      ]
    },
    starterCode: `// Defineix les variables


// Defineix el mapa $productes


// Crea .targeta-producte amb nesting i modificadors
.targeta-producte {
  
}
`
  },

  // REPT 19 (DIFÍCIL): Dashboard amb widgets
  {
    id: 19,
    title: "⚠️ Repte 19: El Dashboard de Widgets",
    badge: "Nivell 19 - DIFÍCIL",
    description: "Crea un dashboard amb widgets. Defineix $color-base: navy, $color-text: white, $color-accent: coral, $mida-base: 20px, $radi: 8px. Crea un mapa $widgets: ('stats': ('color': blue, 'icona': '📊'), 'vendes': ('color': green, 'icona': '💰'), 'usuaris': ('color': purple, 'icona': '👥'), 'alertes': ('color': red, 'icona': '⚠')). Utilitza map-get niuat per accedir als valors. .dashboard amb display: flex, flex-wrap: wrap, gap: $mida-base * 1.5, padding: $mida-base * 2. .widget amb nesting: background: $color-base, color: $color-text, padding: $mida-base * 1.5, border-radius: $radi, width: calc(33.333% - #{$mida-base}), box-sizing: border-box, transition: transform 0.3s, &:hover amb transform: translateY(-5px), box-shadow: 0 10px 20px rgba(black, 0.2). &__capçalera amb display: flex, align-items: center, margin-bottom: $mida-base, padding-bottom: $mida-base, border-bottom: 2px solid rgba($color-text, 0.2). &__icona amb font-size: $mida-base * 2, margin-right: $mida-base. &__titol amb font-size: $mida-base * 1.2, font-weight: bold. &__contingut amb font-size: $mida-base * 0.9, line-height: 1.6. &__peu amb margin-top: $mida-base, padding-top: $mida-base, border-top: 1px solid rgba($color-text, 0.2), font-size: $mida-base * 0.8, opacity: 0.8. Crea modificadors: &--stats amb background: map-get(map-get($widgets, 'stats'), 'color'), .widget__icona::before amb content: map-get(map-get($widgets, 'stats'), 'icona'), &--vendes amb background: map-get(map-get($widgets, 'vendes'), 'color'), .widget__icona::before amb content: map-get(map-get($widgets, 'vendes'), 'icona'), &--usuaris amb background: map-get(map-get($widgets, 'usuaris'), 'color'), .widget__icona::before amb content: map-get(map-get($widgets, 'usuaris'), 'icona'), &--alertes amb background: map-get(map-get($widgets, 'alertes'), 'color'), .widget__icona::before amb content: map-get(map-get($widgets, 'alertes'), 'icona').",
    hint: "Utilitza map-get(map-get($widgets, 'stats'), 'color') per accedir al color de cada widget. Dins de cada modificador escriu .widget__icona::before { content: ... } (si hi poses &__icona generaria .widget--stats__icona, que no existeix a l'HTML).",
    points: 65,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="dashboard"><div class="widget widget--stats"><div class="widget__capçalera"><span class="widget__icona"></span><h3 class="widget__titol">Estadístiques</h3></div><div class="widget__contingut"><p>Visites: 1,234</p><p>Conversions: 56</p></div><div class="widget__peu">Actualitzat fa 5 minuts</div></div><div class="widget widget--vendes"><div class="widget__capçalera"><span class="widget__icona"></span><h3 class="widget__titol">Vendes</h3></div><div class="widget__contingut"><p>Total: 9,876€</p><p>Pendent: 1,234€</p></div><div class="widget__peu">Actualitzat fa 10 minuts</div></div><div class="widget widget--usuaris"><div class="widget__capçalera"><span class="widget__icona"></span><h3 class="widget__titol">Usuaris</h3></div><div class="widget__contingut"><p>Actius: 456</p><p>Nous: 23</p></div><div class="widget__peu">Actualitzat fa 2 minuts</div></div><div class="widget widget--alertes"><div class="widget__capçalera"><span class="widget__icona"></span><h3 class="widget__titol">Alertes</h3></div><div class="widget__contingut"><p>Crítiques: 2</p><p>Avisos: 15</p></div><div class="widget__peu">Actualitzat fa 1 minut</div></div></div></body></html>`,
    validation: {
      scss: [
        { test: (code) => /\$color-base\s*:\s*(navy|darkblue|midnightblue)/i.test(code), msg: "❌ Has de definir $color-base amb un nom de color navy", success: "✅ Has definit $color-base" },
        { test: (code) => /\$color-text\s*:\s*(white|snow)/i.test(code), msg: "❌ Has de definir $color-text amb un nom de color white", success: "✅ Has definit $color-text" },
        { test: (code) => /\$color-accent\s*:\s*(coral|tomato|salmon)/i.test(code), msg: "❌ Has de definir $color-accent amb un nom de color coral", success: "✅ Has definit $color-accent" },
        { test: (code) => /\$mida-base\s*:\s*20px/.test(code), msg: "❌ Has de definir $mida-base: 20px", success: "✅ Has definit $mida-base: 20px" },
        { test: (code) => /\$radi\s*:\s*8px/.test(code), msg: "❌ Has de definir $radi: 8px", success: "✅ Has definit $radi: 8px" },
        { test: (code) => /\$widgets\s*:\s*\(/.test(code), msg: "❌ Has de definir $widgets com a mapa", success: "✅ Has definit $widgets" },
        { test: (code) => /'stats'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'stats' com a sub-mapa", success: "✅ El mapa té 'stats'" },
        { test: (code) => /'vendes'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'vendes' com a sub-mapa", success: "✅ El mapa té 'vendes'" },
        { test: (code) => /'usuaris'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'usuaris' com a sub-mapa", success: "✅ El mapa té 'usuaris'" },
        { test: (code) => /'alertes'\s*:\s*\(/.test(code), msg: "❌ El mapa ha de tenir 'alertes' com a sub-mapa", success: "✅ El mapa té 'alertes'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'stats'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'stats' 'color'", success: "✅ Has utilitzat map-get niuat per 'stats'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'vendes'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'vendes' 'color'", success: "✅ Has utilitzat map-get niuat per 'vendes'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'usuaris'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'usuaris' 'color'", success: "✅ Has utilitzat map-get niuat per 'usuaris'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'alertes'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'alertes' 'color'", success: "✅ Has utilitzat map-get niuat per 'alertes'" },
        { test: (code) => /map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'stats'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ Has d'utilitzar map-get niuat per 'stats' 'icona'", success: "✅ Has utilitzat map-get niuat per 'stats' 'icona'" },
        { test: (code) => /\.dashboard[\s\S]*display\s*:\s*flex/.test(code), msg: "❌ .dashboard ha de tenir display: flex", success: "✅ .dashboard té display: flex" },
        { test: (code) => /\.dashboard[\s\S]*flex-wrap\s*:\s*wrap/.test(code), msg: "❌ .dashboard ha de tenir flex-wrap: wrap", success: "✅ .dashboard té flex-wrap: wrap" },
        { test: (code) => /\.dashboard[\s\S]*gap\s*:\s*\$mida-base\s*\*\s*1\.5/.test(code), msg: "❌ .dashboard ha de tenir gap: $mida-base * 1.5", success: "✅ .dashboard té gap calculat" },
        { test: (code) => /\.dashboard[\s\S]*padding\s*:\s*\$mida-base\s*\*\s*2/.test(code), msg: "❌ .dashboard ha de tenir padding: $mida-base * 2", success: "✅ .dashboard té padding calculat" },
        { test: (code) => /\.widget[\s\S]*background\s*:\s*\$color-base/.test(code), msg: "❌ .widget ha de tenir background: $color-base", success: "✅ .widget té background: $color-base" },
        { test: (code) => /\.widget[\s\S]*color\s*:\s*\$color-text/.test(code), msg: "❌ .widget ha de tenir color: $color-text", success: "✅ .widget té color: $color-text" },
        { test: (code) => /\.widget[\s\S]*padding\s*:\s*\$mida-base\s*\*\s*1\.5/.test(code), msg: "❌ .widget ha de tenir padding: $mida-base * 1.5", success: "✅ .widget té padding calculat" },
        { test: (code) => /\.widget[\s\S]*border-radius\s*:\s*\$radi/.test(code), msg: "❌ .widget ha de tenir border-radius: $radi", success: "✅ .widget té border-radius: $radi" },
        { test: (code) => /\.widget[\s\S]*width\s*:\s*calc\s*\(\s*33\.333%\s*-\s*#\{\s*\$mida-base\s*\}\s*\)/.test(code), msg: "❌ .widget ha de tenir width: calc(33.333% - #{$mida-base})", success: "✅ .widget té width calculat amb interpolació" },
        { test: (code) => /\.widget[\s\S]*box-sizing\s*:\s*border-box/.test(code), msg: "❌ .widget ha de tenir box-sizing: border-box", success: "✅ .widget té box-sizing: border-box" },
        { test: (code) => /\.widget[\s\S]*transition\s*:\s*transform\s+0\.3s/.test(code), msg: "❌ .widget ha de tenir transition: transform 0.3s", success: "✅ .widget té transition: transform 0.3s" },
        { test: (code) => /\.widget[\s\S]*&:hover[\s\S]*transform\s*:\s*translateY\s*\(\s*-5px\s*\)/.test(code), msg: "❌ .widget &:hover ha de tenir transform: translateY(-5px)", success: "✅ .widget &:hover té translateY(-5px)" },
        { test: (code) => /\.widget[\s\S]*&:hover[\s\S]*box-shadow\s*:\s*0\s+10px\s+20px\s+rgba\s*\(\s*(black|navy|darkblue)\s*,\s*0\.2\s*\)/i.test(code), msg: "❌ .widget &:hover ha de tenir box-shadow: 0 10px 20px rgba(black, 0.2)", success: "✅ .widget &:hover té box-shadow calculat" },
        { test: (code) => /\.widget\s*\{[\s\S]*&__capçalera/.test(code), msg: "❌ Has d'utilitzar &__capçalera dins de .widget", success: "✅ Has utilitzat &__capçalera" },
        { test: (code) => /\.widget\s*\{[\s\S]*&__icona/.test(code), msg: "❌ Has d'utilitzar &__icona dins de .widget", success: "✅ Has utilitzat &__icona" },
        { test: (code) => /\.widget\s*\{[\s\S]*&__titol/.test(code), msg: "❌ Has d'utilitzar &__titol dins de .widget", success: "✅ Has utilitzat &__titol" },
        { test: (code) => /\.widget\s*\{[\s\S]*&__contingut/.test(code), msg: "❌ Has d'utilitzar &__contingut dins de .widget", success: "✅ Has utilitzat &__contingut" },
        { test: (code) => /\.widget\s*\{[\s\S]*&__peu/.test(code), msg: "❌ Has d'utilitzar &__peu dins de .widget", success: "✅ Has utilitzat &__peu" },
        { test: (code) => /\.widget\s*\{[\s\S]*&--stats/.test(code), msg: "❌ Has d'utilitzar &--stats dins de .widget", success: "✅ Has utilitzat &--stats" },
        { test: (code) => /\.widget\s*\{[\s\S]*&--vendes/.test(code), msg: "❌ Has d'utilitzar &--vendes dins de .widget", success: "✅ Has utilitzat &--vendes" },
        { test: (code) => /\.widget\s*\{[\s\S]*&--usuaris/.test(code), msg: "❌ Has d'utilitzar &--usuaris dins de .widget", success: "✅ Has utilitzat &--usuaris" },
        { test: (code) => /\.widget\s*\{[\s\S]*&--alertes/.test(code), msg: "❌ Has d'utilitzar &--alertes dins de .widget", success: "✅ Has utilitzat &--alertes" },
        { test: (code) => /&__capçalera[\s\S]*display\s*:\s*flex/.test(code), msg: "❌ &__capçalera ha de tenir display: flex", success: "✅ &__capçalera té display: flex" },
        { test: (code) => /&__capçalera[\s\S]*align-items\s*:\s*center/.test(code), msg: "❌ &__capçalera ha de tenir align-items: center", success: "✅ &__capçalera té align-items: center" },
        { test: (code) => /&__capçalera[\s\S]*margin-bottom\s*:\s*\$mida-base/.test(code), msg: "❌ &__capçalera ha de tenir margin-bottom: $mida-base", success: "✅ &__capçalera té margin-bottom: $mida-base" },
        { test: (code) => /&__capçalera[\s\S]*padding-bottom\s*:\s*\$mida-base/.test(code), msg: "❌ &__capçalera ha de tenir padding-bottom: $mida-base", success: "✅ &__capçalera té padding-bottom: $mida-base" },
        { test: (code) => /&__capçalera[\s\S]*border-bottom\s*:\s*2px\s+solid\s+rgba\s*\(\s*\$color-text\s*,\s*0\.2\s*\)/.test(code), msg: "❌ &__capçalera ha de tenir border-bottom: 2px solid rgba($color-text, 0.2)", success: "✅ &__capçalera té border-bottom calculat" },
        { test: (code) => /&__icona[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*2/.test(code), msg: "❌ &__icona ha de tenir font-size: $mida-base * 2", success: "✅ &__icona té font-size calculat" },
        { test: (code) => /&__icona[\s\S]*margin-right\s*:\s*\$mida-base/.test(code), msg: "❌ &__icona ha de tenir margin-right: $mida-base", success: "✅ &__icona té margin-right: $mida-base" },
        { test: (code) => /&__titol[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*1\.2/.test(code), msg: "❌ &__titol ha de tenir font-size: $mida-base * 1.2", success: "✅ &__titol té font-size calculat" },
        { test: (code) => /&__titol[\s\S]*font-weight\s*:\s*bold/.test(code), msg: "❌ &__titol ha de tenir font-weight: bold", success: "✅ &__titol té font-weight: bold" },
        { test: (code) => /&__contingut[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*0\.9/.test(code), msg: "❌ &__contingut ha de tenir font-size: $mida-base * 0.9", success: "✅ &__contingut té font-size calculat" },
        { test: (code) => /&__contingut[\s\S]*line-height\s*:\s*1\.6/.test(code), msg: "❌ &__contingut ha de tenir line-height: 1.6", success: "✅ &__contingut té line-height: 1.6" },
        { test: (code) => /&__peu[\s\S]*margin-top\s*:\s*\$mida-base/.test(code), msg: "❌ &__peu ha de tenir margin-top: $mida-base", success: "✅ &__peu té margin-top: $mida-base" },
        { test: (code) => /&__peu[\s\S]*padding-top\s*:\s*\$mida-base/.test(code), msg: "❌ &__peu ha de tenir padding-top: $mida-base", success: "✅ &__peu té padding-top: $mida-base" },
        { test: (code) => /&__peu[\s\S]*border-top\s*:\s*1px\s+solid\s+rgba\s*\(\s*\$color-text\s*,\s*0\.2\s*\)/.test(code), msg: "❌ &__peu ha de tenir border-top: 1px solid rgba($color-text, 0.2)", success: "✅ &__peu té border-top calculat" },
        { test: (code) => /&__peu[\s\S]*font-size\s*:\s*\$mida-base\s*\*\s*0\.8/.test(code), msg: "❌ &__peu ha de tenir font-size: $mida-base * 0.8", success: "✅ &__peu té font-size calculat" },
        { test: (code) => /&__peu[\s\S]*opacity\s*:\s*0\.8/.test(code), msg: "❌ &__peu ha de tenir opacity: 0.8", success: "✅ &__peu té opacity: 0.8" },
        { test: (code) => /&--stats[\s\S]*background\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'stats'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ &--stats ha de tenir background: map-get(map-get($widgets, 'stats'), 'color')", success: "✅ &--stats té background calculat" },
        { test: (code) => /&--stats[\s\S]*\.widget__icona::before[\s\S]*content\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'stats'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ &--stats .widget__icona::before ha de tenir content: map-get(map-get($widgets, 'stats'), 'icona')", success: "✅ &--stats .widget__icona::before té content calculat" },
        { test: (code) => /&--vendes[\s\S]*background\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'vendes'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ &--vendes ha de tenir background: map-get(map-get($widgets, 'vendes'), 'color')", success: "✅ &--vendes té background calculat" },
        { test: (code) => /&--vendes[\s\S]*\.widget__icona::before[\s\S]*content\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'vendes'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ &--vendes .widget__icona::before ha de tenir content: map-get(map-get($widgets, 'vendes'), 'icona')", success: "✅ &--vendes .widget__icona::before té content calculat" },
        { test: (code) => /&--usuaris[\s\S]*background\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'usuaris'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ &--usuaris ha de tenir background: map-get(map-get($widgets, 'usuaris'), 'color')", success: "✅ &--usuaris té background calculat" },
        { test: (code) => /&--usuaris[\s\S]*\.widget__icona::before[\s\S]*content\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'usuaris'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ &--usuaris .widget__icona::before ha de tenir content: map-get(map-get($widgets, 'usuaris'), 'icona')", success: "✅ &--usuaris .widget__icona::before té content calculat" },
        { test: (code) => /&--alertes[\s\S]*background\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'alertes'\s*\)\s*,\s*'color'\s*\)/.test(code), msg: "❌ &--alertes ha de tenir background: map-get(map-get($widgets, 'alertes'), 'color')", success: "✅ &--alertes té background calculat" },
        { test: (code) => /&--alertes[\s\S]*\.widget__icona::before[\s\S]*content\s*:\s*map-get\s*\(\s*map-get\s*\(\s*\$widgets\s*,\s*'alertes'\s*\)\s*,\s*'icona'\s*\)/.test(code), msg: "❌ &--alertes .widget__icona::before ha de tenir content: map-get(map-get($widgets, 'alertes'), 'icona')", success: "✅ &--alertes .widget__icona::before té content calculat" }
      ],
      css: [
        { test: (css) => /\.dashboard[\s\S]*display\s*:\s*flex/.test(css), msg: "❌ .dashboard ha de tenir display: flex", success: "✅ .dashboard té display: flex" },
        { test: (css) => /\.dashboard[\s\S]*gap\s*:\s*30px/.test(css), msg: "❌ .dashboard ha de tenir gap: 30px", success: "✅ .dashboard té gap: 30px" },
        { test: (css) => /\.dashboard[\s\S]*padding\s*:\s*40px/.test(css), msg: "❌ .dashboard ha de tenir padding: 40px", success: "✅ .dashboard té padding: 40px" },
        { test: (css) => /\.widget[\s\S]*width\s*:\s*calc\s*\(\s*33\.333%\s*-\s*20px\s*\)/.test(css), msg: "❌ .widget ha de tenir width: calc(33.333% - 20px)", success: "✅ .widget té width calculat" },
        { test: (css) => /\.widget:hover/.test(css), msg: "❌ El CSS ha de tenir .widget:hover", success: "✅ El CSS té .widget:hover" },
        { test: (css) => /\.widget__capçalera[\s\S]*display\s*:\s*flex/.test(css), msg: "❌ .widget__capçalera ha de tenir display: flex", success: "✅ .widget__capçalera té display: flex" },
        { test: (css) => /\.widget__icona[\s\S]*font-size\s*:\s*40px/.test(css), msg: "❌ .widget__icona ha de tenir font-size: 40px", success: "✅ .widget__icona té font-size: 40px" },
        { test: (css) => /\.widget__titol[\s\S]*font-size\s*:\s*24px/.test(css), msg: "❌ .widget__titol ha de tenir font-size: 24px", success: "✅ .widget__titol té font-size: 24px" },
        { test: (css) => /\.widget__contingut[\s\S]*font-size\s*:\s*18px/.test(css), msg: "❌ .widget__contingut ha de tenir font-size: 18px", success: "✅ .widget__contingut té font-size: 18px" },
        { test: (css) => /\.widget__peu[\s\S]*font-size\s*:\s*16px/.test(css), msg: "❌ .widget__peu ha de tenir font-size: 16px", success: "✅ .widget__peu té font-size: 16px" },
        { test: (css) => /\.widget--stats/.test(css), msg: "❌ El CSS ha de tenir .widget--stats", success: "✅ El CSS té .widget--stats" },
        { test: (css) => /\.widget--vendes/.test(css), msg: "❌ El CSS ha de tenir .widget--vendes", success: "✅ El CSS té .widget--vendes" },
        { test: (css) => /\.widget--usuaris/.test(css), msg: "❌ El CSS ha de tenir .widget--usuaris", success: "✅ El CSS té .widget--usuaris" },
        { test: (css) => /\.widget--alertes/.test(css), msg: "❌ El CSS ha de tenir .widget--alertes", success: "✅ El CSS té .widget--alertes" },
        { test: (css) => /\.widget--stats\s+\.widget__icona::before/.test(css), msg: "❌ El CSS ha de tenir .widget--stats .widget__icona::before", success: "✅ El CSS té .widget--stats .widget__icona::before" },
        { test: (css) => /\.widget--vendes\s+\.widget__icona::before/.test(css), msg: "❌ El CSS ha de tenir .widget--vendes .widget__icona::before", success: "✅ El CSS té .widget--vendes .widget__icona::before" },
        { test: (css) => /\.widget--usuaris\s+\.widget__icona::before/.test(css), msg: "❌ El CSS ha de tenir .widget--usuaris .widget__icona::before", success: "✅ El CSS té .widget--usuaris .widget__icona::before" },
        { test: (css) => /\.widget--alertes\s+\.widget__icona::before/.test(css), msg: "❌ El CSS ha de tenir .widget--alertes .widget__icona::before", success: "✅ El CSS té .widget--alertes .widget__icona::before" }
      ]
    },
    starterCode: `// Defineix les variables


// Defineix el mapa $widgets


// Crea .dashboard


// Crea .widget amb nesting i modificadors
.widget {
  
}
`
  },

  // ============================================
  // REPT 20: MOLT DIFÍCIL (Integració total)
  // ============================================

  // REPT 20 (MOLT DIFÍCIL): Component mestre
  {
    id: 20,
    title: "⚠️ Repte 20: La Forja del Mestre Artesà",
    badge: "Nivell 20 - MOLT DIFÍCIL",
    description: "Construeix un component complet 'targeta-eina' integrant TOTS els conceptes dels capítols 2, 3 i 4: 1) Variables per colors (noms). 2) Operacions matemàtiques per mides. 3) Nesting amb & per a estructura. 4) BEM amb &__ i &-- per a elements i modificadors. 5) @use simulat per importar configuració. 6) Mapes niats per a variants. 7) map-get niuat per accedir a valors. 8) Llistes amb list.nth per a configuracions. 9) &:hover i &:active per a interactivitat. 10) &::before per a icones. 11) &:focus-within per a accessibilitat. 12) &:first-child i &:last-child per a estils condicionals. 13) Interpolació #{ } per a noms dinàmics. 14) rgba() amb variables per a transparències. 15) lighten() i darken() per a variants de color. 16) calc() amb interpolació per a mides fluides. La targeta ha de tenir: .targeta-eina amb &__icona (amb &::before per a la icona), &__nom, &__descripcio, &__preu, &__boto (amb &:hover, &:active, &:focus-within), &__etiquetes (amb &__etiqueta, &:first-child, &:last-child). Modificadors: &--daurat (gold), &--ferro (gray), &--diamant (cyan). Cada modificador ha de canviar colors, icones i preus utilitzant map-get niuat.",
    hint: "Integra TOTS els conceptes: variables amb noms de colors, operacions matemàtiques, nesting profund, BEM estricte, @use simulat, mapes niats amb map-get, llistes amb list.nth, selectors & avançats (hover, active, focus-within, first-child, last-child, ::before), interpolació #{ }, rgba() amb variables, lighten()/darken(), calc() amb interpolació. Dins de cada modificador (&--daurat...) canvia la icona amb .targeta-eina__icona::before { content: ... }. Fes-ho modular i complet!",
    points: 100,
    html: `<!DOCTYPE html><html><head><style>BODY_STYLES</style></head><body><div class="targeta-eina targeta-eina--daurat"><div class="targeta-eina__icona"></div><h3 class="targeta-eina__nom">Espasa Daurada</h3><p class="targeta-eina__descripcio">Una espasa brillant de or amb propietats màgiques</p><p class="targeta-eina__preu">150€</p><div class="targeta-eina__etiquetes"><span class="targeta-eina__etiqueta">Màgic</span><span class="targeta-eina__etiqueta">Rar</span><span class="targeta-eina__etiqueta">Nivell 10</span></div><button class="targeta-eina__boto">Equipar</button></div><div class="targeta-eina targeta-eina--ferro"><div class="targeta-eina__icona"></div><h3 class="targeta-eina__nom">Pic de Ferro</h3><p class="targeta-eina__descripcio">Un pic resistent de ferro per a mineria intensiva</p><p class="targeta-eina__preu">50€</p><div class="targeta-eina__etiquetes"><span class="targeta-eina__etiqueta">Resistent</span><span class="targeta-eina__etiqueta">Comú</span></div><button class="targeta-eina__boto">Equipar</button></div><div class="targeta-eina targeta-eina--diamant"><div class="targeta-eina__icona"></div><h3 class="targeta-eina__nom">Diamant</h3><p class="targeta-eina__descripcio">Un diamant brillant i rar amb propietats úniques</p><p class="targeta-eina__preu">500€</p><div class="targeta-eina__etiquetes"><span class="targeta-eina__etiqueta">Llegendari</span><span class="targeta-eina__etiqueta">Únic</span><span class="targeta-eina__etiqueta">Nivell 50</span></div><button class="targeta-eina__boto">Recollir</button></div></body></html>`,
    validation: {
      scss: [
        // Variables i colors
        { test: (code) => /\$[a-zA-Z-]+\s*:/.test(code), msg: "❌ Has d'utilitzar variables", success: "✅ Has utilitzat variables" },
        { test: (code) => /(gold|goldenrod|yellow)/i.test(code), msg: "❌ Has d'utilitzar gold per al daurat", success: "✅ Has utilitzat gold" },
        { test: (code) => /(gray|grey|silver)/i.test(code), msg: "❌ Has d'utilitzar gray per al ferro", success: "✅ Has utilitzat gray" },
        { test: (code) => /(cyan|aqua|turquoise)/i.test(code), msg: "❌ Has d'utilitzar cyan per al diamant", success: "✅ Has utilitzat cyan" },
        
        // Operacions matemàtiques
        { test: (code) => /math\.div|\*|\+|-/.test(code), msg: "❌ Has d'utilitzar operacions matemàtiques", success: "✅ Has utilitzat operacions matemàtiques" },
        
        // Nesting i BEM
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{/.test(code), msg: "❌ Has de definir .targeta-eina", success: "✅ Has definit .targeta-eina" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__icona/.test(code), msg: "❌ Has d'utilitzar &__icona dins de .targeta-eina", success: "✅ Has utilitzat &__icona" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__nom/.test(code), msg: "❌ Has d'utilitzar &__nom dins de .targeta-eina", success: "✅ Has utilitzat &__nom" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__descripcio/.test(code), msg: "❌ Has d'utilitzar &__descripcio dins de .targeta-eina", success: "✅ Has utilitzat &__descripcio" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__preu/.test(code), msg: "❌ Has d'utilitzar &__preu dins de .targeta-eina", success: "✅ Has utilitzat &__preu" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__boto/.test(code), msg: "❌ Has d'utilitzar &__boto dins de .targeta-eina", success: "✅ Has utilitzat &__boto" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__etiquetes/.test(code), msg: "❌ Has d'utilitzar &__etiquetes dins de .targeta-eina", success: "✅ Has utilitzat &__etiquetes" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&__etiquetes[\s\S]*&__etiqueta/.test(code), msg: "❌ Has de crear &__etiqueta després de &__etiquetes (al mateix nivell, perquè generi .targeta-eina__etiqueta)", success: "✅ Has creat &__etiqueta" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&--daurat/.test(code), msg: "❌ Has d'utilitzar &--daurat dins de .targeta-eina", success: "✅ Has utilitzat &--daurat" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&--ferro/.test(code), msg: "❌ Has d'utilitzar &--ferro dins de .targeta-eina", success: "✅ Has utilitzat &--ferro" },
        { test: (code) => /(\.targeta-eina|\.#\{\s*\$[\w-]+\s*\})\s*\{[\s\S]*&--diamant/.test(code), msg: "❌ Has d'utilitzar &--diamant dins de .targeta-eina", success: "✅ Has utilitzat &--diamant" },
        
        // @use simulat
        { test: (code) => /@use/.test(code), msg: "❌ Has d'utilitzar @use", success: "✅ Has utilitzat @use" },
        
        // Mapes niats
        { test: (code) => /\$[a-zA-Z-]+\s*:\s*\(/.test(code), msg: "❌ Has de definir mapes", success: "✅ Has definit mapes" },
        { test: (code) => /map-get\s*\(\s*map-get/.test(code), msg: "❌ Has d'utilitzar map-get niuat", success: "✅ Has utilitzat map-get niuat" },
        
        // Llistes
        { test: (code) => /list\.nth/.test(code), msg: "❌ Has d'utilitzar list.nth", success: "✅ Has utilitzat list.nth" },
        
        // Selectors & avançats
        { test: (code) => /&__boto[\s\S]*&:hover/.test(code), msg: "❌ &__boto ha de tenir &:hover", success: "✅ &__boto té &:hover" },
        { test: (code) => /&__boto[\s\S]*&:active/.test(code), msg: "❌ &__boto ha de tenir &:active", success: "✅ &__boto té &:active" },
        { test: (code) => /&__boto[\s\S]*&:focus-within/.test(code), msg: "❌ &__boto ha de tenir &:focus-within", success: "✅ &__boto té &:focus-within" },
        { test: (code) => /&__icona[\s\S]*&::before/.test(code), msg: "❌ &__icona ha de tenir &::before", success: "✅ &__icona té &::before" },
        { test: (code) => /&__etiquetes[\s\S]*&__etiqueta[\s\S]*&:first-child/.test(code), msg: "❌ &__etiqueta ha de tenir &:first-child", success: "✅ &__etiqueta té &:first-child" },
        { test: (code) => /&__etiquetes[\s\S]*&__etiqueta[\s\S]*&:last-child/.test(code), msg: "❌ &__etiqueta ha de tenir &:last-child", success: "✅ &__etiqueta té &:last-child" },
        
        // Interpolació
        { test: (code) => /#\{/.test(code), msg: "❌ Has d'utilitzar interpolació #{ }", success: "✅ Has utilitzat interpolació" },
        
        // rgba() amb variables
        { test: (code) => /rgba\s*\(\s*\$[a-zA-Z-]+/.test(code), msg: "❌ Has d'utilitzar rgba() amb variables", success: "✅ Has utilitzat rgba() amb variables" },
        
        // lighten() i darken()
        { test: (code) => /lighten\s*\(\s*\$[a-zA-Z-]+/.test(code), msg: "❌ Has d'utilitzar lighten() amb variables", success: "✅ Has utilitzat lighten()" },
        { test: (code) => /darken\s*\(\s*\$[a-zA-Z-]+/.test(code), msg: "❌ Has d'utilitzar darken() amb variables", success: "✅ Has utilitzat darken()" },
        
        // calc() amb interpolació
        { test: (code) => /calc\s*\([^)]*#\{/.test(code), msg: "❌ Has d'utilitzar calc() amb interpolació", success: "✅ Has utilitzat calc() amb interpolació" }
      ],
      css: [
        { test: (css) => /\.targeta-eina__icona/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__icona", success: "✅ El CSS té .targeta-eina__icona" },
        { test: (css) => /\.targeta-eina__nom/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__nom", success: "✅ El CSS té .targeta-eina__nom" },
        { test: (css) => /\.targeta-eina__descripcio/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__descripcio", success: "✅ El CSS té .targeta-eina__descripcio" },
        { test: (css) => /\.targeta-eina__preu/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__preu", success: "✅ El CSS té .targeta-eina__preu" },
        { test: (css) => /\.targeta-eina__boto/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__boto", success: "✅ El CSS té .targeta-eina__boto" },
        { test: (css) => /\.targeta-eina__etiquetes/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__etiquetes", success: "✅ El CSS té .targeta-eina__etiquetes" },
        { test: (css) => /\.targeta-eina__etiqueta/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__etiqueta", success: "✅ El CSS té .targeta-eina__etiqueta" },
        { test: (css) => /\.targeta-eina--daurat/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--daurat", success: "✅ El CSS té .targeta-eina--daurat" },
        { test: (css) => /\.targeta-eina--ferro/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--ferro", success: "✅ El CSS té .targeta-eina--ferro" },
        { test: (css) => /\.targeta-eina--diamant/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--diamant", success: "✅ El CSS té .targeta-eina--diamant" },
        { test: (css) => /\.targeta-eina__boto:hover/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__boto:hover", success: "✅ El CSS té .targeta-eina__boto:hover" },
        { test: (css) => /\.targeta-eina__boto:active/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__boto:active", success: "✅ El CSS té .targeta-eina__boto:active" },
        { test: (css) => /\.targeta-eina__boto:focus-within/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__boto:focus-within", success: "✅ El CSS té .targeta-eina__boto:focus-within" },
        { test: (css) => /\.targeta-eina__icona::before/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__icona::before", success: "✅ El CSS té .targeta-eina__icona::before" },
        { test: (css) => /\.targeta-eina__etiqueta:first-child/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__etiqueta:first-child", success: "✅ El CSS té .targeta-eina__etiqueta:first-child" },
        { test: (css) => /\.targeta-eina__etiqueta:last-child/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina__etiqueta:last-child", success: "✅ El CSS té .targeta-eina__etiqueta:last-child" },
        { test: (css) => /\.targeta-eina--daurat\s+\.targeta-eina__icona::before/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--daurat .targeta-eina__icona::before", success: "✅ El CSS té .targeta-eina--daurat .targeta-eina__icona::before" },
        { test: (css) => /\.targeta-eina--ferro\s+\.targeta-eina__icona::before/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--ferro .targeta-eina__icona::before", success: "✅ El CSS té .targeta-eina--ferro .targeta-eina__icona::before" },
        { test: (css) => /\.targeta-eina--diamant\s+\.targeta-eina__icona::before/.test(css), msg: "❌ El CSS ha de tenir .targeta-eina--diamant .targeta-eina__icona::before", success: "✅ El CSS té .targeta-eina--diamant .targeta-eina__icona::before" }
      ]
    },
    starterCode: `@use 'sass:math';
@use 'sass:list';

// ============================================
// REPT FINAL: LA FORJA DEL MESTRE ARTESÀ
// Integra TOTS els conceptes dels capítols 2, 3 i 4!
// ============================================

// 1. Variables de colors (utilitza noms!)


// 2. Operacions matemàtiques per a mides


// 3. Mapes niats per a variants


// 4. Llistes per a configuracions


// 5. Component principal .targeta-eina amb nesting i BEM
.targeta-eina {
  
}
`
  }
];