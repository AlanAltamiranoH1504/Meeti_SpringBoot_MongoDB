import {BrowserRouter, Routes, Route} from "react-router-dom";
import RegisterView from "./view/RegisterView";
import AuthLayout from "./layouts/AuthLayout";
import LoginView from "./view/LoginView";
import AppLayout from "./layouts/AppLayout";
import AdministracionView from "./view/AdministracionView";

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
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default RouterApp;