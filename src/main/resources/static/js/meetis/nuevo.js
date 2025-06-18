import {peticionesGETWithID} from "../index.js";

document.addEventListener("DOMContentLoaded", () => {
    busquedaGrupos();

    async function busquedaGrupos() {
        try {
            const response = await axios.get("/administracion/list-grupos");
            if (response.status === 200) {
                renderGrupos(response.data);
            }
        } catch (e) {
            console.log(e.message);
        }
    }

    function renderGrupos(grupos) {
        const gruposSelect = document.querySelector("#grupos");
        grupos.forEach((grupo) => {
            const optionGrupo = document.createElement("option");
            optionGrupo.textContent = grupo.nombre;
            optionGrupo.setAttribute("value", grupo.id);
            gruposSelect.appendChild(optionGrupo);
        })
    }
})