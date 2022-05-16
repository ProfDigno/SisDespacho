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

    public void update_tipo_comprobante(tipo_comprobante tgali, JTable tbltabla, boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR TIPO_GASTO_LIQUIDACION";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        } else {
            mensaje = "ESTAS SEGURO DE ELIMINAR TIPO_GASTO_LIQUIDACION";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_tipo_comprobante";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                tgali_dao.update_tipo_comprobante(conn, tgali);
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
