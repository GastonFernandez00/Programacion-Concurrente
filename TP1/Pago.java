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
        keyConfirmadas=new Object();
        keyCanceladas=new Object();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            
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

            try {
                Thread.sleep(100); // Esperar un tiempo antes de realizar la próxima verificación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
            return confirmadas.size();
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
  

        
}
