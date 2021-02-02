package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "localizaciones")
public class Localizaciones {

	@Id
	private String id;
	private String nombre;
	private float latitud;
	private float longitud;
	private boolean oculta;
	private String pista;
	private String imagenPista;
	private String rutaId;
	private PreguntaModel pregunta;//unico objeto ya que no nos da tiempo a implementar

	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public float getLatitud() {
		return latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public boolean isOculta() {
		return oculta;
	}
	public String getPista() {
		return pista;
	}
	public String getImagen_pista() {
		return imagenPista;
	}
	public String getRutaId() {
		return rutaId;
	}
	public PreguntaModel getPregunta() {
		return pregunta;
	}


	public void setId(String id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
	public void setOculta(boolean oculta) {
		this.oculta = oculta;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	public void setImagen_pista(String imagenPista) {
		this.imagenPista = imagenPista;
	}
	public void setRutaId(String rutaId) {
		this.rutaId = rutaId;
	}
	public void setPregunta(PreguntaModel pregunta) {
		this.pregunta = pregunta;
	}

	public Localizaciones() {

	}

	public Localizaciones(String id, String nombre, float latitud, float longitud, boolean oculta, String pista, String imagenPista, String rutaId, PreguntaModel pregunta) {
		this.id = id;
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.oculta = oculta;
		this.pista = pista;
		this.imagenPista = imagenPista;
		this.rutaId = rutaId;
		this.pregunta=pregunta;
	}

	@Override
	public String toString(){
		return this.getId() + " " + this.getLatitud() + " " + this.longitud + " - " + this.getNombre() +" - " + this.getRutaId() + " - " ;
	}

}
