package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_registro;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_registro {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_REGISTRO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_REGISTRO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_registro(idtipo_registro,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE tipo_registro SET nombre=? WHERE idtipo_registro=?;";
    private String sql_select = "SELECT idtipo_registro as id,nombre FROM tipo_registro order by 1 desc;";
    private String sql_cargar = "SELECT idtipo_registro,nombre FROM tipo_registro WHERE idtipo_registro=";

    public void insertar_tipo_registro(Connection conn, tipo_registro treg) {
        treg.setC1idtipo_registro(eveconn.getInt_ultimoID_mas_uno(conn, treg.getTb_tipo_registro(), treg.getId_idtipo_registro()));
        String titulo = "insertar_tipo_registro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, treg.getC1idtipo_registro());
            pst.setString(2, treg.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + treg.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + treg.toString(), titulo);
        }
    }

    public void update_tipo_registro(Connection conn, tipo_registro treg) {
        String titulo = "update_tipo_registro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, treg.getC2nombre());
            pst.setInt(2, treg.getC1idtipo_registro());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + treg.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + treg.toString(), titulo);
        }
    }

    public void cargar_tipo_registro(Connection conn, tipo_registro treg, int id) {
        String titulo = "Cargar_tipo_registro";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                treg.setC1idtipo_registro(rs.getInt(1));
                treg.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + treg.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + treg.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_registro(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_registro(tbltabla);
    }

    public void ancho_tabla_tipo_registro(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
