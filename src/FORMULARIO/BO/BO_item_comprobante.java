package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_comprobante;
import FORMULARIO.ENTIDAD.item_comprobante;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_comprobante {

    private DAO_item_comprobante iscom_dao = new DAO_item_comprobante();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_comprobante(item_comprobante iscom, JTable tbltabla) {
        String titulo = "insertar_item_comprobante";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            iscom_dao.insertar_item_comprobante(conn, iscom);
            iscom_dao.actualizar_tabla_item_comprobante(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, iscom.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, iscom.toString(), titulo);
            }
        }
    }

    public void update_item_comprobante(item_comprobante iscom, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_COMPROBANTE", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_sin_comprobante";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                iscom_dao.update_item_comprobante(conn, iscom);
                iscom_dao.actualizar_tabla_item_comprobante(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, iscom.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, iscom.toString(), titulo);
                }
            }
        }
    }
}
