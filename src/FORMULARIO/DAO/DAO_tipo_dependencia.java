package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_dependencia;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_dependencia {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_DEPENDENCIA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_DEPENDENCIA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_dependencia(idtipo_dependencia,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE tipo_dependencia SET nombre=? WHERE idtipo_dependencia=?;";
    private String sql_select = "SELECT idtipo_dependencia as idd,nombre FROM tipo_dependencia order by 1 desc;";
    private String sql_cargar = "SELECT idtipo_dependencia,nombre FROM tipo_dependencia WHERE idtipo_dependencia=";

    public void insertar_tipo_dependencia(Connection conn, tipo_dependencia tdep) {
        tdep.setC1idtipo_dependencia(eveconn.getInt_ultimoID_mas_uno(conn, tdep.getTb_tipo_dependencia(), tdep.getId_idtipo_dependencia()));
        String titulo = "insertar_tipo_dependencia";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tdep.getC1idtipo_dependencia());
            pst.setString(2, tdep.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tdep.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tdep.toString(), titulo);
        }
    }

    public void update_tipo_dependencia(Connection conn, tipo_dependencia tdep) {
        String titulo = "update_tipo_dependencia";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, tdep.getC2nombre());
            pst.setInt(2, tdep.getC1idtipo_dependencia());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tdep.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tdep.toString(), titulo);
        }
    }

    public void cargar_tipo_dependencia(Connection conn, tipo_dependencia tdep, int id) {
        String titulo = "Cargar_tipo_dependencia";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                tdep.setC1idtipo_dependencia(rs.getInt(1));
                tdep.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tdep.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tdep.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_dependencia(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_dependencia(tbltabla);
    }

    public void ancho_tabla_tipo_dependencia(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
