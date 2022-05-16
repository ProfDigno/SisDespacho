package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_honorario_despacho;
import FORMULARIO.ENTIDAD.honorario_despacho;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_honorario_despacho {

    private DAO_honorario_despacho hdes_dao = new DAO_honorario_despacho();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_honorario_despacho(honorario_despacho hdes, JTable tbltabla) {
        String titulo = "insertar_honorario_despacho";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            hdes_dao.insertar_honorario_despacho(conn, hdes);
            hdes_dao.actualizar_tabla_honorario_despacho(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, hdes.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, hdes.toString(), titulo);
            }
        }
    }

    public void update_honorario_despacho(honorario_despacho hdes, JTable tbltabla, boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR HONORARIO_DESPACHO";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR HONORARIO_DESPACHO";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_honorario_despacho";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                hdes_dao.update_honorario_despacho(conn, hdes);
                hdes_dao.actualizar_tabla_honorario_despacho(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, hdes.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, hdes.toString(), titulo);
                }
            }
        }
    }
}
