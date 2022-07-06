	package FORMULARIO.ENTIDAD;
public class pre_liquidacion {

//---------------DECLARAR VARIABLES---------------
private int C1idpre_liquidacion;
private String C2fecha_creado;
private String C3creado_por;
private String C4fecha_factura;
private String C5fecha_llegada;
private String C6numero_factura;
private String C7numero_invoice;
private double C8monto_factura;
private String C9descripcion;
private String C10observacion;
private String C11tipo_liquidacion;
private String C12estado;
private int C13fk_idtercero_importador;
private int C14fk_idtercero_exportador;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public pre_liquidacion() {
		setTb_pre_liquidacion("pre_liquidacion");
		setId_idpre_liquidacion("idpre_liquidacion");
	}
	public static String getTb_pre_liquidacion(){
		return nom_tabla;
	}
	public static void setTb_pre_liquidacion(String nom_tabla){
		pre_liquidacion.nom_tabla = nom_tabla;
	}
	public static String getId_idpre_liquidacion(){
		return nom_idtabla;
	}
	public static void setId_idpre_liquidacion(String nom_idtabla){
		pre_liquidacion.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idpre_liquidacion(){
		return C1idpre_liquidacion;
	}
	public void setC1idpre_liquidacion(int C1idpre_liquidacion){
		this.C1idpre_liquidacion = C1idpre_liquidacion;
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
	public String getC4fecha_factura(){
		return C4fecha_factura;
	}
	public void setC4fecha_factura(String C4fecha_factura){
		this.C4fecha_factura = C4fecha_factura;
	}
	public String getC5fecha_llegada(){
		return C5fecha_llegada;
	}
	public void setC5fecha_llegada(String C5fecha_llegada){
		this.C5fecha_llegada = C5fecha_llegada;
	}
	public String getC6numero_factura(){
		return C6numero_factura;
	}
	public void setC6numero_factura(String C6numero_factura){
		this.C6numero_factura = C6numero_factura;
	}
	public String getC7numero_invoice(){
		return C7numero_invoice;
	}
	public void setC7numero_invoice(String C7numero_invoice){
		this.C7numero_invoice = C7numero_invoice;
	}
	public double getC8monto_factura(){
		return C8monto_factura;
	}
	public void setC8monto_factura(double C8monto_factura){
		this.C8monto_factura = C8monto_factura;
	}
	public String getC9descripcion(){
		return C9descripcion;
	}
	public void setC9descripcion(String C9descripcion){
		this.C9descripcion = C9descripcion;
	}
	public String getC10observacion(){
		return C10observacion;
	}
	public void setC10observacion(String C10observacion){
		this.C10observacion = C10observacion;
	}
	public String getC11tipo_liquidacion(){
		return C11tipo_liquidacion;
	}
	public void setC11tipo_liquidacion(String C11tipo_liquidacion){
		this.C11tipo_liquidacion = C11tipo_liquidacion;
	}
	public String getC12estado(){
		return C12estado;
	}
	public void setC12estado(String C12estado){
		this.C12estado = C12estado;
	}
	public int getC13fk_idtercero_importador(){
		return C13fk_idtercero_importador;
	}
	public void setC13fk_idtercero_importador(int C13fk_idtercero_importador){
		this.C13fk_idtercero_importador = C13fk_idtercero_importador;
	}
	public int getC14fk_idtercero_exportador(){
		return C14fk_idtercero_exportador;
	}
	public void setC14fk_idtercero_exportador(int C14fk_idtercero_exportador){
		this.C14fk_idtercero_exportador = C14fk_idtercero_exportador;
	}
	public String toString() {
		return "pre_liquidacion(" + ",idpre_liquidacion=" + C1idpre_liquidacion + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,fecha_factura=" + C4fecha_factura + " ,fecha_llegada=" + C5fecha_llegada + " ,numero_factura=" + C6numero_factura + " ,numero_invoice=" + C7numero_invoice + " ,monto_factura=" + C8monto_factura + " ,descripcion=" + C9descripcion + " ,observacion=" + C10observacion + " ,tipo_liquidacion=" + C11tipo_liquidacion + " ,estado=" + C12estado + " ,fk_idtercero_importador=" + C13fk_idtercero_importador + " ,fk_idtercero_exportador=" + C14fk_idtercero_exportador + " )";
	}
}
