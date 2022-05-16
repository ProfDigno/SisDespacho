	package FORMULARIO.ENTIDAD;
public class pre_item_liquidacion_final {

//---------------DECLARAR VARIABLES---------------
private int C1idpre_item_liquidacion_final;
private int C2orden;
private int C3fk_idcomprobante_liquidacion;
private boolean C4eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public pre_item_liquidacion_final() {
		setTb_pre_item_liquidacion_final("pre_item_liquidacion_final");
		setId_idpre_item_liquidacion_final("idpre_item_liquidacion_final");
	}
	public static String getTb_pre_item_liquidacion_final(){
		return nom_tabla;
	}
	public static void setTb_pre_item_liquidacion_final(String nom_tabla){
		pre_item_liquidacion_final.nom_tabla = nom_tabla;
	}
	public static String getId_idpre_item_liquidacion_final(){
		return nom_idtabla;
	}
	public static void setId_idpre_item_liquidacion_final(String nom_idtabla){
		pre_item_liquidacion_final.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idpre_item_liquidacion_final(){
		return C1idpre_item_liquidacion_final;
	}
	public void setC1idpre_item_liquidacion_final(int C1idpre_item_liquidacion_final){
		this.C1idpre_item_liquidacion_final = C1idpre_item_liquidacion_final;
	}
	public int getC2orden(){
		return C2orden;
	}
	public void setC2orden(int C2orden){
		this.C2orden = C2orden;
	}
	public int getC3fk_idcomprobante_liquidacion(){
		return C3fk_idcomprobante_liquidacion;
	}
	public void setC3fk_idcomprobante_liquidacion(int C3fk_idcomprobante_liquidacion){
		this.C3fk_idcomprobante_liquidacion = C3fk_idcomprobante_liquidacion;
	}

    public boolean getC4eliminado() {
        return C4eliminado;
    }

    public void setC4eliminado(boolean C4eliminado) {
        this.C4eliminado = C4eliminado;
    }
        
	public String toString() {
		return "pre_item_liquidacion_final(" + ",idpre_item_liquidacion_final=" + C1idpre_item_liquidacion_final + " ,orden=" + C2orden + " ,fk_idcomprobante_liquidacion=" + C3fk_idcomprobante_liquidacion + " )";
	}
}
