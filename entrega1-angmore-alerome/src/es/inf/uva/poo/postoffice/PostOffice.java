package es.inf.uva.poo.postoffice;

import java.time.LocalDate;
import java.util.ArrayList;

import es.inf.uva.poo.paquete.Package;

public class PostOffice implements es.inf.uva.poo.identificationregistry.IdentificationRegistry {
 
    // VARIABLES GLOBALES

 Package p;
 ArrayList<Package> listaPaquetesPostOffice; //solo va a tener paquetes certificados
 
    //  FUNCIONES GLOBALES
 
 public PostOffice() {
  
 }
 
 public boolean isPackagePickupRegistered(String packageCode) {
  for(int i=0; i<listaPaquetesPostOffice.size();i++) {
   if(listaPaquetesPostOffice.get(i).getIdentificador()==packageCode) {
    return listaPaquetesPostOffice.get(i).getRecogido();
   }
  }
  return false;
 }
 
 public Package getPackageRegistered(String packageCode) {
  for(int i=0; i<listaPaquetesPostOffice.size();i++) {
   if(listaPaquetesPostOffice.get(i).getIdentificador()==packageCode) {
    return listaPaquetesPostOffice.get(i);
   }
  }
  return null;
 }
 
 public LocalDate getPickupDateFor(String packageCode) {
  for(int i=0; i<listaPaquetesPostOffice.size();i++) {
   if(listaPaquetesPostOffice.get(i).getIdentificador()==packageCode) {
    return listaPaquetesPostOffice.get(i).getFecha();
   }
  }
  LocalDate fecha = LocalDate.of(2000,1,1);
  return fecha;
 }
 public void registerCertifiedPackagePickup(Package p, String dni, LocalDate pickupDate) {
  
 }
 
 /**
  * Aï¿½ade un paquete al postOffice siempre y cuando este paquete este certificado
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
  * Estable un paquete segun su cï¿½digo a pagado
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
