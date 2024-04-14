import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Pago implements Runnable {

    //Variables
    private List<Reservas> canceladas;
    private List<Reservas> confirmadas;
    Reservacion pendientes; 

    public Pago() {
        canceladas = new ArrayList<>();
        confirmadas = new ArrayList<>();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {
            //VER SI SACAR SYNCHRONIZED
            synchronized (pendientes.getPendientes()) {
                if (!pendientes.getPendientes().isEmpty()) {
                    Reservas reserva = obtenerReservaAleatoria(); 
                    if (verificarPago()) {
                        pendientes.getPendientes().remove(reserva); //elimino la reserva de pendientes
                        getConfirmadas().add(reserva); //agrego la reserva a la lista de confirmadas
                        reserva.setEstado(1); // 1: CONFIRMADO
                        System.out.println(Thread.currentThread().getName() + " pago con exito el asiento Nº " + reserva.getPosAsiento());
                        
                    } else {

                        getCanceladas().add(reserva);
                        pendientes.getPendientes().remove(reserva);
                        reserva.setEstado(2); // 1: CANCELADO
                        //ME FALTA DESCARTAR EL ASIENTO
                        System.out.println(Thread.currentThread().getName() + " se descarta el asiento Nº " + reserva.getPosAsiento() + " por pago RECHAZADO");
                    }
                }
            }

            try {
                Thread.sleep(100); // Esperar un tiempo antes de realizar la próxima verificación
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    //Metodo para obtener una reserva aleatoria de la lista de Reservas pendientes
    public Reservas obtenerReservaAleatoria() {
        if (pendientes.getPendientes().isEmpty()) {
            return null;
        }
        Random random = new Random();
        int indice = random.nextInt(pendientes.getPendientes().size());
        return pendientes.getPendientes().get(indice);
    }

    //Verifico el pago con una probabilidad del 90% que sea aprobado
    private boolean verificarPago() {
        return new Random().nextInt(100) < 90;
    }

    public synchronized List<Reservas> getConfirmadas(){
        return confirmadas;
    }

    public synchronized List<Reservas> getCanceladas(){
        return canceladas;
    }
        
}
