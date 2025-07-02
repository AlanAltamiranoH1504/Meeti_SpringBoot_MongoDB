import {Fragment} from "react";
import {useQueryClient} from "@tanstack/react-query";
import type {UsuarioLogeado} from "../types";
const AdministracionView = () => {
    const queryClient = useQueryClient();
    const usuarioEnCache: UsuarioLogeado = queryClient.getQueryData<UsuarioLogeado>(["usuarioEnSesion"]) as UsuarioLogeado;
    return (
        <Fragment>
            <div className="max-w-7xl mx-auto md:flex flex-row">
                <div className="md:w-2/5 justify-center align-middle items-center">
                    <h2 className="text-3xl text-center font-fjalla hover:text-red-600">Bienvenido de vuelta <span className="block hover:text-indigo-600">{usuarioEnCache.nombre + " " + usuarioEnCache.apellidos}</span></h2>
                </div>
                <div className="md:w-3/5 justify-center align-middle items-center">
                    Imagen de perfil
                </div>
            </div>
        </Fragment>
    );
}
export default AdministracionView;