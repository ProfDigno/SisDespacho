	package FORMULARIO.ENTIDAD;
public class item_tipo_registro {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_tipo_registro;
private String C2fecha_creacion;
private String C3creado_por;
private String C4estado;
private String C5fecha_estado;
private String C6nro_habilitacion;
private String C7fecha_habilitacion;
private String C8fecha_vencimiento;
private String C9imagen;
private int C10fk_idtipo_registro;
private int C11fk_idtipo_dependencia;
private int C12fk_idtipo_institucion;
private int C13fk_idtercero;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_tipo_registro() {
		setTb_item_tipo_registro("item_tipo_registro");
		setId_iditem_tipo_registro("iditem_tipo_registro");
	}
	public static String getTb_item_tipo_registro(){
		return nom_tabla;
	}
	public static void setTb_item_tipo_registro(String nom_tabla){
		item_tipo_registro.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_tipo_registro(){
		return nom_idtabla;
	}
	public static void setId_iditem_tipo_registro(String nom_idtabla){
		item_tipo_registro.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_tipo_registro(){
		return C1iditem_tipo_registro;
	}
	public void setC1iditem_tipo_registro(int C1iditem_tipo_registro){
		this.C1iditem_tipo_registro = C1iditem_tipo_registro;
	}
	public String getC2fecha_creacion(){
		return C2fecha_creacion;
	}
	public void setC2fecha_creacion(String C2fecha_creacion){
		this.C2fecha_creacion = C2fecha_creacion;
	}
	public String getC3creado_por(){
		return C3creado_por;
	}
	public void setC3creado_por(String C3creado_por){
		this.C3creado_por = C3creado_por;
	}
	public String getC4estado(){
		return C4estado;
	}
	public void setC4estado(String C4estado){
		this.C4estado = C4estado;
	}
	public String getC5fecha_estado(){
		return C5fecha_estado;
	}
	public void setC5fecha_estado(String C5fecha_estado){
		this.C5fecha_estado = C5fecha_estado;
	}
	public String getC6nro_habilitacion(){
		return C6nro_habilitacion;
	}
	public void setC6nro_habilitacion(String C6nro_habilitacion){
		this.C6nro_habilitacion = C6nro_habilitacion;
	}
	public String getC7fecha_habilitacion(){
		return C7fecha_habilitacion;
	}
	public void setC7fecha_habilitacion(String C7fecha_habilitacion){
		this.C7fecha_habilitacion = C7fecha_habilitacion;
	}
	public String getC8fecha_vencimiento(){
		return C8fecha_vencimiento;
	}
	public void setC8fecha_vencimiento(String C8fecha_vencimiento){
		this.C8fecha_vencimiento = C8fecha_vencimiento;
	}
	public String getC9imagen(){
		return C9imagen;
	}
	public void setC9imagen(String C9imagen){
		this.C9imagen = C9imagen;
	}
	public int getC10fk_idtipo_registro(){
		return C10fk_idtipo_registro;
	}
	public void setC10fk_idtipo_registro(int C10fk_idtipo_registro){
		this.C10fk_idtipo_registro = C10fk_idtipo_registro;
	}
	public int getC11fk_idtipo_dependencia(){
		return C11fk_idtipo_dependencia;
	}
	public void setC11fk_idtipo_dependencia(int C11fk_idtipo_dependencia){
		this.C11fk_idtipo_dependencia = C11fk_idtipo_dependencia;
	}
	public int getC12fk_idtipo_institucion(){
		return C12fk_idtipo_institucion;
	}
	public void setC12fk_idtipo_institucion(int C12fk_idtipo_institucion){
		this.C12fk_idtipo_institucion = C12fk_idtipo_institucion;
	}
	public int getC13fk_idtercero(){
		return C13fk_idtercero;
	}
	public void setC13fk_idtercero(int C13fk_idtercero){
		this.C13fk_idtercero = C13fk_idtercero;
	}
	public String toString() {
		return "item_tipo_registro(" + ",iditem_tipo_registro=" + C1iditem_tipo_registro + " ,fecha_creacion=" + C2fecha_creacion + " ,creado_por=" + C3creado_por + " ,estado=" + C4estado + " ,fecha_estado=" + C5fecha_estado + " ,nro_habilitacion=" + C6nro_habilitacion + " ,fecha_habilitacion=" + C7fecha_habilitacion + " ,fecha_vencimiento=" + C8fecha_vencimiento + " ,imagen=" + C9imagen + " ,fk_idtipo_registro=" + C10fk_idtipo_registro + " ,fk_idtipo_dependencia=" + C11fk_idtipo_dependencia + " ,fk_idtipo_institucion=" + C12fk_idtipo_institucion + " ,fk_idtercero=" + C13fk_idtercero + " )";
	}
}
