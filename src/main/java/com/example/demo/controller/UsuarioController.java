package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

	@GetMapping("/getAll")
	public List<Usuarios> getUsuarios() {
		List<Usuarios> listaUsuarios = usuarioRepository.findAll();
		return listaUsuarios;
	}

	@GetMapping("/getId/{id}")
	public Optional<Usuarios> getById(@PathVariable String id){

		return usuarioRepository.findById(id);
	}

	//lo mismo que el getById, pero en vez de un optional, devuelve un list
	@GetMapping("/getAllId/{id}")
	public List<Usuarios> getAllById(@PathVariable String id) {
		return usuarioRepository.findUsuarioById(id);
	}

	@GetMapping("/getUsuario/{usuario}")
	public List<Usuarios> getUsuariosByUsuario(@PathVariable String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	//para hacer el login
	@GetMapping("/getLogin/{usuario}/{contraseña}/{rol}")
	public List<Usuarios> getUsuariosByUsuarioContraseñaRol(@PathVariable String usuario, @PathVariable String contraseña, @PathVariable String rol) {
		return usuarioRepository.findUsuarioByUsuarioAndContraseñaAndRol(usuario,contraseña,rol);
	}

	/**************************************************/
	/******************* MODIFICAR ********************/
	/**************************************************/

	@PutMapping("/editContraseñaId/{id}/{contraseña}")
	public void updateContraseñaById(@PathVariable String id, @PathVariable String contraseña){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("contraseña", contraseña);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}

	/*DUDAS DE SI ES ASI*/
	@PutMapping("/editUsuario/{id}")
	public void bajarSalario(@PathVariable String id, @RequestBody Usuarios nuevoUsuario) {

		List<Usuarios> listaUsuarios = usuarioRepository.findAll();
		for (Usuarios usuario : listaUsuarios) {
			if(usuario.getId().equals(id)){
				usuario.setUsuario(nuevoUsuario.getUsuario());
				usuario.setContraseña(nuevoUsuario.getContraseña());
				usuario.setNombre(nuevoUsuario.getNombre());
				usuario.setApellidos(nuevoUsuario.getApellidos());
				usuario.setAvatar(nuevoUsuario.getAvatar());
			}
		}
		usuarioRepository.saveAll(listaUsuarios);
	 }


	@PutMapping("/conectarUsuario/{id}")
	public void conectarUsuario(@PathVariable String id){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("conectado", true);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}

	@PutMapping("/desconectarUsuario/{id}")
	public void desconectarUsuario(@PathVariable String id){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("conectado", false);
		mongoTemplate.updateFirst(query, update, Usuarios.class);

	}


	/**************************************************/
	/********************* BORRAR *********************/
	/**************************************************/

	@DeleteMapping("/deleteId/{id}")
	public void delete(@PathVariable String id) {
		usuarioRepository.deleteById(id);
	}

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


