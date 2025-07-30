import {clienteAxios} from "../config/clienteAxios";
import type {SaveGrupo, SaveMeeti, UpdateGrupo, UpdatePerfilUsuario, UsuarioLogin, UsuarioRegistro} from "../types";

export async function registroUsuario(usuario: UsuarioRegistro) {
    // eslint-disable-next-line no-useless-catch
    try {
        const response = await clienteAxios.post("/register/crear-cuenta", usuario);
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function loginUsuario(usuario: UsuarioLogin) {
    // eslint-disable-next-line no-useless-catch
    try {
        const response = await clienteAxios.post("/login", usuario);
        const token: string = response.data.token;
        localStorage.setItem("TOKEN_MEETI", token);
        return response.data;
    } catch (e) {
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
    } catch (e) {
        throw e;
    }
}

export async function updateInformacionPerfilPeticion(usuario: UpdatePerfilUsuario) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.put("/usuarios/update-perfiil", usuario, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function updateImagenPerfil(formData: FormData) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.post(`/imagenes/avatar`, formData, {
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "multipart/form-data"
            }
        });
        return response.data;
    } catch (e) {
        console.log(e)
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
    } catch (e) {
        throw e;
    }
}

export async function findAllGruposByUsuarioId() {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.get("/grupos", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function findGrupoByIdPeticion(id: string) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.get(`/grupos/${id}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function saveGrupoPeticion(grupo: SaveGrupo) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.post("/grupos", grupo, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function updateGrupoPeticion(grupo: UpdateGrupo) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.put(`/grupos/${grupo.id}`, grupo, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function updateImagenGrupoPeticion(formData: FormData) {
    try {
        const idGrupo = formData.get("id");
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.post(`/imagenes/grupo/${idGrupo}`, formData, {
            headers: {
                "Authorization": "Bearer " + token,
                "Content-Type": "multipart/form-data"
            }
        });
        return response.data;
    } catch (e) {
        console.log(e)
        throw e;
    }
}

export async function deleteGrupoById(id: number) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.delete(`/grupos/${id}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

/**
 * PETICIONES PARA MEETICONTROLLER
 */

export async function findAllMeetisByUserIdPeticion() {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.get("/meetis/findAllByUserId", {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    }catch (e) {
        throw e;
    }
}

export async function saveMeetiPeticion(meeti: SaveMeeti) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.post("/meetis", meeti, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    } catch (e) {
        throw e;
    }
}

export async function deleteMeetiByIdPeticion(idMeeti: string) {
    try {
        // @ts-ignore
        const token: string = localStorage.getItem("TOKEN_MEETI");
        const response = await clienteAxios.delete(`/meetis/${idMeeti}`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        return response.data;
    }catch (e) {
        throw e;
    }
}
