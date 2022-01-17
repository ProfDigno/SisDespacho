	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tercero_pais;
	import FORMULARIO.ENTIDAD.tercero_pais;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tercero_pais {
private DAO_tercero_pais tepa_dao = new DAO_tercero_pais();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tercero_pais(tercero_pais tepa, JTable tbltabla) {
		String titulo = "insertar_tercero_pais";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			tepa_dao.insertar_tercero_pais(conn, tepa);
			tepa_dao.actualizar_tabla_tercero_pais(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, tepa.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, tepa.toString(), titulo);
			}
		}
	}
	public void update_tercero_pais(tercero_pais tepa, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TERCERO_PAIS", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tercero_pais";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				tepa_dao.update_tercero_pais(conn, tepa);
				tepa_dao.actualizar_tabla_tercero_pais(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, tepa.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, tepa.toString(), titulo);
				}
			}
		}
	}
}
