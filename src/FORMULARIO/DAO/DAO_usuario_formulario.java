	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.usuario_formulario;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_usuario_formulario {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "USUARIO_FORMULARIO GUARDADO CORRECTAMENTE";
	private String mensaje_update = "USUARIO_FORMULARIO MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO usuario_formulario(idusuario_formulario,nombre) VALUES (?,?);";
	private String sql_update = "UPDATE usuario_formulario SET nombre=? WHERE idusuario_formulario=?;";
	private String sql_select = "SELECT idusuario_formulario,nombre FROM usuario_formulario order by 1 desc;";
	private String sql_cargar = "SELECT idusuario_formulario,nombre FROM usuario_formulario WHERE idusuario_formulario=";
	public void insertar_usuario_formulario(Connection conn, usuario_formulario usufrm){
		usufrm.setC1idusuario_formulario(eveconn.getInt_ultimoID_mas_uno(conn, usufrm.getTb_usuario_formulario(), usufrm.getId_idusuario_formulario()));
		String titulo = "insertar_usuario_formulario";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,usufrm.getC1idusuario_formulario());
			pst.setString(2,usufrm.getC2nombre());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + usufrm.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + usufrm.toString(), titulo);
		}
	}
	public void update_usuario_formulario(Connection conn, usuario_formulario usufrm){
		String titulo = "update_usuario_formulario";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,usufrm.getC2nombre());
			pst.setInt(2,usufrm.getC1idusuario_formulario());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + usufrm.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + usufrm.toString(), titulo);
		}
	}
	public void cargar_usuario_formulario(Connection conn, usuario_formulario usufrm,int id){
		String titulo = "Cargar_usuario_formulario";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				usufrm.setC1idusuario_formulario(rs.getInt(1));
				usufrm.setC2nombre(rs.getString(2));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + usufrm.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + usufrm.toString(), titulo);
		}
	}
	public void actualizar_tabla_usuario_formulario(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_usuario_formulario(tbltabla);
	}
	public void ancho_tabla_usuario_formulario(JTable tbltabla){
		int Ancho[]={50,50};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
