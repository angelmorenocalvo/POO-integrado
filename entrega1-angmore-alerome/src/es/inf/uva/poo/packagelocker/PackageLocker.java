/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */

package es.inf.uva.poo.packagelocker;
import java.time.*;

import es.inf.uva.poo.grupablepoint.GrupablePoint;
import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.*;

/**
 * Es un tipo de dato que hace referencia al agrupamiento de paquetes.
 * En el podemos obtener informacion de la clase package, y movilizar los paquetes, a su vez
 * tiene funciones que ofrecen informacion sobre el packagelocker como su localizacion.
 * Con esta clase podemos controlar, modificar y movilizar los paquetes creados a traves 
 * de la clase package
 * @author OMEN
 *
 */

public class PackageLocker extends GrupablePoint {

	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private static final String CADENAERROR= "el numero debe estar en el rango de taquillas.";
	private static final String CADENAERR = "llamada incorrecta al identificador del paquete, identificador == null"; 
	private static final String FUERARANGO ="fuera de rango numeroCelda";
	/**
	 * Construye un packagelocker
	 * En este constructo no damos la opcion de dar un valor al estado, lo inicializamos
	 * directamente a false
	 * @param numeroTaquillas el numero total de taquillas que tendra el packagelocker
	 * @param localizacion donde se encuentra localizado el paquete
	 * Para ello hacemos uso de la clase GPSCoordinate
	 * @param codigo un codigo que identifica los diferentes packagelocker
	 * @param apertura hace referencia a la hora de apertura de dicho packagelocker
	 * @param cierre hace referencia a la hora de cierre de packagelocker
	 * Hemos hecho uso de la clase LocalTime
	 */
	
	public PackageLocker(int numeroTaquillas, GPSCoordinate localizacion, String codigo, LocalTime apertura, LocalTime cierre) {
		super(true, numeroTaquillas,codigo, localizacion);
		if(apertura == null) 
			throw new IllegalArgumentException(CADENAERR);
		if(cierre == null) 
			throw new IllegalArgumentException(CADENAERR);
		if(apertura.compareTo(cierre)>0) {
			throw new IllegalArgumentException("La hora de apertura no puede ser mayor que la de cierre.");
		}
		if (numeroTaquillas<0)
			throw new IllegalArgumentException(CADENAERROR);
		
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		horarioApertura=apertura;
		horarioCierre=cierre;
	}

	/**
	 * Constructor para dar valor al estado del packagelocker
 * @param numeroTaquillas el numero total de taquillas que tendra el packagelocker
	 * @param localizacion donde se encuentra localizado el paquete
	 * Para ello hacemos uso de la clase GPSCoordinate
	 * @param codigo un codigo que identifica los diferentes packagelocker
	 * @param apertura hace referencia a la hora de apertura de dicho packagelocker
	 * @param cierre hace referencia a la hora de cierre de packagelocker
	 * @param estado comprueba si esta abierto, es decir entre horario apertura y de cierre
	 */
	
	public PackageLocker(int numeroTaquillas, GPSCoordinate localizacion, boolean estado, String codigo, LocalTime apertura, LocalTime cierre) {
		super(estado, numeroTaquillas,codigo, localizacion);
		if(apertura == null) 
			throw new IllegalArgumentException(CADENAERR);
		if(cierre == null) 
			throw new IllegalArgumentException(CADENAERR);
		if(apertura.compareTo(cierre)>0) {
			throw new IllegalArgumentException("La hora de apertura no puede ser mayor que la de cierre.");
		}
		horarioApertura=apertura;
		horarioCierre=cierre;
	}
	
	/**
	 * modifica el valor de la hora de apertura del package locker
	 * @param variable tipo Time
	 * @throws IllegalArgumentException si {apertura==null}
	 */
	
	public void setHoraApertura(LocalTime apertura){
		if(apertura == null) 
			throw new IllegalArgumentException(CADENAERR);
		
		
		horarioApertura = apertura;
	}
	
	/**
	 * Consulta el valor de la hora de apertura del package locker
	 * @return una hora de la clase Time
	 */
	
	public LocalTime getHoraApertura() {	
		return horarioApertura;
	}
	
	/**
	 * Consulta la hora de cierre
	 * @return variable localtime horarioCierre
	 */
	
	public LocalTime getHoraCierre() {
		return horarioCierre;
	}

	/**
	 * modifica el valor de la hora de cierre del package locker
	 * @param variable tipo Time
	 */
	
	public void setHoraCierre(LocalTime cierre) {
		if(cierre == null) 
			throw new IllegalArgumentException("llamada incorrecta a la hora de cierre, horaDeCierre == null");
		
		horarioCierre = cierre;
	}

	

	/**
	 * Consulta el valor de la hora de cierre del package locker
	 * @return una hora de la clase Time
	 */
	public Package getPackage(int numeroCelda) { // MIRAR SI FALLA COMO A PABLO
		if (numeroCelda<0)
			throw new IllegalArgumentException(CADENAERROR);
		if (numeroCelda> size())
			throw new IllegalArgumentException(CADENAERROR);
		if(getPackage(numeroCelda-1) == null)
			throw new AssertionError("La celda no contiene paquete.");
		
		return getPackage(numeroCelda-1) ;
	}
	/**
	 * Da la posibilidad de introducir un paquete en una taquilla del packagelocker
	 * @param x posicion es la que vamos a introducir dicho paquete
	 * Habra que restar uno a ese numero puesto que el array empieza desde 0
	 * @param paqueteAIntroducir
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 * @throws IllegalArgumentException si {numeroCelda>taquillas.length}
	 * @throws IllegalArgumentException si {taquillas[numeroCelda-1]!=null}
	 * @throws IllegalArgumentException si {taquillasOperativas[numeroCelda-1]}
	 */
	/*
	public void asignarTaquilla(int numeroCelda, Package paqueteAIntroducir) {
		if (numeroCelda<0)
			throw new IllegalArgumentException(CADENAERROR);
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException(FUERARANGO);
		if(taquillas[numeroCelda-1] != null)
			throw new AssertionError("La taquilla ya contiene un paquete, borralo antes");
		if(taquillasOperativas[numeroCelda-1])
			throw new AssertionError("La taquilla no esta operativa");
		taquillas[numeroCelda-1] = paqueteAIntroducir;
	}
	*/
	
	/**
	 * Comprueba si una celda tiene un paquete
	 * @param numeroCelda celda a comprobar
	 * @return la posicion del array de ese numero de celda - 1 ya que el array empieza en 0
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 * @throws IllegalArgumentException si {numeroCelda>taquillas.length}
	 */
	
	public Package mirarEnTaquilla(int numeroCelda) {
		if (numeroCelda<0)
			throw new IllegalArgumentException(CADENAERROR);
		if (numeroCelda> size())
			throw new IllegalArgumentException(FUERARANGO);
		return getPackage(numeroCelda-1);
	}	
}