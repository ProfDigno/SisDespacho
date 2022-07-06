package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import CONFIGURACION.EveVarGlobal;
import FORMULARIO.ENTIDAD.item_pre_liquidacion;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.entidad_usuario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_pre_liquidacion {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    EveVarGlobal varglo=new EveVarGlobal();
    private entidad_usuario ENTusu = new entidad_usuario();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private String mensaje_insert = "ITEM_PRE_LIQUIDACION GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_PRE_LIQUIDACION MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_pre_liquidacion(iditem_pre_liquidacion,fecha_creado,creado_por,orden,descripcion,comprobante_nro,total_guarani,sin_iva,solo_iva,fk_idpre_liquidacion,fk_idcomprobante_liquidacion) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_pre_liquidacion SET fecha_creado=?,creado_por=?,orden=?,descripcion=?,comprobante_nro=?,total_guarani=?,sin_iva=?,solo_iva=?,fk_idpre_liquidacion=?,fk_idcomprobante_liquidacion=? WHERE iditem_pre_liquidacion=?;";
    private String sql_select = "SELECT iditem_pre_liquidacion,fecha_creado,creado_por,orden,descripcion,comprobante_nro,total_guarani,sin_iva,solo_iva,fk_idpre_liquidacion,fk_idcomprobante_liquidacion FROM item_pre_liquidacion order by 1 desc;";
    private String sql_cargar = "SELECT iditem_pre_liquidacion,fecha_creado,creado_por,orden,descripcion,comprobante_nro,total_guarani,sin_iva,solo_iva,fk_idpre_liquidacion,fk_idcomprobante_liquidacion FROM item_pre_liquidacion WHERE iditem_pre_liquidacion=";

    public void insertar_item_pre_liquidacion(Connection conn, item_pre_liquidacion ipl) {
        ipl.setC1iditem_pre_liquidacion(eveconn.getInt_ultimoID_mas_uno(conn, ipl.getTb_item_pre_liquidacion(), ipl.getId_iditem_pre_liquidacion()));
        String titulo = "insertar_item_pre_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ipl.getC1iditem_pre_liquidacion());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, ipl.getC3creado_por());
            pst.setInt(4, ipl.getC4orden());
            pst.setString(5, ipl.getC5descripcion());
            pst.setString(6, ipl.getC6comprobante_nro());
            pst.setDouble(7, ipl.getC7total_guarani());
            pst.setDouble(8, ipl.getC8sin_iva());
            pst.setDouble(9, ipl.getC9solo_iva());
            pst.setInt(10, ipl.getC10fk_idpre_liquidacion());
            pst.setInt(11, ipl.getC11fk_idcomprobante_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ipl.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ipl.toString(), titulo);
        }
    }

    public void insertar_item_pre_liquidacion_final_de_tabla(Connection conn, JTable tblitem_liquidacion_final) {
        String titulo = "insertar_item_pre_liquidacion_final_de_tabla";
        for (int row = 0; row < tblitem_liquidacion_final.getRowCount(); row++) {
            item_pre_liquidacion itemfin = new item_pre_liquidacion();
            String ord = ((tblitem_liquidacion_final.getModel().getValueAt(row, 0).toString()));
            String descripcion = ((tblitem_liquidacion_final.getModel().getValueAt(row, 1).toString()));
            String comprobante = ((tblitem_liquidacion_final.getModel().getValueAt(row, 2).toString()));
            String Stotal = ((tblitem_liquidacion_final.getModel().getValueAt(row, 3).toString()));
            String Ssin_iva = ((tblitem_liquidacion_final.getModel().getValueAt(row, 4).toString()));
            String Ssolo_iva = ((tblitem_liquidacion_final.getModel().getValueAt(row, 5).toString()));
            String idliqui = ((tblitem_liquidacion_final.getModel().getValueAt(row, 6).toString()));
            String idcompro = ((tblitem_liquidacion_final.getModel().getValueAt(row, 7).toString()));
            double total = Double.parseDouble(Stotal);
            double sin_iva = Double.parseDouble(Ssin_iva);
            double solo_iva = Double.parseDouble(Ssolo_iva);
            try {
                itemfin.setC3creado_por(creado_por);
                itemfin.setC4orden(Integer.parseInt(ord));
                itemfin.setC5descripcion(descripcion);
                itemfin.setC6comprobante_nro(comprobante);
                itemfin.setC7total_guarani(total);
                itemfin.setC8sin_iva(sin_iva);
                itemfin.setC9solo_iva(solo_iva);
                itemfin.setC10fk_idpre_liquidacion(Integer.parseInt(idliqui));
                itemfin.setC11fk_idcomprobante_liquidacion(Integer.parseInt(idcompro));
                insertar_item_pre_liquidacion(conn, itemfin);
            } catch (Exception e) {
                evemen.mensaje_error(e, titulo);
                break;
            }

        }
    }

    public void update_item_pre_liquidacion(Connection conn, item_pre_liquidacion ipl) {
        String titulo = "update_item_pre_liquidacion";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, ipl.getC3creado_por());
            pst.setInt(3, ipl.getC4orden());
            pst.setString(4, ipl.getC5descripcion());
            pst.setString(5, ipl.getC6comprobante_nro());
            pst.setDouble(6, ipl.getC7total_guarani());
            pst.setDouble(7, ipl.getC8sin_iva());
            pst.setDouble(8, ipl.getC9solo_iva());
            pst.setInt(9, ipl.getC10fk_idpre_liquidacion());
            pst.setInt(10, ipl.getC11fk_idcomprobante_liquidacion());
            pst.setInt(11, ipl.getC1iditem_pre_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ipl.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ipl.toString(), titulo);
        }
    }

    public void cargar_item_pre_liquidacion(Connection conn, item_pre_liquidacion ipl, int iditem_pre_liquidacion) {
        String titulo = "Cargar_item_pre_liquidacion";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + iditem_pre_liquidacion, titulo);
            if (rs.next()) {
                ipl.setC1iditem_pre_liquidacion(rs.getInt(1));
                ipl.setC2fecha_creado(rs.getString(2));
                ipl.setC3creado_por(rs.getString(3));
                ipl.setC4orden(rs.getInt(4));
                ipl.setC5descripcion(rs.getString(5));
                ipl.setC6comprobante_nro(rs.getString(6));
                ipl.setC7total_guarani(rs.getDouble(7));
                ipl.setC8sin_iva(rs.getDouble(8));
                ipl.setC9solo_iva(rs.getDouble(9));
                ipl.setC10fk_idpre_liquidacion(rs.getInt(10));
                ipl.setC11fk_idcomprobante_liquidacion(rs.getInt(11));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ipl.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ipl.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_pre_liquidacion(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_pre_liquidacion(tbltabla);
    }

    public void ancho_tabla_item_pre_liquidacion(JTable tbltabla) {
        int Ancho[] = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
    public void actualizar_tabla_item_pre_liquidacion_por_id(Connection conn, JTable tbltabla,int fk_idliquidacion_final) {
        String sql = "select ilf.orden, ilf.descripcion,ilf.comprobante_nro,"
                + "to_char(ilf.total_guarani,'"+varglo.getFormato_numero_3c()+"') as total, \n"
                + "to_char(ilf.sin_iva,'"+varglo.getFormato_numero_3c()+"') as sin_iva, \n"
                + "to_char(ilf.solo_iva,'"+varglo.getFormato_numero_3c()+"') as solo_iva \n"
                + "from item_pre_liquidacion ilf \n"
                + "where ilf.fk_idpre_liquidacion="+fk_idliquidacion_final
                + " order by 1 asc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        evejt.alinear_derecha_columna(tbltabla, 3);
        evejt.alinear_derecha_columna(tbltabla, 4);
        evejt.alinear_derecha_columna(tbltabla, 5);
        ancho_tabla_item_liquidacion_final(tbltabla);
    }
    public void ancho_tabla_item_liquidacion_final(JTable tbltabla) {
        int Ancho[] = {5,30, 20, 15,15,15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
