	package FORMULARIO.ENTIDAD;
public class tercero_ciudad {

//---------------DECLARAR VARIABLES---------------
private int C1idtercero_ciudad;
private String C2nombre;
private String C3sigla;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tercero_ciudad() {
		setTb_tercero_ciudad("tercero_ciudad");
		setId_idtercero_ciudad("idtercero_ciudad");
	}
	public static String getTb_tercero_ciudad(){
		return nom_tabla;
	}
	public static void setTb_tercero_ciudad(String nom_tabla){
		tercero_ciudad.nom_tabla = nom_tabla;
	}
	public static String getId_idtercero_ciudad(){
		return nom_idtabla;
	}
	public static void setId_idtercero_ciudad(String nom_idtabla){
		tercero_ciudad.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtercero_ciudad(){
		return C1idtercero_ciudad;
	}
	public void setC1idtercero_ciudad(int C1idtercero_ciudad){
		this.C1idtercero_ciudad = C1idtercero_ciudad;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String getC3sigla(){
		return C3sigla;
	}
	public void setC3sigla(String C3sigla){
		this.C3sigla = C3sigla;
	}
	public String toString() {
		return "tercero_ciudad(" + ",idtercero_ciudad=" + C1idtercero_ciudad + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " )";
	}
}
