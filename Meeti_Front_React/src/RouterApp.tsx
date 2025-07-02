import {BrowserRouter, Routes, Route} from "react-router-dom";
import LoginView from "./view/LoginView";

const RouterApp = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginView/>}/>
            </Routes>
        </BrowserRouter>
    )
}
export default RouterApp;