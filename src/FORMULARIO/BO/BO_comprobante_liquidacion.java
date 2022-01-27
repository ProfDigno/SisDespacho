	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_comprobante_liquidacion;
	import FORMULARIO.ENTIDAD.comprobante_liquidacion;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_comprobante_liquidacion {
private DAO_comprobante_liquidacion coliq_dao = new DAO_comprobante_liquidacion();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_comprobante_liquidacion(comprobante_liquidacion coliq, JTable tbltabla) {
		String titulo = "insertar_comprobante_liquidacion";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			coliq_dao.insertar_comprobante_liquidacion(conn, coliq);
			coliq_dao.actualizar_tabla_comprobante_liquidacion(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, coliq.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, coliq.toString(), titulo);
			}
		}
	}
	public void update_comprobante_liquidacion(comprobante_liquidacion coliq, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR COMPROBANTE_LIQUIDACION", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_comprobante_liquidacion";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				coliq_dao.update_comprobante_liquidacion(conn, coliq);
				coliq_dao.actualizar_tabla_comprobante_liquidacion(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, coliq.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, coliq.toString(), titulo);
				}
			}
		}
	}
}
