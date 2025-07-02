import {clienteAxios} from "../config/clienteAxios";
import type {UsuarioRegistro} from "../types";

export async function registroUsuario(usuario: UsuarioRegistro) {
    try {
        const response = await clienteAxios.post("/register/crear-cuenta", usuario);
        return response.data;
    } catch (e) {
        throw e;
    }
}
