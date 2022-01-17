	package FORMULARIO.ENTIDAD;
public class tipo_institucion {

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_institucion;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tipo_institucion() {
		setTb_tipo_institucion("tipo_institucion");
		setId_idtipo_institucion("idtipo_institucion");
	}
	public static String getTb_tipo_institucion(){
		return nom_tabla;
	}
	public static void setTb_tipo_institucion(String nom_tabla){
		tipo_institucion.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_institucion(){
		return nom_idtabla;
	}
	public static void setId_idtipo_institucion(String nom_idtabla){
		tipo_institucion.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_institucion(){
		return C1idtipo_institucion;
	}
	public void setC1idtipo_institucion(int C1idtipo_institucion){
		this.C1idtipo_institucion = C1idtipo_institucion;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "tipo_institucion(" + ",idtipo_institucion=" + C1idtipo_institucion + " ,nombre=" + C2nombre + " )";
	}
}
