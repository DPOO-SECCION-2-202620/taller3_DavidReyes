package uniandes.dpoo.aerolinea.modelo;

public class Avion {
	
	//atributos
	private String nombre;
	private int capacidad;

	
	//constructor
	public Avion(String nombre, int capacidad) {
		this.nombre = nombre;
		this.capacidad = capacidad;
	}

	
	//metodos
	public String getNombre() {
		return nombre;
	}


	public int getCapacidad() {
		return capacidad;
	}

	
	

}
