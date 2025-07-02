import {Fragment} from "react";
import {useForm} from "react-hook-form";
import {Link, useNavigate} from "react-router-dom";
import type {UsuarioLogin} from "../types";
import {useMutation} from "@tanstack/react-query";
import {toast} from "react-toastify";
import {loginUsuario} from "../api/ApiSpringBoot";
import {AxiosError} from "axios";

const FormLogin = () => {
    const {register, handleSubmit, formState: {errors}} = useForm<UsuarioLogin>();
    const navigate = useNavigate();

    function iniciarSesion(data: UsuarioLogin) {
        mutatioLoginUser.mutate(data);
    }

    const mutatioLoginUser = useMutation({
        mutationKey: ["mutatioLoginUser"],
        mutationFn: loginUsuario,
        onSuccess: (data) => {
            console.log(data)
            toast.success("Inicio de sesión correcto.");
            navigate("/administracion");
        },
        onError: (error: AxiosError<{msg: string}>) => {
            if (error.isAxiosError){
                toast.error(error.response?.data?.msg);
            }
        }
    })
    return (
        <Fragment>
            <form
                onSubmit={handleSubmit(iniciarSesion)}
            >
                <div className="my-4">
                    <label htmlFor="email" className="text-xl text-gray-600 font-fjalla block mb-2">Email:</label>
                    <input type="email" className="border p-2 w-full border-gray-300 font-fjalla rounded-lg"
                           placeholder="E-mail de registro"
                           {...register("email", {
                               required: "El email es obligatorio.",
                               pattern: {
                                   value: /\S+@\S+\.\S+/,
                                   message: "El formato del email no es el correcto."
                               }
                           })}
                    />
                    <div className="bg-red-200 text-red-600 font-semibold text-center text-sm rounded-md mt-1">
                        {errors.email && String(errors.email.message)}
                    </div>
                </div>
                <div className="mb-4">
                    <label htmlFor="password" className="text-xl text-gray-600 font-fjalla block mb-2">Password:</label>
                    <input type="password" className="border p-2 w-full border-gray-300 font-fjalla rounded-lg"
                           placeholder="******"
                           {...register("password",  {
                               required: "El password es obligatorio.",
                           })}
                    />
                    <div className="bg-red-200 text-red-600 font-semibold text-center text-sm rounded-md mt-1">
                        {errors.password && String(errors.password.message)}
                    </div>
                </div>
                <div className="mb-4">
                    <input type="submit" value="Iniciar Sesión"
                           className="border border-red-600 p-2 w-full font-semibold font-fjalla text-xl rounded-lg text-white bg-red-600 cursor-pointer hover:bg-white hover:text-red-600 "/>
                </div>
                <div className=" mb-4 flex justify-between">
                    <Link to="/register" className="font-fjalla text-gray-600">Crear Cuenta</Link>
                    <Link to="/olvide-password" className="font-fjalla text-gray-600">Recuperar Password</Link>
                </div>
            </form>
        </Fragment>
    );
}
export default FormLogin;