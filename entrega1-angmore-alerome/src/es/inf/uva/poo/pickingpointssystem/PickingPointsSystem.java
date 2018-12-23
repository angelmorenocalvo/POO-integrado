/**
 /**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.pickingpointssystem;
import java.util.ArrayList;

import es.inf.uva.poo.pickingpoint.*;
import es.uva.inf.poo.maps.GPSCoordinate;

/** Herramienta que nos permite controlar el manejo de los package locker
 * funcionalidad de hacer modificaciones sobre package locker.
 * Con ayuda de esta clase obtendremos informacion referente a los packagelocker
 * @author OMEN
 *
 */

public class PickingPointsSystem {
	ArrayList<PickingPoint> listaPickingPoint;
	
	/**
	 * Inicializa el arraylist de packagelockers
	 */
	
	public PickingPointsSystem(){
		listaPickingPoint = new ArrayList<>();
	}
	
	/**
	 * Consulta el numero de packagelocker 
	 * @param listaPackageLockers el array list
	 * @return el tamano del array list que representa un entero equivalente al numero de packagelockers
	 */

	public int getNumeroPackageLocker() {
		return listaPickingPoint.size();
	}
	
	/**
	 * Anadir un nuevo packagelocker al arraylist
	 * @param nuevo packagelocker a introducir
	 * @param listaPackageLockers arraylist
	 * @return el arraylist con el packagelocker anadido
	 * @throws IllegalArgumentException si {nuevo==null}
	 */
		
	public void addPackageLocker(PickingPoint nuevo) {
		if(nuevo==null)
			throw new IllegalArgumentException("llamada incorecta al identificador del paquete, identificador == null");
		listaPickingPoint.add(nuevo);
	}
	
	/**
	 * Eliminar un packagelocker por su identificador
	 * @param codigoIdentificador es el codigo del packagelocker que queremos eliminar
	 * @param listaPackageLockers el arraylist
	 * Recorremos el arraylist buscando un packagelocker que tenga el mismo
	 * codigo que el introducido para eliminar ese packagelocker
	 * @throws IllegalArgumentException si {codigoIdentificador==null}
	 * @throws IllegalArgumentException si {bandera==false}
	 */
	
