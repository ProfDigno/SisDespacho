package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_institucion;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_institucion {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_INSTITUCION GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_INSTITUCION MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_institucion(idtipo_institucion,nombre) VALUES (?,?);";
    private String sql_update = "UPDATE tipo_institucion SET nombre=? WHERE idtipo_institucion=?;";
    private String sql_select = "SELECT idtipo_institucion as idi,nombre FROM tipo_institucion order by 1 desc;";
    private String sql_cargar = "SELECT idtipo_institucion,nombre FROM tipo_institucion WHERE idtipo_institucion=";

    public void insertar_tipo_institucion(Connection conn, tipo_institucion tins) {
        tins.setC1idtipo_institucion(eveconn.getInt_ultimoID_mas_uno(conn, tins.getTb_tipo_institucion(), tins.getId_idtipo_institucion()));
        String titulo = "insertar_tipo_institucion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, tins.getC1idtipo_institucion());
            pst.setString(2, tins.getC2nombre());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + tins.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + tins.toString(), titulo);
        }
    }

    public void update_tipo_institucion(Connection conn, tipo_institucion tins) {
        String titulo = "update_tipo_institucion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, tins.getC2nombre());
            pst.setInt(2, tins.getC1idtipo_institucion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + tins.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + tins.toString(), titulo);
        }
    }

    public void cargar_tipo_institucion(Connection conn, tipo_institucion tins, int id) {
        String titulo = "Cargar_tipo_institucion";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                tins.setC1idtipo_institucion(rs.getInt(1));
                tins.setC2nombre(rs.getString(2));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + tins.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + tins.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_institucion(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_institucion(tbltabla);
    }

    public void ancho_tabla_tipo_institucion(JTable tbltabla) {
        int Ancho[] = {20, 80};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
