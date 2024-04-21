
public class Verificacion implements Runnable {
    Listas lista;
    Log log;


    public Verificacion(Listas l) {
        this.lista = l;
        log = lista.getLog();

    }

    //over
    public void run() {
        
        int contador = 0;        
        while (true) {
                // reserva = new Reservas();
            if(!lista.isEmptyConfirmadas()){
                Reservas reserva = lista.obtenerReservaConfirmadaAleatoria(); //Reserva aleatoria
                if(reserva != null){
                    contador = 0;
                    if (reserva.getChecked()) {
                    
                        lista.removeConfirmadas(reserva); //elimino la reserva de confirmadas 
                        lista.addVerificadas(reserva); //agrego la reserva a la lista de verificadas
                        System.out.println(Thread.currentThread().getName()   
                         + " Esta verificada la reserva Nª " + reserva.getPosAsiento());
                        log.registrarAprobacion();
                    } 
                }
            }
            else{
                    contador++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(contador == 5) break;
            }
            try{
                    Thread.sleep(150);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
         }
            System.out.println(Thread.currentThread().getName()+" Terminó la ejecución");
     }
/*
        public static void main(String[] args) {
        Listas lista = new Listas();
        Reservacion r = new Reservacion(lista);
        Pago p = new Pago(lista);
        Cancelacion c = new Cancelacion(lista);
        Verificacion v = new Verificacion(lista);

        Thread t0 = new Thread(r);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        Thread t3 = new Thread(p);
        Thread t4 = new Thread(p);

        Thread t5 = new Thread(c);
        Thread t6 = new Thread(c); 
        Thread t7 = new Thread(c);

        Thread t8 = new Thread(v);
        Thread t9 = new Thread(v);
        
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
       
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

        try {
            t8.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        try {
            t9.join();
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

        // r.imprimir();
        
    }

    */
}


