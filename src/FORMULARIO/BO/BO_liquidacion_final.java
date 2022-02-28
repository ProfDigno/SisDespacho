package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_caja_detalle;
import FORMULARIO.DAO.DAO_credito_cliente;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_final;
import FORMULARIO.DAO.DAO_tercero;
import FORMULARIO.ENTIDAD.caja_detalle;
import FORMULARIO.ENTIDAD.credito_cliente;
import FORMULARIO.ENTIDAD.liquidacion_final;
import FORMULARIO.ENTIDAD.tercero;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class BO_liquidacion_final {

    private DAO_liquidacion_final liqfin_dao = new DAO_liquidacion_final();
    private DAO_item_liquidacion_final itemfin_dao = new DAO_item_liquidacion_final();
    private DAO_credito_cliente ccli_dao = new DAO_credito_cliente();
    private DAO_tercero cli_dao = new DAO_tercero();
    private DAO_caja_detalle caja_dao = new DAO_caja_detalle();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();

    public boolean getBoolean_insertar_liquidacion_final(liquidacion_final liqfin, JTable tblitem, credito_cliente ccli, tercero clie,caja_detalle caja,boolean esImpoExp) {
        boolean insert = false;
        if (!esImpoExp) {
            JOptionPane.showMessageDialog(null,"EN LA PROFORMA NO SE INGRESA A LA CAJA\nNO SE CREA EL CREDITO AL CLIENTE");
        }
        String titulo = "getBoolean_insertar_liquidacion_final";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            liqfin_dao.insertar_liquidacion_final(conn, liqfin);
            itemfin_dao.insertar_item_liquidacion_final_de_tabla_mercaderia(conn, tblitem);
            if (esImpoExp) {
                caja_dao.insertar_caja_detalle(conn, caja);
                ccli_dao.insertar_credito_cliente1(conn, ccli);
                cli_dao.update_cliente_saldo_credito(conn, clie);
            }
            insert = true;
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, liqfin.toString(), titulo);
            insert = false;
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

    public void anular_update_liquidacion_final(liquidacion_final liqfin) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR LIQUIDACION_FINAL", "ANULAR", "ANULAR", "CANCELAR")) {
            String titulo = "update_liquidacion_final";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                liqfin_dao.estado_update_liquidacion_final(conn, liqfin);
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
    public boolean getBoolean_update_venta_alquiler_anular(liquidacion_final liqfin,  credito_cliente ccli,  tercero clie,caja_detalle caja) {
        boolean anulado = false;
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR VENTA_ALQUILER", "ANULAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "getBoolean_update_venta_alquiler_anular";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
//                vealq_dao.update_venta_alquiler_anular(conn, vealq);
                liqfin_dao.estado_update_liquidacion_final(conn, liqfin);
                ccli_dao.update_credito_cliente_anular(conn, ccli);
                cli_dao.update_cliente_saldo_credito(conn, clie);
                caja_dao.update_caja_detalle_estado(conn, caja);
//                int datocampoid=cdalq.getC20fk_idventa_alquiler();
//                cdalq_dao.update_caja_detalle_alquilado_estado_todos(conn, cdalq,datocampoid,campoid);
                conn.commit();
                anulado = true;
            } catch (SQLException e) {
                evmen.mensaje_error(e, liqfin.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, liqfin.toString(), titulo);
                }
            }
        }
        return anulado;
    }
}
