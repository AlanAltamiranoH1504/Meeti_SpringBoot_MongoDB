import {Fragment} from "react";
import {Outlet, useNavigate} from "react-router-dom";
import HeaderApp from "../components/HeaderApp";
import {useQuery} from "@tanstack/react-query";
import {findAllCategoria, usuarioEnSesion} from "../api/ApiSpringBoot";

const AppLayout = () => {
    const navigate = useNavigate();
    const {data, isLoading, isError} = useQuery({
        queryKey: ["usuarioEnSesion"],
        queryFn: usuarioEnSesion,
        retry: 1,
        refetchOnWindowFocus: false
    });

    const {data: categorias} = useQuery({
        queryKey: ["findAllCategorias"],
        queryFn: findAllCategoria,
        retry: 1,
        refetchOnWindowFocus: false
    })

    if (isLoading) {
        return "Loading...";
    };
    if (isError) {
        navigate("/login");
    }
    if (data && categorias) return (
        <Fragment>
            <div className="bg-gray-50 min-h-screen">
                <HeaderApp/>
                <div className="max-w-7xl mx-auto px-5">
                    <div className="py-10">
                        <Outlet/>
                    </div>
                </div>
            </div>
        </Fragment>
    );
}
export default AppLayout;