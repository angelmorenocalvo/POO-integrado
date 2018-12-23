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
		if(!sameLocation())
			throw new IllegalArgumentException("los grupable no tienen la misma localizacion");
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
	
	public void addConcentrador(GrupablePoint g) {
		if(g == null)
			throw new IllegalArgumentException("elements tiene que ser distinto de null");
		elementos.add(g);
		if(!sameLocation()) {
			throw new IllegalArgumentException("los grupable no tienen la misma localizacion");
		}
		if(comprobarIdentificador()) {
			throw new IllegalArgumentException("No puede haber elementos con el mismo identificador");
		}
		
	}
	public void quitarElemento(String codigo) {
		if (codigo == null)
			throw new IllegalArgumentException("el codigo debe ser distinto de null");
		if(getTamano()==2)
			throw new IllegalArgumentException("Elements debe tener al menos dos elementos");
		boolean bandera = true;
		for(int i= 0;i<getTamano();i++) {
			if (elementos.get(i).getIdentificador() == codigo) {
				bandera = false;
				elementos.remove(i);
			}
		}
		if(bandera)
			throw new IllegalArgumentException("El codigo debe ser de un elemento incluido");
		
	}
	public boolean pointInConc(String codigo) {
		if (codigo == null)
			throw new IllegalArgumentException("el codigo debe ser distinto de null");
		
		
		for(GrupablePoint i:elementos) {
			if (i.getIdentificador() == codigo) {
				return true;
			}
		}
		return false;		
	}
	
	public int getTamano() {
		return elementos.size();
	}
	
	public GrupablePoint[] agrupablePoint() {
		return elementos.toArray(new GrupablePoint[getTamano()]);
	}
	
	public boolean espacioDisponible() {
		for(GrupablePoint i:elementos) {
			if(i.getVacias()> 0)
				return true;
		}
		return false;
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
	
	private boolean sameLocation() {
		
		for (int x=0;x<getTamano();x++) {
	          if (getElemento(x).getCoordenadas().equals(getElemento(1).getCoordenadas()))
	            return false;
	      }
	    
		
		return true;
	}
	
}
