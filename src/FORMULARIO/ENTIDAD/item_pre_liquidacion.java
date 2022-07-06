	package FORMULARIO.ENTIDAD;
public class item_pre_liquidacion {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_pre_liquidacion;
private String C2fecha_creado;
private String C3creado_por;
private int C4orden;
private String C5descripcion;
private String C6comprobante_nro;
private double C7total_guarani;
private double C8sin_iva;
private double C9solo_iva;
private int C10fk_idpre_liquidacion;
private int C11fk_idcomprobante_liquidacion;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_pre_liquidacion() {
		setTb_item_pre_liquidacion("item_pre_liquidacion");
		setId_iditem_pre_liquidacion("iditem_pre_liquidacion");
	}
	public static String getTb_item_pre_liquidacion(){
		return nom_tabla;
	}
	public static void setTb_item_pre_liquidacion(String nom_tabla){
		item_pre_liquidacion.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_pre_liquidacion(){
		return nom_idtabla;
	}
	public static void setId_iditem_pre_liquidacion(String nom_idtabla){
		item_pre_liquidacion.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_pre_liquidacion(){
		return C1iditem_pre_liquidacion;
	}
	public void setC1iditem_pre_liquidacion(int C1iditem_pre_liquidacion){
		this.C1iditem_pre_liquidacion = C1iditem_pre_liquidacion;
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
	public int getC4orden(){
		return C4orden;
	}
	public void setC4orden(int C4orden){
		this.C4orden = C4orden;
	}
	public String getC5descripcion(){
		return C5descripcion;
	}
	public void setC5descripcion(String C5descripcion){
		this.C5descripcion = C5descripcion;
	}
	public String getC6comprobante_nro(){
		return C6comprobante_nro;
	}
	public void setC6comprobante_nro(String C6comprobante_nro){
		this.C6comprobante_nro = C6comprobante_nro;
	}
	public double getC7total_guarani(){
		return C7total_guarani;
	}
	public void setC7total_guarani(double C7total_guarani){
		this.C7total_guarani = C7total_guarani;
	}
	public double getC8sin_iva(){
		return C8sin_iva;
	}
	public void setC8sin_iva(double C8sin_iva){
		this.C8sin_iva = C8sin_iva;
	}
	public double getC9solo_iva(){
		return C9solo_iva;
	}
	public void setC9solo_iva(double C9solo_iva){
		this.C9solo_iva = C9solo_iva;
	}
	public int getC10fk_idpre_liquidacion(){
		return C10fk_idpre_liquidacion;
	}
	public void setC10fk_idpre_liquidacion(int C10fk_idpre_liquidacion){
		this.C10fk_idpre_liquidacion = C10fk_idpre_liquidacion;
	}
	public int getC11fk_idcomprobante_liquidacion(){
		return C11fk_idcomprobante_liquidacion;
	}
	public void setC11fk_idcomprobante_liquidacion(int C11fk_idcomprobante_liquidacion){
		this.C11fk_idcomprobante_liquidacion = C11fk_idcomprobante_liquidacion;
	}
	public String toString() {
		return "item_pre_liquidacion(" + ",iditem_pre_liquidacion=" + C1iditem_pre_liquidacion + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,orden=" + C4orden + " ,descripcion=" + C5descripcion + " ,comprobante_nro=" + C6comprobante_nro + " ,total_guarani=" + C7total_guarani + " ,sin_iva=" + C8sin_iva + " ,solo_iva=" + C9solo_iva + " ,fk_idpre_liquidacion=" + C10fk_idpre_liquidacion + " ,fk_idcomprobante_liquidacion=" + C11fk_idcomprobante_liquidacion + " )";
	}
}
