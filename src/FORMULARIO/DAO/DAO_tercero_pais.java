package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tercero_pais;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tercero_pais {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TERCERO_PAIS GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TERCERO_PAIS MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tercero_pais(idtercero_pais,nombre,sigla) VALUES (?,?,?);";
    private String sql_update = "UPDATE tercero_pais SET nombre=?,sigla=? WHERE idtercero_pais=?;";
    private String sql_select = "SELECT idtercero_pais,nombre,sigla FROM tercero_pais order by 1 desc;";
    private String sql_cargar = "SELECT idtercero_pais,nombre,sigla FROM tercero_pais WHERE idtercero_pais=";

    public void insertar_tercero_pais(Connection conn, tercero_pais tepa) {
        tepa.setC1idtercero_pais(eveconn.getInt_ultimoID_mas_uno(conn, tepa.getTb_tercero_pais(), tepa.getId_idtercero_pais()));
        String titulo = "insertar_tercero_pais";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tepa.getC1idtercero_pais());
            pst.setString(2, tepa.getC2nombre());
            pst.setString(3, tepa.getC3sigla());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tepa.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tepa.toString(), titulo);
        }
    }

    public void update_tercero_pais(Connection conn, tercero_pais tepa) {
        String titulo = "update_tercero_pais";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, tepa.getC2nombre());
            pst.setString(2, tepa.getC3sigla());
            pst.setInt(3, tepa.getC1idtercero_pais());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tepa.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tepa.toString(), titulo);
        }
    }

    public void cargar_tercero_pais(Connection conn, tercero_pais tepa, int id) {
        String titulo = "Cargar_tercero_pais";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                tepa.setC1idtercero_pais(rs.getInt(1));
                tepa.setC2nombre(rs.getString(2));
                tepa.setC3sigla(rs.getString(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tepa.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tepa.toString(), titulo);
        }
    }

    public void actualizar_tabla_tercero_pais(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tercero_pais(tbltabla);
    }

    public void ancho_tabla_tercero_pais(JTable tbltabla) {
        int Ancho[] = {33, 33, 33};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
