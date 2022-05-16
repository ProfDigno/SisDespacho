package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.regimen;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_regimen {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "REGIMEN GUARDADO CORRECTAMENTE";
    private String mensaje_update = "REGIMEN MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO regimen(idregimen,nombre,sigla,descripcion,eliminado) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE regimen SET nombre=?,sigla=?,descripcion=?,eliminado=? WHERE idregimen=?;";
    private String sql_select = "SELECT idregimen as idr,nombre as regimen,sigla,descripcion FROM regimen where eliminado=false order by 1 desc;";
    private String sql_cargar = "SELECT idregimen,nombre,sigla,descripcion FROM regimen WHERE idregimen=";

    public void insertar_regimen(Connection conn, regimen reg) {
        reg.setC1idregimen(eveconn.getInt_ultimoID_mas_uno(conn, reg.getTb_regimen(), reg.getId_idregimen()));
        reg.setC5eliminado(false);
        String titulo = "insertar_regimen";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, reg.getC1idregimen());
            pst.setString(2, reg.getC2nombre());
            pst.setString(3, reg.getC3sigla());
            pst.setString(4, reg.getC4descripcion());
            pst.setBoolean(5, reg.getC5eliminado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + reg.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + reg.toString(), titulo);
        }
    }

    public void update_regimen(Connection conn, regimen reg) {
        String titulo = "update_regimen";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, reg.getC2nombre());
            pst.setString(2, reg.getC3sigla());
            pst.setString(3, reg.getC4descripcion());
            pst.setBoolean(4, reg.getC5eliminado());
            pst.setInt(5, reg.getC1idregimen());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + reg.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + reg.toString(), titulo);
        }
    }

    public void cargar_regimen(Connection conn, regimen reg, int id) {
        String titulo = "Cargar_regimen";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                reg.setC1idregimen(rs.getInt(1));
                reg.setC2nombre(rs.getString(2));
                reg.setC3sigla(rs.getString(3));
                reg.setC4descripcion(rs.getString(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + reg.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + reg.toString(), titulo);
        }
    }

    public void actualizar_tabla_regimen(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_regimen(tbltabla);
    }

    public void ancho_tabla_regimen(JTable tbltabla) {
        int Ancho[] = {5, 60,15,20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
