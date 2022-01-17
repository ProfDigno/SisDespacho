	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_factura_nro_proforma;
	import FORMULARIO.ENTIDAD.factura_nro_proforma;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_factura_nro_proforma {
private DAO_factura_nro_proforma facnro_dao = new DAO_factura_nro_proforma();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_factura_nro_proforma(factura_nro_proforma facnro, JTable tbltabla) {
		String titulo = "insertar_factura_nro_proforma";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			facnro_dao.insertar_factura_nro_proforma(conn, facnro);
			facnro_dao.actualizar_tabla_factura_nro_proforma(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, facnro.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, facnro.toString(), titulo);
			}
		}
	}
	public void update_factura_nro_proforma(factura_nro_proforma facnro, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR FACTURA_NRO_PROFORMA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_factura_nro_proforma";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				facnro_dao.update_factura_nro_proforma(conn, facnro);
				facnro_dao.actualizar_tabla_factura_nro_proforma(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, facnro.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, facnro.toString(), titulo);
				}
			}
		}
	}
}
