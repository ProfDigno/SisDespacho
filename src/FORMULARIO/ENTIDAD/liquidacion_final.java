	package FORMULARIO.ENTIDAD;
public class liquidacion_final {

//---------------DECLARAR VARIABLES---------------
private int C1idliquidacion_final;
private String C2fecha_creado;
private String C3creado_por;
private String C4fecha_despacho;
private String C5despacho_numero;
private String C6tipo_liquidacion;
private String C7estado;
private String C8observacion;
private String C9contenedor_nro;
private String C10contenedor_tipo;
private String C11via_transporte;
private String C12transporte_condicion;
private double C13monto_imponible;
private double C14monto_ajuste_incluir;
private double C15monto_factura;
private double C16monto_flete;
private double C17monto_seguro;
private double C18monto_cif;
private double C19monto_total_despacho;
private double C20monto_adelanto;
private double C21monto_pagar;
private double C22tasa_cambio_aduana;
private double C23tasa_cambio_mercado;
private String C24tipo_tasa_cambio;
private String C25factura_numero;
private String C26monto_letra;
private int C27fk_idtipo_comprobante;
private int C28fk_idtercero_ciudad;
private int C29fk_idaduana;
private int C30fk_iddespacho_zona;
private int C31fk_idtransporte_empresa;
private int C32fk_idtercero_importador;
private int C33fk_idtercero_transportadora;
private int C34fk_idmoneda_cambio;
private int C35fk_idregimen;
private int C36fk_idincoterms;
private String C37fecha_pagado;
private double C38monto_pagado;
private String C39otro_nombre;
private double C40otro_monto;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public liquidacion_final() {
		setTb_liquidacion_final("liquidacion_final");
		setId_idliquidacion_final("idliquidacion_final");
	}
	public static String getTb_liquidacion_final(){
		return nom_tabla;
	}
	public static void setTb_liquidacion_final(String nom_tabla){
		liquidacion_final.nom_tabla = nom_tabla;
	}
	public static String getId_idliquidacion_final(){
		return nom_idtabla;
	}
	public static void setId_idliquidacion_final(String nom_idtabla){
		liquidacion_final.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idliquidacion_final(){
		return C1idliquidacion_final;
	}
	public void setC1idliquidacion_final(int C1idliquidacion_final){
		this.C1idliquidacion_final = C1idliquidacion_final;
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
	public String getC4fecha_despacho(){
		return C4fecha_despacho;
	}
	public void setC4fecha_despacho(String C4fecha_despacho){
		this.C4fecha_despacho = C4fecha_despacho;
	}
	public String getC5despacho_numero(){
		return C5despacho_numero;
	}
	public void setC5despacho_numero(String C5despacho_numero){
		this.C5despacho_numero = C5despacho_numero;
	}
	public String getC6tipo_liquidacion(){
		return C6tipo_liquidacion;
	}
	public void setC6tipo_liquidacion(String C6tipo_liquidacion){
		this.C6tipo_liquidacion = C6tipo_liquidacion;
	}
	public String getC7estado(){
		return C7estado;
	}
	public void setC7estado(String C7estado){
		this.C7estado = C7estado;
	}
	public String getC8observacion(){
		return C8observacion;
	}
	public void setC8observacion(String C8observacion){
		this.C8observacion = C8observacion;
	}
	public String getC9contenedor_nro(){
		return C9contenedor_nro;
	}
	public void setC9contenedor_nro(String C9contenedor_nro){
		this.C9contenedor_nro = C9contenedor_nro;
	}
	public String getC10contenedor_tipo(){
		return C10contenedor_tipo;
	}
	public void setC10contenedor_tipo(String C10contenedor_tipo){
		this.C10contenedor_tipo = C10contenedor_tipo;
	}
	public String getC11via_transporte(){
		return C11via_transporte;
	}
	public void setC11via_transporte(String C11via_transporte){
		this.C11via_transporte = C11via_transporte;
	}
	public String getC12transporte_condicion(){
		return C12transporte_condicion;
	}
	public void setC12transporte_condicion(String C12transporte_condicion){
		this.C12transporte_condicion = C12transporte_condicion;
	}
	public double getC13monto_imponible(){
		return C13monto_imponible;
	}
	public void setC13monto_imponible(double C13monto_imponible){
		this.C13monto_imponible = C13monto_imponible;
	}
	public double getC14monto_ajuste_incluir(){
		return C14monto_ajuste_incluir;
	}
	public void setC14monto_ajuste_incluir(double C14monto_ajuste_incluir){
		this.C14monto_ajuste_incluir = C14monto_ajuste_incluir;
	}
	public double getC15monto_factura(){
		return C15monto_factura;
	}
	public void setC15monto_factura(double C15monto_factura){
		this.C15monto_factura = C15monto_factura;
	}
	public double getC16monto_flete(){
		return C16monto_flete;
	}
	public void setC16monto_flete(double C16monto_flete){
		this.C16monto_flete = C16monto_flete;
	}
	public double getC17monto_seguro(){
		return C17monto_seguro;
	}
	public void setC17monto_seguro(double C17monto_seguro){
		this.C17monto_seguro = C17monto_seguro;
	}
	public double getC18monto_cif(){
		return C18monto_cif;
	}
	public void setC18monto_cif(double C18monto_cif){
		this.C18monto_cif = C18monto_cif;
	}
	public double getC19monto_total_despacho(){
		return C19monto_total_despacho;
	}
	public void setC19monto_total_despacho(double C19monto_total_despacho){
		this.C19monto_total_despacho = C19monto_total_despacho;
	}
	public double getC20monto_adelanto(){
		return C20monto_adelanto;
	}
	public void setC20monto_adelanto(double C20monto_adelanto){
		this.C20monto_adelanto = C20monto_adelanto;
	}
	public double getC21monto_pagar(){
		return C21monto_pagar;
	}
	public void setC21monto_pagar(double C21monto_pagar){
		this.C21monto_pagar = C21monto_pagar;
	}
	public double getC22tasa_cambio_aduana(){
		return C22tasa_cambio_aduana;
	}
	public void setC22tasa_cambio_aduana(double C22tasa_cambio_aduana){
		this.C22tasa_cambio_aduana = C22tasa_cambio_aduana;
	}
	public double getC23tasa_cambio_mercado(){
		return C23tasa_cambio_mercado;
	}
	public void setC23tasa_cambio_mercado(double C23tasa_cambio_mercado){
		this.C23tasa_cambio_mercado = C23tasa_cambio_mercado;
	}
	public String getC24tipo_tasa_cambio(){
		return C24tipo_tasa_cambio;
	}
	public void setC24tipo_tasa_cambio(String C24tipo_tasa_cambio){
		this.C24tipo_tasa_cambio = C24tipo_tasa_cambio;
	}
	public String getC25factura_numero(){
		return C25factura_numero;
	}
	public void setC25factura_numero(String C25factura_numero){
		this.C25factura_numero = C25factura_numero;
	}
	public String getC26monto_letra(){
		return C26monto_letra;
	}
	public void setC26monto_letra(String C26monto_letra){
		this.C26monto_letra = C26monto_letra;
	}
	public int getC27fk_idtipo_comprobante(){
		return C27fk_idtipo_comprobante;
	}
	public void setC27fk_idtipo_comprobante(int C27fk_idtipo_comprobante){
		this.C27fk_idtipo_comprobante = C27fk_idtipo_comprobante;
	}
	public int getC28fk_idtercero_ciudad(){
		return C28fk_idtercero_ciudad;
	}
	public void setC28fk_idtercero_ciudad(int C28fk_idtercero_ciudad){
		this.C28fk_idtercero_ciudad = C28fk_idtercero_ciudad;
	}
	public int getC29fk_idaduana(){
		return C29fk_idaduana;
	}
	public void setC29fk_idaduana(int C29fk_idaduana){
		this.C29fk_idaduana = C29fk_idaduana;
	}
	public int getC30fk_iddespacho_zona(){
		return C30fk_iddespacho_zona;
	}
	public void setC30fk_iddespacho_zona(int C30fk_iddespacho_zona){
		this.C30fk_iddespacho_zona = C30fk_iddespacho_zona;
	}
	public int getC31fk_idtransporte_empresa(){
		return C31fk_idtransporte_empresa;
	}
	public void setC31fk_idtransporte_empresa(int C31fk_idtransporte_empresa){
		this.C31fk_idtransporte_empresa = C31fk_idtransporte_empresa;
	}
	public int getC32fk_idtercero_importador(){
		return C32fk_idtercero_importador;
	}
	public void setC32fk_idtercero_importador(int C32fk_idtercero_importador){
		this.C32fk_idtercero_importador = C32fk_idtercero_importador;
	}
	public int getC33fk_idtercero_transportadora(){
		return C33fk_idtercero_transportadora;
	}
	public void setC33fk_idtercero_transportadora(int C33fk_idtercero_transportadora){
		this.C33fk_idtercero_transportadora = C33fk_idtercero_transportadora;
	}
	public int getC34fk_idmoneda_cambio(){
		return C34fk_idmoneda_cambio;
	}
	public void setC34fk_idmoneda_cambio(int C34fk_idmoneda_cambio){
		this.C34fk_idmoneda_cambio = C34fk_idmoneda_cambio;
	}
	public int getC35fk_idregimen(){
		return C35fk_idregimen;
	}
	public void setC35fk_idregimen(int C35fk_idregimen){
		this.C35fk_idregimen = C35fk_idregimen;
	}
	public int getC36fk_idincoterms(){
		return C36fk_idincoterms;
	}
	public void setC36fk_idincoterms(int C36fk_idincoterms){
		this.C36fk_idincoterms = C36fk_idincoterms;
	}

    public String getC37fecha_pagado() {
        return C37fecha_pagado;
    }

    public void setC37fecha_pagado(String C37fecha_pagado) {
        this.C37fecha_pagado = C37fecha_pagado;
    }

    public double getC38monto_pagado() {
        return C38monto_pagado;
    }

    public void setC38monto_pagado(double C38monto_pagado) {
        this.C38monto_pagado = C38monto_pagado;
    }

    public String getC39otro_nombre() {
        return C39otro_nombre;
    }

    public void setC39otro_nombre(String C39otro_nombre) {
        this.C39otro_nombre = C39otro_nombre;
    }

    public double getC40otro_monto() {
        return C40otro_monto;
    }

    public void setC40otro_monto(double C40otro_monto) {
        this.C40otro_monto = C40otro_monto;
    }
        
	public String toString() {
		return "liquidacion_final(" + ",idliquidacion_final=" + C1idliquidacion_final + " ,fecha_creado=" + C2fecha_creado + " ,creado_por=" + C3creado_por + " ,fecha_despacho=" + C4fecha_despacho + " ,despacho_numero=" + C5despacho_numero + " ,tipo_liquidacion=" + C6tipo_liquidacion + " ,estado=" + C7estado + " ,observacion=" + C8observacion + " ,contenedor_nro=" + C9contenedor_nro + " ,contenedor_tipo=" + C10contenedor_tipo + " ,via_transporte=" + C11via_transporte + " ,transporte_condicion=" + C12transporte_condicion + " ,monto_imponible=" + C13monto_imponible + " ,monto_ajuste_incluir=" + C14monto_ajuste_incluir + " ,monto_factura=" + C15monto_factura + " ,monto_flete=" + C16monto_flete + " ,monto_seguro=" + C17monto_seguro + " ,monto_cif=" + C18monto_cif + " ,monto_total_despacho=" + C19monto_total_despacho + " ,monto_adelanto=" + C20monto_adelanto + " ,monto_pagar=" + C21monto_pagar + " ,tasa_cambio_aduana=" + C22tasa_cambio_aduana + " ,tasa_cambio_mercado=" + C23tasa_cambio_mercado + " ,tipo_tasa_cambio=" + C24tipo_tasa_cambio + " ,factura_numero=" + C25factura_numero + " ,monto_letra=" + C26monto_letra + " ,fk_idtipo_comprobante=" + C27fk_idtipo_comprobante + " ,fk_idtercero_ciudad=" + C28fk_idtercero_ciudad + " ,fk_idaduana=" + C29fk_idaduana + " ,fk_iddespacho_zona=" + C30fk_iddespacho_zona + " ,fk_idtransporte_empresa=" + C31fk_idtransporte_empresa + " ,fk_idtercero_importador=" + C32fk_idtercero_importador + " ,fk_idtercero_transportadora=" + C33fk_idtercero_transportadora + " ,fk_idmoneda_cambio=" + C34fk_idmoneda_cambio + " ,fk_idregimen=" + C35fk_idregimen + " ,fk_idincoterms=" + C36fk_idincoterms + " )";
	}
}
