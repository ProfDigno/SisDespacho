package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.comprobante_liquidacion;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_comprobante_liquidacion {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "COMPROBANTE_LIQUIDACION GUARDADO CORRECTAMENTE";
    private String mensaje_update = "COMPROBANTE_LIQUIDACION MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO comprobante_liquidacion(idcomprobante_liquidacion,descripcion,por_iva,tipo_iva,nro_despacho) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE comprobante_liquidacion SET descripcion=?,por_iva=?,tipo_iva=?,nro_despacho=? WHERE idcomprobante_liquidacion=?;";
    private String sql_select = "SELECT idcomprobante_liquidacion as idc,descripcion,por_iva as p_iva,tipo_iva,nro_despacho as nro_des FROM comprobante_liquidacion order by 1 desc;";
    private String sql_cargar = "SELECT idcomprobante_liquidacion,descripcion,por_iva,tipo_iva,nro_despacho FROM comprobante_liquidacion WHERE idcomprobante_liquidacion=";

    public void insertar_comprobante_liquidacion(Connection conn, comprobante_liquidacion coliq) {
        coliq.setC1idcomprobante_liquidacion(eveconn.getInt_ultimoID_mas_uno(conn, coliq.getTb_comprobante_liquidacion(), coliq.getId_idcomprobante_liquidacion()));
        String titulo = "insertar_comprobante_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, coliq.getC1idcomprobante_liquidacion());
            pst.setString(2, coliq.getC2descripcion());
            pst.setDouble(3, coliq.getC3por_iva());
            pst.setString(4, coliq.getC4tipo_iva());
            pst.setBoolean(5, coliq.getC5nro_despacho());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + coliq.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + coliq.toString(), titulo);
        }
    }

    public void update_comprobante_liquidacion(Connection conn, comprobante_liquidacion coliq) {
        String titulo = "update_comprobante_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, coliq.getC2descripcion());
            pst.setDouble(2, coliq.getC3por_iva());
            pst.setString(3, coliq.getC4tipo_iva());
            pst.setBoolean(4, coliq.getC5nro_despacho());
            pst.setInt(5, coliq.getC1idcomprobante_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + coliq.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + coliq.toString(), titulo);
        }
    }

    public void cargar_comprobante_liquidacion(Connection conn, comprobante_liquidacion coliq, int id) {
        String titulo = "Cargar_comprobante_liquidacion";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                coliq.setC1idcomprobante_liquidacion(rs.getInt(1));
                coliq.setC2descripcion(rs.getString(2));
                coliq.setC3por_iva(rs.getDouble(3));
                coliq.setC4tipo_iva(rs.getString(4));
                coliq.setC5nro_despacho(rs.getBoolean(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + coliq.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + coliq.toString(), titulo);
        }
    }

    public void actualizar_tabla_comprobante_liquidacion(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_comprobante_liquidacion(tbltabla);
    }

    public void ancho_tabla_comprobante_liquidacion(JTable tbltabla) {
        int Ancho[] = {5,35,10, 30, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
