document.addEventListener("DOMContentLoaded", () => {
    llenadoCategorias();
    async function llenadoCategorias() {
        try {
            const response = await axios.get("/categorias");
            if (response.status === 200) {
                renderCategorias(response.data);
            }
        } catch (e) {
            console.log(e.response.data)
        }
    }

    function renderCategorias(categorias) {
        const optionCategorias = document.querySelector("#categoria");
        categorias.forEach((categoria) => {
            const opCategoria = document.createElement("option");
            opCategoria.setAttribute("value", categoria.id);
            opCategoria.setAttribute("id", categoria.id);
            opCategoria.textContent = categoria.nombre;

            optionCategorias.appendChild(opCategoria);
        })
    }

    //Selectores
    const formNuevoGrupo = document.querySelector("#formNuevoGrupo");
    formNuevoGrupo.addEventListener("submit", saveGrupo);

    //Funciones
    function saveGrupo(e){
        e.preventDefault();
        const inputNombre = document.querySelector("#nombre").value;
        const inputDescripcion = document.querySelector("#x").value;
        const inputCategoria = document.querySelector("#categoria").value;
        // const inputImagen = document.querySelector("#imagen").file[0];
        const inputSitioWeb = document.querySelector("#sitio_web").value;

        const requestBody = {
            nombre: inputNombre,
            descripcion: inputDescripcion,
            categoria: inputCategoria,
            // imagen: inputImagen,
            sitioWeb: inputSitioWeb
        };
        peticion(requestBody);
    }

    async function peticion(request) {

        const token = document.querySelector("#_csrf").value;
        try {
            const response = await axios.post("/grupos", request, {
                headers: {
                    "X-CSRF-TOKEN": token
                }
            });
            if (response.status === 201){
                Swal.fire({
                    title: "Grupo Guardado Correctamente",
                    text: "Se guardo correctamente el grupo.",
                    icon: "success",
                    timer: 3000
                });
            }
        }catch (e) {
            Swal.fire({
                title: "Error",
                text: "Ocurrio un error al guardar el grupo.",
                icon: "error",
                timer: 3000
            });
            console.log(e.response.data);
        }
    }
})