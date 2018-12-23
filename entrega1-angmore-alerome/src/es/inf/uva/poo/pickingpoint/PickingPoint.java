package es.inf.uva.poo.pickingpoint;


import java.time.LocalDate;


import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.GPSCoordinate;
public abstract class PickingPoint {
	private String identificador;
	private boolean operativo;
	private GPSCoordinate localizacion;
	
	
	public PickingPoint(boolean estado, String codigo, GPSCoordinate localizacionp) {
		if(codigo == null)
			throw new IllegalArgumentException("el codigo debe ser distinto de null");
		
		if(localizacionp == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		operativo = estado;
		identificador = codigo;
		localizacion = localizacionp;
	}
	
	
	
	//protected abstract  Package getPackage(int i);
	public abstract Package getPackage(String codigo);
	public abstract void eliminaPaquete(String codigo);
	public abstract void packagePicked(String codigoDeBusqueda);
	public abstract void addPackage(Package p);
	public abstract int getVacias();
	public abstract int getOcupadas();
	public abstract void toCentral(String codigoDeBusqueda);
	
	


	
	
	/**
	 * modificar el valor de la variable estado
	 * @param estado para saber si esta abierto o cerrado, es decir entre horario apertura y de cierre
	 */
	public void setEstado(boolean estado) {
		operativo=estado;
	}
	
	/**
	 * consultar el valor del estado
	 * @return operativo valor del estado 
	 */
	public boolean getEstado() {
		return operativo;
	}
	
	/**
	 * modificar el valor del codigo identificador
	 * @param codigo
	 */
	public void setIdentificador(String codigo) {
		if(codigo == null)
			throw new IllegalArgumentException("Codigo debe ser != null");		
		identificador=codigo;
	}
	
	
	/**
	 * Consultar el codigo identificador 
	 * @return identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * modifica el valor de las coordenadas en las que se encuentra un packagelocker
	 * @param x cambia la localizacion
	 * 
	 */
	public void setCoordenadas(GPSCoordinate localizacionp) {
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		
		localizacion = localizacionp;
	}
	
	/**
	 * Consulta el valor de las coordenadas 
	 * @return localizacionPackageLocker coordenadas del packagelocker
	 */
	public GPSCoordinate getCoordenadas() {
		return localizacion;
	}
	/**
	 * Consulta el numero de las taquillas de las que consta el package locker
	 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
	 */
	
	
	/**
	 * Calcula la distancia entre dos GrupablePoint o hijos suyos.
	 * @param otro tipo GrupablePoint o hijos suyos.
	 * 
	 * @return Un doble con la distancia entre ambos.
	 */
	public double distance(PickingPoint otro) {
		return (localizacion.getDistanceTo(otro.getCoordenadas()));
	}
	
	

	//mira si esto tiene que ir en el paquete
		/**
		 * Comprueba si el paquete ha pasado de fecha
		 * @param hoy
		 * @param paquete
		 * @throws IllegalArgumentException si {hoy==null}
		 */
		public void expiredPackage(LocalDate hoy, Package paquete) {
			if (hoy == null)
				throw new IllegalArgumentException("La fecha de hoy no puede ser null.");
			if(paquete.expirado(hoy))
				paquete.setDevuelto(true);
		}
	

}
