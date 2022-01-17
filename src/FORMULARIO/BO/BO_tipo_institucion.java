	package FORMULARIO.BO;
	import BASEDATO.LOCAL.ConnPostgres;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import FORMULARIO.DAO.DAO_tipo_institucion;
	import FORMULARIO.ENTIDAD.tipo_institucion;
	import java.sql.Connection;
	import java.sql.SQLException;
	import javax.swing.JTable;
public class BO_tipo_institucion {
private DAO_tipo_institucion tins_dao = new DAO_tipo_institucion();
	EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

	public void insertar_tipo_institucion(tipo_institucion tins, JTable tbltabla) {
		String titulo = "insertar_tipo_institucion";
		Connection conn = ConnPostgres.getConnPosgres();
		try {
			if (conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			tins_dao.insertar_tipo_institucion(conn, tins);
			tins_dao.actualizar_tabla_tipo_institucion(conn, tbltabla);
			conn.commit();
		} catch (SQLException e) {
			evmen.mensaje_error(e, tins.toString(), titulo);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				evmen.Imprimir_serial_sql_error(e1, tins.toString(), titulo);
			}
		}
	}
	public void update_tipo_institucion(tipo_institucion tins, JTable tbltabla) {
		if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_INSTITUCION", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
			String titulo = "update_tipo_institucion";
			Connection conn = ConnPostgres.getConnPosgres();
			try {
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				tins_dao.update_tipo_institucion(conn, tins);
				tins_dao.actualizar_tabla_tipo_institucion(conn, tbltabla);
				conn.commit();
			} catch (SQLException e) {
				evmen.mensaje_error(e, tins.toString(), titulo);
				try {
					conn.rollback();
				}catch (SQLException e1) {
					evmen.Imprimir_serial_sql_error(e1, tins.toString(), titulo);
				}
			}
		}
	}
}
