package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuaioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuarios/{id}",method = RequestMethod.GET)
    public Usuario getUsuario (@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Nacho");
        usuario.setApellido("Baez");
        usuario.setEmail("baez@gmail.com");
        usuario.setTelefono("09998888");
        usuario.setPassword("12345");

        return usuario;
    }

    @RequestMapping(value = "api/usuarios",method = RequestMethod.GET)
    public List<Usuario> getUsuarios (@RequestHeader(value = "Authorization")String token){

        if(!validarToken(token)){return null;}
        return usuaioDao.getUsuarios();

    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
         return usuarioId != null;
    }


    @RequestMapping(value = "usuariokkj")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Nacho");
        usuario.setApellido("Baez");
        usuario.setEmail("baez@gmail.com");
        usuario.setTelefono("09998888");
        usuario.setPassword("12345");

        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.DELETE)
    public void elimiar(@RequestHeader(value = "Authorization")String token
                        ,@PathVariable Long id){
        if(!validarToken(token)){return;}
        usuaioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios",method = RequestMethod.POST)
    public void registrar(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
      String hash =  argon2.hash(1,1024,1,usuario.getPassword());
      usuario.setPassword(hash);
         usuaioDao.registrar(usuario);

    }



}



