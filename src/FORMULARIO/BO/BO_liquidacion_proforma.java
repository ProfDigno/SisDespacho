package FORMULARIO.BO;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_factura_nro_proforma;
import FORMULARIO.DAO.DAO_item_comprobante;
import FORMULARIO.DAO.DAO_liquidacion_proforma;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_liquidacion_proforma {

    private DAO_liquidacion_proforma liqpro_dao = new DAO_liquidacion_proforma();
    private DAO_item_comprobante icomp_dao = new DAO_item_comprobante();
    private DAO_factura_nro_proforma facnro_dao = new DAO_factura_nro_proforma();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();
    
    public void insertar_liquidacion_proforma(liquidacion_proforma liqpro,JTable tblitem_con_comprobante
    ,JTable tblitem_sin_comprobante,JTable tblitem_bole_despa
            ,JTable tblitem_mercaderia,JTable tblitem_fac_monto) {
        String titulo = "insertar_liquidacion_proforma";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            liqpro_dao.insertar_liquidacion_proforma(conn, liqpro);
            icomp_dao.insertar_item_comprobante_de_tabla(conn, tblitem_con_comprobante, liqpro, 1);
            icomp_dao.insertar_item_comprobante_de_tabla(conn, tblitem_sin_comprobante, liqpro, 2);
            icomp_dao.insertar_item_comprobante_de_tabla(conn, tblitem_bole_despa, liqpro, 3);
            icomp_dao.insertar_item_comprobante_de_tabla_mercaderia(conn, tblitem_mercaderia, liqpro);
            facnro_dao.insertar_factura_nro_proforma_de_tabla_factuta(conn, tblitem_fac_monto, liqpro);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, liqpro.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, liqpro.toString(), titulo);
            }
        }
    }

    public void update_liquidacion_proforma(liquidacion_proforma liqpro, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR LIQUIDACION_PROFORMA", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_liquidacion_proforma";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                liqpro_dao.update_liquidacion_proforma(conn, liqpro);
                liqpro_dao.actualizar_tabla_liquidacion_proforma(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, liqpro.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, liqpro.toString(), titulo);
                }
            }
        }
    }
    public void update_liquidacion_proforma_ANULAR(liquidacion_proforma liqpro) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ANULAR LIQUIDACION_PROFORMA", "ANULAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_liquidacion_proforma";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                liqpro_dao.update_liquidacion_proforma_ANULAR(conn, liqpro);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, liqpro.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, liqpro.toString(), titulo);
                }
            }
        }
    }
}
