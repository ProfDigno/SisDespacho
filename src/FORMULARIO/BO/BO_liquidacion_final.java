package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_final;
import FORMULARIO.ENTIDAD.liquidacion_final;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_liquidacion_final {

    private DAO_liquidacion_final liqfin_dao = new DAO_liquidacion_final();
    private DAO_item_liquidacion_final itemfin_dao = new DAO_item_liquidacion_final();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public boolean getBoolean_insertar_liquidacion_final(liquidacion_final liqfin,JTable tblitem) {
        boolean insert=false;
        String titulo = "getBoolean_insertar_liquidacion_final";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            liqfin_dao.insertar_liquidacion_final(conn, liqfin);
            itemfin_dao.insertar_item_liquidacion_final_de_tabla_mercaderia(conn, tblitem);
//            liqfin_dao.actualizar_tabla_liquidacion_final(conn, tbltabla);
            insert=true;
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, liqfin.toString(), titulo);
            insert=false;
            try {
                conn.rollback();
                
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, liqfin.toString(), titulo);
            }
        }
        return insert;
    }

    public void update_liquidacion_final(liquidacion_final liqfin, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LIQUIDACION_FINAL", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_liquidacion_final";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                liqfin_dao.update_liquidacion_final(conn, liqfin);
//                liqfin_dao.actualizar_tabla_liquidacion_final(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, liqfin.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, liqfin.toString(), titulo);
                }
            }
        }
    }
}
