package es.inf.uva.poo.grupablepoint;

import java.time.LocalDate;

import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.GPSCoordinate;
import java.util.ArrayList;
import es.inf.uva.poo.pickingpoint.PickingPoint;
public class GrupablePoint extends PickingPoint {

	protected String identificador;
	protected boolean operativo;
	protected GPSCoordinate localizacionPackageLocker;
	protected  ArrayList<Package> taquillas;
	protected int tamanoMaximo;
	
	public GrupablePoint(boolean estado,int tamano, String codigo, GPSCoordinate localizacion) {
		if(tamano<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		if(codigo == null)
			throw new IllegalArgumentException("el codigo debe ser distinto de null");
		// tanto el identificador como la localizacion se heredan.
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		operativo = estado;
		taquillas = new ArrayList<>();
		tamanoMaximo = tamano;
		localizacionPackageLocker = localizacion;
	}
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
	 * Consulta el numero de las taquillas que se encuentran ocupadas en  el package locker
	 * @return valor entero positivo
	 */
	
	public int getOcupadas() {
		return  taquillas.size();
	}
	/**
	 * Consulta el numero de las taquillas que se encuentran vacias en  el package locker
	 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
	 */
	
	public int getVacias(){
		return  tamanoMaximo- taquillas.size();
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
	 * @param x
	 * @param y
	 */
	public void setCoordenadas(GPSCoordinate localizacion) {
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		
		localizacionPackageLocker = localizacion;
	}

	/**
	 * Consulta el valor de las coordenadas 
	 * @return localizacionPackageLocker coordenadas del packagelocker
	 */
	public GPSCoordinate getCoordenadas() {
		return localizacionPackageLocker;
	}
	
	/**
	 * Busqueda de un paquete a traves de su codigo de identificacion.
	 * @param codigo {@code String} distinto de null
	 * 
	 * @throws IllegalArgumentException Si el codigo es null
	 * 
	 * 
	 * @return retorna el paquete con el identificador introducido.
	 */
	
	public Package getPackage(String codigo) {
		if(codigo == null)
			throw new IllegalArgumentException("El codigo no puede ser null");
		boolean encontrado = false;
		for(Package i : taquillas) {
			if (codigo == i.getIdentificador())
				encontrado = true;
		}
		if(!encontrado)
			throw new IllegalArgumentException("Debe existir un paquete con el identificador");
		for(Package i : taquillas) {
			if (codigo == i.getIdentificador())
				return i;
		}
		return null;
	}

	/**
	 * Consulta el numero de las taquillas de las que consta el package locker
	 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
	 */
	public int getTamano() {
		return tamanoMaximo;
	}
	
	public void cambiarTam(int tamano) {
		if(tamano<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		tamanoMaximo = tamano;
		
	}

	/**
	 * Calcula la distancia entre dos GrupablePoint o hijos suyos.
	 * @param otro tipo GrupablePoint o hijos suyos.
	 * 
	 * @return Un doble con la distancia entre ambos.
	 */
	public double distance(GrupablePoint otro) {
		return (localizacionPackageLocker.getDistanceTo(otro.getCoordenadas()));
	}


	/**
	 * Sacamos un paquete de una taquilla buscandolo por su codigo
	 * @param codigo String que debe ser distinto de {@code  null}
	 * @throws IllegalArgumentException si no existe paquete con ese codigo
	 */
	public void eliminaPaquete(String codigo) { 
		if(codigo == null) {
			throw new IllegalArgumentException("Debe ser diferente de null");
		}
		boolean encontrado = false;
		for(Package i : taquillas) {
			if (codigo == i.getIdentificador())
				encontrado = true;
		}
		if(!encontrado)
			throw new IllegalArgumentException("Debe existir un paquete con el identificador");
		
		
		for(int i = 0; i<taquillas.size();i++) {
			if (taquillas.get(i).getIdentificador().equals(codigo)) {
				taquillas.add(i, null);
			}
		}
	}

	/**
	 * 
	 * @param codigoDeBusqueda
	 */
	public void packagePicked(String codigoDeBusqueda) {
		if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("identificador debe ser != null");
		}
		for(Package i : taquillas) { // HABRA QUE MIRAR QUE NO ESTE RECOGIDO NI QUE ESTE MANDADO A CENTRAL				
				if (i.getIdentificador() == codigoDeBusqueda ) {
					if(taquillas.get(posicionPackage(i)).getRecogido())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(taquillas.get(posicionPackage(i)).getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					i .setRecogido(true);
				}
		}
	}

	public void toCentral(String codigoDeBusqueda) {
		if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		}
		for(Package i : taquillas) {
				if (i.getIdentificador() == codigoDeBusqueda) {
					if(taquillas.get(posicionPackage(i)).getRecogido())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(taquillas.get(posicionPackage(i)).getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					i .setDevuelto(true);
				}
		}
		throw new IllegalArgumentException("El paquete no exite");
	
	}
	/**
	 * introducimos un paquete dentro del contenedor.
	 * @param p un paquete que debe ser distinto de null
	 * @throws IllegalArgumentException si el paquete es nulo
	 */
	public void meterPaquete(Package p) {

		if(p == null)
			throw new IllegalArgumentException("El paquete no puede ser null");
		if(taquillas.size() == tamanoMaximo)
			throw new IllegalArgumentException("el contenedor esta lleno, no se pueden introducir mas paquetes");
		taquillas.add(p);
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

	/**
	 * Retorna la posicion en la que se encuentra el paquete
	 * @param paquetebuscado
	 * @return la posicion
	 * @throws AssertionError si no encuentra la posicion del paquete
	 */
	protected int posicionPackage(Package paquetebuscado) {
		if(paquetebuscado == null) {
			throw new IllegalArgumentException("El paquete introducido es null, introduzca un paquete creado.");
		}
		for(int i = 0;i<taquillas.size();i++) {
			if(paquetebuscado == taquillas.get(i))
				return i;
		}
		throw new AssertionError("no encontrada la posicion del paquete");
	}

}