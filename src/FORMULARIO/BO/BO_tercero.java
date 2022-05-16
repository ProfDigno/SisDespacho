package FORMULARIO.BO;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.DAO.DAO_caja_detalle;
import FORMULARIO.DAO.DAO_credito_cliente;
import FORMULARIO.DAO.DAO_grupo_credito_cliente;
import FORMULARIO.DAO.DAO_recibo_pago_cliente;
import FORMULARIO.DAO.DAO_saldo_credito_cliente;
import FORMULARIO.DAO.DAO_tercero;
import FORMULARIO.ENTIDAD.caja_detalle;
import FORMULARIO.ENTIDAD.credito_cliente;
import FORMULARIO.ENTIDAD.grupo_credito_cliente;
import FORMULARIO.ENTIDAD.recibo_pago_cliente;
import FORMULARIO.ENTIDAD.saldo_credito_cliente;
import FORMULARIO.ENTIDAD.tercero;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JTable;

public class BO_tercero {

    private DAO_tercero ter_dao = new DAO_tercero();
    private DAO_tercero cli_dao = new DAO_tercero();
    EvenMensajeJoptionpane evmen = new EvenMensajeJoptionpane();
     private DAO_saldo_credito_cliente sccli_dao = new DAO_saldo_credito_cliente();
    private DAO_credito_cliente ccli_dao = new DAO_credito_cliente();
    private DAO_grupo_credito_cliente gcc_dao = new DAO_grupo_credito_cliente();
    private DAO_recibo_pago_cliente rpcli_dao = new DAO_recibo_pago_cliente();
    private grupo_credito_cliente gcc2 = new grupo_credito_cliente();
    private grupo_credito_cliente gcc3 = new grupo_credito_cliente();
    private DAO_caja_detalle caja_dao = new DAO_caja_detalle();
    private String estado_EMITIDO = "EMITIDO";
    private String estado_ABIERTO = "ABIERTO";
    private String estado_CERRADO = "CERRADO";
    EvenConexion eveconn = new EvenConexion();
    public void insertar_tercero(tercero ter, JTable tbltabla) {
        String titulo = "insertar_tercero";
        Connection conn = ConnPostgres.getConnPosgres();
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ter_dao.insertar_tercero(conn, ter);
            ter_dao.actualizar_tabla_tercero(conn, tbltabla);
            conn.commit();
        } catch (SQLException e) {
            evmen.mensaje_error(e, ter.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
            }
        }
    }

    public void update_tercero(tercero ter, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE MODIFICAR TERCERO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tercero";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ter_dao.update_tercero(conn, ter);
                ter_dao.actualizar_tabla_tercero(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ter.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
                }
            }
        }
    }

    public boolean getBoolean_insertar_credito_inicio1(saldo_credito_cliente sccli, credito_cliente ccli, grupo_credito_cliente gcc) {
        String titulo = "getBoolean_insertar_credito_inicio";
        Connection conn = ConnPostgres.getConnPosgres();
        boolean insert = false;
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            sccli_dao.insertar_saldo_credito_cliente(conn, sccli);
            gcc_dao.insertar_grupo_credito_cliente(conn, gcc);
            ccli_dao.insertar_credito_cliente1(conn, ccli);
            conn.commit();
            insert = true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, sccli.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, sccli.toString(), titulo);
            }
        }
        return insert;
    }
    public boolean getBoolean_insertar_cliente_con_credito_inicio1(tercero ter,saldo_credito_cliente sccli,credito_cliente ccli,grupo_credito_cliente gcc) {
        String titulo = "insertar_cliente_con_credito_inicio";
        Connection conn = ConnPostgres.getConnPosgres();
        boolean insert=false;
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            ter_dao.insertar_tercero(conn, ter);
            sccli_dao.insertar_saldo_credito_cliente(conn, sccli);
            gcc_dao.insertar_grupo_credito_cliente(conn, gcc);
            ccli_dao.insertar_credito_cliente1(conn, ccli);
            conn.commit();
            insert=true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, ter.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
            }
        }
        return insert;
    }
    public boolean getBoolean_insertar_cliente_con_recibo_pago1(tercero cli, credito_cliente ccli, credito_cliente ccli2,
            grupo_credito_cliente gcc, recibo_pago_cliente rpcli, saldo_credito_cliente sccli,caja_detalle caja) {
        String titulo = "getBoolean_insertar_cliente_con_recibo_pago";
        Connection conn = ConnPostgres.getConnPosgres();
        boolean insert = false;
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            gcc_dao.cargar_liquidacion_final_pago_auto(conn, rpcli, cli.getC1idtercero());
            rpcli_dao.insertar_recibo_pago_cliente(conn, rpcli);
            ccli_dao.insertar_credito_cliente1(conn, ccli);
            gcc_dao.cargar_grupo_credito_cliente_id(conn, gcc2, cli.getC1idtercero());
            gcc2.setC4estado(estado_CERRADO);
            gcc_dao.cerrar_grupo_credito_cliente(conn, gcc2);
            gcc2.setC4estado(estado_ABIERTO);
            gcc2.setC5fk_idcliente(cli.getC1idtercero());
            gcc_dao.insertar_grupo_credito_cliente(conn, gcc2);
            sccli_dao.insertar_saldo_credito_cliente(conn, sccli);
            gcc_dao.cargar_grupo_credito_cliente_id(conn, gcc3, cli.getC1idtercero());
            int idsaldo_credito_cliente = (eveconn.getInt_ultimoID_max(conn, sccli.getTb_saldo_credito_cliente(), sccli.getId_idsaldo_credito_cliente()));
            ccli2.setC8fk_idgrupo_credito_cliente(gcc3.getC1idgrupo_credito_cliente());
            ccli2.setC9fk_idsaldo_credito_cliente(idsaldo_credito_cliente);
            ccli_dao.insertar_credito_cliente1(conn, ccli2);
            cli_dao.update_cliente_saldo_credito(conn, cli);
            caja_dao.insertar_caja_detalle(conn, caja);
            conn.commit();
            insert = true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, cli.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, cli.toString(), titulo);
            }
        }
        return insert;
    }
     public boolean getBoolean_insertar_recibo_pago_generar_credito(tercero cli, credito_cliente ccli, credito_cliente ccli2,
            grupo_credito_cliente gcc, recibo_pago_cliente rpcli, saldo_credito_cliente sccli) {
        String titulo = "getBoolean_insertar_recibo_pago_generar_credito";
        Connection conn = ConnPostgres.getConnPosgres();
        boolean insert = false;
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
//            gcc_dao.cargar_liquidacion_final_pago_auto(conn, rpcli, cli.getC1idtercero());
//            rpcli_dao.insertar_recibo_pago_cliente(conn, rpcli);
//            ccli_dao.insertar_credito_cliente1(conn, ccli);
//            gcc_dao.cargar_grupo_credito_cliente_id(conn, gcc2, cli.getC1idtercero());
//            gcc2.setC4estado(estado_CERRADO);
//            gcc_dao.cerrar_grupo_credito_cliente(conn, gcc2);
//            gcc2.setC4estado(estado_ABIERTO);
//            gcc2.setC5fk_idcliente(cli.getC1idtercero());
//            gcc_dao.insertar_grupo_credito_cliente(conn, gcc2);
            sccli_dao.insertar_saldo_credito_cliente(conn, sccli);
            gcc_dao.cargar_grupo_credito_cliente_id(conn, gcc3, cli.getC1idtercero());
            int idsaldo_credito_cliente = (eveconn.getInt_ultimoID_max(conn, sccli.getTb_saldo_credito_cliente(), sccli.getId_idsaldo_credito_cliente()));
            ccli2.setC8fk_idgrupo_credito_cliente(gcc3.getC1idgrupo_credito_cliente());
            ccli2.setC9fk_idsaldo_credito_cliente(idsaldo_credito_cliente);
            ccli_dao.insertar_credito_cliente1(conn, ccli2);
            cli_dao.update_cliente_saldo_credito(conn, cli);
            conn.commit();
            insert = true;
        } catch (SQLException e) {
            evmen.mensaje_error(e, cli.toString(), titulo);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                evmen.Imprimir_serial_sql_error(e1, cli.toString(), titulo);
            }
        }
        return insert;
    }
     public void update_tercero_eliminar(tercero ter, JTable tbltabla) {
        if (evmen.MensajeGeneral_warning("ESTAS SEGURO DE ELIMINAR TERCERO", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
            String titulo = "update_tercero_eliminar";
            Connection conn = ConnPostgres.getConnPosgres();
            try {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
                ter_dao.update_tercero_eliminar(conn, ter);
                ter_dao.actualizar_tabla_tercero(conn, tbltabla);
                conn.commit();
            } catch (SQLException e) {
                evmen.mensaje_error(e, ter.toString(), titulo);
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    evmen.Imprimir_serial_sql_error(e1, ter.toString(), titulo);
                }
            }
        }
    }
}
