package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.liquidacion_final;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_liquidacion_final {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "LIQUIDACION_FINAL GUARDADO CORRECTAMENTE";
    private String mensaje_update = "LIQUIDACION_FINAL MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO liquidacion_final(idliquidacion_final,fecha_creado,creado_por,fecha_despacho,despacho_numero,tipo_liquidacion,estado,observacion,\n"
            + "contenedor_nro,contenedor_tipo,via_transporte,transporte_condicion,\n"
            + "monto_imponible,monto_ajuste_incluir,monto_factura,monto_flete,\n"
            + "monto_seguro,monto_cif,monto_total_despacho,monto_adelanto,monto_pagar,\n"
            + "tasa_cambio_aduana,tasa_cambio_mercado,tipo_tasa_cambio,\n"
            + "factura_numero,monto_letra,\n"
            + "fk_idtipo_comprobante,fk_idtercero_ciudad,fk_idaduana,fk_iddespacho_zona,\n"
            + "fk_idtransporte_empresa,fk_idtercero_importador,fk_idtercero_transportadora,\n"
            + "fk_idmoneda_cambio,fk_idregimen,fk_idincoterms) \n"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);\n";
    private String sql_update = "UPDATE liquidacion_final SET fecha_creado=?,creado_por=?,fecha_despacho=?,despacho_numero=?,tipo_liquidacion=?,estado=?,observacion=?,contenedor_nro=?,contenedor_tipo=?,via_transporte=?,transporte_condicion=?,monto_imponible=?,monto_ajuste_incluir=?,monto_factura=?,monto_flete=?,monto_seguro=?,monto_cif=?,monto_total_despacho=?,monto_adelanto=?,monto_pagar=?,tasa_cambio_aduana=?,tasa_cambio_mercado=?,tipo_tasa_cambio=?,factura_numero=?,monto_letra=?,fk_idtipo_comprobante=?,fk_idtercero_ciudad=?,fk_idaduana=?,fk_iddespacho_zona=?,fk_idtransporte_empresa=?,fk_idtercero_importador=?,fk_idtercero_transportadora=?,fk_idmoneda_cambio=?,fk_idregimen=?,fk_idincoterms=? WHERE idliquidacion_final=?;";
