	package FORMULARIO.ENTIDAD;
public class liquidacion_proforma {

    /**
     * @return the C23monto_boleto_despachante
     */
    public double getC23monto_boleto_despachante() {
        return C23monto_boleto_despachante;
    }

    /**
     * @param C23monto_boleto_despachante the C23monto_boleto_despachante to set
     */
    public void setC23monto_boleto_despachante(double C23monto_boleto_despachante) {
        this.C23monto_boleto_despachante = C23monto_boleto_despachante;
    }

    /**
     * @return the C24monto_honorario_despachante
     */
    public double getC24monto_honorario_despachante() {
        return C24monto_honorario_despachante;
    }

    /**
     * @param C24monto_honorario_despachante the C24monto_honorario_despachante to set
     */
    public void setC24monto_honorario_despachante(double C24monto_honorario_despachante) {
        this.C24monto_honorario_despachante = C24monto_honorario_despachante;
    }

    /**
     * @return the C25fk_idmoneda_cambio
     */
    public int getC25fk_idmoneda_cambio() {
        return C25fk_idmoneda_cambio;
    }

    /**
     * @param C25fk_idmoneda_cambio the C25fk_idmoneda_cambio to set
     */
    public void setC25fk_idmoneda_cambio(int C25fk_idmoneda_cambio) {
        this.C25fk_idmoneda_cambio = C25fk_idmoneda_cambio;
    }

    /**
     * @return the C21tipo_liquidacion
     */
    public String getC21tipo_liquidacion() {
        return C21tipo_liquidacion;
    }

    /**
     * @param C21tipo_liquidacion the C21tipo_liquidacion to set
     */
    public void setC21tipo_liquidacion(String C21tipo_liquidacion) {
        this.C21tipo_liquidacion = C21tipo_liquidacion;
    }

    /**
     * @return the C22fk_idhonorario_despacho
     */
    public int getC22fk_idhonorario_despacho() {
        return C22fk_idhonorario_despacho;
    }

    /**
     * @param C22fk_idhonorario_despacho the C22fk_idhonorario_despacho to set
     */
    public void setC22fk_idhonorario_despacho(int C22fk_idhonorario_despacho) {
        this.C22fk_idhonorario_despacho = C22fk_idhonorario_despacho;
    }

    /**
     * @return the C20tipo_tasa_cambio
     */
    public String getC20tipo_tasa_cambio() {
        return C20tipo_tasa_cambio;
    }

    /**
     * @param C20tipo_tasa_cambio the C20tipo_tasa_cambio to set
     */
    public void setC20tipo_tasa_cambio(String C20tipo_tasa_cambio) {
        this.C20tipo_tasa_cambio = C20tipo_tasa_cambio;
    }

    /**
     * @return the C19observacion
     */
    public String getC19observacion() {
        return C19observacion;
    }

