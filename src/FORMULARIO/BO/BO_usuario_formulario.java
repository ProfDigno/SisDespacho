	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_usuario_formulario;
	import FORMULARIO.ENTIDAD.usuario_formulario;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_usuario_formulario {
private DAO_usuario_formulario usufrm_dao = new DAO_usuario_formulario();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_usuario_formulario(usuario_formulario usufrm, JTable tbltabla) {
		String titulo = "insertar_usuario_formulario";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			usufrm_dao.insertar_usuario_formulario(conn, usufrm);
			usufrm_dao.actualizar_tabla_usuario_formulario(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, usufrm.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, usufrm.toString(), titulo);
			}
		}
	}
	public void update_usuario_formulario(usuario_formulario usufrm, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR USUARIO_FORMULARIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_usuario_formulario";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				usufrm_dao.update_usuario_formulario(conn, usufrm);
				usufrm_dao.actualizar_tabla_usuario_formulario(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, usufrm.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, usufrm.toString(), titulo);
				}
			}
		}
	}
}
