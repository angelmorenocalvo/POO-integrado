package es.inf.uva.poo.postoffice;

import java.util.ArrayList;

import es.inf.uva.poo.paquete.Package;

public class PostOffice implements es.inf.uva.poo.identificationregistry.IdentificationRegistry {
	
				//	VARIABLES GLOBALES

	ArrayList<Package> listaPaquetesPostOffice;	//solo va a tener paquetes certificados
	
				//  FUNCIONES GLOBALES
	
	public PostOffice() {
		
	}
	
	/**
	 * A�ade un paquete al postOffice siempre y cuando este paquete este certificado
	 * @param nuevo
	 */
	
	public void addPackage(Package nuevo) {
		if(nuevo==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(nuevo.getCertificado()==true) {
			listaPaquetesPostOffice.add(nuevo);
		}else {
			//si no esta certificado no puede entrar en el PostOffice
		}
	}
	
	/**
	 * Estable un paquete segun su c�digo a pagado
	 * @param paquete
	 * @param codigo
	 */
	
	public void paquetePagado(Package paquete,String codigo) {
		if (listaPaquetesPostOffice!=null) {
			if(paquete.getIdentificador()==codigo) {
				paquete.setPagado();
			}
		}
	}
	
	/**
	 * Comprueba que un paquete este pagado, suponiendo que este pagado, cambia su estado a recogido.
	 * Si un paquete esta pagado significa que ha sido recogido
	 * @param paquete
	 * @param codigo
	 * @return
	 */
	
	public boolean comprobarPagado(Package paquete, String codigo) {
		if (listaPaquetesPostOffice!=null) {
			if(paquete.getIdentificador()==codigo) {
				if((paquete.getPagado())==true){
					paquete.setRecogido(true);
				}else {
					paquete.setRecogido(false);
				}	
			}
		}
		return paquete.getRecogido();
	}
	
	
}