    /**
     * @param C19observacion the C19observacion to set
     */
    public void setC19observacion(String C19observacion) {
        this.C19observacion = C19observacion;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idliquidacion_proforma;
private String C2fecha_creacion;
private String C3creado_por;
private String C4fecha_despacho;
private String C5contenedor_nro;
private String C6contenedor_tipo;
private String C7via_transporte;
private double C8monto_factura;
private double C9monto_sin_comprobante;
private double C10monto_con_comprobante;
private double C11tasa_cambio_aduana;
private double C12tasa_cambio_mercado;
private String C13estado;
private int C14fk_idtercero_importador;
private int C15fk_idtercero_despachante;
private int C16fk_idtercero_colaborador;
private int C17fk_idtercero_transportadora;
private int C18fk_idaduana;
private String C19observacion;
private String C20tipo_tasa_cambio;
private String C21tipo_liquidacion;
private int C22fk_idhonorario_despacho;
private double C23monto_boleto_despachante;
private double C24monto_honorario_despachante;
private int C25fk_idmoneda_cambio;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public liquidacion_proforma() {
		setTb_liquidacion_proforma("liquidacion_proforma");
		setId_idliquidacion_proforma("idliquidacion_proforma");
	}
	public static String getTb_liquidacion_proforma(){
		return nom_tabla;
	}
	public static void setTb_liquidacion_proforma(String nom_tabla){
		liquidacion_proforma.nom_tabla = nom_tabla;
	}
	public static String getId_idliquidacion_proforma(){
		return nom_idtabla;
	}
	public static void setId_idliquidacion_proforma(String nom_idtabla){
		liquidacion_proforma.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idliquidacion_proforma(){
		return C1idliquidacion_proforma;
	}
	public void setC1idliquidacion_proforma(int C1idliquidacion_proforma){
		this.C1idliquidacion_proforma = C1idliquidacion_proforma;
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
	public String getC4fecha_despacho(){
		return C4fecha_despacho;
	}
	public void setC4fecha_despacho(String C4fecha_despacho){
		this.C4fecha_despacho = C4fecha_despacho;
	}
	public String getC5contenedor_nro(){
		return C5contenedor_nro;
	}
	public void setC5contenedor_nro(String C5contenedor_nro){
		this.C5contenedor_nro = C5contenedor_nro;
	}
	public String getC6contenedor_tipo(){
		return C6contenedor_tipo;
	}
	public void setC6contenedor_tipo(String C6contenedor_tipo){
		this.C6contenedor_tipo = C6contenedor_tipo;
	}
	public String getC7via_transporte(){
		return C7via_transporte;
	}
	public void setC7via_transporte(String C7via_transporte){
		this.C7via_transporte = C7via_transporte;
	}
	public double getC8monto_factura(){
		return C8monto_factura;
	}
	public void setC8monto_factura(double C8monto_factura){
		this.C8monto_factura = C8monto_factura;
	}
	public double getC9monto_sin_comprobante(){
		return C9monto_sin_comprobante;
	}
	public void setC9monto_sin_comprobante(double C9monto_sin_comprobante){
		this.C9monto_sin_comprobante = C9monto_sin_comprobante;
	}
	public double getC10monto_con_comprobante(){
		return C10monto_con_comprobante;
	}
	public void setC10monto_con_comprobante(double C10monto_con_comprobante){
		this.C10monto_con_comprobante = C10monto_con_comprobante;
	}
	public double getC11tasa_cambio_aduana(){
		return C11tasa_cambio_aduana;
	}
	public void setC11tasa_cambio_aduana(double C11tasa_cambio_aduana){
		this.C11tasa_cambio_aduana = C11tasa_cambio_aduana;
	}
	public double getC12tasa_cambio_mercado(){
		return C12tasa_cambio_mercado;
	}
	public void setC12tasa_cambio_mercado(double C12tasa_cambio_mercado){
		this.C12tasa_cambio_mercado = C12tasa_cambio_mercado;
	}
	public String getC13estado(){
		return C13estado;
	}
	public void setC13estado(String C13estado){
		this.C13estado = C13estado;
	}
	public int getC14fk_idtercero_importador(){
		return C14fk_idtercero_importador;
	}
	public void setC14fk_idtercero_importador(int C14fk_idtercero_importador){
		this.C14fk_idtercero_importador = C14fk_idtercero_importador;
	}
	public int getC15fk_idtercero_despachante(){
		return C15fk_idtercero_despachante;
	}
	public void setC15fk_idtercero_despachante(int C15fk_idtercero_despachante){
		this.C15fk_idtercero_despachante = C15fk_idtercero_despachante;
	}
	public int getC16fk_idtercero_colaborador(){
		return C16fk_idtercero_colaborador;
	}
	public void setC16fk_idtercero_colaborador(int C16fk_idtercero_colaborador){
		this.C16fk_idtercero_colaborador = C16fk_idtercero_colaborador;
	}
	public int getC17fk_idtercero_transportadora(){
		return C17fk_idtercero_transportadora;
	}
	public void setC17fk_idtercero_transportadora(int C17fk_idtercero_transportadora){
		this.C17fk_idtercero_transportadora = C17fk_idtercero_transportadora;
	}
	public int getC18fk_idaduana(){
		return C18fk_idaduana;
	}
	public void setC18fk_idaduana(int C18fk_idaduana){
		this.C18fk_idaduana = C18fk_idaduana;
	}
	public String toString() {
		return "liquidacion_proforma(" + ",idliquidacion_proforma=" + C1idliquidacion_proforma + " ,fecha_creacion=" + C2fecha_creacion + " ,creado_por=" + C3creado_por + " ,fecha_despacho=" + C4fecha_despacho + " ,contenedor_nro=" + C5contenedor_nro + " ,contenedor_tipo=" + C6contenedor_tipo + " ,via_transporte=" + C7via_transporte + " ,monto_factura=" + C8monto_factura + " ,monto_sin_comprobante=" + C9monto_sin_comprobante + " ,monto_con_comprobante=" + C10monto_con_comprobante + " ,tasa_cambio_aduana=" + C11tasa_cambio_aduana + " ,tasa_cambio_mercado=" + C12tasa_cambio_mercado + " ,estado=" + C13estado + " ,fk_idtercero_importador=" + C14fk_idtercero_importador + " ,fk_idtercero_despachante=" + C15fk_idtercero_despachante + " ,fk_idtercero_colaborador=" + C16fk_idtercero_colaborador + " ,fk_idtercero_transportadora=" + C17fk_idtercero_transportadora + " ,fk_idaduana=" + C18fk_idaduana + " )";
	}
}
