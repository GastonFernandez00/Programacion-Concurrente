
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
                Reservas reserva = lista.obtenerReservaConfirmadaAleatoria(); //Reserva aleatoria
                if( reserva != null){
                    contador = 0;
                    if (reserva.getChecked() ) {
                    
                        lista.removeConfirmadas(reserva); //elimino la reserva de confirmadas 
                        lista.addVerificadas(reserva); //agrego la reserva a la lista de verificadas
                        System.out.println(Thread.currentThread().getName()   
                         + " Esta verificada la reserva Nª " + reserva.getPosAsiento());
                        log.registrarAprobacion();
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
      
    }
}


