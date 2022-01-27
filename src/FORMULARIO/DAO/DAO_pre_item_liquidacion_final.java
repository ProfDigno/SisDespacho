package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.pre_item_liquidacion_final;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_pre_item_liquidacion_final {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "PRE_ITEM_LIQUIDACION_FINAL GUARDADO CORRECTAMENTE";
    private String mensaje_update = "PRE_ITEM_LIQUIDACION_FINAL MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO pre_item_liquidacion_final(idpre_item_liquidacion_final,orden,fk_idcomprobante_liquidacion) VALUES (?,?,?);";
    private String sql_update = "UPDATE pre_item_liquidacion_final SET orden=?,fk_idcomprobante_liquidacion=? WHERE idpre_item_liquidacion_final=?;";
    private String sql_select = "select pi.idpre_item_liquidacion_final as idp,pi.fk_idcomprobante_liquidacion as idc,pi.orden,\n"
            + "cl.descripcion,cl.por_iva as p_iva,cl.tipo_iva as dir_iva,cl.nro_despacho as nro_des\n"
            + "from pre_item_liquidacion_final pi,comprobante_liquidacion cl\n"
            + "where pi.fk_idcomprobante_liquidacion=cl.idcomprobante_liquidacion\n"
            + "order by pi.orden asc;;";
    private String sql_cargar = "SELECT idpre_item_liquidacion_final,orden,fk_idcomprobante_liquidacion FROM pre_item_liquidacion_final WHERE idpre_item_liquidacion_final=";

    public void insertar_pre_item_liquidacion_final(Connection conn, pre_item_liquidacion_final pilf) {
        pilf.setC1idpre_item_liquidacion_final(eveconn.getInt_ultimoID_mas_uno(conn, pilf.getTb_pre_item_liquidacion_final(), pilf.getId_idpre_item_liquidacion_final()));
        String titulo = "insertar_pre_item_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, pilf.getC1idpre_item_liquidacion_final());
            pst.setInt(2, pilf.getC2orden());
            pst.setInt(3, pilf.getC3fk_idcomprobante_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + pilf.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + pilf.toString(), titulo);
        }
    }

    public void update_pre_item_liquidacion_final(Connection conn, pre_item_liquidacion_final pilf) {
        String titulo = "update_pre_item_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setInt(1, pilf.getC2orden());
            pst.setInt(2, pilf.getC3fk_idcomprobante_liquidacion());
            pst.setInt(3, pilf.getC1idpre_item_liquidacion_final());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + pilf.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + pilf.toString(), titulo);
        }
    }

    public void cargar_pre_item_liquidacion_final(Connection conn, pre_item_liquidacion_final pilf, int id) {
        String titulo = "Cargar_pre_item_liquidacion_final";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                pilf.setC1idpre_item_liquidacion_final(rs.getInt(1));
                pilf.setC2orden(rs.getInt(2));
                pilf.setC3fk_idcomprobante_liquidacion(rs.getInt(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + pilf.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + pilf.toString(), titulo);
        }
    }

    public void actualizar_tabla_pre_item_liquidacion_final(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_pre_item_liquidacion_final(tbltabla);
    }

    public void ancho_tabla_pre_item_liquidacion_final(JTable tbltabla) {
        int Ancho[] = {5,5,10,40,10,20,10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
