package FORMULARIO.ENTIDAD;

public class item_comprobante {

    /**
     * @return the tipo_comprobante_MERCADERIA
     */
    public String getTipo_comprobante_MERCADERIA() {
        return tipo_comprobante_MERCADERIA;
    }

    /**
     * @param tipo_comprobante_MERCADERIA the tipo_comprobante_MERCADERIA to set
     */
    public void setTipo_comprobante_MERCADERIA(String tipo_comprobante_MERCADERIA) {
        this.tipo_comprobante_MERCADERIA = tipo_comprobante_MERCADERIA;
    }

    /**
     * @return the tipo_comprobante_CON_COMPROBANTE
     */
    public String getTipo_comprobante_CON_COMPROBANTE() {
        return tipo_comprobante_CON_COMPROBANTE;
    }

    /**
     * @param tipo_comprobante_CON_COMPROBANTE the
     * tipo_comprobante_CON_COMPROBANTE to set
     */
    public void setTipo_comprobante_CON_COMPROBANTE(String tipo_comprobante_CON_COMPROBANTE) {
        this.tipo_comprobante_CON_COMPROBANTE = tipo_comprobante_CON_COMPROBANTE;
    }

    /**
     * @return the tipo_comprobante_SIN_COMPROBANTE
     */
    public String getTipo_comprobante_SIN_COMPROBANTE() {
        return tipo_comprobante_SIN_COMPROBANTE;
    }

    /**
     * @param tipo_comprobante_SIN_COMPROBANTE the
     * tipo_comprobante_SIN_COMPROBANTE to set
     */
    public void setTipo_comprobante_SIN_COMPROBANTE(String tipo_comprobante_SIN_COMPROBANTE) {
        this.tipo_comprobante_SIN_COMPROBANTE = tipo_comprobante_SIN_COMPROBANTE;
    }

    /**
     * @return the tipo_comprobante_BOLETA_DESPACHO
     */
    public String getTipo_comprobante_BOLETA_DESPACHO() {
        return tipo_comprobante_BOLETA_DESPACHO;
    }

    /**
     * @param tipo_comprobante_BOLETA_DESPACHO the
     * tipo_comprobante_BOLETA_DESPACHO to set
     */
    public void setTipo_comprobante_BOLETA_DESPACHO(String tipo_comprobante_BOLETA_DESPACHO) {
        this.tipo_comprobante_BOLETA_DESPACHO = tipo_comprobante_BOLETA_DESPACHO;
    }

//---------------DECLARAR VARIABLES---------------
    /**
     * + ",case when ic.tipo_comprobante='MERCADERIA' then 1\n" + "when
     * ic.tipo_comprobante='CON_COMPROBANTE' then 2\n" + "when
     * ic.tipo_comprobante='SIN_COMPROBANTE' then 3\n" + "when
     * ic.tipo_comprobante='BOLETA_DESPACHO' then 4\n"
     */
    private String tipo_comprobante_MERCADERIA;
    private String tipo_comprobante_CON_COMPROBANTE;
    private String tipo_comprobante_SIN_COMPROBANTE;
    private String tipo_comprobante_BOLETA_DESPACHO;
    private int C1iditem_comprobante;
    private String C2fecha_creado;
    private String C3creado_por;
    private String C4descripcion;
    private double C5monto;
    private String C6referencia;
    private String C7imagen;
    private int C8fk_idliquidacion_proforma;
    private int C9fk_idtipo_comprobante;
    private String C10tipo_comprobante;
    private static String nom_tabla;
    private static String nom_idtabla;
//---------------TABLA-ID---------------

    public item_comprobante() {
        setTb_item_comprobante("item_comprobante");
        setId_iditem_comprobante("iditem_comprobante");
        setTipo_comprobante_MERCADERIA("MERCADERIA");
        setTipo_comprobante_CON_COMPROBANTE("CON_COMPROBANTE");
        setTipo_comprobante_SIN_COMPROBANTE("SIN_COMPROBANTE");
        setTipo_comprobante_BOLETA_DESPACHO("BOLETA_DESPACHO");
    }

    public static String getTb_item_comprobante() {
        return nom_tabla;
    }

    public static void setTb_item_comprobante(String nom_tabla) {
        item_comprobante.nom_tabla = nom_tabla;
    }

    public static String getId_iditem_comprobante() {
        return nom_idtabla;
    }

    public static void setId_iditem_comprobante(String nom_idtabla) {
        item_comprobante.nom_idtabla = nom_idtabla;
    }
//---------------GET-SET-CAMPOS---------------

    public int getC1iditem_comprobante() {
        return C1iditem_comprobante;
    }

    public void setC1iditem_comprobante(int C1iditem_comprobante) {
        this.C1iditem_comprobante = C1iditem_comprobante;
    }

    public String getC2fecha_creado() {
        return C2fecha_creado;
    }

    public void setC2fecha_creado(String C2fecha_creado) {
        this.C2fecha_creado = C2fecha_creado;
    }

    public String getC3creado_por() {
        return C3creado_por;
    }

    public void setC3creado_por(String C3creado_por) {
        this.C3creado_por = C3creado_por;
    }

    public String getC4descripcion() {
        return C4descripcion;
    }

    public void setC4descripcion(String C4descripcion) {
        this.C4descripcion = C4descripcion;
    }

    public double getC5monto() {
        return C5monto;
    }

    public void setC5monto(double C5monto) {
        this.C5monto = C5monto;
    }

    public String getC6referencia() {
        return C6referencia;
    }

    public void setC6referencia(String C6referencia) {
        this.C6referencia = C6referencia;
    }

    public String getC7imagen() {
        return C7imagen;
    }

    public void setC7imagen(String C7imagen) {
        this.C7imagen = C7imagen;
    }

    public int getC8fk_idliquidacion_proforma() {
        return C8fk_idliquidacion_proforma;
    }

    public void setC8fk_idliquidacion_proforma(int C8fk_idliquidacion_proforma) {
        this.C8fk_idliquidacion_proforma = C8fk_idliquidacion_proforma;
    }

    public int getC9fk_idtipo_comprobante() {
        return C9fk_idtipo_comprobante;
    }

    public void setC9fk_idtipo_comprobante(int C9fk_idtipo_gasto_liquidacion) {
        this.C9fk_idtipo_comprobante = C9fk_idtipo_gasto_liquidacion;
    }

    public String toString() {
        return "item_comprobante(" + ",iditem_comprobante=" + C1iditem_comprobante + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,descripcion=" + C4descripcion + " ,monto=" + C5monto + " ,referencia=" + C6referencia + " ,imagen=" + C7imagen + " ,fk_idliquidacion_proforma=" + C8fk_idliquidacion_proforma + " ,fk_idtipo_gasto_liquidacion=" + C9fk_idtipo_comprobante + " )";
    }

    /**
     * @return the C10tipo_comprobante
     */
    public String getC10tipo_comprobante() {
        return C10tipo_comprobante;
    }

    /**
     * @param C10tipo_comprobante the C10tipo_comprobante to set
     */
    public void setC10tipo_comprobante(String C10tipo_comprobante) {
        this.C10tipo_comprobante = C10tipo_comprobante;
    }
}
