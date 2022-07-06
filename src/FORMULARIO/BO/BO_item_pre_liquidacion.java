package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_pre_liquidacion;
import FORMULARIO.ENTIDAD.item_pre_liquidacion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_pre_liquidacion {

    private DAO_item_pre_liquidacion ipl_dao = new DAO_item_pre_liquidacion();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_pre_liquidacion(item_pre_liquidacion ipl, JTable tbltabla) {
        String titulo = "insertar_item_pre_liquidacion";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ipl_dao.insertar_item_pre_liquidacion(conn, ipl);
            ipl_dao.actualizar_tabla_item_pre_liquidacion(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ipl.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ipl.toString(), titulo);
            }
        }
    }

    public void update_item_pre_liquidacion(item_pre_liquidacion ipl, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_PRE_LIQUIDACION", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_pre_liquidacion";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ipl_dao.update_item_pre_liquidacion(conn, ipl);
                ipl_dao.actualizar_tabla_item_pre_liquidacion(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ipl.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ipl.toString(), titulo);
                }
            }
        }
    }
}
