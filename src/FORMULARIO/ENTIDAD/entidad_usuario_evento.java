	package FORMULARIO.ENTIDAD;
public class entidad_usuario_evento {

//---------------DECLARAR VARIABLES---------------
private int C1idusuario_evento;
private String C2fecha_creado;
private int C3cod_evento;
private int C4fk_idusuario_formulario;
private int C5fk_idusuario_tipo_evento;
private String C6descripcion;
private String C7mensaje_error;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public entidad_usuario_evento() {
		setTb_usuario_evento("usuario_evento");
		setId_idusuario_evento("idusuario_evento");
	}
	public static String getTb_usuario_evento(){
		return nom_tabla;
	}
	public static void setTb_usuario_evento(String nom_tabla){
		entidad_usuario_evento.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario_evento(){
		return nom_idtabla;
	}
	public static void setId_idusuario_evento(String nom_idtabla){
		entidad_usuario_evento.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario_evento(){
		return C1idusuario_evento;
	}
	public void setC1idusuario_evento(int C1idusuario_evento){
		this.C1idusuario_evento = C1idusuario_evento;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public int getC3cod_evento(){
		return C3cod_evento;
	}
	public void setC3cod_evento(int C3cod_evento){
		this.C3cod_evento = C3cod_evento;
	}
	public int getC4fk_idusuario_formulario(){
		return C4fk_idusuario_formulario;
	}
	public void setC4fk_idusuario_formulario(int C4fk_idusuario_formulario){
		this.C4fk_idusuario_formulario = C4fk_idusuario_formulario;
	}
	public int getC5fk_idusuario_tipo_evento(){
		return C5fk_idusuario_tipo_evento;
	}
	public void setC5fk_idusuario_tipo_evento(int C5fk_idusuario_tipo_evento){
		this.C5fk_idusuario_tipo_evento = C5fk_idusuario_tipo_evento;
	}
	public String getC6descripcion(){
		return C6descripcion;
	}
	public void setC6descripcion(String C6descripcion){
		this.C6descripcion = C6descripcion;
	}
	public String getC7mensaje_error(){
		return C7mensaje_error;
	}
	public void setC7mensaje_error(String C7mensaje_error){
		this.C7mensaje_error = C7mensaje_error;
	}
	public String toString() {
		return "usuario_evento(" + ",idusuario_evento=" + C1idusuario_evento + " ,fecha_creado=" + C2fecha_creado + " ,cod_evento=" + C3cod_evento + " ,formulario=" + C4fk_idusuario_formulario + " ,evento=" + C5fk_idusuario_tipo_evento + " ,descripcion=" + C6descripcion + " ,mensaje_error=" + C7mensaje_error + " )";
	}
}
