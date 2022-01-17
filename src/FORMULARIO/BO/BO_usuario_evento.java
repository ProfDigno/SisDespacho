package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.dao_usuario_evento;
import FORMULARIO.ENTIDAD.entidad_usuario_evento;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_usuario_evento {

    private dao_usuario_evento uven_dao = new dao_usuario_evento();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_usuario_evento(entidad_usuario_evento uven, JTable tbltabla) {
        String titulo = "insertar_usuario_evento";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            uven_dao.insertar_usuario_evento(conn, uven);
            uven_dao.actualizar_tabla_usuario_evento(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, uven.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, uven.toString(), titulo);
            }
        }
    }

    public void update_usuario_evento(entidad_usuario_evento uven, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR USUARIO_EVENTO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_usuario_evento";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                uven_dao.update_usuario_evento(conn, uven);
                uven_dao.actualizar_tabla_usuario_evento(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, uven.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, uven.toString(), titulo);
                }
            }
        }
    }
}
