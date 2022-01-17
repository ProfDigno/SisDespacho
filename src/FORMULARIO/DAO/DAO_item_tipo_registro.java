package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_tipo_registro;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_tipo_registro {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_TIPO_REGISTRO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_TIPO_REGISTRO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_tipo_registro(iditem_tipo_registro,fecha_creacion,creado_por,estado,fecha_estado,nro_habilitacion,fecha_habilitacion,fecha_vencimiento,imagen,fk_idtipo_registro,fk_idtipo_dependencia,fk_idtipo_institucion,fk_idtercero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_tipo_registro SET fecha_creacion=?,creado_por=?,estado=?,fecha_estado=?,nro_habilitacion=?,fecha_habilitacion=?,fecha_vencimiento=?,imagen=?,fk_idtipo_registro=?,fk_idtipo_dependencia=?,fk_idtipo_institucion=?,fk_idtercero=? WHERE iditem_tipo_registro=?;";
    private String sql_select = "SELECT iditem_tipo_registro,fecha_creacion,creado_por,estado,fecha_estado,nro_habilitacion,fecha_habilitacion,fecha_vencimiento,imagen,fk_idtipo_registro,fk_idtipo_dependencia,fk_idtipo_institucion,fk_idtercero FROM item_tipo_registro order by 1 desc;";
    private String sql_cargar = "SELECT iditem_tipo_registro,fecha_creacion,creado_por,estado,fecha_estado,nro_habilitacion,fecha_habilitacion,fecha_vencimiento,imagen,fk_idtipo_registro,fk_idtipo_dependencia,fk_idtipo_institucion,fk_idtercero FROM item_tipo_registro WHERE iditem_tipo_registro=";

    public void insertar_item_tipo_registro(Connection conn, item_tipo_registro itreg) {
        itreg.setC1iditem_tipo_registro(eveconn.getInt_ultimoID_mas_uno(conn, itreg.getTb_item_tipo_registro(), itreg.getId_iditem_tipo_registro()));
        String titulo = "insertar_item_tipo_registro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, itreg.getC1iditem_tipo_registro());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, itreg.getC3creado_por());
            pst.setString(4, itreg.getC4estado());
            pst.setString(6, itreg.getC6nro_habilitacion());
            pst.setDate(5, evefec.getDateSQL_fecha_cargado_sinformat(itreg.getC5fecha_estado()));
            pst.setDate(7, evefec.getDateSQL_fecha_cargado_sinformat(itreg.getC7fecha_habilitacion()));
            pst.setDate(8, evefec.getDateSQL_fecha_cargado_sinformat(itreg.getC8fecha_vencimiento()));
            pst.setString(9, itreg.getC9imagen());
            pst.setInt(10, itreg.getC10fk_idtipo_registro());
            pst.setInt(11, itreg.getC11fk_idtipo_dependencia());
            pst.setInt(12, itreg.getC12fk_idtipo_institucion());
            pst.setInt(13, itreg.getC13fk_idtercero());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + itreg.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + itreg.toString(), titulo);
        }
    }

    public void update_item_tipo_registro(Connection conn, item_tipo_registro itreg) {
        String titulo = "update_item_tipo_registro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, itreg.getC3creado_por());
            pst.setString(3, itreg.getC4estado());
            pst.setDate(4, evefec.getDateSQL_sistema());
            pst.setString(5, itreg.getC6nro_habilitacion());
            pst.setDate(6, evefec.getDateSQL_sistema());
            pst.setDate(7, evefec.getDateSQL_sistema());
            pst.setString(8, itreg.getC9imagen());
            pst.setInt(9, itreg.getC10fk_idtipo_registro());
            pst.setInt(10, itreg.getC11fk_idtipo_dependencia());
            pst.setInt(11, itreg.getC12fk_idtipo_institucion());
            pst.setInt(12, itreg.getC13fk_idtercero());
            pst.setInt(13, itreg.getC1iditem_tipo_registro());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + itreg.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + itreg.toString(), titulo);
        }
    }

    public void cargar_item_tipo_registro(Connection conn, item_tipo_registro itreg, int id) {
        String titulo = "Cargar_item_tipo_registro";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                itreg.setC1iditem_tipo_registro(rs.getInt(1));
                itreg.setC2fecha_creacion(rs.getString(2));
                itreg.setC3creado_por(rs.getString(3));
                itreg.setC4estado(rs.getString(4));
                itreg.setC5fecha_estado(rs.getString(5));
                itreg.setC6nro_habilitacion(rs.getString(6));
                itreg.setC7fecha_habilitacion(rs.getString(7));
                itreg.setC8fecha_vencimiento(rs.getString(8));
                itreg.setC9imagen(rs.getString(9));
                itreg.setC10fk_idtipo_registro(rs.getInt(10));
                itreg.setC11fk_idtipo_dependencia(rs.getInt(11));
                itreg.setC12fk_idtipo_institucion(rs.getInt(12));
                itreg.setC13fk_idtercero(rs.getInt(13));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + itreg.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + itreg.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_tipo_registro(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_tipo_registro(tbltabla);
    }

    public void ancho_tabla_item_tipo_registro(JTable tbltabla) {
        int Ancho[] = {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
