/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
//paquete certificado no puede estar en un kiosk
package es.inf.uva.poo.kiosk;

import es.inf.uva.poo.grupablepoint.GrupablePoint;
import es.uva.inf.poo.maps.GPSCoordinate;



public class Kiosk extends GrupablePoint{
 
 //     VARIABLES GLOBALES
	int cobrado;
 
 //     FUNCIONES GLOBALES

 public Kiosk (int numeroPaquetes,boolean estado, String codigo, GPSCoordinate localizacion){
	 super(estado, numeroPaquetes,codigo, localizacion);
	 if (numeroPaquetes<1) {
		   throw new IllegalArgumentException("El numero de paquetes debe ser positivo.");
	 }
	 cobrado = 0;
	 
 }
 
 @Override
 public void packagePicked(String codigoDeBusqueda) {
	 if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("identificador debe ser != null");
		}
		for(int i = 0; i<getTamano();i++) { // HABRA QUE MIRAR QUE NO ESTE RECOGIDO NI QUE ESTE MANDADO A CENTRAL				
				if (getPackage(i).getIdentificador() == codigoDeBusqueda ) {
					if(getPackage(i).getRecogido())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(getPackage(i).getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					getPackage(i).setRecogido(true);
					cobrado+= getPackage(i).getPrecio();
					addPackage(i, null);
				}
		}
 }
 
 public int getCobrado() {
	 return cobrado;
 }
 
 public void entregaDinero() {
		cobrado = 0;
	}
 
}