	package FORMULARIO.ENTIDAD;
public class encomienta {

    /**
     * @return the C13fk_idtercero
     */
    public int getC13fk_idtercero() {
        return C13fk_idtercero;
    }

    /**
     * @param C13fk_idtercero the C13fk_idtercero to set
     */
    public void setC13fk_idtercero(int C13fk_idtercero) {
        this.C13fk_idtercero = C13fk_idtercero;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idencomienta;
private String C2fecha_creado;
private double C3numero;
private String C4destinatario;
private String C5telefono;
private String C6direccion_destino;
private String C7forma_entrega;
private String C8forma_pago;
private String C9detalle_paquete;
private double C10monto;
private int C11fk_idtercero_ciudad;
private int C12fk_idliquidacion_proforma;
private int C13fk_idtercero;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public encomienta() {
		setTb_encomienta("encomienta");
		setId_idencomienta("idencomienta");
	}
	public static String getTb_encomienta(){
		return nom_tabla;
	}
	public static void setTb_encomienta(String nom_tabla){
		encomienta.nom_tabla = nom_tabla;
	}
	public static String getId_idencomienta(){
		return nom_idtabla;
	}
	public static void setId_idencomienta(String nom_idtabla){
		encomienta.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idencomienta(){
		return C1idencomienta;
	}
	public void setC1idencomienta(int C1idencomienta){
		this.C1idencomienta = C1idencomienta;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public double getC3numero(){
		return C3numero;
	}
	public void setC3numero(double C3numero){
		this.C3numero = C3numero;
	}
	public String getC4destinatario(){
		return C4destinatario;
	}
	public void setC4destinatario(String C4destinatario){
		this.C4destinatario = C4destinatario;
	}
	public String getC5telefono(){
		return C5telefono;
	}
	public void setC5telefono(String C5telefono){
		this.C5telefono = C5telefono;
	}
	public String getC6direccion_destino(){
		return C6direccion_destino;
	}
	public void setC6direccion_destino(String C6direccion_destino){
		this.C6direccion_destino = C6direccion_destino;
	}
	public String getC7forma_entrega(){
		return C7forma_entrega;
	}
	public void setC7forma_entrega(String C7forma_entrega){
		this.C7forma_entrega = C7forma_entrega;
	}
	public String getC8forma_pago(){
		return C8forma_pago;
	}
	public void setC8forma_pago(String C8forma_pago){
		this.C8forma_pago = C8forma_pago;
	}
	public String getC9detalle_paquete(){
		return C9detalle_paquete;
	}
	public void setC9detalle_paquete(String C9detalle_paquete){
		this.C9detalle_paquete = C9detalle_paquete;
	}
	public double getC10monto(){
		return C10monto;
	}
	public void setC10monto(double C10monto){
		this.C10monto = C10monto;
	}
	public int getC11fk_idtercero_ciudad(){
		return C11fk_idtercero_ciudad;
	}
	public void setC11fk_idtercero_ciudad(int C11fk_idtercero_ciudad){
		this.C11fk_idtercero_ciudad = C11fk_idtercero_ciudad;
	}
	public int getC12fk_idliquidacion_proforma(){
		return C12fk_idliquidacion_proforma;
	}
	public void setC12fk_idliquidacion_proforma(int C12fk_idliquidacion_proforma){
		this.C12fk_idliquidacion_proforma = C12fk_idliquidacion_proforma;
	}
	public String toString() {
		return "encomienta(" + ",idencomienta=" + C1idencomienta + " ,fecha_creado=" + C2fecha_creado + " ,numero=" + C3numero + " ,destinatario=" + C4destinatario + " ,telefono=" + C5telefono + " ,direccion_destino=" + C6direccion_destino + " ,forma_entrega=" + C7forma_entrega + " ,forma_pago=" + C8forma_pago + " ,detalle_paquete=" + C9detalle_paquete + " ,monto=" + C10monto + " ,fk_idtercero_ciudad=" + C11fk_idtercero_ciudad + " ,fk_idliquidacion_proforma=" + C12fk_idliquidacion_proforma + " )";
	}
}
