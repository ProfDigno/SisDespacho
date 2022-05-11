package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import CONFIGURACION.EveVarGlobal;
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
    EveVarGlobal varglo=new EveVarGlobal();
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
            + "fk_idmoneda_cambio,fk_idregimen,fk_idincoterms,fecha_pagado,monto_pagado,otro_nombre,otro_monto,"
            + "fk_idtercero_despachante,fk_idrecibo_pago_tercero) \n"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);\n";

    private String sql_cargar = "SELECT idliquidacion_final,fecha_creado,creado_por,fecha_despacho,despacho_numero,tipo_liquidacion,estado,observacion,"
            + "contenedor_nro,contenedor_tipo,via_transporte,transporte_condicion,"
            + "monto_imponible,monto_ajuste_incluir,monto_factura,monto_flete,"
            + "monto_seguro,monto_cif,monto_total_despacho,monto_adelanto,monto_pagar,"
            + "tasa_cambio_aduana,tasa_cambio_mercado,tipo_tasa_cambio,"
            + "factura_numero,monto_letra,"
            + "fk_idtipo_comprobante,fk_idtercero_ciudad,fk_idaduana,fk_iddespacho_zona,"
            + "fk_idtransporte_empresa,fk_idtercero_importador,fk_idtercero_transportadora,"
            + "fk_idmoneda_cambio,fk_idregimen,fk_idincoterms,fecha_pagado,monto_pagado,otro_nombre,otro_monto,fk_idtercero_despachante,fk_idrecibo_pago_tercero "
            + "FROM liquidacion_final WHERE idliquidacion_final=";
    private String sql_update_estado = "UPDATE liquidacion_final SET estado=? WHERE idliquidacion_final=?;";
    private String sql_update = "UPDATE liquidacion_final SET fecha_creado=?,creado_por=?,fecha_despacho=?,despacho_numero=?,tipo_liquidacion=?,estado=?,observacion=?,"
            + "contenedor_nro=?,contenedor_tipo=?,via_transporte=?,transporte_condicion=?,"
            + "monto_imponible=?,monto_ajuste_incluir=?,monto_factura=?,monto_flete=?,"
            + "monto_seguro=?,monto_cif=?,monto_total_despacho=?,monto_adelanto=?,monto_pagar=?,"
            + "tasa_cambio_aduana=?,tasa_cambio_mercado=?,tipo_tasa_cambio=?,"
            + "factura_numero=?,monto_letra=?,"
            + "fk_idtipo_comprobante=?,fk_idtercero_ciudad=?,fk_idaduana=?,fk_iddespacho_zona=?,"
            + "fk_idtransporte_empresa=?,fk_idtercero_importador=?,fk_idtercero_transportadora=?,"
            + "fk_idmoneda_cambio=?,fk_idregimen=?,fk_idincoterms=?,fecha_pagado=?,monto_pagado=?,otro_nombre=?,otro_monto=? WHERE idliquidacion_final=?;";

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
            pst.setInt(33, liqfin.getC33fk_idtercero_exportador());
            pst.setInt(34, liqfin.getC34fk_idmoneda_cambio());
            pst.setInt(35, liqfin.getC35fk_idregimen());
            pst.setInt(36, liqfin.getC36fk_idincoterms());
            pst.setTimestamp(37, evefec.getTimestamp_sistema());
            pst.setDouble(38, liqfin.getC38monto_pagado());
            pst.setString(39, liqfin.getC39otro_nombre());
            pst.setDouble(40, liqfin.getC40otro_monto());
            pst.setInt(41, liqfin.getC41fk_idtercero_despachante());
            pst.setInt(42, liqfin.getC42fk_idrecibo_pago_tercero());
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
            pst.setInt(32, liqfin.getC33fk_idtercero_exportador());
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

    public void estado_update_liquidacion_final(Connection conn, liquidacion_final liqfin) {
        String titulo = "estado_update_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_estado);
            pst.setString(1, liqfin.getC7estado());
            pst.setInt(2, liqfin.getC1idliquidacion_final());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_estado + "\n" + liqfin.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_estado + "\n" + liqfin.toString(), titulo);
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
                liqfin.setC33fk_idtercero_exportador(rs.getInt(33));
                liqfin.setC34fk_idmoneda_cambio(rs.getInt(34));
                liqfin.setC35fk_idregimen(rs.getInt(35));
                liqfin.setC36fk_idincoterms(rs.getInt(36));
                liqfin.setC37fecha_pagado(rs.getString(37));
                liqfin.setC38monto_pagado(rs.getDouble(38));
                liqfin.setC39otro_nombre(rs.getString(39));
                liqfin.setC40otro_monto(rs.getDouble(40));
                liqfin.setC41fk_idtercero_despachante(rs.getInt(41));
                liqfin.setC42fk_idrecibo_pago_tercero(rs.getInt(42));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + liqfin.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + liqfin.toString(), titulo);
        }
    }

    public void actualizar_tabla_liquidacion_final(Connection conn, JTable tbltabla, String filtro) {

        String sql_1 = "select lf.idliquidacion_final as idlf,ti.nombre as importado,tex.nombre as exportador,\n"
                + "to_char(lf.fecha_despacho,'"+evefec.getFormato_fecha()+"') as fec_despacho,lf.despacho_numero as despacho_nro,lf.factura_numero as factura_nro,\n"
                + "ad.nombre as aduana,re.sigla as regi,\n"
                + "case when lf.tipo_liquidacion='" + varglo.getLiq_Importacion() + "' then 'IMP'\n"
                + "     when lf.tipo_liquidacion='" + varglo.getLiq_Exportacion() + "' then 'EXP'\n"
                + "     when lf.tipo_liquidacion='" + varglo.getLiq_proforma() + "' then 'PRO'\n"
                + "     else '"+varglo.getEst_Error()+"' end as tipo,\n"
                + "TRIM(to_char(lf.monto_imponible,'"+varglo.getFormato_numero_4c()+"')) as mon_imponible,\n"
                + "TRIM(to_char(lf.monto_pagar,'"+varglo.getFormato_numero_4c()+"')) as mon_pagar,\n"
                + "TRIM(to_char(lf.monto_pagado,'"+varglo.getFormato_numero_4c()+"')) as mon_pagado,\n"
                + "TRIM(to_char(((lf.monto_total_despacho/lf.monto_imponible)*100),'"+varglo.getFormato_numero_2c()+"')) as util, lf.estado,ti.idtercero as idi\n"
                + "from liquidacion_final lf,tercero ti,tercero tex,aduana ad,regimen re\n"
                + "where lf.fk_idtercero_importador=ti.idtercero\n"
                + "and lf.fk_idtercero_transportadora=tex.idtercero \n"
                + "and lf.fk_idaduana=ad.idaduana \n"
                + "and lf.fk_idregimen=re.idregimen \n" + filtro
                + " order by 1 desc";
        eveconn.Select_cargar_jtable(conn, sql_1, tbltabla);
        evejt.ocultar_columna(tbltabla, 14);
        ancho_tabla_liquidacion_final(tbltabla);
    }

    public void ancho_tabla_liquidacion_final(JTable tbltabla) {
        int Ancho[] = {2, 14, 14, 6, 9, 9, 9, 4, 4, 8, 7, 7, 3, 6,1};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void imprimir_rep_liquidacion_final(Connection conn, int id) {
        String sql = "select lf.idliquidacion_final as idlqui,lf.tipo_liquidacion as tipo,lf.fecha_despacho as fec_despacho,\n"
                + "ti.nombre as importa,lf.contenedor_nro as contene_nro,tc.descripcion as merca,tc2.nombre as destino,\n"
                + "lf.via_transporte as via,ad.nombre as aduana,te.nombre as trans_emp,lf.transporte_condicion as trans_condi,\n"
                + "lf.despacho_numero as despacho_nro,dz.nombre as zona,\n"
                + "tex.nombre as exportador,\n"
                + "lf.monto_imponible as m_imponible,lf.monto_ajuste_incluir as m_ajuste,\n"
                + "lf.monto_factura as m_factura,lf.factura_numero as  nro_factura,\n"
                + "lf.monto_flete as m_flete,lf.monto_seguro as m_seguro,\n"
                + "lf.monto_cif as m_cif,(lf.monto_cif+lf.monto_ajuste_incluir) as m_cif_ajuste ,lf.tasa_cambio_aduana as tc_aduana,lf.tipo_tasa_cambio as moneda,\n"
                + "re.sigla as regime,ic.sigla as incotern,lf.monto_total_despacho as m_ttdespacho,\n"
                + "lf.monto_adelanto as m_adelanto,lf.monto_pagar as m_pagar,lf.monto_letra as m_letra,lf.observacion as observa,\n"
                + "ilf.orden as i_ord,ilf.descripcion as i_descrip,ilf.comprobante_nro as i_comprobante,\n"
                + "ilf.total_guarani as i_total,ilf.sin_iva as i_sin_iva,ilf.solo_iva as i_solo_iva, \n"
                + "lf.otro_nombre as otro_nombre,lf.otro_monto as otro_monto, \n"
                + "((lf.monto_total_despacho/lf.monto_imponible)*100) as utilidad "
                + "from liquidacion_final lf,tercero ti,tipo_comprobante tc,tercero_ciudad tc2,aduana ad,transporte_empresa te,\n"
                + "despacho_zona dz,tercero tex,regimen re,incoterms ic,item_liquidacion_final ilf \n"
                + "where lf.fk_idtercero_importador=ti.idtercero\n"
                + "and lf.fk_idtercero_transportadora=tex.idtercero \n"
                + "and lf.fk_idtipo_comprobante=tc.idtipo_comprobante\n"
                + "and lf.fk_idtercero_ciudad=tc2.idtercero_ciudad\n"
                + "and lf.fk_idaduana=ad.idaduana \n"
                + "and lf.fk_idtransporte_empresa=te.idtransporte_empresa\n"
                + "and lf.fk_iddespacho_zona=dz.iddespacho_zona \n"
                + "and lf.fk_idregimen=re.idregimen \n"
                + "and lf.fk_idincoterms=ic.idincoterms \n"
                + "and lf.idliquidacion_final=ilf.fk_idliquidacion_final \n"
                + "and lf.idliquidacion_final=" + id
                + " order by ilf.orden asc;";
        String titulonota = varglo.getLiq_Importacion();
        String direccion = "src/REPORTE/LIQUIDACION/repLiquidacionFin_1.jrxml";
        String rutatemp = "Liquidacion_" + evefec.getString_formato_fecha() + "_" + id;
        rep.imprimir_jasper_o_pdf(conn, sql, titulonota, direccion, rutatemp);
    }

    public void imprimir_rep_cuenta_liquidacion(Connection conn, int idtercero, String filtrofecha) {
        String sql = "select ter.idtercero as idter,ter.nombre as cliente,ter.direccion as direccion,\n"
                + "ter.ruc as ruc,ter.telefono as telefono,tr.nombre as rubro,\n"
                + "lf.idliquidacion_final as idlf,trim(to_char(lf.fecha_despacho,'"+evefec.getFormato_fecha()+"')) as fec_despacho,\n"
                + "lf.despacho_numero as despacho_nro,lf.factura_numero  as factura_nro,\n"
                + "substring(lf.tipo_liquidacion,1,3) as tipo, \n"
                + "lf.monto_pagar as mon_pagar,lf.monto_pagado as mon_pagado,\n"
                + "(lf.monto_pagado-lf.monto_pagar) as saldo,\n"
                + "case when (lf.monto_pagado-lf.monto_pagar)=0 then lf.estado \n"
                + "     when ((lf.monto_pagado-lf.monto_pagar)<0 and lf.monto_pagado=0) then '"+varglo.getEst_Credito()+"'\n"
                + "     when ((lf.monto_pagado-lf.monto_pagar)<0 and lf.monto_pagado>0) then '"+varglo.getEst_PagoParcial()+"' else '"+varglo.getEst_Error()+"' end as estado,\n"
                + "case when lf.estado='" + varglo.getEst_Pagado() + "' then to_char(lf.fecha_pagado,'"+evefec.getFormato_fecha()+"') else '"+varglo.getEst_FaltaPagar()+"' end as fec_pago  \n"
                + "from tercero ter,liquidacion_final lf,tercero_rubro tr  \n"
                + "where lf.fk_idtercero_importador=ter.idtercero \n"
                + "and ter.fk_idtercero_rubro=tr.idtercero_rubro \n"
                + "and (lf.estado='" + varglo.getEst_Pagado() + "' or lf.estado='" + varglo.getEst_Emitido() + "') \n"
                + "and ter.idtercero=" + idtercero + filtrofecha
                + " order by lf.idliquidacion_final desc;";
        String titulonota = "CUENTA LIQUIDACION";
        String direccion = "src/REPORTE/TERCERO/repTerceroLiquidacionPagado.jrxml";
        String rutatemp = "Cuenta_liquidacion_" + evefec.getString_formato_fecha() + "_" + idtercero;
        rep.imprimir_jasper_o_pdf(conn, sql, titulonota, direccion, rutatemp);
    }

    public void imprimir_liquidacion_filtro(Connection conn, String filtro) {
        String sql_select = "select lf.idliquidacion_final as idlf,ti.idtercero as idi,ti.nombre as importado,\n"
                + "to_char(lf.fecha_despacho,'"+evefec.getFormato_fecha()+"') as fec_despacho,lf.despacho_numero as despacho_nro,lf.factura_numero as factura_nro,\n"
                + "ad.nombre as aduana,re.sigla as regi,\n"
                + "case when lf.tipo_liquidacion='" + varglo.getLiq_Importacion() + "' then 'IMP'\n"
                + "     when lf.tipo_liquidacion='" + varglo.getLiq_Exportacion() + "' then 'EXP'\n"
                + "     when lf.tipo_liquidacion='" + varglo.getLiq_proforma() + "' then 'PRO'\n"
                + "     else 'error' end as tipo,\n"
                + "lf.monto_imponible as mon_imponible,\n"
                + "lf.monto_pagar as mon_pagar,\n"
                + "lf.monto_pagado as mon_pagado,\n"
                + "((lf.monto_total_despacho/lf.monto_imponible)*100) as util, "
                + "lf.estado\n"
                + "from liquidacion_final lf,tercero ti,aduana ad,regimen re\n"
                + "where lf.fk_idtercero_importador=ti.idtercero\n"
                + "and lf.fk_idaduana=ad.idaduana \n"
                + "and lf.fk_idregimen=re.idregimen \n" + filtro
                + " order by 1 desc";
        String titulonota = "FILTRO LIQUIDACION";
        String direccion = "src/REPORTE/LIQUIDACION/repLiquidacionPorFiltro.jrxml";
        String rutatemp = "Filtr_liquidacion_" + evefec.getString_formato_fecha();
        rep.imprimir_jasper_o_pdf(conn, sql_select, titulonota, direccion, rutatemp);
    }
}
