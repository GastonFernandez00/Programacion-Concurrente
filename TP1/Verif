
import java.util.List;
import java.util.Random;

public class ProcesoVerificacion implements Runnable {
    private RegistroReservas registroReservas;
    private Avion avion;

    public ProcesoVerificacion(RegistroReservas registroReservas, Avion avion) {
        this.registroReservas = registroReservas;
        this.avion = avion;
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
}


// Metodo para sacar una confirmada aleatoria clase MILI, modifciar variables ischekd de reserva LUJAN,
// hacer metodo para poner reservas verificadas y hacer lista de reservas verificadas como MILI
//Metodo 
