import {Fragment, useEffect, useState} from "react";
import {useQuery, useQueryClient} from "@tanstack/react-query";
import type {Grupo, UsuarioLogeado} from "../types";
import {Link} from "react-router-dom";
import {findAllGruposByUsuarioId} from "../api/ApiSpringBoot";
import GrupoDetallesRow from "../components/grupos/GrupoDetallesRow";
// import {toast} from "react-toastify";

const AdministracionView = () => {
    const queryClient = useQueryClient();
    const usuarioEnCache: UsuarioLogeado = queryClient.getQueryData<UsuarioLogeado>(["usuarioEnSesion"]) as UsuarioLogeado;
    const [grupos, setGrupos] = useState<Grupo[]>([]);

    const {data, isLoading, isError} = useQuery({
        queryKey:["findAllGruposByUsuarioId"],
        queryFn: findAllGruposByUsuarioId,
        retry: 1,
        refetchOnWindowFocus: false
    });
    if (isLoading) {
    }
    if (isError) {
        // toast.error("Error en busqueda de grupos.")
    }

    useEffect(() => {
        if (data) {
            setGrupos(data)
        }
    }, [data])
    return (
        <Fragment>
            <div className="max-w-7xl mx-auto md:flex flex-row">
                <div className="md:w-2/5 flex justify-center align-middle items-center">
                    <h2 className="text-3xl text-center font-fjalla hover:text-red-600">
                        Bienvenido de vuelta
                        <span className="block hover:text-indigo-600">{usuarioEnCache.nombre} {usuarioEnCache.apellidos}</span>
                    </h2>
                </div>
                <div className="md:w-3/5 flex justify-center md:justify-start items-center">
                    <img
                        className="max-h-64 rounded-3xl object-cover"
                        src={usuarioEnCache.imagen ? usuarioEnCache.imagen : "/imagenes/Perfil_Default.png"}
                        alt="Imagen de perfil"
                    />
                </div>
            </div>


            <div className="max-w-7xl mx-auto flex flex-col md:flex-row justify-center my-10 space-y-2 md:space-y-0 md:space-x-2">
                <Link to="/administracion/crear-grupo" className="border-2 border-red-600 py-4 px-24 bg-red-600 text-white font-semibold text-lg rounded-lg text-center hover:bg-white hover:text-red-600 font-fjalla">Nuevo Grupo</Link>
                <Link to="" className="border-2 border-indigo-600 py-4 px-24 bg-indigo-600 text-white font-semibold text-lg rounded-lg text-center hover:bg-white hover:text-indigo-600 font-fjalla">Nuevo Meeti</Link>
                <Link to="/administracion/editar/perfil" className="border-2 border-orange-500 py-4 px-24 bg-orange-500 text-white font-semibold text-lg rounded-lg text-center hover:bg-white hover:text-orange-500 font-fjalla">Editar Perfil</Link>
                <Link to="/administracion/editar/avatar" className="border-2 border-emerald-700 py-4 px-24 bg-emerald-700 text-white font-semibold text-lg rounded-lg text-center hover:bg-white hover:text-emerald-700 font-fjalla">Editar Avatar</Link>
            </div>


            <div className="max-w-7xl mx-auto">
                <h2 className="font-fjalla text-4xl uppercase text-center">Tus Grupos</h2>
                {grupos.map((grupo: Grupo) => {
                    return (
                        <GrupoDetallesRow
                            key={grupo.id}
                            grupo={grupo}
                        />
                    )
                })}
        </div>

</Fragment>
    );
}
export default AdministracionView;