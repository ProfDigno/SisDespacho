	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tipo_dependencia;
	import FORMULARIO.ENTIDAD.tipo_dependencia;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tipo_dependencia {
private DAO_tipo_dependencia tdep_dao = new DAO_tipo_dependencia();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tipo_dependencia(tipo_dependencia tdep, JTable tbltabla) {
		String titulo = "insertar_tipo_dependencia";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			tdep_dao.insertar_tipo_dependencia(conn, tdep);
			tdep_dao.actualizar_tabla_tipo_dependencia(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, tdep.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, tdep.toString(), titulo);
			}
		}
	}
	public void update_tipo_dependencia(tipo_dependencia tdep, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_DEPENDENCIA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tipo_dependencia";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				tdep_dao.update_tipo_dependencia(conn, tdep);
				tdep_dao.actualizar_tabla_tipo_dependencia(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, tdep.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, tdep.toString(), titulo);
				}
			}
		}
	}
}
