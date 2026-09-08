package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	
	//atributos
	private Cliente cliente;
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Vuelo vuelo;
	
	//metodos
	//ctor
	public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
		cliente = clienteComprador; 
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.usado = false;
		this.vuelo = vuelo;
		
		clienteComprador.agregarTiquete(this);
	}
	
	//getters
	public Cliente getCliente() {
		return cliente;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}
	
	//metodos implemnetacion
	
	public void marcarComoUsado() {
		usado = true;
	}
	
	public boolean esUsado() {
		return usado;
	}
	

}
