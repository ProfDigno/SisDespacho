package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.grupo_credito_cliente;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.recibo_pago_cliente;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_grupo_credito_cliente {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "GRUPO_CREDITO_CLIENTE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "GRUPO_CREDITO_CLIENTE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO grupo_credito_tercero(idgrupo_credito_tercero,fecha_inicio,fecha_fin,estado,fk_idtercero) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE grupo_credito_tercero SET fecha_inicio=?,fecha_fin=?,estado=?,fk_idtercero=? WHERE idgrupo_credito_tercero=?;";
    private String sql_select = "SELECT idgrupo_credito_tercero,fecha_inicio,fecha_fin,estado,fk_idtercero FROM grupo_credito_tercero order by 1 desc;";
    private String sql_cargar = "SELECT idgrupo_credito_tercero,fecha_inicio,fecha_fin,estado,fk_idtercero FROM grupo_credito_tercero WHERE idgrupo_credito_tercero=";
    private String sql_select_idc = "select gcc.idgrupo_credito_tercero as idgcc,\n"
            + "to_char(gcc.fecha_inicio,'yyyy-MM-dd HH24:MI') as inicio,\n"
            + "to_char(gcc.fecha_fin,'yyyy-MM-dd HH24:MI') as fin,\n"
            + "gcc.estado,cl.nombre,\n"
            + "(select TRIM(to_char(coalesce(sum(cc.monto_contado),0),'999G999G999')) \n"
            + "from credito_tercero cc \n"
            + "where (cc.estado='EMITIDO' or cc.estado='USO_RESERVA')\n"
            + "and cc.fk_idgrupo_credito_tercero=gcc.idgrupo_credito_tercero) as contado,\n"
            + "(select TRIM(to_char(coalesce(sum(cc.monto_credito),0),'999G999G999'))  \n"
            + "from credito_tercero cc \n"
            + "where (cc.estado='EMITIDO' or cc.estado='USO_RESERVA')\n"
            + "and cc.fk_idgrupo_credito_tercero=gcc.idgrupo_credito_tercero) as credito,\n"
            + "(select TRIM(to_char(coalesce(sum(cc.monto_contado - cc.monto_credito),0),'999G999G999'))  \n"
            + "from credito_tercero cc \n"
            + "where (cc.estado='EMITIDO' or cc.estado='USO_RESERVA')\n"
            + "and cc.fk_idgrupo_credito_tercero=gcc.idgrupo_credito_tercero) as saldo\n"
            + "from grupo_credito_tercero gcc,tercero cl\n"
            + "where gcc.fk_idtercero=cl.idtercero\n"
            + "and gcc.fk_idtercero=";
    private String sql_cargar_id = "SELECT idgrupo_credito_tercero "
            + "FROM grupo_credito_tercero "
            + "WHERE estado='ABIERTO' and fk_idtercero=";
    private String sql_update_cerrar = "UPDATE grupo_credito_tercero SET fecha_fin=?,estado=? WHERE idgrupo_credito_tercero=?;";

    public void insertar_grupo_credito_cliente(Connection conn, grupo_credito_cliente gccl) {
        gccl.setC1idgrupo_credito_cliente(eveconn.getInt_ultimoID_mas_uno(conn, gccl.getTb_grupo_credito_cliente(), gccl.getId_idgrupo_credito_cliente()));
        String titulo = "insertar_grupo_credito_tercero";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, gccl.getC1idgrupo_credito_cliente());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setTimestamp(3, evefec.getTimestamp_sistema());
            pst.setString(4, gccl.getC4estado());
            pst.setInt(5, gccl.getC5fk_idcliente());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + gccl.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + gccl.toString(), titulo);
        }
    }

    public void update_grupo_credito_cliente(Connection conn, grupo_credito_cliente gccl) {
        String titulo = "update_grupo_credito_tercero";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, gccl.getC4estado());
            pst.setInt(4, gccl.getC5fk_idcliente());
            pst.setInt(5, gccl.getC1idgrupo_credito_cliente());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + gccl.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + gccl.toString(), titulo);
        }
    }

    public void cargar_grupo_credito_cliente(Connection conn, grupo_credito_cliente gccl, int id) {
        String titulo = "Cargar_grupo_credito_tercero";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                gccl.setC1idgrupo_credito_cliente(rs.getInt(1));
                gccl.setC2fecha_inicio(rs.getString(2));
                gccl.setC3fecha_fin(rs.getString(3));
                gccl.setC4estado(rs.getString(4));
                gccl.setC5fk_idcliente(rs.getInt(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + gccl.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + gccl.toString(), titulo);
        }
    }

    public void actualizar_tabla_grupo_credito_cliente(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_grupo_credito_cliente(tbltabla);
    }

    public void ancho_tabla_grupo_credito_cliente(JTable tbltabla) {
        int Ancho[] = {20, 20, 20, 20, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_grupo_credito_cliente_idc(Connection conn, JTable tbltabla, int idtercero) {
        eveconn.Select_cargar_jtable(conn, sql_select_idc + idtercero + " order by 1 desc", tbltabla);
        ancho_tabla_grupo_credito_cliente_idc(tbltabla);
    }

    public void ancho_tabla_grupo_credito_cliente_idc(JTable tbltabla) {
        int Ancho[] = {6, 12, 12, 10, 30, 10, 10, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void cargar_grupo_credito_cliente_id(Connection conn, grupo_credito_cliente gcc, int fk_idcliente) {
        String titulo = "cargar_grupo_credito_cliente_id";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar_id + fk_idcliente, titulo);
            if (rs.next()) {
                gcc.setC1idgrupo_credito_cliente(rs.getInt(1));
                evemen.Imprimir_serial_sql(sql_cargar_id + "\n" + gcc.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar_id + "\n" + gcc.toString(), titulo);
        }
    }

    public void cerrar_grupo_credito_cliente(Connection conn, grupo_credito_cliente gcc) {
        String titulo = "cerrar_grupo_credito_tercero";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_cerrar);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, gcc.getC4estado());
            pst.setInt(3, gcc.getC1idgrupo_credito_cliente());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_cerrar + "\n" + gcc.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_cerrar + "\n" + gcc.toString(), titulo);
        }
    }
    private void update_liquidacion_final_monto_pagado(Connection conn, double monto_pagado, int idliquidacion_final) {
        String titulo = "update_liquidacion_final_monto_pagado";
         String sql_update_monto_pagado = "update liquidacion_final set estado='PAGADO',monto_pagado=(monto_pagado + ?) where idliquidacion_final=?;";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_monto_pagado);
            pst.setDouble(1, monto_pagado);
            pst.setInt(2, idliquidacion_final);
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_monto_pagado , titulo);
//            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_monto_pagado , titulo);
        }
    }
    public void cargar_liquidacion_final_pago_auto(Connection conn, recibo_pago_cliente rpcli, int fk_idcliente) {
        String titulo = "cargar_liquidacion_final_pago_auto";
        String sql = "select lf.fecha_creado,(lf.monto_pagar-lf.monto_pagado) as saldo,lf.idliquidacion_final \n"
                + "from credito_tercero ct,liquidacion_final lf \n"
                + "where ct.fk_idliquidacion_final=lf.idliquidacion_final \n"
                + "and ct.estado!='ANULADO' and ct.fk_idliquidacion_final >0 \n"
                + "and lf.fk_idtercero_importador=" + fk_idcliente + " \n"
                + " order by 1 asc;";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            double monto_recibo = rpcli.getC4monto_recibo_pago();
            while (rs.next()) {
                double saldo = (rs.getDouble(2));
                int idliquidacion_final = (rs.getInt(3));
                if (monto_recibo > 0) {
                    if (monto_recibo >= saldo) {
                        System.out.println("=======>>>>>>>monto_pagado=" + saldo + ", monto_pagar=" + saldo + ", idliquidacion_final=" + idliquidacion_final);
                        update_liquidacion_final_monto_pagado(conn, saldo, idliquidacion_final);
                        monto_recibo = monto_recibo - saldo;
                    } else {
                        System.out.println("=======>>>>>>>monto_pagado=" + monto_recibo + ", monto_pagar=" + saldo + ", idliquidacion_final=" + idliquidacion_final);
                        update_liquidacion_final_monto_pagado(conn, monto_recibo, idliquidacion_final);
                        monto_recibo = 0;
                    }
                }
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
            evemen.Imprimir_serial_sql_error(e, sql, titulo);
        }
    }
}
