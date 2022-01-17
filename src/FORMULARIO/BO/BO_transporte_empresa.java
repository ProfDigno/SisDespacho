	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_transporte_empresa;
	import FORMULARIO.ENTIDAD.transporte_empresa;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_transporte_empresa {
private DAO_transporte_empresa trem_dao = new DAO_transporte_empresa();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_transporte_empresa(transporte_empresa trem, JTable tbltabla) {
		String titulo = "insertar_transporte_empresa";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			trem_dao.insertar_transporte_empresa(conn, trem);
			trem_dao.actualizar_tabla_transporte_empresa(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, trem.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, trem.toString(), titulo);
			}
		}
	}
	public void update_transporte_empresa(transporte_empresa trem, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TRANSPORTE_EMPRESA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_transporte_empresa";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				trem_dao.update_transporte_empresa(conn, trem);
				trem_dao.actualizar_tabla_transporte_empresa(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, trem.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, trem.toString(), titulo);
				}
			}
		}
	}
}
