package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "rutaUsuario")
public class RutaUsuario {

	@Id
	private String id;
	@Field("usuario_id")
	private String usuarioId;
	@Field("ruta_id")
	private String rutaId;
	private int puntuacion;
	private boolean activo;
	private float lat;
	private float lng;

	public String getId() {
		return id;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public String getRutaId() {
		return rutaId;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public boolean isActivo() {
		return activo;
	}
	public float getLat() {
		return lat;
	}

	public float getLng() {
		return lng;
	}



	public void setId(String id) {
		this.id = id;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public void setRutaId(String rutaId) {
		this.rutaId = rutaId;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public RutaUsuario(){

	}


	public RutaUsuario(String id, String usuarioId, String rutaId, int puntuacion, boolean activo, float lat, float lng) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.rutaId = rutaId;
		this.puntuacion = puntuacion;
		this.activo = activo;
		this.lat = lat;
		this.lng = lng;
	}

}
