package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.entidad_usuario_rol;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class dao_usuario_rol {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "USUARIO_ROL GUARDADO CORRECTAMENTE";
    private String mensaje_update = "USUARIO_ROL MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO usuario_rol(idusuario_rol,fecha_creacion,nombre,descripcion) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE usuario_rol SET fecha_creacion=?,nombre=?,descripcion=? WHERE idusuario_rol=?;";
    private String sql_select = "SELECT idusuario_rol,fecha_creacion,nombre,descripcion FROM usuario_rol order by 1 desc;";
    private String sql_cargar = "SELECT idusuario_rol,fecha_creacion,nombre,descripcion FROM usuario_rol WHERE idusuario_rol=";

    public void insertar_usuario_rol(Connection conn, entidad_usuario_rol urol) {
        urol.setC1idusuario_rol(eveconn.getInt_ultimoID_mas_uno(conn, urol.getTb_usuario_rol(), urol.getId_idusuario_rol()));
        String titulo = "insertar_usuario_rol";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, urol.getC1idusuario_rol());
            pst.setDate(2, evefec.getDateSQL_sistema());
            pst.setString(3, urol.getC3nombre());
            pst.setString(4, urol.getC4descripcion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + urol.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + urol.toString(), titulo);
        }
    }

    public void update_usuario_rol(Connection conn, entidad_usuario_rol urol) {
        String titulo = "update_usuario_rol";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setDate(1, evefec.getDateSQL_sistema());
            pst.setString(2, urol.getC3nombre());
            pst.setString(3, urol.getC4descripcion());
            pst.setInt(4, urol.getC1idusuario_rol());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + urol.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + urol.toString(), titulo);
        }
    }

    public void cargar_usuario_rol(Connection conn, entidad_usuario_rol urol, JTable tabla) {
        String titulo = "Cargar_usuario_rol";
        int id = evejt.getInt_select_id(tabla);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                urol.setC1idusuario_rol(rs.getInt(1));
                urol.setC2fecha_creacion(rs.getString(2));
                urol.setC3nombre(rs.getString(3));
                urol.setC4descripcion(rs.getString(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + urol.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + urol.toString(), titulo);
        }
    }

    public void actualizar_tabla_usuario_rol(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_usuario_rol(tbltabla);
    }

    public void ancho_tabla_usuario_rol(JTable tbltabla) {
        int Ancho[] = {10, 20, 30, 40};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