	public void removePackageLocker(String codigoIdentificador) {
		if(codigoIdentificador==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		boolean bandera = false;
		for(int x=0; x < listaPickingPoint.size(); x++){
			if((listaPickingPoint.get(x)).getIdentificador()==codigoIdentificador) {
				listaPickingPoint.remove(x);	
				bandera = true;
			}
		}
		if(!bandera)
			throw new IllegalArgumentException("no existe ningun paquete con ningun ");
	}
	
	/**
	 * Consultar si existe el packagelocker
	 * @param codigoIdentificador para buscar el packagelocker a comprobar
	 * @param listaPackageLockers el arraylist
	 * @return un valor booleano que va a ser true si existe ese paquete y false si no existe
	 * Para ello recorremos el arraylist a la vez que comprobamos si el codigo dado
	 * coincide con alguno de los packagelocker
	 * @throws IllegalArgumentException si {codigoIdentificador==null}
	 */
	
	public boolean existepickingPoint(String codigoIdentificador) {
		if(codigoIdentificador==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		for(int x=0; x < listaPickingPoint.size(); x++){
			if((listaPickingPoint.get(x)).getIdentificador()==codigoIdentificador) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtener una lista con los packagelocker operativos
	 * @param listaPackageLockers el array list
	 * @return un array con los packagelockers operativos
	 * Para ello cremos un array en el que vamos a ir almacenando los packagelocker operativos
	 * que seran los que tengan su estado = true
	 */
	
	public PickingPoint [] pickingPointsOperativos(){
		ArrayList<PickingPoint> listaPackageLockersOperativos = new ArrayList<>();
		for(int x=0; x < listaPickingPoint.size(); x++){
			if((listaPickingPoint.get(x)).getEstado()) {
				listaPackageLockersOperativos.add(listaPickingPoint.get(x));
			}
		}
		return listaPackageLockersOperativos.toArray(new PickingPoint[listaPackageLockersOperativos.size()]);
	}
	
	public PickingPoint [] pickingPointsConEspacio() {
		ArrayList<PickingPoint> listaPickingPointsConEspacio = new ArrayList<>();
		for(int x=0; x < listaPickingPoint.size(); x++){
			if((listaPickingPoint.get(x)).getVacias()!=0) {
				listaPickingPointsConEspacio.add(listaPickingPoint.get(x));
			}
		}
		return listaPickingPointsConEspacio.toArray(new PickingPoint[listaPickingPointsConEspacio.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker fuera de servicio
	 * @param listaPackageLockers el arraylist
	 * @return array con los packagelockers fuera de servicio
	 * Para ello creamos un array en el que vamos a ir guardando los packagelocker fuera de servicio
	 * que seran los que tengan su estado = false
	 */
	
	public PickingPoint [] pickingPointsFueraDeServicio() {
		ArrayList<PickingPoint> listaPackageLockersFueraDeServicio = new ArrayList<>();
		for(int x=0; x < listaPickingPoint.size(); x++){
			if(!(listaPickingPoint.get(x)).getEstado()) {
				listaPackageLockersFueraDeServicio.add(listaPickingPoint.get(x));			
			}
		}
		return listaPackageLockersFueraDeServicio.toArray(new PickingPoint[listaPackageLockersFueraDeServicio.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker que se encuentran a una distancia menor o igual de una coordenada
	 * @param d double que indica la distancia a la que quieres establecer el radio
	 * @param listaPackageLockers el arraylist
	 * @param coordenadas punto desde el que quieres buscar los packagelocker a una distancia menor o igual
	 * @return un array con los packagelocker en ese radio
	 * Para ello recorremos el arraylist comprobando que packagelocker se encuentran a una distancia menor o igual
	 * que la distancia establecida al punto de coordenadas.
	 * @throws IllegalArgumentException si {coordenadas==null}
	 * @throws IllegalArgumentException si {d<0}
	 */
	
	public PickingPoint [] pickingPointOperativosEnUnRadio(double d,GPSCoordinate coordenadas) {
		if(coordenadas==null)
			throw new IllegalArgumentException("Las coordenadas no pueden ser nulas");
		if(d<0)
			throw new IllegalArgumentException("La distancia no puede ser negativa");
		ArrayList<PickingPoint> listaPackageLockersEnUnRadio = new ArrayList<>();
		for(int x=0; x < listaPickingPoint.size(); x++){
			if(listaPickingPoint.get(x).getEstado() && listaPickingPoint.get(x).getCoordenadas().getDistanceTo(coordenadas)<=d){
				listaPackageLockersEnUnRadio.add(listaPickingPoint.get(x));
			
			}
		}
		return listaPackageLockersEnUnRadio.toArray(new PickingPoint[listaPackageLockersEnUnRadio.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker que tienen alguna taquilla vacia
	 * @param listaPackageLockers el arraylist
	 * @return un array con los packagelocker con alguna taquilla vacia
	 * Para ello recorremos el arraylist comprobando si taquillas vacias es mayor que 0, en el caso
	 * de que lo fuera guardariamos ese packagelocker en el array
	 */
	
	public PickingPoint [] pickingPointsConTaquillasVacias() {
		ArrayList<PickingPoint> listaPackageLockersConTaquillasVacias = new ArrayList<>();
		for(int x=0; x < listaPickingPoint.size(); x++){
			if((listaPickingPoint.get(x)).getVacias()>0){
				listaPackageLockersConTaquillasVacias.add(listaPickingPoint.get(x));
			}
		}
		return listaPackageLockersConTaquillasVacias.toArray(new PickingPoint[listaPackageLockersConTaquillasVacias.size()]);
	}
}
