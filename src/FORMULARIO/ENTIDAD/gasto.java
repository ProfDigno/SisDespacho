	package FORMULARIO.ENTIDAD;
public class gasto {

//---------------DECLARAR VARIABLES---------------
private int C1idgasto;
private String C2fecha_creado;
private String C3creado_por;
private String C4fecha;
private String C5descripcion;
private double C6monto_gasto;
private String C7estado;
private int C8fk_idgasto_tipo;
private int C9fk_idusuario;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public gasto() {
		setTb_gasto("gasto");
		setId_idgasto("idgasto");
	}
	public static String getTb_gasto(){
		return nom_tabla;
	}
	public static void setTb_gasto(String nom_tabla){
		gasto.nom_tabla = nom_tabla;
	}
	public static String getId_idgasto(){
		return nom_idtabla;
	}
	public static void setId_idgasto(String nom_idtabla){
		gasto.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idgasto(){
		return C1idgasto;
	}
	public void setC1idgasto(int C1idgasto){
		this.C1idgasto = C1idgasto;
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
	public String getC4fecha(){
		return C4fecha;
	}
	public void setC4fecha(String C4fecha){
		this.C4fecha = C4fecha;
	}
	public String getC5descripcion(){
		return C5descripcion;
	}
	public void setC5descripcion(String C5descripcion){
		this.C5descripcion = C5descripcion;
	}
	public double getC6monto_gasto(){
		return C6monto_gasto;
	}
	public void setC6monto_gasto(double C6monto_gasto){
		this.C6monto_gasto = C6monto_gasto;
	}
	public String getC7estado(){
		return C7estado;
	}
	public void setC7estado(String C7estado){
		this.C7estado = C7estado;
	}
	public int getC8fk_idgasto_tipo(){
		return C8fk_idgasto_tipo;
	}
	public void setC8fk_idgasto_tipo(int C8fk_idgasto_tipo){
		this.C8fk_idgasto_tipo = C8fk_idgasto_tipo;
	}
	public int getC9fk_idusuario(){
		return C9fk_idusuario;
	}
	public void setC9fk_idusuario(int C9fk_idusuario){
		this.C9fk_idusuario = C9fk_idusuario;
	}
	public String toString() {
		return "gasto(" + ",idgasto=" + C1idgasto + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,fecha=" + C4fecha + " ,descripcion=" + C5descripcion + " ,monto_gasto=" + C6monto_gasto + " ,estado=" + C7estado + " ,fk_idgasto_tipo=" + C8fk_idgasto_tipo + " ,fk_idusuario=" + C9fk_idusuario + " )";
	}
}
