package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tipo_tasa_cambio;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tipo_tasa_cambio {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TIPO_TASA_CAMBIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TIPO_TASA_CAMBIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tipo_tasa_cambio(idtipo_tasa_cambio,fecha_creacion,creado_por,dolar_gua_aduana,dolar_gua_mercado,real_gua_aduana,real_gua_mercado) VALUES (?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE tipo_tasa_cambio SET fecha_creacion=?,creado_por=?,dolar_gua_aduana=?,dolar_gua_mercado=?,real_gua_aduana=?,real_gua_mercado=? WHERE idtipo_tasa_cambio=?;";
    private String sql_select = "SELECT idtipo_tasa_cambio as idttc,to_char(fecha_creacion,'dd-MM-yyyy') as crecion,"
            + "creado_por,dolar_gua_aduana as dolar_aduana,dolar_gua_mercado as dolar_mercado,real_gua_aduana as real_aduana,real_gua_mercado as real_mercado "
            + "FROM tipo_tasa_cambio order by 1 desc;";
    private String sql_cargar = "SELECT idtipo_tasa_cambio,fecha_creacion,creado_por,dolar_gua_aduana,dolar_gua_mercado,real_gua_aduana,real_gua_mercado FROM tipo_tasa_cambio WHERE idtipo_tasa_cambio=";

    public void insertar_tipo_tasa_cambio(Connection conn, tipo_tasa_cambio ttcam) {
        ttcam.setC1idtipo_tasa_cambio(eveconn.getInt_ultimoID_mas_uno(conn, ttcam.getTb_tipo_tasa_cambio(), ttcam.getId_idtipo_tasa_cambio()));
        String titulo = "insertar_tipo_tasa_cambio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ttcam.getC1idtipo_tasa_cambio());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, ttcam.getC3creado_por());
            pst.setDouble(4, ttcam.getC4dolar_gua_aduana());
            pst.setDouble(5, ttcam.getC5dolar_gua_mercado());
            pst.setDouble(6, ttcam.getC6real_gua_aduana());
            pst.setDouble(7, ttcam.getC7real_gua_mercado());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ttcam.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ttcam.toString(), titulo);
        }
    }

    public void update_tipo_tasa_cambio(Connection conn, tipo_tasa_cambio ttcam) {
        String titulo = "update_tipo_tasa_cambio";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, ttcam.getC3creado_por());
            pst.setDouble(3, ttcam.getC4dolar_gua_aduana());
            pst.setDouble(4, ttcam.getC5dolar_gua_mercado());
            pst.setDouble(5, ttcam.getC6real_gua_aduana());
            pst.setDouble(6, ttcam.getC7real_gua_mercado());
            pst.setInt(7, ttcam.getC1idtipo_tasa_cambio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ttcam.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ttcam.toString(), titulo);
        }
    }

    public void cargar_tipo_tasa_cambio(Connection conn, tipo_tasa_cambio ttcam, int id) {
        String titulo = "Cargar_tipo_tasa_cambio";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ttcam.setC1idtipo_tasa_cambio(rs.getInt(1));
                ttcam.setC2fecha_creacion(rs.getString(2));
                ttcam.setC3creado_por(rs.getString(3));
                ttcam.setC4dolar_gua_aduana(rs.getDouble(4));
                ttcam.setC5dolar_gua_mercado(rs.getDouble(5));
                ttcam.setC6real_gua_aduana(rs.getDouble(6));
                ttcam.setC7real_gua_mercado(rs.getDouble(7));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ttcam.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ttcam.toString(), titulo);
        }
    }

    public void actualizar_tabla_tipo_tasa_cambio(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tipo_tasa_cambio(tbltabla);
    }

    public void ancho_tabla_tipo_tasa_cambio(JTable tbltabla) {
        int Ancho[] = {10, 15, 15, 15, 15, 15, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
