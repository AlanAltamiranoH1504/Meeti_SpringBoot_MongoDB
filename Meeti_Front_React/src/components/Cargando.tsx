import {Fragment} from "react";

const Cargando = () => {
    return (
        <Fragment>
            <div className="flex flex-col">
                <h2 className="text-4xl font-fjalla text-center">Cargando...</h2>
                <div className="flex justify-center my-10">
                    <img src="/imagenes/Spiner%20Carga%20Gif.gif" className="max-h-64"/>
                </div>
            </div>;
        </Fragment>
    );
}
export default Cargando;