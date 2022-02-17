	package FORMULARIO.ENTIDAD;
public class vale {

//---------------DECLARAR VARIABLES---------------
private int C1idvale;
private String C2fecha_creado;
private String C3creado_por;
private String C4descripcion;
private double C5monto_vale;
private String C6monto_letra;
private String C7estado;
private int C8fk_idfuncionario;
private int C9fk_idusuario;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public vale() {
		setTb_vale("vale");
		setId_idvale("idvale");
	}
	public static String getTb_vale(){
		return nom_tabla;
	}
	public static void setTb_vale(String nom_tabla){
		vale.nom_tabla = nom_tabla;
	}
	public static String getId_idvale(){
		return nom_idtabla;
	}
	public static void setId_idvale(String nom_idtabla){
		vale.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idvale(){
		return C1idvale;
	}
	public void setC1idvale(int C1idvale){
		this.C1idvale = C1idvale;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public String getC3creado_por(){
		return C3creado_por;
	}
	public void setC3creado_por(String C3creado_por){
		this.C3creado_por = C3creado_por;
	}
	public String getC4descripcion(){
		return C4descripcion;
	}
	public void setC4descripcion(String C4descripcion){
		this.C4descripcion = C4descripcion;
	}
	public double getC5monto_vale(){
		return C5monto_vale;
	}
	public void setC5monto_vale(double C5monto_vale){
		this.C5monto_vale = C5monto_vale;
	}
	public String getC6monto_letra(){
		return C6monto_letra;
	}
	public void setC6monto_letra(String C6monto_letra){
		this.C6monto_letra = C6monto_letra;
	}
	public String getC7estado(){
		return C7estado;
	}
	public void setC7estado(String C7estado){
		this.C7estado = C7estado;
	}
	public int getC8fk_idfuncionario(){
		return C8fk_idfuncionario;
	}
	public void setC8fk_idfuncionario(int C8fk_idfuncionario){
		this.C8fk_idfuncionario = C8fk_idfuncionario;
	}
	public int getC9fk_idusuario(){
		return C9fk_idusuario;
	}
	public void setC9fk_idusuario(int C9fk_idusuario){
		this.C9fk_idusuario = C9fk_idusuario;
	}
	public String toString() {
		return "vale(" + ",idvale=" + C1idvale + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,descripcion=" + C4descripcion + " ,monto_vale=" + C5monto_vale + " ,monto_letra=" + C6monto_letra + " ,estado=" + C7estado + " ,fk_idfuncionario=" + C8fk_idfuncionario + " ,fk_idusuario=" + C9fk_idusuario + " )";
	}
}
