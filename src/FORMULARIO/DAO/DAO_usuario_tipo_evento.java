package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.usuario_tipo_evento;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_usuario_tipo_evento {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "USUARIO_TIPO_EVENTO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "USUARIO_TIPO_EVENTO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO usuario_tipo_evento(idusuario_tipo_evento,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE usuario_tipo_evento SET nombre=? WHERE idusuario_tipo_evento=?;";
    private String sql_select = "SELECT idusuario_tipo_evento,nombre FROM usuario_tipo_evento order by 1 desc;";
    private String sql_cargar = "SELECT idusuario_tipo_evento,nombre FROM usuario_tipo_evento WHERE idusuario_tipo_evento=";

    public void insertar_usuario_tipo_evento(Connection conn, usuario_tipo_evento usute) {
        usute.setC1idusuario_tipo_evento(eveconn.getInt_ultimoID_mas_uno(conn, usute.getTb_usuario_tipo_evento(), usute.getId_idusuario_tipo_evento()));
        String titulo = "insertar_usuario_tipo_evento";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, usute.getC1idusuario_tipo_evento());
            pst.setString(2, usute.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + usute.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + usute.toString(), titulo);
        }
    }

    public void update_usuario_tipo_evento(Connection conn, usuario_tipo_evento usute) {
        String titulo = "update_usuario_tipo_evento";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, usute.getC2nombre());
            pst.setInt(2, usute.getC1idusuario_tipo_evento());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + usute.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + usute.toString(), titulo);
        }
    }

    public void cargar_usuario_tipo_evento(Connection conn, usuario_tipo_evento usute, int id) {
        String titulo = "Cargar_usuario_tipo_evento";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                usute.setC1idusuario_tipo_evento(rs.getInt(1));
                usute.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + usute.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + usute.toString(), titulo);
        }
    }

    public void actualizar_tabla_usuario_tipo_evento(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_usuario_tipo_evento(tbltabla);
    }

    public void ancho_tabla_usuario_tipo_evento(JTable tbltabla) {
        int Ancho[] = {50, 50};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
