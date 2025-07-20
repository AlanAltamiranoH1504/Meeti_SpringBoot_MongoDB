import {useMutation, useQuery} from "@tanstack/react-query";
import {findAllGruposByUsuarioId, saveMeetiPeticion} from "../../api/ApiSpringBoot";
import Cargando from "../Cargando";
import {Link, useNavigate} from "react-router-dom";
import { TrixEditor } from "react-trix";
import "trix/dist/trix.css";
import "trix";
import type { Grupo, SaveMeeti } from "../../types";
import { useState, useEffect } from "react";
import MapaLeaftlet from "./MapaLeaftlet";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";

const FormCrearMeeti = () => {
    const [content, setContent] = useState("");
    const { register, handleSubmit, formState: { errors }, setValue} = useForm<SaveMeeti>();
    const navigate = useNavigate();
    const { data, isLoading, isError } = useQuery({
        queryKey: ["findGruposByUserId"],
        queryFn: findAllGruposByUsuarioId,
        retry: false,
        refetchOnWindowFocus: false
    });

    // Estados para los datos del mapa/ubicacion
    const [direccion, setDireccion] = useState("");
    const [ciudad, setCiudad] = useState("");
    const [estado, setEstado] = useState("");
    const [pais, setPais] = useState("");
    const [lat, setLat] = useState(19.4326);
    const [lng, setLng] = useState(-99.1332);

    // Sincronizar valores para inputs controlados y react-hook-form
    useEffect(() => {
        setValue("direccion", direccion);
    }, [direccion, setValue]);

    useEffect(() => {
        setValue("ciudad", ciudad);
    }, [ciudad, setValue]);

    useEffect(() => {
        setValue("estado", estado);
    }, [estado, setValue]);

    useEffect(() => {
        setValue("pais", pais);
    }, [pais, setValue]);

    useEffect(() => {
        setValue("lat", lat);
    }, [lat, setValue]);

    useEffect(() => {
        setValue("lng", lng);
    }, [lng, setValue]);

    const saveMeetiFuncion = (data: SaveMeeti) => {
        if (content.trim() === "" || content == null) {
            toast.error("La descripcion del meeti es obligatoria");
            return;
        }
        const dataWithDescripcion: SaveMeeti = {
            ...data,
            descripcion: content
        };
        saveMeetiMutation.mutate(dataWithDescripcion);
    };

    const saveMeetiMutation = useMutation({
        mutationKey: ["saveMeetiMutation"],
        mutationFn: saveMeetiPeticion,
        onError: () => {
            toast.error("Error en creacion de meeti");
        },
        onSuccess: () => {
            toast.success("Meeti guardado correctamente");
            navigate("/administracion");
        }
    })

    if (isLoading) {
        return <Cargando />
    }
    if (isError) {
        return (
            <div className="md:flex flex-col">
                <p className="text-center text-4xl font-fjalla text-red-600">
                    Error para creación de meeti. No tienes grupos disponibles.
                </p>
                <Link
                    className="mx-auto text-center my-5 font-fjalla text-xl border py-2 px-24 rounded-lg border-2 border-red-600 text-red-600 hover:text-red-700"
                    to="/administracion"
                >
                    Regresar a Home
                </Link>
            </div>
        )
    }

    return (
        <>
            <div className="max-w-4xl mx-auto">
                <div className="bg-white p-5 py-10 border rounded-lg shadow-md">
                    <h2 className="text-center font-fjalla text-3xl border-b-2 border-red-600 pb-3">
                        Crear Nuevo Meeti
                    </h2>
                    <form className="space-y-5" onSubmit={handleSubmit(saveMeetiFuncion)}>

                        <legend className="font-fjalla text-2xl text-gray-600 text-center font-semibold mt-5">
                            Información general del Meeti
                        </legend>

                        <div className="mt-4">
                            <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="grupo">
                                Grupo
                            </label>
                            <select
                                className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                {...register("grupo_id", { required: "El grupo es obligatorio" })}
                            >
                                <option value="">--- Selecciona una opción ---</option>
                                {data.map((grupo: Grupo) => (
                                    <option key={grupo.id} value={grupo.id}>{grupo.nombre}</option>
                                ))}
                            </select>
                            <div className="bg-red-200 text-center text-red-600 font-fjalla mt-1 rounded-md">
                                {errors.grupo_id && String(errors.grupo_id.message)}
                            </div>
                        </div>

                        <div>
                            <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="titulo">
                                Titulo
                            </label>
                            <input
                                type="text"
                                className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                placeholder="Titulo del meeti"
                                {...register("titulo", { required: "El titulo del meeti es obligatorio" })}
                            />
                            <div className="bg-red-200 text-center text-red-600 font-fjalla mt-1 rounded-md">
                                {errors.titulo && String(errors.titulo.message)}
                            </div>
                        </div>

                        <div>
                            <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="invitado">
                                Invitado
                            </label>
                            <input
                                type="text"
                                className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                placeholder="Invitado del meeti (opcional)"
                                {...register("invitado")}
                            />
                        </div>

                        <div className="md:flex justify-evenly">
                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="fecha">
                                    Fecha
                                </label>
                                <input
                                    type="date"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("fecha", { required: "La fecha del meeti es obligatoria" })}
                                />
                                <div className="bg-red-200 text-center text-red-600 font-fjalla mt-1 rounded-md">
                                    {errors.fecha && String(errors.fecha.message)}
                                </div>
                            </div>
                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="hora">
                                    Hora
                                </label>
                                <input
                                    type="time"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("hora", { required: "La hora del meeti es obligatoria" })}
                                />
                                <div className="bg-red-200 text-center text-red-600 font-fjalla mt-1 rounded-md">
                                    {errors.hora && String(errors.hora.message)}
                                </div>
                            </div>
                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="cupo">
                                    Cupo
                                </label>
                                <input
                                    type="number"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    placeholder="Cupo de asistentes"
                                    {...register("cupo", {
                                        required: "El cupo es obligatorio",
                                        min: { value: 1, message: "El cupo mínimo es 1" }
                                    })}
                                />
                                <div className="bg-red-200 text-center text-red-600 font-fjalla mt-1 rounded-md">
                                    {errors.cupo && String(errors.cupo.message)}
                                </div>
                            </div>
                        </div>

                        <div>
                            <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="descripcion">
                                Descripción
                            </label>
                            <TrixEditor className="min-h-96" value={content} onChange={(html) => setContent(html)} />
                            <div className="hidden" dangerouslySetInnerHTML={{ __html: content }}></div>
                        </div>

                        <legend className="font-fjalla text-2xl text-gray-600 text-center font-semibold">
                            Ubicación del Meeti
                        </legend>

                        <MapaLeaftlet
                            setDireccion={setDireccion}
                            setCiudad={setCiudad}
                            setEstado={setEstado}
                            setPais={setPais}
                            setLat={setLat}
                            setLng={setLng}
                        />

                        {/* Inputs que recuperan la ubicación y que puedes modificar */}
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="direccion">
                                    Dirección
                                </label>
                                <input
                                    type="text"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("direccion")}
                                    value={direccion}
                                    onChange={e => setDireccion(e.target.value)}
                                />
                            </div>

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="ciudad">
                                    Ciudad
                                </label>
                                <input
                                    type="text"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("ciudad")}
                                    value={ciudad}
                                    onChange={e => setCiudad(e.target.value)}
                                />
                            </div>

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="estado">
                                    Estado
                                </label>
                                <input
                                    type="text"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("estado")}
                                    value={estado}
                                    onChange={e => setEstado(e.target.value)}
                                />
                            </div>

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="pais">
                                    País
                                </label>
                                <input
                                    type="text"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("pais")}
                                    value={pais}
                                    onChange={e => setPais(e.target.value)}
                                />
                            </div>

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="lat">
                                    Latitud
                                </label>
                                <input
                                    type="number"
                                    step="any"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("lat", { valueAsNumber: true })}
                                    value={lat}
                                    readOnly={true}
                                    onChange={e => setLat(Number(e.target.value))}
                                />
                            </div>

                            <div>
                                <label className="font-fjalla font-semibold text-gray-600 text-xl block mb-2" htmlFor="lng">
                                    Longitud
                                </label>
                                <input
                                    type="number"
                                    step="any"
                                    className="border p-2 w-full rounded-lg border-gray-300 font-fjalla"
                                    {...register("lng", { valueAsNumber: true })}
                                    value={lng}
                                    readOnly={true}
                                    onChange={e => setLng(Number(e.target.value))}
                                />
                            </div>

                        </div>

                        <div>
                            <input
                                type="submit"
                                value="Crear Meeti"
                                className="border py-2 w-full border-red-600 font-fjalla font-semibold uppercase text-lg rounded-md bg-red-600 text-white cursor-pointer hover:bg-white hover:text-red-600"
                            />
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
};

export default FormCrearMeeti;
