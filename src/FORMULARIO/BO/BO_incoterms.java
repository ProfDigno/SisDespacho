package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_incoterms;
import FORMULARIO.ENTIDAD.incoterms;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_incoterms {

    private DAO_incoterms inc_dao = new DAO_incoterms();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_incoterms(incoterms inc, JTable tbltabla) {
        String titulo = "insertar_incoterms";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            inc_dao.insertar_incoterms(conn, inc);
            inc_dao.actualizar_tabla_incoterms(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, inc.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, inc.toString(), titulo);
            }
        }
    }

    public void update_incoterms(incoterms inc, JTable tbltabla,boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR INCOTERMS";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR INCOTERMS";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_incoterms";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                inc_dao.update_incoterms(conn, inc);
                inc_dao.actualizar_tabla_incoterms(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, inc.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, inc.toString(), titulo);
                }
            }
        }
    }
}
