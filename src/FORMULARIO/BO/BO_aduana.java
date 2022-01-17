	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_aduana;
	import FORMULARIO.ENTIDAD.aduana;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_aduana {
private DAO_aduana adu_dao = new DAO_aduana();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_aduana(aduana adu, JTable tbltabla) {
		String titulo = "insertar_aduana";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			adu_dao.insertar_aduana(conn, adu);
			adu_dao.actualizar_tabla_aduana(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, adu.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, adu.toString(), titulo);
			}
		}
	}
	public void update_aduana(aduana adu, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ADUANA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_aduana";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				adu_dao.update_aduana(conn, adu);
				adu_dao.actualizar_tabla_aduana(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, adu.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, adu.toString(), titulo);
				}
			}
		}
	}
}
