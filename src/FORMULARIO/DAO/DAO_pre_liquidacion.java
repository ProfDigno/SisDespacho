package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.pre_liquidacion;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_pre_liquidacion {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "PRE_LIQUIDACION GUARDADO CORRECTAMENTE";
    private String mensaje_update = "PRE_LIQUIDACION MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO pre_liquidacion(idpre_liquidacion,fecha_creado,creado_por,"
            + "fecha_factura,fecha_llegada,numero_factura,numero_invoice,monto_factura,"
            + "descripcion,observacion,tipo_liquidacion,estado,"
            + "fk_idtercero_importador,fk_idtercero_exportador) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE pre_liquidacion SET fecha_creado=?,creado_por=?,"
            + "fecha_factura=?,fecha_llegada=?,numero_factura=?,numero_invoice=?,monto_factura=?,"
            + "descripcion=?,observacion=?,tipo_liquidacion=?,estado=?,"
            + "fk_idtercero_importador=?,fk_idtercero_exportador=? WHERE idpre_liquidacion=?;";
    private String sql_select = "SELECT idpre_liquidacion,fecha_creado,creado_por,"
            + "fecha_factura,fecha_llegada,numero_factura,numero_invoice,monto_factura,"
            + "descripcion,observacion,tipo_liquidacion,estado,"
            + "fk_idtercero_importador,fk_idtercero_exportador FROM pre_liquidacion order by 1 desc;";
    private String sql_cargar = "SELECT idpre_liquidacion,fecha_creado,creado_por,"
            + "fecha_factura,fecha_llegada,numero_factura,numero_invoice,monto_factura,"
            + "descripcion,observacion,tipo_liquidacion,estado,"
            + "fk_idtercero_importador,fk_idtercero_exportador FROM pre_liquidacion WHERE idpre_liquidacion=";

    public void insertar_pre_liquidacion(Connection conn, pre_liquidacion pl) {
        pl.setC1idpre_liquidacion(eveconn.getInt_ultimoID_mas_uno(conn, pl.getTb_pre_liquidacion(), pl.getId_idpre_liquidacion()));
        String titulo = "insertar_pre_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, pl.getC1idpre_liquidacion());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, pl.getC3creado_por());
            pst.setDate(4, evefec.getDateSQL_fecha_cargado_sinformat(pl.getC4fecha_factura()));
            pst.setDate(5, evefec.getDateSQL_fecha_cargado_sinformat(pl.getC5fecha_llegada()));
            pst.setString(6, pl.getC6numero_factura());
            pst.setString(7, pl.getC7numero_invoice());
            pst.setDouble(8, pl.getC8monto_factura());
            pst.setString(9, pl.getC9descripcion());
            pst.setString(10, pl.getC10observacion());
            pst.setString(11, pl.getC11tipo_liquidacion());
            pst.setString(12, pl.getC12estado());
            pst.setInt(13, pl.getC13fk_idtercero_importador());
            pst.setInt(14, pl.getC14fk_idtercero_exportador());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + pl.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + pl.toString(), titulo);
        }
    }

    public void update_pre_liquidacion(Connection conn, pre_liquidacion pl) {
        String titulo = "update_pre_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, pl.getC3creado_por());
            pst.setDate(3, evefec.getDateSQL_sistema());
            pst.setDate(4, evefec.getDateSQL_sistema());
            pst.setString(5, pl.getC6numero_factura());
            pst.setString(6, pl.getC7numero_invoice());
            pst.setDouble(7, pl.getC8monto_factura());
            pst.setString(8, pl.getC9descripcion());
            pst.setString(9, pl.getC10observacion());
            pst.setString(10, pl.getC11tipo_liquidacion());
            pst.setString(11, pl.getC12estado());
            pst.setInt(12, pl.getC13fk_idtercero_importador());
            pst.setInt(13, pl.getC14fk_idtercero_exportador());
            pst.setInt(14, pl.getC1idpre_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + pl.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + pl.toString(), titulo);
        }
    }

    public void cargar_pre_liquidacion(Connection conn, pre_liquidacion pl, int idpre_liquidacion) {
        String titulo = "Cargar_pre_liquidacion";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idpre_liquidacion, titulo);
            if (rs.next()) {
                pl.setC1idpre_liquidacion(rs.getInt(1));
                pl.setC2fecha_creado(rs.getString(2));
                pl.setC3creado_por(rs.getString(3));
                pl.setC4fecha_factura(rs.getString(4));
                pl.setC5fecha_llegada(rs.getString(5));
                pl.setC6numero_factura(rs.getString(6));
                pl.setC7numero_invoice(rs.getString(7));
                pl.setC8monto_factura(rs.getDouble(8));
                pl.setC9descripcion(rs.getString(9));
                pl.setC10observacion(rs.getString(10));
                pl.setC11tipo_liquidacion(rs.getString(11));
                pl.setC12estado(rs.getString(12));
                pl.setC13fk_idtercero_importador(rs.getInt(13));
                pl.setC14fk_idtercero_exportador(rs.getInt(14));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + pl.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + pl.toString(), titulo);
        }
    }

    public void actualizar_tabla_pre_liquidacion(Connection conn, JTable tbltabla,String filtro) {
        String sql = "select pl.idpre_liquidacion as idpl,ti.nombre as importador,te.nombre as exportador,\n"
                + "to_char(pl.fecha_factura,'dd-MM-yyyy') as fec_fact,"
                + "to_char(pl.monto_factura,'999G999G999D99') as fact_dolar,\n"
                + "to_char(coalesce ((select sum(ipl.total_guarani)  from item_pre_liquidacion ipl where ipl.fk_idpre_liquidacion=pl.idpre_liquidacion),0),'999G999G999G999') as gasto_gs,\n"
                + "pl.numero_factura as nro_fact,pl.numero_invoice as invoice,\n"
                + "case when pl.tipo_liquidacion='IMPORTACIÓN' then 'IMP'\n"
                + "     when pl.tipo_liquidacion='EXPORTACIÓN' then 'EXP'\n"
                + "     else 'error' end as tipo ,\n"
                + "to_char(pl.fecha_llegada,'dd-MM-yyyy') as llega,pl.estado  \n"
                + "from pre_liquidacion pl,tercero ti,tercero te\n"
                + "where pl.fk_idtercero_importador=ti.idtercero \n"
                + "and pl.fk_idtercero_exportador=te.idtercero \n"
                + "order by pl.idpre_liquidacion desc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_pre_liquidacion(tbltabla);
    }

    public void ancho_tabla_pre_liquidacion(JTable tbltabla) {
        int Ancho[] = {4,20,20,6, 7, 7,8,8,5,8, 7};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
        evejt.alinear_derecha_columna(tbltabla, 4);
        evejt.alinear_derecha_columna(tbltabla, 5);
    }
}
