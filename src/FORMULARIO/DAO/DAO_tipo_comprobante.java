package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_comprobante;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_comprobante {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_COMPROBANTE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_COMPROBANTE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_comprobante(idtipo_comprobante,descripcion,"
            + "con_comprobante,sin_comprobante,boleta_despachante,mercaderia,tipo_factura,eliminado) VALUES (?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE tipo_comprobante SET descripcion=?,"
            + "con_comprobante=?,sin_comprobante=?,boleta_despachante=?,mercaderia=?,tipo_factura=?,eliminado=? WHERE idtipo_comprobante=?;";
    private String sql_cargar = "SELECT idtipo_comprobante,descripcion,"
            + "con_comprobante,sin_comprobante,boleta_despachante,mercaderia,tipo_factura FROM tipo_comprobante WHERE idtipo_comprobante=";
    public void insertar_tipo_comprobante(Connection conn, tipo_comprobante tgali) {
        tgali.setC1idtipo_comprobante(eveconn.getInt_ultimoID_mas_uno(conn, tgali.getTb_tipo_comprobante(), tgali.getId_idtipo_comprobante()));
        tgali.setC8eliminado(false);
        String titulo = "insertar_tipo_comprobante";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tgali.getC1idtipo_comprobante());
            pst.setString(2, tgali.getC2descripcion());
            pst.setBoolean(3, tgali.getC3con_comprobante());
            pst.setBoolean(4, tgali.getC4sin_comprobante());
            pst.setBoolean(5, tgali.getC5boleta_despachante());
            pst.setBoolean(6, tgali.getC6mercaderia());
            pst.setBoolean(7, tgali.getC7tipo_factura());
            pst.setBoolean(8, tgali.getC8eliminado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tgali.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tgali.toString(), titulo);
        }
    }

    public void update_tipo_comprobante(Connection conn, tipo_comprobante tgali) {
        String titulo = "update_tipo_comprobante";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, tgali.getC2descripcion());
            pst.setBoolean(2, tgali.getC3con_comprobante());
            pst.setBoolean(3, tgali.getC4sin_comprobante());
            pst.setBoolean(4, tgali.getC5boleta_despachante());
            pst.setBoolean(5, tgali.getC6mercaderia());
            pst.setBoolean(6, tgali.getC7tipo_factura());
            pst.setBoolean(7, tgali.getC8eliminado());
            pst.setInt(8, tgali.getC1idtipo_comprobante());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tgali.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tgali.toString(), titulo);
        }
    }

    public void cargar_tipo_comprobante(Connection conn, tipo_comprobante tgali, int id) {
        String titulo = "Cargar_tipo_comprobante";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                tgali.setC1idtipo_comprobante(rs.getInt(1));
                tgali.setC2descripcion(rs.getString(2));
                tgali.setC3con_comprobante(rs.getBoolean(3));
                tgali.setC4sin_comprobante(rs.getBoolean(4));
                tgali.setC5boleta_despachante(rs.getBoolean(5));
                tgali.setC6mercaderia(rs.getBoolean(6));
                tgali.setC7tipo_factura(rs.getBoolean(7));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tgali.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tgali.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_comprobante(Connection conn, JTable tbltabla, String filtro) {
        String sql_select = "SELECT idtipo_comprobante as idg,descripcion,\n"
                + "case \n"
                + "when con_comprobante=true then 'CON-COMPROBANTE' \n"
                + "when sin_comprobante=true then 'SIN-COMPROBANTE' \n"
                + "when boleta_despachante=true then 'BOLETA-DESPA' \n"
                + "when mercaderia=true then 'MERCADERIA' \n"
                + "when tipo_factura=true then 'TIPO-FACTURA' \n"
                + "else 'SIN-TIPO' end as tipo  "
                + "FROM tipo_comprobante  where eliminado=false "
                + " " + filtro+"  "
                + " order by 3 desc;";
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_comprobante(tbltabla);
    }

    public void ancho_tabla_tipo_comprobante(JTable tbltabla) {
        int Ancho[] = {10, 50,40};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
