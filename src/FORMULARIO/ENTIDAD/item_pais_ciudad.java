	package FORMULARIO.ENTIDAD;
public class item_pais_ciudad {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_pais_ciudad;
private int C2fk_idtercero_pais;
private int C3fk_idtercero_ciudad;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_pais_ciudad() {
		setTb_item_pais_ciudad("item_pais_ciudad");
		setId_iditem_pais_ciudad("iditem_pais_ciudad");
	}
	public static String getTb_item_pais_ciudad(){
		return nom_tabla;
	}
	public static void setTb_item_pais_ciudad(String nom_tabla){
		item_pais_ciudad.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_pais_ciudad(){
		return nom_idtabla;
	}
	public static void setId_iditem_pais_ciudad(String nom_idtabla){
		item_pais_ciudad.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_pais_ciudad(){
		return C1iditem_pais_ciudad;
	}
	public void setC1iditem_pais_ciudad(int C1iditem_pais_ciudad){
		this.C1iditem_pais_ciudad = C1iditem_pais_ciudad;
	}
	public int getC2fk_idtercero_pais(){
		return C2fk_idtercero_pais;
	}
	public void setC2fk_idtercero_pais(int C2fk_idtercero_pais){
		this.C2fk_idtercero_pais = C2fk_idtercero_pais;
	}
	public int getC3fk_idtercero_ciudad(){
		return C3fk_idtercero_ciudad;
	}
	public void setC3fk_idtercero_ciudad(int C3fk_idtercero_ciudad){
		this.C3fk_idtercero_ciudad = C3fk_idtercero_ciudad;
	}
	public String toString() {
		return "item_pais_ciudad(" + ",iditem_pais_ciudad=" + C1iditem_pais_ciudad + " ,fk_idtercero_pais=" + C2fk_idtercero_pais + " ,fk_idtercero_ciudad=" + C3fk_idtercero_ciudad + " )";
	}
}
