/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
//paquete certificado no puede estar en un kiosk
package es.inf.uva.poo.kiosk;

import es.inf.uva.poo.grupablepoint.GrupablePoint;
import es.inf.uva.poo.paquete.Package;
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
 

 
 
 
 public void addPackage(Package nuevo) {
  if(nuevo==null)
   throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
  if (nuevo.getCertificado()) {
   throw new IllegalArgumentException("no puede haber paquetes certificados en un kiosco");
  }
   taquillas.add(nuevo);
 }
 @Override
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
					i.setRecogido(true);
					cobrado+= i.getPrecio();
					taquillas.set(posicionPackage(i), null);
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