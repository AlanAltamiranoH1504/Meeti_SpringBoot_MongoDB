import {peticionesGETWithID, peticionesPUTImagens} from "../index.js";

document.addEventListener("DOMContentLoaded", async () => {
    const path = window.location.pathname;
    const pathParts = path.split("/");
    const idGrupo = pathParts[3];

    const response = await peticionesGETWithID(`/grupos/${idGrupo}`);
    document.querySelector("#tituloGrupo").textContent = response.nombre;
    const {imagen} = response;
    if (imagen == null) {
        document.querySelector("#imagenCargada").classList.remove("hidden")
        document.querySelector("#imagenExistente").classList.add("hidden");
    } else {
        const imagenGrupo = document.querySelector("#imagenGrupo");
        imagenGrupo.setAttribute("src", "/uploads/" + imagen);
    }

    //Selectores
    const formEditarImagem = document.querySelector("#formEditarImagem");

    //Eventos
    formEditarImagem.addEventListener("submit", guardarImagen);

    async function guardarImagen(e) {
        e.preventDefault();
        const token = document.querySelector("#_csrf").value;
        const inputImagen = document.querySelector("#imagen").files[0];
        const formData = new FormData();
        formData.append("imagen", inputImagen);

        try {
            const response = await peticionesPUTImagens(formData, `/grupos/save-imagen/${idGrupo}`, token);
            Swal.fire({
                title: "Imagen almacenada correctamente.",
                text: "Operación exitosa",
                icon: "success",
                textConfirmButton: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "/"
                }
            });
        } catch (e) {
            console.log("Error en peticion al backend");
        }
    }
});