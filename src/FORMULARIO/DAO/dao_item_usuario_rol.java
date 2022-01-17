package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.entidad_item_usuario_rol;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.ENTIDAD.entidad_usuario_rol;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class dao_item_usuario_rol {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_USUARIO_ROL GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_USUARIO_ROL MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_usuario_rol(iditem_usuario_rol,estado,fk_idusuario_rol,fk_idusuario_evento) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE item_usuario_rol SET estado=?,fk_idusuario_rol=?,fk_idusuario_evento=? WHERE iditem_usuario_rol=?;";
    private String sql_select = "SELECT iditem_usuario_rol,estado,fk_idusuario_rol,fk_idusuario_evento FROM item_usuario_rol order by 1 desc;";
    private String sql_cargar = "SELECT iditem_usuario_rol,estado,fk_idusuario_rol,fk_idusuario_evento FROM item_usuario_rol WHERE iditem_usuario_rol=";
    private String sql_delete = "delete from item_usuario_rol  WHERE fk_idusuario_rol=?;";

    public void insertar_item_usuario_rol_tabla(Connection conn,  entidad_usuario_rol urol, JTable tbllocal_evento) {
        for (int row = 0; row < tbllocal_evento.getRowCount(); row++) {
            entidad_item_usuario_rol iurol = new entidad_item_usuario_rol();
            String C4fk_idusuario_evento = ((tbllocal_evento.getModel().getValueAt(row, 0).toString()));
            String C2estado = ((tbllocal_evento.getModel().getValueAt(row, 5).toString()));
            int fk_idusuario_evento=Integer.parseInt(C4fk_idusuario_evento);
            if(C2estado.equals("true")){
                iurol.setC2estado(true);
            }else{
                iurol.setC2estado(false);
            }
            iurol.setC3fk_idusuario_rol(urol.getC1idusuario_rol());
            iurol.setC4fk_idusuario_evento(fk_idusuario_evento);
            insertar_item_usuario_rol(conn, iurol);
        }
    }
    public void insertar_item_usuario_rol(Connection conn, entidad_item_usuario_rol iurol) {
        iurol.setC1iditem_usuario_rol(eveconn.getInt_ultimoID_mas_uno(conn, iurol.getTb_item_usuario_rol(), iurol.getId_iditem_usuario_rol()));
        String titulo = "insertar_item_usuario_rol";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, iurol.getC1iditem_usuario_rol());
            pst.setBoolean(2, iurol.getC2estado());
            pst.setInt(3, iurol.getC3fk_idusuario_rol());
            pst.setInt(4, iurol.getC4fk_idusuario_evento());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + iurol.toString(), titulo);
//            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + iurol.toString(), titulo);
        }
    }
    public void eliminar_item_usuario_rol(Connection conn, entidad_usuario_rol iurol) {
        String titulo = "eliminar_item_usuario_rol";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_delete);
            pst.setInt(1, iurol.getC1idusuario_rol());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_delete + "\n" + iurol.toString(), titulo);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_delete + "\n" + iurol.toString(), titulo);
        }
    }
    public void update_item_usuario_rol(Connection conn, entidad_item_usuario_rol iurol) {
        String titulo = "update_item_usuario_rol";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setBoolean(1, iurol.getC2estado());
            pst.setInt(2, iurol.getC3fk_idusuario_rol());
            pst.setInt(3, iurol.getC4fk_idusuario_evento());
            pst.setInt(4, iurol.getC1iditem_usuario_rol());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + iurol.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + iurol.toString(), titulo);
        }
    }

    public void cargar_item_usuario_rol(Connection conn, entidad_item_usuario_rol iurol, JTable tabla) {
        String titulo = "Cargar_item_usuario_rol";
        int id = evejt.getInt_select_id(tabla);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                iurol.setC1iditem_usuario_rol(rs.getInt(1));
                iurol.setC2estado(rs.getBoolean(2));
                iurol.setC3fk_idusuario_rol(rs.getInt(3));
                iurol.setC4fk_idusuario_evento(rs.getInt(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + iurol.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + iurol.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_usuario_rol(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_usuario_rol(tbltabla);
    }

    public void ancho_tabla_item_usuario_rol(JTable tbltabla) {
        int Ancho[] = {25, 25, 25, 25};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
