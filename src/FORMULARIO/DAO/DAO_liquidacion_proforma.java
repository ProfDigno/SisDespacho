package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_liquidacion_proforma {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LIQUIDACION_PROFORMA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LIQUIDACION_PROFORMA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO liquidacion_proforma(idliquidacion_proforma,fecha_creacion,creado_por,fecha_despacho,"
            + "contenedor_nro,contenedor_tipo,via_transporte,monto_factura,monto_sin_comprobante,monto_con_comprobante,"
            + "tasa_cambio_aduana,tasa_cambio_mercado,estado,"
            + "fk_idtercero_importador,fk_idtercero_despachante,fk_idtercero_colaborador,fk_idtercero_transportadora,"
            + "fk_idaduana,observacion,tipo_tasa_cambio,tipo_liquidacion,fk_idhonorario_despacho,"
            + "monto_boleto_despachante,monto_honorario_despachante,fk_idmoneda_cambio) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE liquidacion_proforma SET fecha_creacion=?,creado_por=?,"
            + "fecha_despacho=?,contenedor_nro=?,contenedor_tipo=?,via_transporte=?,"
            + "monto_factura=?,monto_sin_comprobante=?,monto_con_comprobante=?,"
            + "tasa_cambio_aduana=?,tasa_cambio_mercado=?,estado=?,"
            + "fk_idtercero_importador=?,fk_idtercero_despachante=?,fk_idtercero_colaborador=?,"
            + "fk_idtercero_transportadora=?,fk_idaduana=?,observacion=?,tipo_tasa_cambio=?,tipo_liquidacion=?,fk_idhonorario_despacho=?,"
            + "monto_boleto_despachante=?,monto_honorario_despachante=?,fk_idmoneda_cambio=? "
            + "WHERE idliquidacion_proforma=?;";
    private String sql_select = "SELECT lp.idliquidacion_proforma as idlp,\n"
            + "lp.fecha_despacho,lp.tipo_liquidacion as liquidacion,lp.via_transporte,tei.nombre as importador,ad.sigla as aduana,\n"
            + "lp.monto_factura as fac_monto,lp.estado,lp.creado_por as creado\n"
            + "FROM liquidacion_proforma lp,aduana ad,tercero tei,tercero tet\n"
            + "where lp.fk_idaduana=ad.idaduana\n"
            + "and lp.fk_idtercero_importador=tei.idtercero\n"
            + "and lp.fk_idtercero_transportadora=tet.idtercero \n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT idliquidacion_proforma,fecha_creacion,creado_por,"
            + "fecha_despacho,contenedor_nro,contenedor_tipo,via_transporte,"
            + "monto_factura,monto_sin_comprobante,monto_con_comprobante,"
            + "tasa_cambio_aduana,tasa_cambio_mercado,estado,"
            + "fk_idtercero_importador,fk_idtercero_despachante,"
            + "fk_idtercero_colaborador,fk_idtercero_transportadora,"
            + "fk_idaduana,observacion,tipo_tasa_cambio,"
            + "tipo_liquidacion,fk_idhonorario_despacho,"
            + "monto_boleto_despachante,monto_honorario_despachante,fk_idmoneda_cambio "
            + "FROM liquidacion_proforma WHERE idliquidacion_proforma=";
    private String sql_update_anular="UPDATE liquidacion_proforma SET estado=? "
            + "WHERE idliquidacion_proforma=?;";;

    public void insertar_liquidacion_proforma(Connection conn, liquidacion_proforma liqpro) {
        liqpro.setC1idliquidacion_proforma(eveconn.getInt_ultimoID_mas_uno(conn, liqpro.getTb_liquidacion_proforma(), liqpro.getId_idliquidacion_proforma()));
        String titulo = "insertar_liquidacion_proforma";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, liqpro.getC1idliquidacion_proforma());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, liqpro.getC3creado_por());
            pst.setDate(4, evefec.getDateSQL_sistema());
            pst.setString(5, liqpro.getC5contenedor_nro());
            pst.setString(6, liqpro.getC6contenedor_tipo());
            pst.setString(7, liqpro.getC7via_transporte());
            pst.setDouble(8, liqpro.getC8monto_factura());
            pst.setDouble(9, liqpro.getC9monto_sin_comprobante());
            pst.setDouble(10, liqpro.getC10monto_con_comprobante());
            pst.setDouble(11, liqpro.getC11tasa_cambio_aduana());
            pst.setDouble(12, liqpro.getC12tasa_cambio_mercado());
            pst.setString(13, liqpro.getC13estado());
            pst.setInt(14, liqpro.getC14fk_idtercero_importador());
            pst.setInt(15, liqpro.getC15fk_idtercero_despachante());
            pst.setInt(16, liqpro.getC16fk_idtercero_colaborador());
            pst.setInt(17, liqpro.getC17fk_idtercero_transportadora());
            pst.setInt(18, liqpro.getC18fk_idaduana());
            pst.setString(19, liqpro.getC19observacion());
            pst.setString(20, liqpro.getC20tipo_tasa_cambio());
            pst.setString(21, liqpro.getC21tipo_liquidacion());
            pst.setInt(22, liqpro.getC22fk_idhonorario_despacho());
            pst.setDouble(23, liqpro.getC23monto_boleto_despachante());
            pst.setDouble(24, liqpro.getC24monto_honorario_despachante());
            pst.setInt(25, liqpro.getC25fk_idmoneda_cambio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + liqpro.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + liqpro.toString(), titulo);
        }
    }

    public void update_liquidacion_proforma(Connection conn, liquidacion_proforma liqpro) {
        String titulo = "update_liquidacion_proforma";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, liqpro.getC3creado_por());
            pst.setDate(3, evefec.getDateSQL_sistema());
            pst.setString(4, liqpro.getC5contenedor_nro());
            pst.setString(5, liqpro.getC6contenedor_tipo());
            pst.setString(6, liqpro.getC7via_transporte());
            pst.setDouble(7, liqpro.getC8monto_factura());
            pst.setDouble(8, liqpro.getC9monto_sin_comprobante());
            pst.setDouble(9, liqpro.getC10monto_con_comprobante());
            pst.setDouble(10, liqpro.getC11tasa_cambio_aduana());
            pst.setDouble(11, liqpro.getC12tasa_cambio_mercado());
            pst.setString(12, liqpro.getC13estado());
            pst.setInt(13, liqpro.getC14fk_idtercero_importador());
            pst.setInt(14, liqpro.getC15fk_idtercero_despachante());
            pst.setInt(15, liqpro.getC16fk_idtercero_colaborador());
            pst.setInt(16, liqpro.getC17fk_idtercero_transportadora());
            pst.setInt(17, liqpro.getC18fk_idaduana());
            pst.setString(18, liqpro.getC19observacion());
            pst.setString(19, liqpro.getC20tipo_tasa_cambio());
            pst.setString(20, liqpro.getC21tipo_liquidacion());
            pst.setInt(21, liqpro.getC22fk_idhonorario_despacho());
            pst.setDouble(22, liqpro.getC23monto_boleto_despachante());
            pst.setDouble(23, liqpro.getC24monto_honorario_despachante());
            pst.setInt(24, liqpro.getC25fk_idmoneda_cambio());
            pst.setInt(25, liqpro.getC1idliquidacion_proforma());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + liqpro.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + liqpro.toString(), titulo);
        }
    }
    public void update_liquidacion_proforma_ANULAR(Connection conn, liquidacion_proforma liqpro) {
        String titulo = "update_liquidacion_proforma_ANULAR";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_anular);
            pst.setString(1, liqpro.getC13estado());
            pst.setInt(2, liqpro.getC1idliquidacion_proforma());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + liqpro.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + liqpro.toString(), titulo);
        }
    }
    public void cargar_liquidacion_proforma(Connection conn, liquidacion_proforma liqpro, int id) {
        String titulo = "Cargar_liquidacion_proforma";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                liqpro.setC1idliquidacion_proforma(rs.getInt(1));
                liqpro.setC2fecha_creacion(rs.getString(2));
                liqpro.setC3creado_por(rs.getString(3));
                liqpro.setC4fecha_despacho(rs.getString(4));
                liqpro.setC5contenedor_nro(rs.getString(5));
                liqpro.setC6contenedor_tipo(rs.getString(6));
                liqpro.setC7via_transporte(rs.getString(7));
                liqpro.setC8monto_factura(rs.getDouble(8));
                liqpro.setC9monto_sin_comprobante(rs.getDouble(9));
                liqpro.setC10monto_con_comprobante(rs.getDouble(10));
                liqpro.setC11tasa_cambio_aduana(rs.getDouble(11));
                liqpro.setC12tasa_cambio_mercado(rs.getDouble(12));
                liqpro.setC13estado(rs.getString(13));
                liqpro.setC14fk_idtercero_importador(rs.getInt(14));
                liqpro.setC15fk_idtercero_despachante(rs.getInt(15));
                liqpro.setC16fk_idtercero_colaborador(rs.getInt(16));
                liqpro.setC17fk_idtercero_transportadora(rs.getInt(17));
                liqpro.setC18fk_idaduana(rs.getInt(18));
                liqpro.setC19observacion(rs.getString(19));
                liqpro.setC20tipo_tasa_cambio(rs.getString(20));
                liqpro.setC21tipo_liquidacion(rs.getString(21));
                liqpro.setC22fk_idhonorario_despacho(rs.getInt(22));
                liqpro.setC23monto_boleto_despachante(rs.getDouble(23));
                liqpro.setC24monto_honorario_despachante(rs.getDouble(24));
                liqpro.setC25fk_idmoneda_cambio(rs.getInt(25));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + liqpro.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + liqpro.toString(), titulo);
        }
    }

    public void actualizar_tabla_liquidacion_proforma(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_liquidacion_proforma(tbltabla);
    }

    public void ancho_tabla_liquidacion_proforma(JTable tbltabla) {
        int Ancho[] = {5, 10, 10,15,25,5,10,10,10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
