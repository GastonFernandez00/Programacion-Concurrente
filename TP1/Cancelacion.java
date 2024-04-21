import java.util.Random;

public class Cancelacion implements Runnable {

    private Listas listas;

    public Cancelacion(Listas listas) {
        this.listas = listas;
    }

    @Override
    public void run() {
        //int contador = 0;
        while(!listas.isEmptyConfirmadas()){
                Reservas reserva = listas.obtenerReservaConfirmadaAleatoria(); //Reserva aleatoria
                
                //if( reserva != null){
                    //contador = 0;
                    if(!listas.getCheckedConfirmadas(reserva)){
                        if (seCancela()) {
                            reserva.setEstado(Reservas.CANCELADO); //RESERVA CONFIRMADA
                            reserva.setEstadoAsiento(Asiento.DESCARTADO);
                            listas.removeConfirmadas(reserva); //elimino la reserva de pendientes
                            listas.addCanceladas(reserva); //agrego la reserva a la lista de confirmadas
                            System.out.println(Thread.currentThread().getName()
                            + " canceló con éxito la reserva del asiento Nº " + reserva.getPosAsiento());

                        } else {
                            listas.setCheckedConfirmadas(reserva); // RESERVA CANCELADA
                            System.out.println(Thread.currentThread().getName()
                            + " se reconfirmó la reserva sobre el asiento Nº " + reserva.getPosAsiento());
                            
                        }
                    }
                //}
                /*else{
                    contador++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(contador == 5) break;
                }*/
                try{
                    Thread.sleep(150);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        System.out.println(Thread.currentThread().getName()+" Terminó la ejecución");
    }
    private boolean seCancela() {
        return new Random().nextInt(100) > 90;
    }

    public static void main(String[] args) {
        Listas lista = new Listas();
        Reservacion r = new Reservacion(lista);
        Pago p = new Pago(lista);
        Cancelacion c = new Cancelacion(lista);

        
        Thread t0 = new Thread(r);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        Thread t3 = new Thread(p);
        Thread t4 = new Thread(p);

        Thread t5 = new Thread(c);
        Thread t6 = new Thread(c); 
        Thread t7 = new Thread(c);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
       
        


        

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

      

        try {
            t5.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

        try {
            t6.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        try {
            t7.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

        r.imprimir();
        
    }
    
    
}



