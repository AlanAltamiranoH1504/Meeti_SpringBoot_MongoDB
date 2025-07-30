import type {Meeti} from "../../types";
import {Link} from "react-router-dom";
import {useMutation, useQueryClient} from "@tanstack/react-query";
import {deleteMeetiByIdPeticion} from "../../api/ApiSpringBoot";
import {toast} from "react-toastify";
import {formatoFecha} from "../../helpers";

type MeetiDetallesRowProps = {
    meeti: Meeti
}

const MeetiDetallesRow = ({meeti}: MeetiDetallesRowProps) => {
    const queryClient = useQueryClient();
    const horaActual = new Intl.DateTimeFormat("es-MX").format(new Date());

    function eliminarMeeti(idMeeti: string) {
        deleteMeetiByIdMutation.mutate(idMeeti);
    }

    const deleteMeetiByIdMutation = useMutation({
        mutationKey: ["deleteMeetiById"],
        mutationFn: deleteMeetiByIdPeticion,
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: ["findAllMeetisByUsuarioId"]
            });
            toast.success("Meeti eliminado correctamente");
        },
        onError: () => {
            toast.error("Error en eliminación de meeti");
        }
    });
    return (
        <>
            {formatoFecha(meeti.fecha) >= horaActual ? (
                <div
                    className="flex flex-col md:flex-row md:items-center justify-between my-10 space-y-2 md:space-y-0 md:space-x-0 border-b-2 border-neutral-400 pb-5">
                    <div className="block space-y-2">
                        <h2 className="font-fjalla text-3xl uppercase">{meeti.titulo}</h2>
                        <p className="text-sm text-gray-600 font-fjalla font-light">Fecha: {formatoFecha(meeti.fecha)}</p>
                        <p className="text-sm text-gray-600 font-fjalla">Hora: {meeti.hora} hrs </p>
                        <p className="text-sm text-gray-600 font-fjalla">Asistentes: 14 asistentes</p>
                    </div>
                    <div className="flex flex-col md:flex-row gap-4">
                        <Link to={`/meeti/edicion/${meeti.id}`}
                              className="border-2 border-orange-600 py-4 px-24 rounded-lg shadow-md bg-orange-500 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-orange-500 text-center">Editar</Link>
                        <Link to={`/meeti/edicion/imagen/${meeti.id}`}
                              className="border-2 border-indigo-600 py-4 px-24 rounded-lg shadow-md bg-indigo-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-indigo-600 text-center">Asistentes</Link>
                        <button
                            onClick={() => {
                                eliminarMeeti(meeti.id);
                            }}
                            type="button"
                            className="border-2 border-red-600 py-4 px-24 rounded-lg shadow-md bg-red-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-red-600 text-center"
                        >Eliminar
                        </button>
                    </div>
                </div>
            ) : (
                <p></p>
            )}
        </>
    );
}
export default MeetiDetallesRow;