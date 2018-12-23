package es.inf.uva.poo.kiosk;

import static org.junit.Assert.*;
import org.junit.Test;

import es.inf.uva.poo.PackageLocker.PackageLocker;
import es.inf.uva.poo.paquete.*;
import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.GPSCoordinate;

import java.time.*;
import java.util.Arrays;
import java.util.ArrayList;

public class KioskTest {

	@Test
	public void testInicializadorCorrecto() {
		Kiosk K = new Kiosk(10);
		assertEquals(K.getEspacio(), 10);
	}
	
	@Test
	public void testGetNumeroDePaquetes(){
		Kiosk K = new Kiosk(10);
		ArrayList<Package> listaPaquetesEnElKiosco;
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		K.addPackage(p);
		assertEquals(K.getNumeroDePaquetes(), 1);
	}
	
	@Test
	public void testAddPackage() {
		Kiosk K = new Kiosk(10);
		ArrayList<Package> listaPaquetesEnElKiosco;
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		K.addPackage(p);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testinicializadorHoraAperturaMayorCierre() {
		Kiosk K = new Kiosk(10);
		ArrayList<Package> listaPaquetesEnElKiosco;
		LocalDate fecha = LocalDate.of(2000,1,1);
		Package p = new Package("9999999991",fecha);
		p.set
		K.addPackage(p);
	}
		
}
