package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_tercero_rubro;
import FORMULARIO.ENTIDAD.tercero_rubro;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_tercero_rubro {

    private DAO_tercero_rubro rub_dao = new DAO_tercero_rubro();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_tercero_rubro(tercero_rubro rub, JTable tbltabla) {
        String titulo = "insertar_tercero_rubro";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            rub_dao.insertar_tercero_rubro(conn, rub);
            rub_dao.actualizar_tabla_tercero_rubro(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, rub.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, rub.toString(), titulo);
            }
        }
    }

    public void update_tercero_rubro(tercero_rubro rub, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TERCERO_RUBRO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tercero_rubro";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                rub_dao.update_tercero_rubro(conn, rub);
                rub_dao.actualizar_tabla_tercero_rubro(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, rub.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, rub.toString(), titulo);
                }
            }
        }
    }
    public void update_tercero_rubro_eliminar(tercero_rubro rub, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ELIMINAR TERCERO_RUBRO", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tercero_rubro";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                rub_dao.update_tercero_rubro_eliminar(conn, rub);
                rub_dao.actualizar_tabla_tercero_rubro(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, rub.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, rub.toString(), titulo);
                }
            }
        }
    }
}
