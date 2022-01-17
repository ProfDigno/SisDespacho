package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.dao_item_usuario_rol;
import FORMULARIO.DAO.dao_usuario_rol;
import FORMULARIO.ENTIDAD.entidad_usuario_rol;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_usuario_rol {

    private dao_usuario_rol urol_dao = new dao_usuario_rol();
    private dao_item_usuario_rol iurol_dao = new dao_item_usuario_rol();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_usuario_rol(entidad_usuario_rol urol, JTable tbltabla, JTable tbllocal_evento) {
        String titulo = "insertar_usuario_rol";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            urol_dao.insertar_usuario_rol(conn, urol);
            iurol_dao.insertar_item_usuario_rol_tabla(conn, urol, tbllocal_evento);
            urol_dao.actualizar_tabla_usuario_rol(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, urol.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, urol.toString(), titulo);
            }
        }
    }

    public void update_usuario_rol(entidad_usuario_rol urol, JTable tbltabla,JTable tbllocal_evento) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR USUARIO_ROL", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_usuario_rol";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                urol_dao.update_usuario_rol(conn, urol);
                iurol_dao.eliminar_item_usuario_rol(conn, urol);
                iurol_dao.insertar_item_usuario_rol_tabla(conn, urol, tbllocal_evento);
                urol_dao.actualizar_tabla_usuario_rol(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, urol.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, urol.toString(), titulo);
                }
            }
        }
    }
    public void eliminar_recargarevento_usuario_rol(entidad_usuario_rol urol, JTable tbltabla,JTable tbllocal_evento) {
//        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE CARGAR DENUEVO USUARIO_ROL", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "eliminar_recargarevento_usuario_rol";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
//                urol_dao.update_usuario_rol(conn, urol);
                iurol_dao.eliminar_item_usuario_rol(conn, urol);
                iurol_dao.insertar_item_usuario_rol_tabla(conn, urol, tbllocal_evento);
//                urol_dao.actualizar_tabla_usuario_rol(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, urol.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, urol.toString(), titulo);
                }
            }
        }
//    }
}
