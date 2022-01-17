	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_regimen;
	import FORMULARIO.ENTIDAD.regimen;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_regimen {
private DAO_regimen reg_dao = new DAO_regimen();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_regimen(regimen reg, JTable tbltabla) {
		String titulo = "insertar_regimen";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			reg_dao.insertar_regimen(conn, reg);
			reg_dao.actualizar_tabla_regimen(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, reg.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, reg.toString(), titulo);
			}
		}
	}
	public void update_regimen(regimen reg, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR REGIMEN", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_regimen";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				reg_dao.update_regimen(conn, reg);
				reg_dao.actualizar_tabla_regimen(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, reg.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, reg.toString(), titulo);
				}
			}
		}
	}
}
