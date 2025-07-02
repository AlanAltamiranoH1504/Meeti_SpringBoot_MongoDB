import {Fragment} from "react";
import {Outlet} from "react-router-dom";
import HeaderAuth from "../components/HeaderAuth";
import Footer from "../components/Footer";

const AuthLayout = () => {
    return (
        <Fragment>
            <div className="bg-gray-50 min-h-screen">
                <HeaderAuth/>
                <div className="max-w-7xl mx-auto px-5">
                    <div className="py-10">
                        <Outlet/>
                    </div>
                </div>
                <Footer/>
            </div>
        </Fragment>
    );
}
export default AuthLayout;