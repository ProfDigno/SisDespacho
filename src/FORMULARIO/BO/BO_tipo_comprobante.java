package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_tipo_comprobante;
import FORMULARIO.ENTIDAD.tipo_comprobante;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_tipo_comprobante {

    private DAO_tipo_comprobante tgali_dao = new DAO_tipo_comprobante();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_tipo_comprobante(tipo_comprobante tgali, JTable tbltabla) {
        String titulo = "insertar_tipo_comprobante";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            tgali_dao.insertar_tipo_comprobante(conn, tgali);
//            tgali_dao.actualizar_tabla_tipo_comprobante(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, tgali.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, tgali.toString(), titulo);
            }
        }
    }

    public void update_tipo_comprobante(tipo_comprobante tgali, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_GASTO_LIQUIDACION", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tipo_comprobante";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                tgali_dao.update_tipo_comprobante(conn, tgali);
//                tgali_dao.actualizar_tabla_tipo_comprobante(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, tgali.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, tgali.toString(), titulo);
                }
            }
        }
    }
}
