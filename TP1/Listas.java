import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Listas {    
    private List<Reservas> canceladas, confirmadas, pendientes, verificadas;
    private final Object keyConfirmadas, keyCanceladas, keyPendientes, keyVerificadas;
    private Log log;
    private List<Reservas> copiaPendientes = new ArrayList<>();
    private List<Reservas> copiaConfirmadas = new ArrayList<>();


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
    public void printListas(){
        System.out.println("Canceladas: " + canceladas.size());
        System.out.println("Confirmadas: " + confirmadas.size());
        System.out.println("Pendientes: " + pendientes.size());
        System.out.println("Verificadas: " + verificadas.size());
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
    public void InicializarCopiaPendientes(){
        for(int i = 0; i<pendientes.size();i++){
            copiaPendientes.add(pendientes.get(i));
        }
    }
    public Reservas obtenerReservaPendientesAleatoria() {
        synchronized(keyPendientes){
        if (copiaPendientes.isEmpty()) {
            InicializarCopiaPendientes();
        }
        Random random = new Random();
        int indicependientes = random.nextInt(copiaPendientes.size());

        Reservas r_aux = copiaPendientes.get(indicependientes);
        copiaPendientes.remove(indicependientes);
        return r_aux;
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
    public void InicializarCopiaConfirmadas(){
        for(int i = 0; i<confirmadas.size();i++){
            copiaConfirmadas.add(confirmadas.get(i));
        }
    }
    public Reservas obtenerReservaConfirmadaAleatoria() {
        synchronized(keyPendientes){
        if (copiaConfirmadas.isEmpty()) {
            InicializarCopiaConfirmadas();
        }
        Random random = new Random();
        int indiceConfirmadas = random.nextInt(copiaConfirmadas.size());

        Reservas r_aux = copiaPendientes.get(indiceConfirmadas);
        copiaPendientes.remove(indiceConfirmadas);
        return r_aux;
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
