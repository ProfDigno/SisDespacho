	package FORMULARIO.ENTIDAD;
public class entidad_usuario_rol {

//---------------DECLARAR VARIABLES---------------
private int C1idusuario_rol;
private String C2fecha_creacion;
private String C3nombre;
private String C4descripcion;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public entidad_usuario_rol() {
		setTb_usuario_rol("usuario_rol");
		setId_idusuario_rol("idusuario_rol");
	}
	public static String getTb_usuario_rol(){
		return nom_tabla;
	}
	public static void setTb_usuario_rol(String nom_tabla){
		entidad_usuario_rol.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario_rol(){
		return nom_idtabla;
	}
	public static void setId_idusuario_rol(String nom_idtabla){
		entidad_usuario_rol.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario_rol(){
		return C1idusuario_rol;
	}
	public void setC1idusuario_rol(int C1idusuario_rol){
		this.C1idusuario_rol = C1idusuario_rol;
	}
	public String getC2fecha_creacion(){
		return C2fecha_creacion;
	}
	public void setC2fecha_creacion(String C2fecha_creacion){
		this.C2fecha_creacion = C2fecha_creacion;
	}
	public String getC3nombre(){
		return C3nombre;
	}
	public void setC3nombre(String C3nombre){
		this.C3nombre = C3nombre;
	}
	public String getC4descripcion(){
		return C4descripcion;
	}
	public void setC4descripcion(String C4descripcion){
		this.C4descripcion = C4descripcion;
	}
	public String toString() {
		return "usuario_rol(" + ",idusuario_rol=" + C1idusuario_rol + " ,fecha_creacion=" + C2fecha_creacion + " ,nombre=" + C3nombre + " ,descripcion=" + C4descripcion + " )";
	}
}
