	package FORMULARIO.ENTIDAD;
public class caja_detalle {

//---------------DECLARAR VARIABLES---------------
private int C1idcaja_detalle;
private String C2fecha_creado;
private String C3creado_por;
private String C4descripcion;
private String C5estado;
private double C6monto_liquidacion_credito;
private double C7monto_recibo_pago;
private double C8monto_gasto;
private double C9monto_vale;
private int C10fk_idusuario;
private int C11fk_idvale;
private int C12fk_idgasto;
private int C13fk_idliquidacion_final;
private int C14fk_recibo_pago_tercero;
private int fk_idtodos;
private String nom_campo_todos;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public caja_detalle() {
		setTb_caja_detalle("caja_detalle");
		setId_idcaja_detalle("idcaja_detalle");
	}
	public static String getTb_caja_detalle(){
		return nom_tabla;
	}
	public static void setTb_caja_detalle(String nom_tabla){
		caja_detalle.nom_tabla = nom_tabla;
	}
	public static String getId_idcaja_detalle(){
		return nom_idtabla;
	}
	public static void setId_idcaja_detalle(String nom_idtabla){
		caja_detalle.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idcaja_detalle(){
		return C1idcaja_detalle;
	}
	public void setC1idcaja_detalle(int C1idcaja_detalle){
		this.C1idcaja_detalle = C1idcaja_detalle;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public String getC3creado_por(){
		return C3creado_por;
	}
	public void setC3creado_por(String C3creado_por){
		this.C3creado_por = C3creado_por;
	}
	public String getC4descripcion(){
		return C4descripcion;
	}
	public void setC4descripcion(String C4descripcion){
		this.C4descripcion = C4descripcion;
	}
	public String getC5estado(){
		return C5estado;
	}
	public void setC5estado(String C5estado){
		this.C5estado = C5estado;
	}
	public double getC6monto_liquidacion_credito(){
		return C6monto_liquidacion_credito;
	}
	public void setC6monto_liquidacion_credito(double C6monto_liquidacion_credito){
		this.C6monto_liquidacion_credito = C6monto_liquidacion_credito;
	}
	public double getC7monto_recibo_pago(){
		return C7monto_recibo_pago;
	}
	public void setC7monto_recibo_pago(double C7monto_recibo_pago){
		this.C7monto_recibo_pago = C7monto_recibo_pago;
	}
	public double getC8monto_gasto(){
		return C8monto_gasto;
	}
	public void setC8monto_gasto(double C8monto_gasto){
		this.C8monto_gasto = C8monto_gasto;
	}
	public double getC9monto_vale(){
		return C9monto_vale;
	}
	public void setC9monto_vale(double C9monto_vale){
		this.C9monto_vale = C9monto_vale;
	}
	public int getC10fk_idusuario(){
		return C10fk_idusuario;
	}
	public void setC10fk_idusuario(int C10fk_idusuario){
		this.C10fk_idusuario = C10fk_idusuario;
	}
	public int getC11fk_idvale(){
		return C11fk_idvale;
	}
	public void setC11fk_idvale(int C11fk_idvale){
		this.C11fk_idvale = C11fk_idvale;
	}
	public int getC12fk_idgasto(){
		return C12fk_idgasto;
	}
	public void setC12fk_idgasto(int C12fk_idgasto){
		this.C12fk_idgasto = C12fk_idgasto;
	}
	public int getC13fk_idliquidacion_final(){
		return C13fk_idliquidacion_final;
	}
	public void setC13fk_idliquidacion_final(int C13fk_idliquidacion_final){
		this.C13fk_idliquidacion_final = C13fk_idliquidacion_final;
	}
	public int getC14fk_recibo_pago_tercero(){
		return C14fk_recibo_pago_tercero;
	}
	public void setC14fk_recibo_pago_tercero(int C14fk_recibo_pago_tercero){
		this.C14fk_recibo_pago_tercero = C14fk_recibo_pago_tercero;
	}

    public int getFk_idtodos() {
        return fk_idtodos;
    }

    public void setFk_idtodos(int fk_idtodos) {
        this.fk_idtodos = fk_idtodos;
    }

    public String getNom_campo_todos() {
        return nom_campo_todos;
    }

    public void setNom_campo_todos(String nom_campo_todos) {
        this.nom_campo_todos = nom_campo_todos;
    }
        
	public String toString() {
		return "caja_detalle(" + ",idcaja_detalle=" + C1idcaja_detalle + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,descripcion=" + C4descripcion + " ,estado=" + C5estado + " ,monto_liquidacion_credito=" + C6monto_liquidacion_credito + " ,monto_recibo_pago=" + C7monto_recibo_pago + " ,monto_gasto=" + C8monto_gasto + " ,monto_vale=" + C9monto_vale + " ,fk_idusuario=" + C10fk_idusuario + " ,fk_idvale=" + C11fk_idvale + " ,fk_idgasto=" + C12fk_idgasto + " ,fk_idliquidacion_final=" + C13fk_idliquidacion_final + " ,fk_recibo_pago_tercero=" + C14fk_recibo_pago_tercero + " )";
	}
}
