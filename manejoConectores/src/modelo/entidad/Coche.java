package modelo.entidad;

public class Coche {
	
	
		private int id;
		private String marca;
		private String modelo;
		private String año;
		private double km;
		
		
		//GETTERS Y SETTERS
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getAño() {
			return año;
		}
		public void setAño(String año) {
			this.año = año;
		}
		public double getKm() {
			return km;
		}
		public void setKm(double km) {
			this.km = km;
		}
		
		//Creo el ToString para listar variables
		@Override
		public String toString() {
			return "[id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", km=" + km + "]";
		}
		
	}
	


	



