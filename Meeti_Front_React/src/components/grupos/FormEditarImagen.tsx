import {useNavigate, useParams} from "react-router-dom";
import {useMutation, useQuery} from "@tanstack/react-query";
import {findGrupoByIdPeticion, updateImagenGrupoPeticion} from "../../api/ApiSpringBoot";
import Cargando from "../Cargando";
import {useForm} from "react-hook-form";
import type {UpdateImagenGrupo} from "../../types";
import {toast} from "react-toastify";

const FormEditarImagen = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const {register, handleSubmit, formState: {errors}} = useForm<UpdateImagenGrupo>();

        function actualizarImagen(data: UpdateImagenGrupo) {
            if (data.imagen[0]){
                const formData = new FormData();
                formData.append("imagen", data.imagen[0]);
                // @ts-ignore
                formData.append("id", id);

                mutationUpdateImagenGrupo.mutate(formData);
            }else{
                console.log("NO EXISTE")
            }
        }

    const mutationUpdateImagenGrupo = useMutation({
        mutationKey: ["updateImagenGrupoById"],
        mutationFn: updateImagenGrupoPeticion,
        onSuccess: () => {
            toast.success("Imagen actualizada correctamente");
            navigate("/administracion");
        },
        onError: () => {
            toast.error("Error en actualizacion de imagen");
        }
    })

    const {data, isLoading} = useQuery({
        queryKey: ["findGrupoByIdImagen"],
        queryFn: () => {
            // @ts-ignore
            return findGrupoByIdPeticion(id)
        },
        retry: 1,
        refetchOnWindowFocus: false
    });
    if (isLoading) {
        return <Cargando/>
    }

    if (data) return (
        <div className="max-w-3xl mx-auto">
            <div className="bg-white p-5 py-10 border rounded-lg shadow-md">
                <h2 className="text-center font-fjalla font-semibold text-3xl border-b-2 border-red-600 pb-3">Editar
                    Imagen de Grupo</h2>
                <div className="border border-2 mt-10 flex justify-center items-center mx-auto p-10 shadow-md">
                    <img alt="Imagen del grupo" src={data.imagen ? data.imagen : "/imagenes/Grupo_Default.png"}
                         className="max-h-72"/>
                </div>
                <h3 className="text-center font-fjalla text-lg mt-5">Imagen Actual</h3>
                <form
                    onSubmit={handleSubmit(actualizarImagen)}
                    encType="multipart/form-data"
                >
                    <div className="my-5">
                        <label htmlFor="imagen" className="text-lg font-fjalla font-semibold text-gray-600 block mb-2">Imagen
                            Nueva:</label>
                        <input type="file" accept="image/*" className="border p-2 w-full rounded-lg border-gray-300"
                               {...register("imagen", {
                                   required: "La imagen es obligatoria"
                               })}
                        />
                        <div className="mt-1 border bg-red-200 text-red-600 text-center font-semibold rounded-lg">
                            {errors.imagen && String(errors.imagen.message)}
                        </div>
                    </div>
                    <div className="">
                        <input type="submit" value="Actualizar Imagen"
                               className="border p-2 w-full rounded-lg border-red-600 bg-red-600 text-white text-center font-semibold font-fjalla cursor-pointer hover:bg-white hover:text-red-600"/>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default FormEditarImagen;