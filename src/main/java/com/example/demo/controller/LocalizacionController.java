package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Localizaciones;
import com.example.demo.model.RutaUsuario;
import com.example.demo.model.Rutas;
import com.example.demo.repository.LocalizacionRepository;
import com.example.demo.repository.RutaRepository;


@RequestMapping("/localizaciones")
@RestController
public class LocalizacionController {

	@Autowired
	private LocalizacionRepository localizacionRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RutaRepository rutaRepository;

	/***********************************************************
	 ********************** CREATE *****************************
	 ***********************************************************/
	//Crear una localización nueva
	@PostMapping("/add")
	public void insertarLocalizacion(@RequestBody Localizaciones nuevaLocalizacion) {
		localizacionRepository.save(nuevaLocalizacion);
		//addRuta(nuevaLocalizacion);
	}

	/*public void addRuta(Localizaciones nuevaLocalizacion){
		Rutas ruta = rutaRepository.findById(nuevaLocalizacion.getRutaId()).orElse(null);
		List<Localizaciones>lista=ruta.getListaLocalizaciones();
		lista.add(nuevaLocalizacion);
		ruta.setListaLocalizaciones(lista);
		rutaRepository.save(ruta);
	}*/
	/***********************************************************
	 ********************** READ *******************************
	 ***********************************************************/
	//recoger todas las localizaciones
	@GetMapping("/getAll")
	public List<Localizaciones> allLocalizaciones(){
		return localizacionRepository.findAll();
	}

	//recoger localizaciones por nombre
	@GetMapping("/getNombre/{nombre}")
	public List<Localizaciones> getByName(@PathVariable String nombre) {
		return localizacionRepository.findByNombre(nombre);
	}
	//recoger localizaciones por su id
	@GetMapping("/getId/{id}")
    public Localizaciones getById(@PathVariable String id) {
      return localizacionRepository.findById(id).orElse(null);
    }
	//todos ordenados por el nombre
	@GetMapping("/todosEnOrden")
	 public List<Localizaciones> getLocalizacinoesOrdenados() {
		List<Localizaciones> listaLocalizaciones = localizacionRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
	    return listaLocalizaciones;
	 }

	//recoger localizaciones por id ruta (todas las localizaciones de una ruta)
	@GetMapping("/getIdRuta/{rutaid}")
    public List<Localizaciones> getByIdRuta(@PathVariable String rutaid) {
		List<Localizaciones> listaLocalizaciones = localizacionRepository.findByRutaId(rutaid);
		return listaLocalizaciones;
    }

	/***********************************************************
	 ********************** UPDATE ******************************
	 ***********************************************************/
	//Editar una localización
	//editar por id
	@PutMapping("/editId/{id}")
	public Localizaciones editarPorId(@PathVariable String id, @RequestBody Localizaciones nuevaLocalizacion) {
		Localizaciones localizacion=localizacionRepository.findById(id).orElse(null);
		localizacion.setNombre(nuevaLocalizacion.getNombre());
		localizacion.setImagen_pista(nuevaLocalizacion.getImagen_pista());
		localizacion.setLatitud(nuevaLocalizacion.getLatitud());
		localizacion.setLongitud(nuevaLocalizacion.getLongitud());
		localizacion.setOculta(nuevaLocalizacion.isOculta());
		localizacion.setPista(nuevaLocalizacion.getPista());
		localizacion.setPregunta(nuevaLocalizacion.getPregunta());
		localizacion.setRutaId(nuevaLocalizacion.getRutaId());
		return localizacionRepository.save(localizacion);

	}

	//editar por nombre
	@PutMapping("/editNombre/{nombre}")
	public Localizaciones editarPorNombre(@PathVariable String nombre, @RequestBody Localizaciones nuevaLocalizacion) {

		Localizaciones localizacion=(Localizaciones) localizacionRepository.findByNombre(nombre);
		localizacion.setNombre(nuevaLocalizacion.getNombre());
		localizacion.setImagen_pista(nuevaLocalizacion.getImagen_pista());
		localizacion.setLatitud(nuevaLocalizacion.getLatitud());
		localizacion.setLongitud(nuevaLocalizacion.getLongitud());
		localizacion.setOculta(nuevaLocalizacion.isOculta());
		localizacion.setPista(nuevaLocalizacion.getPista());
		localizacion.setPregunta(nuevaLocalizacion.getPregunta());
		localizacion.setRutaId(nuevaLocalizacion.getRutaId());
		return localizacionRepository.save(localizacion);
	}

	/***********************************************************
	 ********************** DELETE *****************************
	 ***********************************************************/
	//Eliminar TODAS las localizaciones
	 @DeleteMapping("/deleteAll") void deleteAll() {
		localizacionRepository.deleteAll();
	}
	//Eliminar UNA localizacion por su id
	@DeleteMapping("/deleteId/{id}") void deleteLocalizacionId(@PathVariable String id) {
		localizacionRepository.deleteById(id);
	}
	//Eliminar localización por nombre
	@DeleteMapping("/deleteNombre/{nombre}") void deleteLocalizacionNombre(@PathVariable String nombre) {
		localizacionRepository.deleteByNombre(nombre);
	}

}
