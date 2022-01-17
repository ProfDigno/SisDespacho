	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.dao_item_usuario_rol;
	import FORMULARIO.ENTIDAD.entidad_item_usuario_rol;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_item_usuario_rol {
private dao_item_usuario_rol iurol_dao = new dao_item_usuario_rol();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_item_usuario_rol(entidad_item_usuario_rol iurol, JTable tbltabla) {
		String titulo = "insertar_item_usuario_rol";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			iurol_dao.insertar_item_usuario_rol(conn, iurol);
			iurol_dao.actualizar_tabla_item_usuario_rol(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, iurol.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, iurol.toString(), titulo);
			}
		}
	}
	public void update_item_usuario_rol(entidad_item_usuario_rol iurol, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_USUARIO_ROL", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_item_usuario_rol";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				iurol_dao.update_item_usuario_rol(conn, iurol);
				iurol_dao.actualizar_tabla_item_usuario_rol(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, iurol.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, iurol.toString(), titulo);
				}
			}
		}
	}
}
