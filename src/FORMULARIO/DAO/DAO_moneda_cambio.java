package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.moneda_cambio;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_moneda_cambio {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "MONEDA_CAMBIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "MONEDA_CAMBIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO moneda_cambio(idmoneda_cambio,moneda,"
            + "guarani_unidad_aduana,guarani_unidad_mercado,sigla,eliminado) VALUES (?,?,?,?,?,?);";
    private String sql_update = "UPDATE moneda_cambio SET moneda=?,guarani_unidad_aduana=?,"
            + "guarani_unidad_mercado=?,sigla=?,eliminado=? WHERE idmoneda_cambio=?;";
    private String sql_select = "SELECT idmoneda_cambio as idmc,moneda,"
            + "to_char(guarani_unidad_aduana,'999G999D99') as aduana,"
            + "to_char(guarani_unidad_mercado,'999G999D99') as mercado,"
            + "sigla "
            + "FROM moneda_cambio "
            + "where eliminado=false order by 1 desc;";
    private String sql_cargar = "SELECT idmoneda_cambio,moneda,guarani_unidad_aduana,"
            + "guarani_unidad_mercado,sigla FROM moneda_cambio WHERE idmoneda_cambio=";
    
    public void insertar_moneda_cambio(Connection conn, moneda_cambio mcam) {
        mcam.setC1idmoneda_cambio(eveconn.getInt_ultimoID_mas_uno(conn, mcam.getTb_moneda_cambio(), mcam.getId_idmoneda_cambio()));
        String titulo = "insertar_moneda_cambio";
        mcam.setC6eliminado(false);
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, mcam.getC1idmoneda_cambio());
            pst.setString(2, mcam.getC2moneda());
            pst.setDouble(3, mcam.getC3guarani_unidad_aduana());
            pst.setDouble(4, mcam.getC4guarani_unidad_mercado());
            pst.setString(5, mcam.getC5sigla());
            pst.setBoolean(6, mcam.getC6eliminado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + mcam.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + mcam.toString(), titulo);
        }
    }

    public void update_moneda_cambio(Connection conn, moneda_cambio mcam) {
        String titulo = "update_moneda_cambio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, mcam.getC2moneda());
            pst.setDouble(2, mcam.getC3guarani_unidad_aduana());
            pst.setDouble(3, mcam.getC4guarani_unidad_mercado());
            pst.setString(4, mcam.getC5sigla());
            pst.setBoolean(5,mcam.getC6eliminado());
            pst.setInt(6, mcam.getC1idmoneda_cambio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + mcam.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + mcam.toString(), titulo);
        }
    }

    public void cargar_moneda_cambio(Connection conn, moneda_cambio mcam, int id) {
        String titulo = "Cargar_moneda_cambio";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                mcam.setC1idmoneda_cambio(rs.getInt(1));
                mcam.setC2moneda(rs.getString(2));
                mcam.setC3guarani_unidad_aduana(rs.getDouble(3));
                mcam.setC4guarani_unidad_mercado(rs.getDouble(4));
                mcam.setC5sigla(rs.getString(5));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + mcam.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + mcam.toString(), titulo);
        }
    }

    public void actualizar_tabla_moneda_cambio(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        evejt.alinear_derecha_columna(tbltabla, 2);
        evejt.alinear_derecha_columna(tbltabla, 3);
        ancho_tabla_moneda_cambio(tbltabla);
    }

    public void ancho_tabla_moneda_cambio(JTable tbltabla) {
        int Ancho[] = {10,40,20, 20,10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
