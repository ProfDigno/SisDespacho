package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.honorario_despacho;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_honorario_despacho {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "HONORARIO_DESPACHO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "HONORARIO_DESPACHO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO honorario_despacho(idhonorario_despacho,fecha_creado,monto) VALUES (?,?,?);";
    private String sql_update = "UPDATE honorario_despacho SET fecha_creado=?,monto=? WHERE idhonorario_despacho=?;";
    private String sql_select = "SELECT idhonorario_despacho,fecha_creado,monto FROM honorario_despacho order by 1 desc;";
    private String sql_cargar = "SELECT idhonorario_despacho,fecha_creado,monto FROM honorario_despacho WHERE idhonorario_despacho=";

    public void insertar_honorario_despacho(Connection conn, honorario_despacho hdes) {
        hdes.setC1idhonorario_despacho(eveconn.getInt_ultimoID_mas_uno(conn, hdes.getTb_honorario_despacho(), hdes.getId_idhonorario_despacho()));
        String titulo = "insertar_honorario_despacho";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, hdes.getC1idhonorario_despacho());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setDouble(3, hdes.getC3monto());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + hdes.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + hdes.toString(), titulo);
        }
    }

    public void update_honorario_despacho(Connection conn, honorario_despacho hdes) {
        String titulo = "update_honorario_despacho";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setDouble(2, hdes.getC3monto());
            pst.setInt(3, hdes.getC1idhonorario_despacho());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + hdes.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + hdes.toString(), titulo);
        }
    }

    public void cargar_honorario_despacho(Connection conn, honorario_despacho hdes, int id) {
        String titulo = "Cargar_honorario_despacho";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                hdes.setC1idhonorario_despacho(rs.getInt(1));
                hdes.setC2fecha_creado(rs.getString(2));
                hdes.setC3monto(rs.getDouble(3));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + hdes.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + hdes.toString(), titulo);
        }
    }

    public void actualizar_tabla_honorario_despacho(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_honorario_despacho(tbltabla);
    }

    public void ancho_tabla_honorario_despacho(JTable tbltabla) {
        int Ancho[] = {10,40,50};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
