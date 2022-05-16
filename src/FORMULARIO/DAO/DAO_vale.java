package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import CONFIGURACION.EveVarGlobal;
import FORMULARIO.ENTIDAD.vale;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_vale {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    EveVarGlobal varglo=new EveVarGlobal();
    private String mensaje_insert = "VALE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "VALE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO vale(idvale,fecha_creado,creado_por,descripcion,monto_vale,monto_letra,estado,fk_idfuncionario,fk_idusuario) VALUES (?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE vale SET fecha_creado=?,creado_por=?,descripcion=?,monto_vale=?,monto_letra=?,estado=?,fk_idfuncionario=?,fk_idusuario=? WHERE idvale=?;";

    private String sql_cargar = "SELECT idvale,fecha_creado,creado_por,descripcion,monto_vale,monto_letra,estado,fk_idfuncionario,fk_idusuario FROM vale WHERE idvale=";
    private String sql_anular = "UPDATE vale SET estado=? WHERE idvale=?;";

    public void insertar_vale(Connection conn, vale val) {
        val.setC1idvale(eveconn.getInt_ultimoID_mas_uno(conn, val.getTb_vale(), val.getId_idvale()));
        String titulo = "insertar_vale";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, val.getC1idvale());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, val.getC3creado_por());
            pst.setString(4, val.getC4descripcion());
            pst.setDouble(5, val.getC5monto_vale());
            pst.setString(6, val.getC6monto_letra());
            pst.setString(7, val.getC7estado());
            pst.setInt(8, val.getC8fk_idfuncionario());
            pst.setInt(9, val.getC9fk_idusuario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + val.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + val.toString(), titulo);
        }
    }

    public void update_vale(Connection conn, vale val) {
        String titulo = "update_vale";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, val.getC3creado_por());
            pst.setString(3, val.getC4descripcion());
            pst.setDouble(4, val.getC5monto_vale());
            pst.setString(5, val.getC6monto_letra());
            pst.setString(6, val.getC7estado());
            pst.setInt(7, val.getC8fk_idfuncionario());
            pst.setInt(8, val.getC9fk_idusuario());
            pst.setInt(9, val.getC1idvale());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + val.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + val.toString(), titulo);
        }
    }

    public void anular_vale(Connection conn, vale val) {
        String titulo = "anular_vale";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_anular);
            pst.setString(1, val.getC7estado());
            pst.setInt(2, val.getC1idvale());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_anular + "\n" + val.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_anular + "\n" + val.toString(), titulo);
        }
    }

    public void cargar_vale(Connection conn, vale val, int idvale) {
        String titulo = "Cargar_vale";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idvale, titulo);
            if (rs.next()) {
                val.setC1idvale(rs.getInt(1));
                val.setC2fecha_creado(rs.getString(2));
                val.setC3creado_por(rs.getString(3));
                val.setC4descripcion(rs.getString(4));
                val.setC5monto_vale(rs.getDouble(5));
                val.setC6monto_letra(rs.getString(6));
                val.setC7estado(rs.getString(7));
                val.setC8fk_idfuncionario(rs.getInt(8));
                val.setC9fk_idusuario(rs.getInt(9));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + val.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + val.toString(), titulo);
        }
    }

    public void actualizar_tabla_vale(Connection conn, JTable tbltabla, String filtro) {
        String sql_select = "SELECT v.idvale,to_char(v.fecha_creado,'"+evefec.getFormato_fecha()+"') as fecha,f.nombre as funcionario,v.descripcion,\n"
                + "to_char(v.monto_vale,'"+varglo.getFormato_numero_3c()+"') as monto,v.estado,v.monto_vale \n"
                + "FROM vale v,funcionario f \n"
                + "where v.fk_idfuncionario=f.idfuncionario \n" + filtro
                + "order by 1 desc;";
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        evejt.ocultar_columna(tbltabla, 6);
        evejt.alinear_derecha_columna(tbltabla, 4);
        ancho_tabla_vale(tbltabla);
    }

    public void ancho_tabla_vale(JTable tbltabla) {
        int Ancho[] = {5, 15, 25, 24, 15, 15, 1};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void imprimir_rep_vale(Connection conn, int id) {
        String sql = "select v.idvale as idv,\n"
                + "f.nombre as funcionario,f.cedula as cedula,\n"
                + "to_char(v.fecha_creado,'"+evefec.getFormato_fechaHora_psql()+"') as fecha_creado,\n"
                + "v.descripcion as concepto,\n"
                + "v.monto_vale as monto,v.monto_letra as letra \n"
                + "from vale v,funcionario f\n"
                + "where v.fk_idfuncionario=f.idfuncionario\n"
                + "and v.idvale=" + id;
        String titulonota = "VALE";
        String direccion = "src/REPORTE/VALE/repValeNT.jrxml";
        String rutatemp = "Vale_" + evefec.getString_formato_fecha() + "_" + id;
        rep.imprimir_jasper_o_pdf(conn, sql, titulonota, direccion, rutatemp);
    }

    public void imprimir_rep_vale_por_fecha(Connection conn,int fk_idfuncionario,String filtro) {
        String sql = "select v.idvale as idv,to_char(v.fecha_creado,'"+evefec.getFormato_fechaHora_psql()+"') fecha,\n"
                + "f.nombre as funcionario,f.cedula as cedula,  v.descripcion as descrip_vale,v.monto_vale as monto \n"
                + "from vale v,funcionario f \n"
                + "where v.fk_idfuncionario=f.idfuncionario \n"
                + "and v.estado='"+varglo.getEst_Emitido()+"'\n"
                + "and v.fk_idfuncionario="+fk_idfuncionario+filtro
                + " \n order by 1 desc;";
        String titulonota = "VALE POR FECHA";
        String direccion = "src/REPORTE/VALE/repValePorFecha.jrxml";
        String rutatemp = "ValePorFecha_" + evefec.getString_formato_fecha() + "_" + fk_idfuncionario;
        rep.imprimir_jasper_o_pdf(conn, sql, titulonota, direccion, rutatemp);
    }
}
