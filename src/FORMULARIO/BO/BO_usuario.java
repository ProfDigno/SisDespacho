package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.dao_usuario;
import FORMULARIO.ENTIDAD.entidad_usuario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_usuario {

    private dao_usuario user_dao = new dao_usuario();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_usuario(entidad_usuario user, JTable tbltabla) {
        String titulo = "insertar_usuario";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            user_dao.insertar_usuario(conn, user);
            user_dao.actualizar_tabla_usuario(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, user.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, user.toString(), titulo);
            }
        }
    }

    public void update_usuario(entidad_usuario user, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR USUARIO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_usuario";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                user_dao.update_usuario(conn, user);
                user_dao.actualizar_tabla_usuario(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, user.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, user.toString(), titulo);
                }
            }
        }
    }
}
