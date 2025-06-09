document.addEventListener("DOMContentLoaded", () => {
    confirmacionCuenta();
    async function confirmacionCuenta() {
        try {
            const path = window.location.pathname;
            const pathParths = path.split("/");
            const token = pathParths[2];
            const csrfToken = document.querySelector("#_csrf").value;

            const response = await axios.post(`/register/confirmar-cuenta/${token}`, {}, {
                headers: {
                    "X-CSRF-TOKEN": csrfToken
                }
            });
            if (response.status === 200) {
                Swal.fire({
                    title: "Cuenta Confirmada",
                    text: "Tu cuenta ha sido confirmada",
                    icon: "success",
                    textConfirmButton: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "/crear-cuenta";
                    }
                })
            }
        } catch (e) {
            Swal.fire({
                title: "Token Invalido",
                text: "El token se encuentra corrupto",
                icon: "error",
                textConfirmButton: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "/crear-cuenta";
                }
            });
            setTimeout(() => {
                window.location.href = "/crear-cuenta";
            }, 1500)
        }
    }
})