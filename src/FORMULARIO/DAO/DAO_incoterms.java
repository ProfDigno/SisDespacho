package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.incoterms;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_incoterms {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "INCOTERMS GUARDADO CORRECTAMENTE";
    private String mensaje_update = "INCOTERMS MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO incoterms(idincoterms,nombre,sigla,descripcion) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE incoterms SET nombre=?,sigla=?,descripcion=? WHERE idincoterms=?;";
    private String sql_select = "SELECT idincoterms,nombre,sigla,descripcion FROM incoterms order by 1 desc;";
    private String sql_cargar = "SELECT idincoterms,nombre,sigla,descripcion FROM incoterms WHERE idincoterms=";

    public void insertar_incoterms(Connection conn, incoterms inc) {
        inc.setC1idincoterms(eveconn.getInt_ultimoID_mas_uno(conn, inc.getTb_incoterms(), inc.getId_idincoterms()));
        String titulo = "insertar_incoterms";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, inc.getC1idincoterms());
            pst.setString(2, inc.getC2nombre());
            pst.setString(3, inc.getC3sigla());
            pst.setString(4, inc.getC4descripcion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + inc.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + inc.toString(), titulo);
        }
    }

    public void update_incoterms(Connection conn, incoterms inc) {
        String titulo = "update_incoterms";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, inc.getC2nombre());
            pst.setString(2, inc.getC3sigla());
            pst.setString(3, inc.getC4descripcion());
            pst.setInt(4, inc.getC1idincoterms());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + inc.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + inc.toString(), titulo);
        }
    }

    public void cargar_incoterms(Connection conn, incoterms inc, int id) {
        String titulo = "Cargar_incoterms";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                inc.setC1idincoterms(rs.getInt(1));
                inc.setC2nombre(rs.getString(2));
                inc.setC3sigla(rs.getString(3));
                inc.setC4descripcion(rs.getString(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + inc.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + inc.toString(), titulo);
        }
    }

    public void actualizar_tabla_incoterms(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_incoterms(tbltabla);
    }

    public void ancho_tabla_incoterms(JTable tbltabla) {
        int Ancho[] = {5,60, 15, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
