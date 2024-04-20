import java.util.Random;

public class Cancelacion implements Runnable {

    private Listas listas;

    public Cancelacion(Listas listas) {
        this.listas = listas;
    }

    @Override
    public void run() {
        Reservas reserva = listas.obtenerReservaConfirmadaAleatoria(); //Reserva aleatoria
        if (seCancela()) {
            reserva.setEstado(Reservas.CANCELADO); //RESERVA CONFIRMADA
            reserva.setEstadoAsiento(Asiento.DESCARTADO);
            listas.removeConfirmadas(reserva); //elimino la reserva de pendientes
            reserva.setCheked(true);
            listas.addCanceladas(reserva); //agrego la reserva a la lista de confirmadas
            System.out.println(Thread.currentThread().getName()
                    + " canceló con éxito la reserva del asiento Nº " + reserva.getPosAsiento() + "porque pintó");

        } else {
            reserva.setCheked(true); // RESERVA CANCELADA
            System.out.println(Thread.currentThread().getName()
                    + " se reconfirmó la reserva sobre el asiento Nº " + reserva.getPosAsiento());
            //log.registrarCancelacion();
        }

    }

    private boolean seCancela() {
        return new Random().nextInt(100) > 90;
    }
}
