package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_moneda_cambio;
import FORMULARIO.ENTIDAD.moneda_cambio;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_moneda_cambio {

    private DAO_moneda_cambio mcam_dao = new DAO_moneda_cambio();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_moneda_cambio(moneda_cambio mcam, JTable tbltabla) {
        String titulo = "insertar_moneda_cambio";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            mcam_dao.insertar_moneda_cambio(conn, mcam);
            mcam_dao.actualizar_tabla_moneda_cambio(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, mcam.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, mcam.toString(), titulo);
            }
        }
    }

    public void update_moneda_cambio(moneda_cambio mcam, JTable tbltabla, boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR MONEDA_CAMBIO";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        }else{
            mensaje = "ESTAS SEGURO DE ELIMINAR MONEDA_CAMBIO";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR"; 
        }

        if (evmen.MensajeGeneral_warning(mensaje, titulo1, btnok, "CANCELAR")) {
            String titulo = "update_moneda_cambio";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                mcam_dao.update_moneda_cambio(conn, mcam);
                mcam_dao.actualizar_tabla_moneda_cambio(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, mcam.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, mcam.toString(), titulo);
                }
            }
        }
    }
}
