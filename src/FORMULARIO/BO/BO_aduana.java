package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_aduana;
import FORMULARIO.ENTIDAD.aduana;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_aduana {

    private DAO_aduana adu_dao = new DAO_aduana();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_aduana(aduana adu, JTable tbltabla) {
        String titulo = "insertar_aduana";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            adu_dao.insertar_aduana(conn, adu);
            adu_dao.actualizar_tabla_aduana(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, adu.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, adu.toString(), titulo);
            }
        }
    }

    public void update_aduana(aduana adu, JTable tbltabla,boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR ADUANA";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR ADUANA";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1,btnok, "CANCELAR")) {
            String titulo = "update_aduana";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                adu_dao.update_aduana(conn, adu);
                adu_dao.actualizar_tabla_aduana(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, adu.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, adu.toString(), titulo);
                }
            }
        }
    }
}
