document.addEventListener("DOMContentLoaded", () => {
    const path = window.location.pathname;
    const pathParts = path.split("/");
    const token = pathParts[2];

    const formularioConfirmacionCuenta = document.querySelector("#formularioConfirmacionCuenta");
    formularioConfirmacionCuenta.addEventListener("submit", confirmacionCuenta);

    async function confirmacionCuenta(e) {
        e.preventDefault();
        try {
            const response = await axios.post(`/register/confirmar-cuenta`, {
                token: token
            });
            if (response.status === 200){
                Swal.fire({
                    title: "¡Confirmacion Correcta!",
                    text: "Ahora puedes empezar a crear grupos",
                    icon: "success",
                    textConfirmButton: "Aceptar"
                })
                    // .then((result) => {
                    //     if (result.isConfirmed){
                    //         window.location.href = "/crear-cuenta";
                    //     }
                    // })

                console.log("Confirmando")
            }
        }catch (e) {
            console.log("Error en confirmacion")
        }
    }
})