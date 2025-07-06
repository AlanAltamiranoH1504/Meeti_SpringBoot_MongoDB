import {useState} from "react";
import {TrixEditor} from "react-trix";
import "trix/dist/trix.css";
import "trix";
import {useForm} from "react-hook-form";
import type {Categoria, SaveGrupo} from "../../types";
import {QueryClient, useMutation, useQueryClient} from "@tanstack/react-query";
import {toast} from "react-toastify";
import {saveGrupoPeticion} from "../../api/ApiSpringBoot";
import {type NavigateFunction, useNavigate} from "react-router-dom";

const FormCrearGrupo = () => {
    const queryClient: QueryClient = useQueryClient();
    const navigate: NavigateFunction = useNavigate();
    const {register, handleSubmit, formState: {errors}} = useForm<SaveGrupo>();
    const [content, setContent] = useState("");
    const cacheCategorias: Categoria[] = queryClient.getQueryData<Categoria[]>(["findAllCategorias"]) || [];

    function saveGrupo(data: SaveGrupo) {
        if (!content || content.trim() === "" || content === "<div><br></div>") {
            toast.error("La descripción es obligatoria");
            return;
        }
        const dataConCategoria = {
            ...data,
            descripcion: content
        };

        mutationSaveGrupo.mutate(dataConCategoria);
    }

    const mutationSaveGrupo = useMutation({
        mutationKey: ["saveGrupo"],
        mutationFn: saveGrupoPeticion,
        onSuccess: (data) => {
            toast.success(data.msg)
            navigate("/administracion");
        },
        onError: (error) => {
            console.log(error);
            toast.error("Error en creacion de grupo");
        }
    })

    return (
        <div className="max-w-3xl mx-auto">
            <div className="bg-white p-5 py-10 border rounded-lg shadow-md">
                <h2 className="text-center font-fjalla text-3xl border-b-2 border-red-600 pb-3">Crear Nuevo Grupo</h2>
                <form
                    encType="multipart/form-data"
                    onSubmit={handleSubmit(saveGrupo)}
                >
                    <div className="my-4">
                        <label htmlFor="nombre"
                               className="font-fjalla font-semibold text-gray-600 text-xl block mb-2">Nombre:</label>
                        <input type="text" className="border p-2 w-full rounded-lg border-b-gray-300 font-fjalla"
                               placeholder="Nombre del grupo"
                               {...register("nombre", {
                                   required: "El nombre es obligatorio",
                               })}
                        />
                        <div className="bg-red-200 text-center font-semibold text-red-600 rounded-md mt-1">
                            {errors.nombre && String(errors.nombre.message)}
                        </div>
                    </div>
                    <div className="mb-4">
                        <label htmlFor="descripcion"
                               className="font-fjalla font-semibold text-gray-600 text-xl block mb-2">Descripción:</label>
                        <TrixEditor className="min-h-96" value={content} onChange={(html) => setContent(html)}/>
                        <div className="hidden" dangerouslySetInnerHTML={{__html: content}}></div>
                    </div>
                    <div className="mb-4">
                        <label htmlFor="categoria"
                               className="font-fjalla font-semibold text-gray-600 text-xl block mb-2">Categoria:</label>
                        <select className="border p-2 w-full rounded-lg border-b-gray-300 font-fjalla"
                                {...register("categoriaId", {
                                    required: "La categoria es obligatoria"
                                })}
                        >
                            <option value="">--- Selecciona una categoria ----</option>
                            {cacheCategorias.map((categoria: Categoria) => {
                                return (
                                    <option key={categoria.id} value={categoria.id}>{categoria.nombre}</option>
                                )
                            })}
                        </select>
                        <div className="bg-red-200 text-center font-semibold text-red-600 rounded-md mt-1">
                            {errors.categoriaId && String(errors.categoriaId.message)}
                        </div>
                    </div>

                    <div className="mb-4">
                        <label htmlFor="web" className="font-fjalla font-semibold text-gray-600 text-xl block mb-2">Sitio
                            Web:</label>
                        <input type="text" className="border p-2 w-full rounded-lg border-b-gray-300 font-fjalla"
                               placeholder="Sitio web (opcional)"
                               {...register("sitioWeb", {
                                   pattern: {
                                       value: /^(https?:\/\/)?([\w\-]+\.)+[\w\-]+(\/[\w\-._~:/?#[\]@!$&'()*+,;=]*)?$/,
                                       message: "Formato de URL no valido"
                                   }
                               })}
                        />
                        <div className="bg-red-200 text-center font-semibold text-red-600 rounded-md mt-1">
                            {errors.sitioWeb && String(errors.sitioWeb.message)}
                        </div>
                    </div>
                    <div className="mt-4">
                        <input type="submit" value="Crear Grupo"
                               className="border p-2 w-full rounded-lg font-semibold text-lg border-red-600 bg-red-600 text-white cursor-pointer hover:bg-white hover:text-red-600"/>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default FormCrearGrupo;