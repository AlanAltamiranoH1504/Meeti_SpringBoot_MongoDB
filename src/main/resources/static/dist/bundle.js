/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	var __webpack_modules__ = ({

/***/ "./src/main/resources/static/js/app.js":
/*!*********************************************!*\
  !*** ./src/main/resources/static/js/app.js ***!
  \*********************************************/
/***/ (() => {

eval("// Usando una variable en módulo, fuera del listener\nvar map;\ndocument.addEventListener('DOMContentLoaded', function () {\n  if (map) {\n    map.remove(); // elimina mapa anterior si existe\n  }\n  map = L.map('mapa').setView([51.505, -0.09], 13);\n  L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n    attribution: '&copy; OpenStreetMap contributors'\n  }).addTo(map);\n  L.marker([51.5, -0.09]).addTo(map).bindPopup('A pretty CSS popup.<br> Easily customizable.').openPopup();\n});\n\n//# sourceURL=webpack://meeti_springboot_mongodb/./src/main/resources/static/js/app.js?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	
/******/ 	// startup
/******/ 	// Load entry module and return exports
/******/ 	// This entry module can't be inlined because the eval devtool is used.
/******/ 	var __webpack_exports__ = {};
/******/ 	__webpack_modules__["./src/main/resources/static/js/app.js"]();
/******/ 	
/******/ })()
;