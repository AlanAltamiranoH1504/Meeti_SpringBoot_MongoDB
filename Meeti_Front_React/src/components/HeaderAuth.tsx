import {Fragment} from "react";
import {Link} from "react-router-dom";

const HeaderAuth = () => {
    return (
        <Fragment>
            <header className="bg-gray-50 py-5 border shadow-md">
                <div className="max-w-7xl mx-auto flex flex-col md:flex-row items-center md:justify-between">
                    <div className="w-full p-5 lg:p-0 md:w-1/3">
                        <Link to="/login">
                            <img src="/imagenes/logo1.png" alt="Logo de Meeti" className="max-h-32"/>
                        </Link>
                    </div>
                    <div className="md:w-2/3 md:flex md:justify-center md:gap-5 space-x-5">
                        <h2 className="text-4xl text-red-600 font-black uppercase font-fjalla">La app que transforma reuniones en experiencias</h2>
                    </div>
                </div>
            </header>
        </Fragment>
    );
}
export default HeaderAuth;