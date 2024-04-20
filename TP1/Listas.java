import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Listas {    
    private List<Reservas> canceladas, confirmadas, pendientes, verificadas;
    private final Object keyConfirmadas, keyCanceladas, keyPendientes, keyVerificadas;
    private Log log;


    public Listas(){
        
            canceladas = new ArrayList<>();
            confirmadas = new ArrayList<>();
            pendientes = new ArrayList<>();
            verificadas = new ArrayList<>();
            
            keyCanceladas = new Object();
            keyConfirmadas = new Object();
            keyPendientes = new Object();
            keyVerificadas = new Object();
        
            log = new Log();
            
    }

    //---- METODOS PARA EL LOG -----

    public Log getLog(){
        return log;
    }

    public void escribirLogLista(){
        log.escribirLog();
    }

    public synchronized void imprimirOcupacionFinal(){
        log.imprimirOcupacionFinal(cantVerificadas());
    }
    
    

    //----METODOS PARA CHECKED-----
    public boolean getCheckedConfirmadas(Reservas r){
        synchronized(keyConfirmadas){
            return r.getChecked();
        }

    }
    public void setCheckedConfirmadas(Reservas r){
        synchronized(keyConfirmadas){
            r.setCheked();
        }
    }

    //----METODOS PARA LISTA PENDIENTES-----
    
    public Reservas obtenerReservaPendientesAleatoria() {
        synchronized(keyPendientes){
        if (pendientes.isEmpty()) {
              return null;
        }
        Random random = new Random();
        int indicependientes = random.nextInt(pendientes.size());
        return pendientes.get(indicependientes);
        }
       }
    
    public void addPendientes(Reservas r){
        synchronized(keyPendientes){
        pendientes.add(r);
        }
    }

    public void removePendientes(Reservas r){
        synchronized(keyPendientes){
        pendientes.remove(r);
        }
    }

    public boolean isEmptyPendientes(){
        synchronized(keyPendientes){
        return pendientes.isEmpty();
        }   
    }   

    //----METODOS PARA LISTA CONFIRMADAS-----

    public Reservas obtenerReservaConfirmadaAleatoria() {
      synchronized(keyConfirmadas){
        if (confirmadas.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indiceConfirmadas = random.nextInt(confirmadas.size());
        return confirmadas.get(indiceConfirmadas);
      } 
        
    }
    
    public void addConfirmadas(Reservas r){
        synchronized(keyConfirmadas){
            confirmadas.add(r);
        }
    }

    public void removeConfirmadas(Reservas r){
        synchronized(keyConfirmadas){
            confirmadas.remove(r);
        }
    }
    
    public boolean isEmptyConfirmadas(){
        synchronized(keyConfirmadas){
            return confirmadas.isEmpty();   
        }
    }
    
    public int cantConfirmadas(){
        synchronized(keyConfirmadas){
            return confirmadas.size();
        }     
    }

    //----METODOS PARA LISTA CANCELADAS-----

    public Reservas obtenerReservaCanceladasAleatoria() {
        synchronized(keyCanceladas){
          if (canceladas.isEmpty()) {
              return null;
          }
          Random random = new Random();
          int indiceCanceladas = random.nextInt(canceladas.size());
          return canceladas.get(indiceCanceladas);
        } 
          
    }   
    public void addCanceladas(Reservas r){
        synchronized(keyCanceladas){
            canceladas.add(r);
        }
    }
    public void removeCanceladas(Reservas r){
        synchronized(keyCanceladas){
            canceladas.remove(r);
        }
    }
     public boolean isEmptyCanceladas(){
        synchronized(keyCanceladas){
            return canceladas.isEmpty();   
        }
    }

    //----METODOS PARA LISTA VERIFICADAS-----

    public Reservas obtenerReservaVerificadasAleatoria() {
        synchronized(keyVerificadas){
            if (verificadas.isEmpty()) {
              return null;
          }
          Random random = new Random();
          int indiceVerificadas = random.nextInt(verificadas.size());
          return verificadas.get(indiceVerificadas);   
        }  
    }
      
    public void addVerificadas(Reservas r){
        synchronized(keyVerificadas){
            verificadas.add(r); 
        }
    }
    public  void removeVerificadas(Reservas r){
        synchronized(keyVerificadas){
             verificadas.remove(r);  
        }  
    }
    public boolean isEmptyVerificadas(){ 
        synchronized(keyVerificadas){
            return verificadas.isEmpty(); 
        } 
    }
    public int cantVerificadas(){
        synchronized(keyVerificadas){
             return verificadas.size(); 
        }
    }
}
