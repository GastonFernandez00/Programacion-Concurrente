import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Pago implements Runnable {

    //Variables
    private List<Reservas> canceladas;
    private List<Reservas> confirmadas;
    private final Object keyConfirmadas, keyCanceladas;
    Reservacion pendientes; 
    Log log;

    public Pago() {
        canceladas = new ArrayList<>();
        confirmadas = new ArrayList<>();
        keyConfirmadas = new Object();
        keyCanceladas = new Object();
        pendientes = new Reservacion();
    }

    @Override
    public void run() {

        while (!pendientes.isEmptyPendientes()) {
            
                if (!pendientes.isEmptyPendientes()) {
                    Reservas reserva = obtenerReservaCanceladasAleatoria(); //Reserva aleatoria
                    if (verificarPago()) {
                        pendientes.removePendientes(reserva); //elimino la reserva de pendientes 
                        addConfirmadas(reserva); //agrego la reserva a la lista de confirmadas
                        reserva.setEstado(Reservas.CONFIRMADO); //RESERVA CONFIRMADA
                        System.out.println(Thread.currentThread().getName() + " pago con exito el asiento Nº " + reserva.getPosAsiento());
                        
                    } else {

                        addCanceladas(reserva);
                        pendientes.removePendientes(reserva);
                        reserva.setEstado(Reservas.CANCELADO); // RESERVA CANCELADA
                        reserva.setEstadoAsiento(Asiento.DESCARTADO); // ASIENTO DESCARTADO
                        System.out.println(Thread.currentThread().getName() + " se descarta el asiento Nº " + reserva.getPosAsiento() + " por pago RECHAZADO");
                        log.registrarCancelacion();
                    }
                }
            
            }
    }

    //Verifico el pago con una probabilidad del 90% que sea aprobado
       private boolean verificarPago() {
       
        return new Random().nextInt(100) < 90;
    }

    //Metodos de la lista CONFIRMADOS

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

     //Metodos de la lista CANCELADAS

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
    
    public static void main(String[] args) {
        Reservacion r = new Reservacion();
        Pago p = new Pago();

        
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        Thread t4 = new Thread(p);
        Thread t5 = new Thread(p);
        
        t1.start();
        t2.start();
        t3.start();

        

        try {
            t1.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

        try {
            t2.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            t3.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

            t4.start();
            t5.start();

        try {
            t4.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            t5.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        //r.imprimir();
        
    }
        
}
