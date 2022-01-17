	package FORMULARIO.ENTIDAD;
public class tipo_tasa_cambio {

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_tasa_cambio;
private String C2fecha_creacion;
private String C3creado_por;
private double C4dolar_gua_aduana;
private double C5dolar_gua_mercado;
private double C6real_gua_aduana;
private double C7real_gua_mercado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tipo_tasa_cambio() {
		setTb_tipo_tasa_cambio("tipo_tasa_cambio");
		setId_idtipo_tasa_cambio("idtipo_tasa_cambio");
	}
	public static String getTb_tipo_tasa_cambio(){
		return nom_tabla;
	}
	public static void setTb_tipo_tasa_cambio(String nom_tabla){
		tipo_tasa_cambio.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_tasa_cambio(){
		return nom_idtabla;
	}
	public static void setId_idtipo_tasa_cambio(String nom_idtabla){
		tipo_tasa_cambio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_tasa_cambio(){
		return C1idtipo_tasa_cambio;
	}
	public void setC1idtipo_tasa_cambio(int C1idtipo_tasa_cambio){
		this.C1idtipo_tasa_cambio = C1idtipo_tasa_cambio;
	}
	public String getC2fecha_creacion(){
		return C2fecha_creacion;
	}
	public void setC2fecha_creacion(String C2fecha_creacion){
		this.C2fecha_creacion = C2fecha_creacion;
	}
	public String getC3creado_por(){
		return C3creado_por;
	}
	public void setC3creado_por(String C3creado_por){
		this.C3creado_por = C3creado_por;
	}
	public double getC4dolar_gua_aduana(){
		return C4dolar_gua_aduana;
	}
	public void setC4dolar_gua_aduana(double C4dolar_gua_aduana){
		this.C4dolar_gua_aduana = C4dolar_gua_aduana;
	}
	public double getC5dolar_gua_mercado(){
		return C5dolar_gua_mercado;
	}
	public void setC5dolar_gua_mercado(double C5dolar_gua_mercado){
		this.C5dolar_gua_mercado = C5dolar_gua_mercado;
	}
	public double getC6real_gua_aduana(){
		return C6real_gua_aduana;
	}
	public void setC6real_gua_aduana(double C6real_gua_aduana){
		this.C6real_gua_aduana = C6real_gua_aduana;
	}
	public double getC7real_gua_mercado(){
		return C7real_gua_mercado;
	}
	public void setC7real_gua_mercado(double C7real_gua_mercado){
		this.C7real_gua_mercado = C7real_gua_mercado;
	}
	public String toString() {
		return "tipo_tasa_cambio(" + ",idtipo_tasa_cambio=" + C1idtipo_tasa_cambio + " ,fecha_creacion=" + C2fecha_creacion + " ,creado_por=" + C3creado_por + " ,dolar_gua_aduana=" + C4dolar_gua_aduana + " ,dolar_gua_mercado=" + C5dolar_gua_mercado + " ,real_gua_aduana=" + C6real_gua_aduana + " ,real_gua_mercado=" + C7real_gua_mercado + " )";
	}
}
