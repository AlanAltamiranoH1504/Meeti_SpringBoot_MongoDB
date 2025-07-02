import {Fragment} from "react";
import {useForm} from "react-hook-form";
import {useMutation} from "@tanstack/react-query"
import type {UsuarioRegistro} from "../types";
import {registroUsuario} from "../api/ApiSpringBoot";
import {toast} from "react-toastify";
import {AxiosError} from "axios";
import {Link, useNavigate} from "react-router-dom";

const FormRegister = () => {
    const {register, handleSubmit, formState: {errors}} = useForm<UsuarioRegistro>();
    const navigate = useNavigate();

    const handleSubmitToRegiser = (data: UsuarioRegistro) => {
        mutatioRegistroUsuario.mutate(data);
    }

    const mutatioRegistroUsuario = useMutation({
        mutationFn: registroUsuario,
        mutationKey: ["registroUsuario"],
        onSuccess: () => {
            toast.success("Usuario registrado correctamente.")
            navigate("/login");
        },
        onError: (error: AxiosError<{ msg: string }>) => {
            if (error.isAxiosError) {
                toast.error(error.response?.data?.msg || "Error en el registro de usuario.")
            }
        }
    });

    return (
        <Fragment>
            <form className="space-y-5"
                  onSubmit={handleSubmit(handleSubmitToRegiser)}
            >
                <div className="my-3">
                    <label htmlFor="nombre" className="text-gray-600 text-xl  font-fjalla block mb-2">Nombre:</label>
                    <input type="text" className="border p-2 w-full rounded-lg border-gray-300 font-fjalla" placeholder="Nombre"
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
                           className="text-gray-600 text-xl  block font-fjalla mb-2">Apellidos:</label>
                    <input type="text" className="border font-fjalla p-2 w-full rounded-lg border-b-gray-300"
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
                           className="text-gray-600 text-xl  block font-fjalla mb-2">Username:</label>
                    <input type="text" className="border p-2 font-fjalla w-full rounded-lg border-gray-300" placeholder="Username"
                           {...register("username", {
                               required: "El username es obligatorio."
                           })}
                    />
                    <div className="bg-red-200 text-red-500 font-semibold text-sm text-center rounded-md mt-1">
                        {errors.username && String(errors.username.message)}
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="text-gray-600 text-xl  font-fjalla block mb-2">E-Mail:</label>
                    <input type="email" className="border font-fjalla p-2 w-full rounded-lg border-gray-300"
                           placeholder="E-Mail"
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
                           className="text-gray-600 text-xl  block font-fjalla mb-2">Password:</label>
                    <input type="password" className="border font-fjalla p-2 w-full rounded-lg border-gray-300"
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
                           className="w-full font-fjalla border border-red-600 p-2 rounded-lg text-white  uppercase bg-red-600 hover:text-red-600 hover:bg-white"/>
                </div>

                <div className="mb-3 flex justify-between">
                    <Link className="text-slate-500 font-fjalla" to="/login">Iniciar Sesión</Link>
                    <Link className="text-slate-500  font-fjalla" to="/olvide-password">Recuperar password</Link>
                </div>
            </form>
        </Fragment>
    );
}
export default FormRegister;