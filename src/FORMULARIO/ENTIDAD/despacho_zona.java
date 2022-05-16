	package FORMULARIO.ENTIDAD;
public class despacho_zona {

//---------------DECLARAR VARIABLES---------------
private int C1iddespacho_zona;
private String C2nombre;
private boolean C3eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public despacho_zona() {
		setTb_despacho_zona("despacho_zona");
		setId_iddespacho_zona("iddespacho_zona");
	}
	public static String getTb_despacho_zona(){
		return nom_tabla;
	}
	public static void setTb_despacho_zona(String nom_tabla){
		despacho_zona.nom_tabla = nom_tabla;
	}
	public static String getId_iddespacho_zona(){
		return nom_idtabla;
	}
	public static void setId_iddespacho_zona(String nom_idtabla){
		despacho_zona.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iddespacho_zona(){
		return C1iddespacho_zona;
	}
	public void setC1iddespacho_zona(int C1iddespacho_zona){
		this.C1iddespacho_zona = C1iddespacho_zona;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}

    public boolean getC3eliminado() {
        return C3eliminado;
    }

    public void setC3eliminado(boolean C3eliminado) {
        this.C3eliminado = C3eliminado;
    }
        
	public String toString() {
		return "despacho_zona(" + ",iddespacho_zona=" + C1iddespacho_zona + " ,nombre=" + C2nombre + " )";
	}
}
