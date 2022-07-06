package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_pre_liquidacion;
import FORMULARIO.DAO.DAO_pre_liquidacion;
import FORMULARIO.ENTIDAD.pre_liquidacion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_pre_liquidacion {

    private DAO_pre_liquidacion pl_dao = new DAO_pre_liquidacion();
    private DAO_item_pre_liquidacion ipl_dao = new DAO_item_pre_liquidacion();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public void insertar_pre_liquidacion(pre_liquidacion pl,JTable tblitem_liquidacion_final) {
        String titulo = "insertar_pre_liquidacion";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            pl_dao.insertar_pre_liquidacion(conn, pl);
            ipl_dao.insertar_item_pre_liquidacion_final_de_tabla(conn, tblitem_liquidacion_final);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, pl.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, pl.toString(), titulo);
            }
        }
    }

    public void update_pre_liquidacion(pre_liquidacion pl, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR PRE_LIQUIDACION", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_pre_liquidacion";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                pl_dao.update_pre_liquidacion(conn, pl);
//                pl_dao.actualizar_tabla_pre_liquidacion(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, pl.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, pl.toString(), titulo);
                }
            }
        }
    }
}
