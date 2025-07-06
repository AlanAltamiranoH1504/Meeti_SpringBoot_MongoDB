import {Fragment, useEffect, useState} from "react";
import {TrixEditor} from "react-trix";
import "trix/dist/trix.css";
import "trix";
import type {Categoria, UpdateGrupo} from "../../types";
import {useForm} from "react-hook-form";
import {useNavigate, useParams} from "react-router-dom";
import {useMutation, useQuery, useQueryClient} from "@tanstack/react-query";
import {findGrupoByIdPeticion, updateGrupoPeticion} from "../../api/ApiSpringBoot";
import Error404 from "../error/404";
import {toast} from "react-toastify";

const FormEditarGrupo = () => {
    const queryClient = useQueryClient();
    const navigate = useNavigate();
    const [content, setContent] = useState("");
    const cacheCategorias: Categoria[] = queryClient.getQueryData<Categoria[]>(["findAllCategorias"]) || [];
    const {id} = useParams();

    function actualizarGrupo(data: UpdateGrupo) {
        if (!content || content.trim() === "" || content === "<div><br></div>") {
            toast.error("La descripción es obligatoria");
            return;
        }
        const grupoConId: UpdateGrupo = {
            ...data,
            descripcion: content,
            id
        };
        console.log(grupoConId);

        mutationUpdateGrupo.mutate(grupoConId);
    }

    const mutationUpdateGrupo = useMutation({
        mutationKey: ["updateGrupoById"],
        mutationFn: updateGrupoPeticion,
        onSuccess: () => {
            toast.success("Grupo actualizado correctamente");
            queryClient.invalidateQueries({
                queryKey: ["findAllGruposByUsuarioId"]
            });
            navigate("/administracion");
        },
        onError: (error) => {
            console.log(error);
            toast.error("Error en actualizacion de grupo");
        }
    })

    const {data, isLoading, isError} = useQuery({
        queryKey: ["findGrupoById"],
        queryFn: () => {
            // @ts-ignore
            return  findGrupoByIdPeticion(id)
        },
        retry: 1,
        refetchOnWindowFocus: false
    });
    const {register, handleSubmit, formState: {errors}, reset} = useForm<UpdateGrupo>({
        defaultValues: {
            nombre: "",
            sitioWeb: "",
            categoriaId: undefined
        }
    });

    useEffect(() => {
        if (data) {
            reset({
                nombre: data.nombre,
                sitioWeb: data.sitioWeb,
                categoriaId: data.categoriaId
            });
            setContent(data.descripcion);
        }
    }, [data]);
    if (isLoading) {
        return <div className="flex flex-col">
            <h2 className="text-4xl font-fjalla text-center">Cargando...</h2>
            <div className="flex justify-center my-10">
                <img src="/imagenes/Spiner%20Carga%20Gif.gif" className="max-h-64"/>
            </div>
        </div>;
    }
    if (isError){
        return <Fragment>
            <Error404/>
        </Fragment>
    }

    if (data) return (
        <Fragment>
            <div className="max-w-3xl mx-auto">
                <div className="bg-white p-5 py-10 border rounded-lg shadow-md">
                    <h2 className="text-center font-fjalla text-3xl border-b-2 border-red-600 pb-3">Editar Grupo: </h2>
                    <form
                        encType="multipart/form-data"
                        onSubmit={handleSubmit(actualizarGrupo)}
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
        </Fragment>
    );
}
export default FormEditarGrupo;