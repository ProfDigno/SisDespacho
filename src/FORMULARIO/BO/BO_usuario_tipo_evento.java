	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_usuario_tipo_evento;
	import FORMULARIO.ENTIDAD.usuario_tipo_evento;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_usuario_tipo_evento {
private DAO_usuario_tipo_evento usute_dao = new DAO_usuario_tipo_evento();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_usuario_tipo_evento(usuario_tipo_evento usute, JTable tbltabla) {
		String titulo = "insertar_usuario_tipo_evento";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			usute_dao.insertar_usuario_tipo_evento(conn, usute);
			usute_dao.actualizar_tabla_usuario_tipo_evento(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, usute.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, usute.toString(), titulo);
			}
		}
	}
	public void update_usuario_tipo_evento(usuario_tipo_evento usute, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR USUARIO_TIPO_EVENTO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_usuario_tipo_evento";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				usute_dao.update_usuario_tipo_evento(conn, usute);
				usute_dao.actualizar_tabla_usuario_tipo_evento(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, usute.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, usute.toString(), titulo);
				}
			}
		}
	}
}
