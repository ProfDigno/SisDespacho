	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_encomienta;
	import FORMULARIO.ENTIDAD.encomienta;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_encomienta {
private DAO_encomienta enco_dao = new DAO_encomienta();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_encomienta(encomienta enco, JTable tbltabla) {
		String titulo = "insertar_encomienta";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			enco_dao.insertar_encomienta(conn, enco);
			enco_dao.actualizar_tabla_encomienta(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, enco.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, enco.toString(), titulo);
			}
		}
	}
	public void update_encomienta(encomienta enco, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ENCOMIENTA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_encomienta";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				enco_dao.update_encomienta(conn, enco);
				enco_dao.actualizar_tabla_encomienta(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, enco.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, enco.toString(), titulo);
				}
			}
		}
	}
}
