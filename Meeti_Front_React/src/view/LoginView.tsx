import FormLogin from "../components/FormLogin";

const LoginView = () => {
    return (
        <div className="md:flex md:items-center md:justify-center min-h-screen px-4 gap-8">
            <div className="md:w-1/2 flex justify-center items-center mb-6 md:mb-0">
                <img src="/imagenes/img.png" className="max-h-96" alt="Logo Meeti"/>
            </div>
            <div className="md:w-1/2 bg-white p-6 rounded-lg shadow-sm border w-full max-w-full">
                <h3 className="text-center text-2xl font-semibold uppercase text-red-600 border-b-4 border-red-600">Crea una Cuenta y Comienza tus Meetis</h3>
                <FormLogin/>
            </div>
        </div>
    );
}
export default LoginView