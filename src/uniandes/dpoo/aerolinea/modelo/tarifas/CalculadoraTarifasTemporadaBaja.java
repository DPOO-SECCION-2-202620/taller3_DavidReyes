package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
	
	//atributos
	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;
	
	//metodos
	
	/**
	 * Calcula el costo base como COSTO_POR_KM x distancia.
	 */
	@Override
	protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		
		//determinar costo por km
		int COSTO_KM = (cliente.getTipoCliente().equals("Natural")) ? COSTO_POR_KM_NATURAL : COSTO_POR_KM_CORPORATIVO;
		int distanciaVuelo = calcularDistanciaVuelo(vuelo.getRuta());
		
		return COSTO_KM*distanciaVuelo;
	}
	
	/**
	 * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia.
	 * El método retorna un número entre 0 y 1: 0 significa que no hay descuento, y 1 significa que el descuento es del 100%.

	 */
	@Override
	protected double calcularPorcentajeDescuento(Cliente cliente) {
		double descuento = 0.0;
		
		//determinar tipo cliente
		if (cliente.getTipoCliente().equals(ClienteCorporativo.CORPORATIVO)) {
			ClienteCorporativo clienteCorporativo = (ClienteCorporativo) cliente;
			descuento = (clienteCorporativo.getTamanoEmpresa() == 1) ? DESCUENTO_GRANDES : (clienteCorporativo.getTamanoEmpresa() == 2) ? DESCUENTO_MEDIANAS : DESCUENTO_PEQ;
			
		}
		
		return descuento;
	}
}
