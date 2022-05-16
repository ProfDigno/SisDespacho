	package FORMULARIO.ENTIDAD;
public class comprobante_liquidacion {

//---------------DECLARAR VARIABLES---------------
private int C1idcomprobante_liquidacion;
private String C2descripcion;
private double C3por_iva;
private String C4tipo_iva;
private boolean C5nro_despacho;
private boolean C6eliminado;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public comprobante_liquidacion() {
		setTb_comprobante_liquidacion("comprobante_liquidacion");
		setId_idcomprobante_liquidacion("idcomprobante_liquidacion");
	}
	public static String getTb_comprobante_liquidacion(){
		return nom_tabla;
	}
	public static void setTb_comprobante_liquidacion(String nom_tabla){
		comprobante_liquidacion.nom_tabla = nom_tabla;
	}
	public static String getId_idcomprobante_liquidacion(){
		return nom_idtabla;
	}
	public static void setId_idcomprobante_liquidacion(String nom_idtabla){
		comprobante_liquidacion.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idcomprobante_liquidacion(){
		return C1idcomprobante_liquidacion;
	}
	public void setC1idcomprobante_liquidacion(int C1idcomprobante_liquidacion){
		this.C1idcomprobante_liquidacion = C1idcomprobante_liquidacion;
	}
	public String getC2descripcion(){
		return C2descripcion;
	}
	public void setC2descripcion(String C2descripcion){
		this.C2descripcion = C2descripcion;
	}
	public double getC3por_iva(){
		return C3por_iva;
	}
	public void setC3por_iva(double C3por_iva){
		this.C3por_iva = C3por_iva;
	}
	
	public boolean getC5nro_despacho(){
		return C5nro_despacho;
	}
	public void setC5nro_despacho(boolean C5nro_despacho){
		this.C5nro_despacho = C5nro_despacho;
	}

    public String getC4tipo_iva() {
        return C4tipo_iva;
    }

    public void setC4tipo_iva(String C4tipo_iva) {
        this.C4tipo_iva = C4tipo_iva;
    }

    public boolean getC6eliminado() {
        return C6eliminado;
    }

    public void setC6eliminado(boolean C6eliminado) {
        this.C6eliminado = C6eliminado;
    }

    @Override
    public String toString() {
        return "comprobante_liquidacion{" + "C1idcomprobante_liquidacion=" + C1idcomprobante_liquidacion + ", C2descripcion=" + C2descripcion + ", C3por_iva=" + C3por_iva + ", C4tipo_iva=" + C4tipo_iva + ", C5nro_despacho=" + C5nro_despacho + '}';
    }
	
}
