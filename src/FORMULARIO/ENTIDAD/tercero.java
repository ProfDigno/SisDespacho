	package FORMULARIO.ENTIDAD;
public class tercero {

//---------------DECLARAR VARIABLES---------------
private int C1idtercero;
private String C2fecha_creacion;
private String C3creado_por;
private String C4nombre;
private String C5ruc;
private String C6telefono;
private String C7direccion;
private String C8representante_nombre;
private String C9representante_cedula;
private boolean C10importador;
private boolean C11despachante;
private boolean C12colaborador;
private boolean C13proveedor;
private boolean C14transportadora;
private int C15fk_idtercero_pais;
private int C16fk_idtercero_ciudad;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tercero() {
		setTb_tercero("tercero");
		setId_idtercero("idtercero");
	}
	public static String getTb_tercero(){
		return nom_tabla;
	}
	public static void setTb_tercero(String nom_tabla){
		tercero.nom_tabla = nom_tabla;
	}
	public static String getId_idtercero(){
		return nom_idtabla;
	}
	public static void setId_idtercero(String nom_idtabla){
		tercero.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtercero(){
		return C1idtercero;
	}
	public void setC1idtercero(int C1idtercero){
		this.C1idtercero = C1idtercero;
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
	public String getC4nombre(){
		return C4nombre;
	}
	public void setC4nombre(String C4nombre){
		this.C4nombre = C4nombre;
	}
	public String getC5ruc(){
		return C5ruc;
	}
	public void setC5ruc(String C5ruc){
		this.C5ruc = C5ruc;
	}
	public String getC6telefono(){
		return C6telefono;
	}
	public void setC6telefono(String C6telefono){
		this.C6telefono = C6telefono;
	}
	public String getC7direccion(){
		return C7direccion;
	}
	public void setC7direccion(String C7direccion){
		this.C7direccion = C7direccion;
	}
	public String getC8representante_nombre(){
		return C8representante_nombre;
	}
	public void setC8representante_nombre(String C8representante_nombre){
		this.C8representante_nombre = C8representante_nombre;
	}
	public String getC9representante_cedula(){
		return C9representante_cedula;
	}
	public void setC9representante_cedula(String C9representante_cedula){
		this.C9representante_cedula = C9representante_cedula;
	}
	public boolean getC10importador(){
		return C10importador;
	}
	public void setC10importador(boolean C10importador){
		this.C10importador = C10importador;
	}
	public boolean getC11despachante(){
		return C11despachante;
	}
	public void setC11despachante(boolean C11despachante){
		this.C11despachante = C11despachante;
	}
	public boolean getC12colaborador(){
		return C12colaborador;
	}
	public void setC12colaborador(boolean C12colaborador){
		this.C12colaborador = C12colaborador;
	}
	public boolean getC13proveedor(){
		return C13proveedor;
	}
	public void setC13proveedor(boolean C13proveedor){
		this.C13proveedor = C13proveedor;
	}
	public boolean getC14transportadora(){
		return C14transportadora;
	}
	public void setC14transportadora(boolean C14transportadora){
		this.C14transportadora = C14transportadora;
	}
	public int getC15fk_idtercero_pais(){
		return C15fk_idtercero_pais;
	}
	public void setC15fk_idtercero_pais(int C15fk_idtercero_pais){
		this.C15fk_idtercero_pais = C15fk_idtercero_pais;
	}
	public int getC16fk_idtercero_ciudad(){
		return C16fk_idtercero_ciudad;
	}
	public void setC16fk_idtercero_ciudad(int C16fk_idtercero_ciudad){
		this.C16fk_idtercero_ciudad = C16fk_idtercero_ciudad;
	}
	public String toString() {
		return "tercero(" + ",idtercero=" + C1idtercero + " ,fecha_creacion=" + C2fecha_creacion + " ,creado_por=" + C3creado_por + " ,nombre=" + C4nombre + " ,ruc=" + C5ruc + " ,telefono=" + C6telefono + " ,direccion=" + C7direccion + " ,representante_nombre=" + C8representante_nombre + " ,representante_cedula=" + C9representante_cedula + " ,importador=" + C10importador + " ,despachante=" + C11despachante + " ,colaborador=" + C12colaborador + " ,proveedor=" + C13proveedor + " ,transportadora=" + C14transportadora + " ,fk_idtercero_pais=" + C15fk_idtercero_pais + " ,fk_idtercero_ciudad=" + C16fk_idtercero_ciudad + " )";
	}
}
