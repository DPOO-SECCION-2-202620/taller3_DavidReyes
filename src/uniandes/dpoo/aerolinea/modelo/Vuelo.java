package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;


public class Vuelo {
	
	//atributos 
	private Ruta ruta;
	private String fecha; 
	private Avion avion;
	private Map<String, Tiquete> tiquetes;
	
	//constructor
	public Vuelo(Ruta ruta,String fecha, Avion avion) {
		this.avion = avion;
		this.fecha = fecha;
		this.ruta = ruta;
		this.tiquetes = new HashMap<String, Tiquete>();
	}

	public Avion getAvion() {
		return avion;
	}

	public String getFecha() {
		return fecha;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public Collection<Tiquete> getTiquetes() {
		
		return new ArrayList<Tiquete>(tiquetes.values());
	}
	
	/**
	 * Vende una cantidad determinada de tiquetes para el vuelo y los deja registrados en el mapa de tiquetes
	 * @param cliente
	 * @param calculadora
	 * @param cantidad
	 * @return El valor total de los tiquetes vendidos
	 * @throws VueloSobrevendidoException
	 */
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
		
		//verificar puestos disponibles
		int capacidadAvion = avion.getCapacidad();
		int sillasVendidas = tiquetes.size();
		int puestosDisponibles = capacidadAvion - sillasVendidas;
		
		if (cantidad > puestosDisponibles) {
			throw new VueloSobrevendidoException(this);
			
		} else {
			
			int tarifa = calculadora.calcularTarifa(this, cliente);
			
			//crear tiquetes y registralos
			for(int i =0; i < cantidad ; i++) {
				Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
				tiquetes.put(tiquete.getCodigo(), tiquete);
				GeneradorTiquetes.registrarTiquete(tiquete);	
			}
			
			return tarifa * cantidad;
		}

		
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj == null)
	    {
	        return false;
	    }

	    if (!(obj instanceof Vuelo))
	    {
	        return false;
	    }

	    Vuelo otroVuelo = (Vuelo) obj;

	    return fecha.equals(otroVuelo.getFecha())
	            && ruta.getCodigoRuta().equals(
	                    otroVuelo.getRuta().getCodigoRuta()
	               );
		
	}

}
