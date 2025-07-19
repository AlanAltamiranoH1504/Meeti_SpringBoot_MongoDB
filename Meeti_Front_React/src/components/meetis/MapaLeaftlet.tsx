import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { useRef, useState, useEffect } from "react";
import L from "leaflet";
import "leaflet/dist/leaflet.css";

delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon-2x.png",
    iconUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png",
    shadowUrl: "https://unpkg.com/leaflet@1.9.4/dist/images/marker-shadow.png",
});

interface Address {
    road?: string;
    city?: string;
    state?: string;
    country?: string;
    postcode?: string;
}

interface Props {
    setDireccion: (dir: string) => void;
    setCiudad: (ciudad: string) => void;
    setEstado: (estado: string) => void;
    setPais: (pais: string) => void;
    setLat: (lat: number) => void;
    setLng: (lng: number) => void;
}

const MapaLeaftlet = ({
                          setDireccion,
                          setCiudad,
                          setEstado,
                          setPais,
                          setLat,
                          setLng
                      }: Props) => {
    const [markerPosition, setMarkerPosition] = useState<[number, number]>([19.4326, -99.1332]);
    const [address, setAddress] = useState<Address | null>(null);
    const markerRef = useRef<L.Marker>(null);

    const fetchAddress = async (lat: number, lon: number) => {
        try {
            const response = await fetch(
                `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lon}`
            );
            if (!response.ok) throw new Error("Error en la geocodificación inversa");
            const data = await response.json();
            setAddress(data.address);
            setDireccion(data.address.road || "");
            setCiudad(data.address.city || data.address.town || data.address.village || "");
            setEstado(data.address.state || "");
            setPais(data.address.country || "");
            setLat(lat);
            setLng(lon);
        } catch (error) {
            console.error("Error obteniendo dirección:", error);
        }
    };

    useEffect(() => {
        // Cuando cambia el marcador, actualizar la dirección
        const [lat, lon] = markerPosition;
        fetchAddress(lat, lon);
    }, [markerPosition]);

    const handleDragEnd = () => {
        const marker = markerRef.current;
        if (marker != null) {
            const latLng = marker.getLatLng();
            setMarkerPosition([latLng.lat, latLng.lng]);
        }
    };

    return (
        <div className="h-96 w-full my-5 rounded-md overflow-hidden">
            <MapContainer
                center={markerPosition}
                zoom={13}
                scrollWheelZoom={true}
                className="h-full w-full"
            >
                <TileLayer
                    attribution='&copy; <a href="https://osm.org/copyright">OpenStreetMap</a>'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                <Marker
                    position={markerPosition}
                    draggable={true}
                    eventHandlers={{
                        dragend: handleDragEnd
                    }}
                    ref={markerRef}
                >
                    <Popup>
                        {address
                            ? `${address.road || ""}, ${address.city || address.town || address.village || ""}, ${address.state || ""}, ${address.country || ""}`
                            : "Arrastra el marcador para seleccionar ubicación"}
                    </Popup>
                </Marker>
            </MapContainer>
            <div className="mt-2 font-fjalla">
                <p><strong>Dirección:</strong> {address?.road || "No especificada"}</p>
                <p><strong>Ciudad:</strong> {address?.city || address?.town || address?.village || "No especificada"}</p>
                <p><strong>Estado:</strong> {address?.state || "No especificado"}</p>
                <p><strong>País:</strong> {address?.country || "No especificado"}</p>
            </div>
        </div>
    );
};

export default MapaLeaftlet;
