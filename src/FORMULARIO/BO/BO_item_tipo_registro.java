package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_tipo_registro;
import FORMULARIO.ENTIDAD.item_tipo_registro;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_item_tipo_registro {

    private DAO_item_tipo_registro itreg_dao = new DAO_item_tipo_registro();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_item_tipo_registro(item_tipo_registro itreg, JTable tbltabla) {
        String titulo = "insertar_item_tipo_registro";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            itreg_dao.insertar_item_tipo_registro(conn, itreg);
            itreg_dao.actualizar_tabla_item_tipo_registro(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, itreg.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, itreg.toString(), titulo);
            }
        }
    }

    public void update_item_tipo_registro(item_tipo_registro itreg, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR ITEM_TIPO_REGISTRO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_item_tipo_registro";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                itreg_dao.update_item_tipo_registro(conn, itreg);
                itreg_dao.actualizar_tabla_item_tipo_registro(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, itreg.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, itreg.toString(), titulo);
                }
            }
        }
    }
}
