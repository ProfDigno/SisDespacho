package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.aduana;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_aduana {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ADUANA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ADUANA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO aduana(idaduana,nombre,telefono,direccion,sigla,eliminado) VALUES (?,?,?,?,?,?);";
    private String sql_update = "UPDATE aduana SET nombre=?,telefono=?,direccion=?,sigla=?,eliminado=? WHERE idaduana=?;";
    private String sql_select = "SELECT idaduana as id,nombre,direccion,sigla FROM aduana where eliminado=false order by 1 desc;";
    private String sql_cargar = "SELECT idaduana,nombre,telefono,direccion,sigla FROM aduana WHERE idaduana=";

    public void insertar_aduana(Connection conn, aduana adu) {
        adu.setC1idaduana(eveconn.getInt_ultimoID_mas_uno(conn, adu.getTb_aduana(), adu.getId_idaduana()));
        adu.setC6eliminado(false);
        String titulo = "insertar_aduana";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, adu.getC1idaduana());
            pst.setString(2, adu.getC2nombre());
            pst.setString(3, adu.getC3telefono());
            pst.setString(4, adu.getC4direccion());
            pst.setString(5, adu.getC5sigla());
            pst.setBoolean(6, adu.getC6eliminado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + adu.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + adu.toString(), titulo);
        }
    }

    public void update_aduana(Connection conn, aduana adu) {
        String titulo = "update_aduana";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, adu.getC2nombre());
            pst.setString(2, adu.getC3telefono());
            pst.setString(3, adu.getC4direccion());
            pst.setString(4, adu.getC5sigla());
            pst.setBoolean(5, adu.getC6eliminado());
            pst.setInt(6, adu.getC1idaduana());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + adu.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + adu.toString(), titulo);
        }
    }

    public void cargar_aduana(Connection conn, aduana adu, int id) {
        String titulo = "Cargar_aduana";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                adu.setC1idaduana(rs.getInt(1));
                adu.setC2nombre(rs.getString(2));
                adu.setC3telefono(rs.getString(3));
                adu.setC4direccion(rs.getString(4));
                adu.setC5sigla(rs.getString(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + adu.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + adu.toString(), titulo);
        }
    }

    public void actualizar_tabla_aduana(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_aduana(tbltabla);
    }

    public void ancho_tabla_aduana(JTable tbltabla) {
        int Ancho[] = {10,40,35, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
