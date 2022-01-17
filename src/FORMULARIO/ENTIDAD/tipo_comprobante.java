	package FORMULARIO.ENTIDAD;
public class tipo_comprobante {

    /**
     * @return the C7tipo_factura
     */
    public boolean getC7tipo_factura() {
        return C7tipo_factura;
    }

    /**
     * @param C7tipo_factura the C7tipo_factura to set
     */
    public void setC7tipo_factura(boolean C7tipo_factura) {
        this.C7tipo_factura = C7tipo_factura;
    }

    /**
     * @return the C6mercaderia
     */
    public boolean getC6mercaderia() {
        return C6mercaderia;
    }

    /**
     * @param C6mercaderia the C6mercaderia to set
     */
    public void setC6mercaderia(boolean C6mercaderia) {
        this.C6mercaderia = C6mercaderia;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idtipo_comprobante;
private String C2descripcion;
private boolean C3con_comprobante;
private boolean C4sin_comprobante;
private boolean C5boleta_despachante;
private boolean C6mercaderia;
private boolean C7tipo_factura;
private static String nom_tabla;
private static String nom_idtabla;
private static boolean config_inicio;
private static boolean ini_con_comprobante;
private static boolean ini_sin_comprobante;
private static boolean ini_boleta_despachante;
private static boolean ini_mercaderia;
private static boolean ini_tipo_factura;
//---------------TABLA-ID---------------
	public tipo_comprobante() {
		setTb_tipo_comprobante("tipo_comprobante");
		setId_idtipo_comprobante("idtipo_comprobante");
	}
	public static String getTb_tipo_comprobante(){
		return nom_tabla;
	}
	public static void setTb_tipo_comprobante(String nom_tabla){
		tipo_comprobante.nom_tabla = nom_tabla;
	}
	public static String getId_idtipo_comprobante(){
		return nom_idtabla;
	}
	public static void setId_idtipo_comprobante(String nom_idtabla){
		tipo_comprobante.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtipo_comprobante(){
		return C1idtipo_comprobante;
	}
	public void setC1idtipo_comprobante(int C1idtipo_comprobante){
		this.C1idtipo_comprobante = C1idtipo_comprobante;
	}
	public String getC2descripcion(){
		return C2descripcion;
	}
	public void setC2descripcion(String C2descripcion){
		this.C2descripcion = C2descripcion;
	}
	public boolean getC3con_comprobante(){
		return C3con_comprobante;
	}
	public void setC3con_comprobante(boolean C3con_comprobante){
		this.C3con_comprobante = C3con_comprobante;
	}
	public boolean getC4sin_comprobante(){
		return C4sin_comprobante;
	}
	public void setC4sin_comprobante(boolean C4sin_comprobante){
		this.C4sin_comprobante = C4sin_comprobante;
	}
	public boolean getC5boleta_despachante(){
		return C5boleta_despachante;
	}
	public void setC5boleta_despachante(boolean C5boleta_despachante){
		this.C5boleta_despachante = C5boleta_despachante;
	}
	public String toString() {
		return "tipo_comprobante(" + ",idtipo_comprobante=" + C1idtipo_comprobante + " ,descripcion=" + C2descripcion + " ,con_comprobante=" + C3con_comprobante + " ,sin_comprobante=" + C4sin_comprobante + " ,boleta_despachante=" + C5boleta_despachante + " )";
	}
    public void desactivar_todos(){
        setIni_con_comprobante(false);
        setIni_sin_comprobante(false);
        setIni_mercaderia(false);
        setIni_boleta_despachante(false);
        setIni_tipo_factura(false);
    }
    public static boolean isConfig_inicio() {
        return config_inicio;
    }

    public static void setConfig_inicio(boolean config_inicio) {
        tipo_comprobante.config_inicio = config_inicio;
    }

    public static boolean isIni_con_comprobante() {
        return ini_con_comprobante;
    }

    public static void setIni_con_comprobante(boolean ini_con_comprobante) {
        tipo_comprobante.ini_con_comprobante = ini_con_comprobante;
    }

    public static boolean isIni_sin_comprobante() {
        return ini_sin_comprobante;
    }

    public static void setIni_sin_comprobante(boolean ini_sin_comprobante) {
        tipo_comprobante.ini_sin_comprobante = ini_sin_comprobante;
    }

    public static boolean isIni_boleta_despachante() {
        return ini_boleta_despachante;
    }

    public static void setIni_boleta_despachante(boolean ini_boleta_despachante) {
        tipo_comprobante.ini_boleta_despachante = ini_boleta_despachante;
    }

    public static boolean isIni_mercaderia() {
        return ini_mercaderia;
    }

    public static void setIni_mercaderia(boolean ini_mercaderia) {
        tipo_comprobante.ini_mercaderia = ini_mercaderia;
    }

    public static boolean isIni_tipo_factura() {
        return ini_tipo_factura;
    }

    public static void setIni_tipo_factura(boolean ini_tipo_factura) {
        tipo_comprobante.ini_tipo_factura = ini_tipo_factura;
    }
        
        
}
