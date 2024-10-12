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

function validar(msgErrorEtiquetaId) {
    let validado = true;
    let usuarioV = "usuario123";
    let claveV = "clave123";

    let msgs = document.querySelectorAll("small[id^='msg']");

    for (let mensaje of msgs) {
        mensaje.innerHTML = '';
    }

    let usuario = document.getElementById("usuarioLogin"); 
    let clave = document.getElementById("claveLogin");

    if(!validarVacio(usuario, "Usuario no puede estar vacio", msgErrorEtiquetaId)) { return false; }
    if(!validarVacio(clave, "Clave no puede estar vacia", msgErrorEtiquetaId)) { return false; }

    let msgError = document.querySelector(msgErrorEtiquetaId);

    if (usuario.value == '' && clave.value == '') {
        msgError.innerHTML += "Ingrese sus credenciales. <br/>";
        validado = false;
    }

    /*else if (usuario.value !== usuarioV) {
        msgError.innerHTML += "Usuario incorrecto. <br/>";
        validado = false;
    }

    else if (clave.value !== claveV) {
        msgError.innerHTML += "Contraseña incorrecta. <br/>";
        validado = false;
    }*/
    
    return validado;
}

function validarVacio(valor, mensajeError, etiquetaId) {
    let msgError = document.querySelector(etiquetaId);

    if(valor.value === '') {
        msgError.innerHTML += mensajeError;
        return false;
    }

    return true;
}
