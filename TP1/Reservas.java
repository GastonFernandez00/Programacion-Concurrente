/* La clase Reservas simula una reserva con número de asiento, estado y si está chequeado */

public class Reservas {

    public static final Integer PENDIENTE = 0;
    public static final Integer CONFIRMADO = 1;
    public static final Integer CANCELADO = 2;
    public static final Integer VERIFICADO = -1;

    private String posicion_asiento;
    private Integer estado;
    private boolean checked;
    private Asiento asiento;

    public Reservas(Asiento a){
        asiento = a;
        this.checked = false;
        posicion_asiento = a.getAsiento();
    }
/*
    public Reservas(String posicion_asiento, Integer estado){
        asiento = new Asiento(posicion_asiento);
        //this.posicion_asiento = posicion_asiento;
        this.estado = estado;
        this.checked = false;
    }
*/
    public void setPosAsiento(String numero){
        posicion_asiento = numero; 
    }

    /*Los estados posibles son: - PENDIENTE DE PAGO
                                - CONFIRMADO
                                - CANCELADO
                                - VERIFICADO 
    */
    public synchronized void setEstado(Integer estado){
        this.estado = estado;
    }

    public synchronized void setEstadoAsiento(Integer E){
        asiento.cambiarEstado(E);
    }

    public void setCheked(){
        this.checked = true;
    }

    public String getPosAsiento(){
        return posicion_asiento;
    }

    public Integer getEstado(){
        return estado;
    }

    public boolean getChecked(){
        return checked;
    }
    
}
