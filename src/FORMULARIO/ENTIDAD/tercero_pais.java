	package FORMULARIO.ENTIDAD;
public class tercero_pais {

//---------------DECLARAR VARIABLES---------------
private int C1idtercero_pais;
private String C2nombre;
private String C3sigla;
private boolean C4eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tercero_pais() {
		setTb_tercero_pais("tercero_pais");
		setId_idtercero_pais("idtercero_pais");
	}
	public static String getTb_tercero_pais(){
		return nom_tabla;
	}
	public static void setTb_tercero_pais(String nom_tabla){
		tercero_pais.nom_tabla = nom_tabla;
	}
	public static String getId_idtercero_pais(){
		return nom_idtabla;
	}
	public static void setId_idtercero_pais(String nom_idtabla){
		tercero_pais.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtercero_pais(){
		return C1idtercero_pais;
	}
	public void setC1idtercero_pais(int C1idtercero_pais){
		this.C1idtercero_pais = C1idtercero_pais;
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

    public boolean isC4eliminado() {
        return C4eliminado;
    }

    public void setC4eliminado(boolean C4eliminado) {
        this.C4eliminado = C4eliminado;
    }
        
	public String toString() {
		return "tercero_pais(" + ",idtercero_pais=" + C1idtercero_pais + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " )";
	}
}
