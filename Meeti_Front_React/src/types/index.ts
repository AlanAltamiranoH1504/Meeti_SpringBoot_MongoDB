export type Usuario = {
    id: number,
    nombre: string,
    apellidos: string,
    username: string,
    email: string,
    password: string,
    token: string,
    confirmado: boolean
}

export type UsuarioRegistro = Pick<Usuario, "nombre" | "apellidos" | "username" | "email" | "password">
export type UsuarioLogin = Pick<Usuario, "email" | "password">
export type UsuarioLogeado = Pick<Usuario, "id" | "nombre" | "apellidos" | "email">