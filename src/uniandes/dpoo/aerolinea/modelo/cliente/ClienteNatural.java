package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{
	
	public static final String NATURAL = "Natural";
	private String nombre;
	
	//ctor
	public ClienteNatural(String nombre) {
		super();
		this.nombre = nombre;
		
	}
	
	//metodos
	
	@Override
	public String getIdentificador() {
		return nombre;
		
	}
	
	@Override
	public String getTipoCliente() {
		return NATURAL;
	}
	
	
	

}
