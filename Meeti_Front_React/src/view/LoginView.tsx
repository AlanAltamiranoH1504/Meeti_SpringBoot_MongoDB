import FormLogin from "../components/FormLogin";

const LoginView = () => {
    return (
        <div className="md:flex md:items-center md:justify-center min-h-screen px-4 gap-8">
            <div className="md:w-1/2 flex justify-center items-center mb-6 md:mb-0">
                <img src="/imagenes/logo2.png" className="max-h-96" alt="Logo de meeti"/>
            </div>
            <div className="md:w-1/2 bg-white px-6 rounded-lg shadow-md border w-full max-w-full pt-4">
                <h3 className="text-center text-2xl font-semibold uppercase font-fjalla mb-8 py-3 text-red-600 border-b-4 border-red-600">Inicia Sesión y Comienza tus Meetis</h3>
                <FormLogin/>
            </div>
        </div>
    );
}
export default LoginView;