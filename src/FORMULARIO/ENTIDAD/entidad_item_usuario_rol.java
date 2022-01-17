	package FORMULARIO.ENTIDAD;
public class entidad_item_usuario_rol {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_usuario_rol;
private boolean C2estado;
private int C3fk_idusuario_rol;
private int C4fk_idusuario_evento;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public entidad_item_usuario_rol() {
		setTb_item_usuario_rol("item_usuario_rol");
		setId_iditem_usuario_rol("iditem_usuario_rol");
	}
	public static String getTb_item_usuario_rol(){
		return nom_tabla;
	}
	public static void setTb_item_usuario_rol(String nom_tabla){
		entidad_item_usuario_rol.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_usuario_rol(){
		return nom_idtabla;
	}
	public static void setId_iditem_usuario_rol(String nom_idtabla){
		entidad_item_usuario_rol.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_usuario_rol(){
		return C1iditem_usuario_rol;
	}
	public void setC1iditem_usuario_rol(int C1iditem_usuario_rol){
		this.C1iditem_usuario_rol = C1iditem_usuario_rol;
	}
	public boolean getC2estado(){
		return C2estado;
	}
	public void setC2estado(boolean C2estado){
		this.C2estado = C2estado;
	}
	public int getC3fk_idusuario_rol(){
		return C3fk_idusuario_rol;
	}
	public void setC3fk_idusuario_rol(int C3fk_idusuario_rol){
		this.C3fk_idusuario_rol = C3fk_idusuario_rol;
	}
	public int getC4fk_idusuario_evento(){
		return C4fk_idusuario_evento;
	}
	public void setC4fk_idusuario_evento(int C4fk_idusuario_evento){
		this.C4fk_idusuario_evento = C4fk_idusuario_evento;
	}
	public String toString() {
		return "item_usuario_rol(" + ",iditem_usuario_rol=" + C1iditem_usuario_rol + " ,estado=" + C2estado + " ,fk_idusuario_rol=" + C3fk_idusuario_rol + " ,fk_idusuario_evento=" + C4fk_idusuario_evento + " )";
	}
}
