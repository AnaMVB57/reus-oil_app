// alert("Integrado con JS");

document.addEventListener('DOMContentLoaded', function () {
    const container = document.getElementById('container');
    const registerBtn = document.getElementById('register');
    const loginBtn = document.getElementById('login');

    registerBtn.addEventListener('click', () => {
        container.classList.add("active");
    });

    loginBtn.addEventListener('click', () => {
        container.classList.remove("active");
    });
});


function validar() {
    let validado = true;
    let usuarioV = "usuario123";
    let claveV = "clave123";

    let msgs = document.querySelectorAll("small[id^='msg']");

    for (let mensaje of msgs) {
        mensaje.innerHTML = '';
    }

    let usuario = document.getElementById("usuarioLogin"); 
    let clave = document.getElementById("claveLogin");

    let msgError = document.querySelector("#msgError");

    if (usuario.value == '' && clave.value == '') {
        msgError.innerHTML += "Ingrese sus credenciales. <br/>";
        validado = false;
    }

    else if (usuario.value !== usuarioV) {
        msgError.innerHTML += "Usuario incorrecto. <br/>";
        validado = false;
    }

    else if (clave.value !== claveV) {
        msgError.innerHTML += "Contraseña incorrecta. <br/>";
        validado = false;
    }
    
    return validado;
}
