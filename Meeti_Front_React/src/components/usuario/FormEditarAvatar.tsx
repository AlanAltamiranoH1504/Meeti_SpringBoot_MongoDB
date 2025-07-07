import {useMutation, useQueryClient} from "@tanstack/react-query";
import {useForm} from "react-hook-form";
import type {UpdateImagenUsuario, UsuarioLogeado} from "../../types";
import {updateImagenPerfil} from "../../api/ApiSpringBoot";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

const FormEditarAvatar = () => {
    const queryClient = useQueryClient();
    const navigate = useNavigate();
    const cacheUsuario: UsuarioLogeado = queryClient.getQueryData<UsuarioLogeado>(["usuarioEnSesion"]) as UsuarioLogeado;
    const {register, handleSubmit, formState:{errors}} = useForm<UpdateImagenUsuario>();

    function actualizarImagen(data: UpdateImagenUsuario)  {
        const formData = new FormData();
        formData.append("imagen", data.imagen[0]);
        mutationUpdateImagenPerfil.mutate(formData);
    }

    const mutationUpdateImagenPerfil = useMutation({
        mutationKey: ["updateImagenPerfil"],
        mutationFn: updateImagenPerfil,
        onSuccess: () => {
            toast.success("Imagen actualizada correctamente");
            queryClient.invalidateQueries({
                queryKey: ["usuarioEnSesion"]
            });
            navigate("/administracion");
        },
        onError: () => {
            toast.error("Error en actualizacion de imagen");
        }
    })

    return (
        <div className="max-w-3xl mx-auto">
            <div className="bg-white p-5 py-10 border rounded-lg shadow-md">
                <h2 className="text-center font-fjalla font-semibold text-3xl border-b-2 border-red-600 pb-3">Editar
                    Imagen de Grupo</h2>
                <div className="border border-2 mt-10 flex justify-center items-center mx-auto p-10 shadow-md">
                    <img alt="Imagen del grupo" src={cacheUsuario.imagen ? cacheUsuario.imagen : "/imagenes/Perfil_Default.png"}
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
export default FormEditarAvatar;