package es.inf.uva.poo.pickingpointhub;

import java.time.*;
import es.inf.uva.poo.paquete.Package;
import es.inf.uva.poo.grupablepoint.GrupablePoint;
import java.util.ArrayList;
import java.util.Arrays;

import es.inf.uva.poo.pickingpoint.*;
import es.uva.inf.poo.maps.GPSCoordinate;
public class PickingPointHub extends PickingPoint{
	
				//	VARIABLES GLOBALES
	
	ArrayList<GrupablePoint> elementos;

	
				//  FUNCIONES GLOBALES
	
	public PickingPointHub(GrupablePoint[] elements, boolean estado, String codigo,GPSCoordinate localizacionp) {
		super(estado,codigo,localizacionp);
		if(elements == null)
			throw new IllegalArgumentException("elements tiene que ser distinto de null");
		for(GrupablePoint i: elements) {
			if(i == null) {
				throw new IllegalArgumentException("elements no puede contener elementos tipo null");
			}
		}
			
		if(elements.length<2)
			throw new IllegalArgumentException("Elements debe tener al menos dos elementos");
		if(comprobarIdentificador()) {
			throw new IllegalArgumentException("No puede haber elementos con el mismo identificador");
		}
		elementos = new ArrayList<>(Arrays.asList(elements));
		
	}
	public int getTamano() {
		return elementos.size();
	}
	public GrupablePoint getElemento(int i) {
		if(i<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		if(i>=getTamano())
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		
		return elementos.get(i);
	}
	private boolean comprobarIdentificador() {
	    for (int x=0;x<getTamano();x++) {
	        for (int y=0;y<getTamano();y++) {
	          if (getElemento(x).getIdentificador()==getElemento(y).getIdentificador())
	            return true;
	        }
	      }
	    return false;
	}
}
