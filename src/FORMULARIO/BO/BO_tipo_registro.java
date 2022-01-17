	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tipo_registro;
	import FORMULARIO.ENTIDAD.tipo_registro;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tipo_registro {
private DAO_tipo_registro treg_dao = new DAO_tipo_registro();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tipo_registro(tipo_registro treg, JTable tbltabla) {
		String titulo = "insertar_tipo_registro";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			treg_dao.insertar_tipo_registro(conn, treg);
			treg_dao.actualizar_tabla_tipo_registro(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, treg.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, treg.toString(), titulo);
			}
		}
	}
	public void update_tipo_registro(tipo_registro treg, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_REGISTRO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tipo_registro";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				treg_dao.update_tipo_registro(conn, treg);
				treg_dao.actualizar_tabla_tipo_registro(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, treg.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, treg.toString(), titulo);
				}
			}
		}
	}
}
