import {Fragment} from "react";
import {Link} from "react-router-dom";

const HeaderApp = () => {
    return (
        <Fragment>
            <header className="bg-gray-50 py-5 border shadow-md">
                <div className="max-w-7xl mx-auto flex flex-col md:flex-row items-center md:justify-between">
                    <div className="w-full p-5 lg:p-0 md:w-1/4">
                        <Link to="/administracion">
                            <img src="/imagenes/logo1.png" alt="Logo de Meeti" className="max-h-32"/>
                        </Link>
                    </div>
                    <div className="w-full py-5 lg:p-0 md:w-2/4">
                        <h2 className="text-4xl font-fjalla text-red-600">Conectando personas, creando momentos</h2>
                    </div>
                    <div className="w-full py-5 lg:p-0 md:w-1/4 space-x-6">
                        <Link to="/administracion/crear-grupo" className="border-b-4 border-red-600 text-xl font-semibold text-red-600 hover:bg-gray-800 hover:text-white hover:border-none hover:p-1 hover:rounded-md">Crear Grupo</Link>
                        <Link to="/administracion/perfil" className="border-b-4 border-red-600 text-xl font-semibold text-red-600 hover:bg-gray-800 hover:text-white hover:border-none hover:p-1 hover:rounded-md">Mi Perfil</Link>
                        <a onClick={() => {
                            localStorage.removeItem("TOKEN_MEETI");
                        }} className="border-b-4 border-red-600 text-xl font-semibold text-red-600 hover:bg-gray-800 hover:text-white hover:border-none hover:p-1 hover:rounded-md">Salir</a>
                    </div>
                </div>
            </header>
        </Fragment>
    );
}
export default HeaderApp;