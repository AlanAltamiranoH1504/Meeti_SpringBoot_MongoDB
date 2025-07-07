import {BrowserRouter, Routes, Route} from "react-router-dom";
import RegisterView from "./view/RegisterView";
import AuthLayout from "./layouts/AuthLayout";
import LoginView from "./view/LoginView";
import AppLayout from "./layouts/AppLayout";
import AdministracionView from "./view/AdministracionView";
import CrearGrupoView from "./view/grupos/CrearGrupoView";
import EditarGrupoView from "./view/grupos/EditarGrupoView";
import EditarImagenView from "./view/grupos/EditarImagenView";
import EditarPerfilView from "./view/usuario/EditarPerfilView";

const RouterApp = () => {
    return (
        <BrowserRouter>
            <Routes>
                {/*Rutas de registro y login*/}
                <Route element={<AuthLayout/>}>
                    <Route path="/register" element={<RegisterView/>}/>
                    <Route path="/login" element={<LoginView/>}/>
                </Route>

                {/*Ruta de area protegida por autenticacion*/}
                <Route element={<AppLayout/>}>
                    <Route path="/administracion" element={<AdministracionView/>}/>
                    <Route path="/administracion/crear-grupo" element={<CrearGrupoView/>}/>
                </Route>

                {/*Rutas par grupos*/}
                <Route element={<AppLayout/>}>
                    <Route path="/grupos/edicion/:id" element={<EditarGrupoView/>}></Route>
                    <Route path="/grupos/edicion/imagen/:id" element={<EditarImagenView/>}></Route>
                </Route>

                {/* Rutas para usuario*/}
                <Route element={<AppLayout/>}>
                    <Route path="/administracion/editar/perfil" element={<EditarPerfilView/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default RouterApp;