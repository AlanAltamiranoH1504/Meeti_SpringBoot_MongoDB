// Usando una variable en módulo, fuera del listener
let map;

document.addEventListener('DOMContentLoaded', () => {
    if (map) {
        map.remove();  // elimina mapa anterior si existe
    }

    map = L.map('mapa').setView([51.505, -0.09], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    L.marker([51.5, -0.09]).addTo(map)
        .bindPopup('A pretty CSS popup.<br> Easily customizable.')
        .openPopup();
});
