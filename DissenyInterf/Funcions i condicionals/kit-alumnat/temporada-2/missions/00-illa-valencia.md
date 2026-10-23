# Etapa 0. Illa València: preparació

**Temps:** 5 minuts. No dona XP. [Tornar al mapa](../README.md)

---

## Història

> *"Benvinguts a l'Illa València. Sóc la Professora Ivy i estudio com els Pokémon d'aquestes illes hereten trets dels seus avantpassats. Aquesta temporada farem el mateix amb el CSS: en lloc de copiar estils, els heretarem. Primer, però, prepareu el quadern de camp."*

---

## Passos

La segona temporada es fa **al mateix projecte** de la primera, però amb un fitxer d'entrada nou.

1. Copieu `kit-alumnat/temporada-2/progres-temporada-2.md` a l'arrel del projecte (al costat del `package.json`) i escriviu-hi el vostre nom.
2. Copieu `kit-alumnat/temporada-2/plantilles/arxipelag.html` dins de `src/`.
3. Creeu el fitxer `src/scss/arxipelag.scss` (sense guió baix: és un fitxer d'entrada) i la carpeta `src/scss/arxipelag/`.
4. Afegiu aquests dos scripts al `package.json`, dins de `"scripts"` (recordeu les comes entre línies):
   ```json
   "arxipelag:watch": "sass --watch src/scss/arxipelag.scss:dist/css/arxipelag.css --style=expanded --source-map",
   "arxipelag:build": "sass src/scss/arxipelag.scss:dist/css/arxipelag.css --style=compressed --no-source-map"
   ```
5. Engegueu el compilador i deixeu-lo obert tota la sessió:
   ```powershell
   npm run arxipelag:watch
   ```
   Com que `arxipelag.scss` encara és buit, el CSS també ho serà. És normal.
6. Obriu `src/arxipelag.html` al navegador i `dist/css/arxipelag.css` en una pestanya de VS Code. Dividiu l'editor (`Ctrl + \`) per tenir el `.scss` i el `.css` un al costat de l'altre.

> Recordatori: obriu les fitxes de missió amb `Ctrl + Shift + V` perquè les pistes quedin plegades.

---

## Comprovació

- [ ] `progres-temporada-2.md` copiat i amb el vostre nom.
- [ ] `src/arxipelag.html` copiat.
- [ ] `src/scss/arxipelag.scss` i la carpeta `src/scss/arxipelag/` creats.
- [ ] `npm run arxipelag:watch` funcionant sense errors.

> *"Perfecte. El vaixell cap a l'Illa Mikan surt d'aquí a cinc minuts. La líder del gimnàs, la Cissy, té un problema amb els seus botons."*

Següent: [01-illa-mikan.md](01-illa-mikan.md)
