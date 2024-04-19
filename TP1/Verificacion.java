import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Verificacion implements Runnable {
    private RegistroReservas registroReservas;
    private Avion avion;
    private List<Reservas> verificadas;

    public ProcesoVerificacion(RegistroReservas registroReservas, Avion avion) {
        this.registroReservas = registroReservas;
        this.avion = avion;
        verificadas = new ArrayList<>();
    }

    //over
    public void run() {
        // Obtener la lista de reservas confirmadas
        List<Reserva> reservasConfirmadas = registroReservas.getReservasConfirmadas();

        // Verificar cada reserva confirmada
        for (Reserva reserva : reservasConfirmadas) {
            // Seleccionar aleatoriamente si se marca como "checked"
            boolean isChecked = generarAleatorio(0.5); // Suponiendo una probabilidad del 50%

            if (isChecked) {
                // Mover la reserva a reservas verificadas
                registroReservas.moverReserva(reserva, EstadoReserva.VERIFICADA);
            }
        }
    }

    // Método para generar un valor booleano aleatorio con una probabilidad dada
    private boolean generarAleatorio(double probabilidad) {
        Random random = new Random();
        return random.nextDouble() < probabilidad;
    }

     //Metodos de la lista VERIFICADAS

    public synchronized Reservas obtenerReservaVerificadasAleatoria() {
          if (verificadas.isEmpty()) {
              return null;
          }
          Random random = new Random();
          int indiceVerificadas = random.nextInt(verificadas.size());
          return verificadas.get(indiceVerificadas);   
      }
      
    public synchronized void addVerificadas(Reservas r){
           verificadas.add(r); 
      }
    public synchronized void removeVerificadas(Reservas r){
            verificadas.remove(r);    
      }
     public synchronized boolean isEmptyVerificadas(){ 
            return verificadas.isEmpty();   
        }
    public synchronized int cantVerificadas(){
            return verificadas.size();
      }
  
}


// Metodo para sacar una confirmada aleatoria clase MILI, modifciar variables ischekd de reserva LUJAN,
// hacer metodo para poner reservas verificadas y hacer lista de reservas verificadas como MILI
//Metodo 
