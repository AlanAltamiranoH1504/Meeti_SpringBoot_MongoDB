import { peticionesDELETEWithID} from "../index.js";

document.addEventListener("DOMContentLoaded", () => {
    listadoGrupos();

    async function listadoGrupos() {
        try {
            const response = await axios.get("/administracion/list-grupos");
            if (response.status === 200) {
                render(response.data, "grupos");
            }
        } catch (e) {
            document.querySelector("#noGrupos").classList.remove("hidden")
        }
    }

    function render(informacion, tipo) {
        if (tipo === "grupos") {
            const listGrupos = document.querySelector("#listGrupos");
            informacion.forEach((grupo) => {
                const liGrupo = document.createElement("li");
                liGrupo.innerHTML = `
                    <div class="informacion-admin">
                        <h3>${grupo.nombre}</h3>
                    </div>
                    <div class="flex flex-col md:flex-row justify-between items-center gap-4 md:space-x-5">
                        <a href="/grupo-edicion/${grupo.id}" class="flex w-56 justify-around border p-4 bg-orange-500 text-center rounded-lg text-white space-x-2 font-bold hover:bg-orange-700">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.487Zm0 0L19.5 7.125" />
                            </svg>
                            Editar
                        </a>
                        <a href="/grupo-imagen/${grupo.id}" class="flex w-56 justify-around border p-4 bg-indigo-400 text-center rounded-lg text-white space-x-2 font-bold hover:bg-indigo-500">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                <path stroke-linecap="round" stroke-linejoin="round" d="m2.25 15.75 5.159-5.159a2.25 2.25 0 0 1 3.182 0l5.159 5.159m-1.5-1.5 1.409-1.409a2.25 2.25 0 0 1 3.182 0l2.909 2.909m-18 3.75h16.5a1.5 1.5 0 0 0 1.5-1.5V6a1.5 1.5 0 0 0-1.5-1.5H3.75A1.5 1.5 0 0 0 2.25 6v12a1.5 1.5 0 0 0 1.5 1.5Zm10.5-11.25h.008v.008h-.008V8.25Zm.375 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Z" />
                            </svg>
                            Imagen
                        </a>
                        <button id="btnEliminar" data_id="${grupo.id}" type="button" class="flex w-56 justify-around border p-4 bg-red-600 text-center rounded-lg text-white space-x-2 font-bold hover:bg-red-700">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                            </svg>
                            Eliminar
                        </button>
                    </div>
                `;
                listGrupos.appendChild(liGrupo);
            });
            listGrupos.addEventListener("click", function (e) {
                peticionDelete(e);
            })
        }
    }

    async function peticionDelete(e) {
        e.preventDefault();
        if (e.target.id === "btnEliminar") {
            const btn = e.target;
            const id = btn.getAttribute("data_id");
            const token = document.querySelector("#_csrf").value;
            Swal.fire({
                title: "¿Estás seguro de realizar esta acción?",
                text: "Los cambios no se pueden reveertir.",
                icon: "warning",
                textConfirmButton: "Confirmar"
            }).then(async (result) => {
                if (result.isConfirmed) {
                    const response = await peticionesDELETEWithID(`/grupos/${id}`, token);
                    if (response.status === 200){
                        Swal.fire({
                            title: "Grupo eliminado.",
                            text: "Operacion exitosa.",
                            icon: "success",
                            timer: 3000
                        })
                    }
                }
            });
        }
    }
});