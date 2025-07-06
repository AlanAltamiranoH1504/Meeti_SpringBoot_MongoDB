import {Link} from "react-router-dom";

const Error404 = () => {
    return(
        <>
            <div className="bg-gradient-to-br border-gray-100 flex items-center justify-center min-h-screen p-4 font-fjalla">
                <div className="bg-white shadow-2xl rounded-3xl p-10 max-w-md text-center border border-blue-200">
                    <div className="text-red-700">
                        <svg className="mx-auto h-20 w-20" fill="none" stroke="currentColor" stroke-width="1.5"
                             viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                  d="M12 9v2.25M12 15h.01M21 12A9 9 0 113 12a9 9 0 0118 0z"/>
                        </svg>
                    </div>
                    <h1 className="text-5xl font-extrabold text-gray-800 mt-4 mb-2">404</h1>
                    <h2 className="text-xl font-semibold text-gray-700 mb-4">Página no encontrada</h2>
                    <p className="text-gray-500 mb-6">Lo sentimos, no pudimos encontrar la página que estás
                        buscando.</p>
                    <Link to="/administracion" className="inline-block px-6 py-3 bg-red-600 font-semibold  text-white font-medium rounded-lg shadow hover:bg-red-700 transition">Regresar</Link>
                </div>
            </div>
        </>
    );
}
export default Error404;