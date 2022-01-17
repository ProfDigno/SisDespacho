	package FORMULARIO.ENTIDAD;
public class tipo_dependencia {

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_dependencia;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tipo_dependencia() {
		setTb_tipo_dependencia("tipo_dependencia");
		setId_idtipo_dependencia("idtipo_dependencia");
	}
	public static String getTb_tipo_dependencia(){
		return nom_tabla;
	}
	public static void setTb_tipo_dependencia(String nom_tabla){
		tipo_dependencia.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_dependencia(){
		return nom_idtabla;
	}
	public static void setId_idtipo_dependencia(String nom_idtabla){
		tipo_dependencia.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_dependencia(){
		return C1idtipo_dependencia;
	}
	public void setC1idtipo_dependencia(int C1idtipo_dependencia){
		this.C1idtipo_dependencia = C1idtipo_dependencia;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "tipo_dependencia(" + ",idtipo_dependencia=" + C1idtipo_dependencia + " ,nombre=" + C2nombre + " )";
	}
}
