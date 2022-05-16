	package FORMULARIO.ENTIDAD;
public class regimen {

//---------------DECLARAR VARIABLES---------------
private int C1idregimen;
private String C2nombre;
private String C3sigla;
private String C4descripcion;
private boolean C5eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public regimen() {
		setTb_regimen("regimen");
		setId_idregimen("idregimen");
	}
	public static String getTb_regimen(){
		return nom_tabla;
	}
	public static void setTb_regimen(String nom_tabla){
		regimen.nom_tabla = nom_tabla;
	}
	public static String getId_idregimen(){
		return nom_idtabla;
	}
	public static void setId_idregimen(String nom_idtabla){
		regimen.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idregimen(){
		return C1idregimen;
	}
	public void setC1idregimen(int C1idregimen){
		this.C1idregimen = C1idregimen;
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

    public boolean getC5eliminado() {
        return C5eliminado;
    }

    public void setC5eliminado(boolean C5eliminado) {
        this.C5eliminado = C5eliminado;
    }
        
	public String toString() {
		return "regimen(" + ",idregimen=" + C1idregimen + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " ,descripcion=" + C4descripcion + " )";
	}
}
