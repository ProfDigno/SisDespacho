package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tercero_ciudad;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tercero_ciudad {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TERCERO_CIUDAD GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TERCERO_CIUDAD MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tercero_ciudad(idtercero_ciudad,nombre,sigla,eliminado) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE tercero_ciudad SET nombre=?,sigla=? WHERE idtercero_ciudad=?;";
    private String sql_select = "SELECT idtercero_ciudad as idc,nombre,sigla FROM tercero_ciudad where eliminado=false order by 1 desc;";
    private String sql_cargar = "SELECT idtercero_ciudad,nombre,sigla FROM tercero_ciudad WHERE idtercero_ciudad=";
    private String sql_update_eliminar = "UPDATE tercero_ciudad SET eliminado=true WHERE idtercero_ciudad=?;"; 
    public void insertar_tercero_ciudad(Connection conn, tercero_ciudad teciu) {
        teciu.setC1idtercero_ciudad(eveconn.getInt_ultimoID_mas_uno(conn, teciu.getTb_tercero_ciudad(), teciu.getId_idtercero_ciudad()));
        String titulo = "insertar_tercero_ciudad";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, teciu.getC1idtercero_ciudad());
            pst.setString(2, teciu.getC2nombre());
            pst.setString(3, teciu.getC3sigla());
            pst.setBoolean(4, false);//eliminar
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + teciu.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + teciu.toString(), titulo);
        }
    }

    public void update_tercero_ciudad(Connection conn, tercero_ciudad teciu) {
        String titulo = "update_tercero_ciudad";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, teciu.getC2nombre());
            pst.setString(2, teciu.getC3sigla());
            pst.setInt(3, teciu.getC1idtercero_ciudad());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + teciu.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + teciu.toString(), titulo);
        }
    }

    public void cargar_tercero_ciudad(Connection conn, tercero_ciudad teciu, int id) {
        String titulo = "Cargar_tercero_ciudad";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                teciu.setC1idtercero_ciudad(rs.getInt(1));
                teciu.setC2nombre(rs.getString(2));
                teciu.setC3sigla(rs.getString(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + teciu.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + teciu.toString(), titulo);
        }
    }
public void update_tercero_ciudad_eliminar(Connection conn, tercero_ciudad teciu) {
        String titulo = "update_tercero_ciudad";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_eliminar);
            pst.setInt(1, teciu.getC1idtercero_ciudad());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_eliminar + "\n" + teciu.toString(), titulo);
//            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_eliminar + "\n" + teciu.toString(), titulo);
        }
    }
    public void actualizar_tabla_tercero_ciudad(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tercero_ciudad(tbltabla);
    }

    public void ancho_tabla_tercero_ciudad(JTable tbltabla) {
        int Ancho[] = {10,70,20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
       // tbltabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
}
