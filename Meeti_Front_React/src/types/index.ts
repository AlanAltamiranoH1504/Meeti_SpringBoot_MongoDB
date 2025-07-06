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

export type Grupo = {
    id: number,
    nombre: string,
    categoriaId: number,
    descripcion: string,
    imagen: string,
    sitioWeb: string
}

export type Categoria = {
    id: number,
    nombre: string,
    updatedAt: Date,
}

export type UsuarioRegistro = Pick<Usuario, "nombre" | "apellidos" | "username" | "email" | "password">
export type UsuarioLogin = Pick<Usuario, "email" | "password">
export type UsuarioLogeado = Pick<Usuario, "id" | "nombre" | "apellidos" | "email">
export type SaveGrupo = Pick<Grupo, "nombre" | "descripcion" | "categoriaId" | "imagen" | "sitioWeb">