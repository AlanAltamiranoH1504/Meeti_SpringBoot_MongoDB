import {clienteAxios} from "../config/clienteAxios";
import type {SaveGrupo, UsuarioLogin, UsuarioRegistro} from "../types";

export async function registroUsuario(usuario: UsuarioRegistro) {
    // eslint-disable-next-line no-useless-catch
    try {
        const response = await clienteAxios.post("/register/crear-cuenta", usuario);
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function loginUsuario(usuario: UsuarioLogin){
    // eslint-disable-next-line no-useless-catch
    try {
        const response = await clienteAxios.post("/login", usuario);
        const token : string = response.data.token;
        localStorage.setItem("TOKEN_MEETI", token);
        return response.data;
    }catch (e) {
        throw e;
    }
}

export async function usuarioEnSesion() {
    // eslint-disable-next-line no-useless-catch
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.get("/usuarios/usuario-in-session", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    }catch (e) {
        throw e;
    }
}

export async function findAllCategoria() {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.get("/categorias", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    }catch (e) {
        throw e;
    }
}

export async  function saveGrupoPeticion(grupo: SaveGrupo) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.post("/grupos", grupo, {
            headers: {
                "Authorization" : "Bearer " + token
            }
        });
        return response.data;
    }catch (e) {
        throw e;
    }
}
