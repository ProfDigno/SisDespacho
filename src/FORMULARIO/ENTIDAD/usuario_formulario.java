	package FORMULARIO.ENTIDAD;
public class usuario_formulario {

//---------------DECLARAR VARIABLES---------------
private int C1idusuario_formulario;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public usuario_formulario() {
		setTb_usuario_formulario("usuario_formulario");
		setId_idusuario_formulario("idusuario_formulario");
	}
	public static String getTb_usuario_formulario(){
		return nom_tabla;
	}
	public static void setTb_usuario_formulario(String nom_tabla){
		usuario_formulario.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario_formulario(){
		return nom_idtabla;
	}
	public static void setId_idusuario_formulario(String nom_idtabla){
		usuario_formulario.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario_formulario(){
		return C1idusuario_formulario;
	}
	public void setC1idusuario_formulario(int C1idusuario_formulario){
		this.C1idusuario_formulario = C1idusuario_formulario;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "usuario_formulario(" + ",idusuario_formulario=" + C1idusuario_formulario + " ,nombre=" + C2nombre + " )";
	}
}
