	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tercero_ciudad;
	import FORMULARIO.ENTIDAD.tercero_ciudad;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tercero_ciudad {
private DAO_tercero_ciudad teciu_dao = new DAO_tercero_ciudad();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tercero_ciudad(tercero_ciudad teciu, JTable tbltabla) {
		String titulo = "insertar_tercero_ciudad";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			teciu_dao.insertar_tercero_ciudad(conn, teciu);
			teciu_dao.actualizar_tabla_tercero_ciudad(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, teciu.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, teciu.toString(), titulo);
			}
		}
	}
	public void update_tercero_ciudad(tercero_ciudad teciu, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TERCERO_CIUDAD", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tercero_ciudad";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				teciu_dao.update_tercero_ciudad(conn, teciu);
				teciu_dao.actualizar_tabla_tercero_ciudad(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, teciu.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, teciu.toString(), titulo);
				}
			}
		}
	}
}
