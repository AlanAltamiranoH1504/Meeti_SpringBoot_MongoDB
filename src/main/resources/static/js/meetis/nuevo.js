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
            if (e.response.status === 404) {
                Swal.fire({
                    title: "No tienes grupos creados.",
                    text: "Crea un grupo para generar un nuevo Meeti",
                    icon: "warning",
                    textConfirmButton: "Crear Grupo"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/grupos-views/nuevo-grupo"
                    }
                })
            }
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