	package FORMULARIO.ENTIDAD;
public class factura_nro_proforma {

    /**
     * @return the C8guarani_unidad
     */
    public double getC8guarani_unidad() {
        return C8guarani_unidad;
    }

    /**
     * @param C8guarani_unidad the C8guarani_unidad to set
     */
    public void setC8guarani_unidad(double C8guarani_unidad) {
        this.C8guarani_unidad = C8guarani_unidad;
    }

    /**
     * @return the C9fk_idmoneda_cambio
     */
    public int getC9fk_idmoneda_cambio() {
        return C9fk_idmoneda_cambio;
    }

    /**
     * @param C9fk_idmoneda_cambio the C9fk_idmoneda_cambio to set
     */
    public void setC9fk_idmoneda_cambio(int C9fk_idmoneda_cambio) {
        this.C9fk_idmoneda_cambio = C9fk_idmoneda_cambio;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idfactura_nro_proforma;
private String C2fecha_creacion;
private String C3creado_por;
private String C4nro_factura;
private double C5monto;
private String C6imagen;
private int C7fk_idliquidacion_proforma;
private double C8guarani_unidad;
private int C9fk_idmoneda_cambio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public factura_nro_proforma() {
		setTb_factura_nro_proforma("factura_nro_proforma");
		setId_idfactura_nro_proforma("idfactura_nro_proforma");
	}
	public static String getTb_factura_nro_proforma(){
		return nom_tabla;
	}
	public static void setTb_factura_nro_proforma(String nom_tabla){
		factura_nro_proforma.nom_tabla = nom_tabla;
	}
	public static String getId_idfactura_nro_proforma(){
		return nom_idtabla;
	}
	public static void setId_idfactura_nro_proforma(String nom_idtabla){
		factura_nro_proforma.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idfactura_nro_proforma(){
		return C1idfactura_nro_proforma;
	}
	public void setC1idfactura_nro_proforma(int C1idfactura_nro_proforma){
		this.C1idfactura_nro_proforma = C1idfactura_nro_proforma;
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
	public String getC4nro_factura(){
		return C4nro_factura;
	}
	public void setC4nro_factura(String C4nro_factura){
		this.C4nro_factura = C4nro_factura;
	}
	public double getC5monto(){
		return C5monto;
	}
	public void setC5monto(double C5monto){
		this.C5monto = C5monto;
	}
	public String getC6imagen(){
		return C6imagen;
	}
	public void setC6imagen(String C6imagen){
		this.C6imagen = C6imagen;
	}
	public int getC7fk_idliquidacion_proforma(){
		return C7fk_idliquidacion_proforma;
	}
	public void setC7fk_idliquidacion_proforma(int C7fk_idliquidacion_proforma){
		this.C7fk_idliquidacion_proforma = C7fk_idliquidacion_proforma;
	}
	public String toString() {
		return "factura_nro_proforma(" + ",idfactura_nro_proforma=" + C1idfactura_nro_proforma + " ,fecha_creacion=" + C2fecha_creacion + " ,creado_por=" + C3creado_por + " ,nro_factura=" + C4nro_factura + " ,monto=" + C5monto + " ,imagen=" + C6imagen + " ,fk_idliquidacion_proforma=" + C7fk_idliquidacion_proforma + " )";
	}
}
