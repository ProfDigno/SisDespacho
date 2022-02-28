package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.caja_detalle;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_caja_detalle {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
        private String estado_emitido = "EMITIDO";
    private String estado_anulado = "ANULADO";
    private String mensaje_insert = "CAJA_DETALLE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "CAJA_DETALLE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO caja_detalle(idcaja_detalle,fecha_creado,creado_por,descripcion,estado,monto_liquidacion_credito,monto_recibo_pago,monto_gasto,monto_vale,fk_idusuario,fk_idvale,fk_idgasto,fk_idliquidacion_final,fk_recibo_pago_tercero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE caja_detalle SET fecha_creado=?,creado_por=?,descripcion=?,estado=?,monto_liquidacion_credito=?,monto_recibo_pago=?,monto_gasto=?,monto_vale=?,fk_idusuario=?,fk_idvale=?,fk_idgasto=?,fk_idliquidacion_final=?,fk_recibo_pago_tercero=? WHERE idcaja_detalle=?;";
    private String sql_select = "SELECT idcaja_detalle,fecha_creado,creado_por,descripcion,estado,monto_liquidacion_credito,monto_recibo_pago,monto_gasto,monto_vale,fk_idusuario,fk_idvale,fk_idgasto,fk_idliquidacion_final,fk_recibo_pago_tercero FROM caja_detalle order by 1 desc;";
    private String sql_cargar = "SELECT idcaja_detalle,fecha_creado,creado_por,descripcion,estado,monto_liquidacion_credito,monto_recibo_pago,monto_gasto,monto_vale,fk_idusuario,fk_idvale,fk_idgasto,fk_idliquidacion_final,fk_recibo_pago_tercero FROM caja_detalle WHERE idcaja_detalle=";

    public void insertar_caja_detalle(Connection conn, caja_detalle caja) {
        caja.setC1idcaja_detalle(eveconn.getInt_ultimoID_mas_uno(conn, caja.getTb_caja_detalle(), caja.getId_idcaja_detalle()));
        String titulo = "insertar_caja_detalle";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, caja.getC1idcaja_detalle());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, caja.getC3creado_por());
            pst.setString(4, caja.getC4descripcion());
            pst.setString(5, caja.getC5estado());
            pst.setDouble(6, caja.getC6monto_liquidacion_credito());
            pst.setDouble(7, caja.getC7monto_recibo_pago());
            pst.setDouble(8, caja.getC8monto_gasto());
            pst.setDouble(9, caja.getC9monto_vale());
            pst.setInt(10, caja.getC10fk_idusuario());
            pst.setInt(11, caja.getC11fk_idvale());
            pst.setInt(12, caja.getC12fk_idgasto());
            pst.setInt(13, caja.getC13fk_idliquidacion_final());
            pst.setInt(14, caja.getC14fk_recibo_pago_tercero());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + caja.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + caja.toString(), titulo);
        }
    }

    public void update_caja_detalle(Connection conn, caja_detalle caja) {
        String titulo = "update_caja_detalle";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, caja.getC3creado_por());
            pst.setString(3, caja.getC4descripcion());
            pst.setString(4, caja.getC5estado());
            pst.setDouble(5, caja.getC6monto_liquidacion_credito());
            pst.setDouble(6, caja.getC7monto_recibo_pago());
            pst.setDouble(7, caja.getC8monto_gasto());
            pst.setDouble(8, caja.getC9monto_vale());
            pst.setInt(9, caja.getC10fk_idusuario());
            pst.setInt(10, caja.getC11fk_idvale());
            pst.setInt(11, caja.getC12fk_idgasto());
            pst.setInt(12, caja.getC13fk_idliquidacion_final());
            pst.setInt(13, caja.getC14fk_recibo_pago_tercero());
            pst.setInt(14, caja.getC1idcaja_detalle());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + caja.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + caja.toString(), titulo);
        }
    }

    public void cargar_caja_detalle(Connection conn, caja_detalle caja, int idcaja_detalle) {
        String titulo = "Cargar_caja_detalle";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idcaja_detalle, titulo);
            if (rs.next()) {
                caja.setC1idcaja_detalle(rs.getInt(1));
                caja.setC2fecha_creado(rs.getString(2));
                caja.setC3creado_por(rs.getString(3));
                caja.setC4descripcion(rs.getString(4));
                caja.setC5estado(rs.getString(5));
                caja.setC6monto_liquidacion_credito(rs.getDouble(6));
                caja.setC7monto_recibo_pago(rs.getDouble(7));
                caja.setC8monto_gasto(rs.getDouble(8));
                caja.setC9monto_vale(rs.getDouble(9));
                caja.setC10fk_idusuario(rs.getInt(10));
                caja.setC11fk_idvale(rs.getInt(11));
                caja.setC12fk_idgasto(rs.getInt(12));
                caja.setC13fk_idliquidacion_final(rs.getInt(13));
                caja.setC14fk_recibo_pago_tercero(rs.getInt(14));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + caja.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + caja.toString(), titulo);
        }
    }

    public void actualizar_tabla_caja_detalle(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_caja_detalle(tbltabla);
    }

    public void ancho_tabla_caja_detalle(JTable tbltabla) {
        int Ancho[] = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_caja_detalle_todos(Connection conn, JTable tbltabla, String fecha) {
        String sql = "select cd.idcaja_detalle as idcd,to_char(cd.fecha_creado,'yyyy-MM-dd HH24:MI') as fecha,\n"
                + "cd.descripcion,cd.estado,\n"
                + "cd.monto_liquidacion_credito as liquidacion_credito,\n"
                + "cd.monto_recibo_pago as recibo_pago,\n"
                + "cd.monto_gasto as gasto,\n"
                + "cd.monto_vale  as vale\n"
                + "from caja_detalle cd\n"
                + "where cd.estado='"+estado_emitido+"' \n" + fecha
                + "order by cd.idcaja_detalle desc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_caja_detalle_todos(tbltabla);
    }

    public void ancho_tabla_caja_detalle_todos(JTable tbltabla) {
        int Ancho[] = {5, 15, 25, 10, 8, 8, 8, 8};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_caja_detalle_suma(Connection conn, JTable tbltabla, String fecha) {
        String sql = "select to_char(cd.fecha_creado,'yyyy-MM') as fecha,\n"
                + "case when cd.fk_idliquidacion_final>0 then 'LIQUIDACION-CREDITO'\n"
                + "     when cd.fk_recibo_pago_tercero >0 then 'RECIBO'\n"
                + "     when cd.fk_idvale>0 then 'VALE'\n"
                + "     when cd.fk_idgasto>0 then 'GASTO'\n"
                + "     else 'error' end as tipo_caja,\n"
                + "trim(to_char(sum(cd.monto_liquidacion_credito),'999G999G999G999')) as liquidacion_credito,\n"
                + "trim(to_char(sum(cd.monto_recibo_pago),'999G999G999G999')) as recibo_pago,\n"
                + "trim(to_char(sum(cd.monto_gasto),'999G999G999G999')) as gasto,\n"
                + "trim(to_char(sum(cd.monto_vale),'999G999G999G999'))  as vale\n"
                + "from caja_detalle cd\n"
                + "where cd.estado='"+estado_emitido+"' \n" + fecha
                + " group by 1,2\n"
                + "order by 1 desc";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_caja_detalle_suma(tbltabla);
    }

    public void ancho_tabla_caja_detalle_suma(JTable tbltabla) {
        int Ancho[] = {15, 25, 13, 13, 12, 12};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void vaciar_caja_detalle(caja_detalle caja) {
        caja.setC3creado_por("ninguno");
        caja.setC4descripcion("ninguna");
        caja.setC5estado("error");
        caja.setC6monto_liquidacion_credito(0);
        caja.setC7monto_recibo_pago(0);
        caja.setC8monto_gasto(0);
        caja.setC9monto_vale(0);
        caja.setC10fk_idusuario(0);
        caja.setC11fk_idvale(0);
        caja.setC12fk_idgasto(0);
        caja.setC13fk_idliquidacion_final(0);
        caja.setC14fk_recibo_pago_tercero(0);
    }

    public void update_caja_detalle_estado(Connection conn, caja_detalle cdalq) {
        String sql_estado = "UPDATE caja_detalle SET estado=? WHERE " + cdalq.getNom_campo_todos() + "=?;";
        String titulo = "update_caja_detalle_estado";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_estado);
            pst.setString(1, cdalq.getC5estado());
            pst.setInt(2, cdalq.getFk_idtodos());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_estado + "\n" + cdalq.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_estado + "\n" + cdalq.toString(), titulo);
        }
    }

    public void imprimir_caja_detalle_todos(Connection conn, String filtrofecha) {
        String sql = "select cd.idcaja_detalle as idcd,\n"
                + "to_char(cd.fecha_creado,'yyyy-MM-dd HH24:MI') as fecha,\n"
                + "cd.descripcion,cd.estado,\n"
                + "cd.monto_liquidacion_credito as liquidacion_credito,\n"
                + "cd.monto_recibo_pago as recibo_pago,\n"
                + "cd.monto_gasto as gasto,\n"
                + "cd.monto_vale  as vale,\n  "
                + "cd.monto_recibo_pago as ingreso,\n" 
                + "(cd.monto_gasto+cd.monto_vale) as egreso,\n" 
                + "(cd.monto_recibo_pago-(cd.monto_gasto+cd.monto_vale)) as diferencia "
                + "from caja_detalle cd\n"
                + "where cd.estado='"+estado_emitido+"' \n" + filtrofecha
                + "order by cd.idcaja_detalle desc;";
        String titulonota = "CAJA DETALLE";
        String direccion = "src/REPORTE/CAJA/repDetalleCajaTodos.jrxml";
        String rutatemp = "Caja_Detalle_" + evefec.getString_formato_fecha();
        rep.imprimir_jasper_o_pdf(conn, sql, titulonota, direccion, rutatemp);
    }
}
