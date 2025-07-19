export type Usuario = {
    id: number,
    nombre: string,
    apellidos: string,
    username: string,
    email: string,
    password: string,
    imagen: string,
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

export type Meeti = {
    id: string,
    grupo_id: number,
    titulo: string,
    invitado: string,
    fecha: string,
    hora: string,
    cupo: number,
    descripcion: string,
    direccion: string,
    ciudad: string,
    estado: string,
    pais: string,
    lat: string,
    lng: string,
}

export type Categoria = {
    id: number,
    nombre: string,
    updatedAt: Date,
}

export type UsuarioRegistro = Pick<Usuario, "nombre" | "apellidos" | "username" | "email" | "password">
export type UsuarioLogin = Pick<Usuario, "email" | "password">
export type UsuarioLogeado = Pick<Usuario, "id" | "nombre" | "apellidos" | "email" | "username" | "imagen" >
export type UpdatePerfilUsuario = Pick<Usuario, "nombre" | "apellidos" | "username" | "email" | "password">
export type UpdateImagenUsuario = Pick<Usuario, "imagen">

export type SaveGrupo = Pick<Grupo, "nombre" | "descripcion" | "categoriaId" | "imagen" | "sitioWeb">
export type UpdateGrupo = Pick<Grupo, "id" | "nombre" | "descripcion" | "categoriaId" | "sitioWeb">
export type UpdateImagenGrupo = Pick<Grupo, "imagen">

export type SaveMeeti = Omit<Meeti, "id">