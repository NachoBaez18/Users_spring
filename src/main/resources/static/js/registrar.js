// Call the dataTables jQuery plugin
$(document).ready(function() {


});

async function registrarUsuario(){

let datos ={};

datos.nombre = document.getElementById('txtnombre').value;
datos.apellido = document.getElementById('txtapellido').value;
datos.email = document.getElementById('txtemail').value;
datos.password = document.getElementById('txtpassword').value;

let repetirPassword = document.getElementById('txtrepetirPassword').value;

if(repetirPassword != datos.password){
alert('La contrasena que escribste es diferente')
return;
}

  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos),
  });

alert("La cuenta fue creada exitosamente");
window.location.href = "login.html"
}


