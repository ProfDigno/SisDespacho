package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_tipo_tasa_cambio;
import FORMULARIO.ENTIDAD.tipo_tasa_cambio;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_tipo_tasa_cambio {

    private DAO_tipo_tasa_cambio ttcam_dao = new DAO_tipo_tasa_cambio();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_tipo_tasa_cambio(tipo_tasa_cambio ttcam, JTable tbltabla) {
        String titulo = "insertar_tipo_tasa_cambio";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ttcam_dao.insertar_tipo_tasa_cambio(conn, ttcam);
            ttcam_dao.actualizar_tabla_tipo_tasa_cambio(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ttcam.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ttcam.toString(), titulo);
            }
        }
    }

    public void update_tipo_tasa_cambio(tipo_tasa_cambio ttcam, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TIPO_TASA_CAMBIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tipo_tasa_cambio";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ttcam_dao.update_tipo_tasa_cambio(conn, ttcam);
                ttcam_dao.actualizar_tabla_tipo_tasa_cambio(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ttcam.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ttcam.toString(), titulo);
                }
            }
        }
    }
}
