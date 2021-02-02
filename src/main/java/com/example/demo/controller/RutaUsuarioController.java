 
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RutaUsuario;
import com.example.demo.repository.RutaUsuarioRepository;

@RequestMapping("/rutaUsuario")
@RestController
public class RutaUsuarioController {

	@Autowired
	private RutaUsuarioRepository rutaUsuarioRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**************************************************/
	/******************** INSERTAR ********************/
	/**************************************************/
	//crear rutaUsuario
	@PostMapping("/add")
	public RutaUsuario insertarUsuario(@RequestBody RutaUsuario nuevaRutaUsuario) {
		return rutaUsuarioRepository.save(nuevaRutaUsuario);
	}

	/**************************************************/
	/********************** LEER **********************/
	/**************************************************/
	//obtener todos los datos
	@GetMapping("/getAll")
	public List<RutaUsuario> getRutaUsuario() {
		List<RutaUsuario> listaRutasUsuarios = rutaUsuarioRepository.findAll();
		return listaRutasUsuarios;
	}
	//obtener las rutasUsuario por id
	@GetMapping("/getId/{id}")
	public RutaUsuario getById(@PathVariable String id){
		return rutaUsuarioRepository.findById(id).orElse(null);
	}

	//lo mismo que el getById, pero en vez de un optional, devuelve un list
	@GetMapping("/getAllById/{id}")
	public List<RutaUsuario> getAllById(@PathVariable String id) {
		return rutaUsuarioRepository.findRutaUsuarioById(id);
	}


	/*ranking de una ruta por la id de la RUTA*/
	@GetMapping("/getAllByRutaId/{id}")
	public List<RutaUsuario> getAllByRutaId(@PathVariable String id) {
		return rutaUsuarioRepository.findRutaUsuarioByRutaIdOrderByPuntuacionDesc(id);
	}

	/**************************************************/
	/******************* MODIFICAR ********************/
	/**************************************************/

	//editar la puntuacion de un usuario-ruta
	@PutMapping("/editPuntuacion/{id}/{puntuacion}")
	public void updatePuntuacion(@PathVariable String id, @PathVariable int puntuacion) {

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().inc("puntuacion", puntuacion);
		mongoTemplate.updateFirst(query, update, RutaUsuario.class);

	 }

	//editar si la partida está activa
	@PutMapping("/editRutaUsuarioActivar/{id}")
	public void conectarUsuario(@PathVariable String id){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("activo", true);
		mongoTemplate.updateFirst(query, update, RutaUsuario.class);

	}
	//editar si la partida ha acabado
	@PutMapping("/editRutaUsuarioDesactivar/{id}")
	public void desconectarUsuario(@PathVariable String id){

		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("activo", false);
		mongoTemplate.updateFirst(query, update, RutaUsuario.class);

	}

	//editar posicion usuario
		@PutMapping("/editRutaPosicion/{id}/{lat}/{lng}")
		public void editarLat(@PathVariable String id, @PathVariable Float lat,@PathVariable Float lng){

			Query query = new Query(Criteria.where("id").is(id));
			Update update = new Update().set("lat", lat).set("lng", lng);
			mongoTemplate.updateFirst(query, update, RutaUsuario.class);

		}

	/**************************************************/
	/********************* BORRAR *********************/
	/**************************************************/
	//eliminar por id
	@DeleteMapping("/deleteId/{id}")
	public void delete(@PathVariable String id) {
		rutaUsuarioRepository.deleteById(id);
	}
	//eliminar por id del usuario
	@DeleteMapping("/deleteUsuarioId/{id}")
	public void deleteByUsuarioId(@PathVariable String id) {
		rutaUsuarioRepository.deleteByUsuarioId(id);
	}
	//eliminar por id de la ruta
	@DeleteMapping("/deleteRutaId/{id}")
	public void deleteByRutaId(@PathVariable String id) {
		rutaUsuarioRepository.deleteByRutaId(id);
	}

	//borrar todo
	@DeleteMapping("/deleteAll")
	public void deleteRutaUsuarios(){
		rutaUsuarioRepository.deleteAll();
	}

}





