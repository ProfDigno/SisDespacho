package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.ENTIDAD.item_liquidacion_final;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_liquidacion_final {

    private DAO_item_liquidacion_final itemfin_dao = new DAO_item_liquidacion_final();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_liquidacion_final(item_liquidacion_final itemfin, JTable tbltabla) {
        String titulo = "insertar_item_liquidacion_final";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            itemfin_dao.insertar_item_liquidacion_final(conn, itemfin);
            itemfin_dao.actualizar_tabla_item_liquidacion_final(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, itemfin.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, itemfin.toString(), titulo);
            }
        }
    }

    public void update_item_liquidacion_final(item_liquidacion_final itemfin, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_LIQUIDACION_FINAL", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_liquidacion_final";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                itemfin_dao.update_item_liquidacion_final(conn, itemfin);
                itemfin_dao.actualizar_tabla_item_liquidacion_final(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, itemfin.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, itemfin.toString(), titulo);
                }
            }
        }
    }
}
