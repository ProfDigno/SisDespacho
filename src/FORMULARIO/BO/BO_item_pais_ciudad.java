	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_item_pais_ciudad;
	import FORMULARIO.ENTIDAD.item_pais_ciudad;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_item_pais_ciudad {
private DAO_item_pais_ciudad ipaciu_dao = new DAO_item_pais_ciudad();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_item_pais_ciudad(item_pais_ciudad ipaciu, JTable tbltabla) {
		String titulo = "insertar_item_pais_ciudad";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			ipaciu_dao.insertar_item_pais_ciudad(conn, ipaciu);
			ipaciu_dao.actualizar_tabla_item_pais_ciudad(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, ipaciu.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, ipaciu.toString(), titulo);
			}
		}
	}
	public void update_item_pais_ciudad(item_pais_ciudad ipaciu, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_PAIS_CIUDAD", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_item_pais_ciudad";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				ipaciu_dao.update_item_pais_ciudad(conn, ipaciu);
				ipaciu_dao.actualizar_tabla_item_pais_ciudad(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, ipaciu.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, ipaciu.toString(), titulo);
				}
			}
		}
	}
}
