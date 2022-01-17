	package FORMULARIO.ENTIDAD;
public class entidad_usuario {

    /**
     * @return the global_idusuario
     */
    public static int getGlobal_idusuario() {
        return global_idusuario;
    }

    /**
     * @param aGlobal_idusuario the global_idusuario to set
     */
    public static void setGlobal_idusuario(int aGlobal_idusuario) {
        global_idusuario = aGlobal_idusuario;
    }

    /**
     * @return the global_nombre
     */
    public static String getGlobal_nombre() {
        return global_nombre;
    }

    /**
     * @param aGlobal_nombre the global_nombre to set
     */
    public static void setGlobal_nombre(String aGlobal_nombre) {
        global_nombre = aGlobal_nombre;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idusuario;
private String C2usuario;
private String C3senha;
private String C4nombre;
private int C5fk_idusuario_rol;
private String C6nombre_rol;
private static String nom_tabla;
private static String nom_idtabla;
private static int global_idusuario;
private static String global_nombre;
//---------------TABLA-ID---------------
	public entidad_usuario() {
		setTb_usuario("usuario");
		setId_idusuario("idusuario");
	}
	public static String getTb_usuario(){
		return nom_tabla;
	}
	public static void setTb_usuario(String nom_tabla){
		entidad_usuario.nom_tabla = nom_tabla;
	}
	public static String getId_idusuario(){
		return nom_idtabla;
	}
	public static void setId_idusuario(String nom_idtabla){
		entidad_usuario.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idusuario(){
		return C1idusuario;
	}
	public void setC1idusuario(int C1idusuario){
		this.C1idusuario = C1idusuario;
	}
	public String getC2usuario(){
		return C2usuario;
	}
	public void setC2usuario(String C2usuario){
		this.C2usuario = C2usuario;
	}
	public String getC3senha(){
		return C3senha;
	}
	public void setC3senha(String C3senha){
		this.C3senha = C3senha;
	}
	public String getC4nombre(){
		return C4nombre;
	}
	public void setC4nombre(String C4nombre){
		this.C4nombre = C4nombre;
	}
	public int getC5fk_idusuario_rol(){
		return C5fk_idusuario_rol;
	}
	public void setC5fk_idusuario_rol(int C5fk_idusuario_rol){
		this.C5fk_idusuario_rol = C5fk_idusuario_rol;
	}
	public String toString() {
		return "usuario(" + ",idusuario=" + C1idusuario + " ,usuario=" + C2usuario + " ,senha=" + C3senha + " ,nombre=" + C4nombre + " ,fk_idusuario_rol=" + C5fk_idusuario_rol + " )";
	}

    /**
     * @return the C5nombre_rol
     */
    public String getC6nombre_rol() {
        return C6nombre_rol;
    }

    /**
     * @param C6nombre_rol the C5nombre_rol to set
     */
    public void setC6nombre_rol(String C6nombre_rol) {
        this.C6nombre_rol = C6nombre_rol;
    }
}
