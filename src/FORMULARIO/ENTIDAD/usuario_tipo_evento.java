	package FORMULARIO.ENTIDAD;
public class usuario_tipo_evento {

//---------------DECLARAR VARIABLES---------------
private int C1idusuario_tipo_evento;
private String C2nombre;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public usuario_tipo_evento() {
		setTb_usuario_tipo_evento("usuario_tipo_evento");
		setId_idusuario_tipo_evento("idusuario_tipo_evento");
	}
	public static String getTb_usuario_tipo_evento(){
		return nom_tabla;
	}
	public static void setTb_usuario_tipo_evento(String nom_tabla){
		usuario_tipo_evento.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario_tipo_evento(){
		return nom_idtabla;
	}
	public static void setId_idusuario_tipo_evento(String nom_idtabla){
		usuario_tipo_evento.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario_tipo_evento(){
		return C1idusuario_tipo_evento;
	}
	public void setC1idusuario_tipo_evento(int C1idusuario_tipo_evento){
		this.C1idusuario_tipo_evento = C1idusuario_tipo_evento;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String toString() {
		return "usuario_tipo_evento(" + ",idusuario_tipo_evento=" + C1idusuario_tipo_evento + " ,nombre=" + C2nombre + " )";
	}
}
