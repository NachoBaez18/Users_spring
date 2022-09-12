// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();

let listadoHtml ='';
  for (let usuario of usuarios){

    let telefono = usuario.telefono == null ? '-':usuario.telefono
    let botonEliminar = '<a onclick="eliminarUsuario('+usuario.id+')" href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
    let usuariohtml = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+''+usuario.apellido+'</td><td>'+usuario.email+'</td><td>'+telefono+'</td><td>'+botonEliminar+'</td></tr>';

    listadoHtml += usuariohtml;
  }

  console.log(usuarios);



  document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders(){
   return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
   };
}

async function eliminarUsuario(id){


if(!confirm('Deseas eliminar el usuario')){
return
}
 const request = await fetch('api/usuarios/'+id, {
    method: 'DELETE',
    headers:getHeaders()
  });

  location.reload()

}
