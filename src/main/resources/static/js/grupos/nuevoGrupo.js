import {
    llenadoCategorias,
    renderCategorias,
    peticionesPOST,
    mostrarAlertas
} from "../index.js";


document.addEventListener("DOMContentLoaded", async () => {
    //Llenado categorias
    const categorias = await llenadoCategorias();
    renderCategorias(categorias);

    //Selectores
    const formNuevoGrupo = document.querySelector("#formNuevoGrupo");
    formNuevoGrupo.addEventListener("submit", saveGrupo);

    //Funciones
    async function saveGrupo(e) {
        e.preventDefault();
        const inputNombre = document.querySelector("#nombre").value;
        const inputDescripcion = document.querySelector("#x").value;
        const inputCategoria = document.querySelector("#categoria").value;
        const inputSitioWeb = document.querySelector("#sitio_web").value;
        const token = document.querySelector("#_csrf").value;

        const requestBody = {
            nombre: inputNombre,
            descripcion: inputDescripcion,
            categoria: inputCategoria,
            sitioWeb: inputSitioWeb
        };

        const respuestaPeticion = await peticionesPOST(requestBody, "/grupos", token);
        if (respuestaPeticion.status === 201) {
            Swal.fire({
                title: respuestaPeticion.data.msg,
                text: "Operación exitosa",
                icon: "success",
                textConfirmButton: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "/";
                }
            });
        } else {
            Swal.fire({
                title: "Error en el registro del grupo.",
                text: "Operación erronea.",
                icon: "error",
                timer: 3000
            });
            mostrarAlertas(respuestaPeticion.data.errors, "alertasErrores");
        }
    }
});