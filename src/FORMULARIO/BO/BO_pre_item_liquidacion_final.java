package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_pre_item_liquidacion_final;
import FORMULARIO.ENTIDAD.pre_item_liquidacion_final;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_pre_item_liquidacion_final {

    private DAO_pre_item_liquidacion_final pilf_dao = new DAO_pre_item_liquidacion_final();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_pre_item_liquidacion_final(pre_item_liquidacion_final pilf, JTable tbltabla) {
        String titulo = "insertar_pre_item_liquidacion_final";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            pilf_dao.insertar_pre_item_liquidacion_final(conn, pilf);
            pilf_dao.actualizar_tabla_pre_item_liquidacion_final(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, pilf.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, pilf.toString(), titulo);
            }
        }
    }

    public void update_pre_item_liquidacion_final(pre_item_liquidacion_final pilf, JTable tbltabla,boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR PRE_ITEM_LIQUIDACION_FINAL";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR PRE_ITEM_LIQUIDACION_FINAL";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_pre_item_liquidacion_final";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                pilf_dao.update_pre_item_liquidacion_final(conn, pilf);
                pilf_dao.actualizar_tabla_pre_item_liquidacion_final(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, pilf.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, pilf.toString(), titulo);
                }
            }
        }
    }
}
