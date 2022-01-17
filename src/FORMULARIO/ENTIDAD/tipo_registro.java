	package FORMULARIO.ENTIDAD;
public class tipo_registro {

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_registro;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tipo_registro() {
		setTb_tipo_registro("tipo_registro");
		setId_idtipo_registro("idtipo_registro");
	}
	public static String getTb_tipo_registro(){
		return nom_tabla;
	}
	public static void setTb_tipo_registro(String nom_tabla){
		tipo_registro.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_registro(){
		return nom_idtabla;
	}
	public static void setId_idtipo_registro(String nom_idtabla){
		tipo_registro.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_registro(){
		return C1idtipo_registro;
	}
	public void setC1idtipo_registro(int C1idtipo_registro){
		this.C1idtipo_registro = C1idtipo_registro;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "tipo_registro(" + ",idtipo_registro=" + C1idtipo_registro + " ,nombre=" + C2nombre + " )";
	}
}
