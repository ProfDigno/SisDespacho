	package FORMULARIO.ENTIDAD;
public class moneda_cambio {

    /**
     * @return the C4guarani_unidad_mercado
     */
    public double getC4guarani_unidad_mercado() {
        return C4guarani_unidad_mercado;
    }

    /**
     * @param C4guarani_unidad_mercado the C4guarani_unidad_mercado to set
     */
    public void setC4guarani_unidad_mercado(double C4guarani_unidad_mercado) {
        this.C4guarani_unidad_mercado = C4guarani_unidad_mercado;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idmoneda_cambio;
private String C2moneda;
private double C3guarani_unidad_aduana;
private double C4guarani_unidad_mercado;
private String C5sigla;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public moneda_cambio() {
		setTb_moneda_cambio("moneda_cambio");
		setId_idmoneda_cambio("idmoneda_cambio");
	}
	public static String getTb_moneda_cambio(){
		return nom_tabla;
	}
	public static void setTb_moneda_cambio(String nom_tabla){
		moneda_cambio.nom_tabla = nom_tabla;
	}
	public static String getId_idmoneda_cambio(){
		return nom_idtabla;
	}
	public static void setId_idmoneda_cambio(String nom_idtabla){
		moneda_cambio.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idmoneda_cambio(){
		return C1idmoneda_cambio;
	}
	public void setC1idmoneda_cambio(int C1idmoneda_cambio){
		this.C1idmoneda_cambio = C1idmoneda_cambio;
	}
	public String getC2moneda(){
		return C2moneda;
	}
	public void setC2moneda(String C2moneda){
		this.C2moneda = C2moneda;
	}
	public double getC3guarani_unidad_aduana(){
		return C3guarani_unidad_aduana;
	}
	public void setC3guarani_unidad_aduana(double C3guarani_unidad){
		this.C3guarani_unidad_aduana = C3guarani_unidad;
	}
//	public String toString() {
//		return "moneda_cambio(" + ",idmoneda_cambio=" + C1idmoneda_cambio + " ,moneda=" + C2moneda + " ,guarani_unidad=" + C3guarani_unidad_aduana + " )";
//	}

    @Override
    public String toString() {
        return "moneda_cambio{" + "C1idmoneda_cambio=" + C1idmoneda_cambio + ", C2moneda=" + C2moneda + ", C3guarani_unidad_aduana=" + C3guarani_unidad_aduana + ", C4guarani_unidad_mercado=" + C4guarani_unidad_mercado + ", C4sigla=" + C5sigla + '}';
    }

    /**
     * @return the C4sigla
     */
    public String getC5sigla() {
        return C5sigla;
    }

    /**
     * @param C4sigla the C4sigla to set
     */
    public void setC5sigla(String C4sigla) {
        this.C5sigla = C4sigla;
    }
}
