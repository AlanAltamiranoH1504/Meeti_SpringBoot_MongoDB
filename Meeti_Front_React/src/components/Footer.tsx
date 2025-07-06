import {Link} from "react-router-dom";

const Footer = () => {
    return (
        <div className="bg-gray-50 py-5 border shadow-md">
            <div className="max-w-7xl mx-auto flex flex-col md:flex-row items-center md:justify-center">
                <div className="w-full p-5 lg:p-0 md:w-1/3">
                    <Link to="/login">
                        <img src="/imagenes/logo1.png" alt="Logo de Meeti" className="max-h-20"/>
                    </Link>
                </div>
                <div className="md:w-2/3 md:flex md:justify-center md:gap-5 space-x-5">
                    <h2 className="text-2xl font-fjalla text-red-600">Conectando personas, creando momentos &nbsp;&reg;</h2>
                </div>
            </div>
        </div>
    );
}
export default Footer;