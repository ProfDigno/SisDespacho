	package FORMULARIO.ENTIDAD;
public class item_liquidacion_final {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_liquidacion_final;
private String C2fecha_creado;
private String C3creado_por;
private int C4orden;
private String C5descripcion;
private String C6comprobante_nro;
private double C7total_guarani;
private double C8desglose;
private double C9descriminacion_iva;
private int C10fk_idliquidacion_final;
private int C11fk_idtipo_comprobante;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_liquidacion_final() {
		setTb_item_liquidacion_final("item_liquidacion_final");
		setId_iditem_liquidacion_final("iditem_liquidacion_final");
	}
	public static String getTb_item_liquidacion_final(){
		return nom_tabla;
	}
	public static void setTb_item_liquidacion_final(String nom_tabla){
		item_liquidacion_final.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_liquidacion_final(){
		return nom_idtabla;
	}
	public static void setId_iditem_liquidacion_final(String nom_idtabla){
		item_liquidacion_final.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_liquidacion_final(){
		return C1iditem_liquidacion_final;
	}
	public void setC1iditem_liquidacion_final(int C1iditem_liquidacion_final){
		this.C1iditem_liquidacion_final = C1iditem_liquidacion_final;
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
	public double getC8desglose(){
		return C8desglose;
	}
	public void setC8desglose(double C8desglose){
		this.C8desglose = C8desglose;
	}
	public double getC9descriminacion_iva(){
		return C9descriminacion_iva;
	}
	public void setC9descriminacion_iva(double C9descriminacion_iva){
		this.C9descriminacion_iva = C9descriminacion_iva;
	}
	public int getC10fk_idliquidacion_final(){
		return C10fk_idliquidacion_final;
	}
	public void setC10fk_idliquidacion_final(int C10fk_idliquidacion_final){
		this.C10fk_idliquidacion_final = C10fk_idliquidacion_final;
	}
	public int getC11fk_idtipo_comprobante(){
		return C11fk_idtipo_comprobante;
	}
	public void setC11fk_idtipo_comprobante(int C11fk_idtipo_comprobante){
		this.C11fk_idtipo_comprobante = C11fk_idtipo_comprobante;
	}
	public String toString() {
		return "item_liquidacion_final(" + ",iditem_liquidacion_final=" + C1iditem_liquidacion_final + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,orden=" + C4orden + " ,descripcion=" + C5descripcion + " ,comprobante_nro=" + C6comprobante_nro + " ,total_guarani=" + C7total_guarani + " ,desglose=" + C8desglose + " ,descriminacion_iva=" + C9descriminacion_iva + " ,fk_idliquidacion_final=" + C10fk_idliquidacion_final + " ,fk_idtipo_comprobante=" + C11fk_idtipo_comprobante + " )";
	}
}
