package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Usuarios;
import com.example.demo.repository.UsuarioRepository;


@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**************************************************/
	/******************** INSERTAR ********************/
	/**************************************************/

	@PostMapping("/add")
	public void insertarUsuario(@RequestBody Usuarios nuevoUsuario) {
		usuarioRepository.save(nuevoUsuario);
	}

	/**************************************************/
	/********************** LEER **********************/
	/**************************************************/
	//obtener todos los usuarios
	@GetMapping("/getAll")
	public List<Usuarios> getUsuarios() {
		List<Usuarios> listaUsuarios = usuarioRepository.findAll();
		return listaUsuarios;
	}
	//obtener usuario por su id
	@GetMapping("/getId/{id}")
	public Usuarios getById(@PathVariable String id){

		return usuarioRepository.findById(id).orElse(null);
	}

	//lo mismo que el getById, pero en vez de un optional, devuelve un list
	@GetMapping("/getAllId/{id}")
	public List<Usuarios> getAllById(@PathVariable String id) {
		return usuarioRepository.findUsuarioById(id);
	}
	//obtener usuario por su campo 'usuario' (el nick)
	@GetMapping("/getUsuario/{usuario}")
	public List<Usuarios> getUsuariosByUsuario(@PathVariable String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	//para hacer el login, pasamos los parametros nick, contraseña y rol 
	@GetMapping("/getLogin/{usuario}/{contrasena}/{rol}")
	public List<Usuarios> getUsuariosByUsuarioContrasenaRol(@PathVariable String usuario, @PathVariable String contrasena, @PathVariable String rol) {
		return usuarioRepository.findUsuarioByUsuarioAndContrasenaAndRol(usuario,contrasena,rol);
	}

	/**************************************************/
	/******************* MODIFICAR ********************/
	/**************************************************/
	//editar la contraseña de un usuario por su id
	@PutMapping("/editContrasenaId/{id}/{contrasena}")
	public void updateContrasenaById(@PathVariable String id, @PathVariable String contrasena){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("contrasena", contrasena);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}

	//modificar usuario por su id
	@PutMapping("/editUsuario/{id}")
	public void editUsuarioId(@PathVariable String id, @RequestBody Usuarios nuevoUsuario) {

		List<Usuarios> listaUsuarios = usuarioRepository.findAll();
		for (Usuarios usuario : listaUsuarios) {
			if(usuario.getId().equals(id)){
				usuario.setUsuario(nuevoUsuario.getUsuario());
				usuario.setContrasena(nuevoUsuario.getContrasena());
				usuario.setNombre(nuevoUsuario.getNombre());
				usuario.setApellidos(nuevoUsuario.getApellidos());
				usuario.setAvatar(nuevoUsuario.getAvatar());
			}
		}
		usuarioRepository.saveAll(listaUsuarios);
	 }

	//conectar usuario, cambiar el estado del atributo 'conectado' a true para saber que esta en partida
	@PutMapping("/conectarUsuario/{id}")
	public void conectarUsuario(@PathVariable String id){
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("conectado", true);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}
	//desconectar usuario, cambiar el estado del atributo 'conectado' a false
	@PutMapping("/desconectarUsuario/{id}")
	public void desconectarUsuario(@PathVariable String id){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("conectado", false);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}


	/**************************************************/
	/********************* BORRAR *********************/
	/**************************************************/
	//eliminar un usuario por su id
	@DeleteMapping("/deleteId/{id}")
	public void delete(@PathVariable String id) {
		usuarioRepository.deleteById(id);
	}
	//eliminar el usuario con un nick x
	@DeleteMapping("/deleteUsername/{username}")
	public void deleteByUsername(@PathVariable String username) {
		usuarioRepository.deleteByUsuario(username);
	}

	//si te cansas de la vida y quieres borrar todo
	@DeleteMapping("/deleteAll")
	public void deleteUsuarios(){
		usuarioRepository.deleteAll();
	}

}


