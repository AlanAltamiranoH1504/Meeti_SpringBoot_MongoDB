document.addEventListener("DOMContentLoaded", () => {
    const formIniciarSesion = document.querySelector("#formIniciarSesion");

    formIniciarSesion.addEventListener("submit", iniciarSesionPeticion);

    async function iniciarSesionPeticion(e) {
        e.preventDefault();
        try {
            const inputEmail = document.querySelector("#email").value;
            const inputPassword = document.querySelector("#password").value;
            const requestBody = {
                email: inputEmail,
                password: inputPassword
            }

            const response = await axios.post("/login", requestBody, {
                headers: {
                    "X-CSRF-TOKEN": document.querySelector("#_csrf").value
                }
            });
            if (response.status === 200){
                window.location.href = "/";
            }
        }catch (e) {
            if (e.response.status === 401){
                Swal.fire({
                    title: "¡ERROR!",
                    text: e.response.data.message,
                    icon: "error",
                    timer: 3000
                });
            } else {
                Swal.fire({
                    title: "¡ERROR!",
                    text: "Verifica los errores",
                    icon: "error",
                    timer: 3000
                });
                mostrarErrores(e.response.data);
            }
        }
    }

    function mostrarErrores(errores) {
        const divErrores = document.querySelector("#errores");
        const erroresArray = Object.values(errores);
        erroresArray.forEach((error) => {
            const parrafoError = document.createElement("p");
            parrafoError.textContent = error;
            parrafoError.classList.add("text-center", "rounded-lg", "bg-red-400", "py-2", "m-2", "font-bold", "border")
            divErrores.appendChild(parrafoError);
        });
        setTimeout(() => {
            divErrores.innerHTML = "";
        }, 4000)
    }
})