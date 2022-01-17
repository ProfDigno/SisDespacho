package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.factura_nro_proforma;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_factura_nro_proforma {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "FACTURA_NRO_PROFORMA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "FACTURA_NRO_PROFORMA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO factura_nro_proforma(idfactura_nro_proforma,fecha_creacion,creado_por,nro_factura,"
            + "monto,imagen,fk_idliquidacion_proforma,guarani_unidad,fk_idmoneda_cambio) VALUES (?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE factura_nro_proforma SET fecha_creacion=?,creado_por=?,nro_factura=?,monto=?,"
            + "imagen=?,fk_idliquidacion_proforma=?,guarani_unidad=?,fk_idmoneda_cambio=? WHERE idfactura_nro_proforma=?;";
    private String sql_select = "SELECT idfactura_nro_proforma,fecha_creacion,creado_por,nro_factura,monto,"
            + "imagen,fk_idliquidacion_proforma,guarani_unidad,fk_idmoneda_cambio FROM factura_nro_proforma order by 1 desc;";
    private String sql_cargar = "SELECT idfactura_nro_proforma,fecha_creacion,creado_por,nro_factura,monto,"
            + "imagen,fk_idliquidacion_proforma,guarani_unidad,fk_idmoneda_cambio FROM factura_nro_proforma WHERE idfactura_nro_proforma=";

    public void insertar_factura_nro_proforma(Connection conn, factura_nro_proforma facnro) {
        facnro.setC1idfactura_nro_proforma(eveconn.getInt_ultimoID_mas_uno(conn, facnro.getTb_factura_nro_proforma(), facnro.getId_idfactura_nro_proforma()));
        String titulo = "insertar_factura_nro_proforma";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, facnro.getC1idfactura_nro_proforma());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, facnro.getC3creado_por());
            pst.setString(4, facnro.getC4nro_factura());
            pst.setDouble(5, facnro.getC5monto());
            pst.setString(6, facnro.getC6imagen());
            pst.setInt(7, facnro.getC7fk_idliquidacion_proforma());
            pst.setDouble(8, facnro.getC8guarani_unidad());
            pst.setInt(9, facnro.getC9fk_idmoneda_cambio());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + facnro.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + facnro.toString(), titulo);
        }
    }
public void insertar_factura_nro_proforma_de_tabla_factuta(Connection conn, JTable tblitem_producto,  liquidacion_proforma liqpro) {
        String titulo="insertar_item_comprobante_de_tabla";
         for (int row = 0; row < tblitem_producto.getRowCount(); row++) {
            String fk_idmoneda_cambio = ((tblitem_producto.getModel().getValueAt(row, 0).toString()));
            String guarani_unidad = ((tblitem_producto.getModel().getValueAt(row, 2).toString()));
            String monto = ((tblitem_producto.getModel().getValueAt(row, 3).toString()));
            String nro_factura = ((tblitem_producto.getModel().getValueAt(row, 5).toString()));
            String imagen = ((tblitem_producto.getModel().getValueAt(row, 6).toString()));
            try {
                factura_nro_proforma item = new factura_nro_proforma();
                item.setC3creado_por("DIGNO");
                item.setC4nro_factura(nro_factura);
                item.setC5monto(Double.parseDouble(monto));
                item.setC6imagen(imagen);
                item.setC7fk_idliquidacion_proforma(liqpro.getC1idliquidacion_proforma());
                item.setC8guarani_unidad(Double.parseDouble(guarani_unidad));
                item.setC9fk_idmoneda_cambio(Integer.parseInt(fk_idmoneda_cambio));
                insertar_factura_nro_proforma(conn, item);
            } catch (Exception e) {
                evemen.mensaje_error(e,titulo);
                break;
            }
            
        }
    }
    public void update_factura_nro_proforma(Connection conn, factura_nro_proforma facnro) {
        String titulo = "update_factura_nro_proforma";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, facnro.getC3creado_por());
            pst.setString(3, facnro.getC4nro_factura());
            pst.setDouble(4, facnro.getC5monto());
            pst.setString(5, facnro.getC6imagen());
            pst.setInt(6, facnro.getC7fk_idliquidacion_proforma());
            pst.setDouble(7, facnro.getC8guarani_unidad());
            pst.setInt(8, facnro.getC9fk_idmoneda_cambio());
            pst.setInt(9, facnro.getC1idfactura_nro_proforma());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + facnro.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + facnro.toString(), titulo);
        }
    }

    public void cargar_factura_nro_proforma(Connection conn, factura_nro_proforma facnro, int id) {
        String titulo = "Cargar_factura_nro_proforma";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                facnro.setC1idfactura_nro_proforma(rs.getInt(1));
                facnro.setC2fecha_creacion(rs.getString(2));
                facnro.setC3creado_por(rs.getString(3));
                facnro.setC4nro_factura(rs.getString(4));
                facnro.setC5monto(rs.getDouble(5));
                facnro.setC6imagen(rs.getString(6));
                facnro.setC7fk_idliquidacion_proforma(rs.getInt(7));
                facnro.setC8guarani_unidad(rs.getDouble(8));
                facnro.setC9fk_idmoneda_cambio(rs.getInt(9));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + facnro.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + facnro.toString(), titulo);
        }
    }

    public void actualizar_tabla_factura_nro_proforma(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_factura_nro_proforma(tbltabla);
    }

    public void ancho_tabla_factura_nro_proforma(JTable tbltabla) {
        int Ancho[] = {14, 14, 14, 14, 14, 14, 14};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
