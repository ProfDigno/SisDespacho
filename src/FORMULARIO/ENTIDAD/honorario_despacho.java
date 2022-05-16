	package FORMULARIO.ENTIDAD;
public class honorario_despacho {

//---------------DECLARAR VARIABLES---------------
private int C1idhonorario_despacho;
private String C2fecha_creado;
private double C3monto;
private boolean C4eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public honorario_despacho() {
		setTb_honorario_despacho("honorario_despacho");
		setId_idhonorario_despacho("idhonorario_despacho");
	}
	public static String getTb_honorario_despacho(){
		return nom_tabla;
	}
	public static void setTb_honorario_despacho(String nom_tabla){
		honorario_despacho.nom_tabla = nom_tabla;
	}
	public static String getId_idhonorario_despacho(){
		return nom_idtabla;
	}
	public static void setId_idhonorario_despacho(String nom_idtabla){
		honorario_despacho.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idhonorario_despacho(){
		return C1idhonorario_despacho;
	}
	public void setC1idhonorario_despacho(int C1idhonorario_despacho){
		this.C1idhonorario_despacho = C1idhonorario_despacho;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public double getC3monto(){
		return C3monto;
	}
	public void setC3monto(double C3monto){
		this.C3monto = C3monto;
	}

    public boolean getC4eliminado() {
        return C4eliminado;
    }

    public void setC4eliminado(boolean C4eliminado) {
        this.C4eliminado = C4eliminado;
    }
        
	public String toString() {
		return "honorario_despacho(" + ",idhonorario_despacho=" + C1idhonorario_despacho + " ,fecha_creado=" + C2fecha_creado + " ,monto=" + C3monto + " )";
	}
}
