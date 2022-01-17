package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.entidad_usuario_evento;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class dao_usuario_evento {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "USUARIO_EVENTO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "USUARIO_EVENTO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO usuario_evento(idusuario_evento,fecha_creado,cod_evento,fk_idusuario_formulario,fk_idusuario_tipo_evento,descripcion,mensaje_error) VALUES (?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE usuario_evento SET fecha_creado=?,cod_evento=?,fk_idusuario_formulario=?,fk_idusuario_tipo_evento=?,descripcion=?,mensaje_error=? WHERE idusuario_evento=?;";
    private String sql_select = "SELECT ue.idusuario_evento as idu,ue.cod_evento,\n"
            + "uf.nombre as formulario,ut.nombre as tipo_evento,ue.descripcion \n"
            + "FROM usuario_evento ue,usuario_formulario uf,usuario_tipo_evento ut\n"
            + "where ue.fk_idusuario_formulario=uf.idusuario_formulario\n"
            + "and ue.fk_idusuario_tipo_evento=ut.idusuario_tipo_evento\n"
            + "order by 1 desc;";
    private String sql_select_en_rol = "SELECT ue.idusuario_evento as idu,ue.cod_evento,\n"
            + "uf.nombre as formulario,ut.nombre as tipo_evento,ue.descripcion as descrip \n"
            + "FROM usuario_evento ue,usuario_formulario uf,usuario_tipo_evento ut\n"
            + "where ue.fk_idusuario_formulario=uf.idusuario_formulario\n"
            + "and ue.fk_idusuario_tipo_evento=ut.idusuario_tipo_evento\n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT idusuario_evento,fecha_creado,cod_evento,fk_idusuario_formulario,fk_idusuario_tipo_evento,descripcion,mensaje_error FROM usuario_evento WHERE idusuario_evento=";
    private String sql_item_rol = "SELECT ue.idusuario_evento as idu,ue.cod_evento,"
            + "uf.nombre as formulario,ut.nombre as tipo_evento,iur.estado,ue.descripcion as descrip \n"
            + "FROM usuario_evento ue,usuario_formulario uf,usuario_tipo_evento ut,item_usuario_rol iur \n"
            + "where iur.fk_idusuario_evento=ue.idusuario_evento \n"
            + "and ue.fk_idusuario_formulario=uf.idusuario_formulario\n"
            + "and ue.fk_idusuario_tipo_evento=ut.idusuario_tipo_evento\n"
            + "and iur.fk_idusuario_rol=";

    public void insertar_usuario_evento(Connection conn, entidad_usuario_evento uven) {
        uven.setC1idusuario_evento(eveconn.getInt_ultimoID_mas_uno(conn, uven.getTb_usuario_evento(), uven.getId_idusuario_evento()));
        String titulo = "insertar_usuario_evento";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, uven.getC1idusuario_evento());
            pst.setDate(2, evefec.getDateSQL_sistema());
            pst.setInt(3, uven.getC3cod_evento());
            pst.setInt(4, uven.getC4fk_idusuario_formulario());
            pst.setInt(5, uven.getC5fk_idusuario_tipo_evento());
            pst.setString(6, uven.getC6descripcion());
            pst.setString(7, uven.getC7mensaje_error());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + uven.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + uven.toString(), titulo);
        }
    }

    public void update_usuario_evento(Connection conn, entidad_usuario_evento uven) {
        String titulo = "update_usuario_evento";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setDate(1, evefec.getDateSQL_sistema());
            pst.setInt(2, uven.getC3cod_evento());
            pst.setInt(3, uven.getC4fk_idusuario_formulario());
            pst.setInt(4, uven.getC5fk_idusuario_tipo_evento());
            pst.setString(5, uven.getC6descripcion());
            pst.setString(6, uven.getC7mensaje_error());
            pst.setInt(7, uven.getC1idusuario_evento());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + uven.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + uven.toString(), titulo);
        }
    }

    public void cargar_usuario_evento(Connection conn, entidad_usuario_evento uven, JTable tabla) {
        String titulo = "Cargar_usuario_evento";
        int id = evejt.getInt_select_id(tabla);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                uven.setC1idusuario_evento(rs.getInt(1));
                uven.setC2fecha_creado(rs.getString(2));
                uven.setC3cod_evento(rs.getInt(3));
                uven.setC4fk_idusuario_formulario(rs.getInt(4));
                uven.setC5fk_idusuario_tipo_evento(rs.getInt(5));
                uven.setC6descripcion(rs.getString(6));
                uven.setC7mensaje_error(rs.getString(7));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + uven.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + uven.toString(), titulo);
        }
    }

    public void cargar_usuario_evento_en_rol(Connection conn, DefaultTableModel model_urol, JTable tbllocal_evento) {
        String titulo = "cargar_usuario_evento_en_rol";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_select_en_rol, titulo);
            while (rs.next()) {
                String idusuario_evento = (rs.getString(1));
                String cod_evento = (rs.getString(2));
                String fk_idusuario_formulario = (rs.getString(3));
                String fk_idusuario_tipo_evento = (rs.getString(4));
                boolean estado = false;
                String descrip = (rs.getString(5));
                Object dato[] = {idusuario_evento, cod_evento, fk_idusuario_formulario, fk_idusuario_tipo_evento,descrip, estado};
                model_urol = (DefaultTableModel) tbllocal_evento.getModel();
                model_urol.addRow(dato);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_select_en_rol, titulo);
        }
    }

    public void cargar_usuario_evento_en_rol_seleccionar(Connection conn, DefaultTableModel model_urol, JTable tbllocal_evento, int fk_idusuario_rol) {
        String titulo = "cargar_usuario_evento_en_rol_seleccionar";

        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_item_rol + fk_idusuario_rol + " order by 1 desc;", titulo);
            while (rs.next()) {
                String idusuario_evento = (rs.getString(1));
                String cod_evento = (rs.getString(2));
                String fk_idusuario_formulario = (rs.getString(3));
                String fk_idusuario_tipo_evento = (rs.getString(4));
                boolean estado = (rs.getBoolean(5));
                String descrip = (rs.getString(6));
                Object dato[] = {idusuario_evento, cod_evento, fk_idusuario_formulario, fk_idusuario_tipo_evento,descrip, estado};
                model_urol = (DefaultTableModel) tbllocal_evento.getModel();
                model_urol.addRow(dato);
            }

        } catch (Exception e) {
            evemen.mensaje_error(e, sql_select_en_rol, titulo);
        }
    }

    public void actualizar_tabla_usuario_evento(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_usuario_evento(tbltabla);
    }

    public void ancho_tabla_usuario_evento(JTable tbltabla) {
        int Ancho[] = {10, 10, 20, 30,20, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void ancho_tabla_usuario_evento_en_rol(JTable tbllocal_evento) {
        int Ancho[] = {10, 10, 20, 30,20, 10};
        evejt.setAnchoColumnaJtable(tbllocal_evento, Ancho);
    }
}
