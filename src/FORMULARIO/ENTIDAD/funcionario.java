	package FORMULARIO.ENTIDAD;
public class funcionario {

//---------------DECLARAR VARIABLES---------------
private int C1idfuncionario;
private String C2fecha_creado;
private String C3creado_por;
private String C4nombre;
private String C5cedula;
private String C6telefono;
private String C7direccion;
private String C8cargo;
private double C9salario;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public funcionario() {
		setTb_funcionario("funcionario");
		setId_idfuncionario("idfuncionario");
	}
	public static String getTb_funcionario(){
		return nom_tabla;
	}
	public static void setTb_funcionario(String nom_tabla){
		funcionario.nom_tabla = nom_tabla;
	}
	public static String getId_idfuncionario(){
		return nom_idtabla;
	}
	public static void setId_idfuncionario(String nom_idtabla){
		funcionario.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idfuncionario(){
		return C1idfuncionario;
	}
	public void setC1idfuncionario(int C1idfuncionario){
		this.C1idfuncionario = C1idfuncionario;
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
	public String getC4nombre(){
		return C4nombre;
	}
	public void setC4nombre(String C4nombre){
		this.C4nombre = C4nombre;
	}
	public String getC5cedula(){
		return C5cedula;
	}
	public void setC5cedula(String C5cedula){
		this.C5cedula = C5cedula;
	}
	public String getC6telefono(){
		return C6telefono;
	}
	public void setC6telefono(String C6telefono){
		this.C6telefono = C6telefono;
	}
	public String getC7direccion(){
		return C7direccion;
	}
	public void setC7direccion(String C7direccion){
		this.C7direccion = C7direccion;
	}
	public String getC8cargo(){
		return C8cargo;
	}
	public void setC8cargo(String C8cargo){
		this.C8cargo = C8cargo;
	}
	public double getC9salario(){
		return C9salario;
	}
	public void setC9salario(double C9salario){
		this.C9salario = C9salario;
	}
	public String toString() {
		return "funcionario(" + ",idfuncionario=" + C1idfuncionario + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,nombre=" + C4nombre + " ,cedula=" + C5cedula + " ,telefono=" + C6telefono + " ,direccion=" + C7direccion + " ,cargo=" + C8cargo + " ,salario=" + C9salario + " )";
	}
}
