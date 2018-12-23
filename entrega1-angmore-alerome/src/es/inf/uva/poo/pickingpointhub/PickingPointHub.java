package es.inf.uva.poo.pickingpointhub;

import java.time.*;
import es.inf.uva.poo.paquete.Package;
import es.inf.uva.poo.grupablepoint.GrupablePoint;
import es.inf.uva.poo.kiosk.Kiosk;

import java.util.Arrays;
import java.util.ArrayList;
import es.inf.uva.poo.pickingpoint.*;
public class PickingPointHub extends PickingPoint{
	
				//	VARIABLES GLOBALES
	
	GrupablePoint casillero;
	Kiosk kiosk1;
	
				//  FUNCIONES GLOBALES
	
	public PickingPointHub(GrupablePoint P, Kiosk K) {
		casillero = P;
		kiosk1 = K;
	}
}
