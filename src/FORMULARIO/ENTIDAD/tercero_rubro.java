	package FORMULARIO.ENTIDAD;
public class tercero_rubro {

//---------------DECLARAR VARIABLES---------------
private int C1idtercero_rubro;
private String C2nombre;
private String C3sigla;
private String C4descripcion;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tercero_rubro() {
		setTb_tercero_rubro("tercero_rubro");
		setId_idtercero_rubro("idtercero_rubro");
	}
	public static String getTb_tercero_rubro(){
		return nom_tabla;
	}
	public static void setTb_tercero_rubro(String nom_tabla){
		tercero_rubro.nom_tabla = nom_tabla;
	}
	public static String getId_idtercero_rubro(){
		return nom_idtabla;
	}
	public static void setId_idtercero_rubro(String nom_idtabla){
		tercero_rubro.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtercero_rubro(){
		return C1idtercero_rubro;
	}
	public void setC1idtercero_rubro(int C1idtercero_rubro){
		this.C1idtercero_rubro = C1idtercero_rubro;
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
	public String getC4descripcion(){
		return C4descripcion;
	}
	public void setC4descripcion(String C4descripcion){
		this.C4descripcion = C4descripcion;
	}
	public String toString() {
		return "tercero_rubro(" + ",idtercero_rubro=" + C1idtercero_rubro + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " ,descripcion=" + C4descripcion + " )";
	}
}
