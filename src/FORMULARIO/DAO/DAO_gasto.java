package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import CONFIGURACION.EveVarGlobal;
import FORMULARIO.ENTIDAD.gasto;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_gasto {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    EveVarGlobal varglo = new EveVarGlobal();
    private String mensaje_insert = "GASTO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "GASTO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO gasto(idgasto,fecha_creado,creado_por,fecha,descripcion,monto_gasto,estado,fk_idgasto_tipo,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE gasto SET fecha_creado=?,creado_por=?,fecha=?,descripcion=?,monto_gasto=?,estado=?,fk_idgasto_tipo=?,fk_idusuario=? WHERE idgasto=?;";
    private String sql_cargar = "SELECT idgasto,fecha_creado,creado_por,fecha,descripcion,monto_gasto,estado,fk_idgasto_tipo,fk_idusuario FROM gasto WHERE idgasto=";
    private String sql_anular = "UPDATE gasto SET estado=? WHERE idgasto=?;";

    public void insertar_gasto(Connection conn, gasto gas) {
        gas.setC1idgasto(eveconn.getInt_ultimoID_mas_uno(conn, gas.getTb_gasto(), gas.getId_idgasto()));
        String titulo = "insertar_gasto";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, gas.getC1idgasto());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, gas.getC3creado_por());
            pst.setDate(4, evefec.getDateSQL_fecha_cargado_sinformat(gas.getC4fecha()));
            pst.setString(5, gas.getC5descripcion());
            pst.setDouble(6, gas.getC6monto_gasto());
            pst.setString(7, gas.getC7estado());
            pst.setInt(8, gas.getC8fk_idgasto_tipo());
            pst.setInt(9, gas.getC9fk_idusuario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + gas.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + gas.toString(), titulo);
        }
    }

    public void update_gasto(Connection conn, gasto gas) {
        String titulo = "update_gasto";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, gas.getC3creado_por());
            pst.setDate(3, evefec.getDateSQL_sistema());
            pst.setString(4, gas.getC5descripcion());
            pst.setDouble(5, gas.getC6monto_gasto());
            pst.setString(6, gas.getC7estado());
            pst.setInt(7, gas.getC8fk_idgasto_tipo());
            pst.setInt(8, gas.getC9fk_idusuario());
            pst.setInt(9, gas.getC1idgasto());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + gas.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + gas.toString(), titulo);
        }
    }

    public void anular_gasto(Connection conn, gasto gas) {
        String titulo = "anular_gasto";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_anular);
            pst.setString(1, gas.getC7estado());
            pst.setInt(2, gas.getC1idgasto());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_anular + "\n" + gas.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_anular + "\n" + gas.toString(), titulo);
        }
    }

    public void cargar_gasto(Connection conn, gasto gas, int idgasto) {
        String titulo = "Cargar_gasto";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idgasto, titulo);
            if (rs.next()) {
                gas.setC1idgasto(rs.getInt(1));
                gas.setC2fecha_creado(rs.getString(2));
                gas.setC3creado_por(rs.getString(3));
                gas.setC4fecha(rs.getString(4));
                gas.setC5descripcion(rs.getString(5));
                gas.setC6monto_gasto(rs.getDouble(6));
                gas.setC7estado(rs.getString(7));
                gas.setC8fk_idgasto_tipo(rs.getInt(8));
                gas.setC9fk_idusuario(rs.getInt(9));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + gas.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + gas.toString(), titulo);
        }
    }

    public void actualizar_tabla_gasto(Connection conn, JTable tbltabla, String fecha) {
        String sql_select = "SELECT g.idgasto,to_char(g.fecha,'" + evefec.getFormato_fecha() + "') as fecha,gt.nombre,\n"
                + "trim(to_char(g.monto_gasto,'" + varglo.getFormato_numero_3c() + "')) as monto,g.estado,g.monto_gasto \n"
                + "FROM gasto g,gasto_tipo gt \n"
                + "where g.fk_idgasto_tipo=gt.idgasto_tipo " + fecha
                + " order by 1 desc;";
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        evejt.ocultar_columna(tbltabla, 5);
        evejt.alinear_derecha_columna(tbltabla, 3);
        ancho_tabla_gasto(tbltabla);
    }

    public void ancho_tabla_gasto(JTable tbltabla) {
        int Ancho[] = {5, 20, 40, 20, 15, 1};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void imprimir_gasto_filtro(Connection conn, String filtro) {
        String sql_select = "SELECT g.idgasto as idg,\n"
                + "to_char(g.fecha,'" + evefec.getFormato_fecha() + "') as fecha,\n"
                + "gt.nombre as tipo,\n"
                + "g.descripcion as descripcion,\n"
                + "g.estado as estado,\n"
                + "g.monto_gasto as monto \n"
                + "FROM gasto g,gasto_tipo gt \n"
                + "where g.fk_idgasto_tipo=gt.idgasto_tipo \n"
                + "and g.estado='EMITIDO'  \n"+filtro
                + " order by g.idgasto desc;";
        String titulonota = "FILTRO GASTO";
        String direccion = "src/REPORTE/GASTO/repFiltroGasto.jrxml";
        String rutatemp = "Filtr_gasto_" + evefec.getString_formato_fecha();
        rep.imprimir_jasper_o_pdf(conn, sql_select, titulonota, direccion, rutatemp);
    }
}
