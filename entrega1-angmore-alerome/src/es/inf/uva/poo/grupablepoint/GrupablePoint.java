package es.inf.uva.poo.grupablepoint;

import java.util.ArrayList;
import es.uva.inf.poo.maps.GPSCoordinate;
import es.inf.uva.poo.paquete.Package;
import es.inf.uva.poo.pickingpoint.PickingPoint;

public class GrupablePoint extends PickingPoint {
	private  ArrayList<Package> contenedores;
	private int tamanoMaximo;
	
	public GrupablePoint(boolean estado,int tamano, String codigo, GPSCoordinate localizacionp) {
		super(estado,codigo,localizacionp);
		if(tamano<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		
		contenedores = new ArrayList<>();
		tamanoMaximo = tamano;
	}
	
	protected int size() {
		return contenedores.size();
	}
	
	/**
	 * introducimos un paquete dentro del contenedor.
	 * @param p un paquete que debe ser distinto de null
	 * @throws IllegalArgumentException si el paquete es nulo
	 */
	public void addPackage(Package p) {

		if(p == null)
			throw new IllegalArgumentException("El paquete no puede ser null");
		if(contenedores.size() == tamanoMaximo)
			throw new IllegalArgumentException("el contenedor esta lleno, no se pueden introducir mas paquetes");
		contenedores.add(p);
	}
	public void addPackage(int i,Package p) {
		if(i<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		if(i>=getTamano())
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		if(contenedores.size() == tamanoMaximo)
			throw new IllegalArgumentException("el contenedor esta lleno, no se pueden introducir mas paquetes");
		contenedores.add(i,p);
	}
	
	
	protected Package getPackage(int i) {
		if(i<0)
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		if(i>=getTamano())
			throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
		return contenedores.get(i);
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
	@Override
	public Package getPackage(String codigo) {
		if(codigo == null)
			throw new IllegalArgumentException("El codigo no puede ser null");
		boolean encontrado = false;
		for(Package i : contenedores) {
			if (codigo == i.getIdentificador())
				encontrado = true;
		}
		if(!encontrado)
			throw new IllegalArgumentException("Debe existir un paquete con el identificador");
		for(Package i : contenedores) {
			if (codigo == i.getIdentificador())
				return i;
		}
		return null;
	}
	
	/**
	 * Sacamos un paquete de una taquilla buscandolo por su codigo
	 * @param codigo String que debe ser distinto de {@code  null}
	 * @throws IllegalArgumentException si no existe paquete con ese codigo
	 */
	@Override
	public void eliminaPaquete(String codigo) { 
		if(codigo == null) {
			throw new IllegalArgumentException("Debe ser diferente de null");
		}
		boolean encontrado = false;
		for(Package i : contenedores) {
			if (codigo == i.getIdentificador())
				encontrado = true;
		}
		if(!encontrado)
			throw new IllegalArgumentException("Debe existir un paquete con el identificador");
		
		
		for(int i = 0; i<contenedores.size();i++) {
			if (contenedores.get(i).getIdentificador().equals(codigo)) {
				contenedores.add(i, null);
			}
		}
	}
	
	/**
	 * 
	 * @param codigoDeBusqueda
	 */
	@Override
	public void packagePicked(String codigoDeBusqueda) {
		if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("identificador debe ser != null");
		}
		for(Package i : contenedores) { // HABRA QUE MIRAR QUE NO ESTE RECOGIDO NI QUE ESTE MANDADO A CENTRAL				
				if (i.getIdentificador() == codigoDeBusqueda ) {
					if(contenedores.get(posicionPackage(i)).getRecogido())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(contenedores.get(posicionPackage(i)).getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					i .setRecogido(true);
				}
		}
	}
		
		/**
		 * Consulta el numero de las taquillas que se encuentran ocupadas en  el package locker
		 * @return valor entero positivo
		 */
		@Override
		public int getOcupadas() {
			return  contenedores.size();
		}
		/**
		 * Consulta el numero de las taquillas que se encuentran vacias en  el package locker
		 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
		 */
		@Override
		public int getVacias(){
			return  tamanoMaximo- contenedores.size();
		}
	
		public int getTamano() {
			return tamanoMaximo;
		}
		
		public void cambiarTam(int tamano) {
			if(tamano<0)
				throw new IllegalArgumentException("El tamano tiene que ser mayor de 0");
			tamanoMaximo = tamano;
			
		}
		
		public void toCentral(String codigoDeBusqueda) {
			if(codigoDeBusqueda == null) {
				throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
			}
			for(Package i : contenedores) {
					if (i.getIdentificador() == codigoDeBusqueda) {
						if(contenedores.get(posicionPackage(i)).getRecogido())
							throw new AssertionError("El paquete ya ha sido recogido por el cliente");
						if(contenedores.get(posicionPackage(i)).getDevuelto())
							throw new AssertionError("El paquete se ha devuelto al centro");
						i .setDevuelto(true);
					}
			}
			throw new IllegalArgumentException("El paquete no exite");
		
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
			for(int i = 0;i<contenedores.size();i++) {
				if(paquetebuscado == contenedores.get(i))
					return i;
			}
			throw new AssertionError("no encontrada la posicion del paquete");
		}
	}

