package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Rutas;
import com.example.demo.repository.LocalizacionRepository;
import com.example.demo.repository.RutaRepository;

@RequestMapping("/rutas")
@RestController
public class RutaController {

	@Autowired
	private RutaRepository rutaRepository;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private LocalizacionRepository localizacionRepository;


	/***********************CREAR***********************/

	@PostMapping("/nuevo")
	public void insertarRuta(@RequestBody Rutas nuevaRuta) {
		rutaRepository.save(nuevaRuta);
	}

	/***********************LEER***********************/

	@GetMapping("/leer")
	public List<Rutas> leerRutas() {
		return rutaRepository.findAll();
	}

	@GetMapping("/leer/{id}")
	public Optional<Rutas> leerRutaId(@PathVariable String id) {
		return rutaRepository.findById(id);
	}

	@GetMapping("/leer/{nombre}")
	public List<Rutas> leerRutaNombre(@PathVariable String nombre) {
		return rutaRepository.findByNombre(nombre);
	}
	
	@GetMapping("/leer/ciudad/{ciudad}")
	public List<Rutas> leerRutaCiudad(@PathVariable String ciudad) {
		return rutaRepository.findRutaByCiudad(ciudad);
	}

	/***********************EDITAR***********************/

	@PutMapping("/editar/{id}")
	public List<Rutas> editarRutaId(@PathVariable String id, @RequestBody Rutas ruta){
		List<Rutas> rutas = rutaRepository.findAllById(id);
		for (Rutas ruta1 : rutas){
			ruta1.setNombre(ruta.getNombre());
			ruta1.setCiudad(ruta.getCiudad());
			ruta1.setTematica(ruta.getTematica());
			ruta1.setDuracion(ruta.getDuracion());
			ruta1.setDescripcion(ruta.getDescripcion());
			ruta1.setTransporte(ruta.getTransporte());
			ruta1.setImagen(ruta.getImagen());
			ruta1.setDificultad(ruta.getDificultad());
			ruta1.setListaLocalizaciones(ruta.getListaLocalizaciones());
		}
		return rutaRepository.saveAll(rutas);
	}

	@PutMapping("/editar/{nombre}")
	public List<Rutas> editarRutaNombre(@PathVariable String nombre, @RequestBody Rutas ruta){
		List<Rutas> rutas = rutaRepository.findByNombre(nombre);
		for (Rutas ruta1 : rutas){
			ruta1.setNombre(ruta.getNombre());
			ruta1.setCiudad(ruta.getCiudad());
			ruta1.setTematica(ruta.getTematica());
			ruta1.setDuracion(ruta.getDuracion());
			ruta1.setDescripcion(ruta.getDescripcion());
			ruta1.setTransporte(ruta.getTransporte());
			ruta1.setImagen(ruta.getImagen());
			ruta1.setDificultad(ruta.getDificultad());
			ruta1.setListaLocalizaciones(ruta.getListaLocalizaciones());
		}
		return rutaRepository.saveAll(rutas);
	}

	/***********************ELIMINAR***********************/

	@DeleteMapping("/eliminar")
	public void eliminarRuta(@RequestBody Rutas ruta) {
		rutaRepository.deleteAll();
	}

	@DeleteMapping("/eliminarPorId/{id}")
	public void eliminarRutaId(@PathVariable String id) {
		//primero tiene que eliminar las localizaciones
		localizacionRepository.deleteByRutaId(id);
		//eliminamos la ruta
		rutaRepository.deleteById(id);
	}

	@DeleteMapping("/eliminar/{nombre}")
	public void eliminarRutaNombre(@PathVariable String nombre) {
		rutaRepository.deleteByNombre(nombre);
	}


}