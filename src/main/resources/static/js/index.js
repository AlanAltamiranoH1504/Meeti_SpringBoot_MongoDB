export async function llenadoCategorias() {
    try {
        const response = await axios.get("/categorias");
        if (response.status === 200) {
            return response.data;
        }
    } catch (e) {
        console.log(e.response.data)
    }
}

export function renderCategorias(categorias) {
    const optionCategorias = document.querySelector("#categoria");
    categorias.forEach((categoria) => {
        const opCategoria = document.createElement("option");
        opCategoria.setAttribute("value", categoria.id);
        opCategoria.setAttribute("id", categoria.id);
        opCategoria.textContent = categoria.nombre;

        optionCategorias.appendChild(opCategoria);
    });
}


// Funciones generales
export async function peticionesPOST(request, endPoint, csrfToken) {
    try {
        const response = await axios.post(endPoint, request, {
            headers: {
                "X-CSRF-TOKEN": csrfToken
            }
        });
        if (response.status === 201){
            return response;
        }
    }catch (e) {
        return e.response;
    }
}

export async function peticionesGETNotID(endPoint) {
    try {
        const response = await axios.get(endPoint);
        if (response.status === 200) {
            return response.data;
        }
    }catch (e) {
        return e.response;
    }
}

export async function peticionesPUTWithID(request, endPoint, csrfToken) {
    try {
        const response = await axios.put(endPoint, request, {
            headers: {
                "X-CSRF-TOKEN": csrfToken
            }
        });
        if (response.status === 200) {
            return response.status;
        }
    }catch (e) {
        return e.response;
    }
}

export async function peticionesDELETEWithID(endPoint, csrfToken) {
    try {
        const response = await axios.delete(endPoint, {
            headers: {
                "X-CSRF-TOKEN": csrfToken
            }
        });
        if (response.status === 200) {
            return response;
        }
    }catch (e) {
        return e.response;
    }
}

export function mostrarAlertas(informacion, lugar) {
    const divSeccion = document.querySelector(`#${lugar}`);
    informacion.forEach((error) => {
        const parrafoError = document.createElement("p");
        parrafoError.textContent = error.defaultMessage;
        parrafoError.classList.add("border", "p-1", "text-center", "font-semibold", "rounded-lg", "my-2")
        divSeccion.appendChild(parrafoError);

        setTimeout(() => {
            divSeccion.textContent = "";
        }, 5000)
    });
}
