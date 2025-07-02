import {Fragment} from "react";
import {useForm} from "react-hook-form";
import {useMutation} from "@tanstack/react-query"
import type {UsuarioRegistro} from "../types";
import {registroUsuario} from "../api/ApiSpringBoot";
import {toast} from "react-toastify";

const FormLogin = () => {
    const {register, handleSubmit, formState: {errors}} = useForm<UsuarioRegistro>();

    const handleSubmitToRegiser = (data: UsuarioRegistro) => {
        mutatioRegistroUsuario.mutate(data);
    }

    const mutatioRegistroUsuario = useMutation({
        mutationFn: registroUsuario,
        mutationKey: ["registroUsuario"],
        onSuccess: (data) => {
            console.log(data)
            toast.success("Usuario registrado correctamente.")
        },
        onError: (error) => {
            console.log(error)
            toast.error("Error en el registro de usaurio.")
        }
    });

    return (
        <Fragment>
            <form className="space-y-5"
                  onSubmit={handleSubmit(handleSubmitToRegiser)}
            >
                <div className="my-3">
                    <label htmlFor="nombre" className="text-gray-600 text-xl font-semibold block mb-2">Nombre:</label>
                    <input type="text" className="border p-2 w-full rounded-lg border-gray-300" placeholder="Nombre"
                           {...register("nombre", {
                               required: "El nombre es obligatorio."
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.nombre && String(errors.nombre.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="apellidos"
                           className="text-gray-600 text-xl font-semibold block mb-2">Apellidos:</label>
                    <input type="text" className="border p-2 w-full rounded-lg border-b-gray-300"
                           placeholder="Tus apellidos"
                           {...register("apellidos", {
                               required: "Los apellidos son obligatorios."
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.apellidos && String(errors.apellidos.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="username"
                           className="text-gray-600 text-xl font-semibold block mb-2">Username:</label>
                    <input type="text" className="border p-2 w-full rounded-lg border-gray-300" placeholder="Username"
                           {...register("username", {
                               required: "El username es obligatorio."
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.username && String(errors.username.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="text-gray-600 text-xl font-semibold block mb-2">E-Mail:</label>
                    <input type="email" className="border p-2 w-full rounded-lg border-gray-300" placeholder="E-Mail"
                           {...register("email", {
                               required: "El e-mail es obligatorio.",
                               pattern: {
                                   value: /\S+@\S+\.\S+/,
                                   message: "El formato de email no es el correcto."
                               }
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.email && String(errors.email.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="password"
                           className="text-gray-600 text-xl font-semibold block mb-2">Password:</label>
                    <input type="password" className="border p-2 w-full rounded-lg border-gray-300"
                           placeholder="Minimo 5 caracteres"
                           {...register("password", {
                               required: "El password es obligatorio.",
                               minLength: {
                                   value: 5,
                                   message: "El password debe tener minimo 5 caracteres"
                               }
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.password && String(errors.password.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <input type="submit" value="Registrar Cuenta"
                           className="w-full border border-red-600 p-2 rounded-lg text-white font-semibold uppercase bg-red-600 hover:text-red-600 hover:bg-white"/>
                </div>
            </form>
        </Fragment>
    );
}
export default FormLogin;