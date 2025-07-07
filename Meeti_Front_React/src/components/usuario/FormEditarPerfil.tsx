import {Fragment} from "react";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {useForm} from "react-hook-form";
import type {UpdatePerfilUsuario} from "../../types";
import {updateInformacionPerfilPeticion} from "../../api/ApiSpringBoot";
import {toast} from "react-toastify";
import {useNavigate} from "react-router-dom";

const FormEditarPerfil = () => {
    const queryClient = useQueryClient();
    const navigate = useNavigate();
    const cacheUsuario: UpdatePerfilUsuario = queryClient.getQueryData(["usuarioEnSesion"]) as UpdatePerfilUsuario;
    const {register, handleSubmit, formState: {errors}} = useForm({
        defaultValues: {
            nombre: cacheUsuario.nombre,
            apellidos: cacheUsuario.apellidos,
            username: cacheUsuario.username,
            email: cacheUsuario.email,
            password: cacheUsuario.password
        }
    });

    function actualizarPerfil(data: UpdatePerfilUsuario){
        console.log("Actualizando perfil de usuario");
        console.log(data);
        mutationUpdateInformacionPerfil.mutate(data);
    }

    const mutationUpdateInformacionPerfil = useMutation({
        mutationKey: ["updateInformacionPerfil"],
        mutationFn: updateInformacionPerfilPeticion,
        onSuccess: (data) => {
            toast.success(data.success);
            queryClient.invalidateQueries({
                queryKey: ["usuarioEnSesion"]
            });
            navigate("/administracion");
        },
        onError: (error) => {
            console.log(error);
            toast.error("Error en actualizacion de perfil.");
        }
    })

    return (
        <Fragment>
            <div className="max-w-3xl mx-auto">
                <div className="border py-10 px-5 bg-white shadow-md rounded-lg">
                    <h2 className="text-center font-semibold font-fjalla text-4xl border-b-4 pb-3 border-red-600">Editar
                        Perfil</h2>

                    <form
                        onSubmit={handleSubmit(actualizarPerfil)}
                    >
                        <div className="my-5">
                            <label htmlFor="nombre"
                                   className="font-fjalla text-lg text-gray-600 uppercase mb-1 block">Nombre:</label>
                            <input type="text" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                   placeholder="Nombre de usuario"
                                   {...register("nombre", {
                                       required: "El nombre es obligatorio",
                                   })}
                            />
                            <div className="bg-red-200 text-red-600 text-center font-fjalla mt-1 rounded-md">
                                {errors.nombre && String(errors.nombre.message)}
                            </div>
                        </div>

                        <div className="my-5">
                            <label htmlFor="apellidos"
                                   className="font-fjalla text-lg text-gray-600 uppercase mb-1 block">Apellidos:</label>
                            <input type="text" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                   placeholder="Apellidos"
                                   {...register("apellidos", {
                                       required: "Los apellidos son obligatorios",
                                   })}
                            />
                            <div className="bg-red-200 text-red-600 text-center  font-fjalla mt-1 rounded-md">
                                {errors.apellidos && String(errors.apellidos.message)}
                            </div>
                        </div>

                        <div className="my-5">
                            <label htmlFor="username"
                                   className="font-fjalla text-lg text-gray-600 uppercase mb-1 block">Username:</label>
                            <input type="text" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                   placeholder="Username"
                                   {...register("username", {
                                       required: "El username es obligatorio",
                                   })}
                            />
                            <div className="bg-red-200 text-red-600 text-center font-fjalla mt-1 rounded-md">
                                {errors.username && String(errors.username.message)}
                            </div>
                        </div>

                        <div className="my-5">
                            <label htmlFor="email"
                                   className="font-fjalla text-lg text-gray-600 uppercase mb-1 block">Email:</label>
                            <input type="text" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                   placeholder="E-Mail"
                                   {...register("email", {
                                       required: "El email es obligatorio",
                                       pattern: {
                                           value: /\S+@\S+\.\S+/,
                                           message: "El formato de e-mail no es valido",
                                       }
                                   })}
                            />
                            <div className="bg-red-200 text-red-600 text-center font-fjalla mt-1 rounded-md">
                                {errors.email && String(errors.email.message)}
                            </div>
                        </div>

                        <div className="my-5">
                            <label htmlFor="password"
                                   className="font-fjalla text-lg text-gray-600 uppercase mb-1 block">Password:</label>
                            <input type="password" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                   placeholder="Minimo 5 caracteres"
                                   {...register("password", {
                                       required: "El password es obligatorio",
                                       minLength: {
                                           value: 5,
                                           message: "El password debe tener al menos 5 caracteres"
                                       }
                                   })}
                            />
                            <div className="bg-red-200 text-red-600 text-center font-fjalla mt-1 rounded-md">
                                {errors.password && String(errors.password.message)}
                            </div>
                        </div>

                        <div>
                            <input type="submit" value="Actualizar Perfil" className="border border-red-600 p-2 w-full text-center font-fjalla font-semibold text-xl rounded-lg bg-red-600 text-white cursor-pointer hover:bg-white hover:text-red-600"/>
                        </div>
                    </form>
                </div>
            </div>
        </Fragment>
    );
}
export default FormEditarPerfil;