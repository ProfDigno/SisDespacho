	package FORMULARIO.ENTIDAD;
public class gasto_tipo {

//---------------DECLARAR VARIABLES---------------
private int C1idgasto_tipo;
private String C2nombre;
private boolean C3activar;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public gasto_tipo() {
		setTb_gasto_tipo("gasto_tipo");
		setId_idgasto_tipo("idgasto_tipo");
	}
	public static String getTb_gasto_tipo(){
		return nom_tabla;
	}
	public static void setTb_gasto_tipo(String nom_tabla){
		gasto_tipo.nom_tabla = nom_tabla;
	}
	public static String getId_idgasto_tipo(){
		return nom_idtabla;
	}
	public static void setId_idgasto_tipo(String nom_idtabla){
		gasto_tipo.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idgasto_tipo(){
		return C1idgasto_tipo;
	}
	public void setC1idgasto_tipo(int C1idgasto_tipo){
		this.C1idgasto_tipo = C1idgasto_tipo;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public boolean getC3activar(){
		return C3activar;
	}
	public void setC3activar(boolean C3activar){
		this.C3activar = C3activar;
	}
	public String toString() {
		return "gasto_tipo(" + ",idgasto_tipo=" + C1idgasto_tipo + " ,nombre=" + C2nombre + " ,activar=" + C3activar + " )";
	}
}
