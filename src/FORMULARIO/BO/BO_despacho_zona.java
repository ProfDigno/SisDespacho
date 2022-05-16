package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_despacho_zona;
import FORMULARIO.ENTIDAD.despacho_zona;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_despacho_zona {

    private DAO_despacho_zona dzon_dao = new DAO_despacho_zona();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_despacho_zona(despacho_zona dzon, JTable tbltabla) {
        String titulo = "insertar_despacho_zona";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            dzon_dao.insertar_despacho_zona(conn, dzon);
            dzon_dao.actualizar_tabla_despacho_zona(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, dzon.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, dzon.toString(), titulo);
            }
        }
    }

    public void update_despacho_zona(despacho_zona dzon, JTable tbltabla,boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR DESPACHO_ZONA";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR DESPACHO_ZONA";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_despacho_zona";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                dzon_dao.update_despacho_zona(conn, dzon);
                dzon_dao.actualizar_tabla_despacho_zona(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, dzon.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, dzon.toString(), titulo);
                }
            }
        }
    }
}
