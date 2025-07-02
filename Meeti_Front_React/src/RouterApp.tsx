import {BrowserRouter, Routes, Route} from "react-router-dom";
import RegisterView from "./view/RegisterView";
import AuthLayout from "./layouts/AuthLayout";
import LoginView from "./view/LoginView";

const RouterApp = () => {
    return (
        <BrowserRouter>
            <Routes>
                {/*Rutas de registro y login*/}
                <Route element={<AuthLayout/>}>
                    <Route path="/register" element={<RegisterView/>}/>
                    <Route path="/login" element={<LoginView/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default RouterApp;