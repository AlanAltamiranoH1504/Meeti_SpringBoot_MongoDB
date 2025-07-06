import type {Grupo} from "../../types";
import {Link} from "react-router-dom";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {deleteGrupoById} from "../../api/ApiSpringBoot";
import {toast} from "react-toastify";

type GrupoDetallesRowProps = {
    grupo: Grupo
}

const GrupoDetallesRow = ({grupo}: GrupoDetallesRowProps) => {
    const queryClient = useQueryClient();
    const eliminarGrupo = (id: number) => {
        mutationDeleteGrupoById.mutate(id);
    }

    const mutationDeleteGrupoById = useMutation({
        mutationKey: ["deleteGrupoById"],
        mutationFn: deleteGrupoById,
        onSuccess: (result) => {
            queryClient.invalidateQueries({
                queryKey: ["findAllGruposByUsuarioId"]
            });
            toast.success(result.msg)
        },
        onError: (error) => {
            console.log(error)
            toast.error("Error en eliminiación de grupo.")
        }
    })


    return (
        <div className="flex flex-col md:flex-row md:items-center justify-between my-10 space-y-2 md:space-y-0 md:space-x-0 border-b-2 border-neutral-400 pb-5">
            <h2 className="font-fjalla text-3xl uppercase">{grupo.nombre}</h2>
            <div className="flex flex-col md:flex-row gap-4">
                <Link to={`/grupos/edicion/${grupo.id}`} className="border-2 border-orange-600 py-4 px-24 rounded-lg shadow-md bg-orange-500 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-orange-500 text-center">Editar</Link>
                <Link to="" className="border-2 border-indigo-600 py-4 px-24 rounded-lg shadow-md bg-indigo-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-indigo-600 text-center">Imagen de Grupo</Link>
                <button
                    type="button"
                    className="border-2 border-red-600 py-4 px-24 rounded-lg shadow-md bg-red-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-red-600 text-center"
                    onClick={() => {
                        eliminarGrupo(grupo.id);
                    }}
                >Eliminar</button>
            </div>
        </div>
    );
}
export default GrupoDetallesRow;