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