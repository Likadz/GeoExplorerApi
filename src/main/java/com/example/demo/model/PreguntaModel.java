package com.example.demo.model;

//el objeto de pregunta en el modelo de localizaciones
public class PreguntaModel {
	
		
		private String pregunta;
		private String respuesta1;
		private String respuesta2;
		private String respuesta3;
		private int correcta;
		private String imagen;
		private String tipo;
	
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getPregunta() {
			return pregunta;
		}
		public String getRespuesta1() {
			return respuesta1;
		}
		public String getRespuesta2() {
			return respuesta2;
		}
		public String getRespuesta3() {
			return respuesta3;
		}
		public int getCorrecta() {
			return correcta;
		}
		public String getImagen() {
			return imagen;
		}
	
		public void setPregunta(String pregunta) {
			this.pregunta = pregunta;
		}
		public void setRespuesta1(String respuesta1) {
			this.respuesta1 = respuesta1;
		}
		public void setRespuesta2(String respuesta2) {
			this.respuesta2 = respuesta2;
		}
		public void setRespuesta3(String respuesta3) {
			this.respuesta3 = respuesta3;
		}
		public void setCorrecta(int correcta) {
			this.correcta = correcta;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
	
		 public PreguntaModel() {
			// TODO Auto-generated constructor stub
		}

		public PreguntaModel(String pregunta, String respuesta1, String respuesta2, String respuesta3, int correcta,
				String imagen, String tipo) {
			
			this.pregunta = pregunta;
			this.respuesta1 = respuesta1;
			this.respuesta2 = respuesta2;
			this.respuesta3 = respuesta3;
			this.correcta = correcta;
			this.imagen = imagen;
			this.tipo=tipo;
		}



}