//    private String sql_select = "SELECT idliquidacion_final,fecha_creado,creado_por,fecha_despacho,despacho_numero,tipo_liquidacion,estado,observacion,contenedor_nro,contenedor_tipo,via_transporte,transporte_condicion,monto_imponible,monto_ajuste_incluir,monto_factura,monto_flete,monto_seguro,monto_cif,monto_total_despacho,monto_adelanto,monto_pagar,tasa_cambio_aduana,tasa_cambio_mercado,tipo_tasa_cambio,factura_numero,monto_letra,fk_idtipo_comprobante,fk_idtercero_ciudad,fk_idaduana,fk_iddespacho_zona,fk_idtransporte_empresa,fk_idtercero_importador,fk_idtercero_transportadora,fk_idmoneda_cambio,fk_idregimen,fk_idincoterms FROM liquidacion_final order by 1 desc;";
    private String sql_cargar = "SELECT idliquidacion_final,fecha_creado,creado_por,fecha_despacho,despacho_numero,tipo_liquidacion,estado,observacion,contenedor_nro,contenedor_tipo,via_transporte,transporte_condicion,monto_imponible,monto_ajuste_incluir,monto_factura,monto_flete,monto_seguro,monto_cif,monto_total_despacho,monto_adelanto,monto_pagar,tasa_cambio_aduana,tasa_cambio_mercado,tipo_tasa_cambio,factura_numero,monto_letra,fk_idtipo_comprobante,fk_idtercero_ciudad,fk_idaduana,fk_iddespacho_zona,fk_idtransporte_empresa,fk_idtercero_importador,fk_idtercero_transportadora,fk_idmoneda_cambio,fk_idregimen,fk_idincoterms FROM liquidacion_final WHERE idliquidacion_final=";

    public void insertar_liquidacion_final(Connection conn, liquidacion_final liqfin) {
        liqfin.setC1idliquidacion_final(eveconn.getInt_ultimoID_mas_uno(conn, liqfin.getTb_liquidacion_final(), liqfin.getId_idliquidacion_final()));
        String titulo = "insertar_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, liqfin.getC1idliquidacion_final());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, liqfin.getC3creado_por());
            pst.setDate(4, evefec.getDateSQL_fecha_cargado_sinformat(liqfin.getC4fecha_despacho()));
            pst.setString(5, liqfin.getC5despacho_numero());
            pst.setString(6, liqfin.getC6tipo_liquidacion());
            pst.setString(7, liqfin.getC7estado());
            pst.setString(8, liqfin.getC8observacion());
            pst.setString(9, liqfin.getC9contenedor_nro());
            pst.setString(10, liqfin.getC10contenedor_tipo());
            pst.setString(11, liqfin.getC11via_transporte());
            pst.setString(12, liqfin.getC12transporte_condicion());
            pst.setDouble(13, liqfin.getC13monto_imponible());
            pst.setDouble(14, liqfin.getC14monto_ajuste_incluir());
            pst.setDouble(15, liqfin.getC15monto_factura());
            pst.setDouble(16, liqfin.getC16monto_flete());
            pst.setDouble(17, liqfin.getC17monto_seguro());
            pst.setDouble(18, liqfin.getC18monto_cif());
            pst.setDouble(19, liqfin.getC19monto_total_despacho());
            pst.setDouble(20, liqfin.getC20monto_adelanto());
            pst.setDouble(21, liqfin.getC21monto_pagar());
            pst.setDouble(22, liqfin.getC22tasa_cambio_aduana());
            pst.setDouble(23, liqfin.getC23tasa_cambio_mercado());
            pst.setString(24, liqfin.getC24tipo_tasa_cambio());
            pst.setString(25, liqfin.getC25factura_numero());
            pst.setString(26, liqfin.getC26monto_letra());
            pst.setInt(27, liqfin.getC27fk_idtipo_comprobante());
            pst.setInt(28, liqfin.getC28fk_idtercero_ciudad());
            pst.setInt(29, liqfin.getC29fk_idaduana());
            pst.setInt(30, liqfin.getC30fk_iddespacho_zona());
            pst.setInt(31, liqfin.getC31fk_idtransporte_empresa());
            pst.setInt(32, liqfin.getC32fk_idtercero_importador());
            pst.setInt(33, liqfin.getC33fk_idtercero_transportadora());
            pst.setInt(34, liqfin.getC34fk_idmoneda_cambio());
            pst.setInt(35, liqfin.getC35fk_idregimen());
            pst.setInt(36, liqfin.getC36fk_idincoterms());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + liqfin.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + liqfin.toString(), titulo);
        }
    }

    public void update_liquidacion_final(Connection conn, liquidacion_final liqfin) {
        String titulo = "update_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, liqfin.getC3creado_por());
            pst.setDate(3, evefec.getDateSQL_sistema());
            pst.setString(4, liqfin.getC5despacho_numero());
            pst.setString(5, liqfin.getC6tipo_liquidacion());
            pst.setString(6, liqfin.getC7estado());
            pst.setString(7, liqfin.getC8observacion());
            pst.setString(8, liqfin.getC9contenedor_nro());
            pst.setString(9, liqfin.getC10contenedor_tipo());
            pst.setString(10, liqfin.getC11via_transporte());
            pst.setString(11, liqfin.getC12transporte_condicion());
            pst.setDouble(12, liqfin.getC13monto_imponible());
            pst.setDouble(13, liqfin.getC14monto_ajuste_incluir());
            pst.setDouble(14, liqfin.getC15monto_factura());
            pst.setDouble(15, liqfin.getC16monto_flete());
            pst.setDouble(16, liqfin.getC17monto_seguro());
            pst.setDouble(17, liqfin.getC18monto_cif());
            pst.setDouble(18, liqfin.getC19monto_total_despacho());
            pst.setDouble(19, liqfin.getC20monto_adelanto());
            pst.setDouble(20, liqfin.getC21monto_pagar());
            pst.setDouble(21, liqfin.getC22tasa_cambio_aduana());
            pst.setDouble(22, liqfin.getC23tasa_cambio_mercado());
            pst.setString(23, liqfin.getC24tipo_tasa_cambio());
            pst.setString(24, liqfin.getC25factura_numero());
            pst.setString(25, liqfin.getC26monto_letra());
            pst.setInt(26, liqfin.getC27fk_idtipo_comprobante());
            pst.setInt(27, liqfin.getC28fk_idtercero_ciudad());
            pst.setInt(28, liqfin.getC29fk_idaduana());
            pst.setInt(29, liqfin.getC30fk_iddespacho_zona());
            pst.setInt(30, liqfin.getC31fk_idtransporte_empresa());
            pst.setInt(31, liqfin.getC32fk_idtercero_importador());
            pst.setInt(32, liqfin.getC33fk_idtercero_transportadora());
            pst.setInt(33, liqfin.getC34fk_idmoneda_cambio());
            pst.setInt(34, liqfin.getC35fk_idregimen());
            pst.setInt(35, liqfin.getC36fk_idincoterms());
            pst.setInt(36, liqfin.getC1idliquidacion_final());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + liqfin.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + liqfin.toString(), titulo);
        }
    }

    public void cargar_liquidacion_final(Connection conn, liquidacion_final liqfin, int id) {
        String titulo = "Cargar_liquidacion_final";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                liqfin.setC1idliquidacion_final(rs.getInt(1));
                liqfin.setC2fecha_creado(rs.getString(2));
                liqfin.setC3creado_por(rs.getString(3));
                liqfin.setC4fecha_despacho(rs.getString(4));
                liqfin.setC5despacho_numero(rs.getString(5));
                liqfin.setC6tipo_liquidacion(rs.getString(6));
                liqfin.setC7estado(rs.getString(7));
                liqfin.setC8observacion(rs.getString(8));
                liqfin.setC9contenedor_nro(rs.getString(9));
                liqfin.setC10contenedor_tipo(rs.getString(10));
                liqfin.setC11via_transporte(rs.getString(11));
                liqfin.setC12transporte_condicion(rs.getString(12));
                liqfin.setC13monto_imponible(rs.getDouble(13));
                liqfin.setC14monto_ajuste_incluir(rs.getDouble(14));
                liqfin.setC15monto_factura(rs.getDouble(15));
                liqfin.setC16monto_flete(rs.getDouble(16));
                liqfin.setC17monto_seguro(rs.getDouble(17));
                liqfin.setC18monto_cif(rs.getDouble(18));
                liqfin.setC19monto_total_despacho(rs.getDouble(19));
                liqfin.setC20monto_adelanto(rs.getDouble(20));
                liqfin.setC21monto_pagar(rs.getDouble(21));
                liqfin.setC22tasa_cambio_aduana(rs.getDouble(22));
                liqfin.setC23tasa_cambio_mercado(rs.getDouble(23));
                liqfin.setC24tipo_tasa_cambio(rs.getString(24));
                liqfin.setC25factura_numero(rs.getString(25));
                liqfin.setC26monto_letra(rs.getString(26));
                liqfin.setC27fk_idtipo_comprobante(rs.getInt(27));
                liqfin.setC28fk_idtercero_ciudad(rs.getInt(28));
                liqfin.setC29fk_idaduana(rs.getInt(29));
                liqfin.setC30fk_iddespacho_zona(rs.getInt(30));
                liqfin.setC31fk_idtransporte_empresa(rs.getInt(31));
                liqfin.setC32fk_idtercero_importador(rs.getInt(32));
                liqfin.setC33fk_idtercero_transportadora(rs.getInt(33));
                liqfin.setC34fk_idmoneda_cambio(rs.getInt(34));
                liqfin.setC35fk_idregimen(rs.getInt(35));
                liqfin.setC36fk_idincoterms(rs.getInt(36));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + liqfin.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + liqfin.toString(), titulo);
        }
    }

    public void actualizar_tabla_liquidacion_final(Connection conn, JTable tbltabla, String filtro) {
        String sql_select = "select lf.idliquidacion_final as idlf,ti.nombre as importado,\n"
                + "lf.fecha_despacho as fec_despacho,lf.despacho_numero as despacho_nro,lf.factura_numero as factura_nro,\n"
                + "ad.nombre as aduana,re.sigla as regi,\n"
                + "case when lf.tipo_liquidacion='IMPORTACION' then 'IMP'\n"
                + "     when lf.tipo_liquidacion='EXPORTACION' then 'EXP'\n"
                + "     else 'error' end as tipo,\n"
                + "TRIM(to_char(lf.monto_imponible,'999G999G999G999')) as mon_imponible,\n"
                + "TRIM(to_char(lf.monto_pagar,'999G999G999G999')) as mon_pagar,\n"
                + "lf.estado\n"
                + "from liquidacion_final lf,tercero ti,aduana ad,regimen re\n"
                + "where lf.fk_idtercero_importador=ti.idtercero\n"
                + "and lf.fk_idaduana=ad.idaduana \n"
                + "and lf.fk_idregimen=re.idregimen \n"+filtro
                + " order by 1 desc";
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_liquidacion_final(tbltabla);
    }

    public void ancho_tabla_liquidacion_final(JTable tbltabla) {
        int Ancho[] = {3, 19, 9, 12, 10, 10, 5, 5, 10, 10, 8};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
