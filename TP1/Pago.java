import java.util.Random;

public class Pago implements Runnable {

    //Variables
    Listas lista;
    Log log;


    public Pago(Listas l) {
        this.lista = l;
        log = lista.getLog();
    }

    @Override
    public void run() {  
        try{
            Thread.sleep(100);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }     
        //boolean continuar = true;
        while (!lista.isEmptyPendientes()) {
            //if(!lista.isEmptyPendientes()) {

                Reservas reserva = lista.obtenerReservaPendientesAleatoria(); //Reserva aleatoria
                if (verificarPago()) {
                    reserva.setEstado(Reservas.CONFIRMADO); //RESERVA CONFIRMADA
                    lista.removePendientes(reserva); //elimino la reserva de pendientes 
                    lista.addConfirmadas(reserva); //agrego la reserva a la lista de confirmadas
                    System.out.println(Thread.currentThread().getName() 
                    + " pago con exito el asiento Nº " + reserva.getPosAsiento());
                    
                } else {
                    reserva.setEstado(Reservas.CANCELADO); // RESERVA CANCELADA
                    reserva.setEstadoAsiento(Asiento.DESCARTADO); // ASIENTO DESCARTADO rompe todo
                    lista.addCanceladas(reserva);
                    lista.removePendientes(reserva);
                    System.out.println(Thread.currentThread().getName() 
                    + " se descarta el asiento Nº " + reserva.getPosAsiento() + " por pago RECHAZADO");
                    log.registrarCancelacion();
                }
            //}
            try{
                Thread.sleep(200);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*if (lista.isEmptyPendientes()) {
                continuar = false; // Si la lista de pendientes está vacía, salimos del bucle
                System.out.println(Thread.currentThread().getName() + " Lista de pendientes vacía. Saliendo del bucle.");
            }*/
        }
        System.out.println(Thread.currentThread().getName()+" Terminó la ejecución");
    }

    //Verifico el pago con una probabilidad del 90% que sea aprobado
       private boolean verificarPago() {
       
        return new Random().nextInt(100) < 90;
    }

    
    public static void main(String[] args) {
        Listas lista = new Listas();
        Reservacion r = new Reservacion(lista);
        Pago p = new Pago(lista);

        
        Thread t0 = new Thread(r);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        Thread t3 = new Thread(p);
        Thread t4 = new Thread(p);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();


        

        try {
            t0.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

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
        try {
            t4.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        r.imprimir();
        
    }
    
        
}
