	package FORMULARIO.ENTIDAD;
public class aduana {

//---------------DECLARAR VARIABLES---------------
private int C1idaduana;
private String C2nombre;
private String C3telefono;
private String C4direccion;
private String C5sigla;
private boolean C6eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public aduana() {
		setTb_aduana("aduana");
		setId_idaduana("idaduana");
	}
	public static String getTb_aduana(){
		return nom_tabla;
	}
	public static void setTb_aduana(String nom_tabla){
		aduana.nom_tabla = nom_tabla;
	}
	public static String getId_idaduana(){
		return nom_idtabla;
	}
	public static void setId_idaduana(String nom_idtabla){
		aduana.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idaduana(){
		return C1idaduana;
	}
	public void setC1idaduana(int C1idaduana){
		this.C1idaduana = C1idaduana;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String getC3telefono(){
		return C3telefono;
	}
	public void setC3telefono(String C3telefono){
		this.C3telefono = C3telefono;
	}
	public String getC4direccion(){
		return C4direccion;
	}
	public void setC4direccion(String C4direccion){
		this.C4direccion = C4direccion;
	}
	public String getC5sigla(){
		return C5sigla;
	}
	public void setC5sigla(String C5sigla){
		this.C5sigla = C5sigla;
	}

    public boolean getC6eliminado() {
        return C6eliminado;
    }

    public void setC6eliminado(boolean C6eliminado) {
        this.C6eliminado = C6eliminado;
    }
        
	public String toString() {
		return "aduana(" + ",idaduana=" + C1idaduana + " ,nombre=" + C2nombre + " ,telefono=" + C3telefono + " ,direccion=" + C4direccion + " ,sigla=" + C5sigla + " )";
	}
}
