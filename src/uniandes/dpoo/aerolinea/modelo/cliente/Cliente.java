package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase abstracta define e implementa los aspectos que son comunes para todos los tipos de clientes de la Aerolínea 
 * Cada cliente, sin importar su tipo, tiene una lista de tiquetes usados y sin usar.
 */

public abstract class Cliente {
	
	//atributos
	private List<Tiquete> tiquetesSinUsar;
	private List<Tiquete> tiquetesUsados;
	
	//metodos
	//ctor
	public Cliente() {
		tiquetesSinUsar = new ArrayList<Tiquete>();
		tiquetesUsados = new ArrayList<Tiquete>();
	}
	
	//metodos abstractos
	public abstract String getTipoCliente();
	
	public abstract String getIdentificador();
	
	//metodos con implementacion (no abstractos)
	/**
	 * Agrega un nuevo tiquete a la lista de tiquetes (sin usar) que ha comprado el cliente
	 * @param tiquete
	 */
	public void agregarTiquete(Tiquete tiquete) {
		
		tiquetesSinUsar.add(tiquete);
		
	}
	
	/**
	 * Calcula el valor total de los tiquetes que ha comprado un cliente
	 * @return valor total
	 */
	
	public int calcularValorTotalTiquetes() {
		
		//variable retorno
		int valorTotal;
		
		//recorrer tiquetes sin usar
		int valorTiquetesSinUsar = 0;
		for (Tiquete tiquete: tiquetesSinUsar) {
			valorTiquetesSinUsar += tiquete.getTarifa();	
		}
		
		//recorrer tiquetes usados
		int valorTiquetesUsados = 0;
		for (Tiquete tiquete: tiquetesUsados) {
			valorTiquetesUsados += tiquete.getTarifa();	
		}
		
		
		valorTotal = valorTiquetesSinUsar + valorTiquetesUsados;
		return valorTotal;
		
	}
	
	/**
	 * Marca como usados todos los tiquetes del cliente qus se hayan realizado en el vuelo que llega por parámetro,
	 *  moviéndolos de la lista de tiquetes sin usar a la lista de tiquetes usados
	 * @param vuelo
	 */
	public void usarTiquetes(Vuelo vuelo) {
		
		//obtener lista de tiquetes
		Collection<Tiquete> listaTiquetes = vuelo.getTiquetes();
		
		//eliminarlos de lista sin usar y agergar a lista usados
		for (Tiquete tiquete: listaTiquetes) {
			
			if (tiquetesSinUsar.contains(tiquete)) {
				tiquetesSinUsar.remove(tiquete);
				tiquetesUsados.add(tiquete);
				tiquete.marcarComoUsado();
			}
		}
		
	}
	
}
