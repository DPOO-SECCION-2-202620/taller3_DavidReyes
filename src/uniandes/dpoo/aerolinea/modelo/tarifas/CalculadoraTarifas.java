package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

/**
 * 	
Esta clase abstracta define el contrato para las posibles calculadoras de tarifa e implementa
 algunos métodos que pueden ser utilizadas en varias implementaciones.
 */
public abstract class CalculadoraTarifas {
	
	//atributos
	public static final double IMPUESTO = 0.28;
	
	//metodos
	
	/**
	 * Este método calcula cuál debe ser la tarifa total para un vuelo, dado el vuelo y el cliente.
	 *  La tarifa total está constituída por un costo base, un descuento que podría aplicarse sobre el costo base,
	 *  y un impuesto que se aplica sobre el costo base menos el descuento. Este método utiliza los métodos
	 *  calcularCostoBase y calcularPorcentajeDescuento para calcular la tarifa total
	 * @param vuelo
	 * @param cliente
	 * @return tarifa total
	 */
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		
		int costoBase = calcularCostoBase(vuelo, cliente);
		int costoBaseDescuento = costoBase - (int)(costoBase*calcularPorcentajeDescuento(cliente));
		int impuesto = calcularValorImpuestos(costoBaseDescuento);

		return costoBaseDescuento+impuesto;
	
	}
	
	/**
	 * Este método calcula cuál debe ser el costo base dado el vuelo y el cliente.
	 * @param vuelo
	 * @param cliente
	 * @return costo base
	 */
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	
	/**
	 * Calcula el porcentaje de descuento que se le debería dar a un cliente dado su tipo y/o su historia. 
	 * El método retorna un número entre 0 y 1: 0 significa que no hay descuento, y 1 significa que el descuento es del 100%.
	 * @param cliente
	 * @return
	 */
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	
	/**
	 * Calcula la distancia aproximada en kilómetros para una ruta
	 * @param ruta
	 * @return distancia en km
	 */
	protected int calcularDistanciaVuelo(Ruta ruta) {
		Aeropuerto origen = ruta.getOrigen();
		Aeropuerto destino = ruta.getDestino();
		
		return Aeropuerto.calcularDistancia(origen, destino);
		
	}
	
	
	/**
	 * Calcula el valor de los impuestos para un tiquete, dado el costo base. Los impuestos 
	 * se calculan como un porcentaje sobre el costo base, usando la constante IMPUESTO
	 * @param costoBase
	 * @return El valor correspondiente a los impuestos
	 */
	protected int calcularValorImpuestos(int costoBase) {
		return (int) Math.round(costoBase * IMPUESTO);
		
	}
	

}
