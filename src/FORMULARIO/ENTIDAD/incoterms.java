	package FORMULARIO.ENTIDAD;
public class incoterms {

//---------------DECLARAR VARIABLES---------------
private int C1idincoterms;
private String C2nombre;
private String C3sigla;
private String C4descripcion;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public incoterms() {
		setTb_incoterms("incoterms");
		setId_idincoterms("idincoterms");
	}
	public static String getTb_incoterms(){
		return nom_tabla;
	}
	public static void setTb_incoterms(String nom_tabla){
		incoterms.nom_tabla = nom_tabla;
	}
	public static String getId_idincoterms(){
		return nom_idtabla;
	}
	public static void setId_idincoterms(String nom_idtabla){
		incoterms.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idincoterms(){
		return C1idincoterms;
	}
	public void setC1idincoterms(int C1idincoterms){
		this.C1idincoterms = C1idincoterms;
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
		return "incoterms(" + ",idincoterms=" + C1idincoterms + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " ,descripcion=" + C4descripcion + " )";
	}
}
