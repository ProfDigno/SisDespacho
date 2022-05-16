	package FORMULARIO.ENTIDAD;
public class transporte_empresa {

//---------------DECLARAR VARIABLES---------------
private int C1idtransporte_empresa;
private String C2nombre;
private String C3sigla;
private String C4direccion;
private String C5telefono;
private boolean C6eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public transporte_empresa() {
		setTb_transporte_empresa("transporte_empresa");
		setId_idtransporte_empresa("idtransporte_empresa");
	}
	public static String getTb_transporte_empresa(){
		return nom_tabla;
	}
	public static void setTb_transporte_empresa(String nom_tabla){
		transporte_empresa.nom_tabla = nom_tabla;
	}
	public static String getId_idtransporte_empresa(){
		return nom_idtabla;
	}
	public static void setId_idtransporte_empresa(String nom_idtabla){
		transporte_empresa.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtransporte_empresa(){
		return C1idtransporte_empresa;
	}
	public void setC1idtransporte_empresa(int C1idtransporte_empresa){
		this.C1idtransporte_empresa = C1idtransporte_empresa;
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
	public String getC4direccion(){
		return C4direccion;
	}
	public void setC4direccion(String C4direccion){
		this.C4direccion = C4direccion;
	}
	public String getC5telefono(){
		return C5telefono;
	}
	public void setC5telefono(String C5telefono){
		this.C5telefono = C5telefono;
	}

    public boolean getC6eliminado() {
        return C6eliminado;
    }

    public void setC6eliminado(boolean C6eliminado) {
        this.C6eliminado = C6eliminado;
    }
        
	public String toString() {
		return "transporte_empresa(" + ",idtransporte_empresa=" + C1idtransporte_empresa + " ,nombre=" + C2nombre + " ,sigla=" + C3sigla + " ,direccion=" + C4direccion + " ,telefono=" + C5telefono + " )";
	}
}
