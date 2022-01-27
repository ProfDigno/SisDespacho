package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_liquidacion_final;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.entidad_usuario;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_liquidacion_final {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private entidad_usuario ENTusu = new entidad_usuario();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private String mensaje_insert = "ITEM_LIQUIDACION_FINAL GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_LIQUIDACION_FINAL MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_liquidacion_final(iditem_liquidacion_final,fecha_creado,creado_por,orden,descripcion,comprobante_nro,total_guarani,sin_iva,solo_iva,fk_idliquidacion_final,fk_idcomprobante_liquidacion) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_liquidacion_final SET fecha_creado=?,creado_por=?,orden=?,descripcion=?,comprobante_nro=?,total_guarani=?,sin_iva=?,solo_iva=?,fk_idliquidacion_final=?,fk_idcomprobante_liquidacion=? WHERE iditem_liquidacion_final=?;";
    private String sql_select = "SELECT iditem_liquidacion_final,fecha_creado,creado_por,orden,descripcion,comprobante_nro,total_guarani,sin_iva,solo_iva,fk_idliquidacion_final,fk_idcomprobante_liquidacion FROM item_liquidacion_final order by 1 desc;";
    private String sql_cargar = "SELECT iditem_liquidacion_final,fecha_creado,creado_por,"
            + "orden,descripcion,comprobante_nro,"
            + "total_guarani,sin_iva,solo_iva,"
            + "fk_idliquidacion_final,fk_idcomprobante_liquidacion "
            + "FROM item_liquidacion_final WHERE iditem_liquidacion_final=";

    public void insertar_item_liquidacion_final(Connection conn, item_liquidacion_final itemfin) {
        itemfin.setC1iditem_liquidacion_final(eveconn.getInt_ultimoID_mas_uno(conn, itemfin.getTb_item_liquidacion_final(), itemfin.getId_iditem_liquidacion_final()));
        String titulo = "insertar_item_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, itemfin.getC1iditem_liquidacion_final());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, itemfin.getC3creado_por());
            pst.setInt(4, itemfin.getC4orden());
            pst.setString(5, itemfin.getC5descripcion());
            pst.setString(6, itemfin.getC6comprobante_nro());
            pst.setDouble(7, itemfin.getC7total_guarani());
            pst.setDouble(8, itemfin.getC8sin_iva());
            pst.setDouble(9, itemfin.getC9solo_iva());
            pst.setInt(10, itemfin.getC10fk_idliquidacion_final());
            pst.setInt(11, itemfin.getC11fk_idcomprobante_liquidacion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + itemfin.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + itemfin.toString(), titulo);
        }
    }

    public void insertar_item_liquidacion_final_de_tabla_mercaderia(Connection conn, JTable tblitem_producto) {
        String titulo = "insertar_item_comprobante_de_tabla";
        //String dato[] = {"ord", "descripcion", "comprobante", "total", "idliqui", "idcompro"};
        for (int row = 0; row < tblitem_producto.getRowCount(); row++) {
            item_liquidacion_final itemfin = new item_liquidacion_final();
            String ord = ((tblitem_producto.getModel().getValueAt(row, 0).toString()));
            String descripcion = ((tblitem_producto.getModel().getValueAt(row, 1).toString()));
            String comprobante = ((tblitem_producto.getModel().getValueAt(row, 2).toString()));
            String total = ((tblitem_producto.getModel().getValueAt(row, 3).toString()));
            String idliqui = ((tblitem_producto.getModel().getValueAt(row, 4).toString()));
            String idcompro = ((tblitem_producto.getModel().getValueAt(row, 5).toString()));
            double sin_iva = 0;
            double solo_iva = 0;
            try {
                itemfin.setC3creado_por(creado_por);
                itemfin.setC4orden(Integer.parseInt(ord));
                itemfin.setC5descripcion(descripcion);
                itemfin.setC6comprobante_nro(comprobante);
                itemfin.setC7total_guarani(Double.parseDouble(total));
                itemfin.setC8sin_iva(sin_iva);
                itemfin.setC9solo_iva(solo_iva);
                itemfin.setC10fk_idliquidacion_final(Integer.parseInt(idliqui));
                itemfin.setC11fk_idcomprobante_liquidacion(Integer.parseInt(idcompro));
                insertar_item_liquidacion_final(conn, itemfin);
            } catch (Exception e) {
                evemen.mensaje_error(e, titulo);
                break;
            }

        }
    }

    public void update_item_liquidacion_final(Connection conn, item_liquidacion_final itemfin) {
        String titulo = "update_item_liquidacion_final";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, itemfin.getC3creado_por());
            pst.setInt(3, itemfin.getC4orden());
            pst.setString(4, itemfin.getC5descripcion());
            pst.setString(5, itemfin.getC6comprobante_nro());
            pst.setDouble(6, itemfin.getC7total_guarani());
            pst.setDouble(7, itemfin.getC8sin_iva());
            pst.setDouble(8, itemfin.getC9solo_iva());
            pst.setInt(9, itemfin.getC10fk_idliquidacion_final());
            pst.setInt(10, itemfin.getC11fk_idcomprobante_liquidacion());
            pst.setInt(11, itemfin.getC1iditem_liquidacion_final());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + itemfin.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + itemfin.toString(), titulo);
        }
    }

    public void cargar_item_liquidacion_final(Connection conn, item_liquidacion_final itemfin, int id) {
        String titulo = "Cargar_item_liquidacion_final";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                itemfin.setC1iditem_liquidacion_final(rs.getInt(1));
                itemfin.setC2fecha_creado(rs.getString(2));
                itemfin.setC3creado_por(rs.getString(3));
                itemfin.setC4orden(rs.getInt(4));
                itemfin.setC5descripcion(rs.getString(5));
                itemfin.setC6comprobante_nro(rs.getString(6));
                itemfin.setC7total_guarani(rs.getDouble(7));
                itemfin.setC8sin_iva(rs.getDouble(8));
                itemfin.setC9solo_iva(rs.getDouble(9));
                itemfin.setC10fk_idliquidacion_final(rs.getInt(10));
                itemfin.setC11fk_idcomprobante_liquidacion(rs.getInt(11));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + itemfin.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + itemfin.toString(), titulo);
        }
    }

    public void actualizar_tabla_item_liquidacion_final(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
//        ancho_tabla_item_liquidacion_final(tbltabla);
    }

    public void actualizar_tabla_item_liquidacion_final_por_id(Connection conn, JTable tbltabla,int fk_idliquidacion_final) {
        String sql = "select ilf.orden, ilf.descripcion,ilf.comprobante_nro,to_char(ilf.total_guarani,'999G999G999') as total_guarani \n"
                + "from item_liquidacion_final ilf \n"
                + "where ilf.fk_idliquidacion_final="+fk_idliquidacion_final
                + " order by 1 asc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_item_liquidacion_final(tbltabla);
    }

    public void ancho_tabla_item_liquidacion_final(JTable tbltabla) {
        int Ancho[] = {10,40, 30, 20};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
