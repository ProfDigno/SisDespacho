package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_funcionario;
import FORMULARIO.ENTIDAD.funcionario;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_funcionario {

    private DAO_funcionario fun_dao = new DAO_funcionario();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_funcionario(funcionario fun, JTable tbltabla) {
        String titulo = "insertar_funcionario";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            fun_dao.insertar_funcionario(conn, fun);
            fun_dao.actualizar_tabla_funcionario(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, fun.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, fun.toString(), titulo);
            }
        }
    }

    public void update_funcionario(funcionario fun, JTable tbltabla, boolean es_editar) {
        String mensaje = "";
        String titulo1 = "";
        String btnok = "";
        if (es_editar) {
            mensaje = "ESTAS SEGURO DE MODIFICAR FUNCIONARIO";
            titulo1 = "MODIFICAR";
            btnok = "ACEPTAR";
        } else {
            mensaje = "ESTAS SEGURO DE ELIMINAR FUNCIONARIO";
            titulo1 = "ELIMINAR";
            btnok = "ELIMINAR";
        }
        if (evmen.MensajeGeneral_warning(mensaje, titulo1, btnok, "CANCELAR")) {
            String titulo = "update_funcionario";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                fun_dao.update_funcionario(conn, fun);
                fun_dao.actualizar_tabla_funcionario(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, fun.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, fun.toString(), titulo);
                }
            }
        }
    }
}
