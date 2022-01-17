package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.item_comprobante;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.entidad_usuario;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_item_comprobante {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    private entidad_usuario ENTusu = new entidad_usuario();
    private String creado_por =ENTusu.getGlobal_idusuario()+"-"+ENTusu.getGlobal_nombre();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ITEM_COMPROBANTE GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ITEM_COMPROBANTE MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO item_comprobante(iditem_comprobante,fecha_creado,creado_por,descripcion,monto,referencia,imagen,fk_idliquidacion_proforma,fk_idtipo_comprobante,tipo_comprobante) VALUES (?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE item_comprobante SET fecha_creado=?,creado_por=?,descripcion=?,monto=?,referencia=?,imagen=?,fk_idliquidacion_proforma=?,fk_idtipo_comprobante=?,tipo_comprobante=? WHERE iditem_comprobante=?;";
    private String sql_select = "SELECT iditem_comprobante,fecha_creado,creado_por,descripcion,monto,referencia,imagen,fk_idliquidacion_proforma,fk_idtipo_comprobante FROM item_comprobante order by 1 desc;";
    private String sql_cargar = "SELECT iditem_comprobante,fecha_creado,creado_por,descripcion,monto,referencia,imagen,fk_idliquidacion_proforma,fk_idtipo_comprobante,tipo_comprobante FROM item_comprobante WHERE iditem_comprobante=";

    public void insertar_item_comprobante(Connection conn, item_comprobante iscom) {
        iscom.setC1iditem_comprobante(eveconn.getInt_ultimoID_mas_uno(conn, iscom.getTb_item_comprobante(), iscom.getId_iditem_comprobante()));
        String titulo = "insertar_item_comprobante";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, iscom.getC1iditem_comprobante());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, iscom.getC3creado_por());
            pst.setString(4, iscom.getC4descripcion());
            pst.setDouble(5, iscom.getC5monto());
            pst.setString(6, iscom.getC6referencia());
            pst.setString(7, iscom.getC7imagen());
            pst.setInt(8, iscom.getC8fk_idliquidacion_proforma());
            pst.setInt(9, iscom.getC9fk_idtipo_comprobante());
            pst.setString(10, iscom.getC10tipo_comprobante());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + iscom.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + iscom.toString(), titulo);
        }
    }

    public void update_item_comprobante(Connection conn, item_comprobante iscom) {
        String titulo = "update_item_comprobante";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, iscom.getC3creado_por());
            pst.setString(3, iscom.getC4descripcion());
            pst.setDouble(4, iscom.getC5monto());
            pst.setString(5, iscom.getC6referencia());
            pst.setString(6, iscom.getC7imagen());
            pst.setInt(7, iscom.getC8fk_idliquidacion_proforma());
            pst.setInt(8, iscom.getC9fk_idtipo_comprobante());
            pst.setString(9, iscom.getC10tipo_comprobante());
            pst.setInt(10, iscom.getC1iditem_comprobante());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + iscom.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + iscom.toString(), titulo);
        }
    }

    public void cargar_item_comprobante(Connection conn, item_comprobante iscom, int id) {
        String titulo = "Cargar_item_comprobante";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                iscom.setC1iditem_comprobante(rs.getInt(1));
                iscom.setC2fecha_creado(rs.getString(2));
                iscom.setC3creado_por(rs.getString(3));
                iscom.setC4descripcion(rs.getString(4));
                iscom.setC5monto(rs.getDouble(5));
                iscom.setC6referencia(rs.getString(6));
                iscom.setC7imagen(rs.getString(7));
                iscom.setC8fk_idliquidacion_proforma(rs.getInt(8));
                iscom.setC9fk_idtipo_comprobante(rs.getInt(9));
                iscom.setC10tipo_comprobante(rs.getString(10));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + iscom.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + iscom.toString(), titulo);
        }
    }
     public void insertar_item_comprobante_de_tabla(Connection conn, JTable tblitem_producto,  liquidacion_proforma liqpro,int tipo_item_comp) {
        String titulo="insertar_item_comprobante_de_tabla";
         for (int row = 0; row < tblitem_producto.getRowCount(); row++) {
             item_comprobante item = new item_comprobante();
            String iditem_comprobante = ((tblitem_producto.getModel().getValueAt(row, 0).toString()));
            String descripcion = ((tblitem_producto.getModel().getValueAt(row, 1).toString()));
            String monto = ((tblitem_producto.getModel().getValueAt(row, 2).toString()));
            String referencia = ((tblitem_producto.getModel().getValueAt(row, 3).toString()));
            String imagen = ((tblitem_producto.getModel().getValueAt(row, 4).toString()));
            String tipo_comprobante="error";
            if(tipo_item_comp==1){
                tipo_comprobante=item.getTipo_comprobante_CON_COMPROBANTE();
            }
            if(tipo_item_comp==2){
                tipo_comprobante=item.getTipo_comprobante_SIN_COMPROBANTE();
            }
            if(tipo_item_comp==3){
                tipo_comprobante=item.getTipo_comprobante_BOLETA_DESPACHO();
            }
            if(tipo_item_comp==4){
                tipo_comprobante="FACTURA_MONTO";
            }
            try {
                
                item.setC3creado_por(creado_por);
                item.setC4descripcion(descripcion);
                item.setC5monto(Double.parseDouble(monto));
                item.setC6referencia(referencia);
                item.setC7imagen(imagen);
                item.setC8fk_idliquidacion_proforma(liqpro.getC1idliquidacion_proforma());
                item.setC9fk_idtipo_comprobante(Integer.parseInt(iditem_comprobante));
                item.setC10tipo_comprobante(tipo_comprobante);
                insertar_item_comprobante(conn, item);
            } catch (Exception e) {
                evemen.mensaje_error(e,titulo);
                break;
            }
            
        }
    }
     public void insertar_item_comprobante_de_tabla_mercaderia(Connection conn, JTable tblitem_producto,  liquidacion_proforma liqpro) {
        String titulo="insertar_item_comprobante_de_tabla";
         for (int row = 0; row < tblitem_producto.getRowCount(); row++) {
             item_comprobante item = new item_comprobante();
            String iditem_comprobante = ((tblitem_producto.getModel().getValueAt(row, 0).toString()));
            String descripcion = ((tblitem_producto.getModel().getValueAt(row, 1).toString()));
            String monto = "0";
            String referencia = "SIN-REF";
            String imagen = "SIN-IMAGEN";
            String tipo_comprobante=item.getTipo_comprobante_MERCADERIA();
            try {
                item.setC3creado_por(creado_por);
                item.setC4descripcion(descripcion);
                item.setC5monto(Double.parseDouble(monto));
                item.setC6referencia(referencia);
                item.setC7imagen(imagen);
                item.setC8fk_idliquidacion_proforma(liqpro.getC1idliquidacion_proforma());
                item.setC9fk_idtipo_comprobante(Integer.parseInt(iditem_comprobante));
                item.setC10tipo_comprobante(tipo_comprobante);
                insertar_item_comprobante(conn, item);
            } catch (Exception e) {
                evemen.mensaje_error(e,titulo);
                break;
            }
            
        }
    }
    public void actualizar_tabla_item_comprobante(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_item_comprobante(tbltabla);
    }

    public void ancho_tabla_item_comprobante(JTable tbltabla) {
        int Ancho[] = {11, 11, 11, 11, 11, 11, 11, 11, 11};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
