import {llenadoCategorias, renderCategorias, peticionesGETNotID, peticionesPUTWithID} from "../index.js";

document.addEventListener("DOMContentLoaded", async () => {
    const path = window.location.pathname;
    const pathParths = path.split("/");
    const id = pathParths[2];

    //Llenado de categorias
    const categorias = await llenadoCategorias();
    renderCategorias(categorias);
    //Busqueda de grupo
    const grupoEditar = await peticionesGETNotID(`/grupos/${id}`);
    llenarFormulario(grupoEditar);

    //Selectores
    const formEditarGrupo = document.querySelector("#formEditarGrupo");
    formEditarGrupo.addEventListener("submit", actualizarGrupo);

    //Funciones
    function llenarFormulario(informacionGrupo) {
        const sitioWeb = informacionGrupo.sitioWeb ? informacionGrupo.sitioWeb : null;
        document.querySelector("#nombre").value = informacionGrupo.nombre;
        document.querySelector("trix-editor[input='x']").innerHTML = informacionGrupo.descripcion;
        document.querySelector("#categoria").value = informacionGrupo.categoria;
        document.querySelector("#sitio_web").value = sitioWeb;
    }

    async function actualizarGrupo(e) {
        e.preventDefault()
        const nombre = document.querySelector("#nombre").value;
        const descripcion = document.querySelector("#x").value;
        const categoria = document.querySelector("#categoria").value;
        const sitioWeb = document.querySelector("#sitio_web").value;
        const token = document.querySelector("#_csrf").value;

        const requestBody = {
            nombre,
            descripcion,
            categoria,
            sitioWeb
        };
        const response = await peticionesPUTWithID(requestBody, `/grupos/${id}`, token);
        if (response === 200) {
            Swal.fire({
                title: "Grupo actualizado correctamente",
                text: "Operación exitosa",
                icon: "success",
                timer: 3000
            });
        } else{
            console.log("Error en peticion")
            console.log(response)
        }
    }
});