import type {Meeti} from "../../types";
import {Link} from "react-router-dom";
type MeetiDetallesRowProps = {
    meeti: Meeti
}

const MeetiDetallesRow = ({meeti}: MeetiDetallesRowProps) => {
    return (
        <>
            <div className="flex flex-col md:flex-row md:items-center justify-between my-10 space-y-2 md:space-y-0 md:space-x-0 border-b-2 border-neutral-400 pb-5">
                <h2 className="font-fjalla text-3xl uppercase">{meeti.titulo}</h2>
                <div className="flex flex-col md:flex-row gap-4">
                    <Link to={`/meeti/edicion/${meeti.id}`} className="border-2 border-orange-600 py-4 px-24 rounded-lg shadow-md bg-orange-500 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-orange-500 text-center">Editar</Link>
                    <Link to={`/meeti/edicion/imagen/${meeti.id}`} className="border-2 border-indigo-600 py-4 px-24 rounded-lg shadow-md bg-indigo-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-indigo-600 text-center">Asistentes</Link>
                    <button
                        type="button"
                        className="border-2 border-red-600 py-4 px-24 rounded-lg shadow-md bg-red-600 text-white font-semibold font-fjalla hover:bg-gray-50 hover:text-red-600 text-center"
                    >Eliminar</button>
                </div>
            </div>
        </>
    );
}
export default MeetiDetallesRow;