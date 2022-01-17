	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tercero;
	import FORMULARIO.ENTIDAD.tercero;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tercero {
private DAO_tercero ter_dao = new DAO_tercero();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tercero(tercero ter, JTable tbltabla) {
		String titulo = "insertar_tercero";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			ter_dao.insertar_tercero(conn, ter);
			ter_dao.actualizar_tabla_tercero(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, ter.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
			}
		}
	}
	public void update_tercero(tercero ter, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TERCERO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tercero";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				ter_dao.update_tercero(conn, ter);
				ter_dao.actualizar_tabla_tercero(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, ter.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
				}
			}
		}
	}
}
