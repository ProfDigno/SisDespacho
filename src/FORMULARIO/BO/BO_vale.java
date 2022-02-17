package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_caja_detalle;
import FORMULARIO.DAO.DAO_vale;
import FORMULARIO.ENTIDAD.caja_detalle;
import FORMULARIO.ENTIDAD.vale;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_vale {

    private DAO_vale val_dao = new DAO_vale();
    private DAO_caja_detalle caja_dao = new DAO_caja_detalle();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_vale(vale val,caja_detalle caja) {
        String titulo = "insertar_vale";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            val_dao.insertar_vale(conn, val);
            caja_dao.insertar_caja_detalle(conn, caja);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, val.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, val.toString(), titulo);
            }
        }
    }

    public void update_vale(vale val) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR VALE", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_vale";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                val_dao.update_vale(conn, val);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, val.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, val.toString(), titulo);
                }
            }
        }
    }
    public void anular_vale(vale val,caja_detalle caja) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR VALE", "ANULAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_vale";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                val_dao.anular_vale(conn, val);
                caja_dao.update_caja_detalle_estado(conn, caja);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, val.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, val.toString(), titulo);
                }
            }
        }
    }
}
