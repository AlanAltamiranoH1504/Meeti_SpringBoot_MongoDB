export const formatoFecha = (fecha: string) => {
    return Intl.DateTimeFormat("es-MX", {
        year: "numeric",
        month: "long",
        day: "numeric",
    }).format(new Date(fecha));
};

export const formatoHora = (hora: string) => {
    return Intl.DateTimeFormat("es-MX", {
        hour: "numeric",
        minute: "numeric",
        hour12: true,
    }).format(new Date(hora));
}