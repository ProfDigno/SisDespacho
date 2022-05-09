package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.transporte_empresa;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_transporte_empresa {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TRANSPORTE_EMPRESA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TRANSPORTE_EMPRESA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO transporte_empresa(idtransporte_empresa,nombre,sigla,direccion,telefono) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE transporte_empresa SET nombre=?,sigla=?,direccion=?,telefono=? WHERE idtransporte_empresa=?;";
    private String sql_select = "SELECT idtransporte_empresa,nombre,sigla,direccion,telefono FROM transporte_empresa order by 1 desc;";
    private String sql_cargar = "SELECT idtransporte_empresa,nombre,sigla,direccion,telefono FROM transporte_empresa WHERE idtransporte_empresa=";

    public void insertar_transporte_empresa(Connection conn, transporte_empresa trem) {
        trem.setC1idtransporte_empresa(eveconn.getInt_ultimoID_mas_uno(conn, trem.getTb_transporte_empresa(), trem.getId_idtransporte_empresa()));
        String titulo = "insertar_transporte_empresa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, trem.getC1idtransporte_empresa());
            pst.setString(2, trem.getC2nombre());
            pst.setString(3, trem.getC3sigla());
            pst.setString(4, trem.getC4direccion());
            pst.setString(5, trem.getC5telefono());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + trem.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + trem.toString(), titulo);
        }
    }

    public void update_transporte_empresa(Connection conn, transporte_empresa trem) {
        String titulo = "update_transporte_empresa";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, trem.getC2nombre());
            pst.setString(2, trem.getC3sigla());
            pst.setString(3, trem.getC4direccion());
            pst.setString(4, trem.getC5telefono());
            pst.setInt(5, trem.getC1idtransporte_empresa());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + trem.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + trem.toString(), titulo);
        }
    }

    public void cargar_transporte_empresa(Connection conn, transporte_empresa trem, int id) {
        String titulo = "Cargar_transporte_empresa";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                trem.setC1idtransporte_empresa(rs.getInt(1));
                trem.setC2nombre(rs.getString(2));
                trem.setC3sigla(rs.getString(3));
                trem.setC4direccion(rs.getString(4));
                trem.setC5telefono(rs.getString(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + trem.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + trem.toString(), titulo);
        }
    }

    public void actualizar_tabla_transporte_empresa(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_transporte_empresa(tbltabla);
    }

    public void ancho_tabla_transporte_empresa(JTable tbltabla) {
        int Ancho[] = {5, 50, 30,8,7};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
