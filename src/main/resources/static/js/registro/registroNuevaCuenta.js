document.addEventListener("DOMContentLoaded", () => {
    const formularioRegistroCuenta = document.querySelector("#formularioRegistroCuenta");
    formularioRegistroCuenta.addEventListener("submit", registroNuevaCuenta);

    function registroNuevaCuenta(e) {
        e.preventDefault();
        const inputNombre = document.querySelector("#nombre").value;
        const inputApellidos = document.querySelector("#apellidos").value;
        const inputUserName = document.querySelector("#username").value;
        const inputEmail = document.querySelector("#email").value;
        const inputPassword = document.querySelector("#password").value;
        const inputPassword2 = document.querySelector("#password2").value;

        if (inputPassword.trim() !== inputPassword2.trim()){
            Swal.fire({
                title: "Contraseñas Diferentes",
                text: "Las contraseñas no coinciden.",
                icon: "error",
                timer: 3000
            });
            return;
        }
        const requestBody = {
            nombre: inputNombre,
            apellidos: inputApellidos,
            username: inputUserName,
            email: inputEmail,
            password: inputPassword
        }
        peticionSaveNuevaCuenta(requestBody);
    }

    async function peticionSaveNuevaCuenta(requestBody){
        try {
            const token = document.querySelector("#_csrf").value;
            const response = await axios.post("register/crear-cuenta", requestBody, {
                headers: {
                    "X-CSRF-TOKEN": token
                }
            });
            if (response.status === 201){
                Swal.fire({
                    title: "¡Usuario Registrado!",
                    text: "La cuenta fue registrada de manera correcta. Confírmala",
                    icon: "success",
                    textConfirmButton: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed){
                        console.log("Redireccion");
                    }
                })
            }
        }catch (e) {
            Swal.fire({
                title: "¡Error en registro de Usuario!",
                text: "Consulta los errores.",
                icon: "error",
                timer: 3000
            });
            mostrarAlertas(e.response.data);
        }
    }

    function mostrarAlertas(errores){
        const erroresArray = Object.values(errores);
        const divErrores = document.querySelector("#divErrores");

        erroresArray.forEach((error) => {
            const parrafoError = document.createElement("p");
            parrafoError.textContent = error;
            parrafoError.classList.add("border", "rounded-lg", "bg-red-500", "p-2", "text-center", "font-bold");

            divErrores.appendChild(parrafoError);
        });

        setTimeout(() => {
            divErrores.innerHTML = "";
        }, 5000);
    }
})