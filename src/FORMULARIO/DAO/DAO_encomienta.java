package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.encomienta;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_encomienta {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "ENCOMIENTA GUARDADO CORRECTAMENTE";
    private String mensaje_update = "ENCOMIENTA MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO encomienta(idencomienta,fecha_creado,numero,destinatario,telefono,direccion_destino,forma_entrega,forma_pago,detalle_paquete,monto,fk_idtercero_ciudad,fk_idliquidacion_proforma,fk_idtercero) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE encomienta SET fecha_creado=?,numero=?,destinatario=?,telefono=?,direccion_destino=?,forma_entrega=?,forma_pago=?,detalle_paquete=?,monto=?,fk_idtercero_ciudad=?,fk_idliquidacion_proforma=?,fk_idtercero=? WHERE idencomienta=?;";
    private String sql_select = "SELECT idencomienta,fecha_creado,numero,destinatario,telefono,direccion_destino,forma_entrega,forma_pago,detalle_paquete,monto,fk_idtercero_ciudad,fk_idliquidacion_proforma FROM encomienta order by 1 desc;";
    private String sql_cargar = "SELECT idencomienta,fecha_creado,numero,destinatario,telefono,direccion_destino,forma_entrega,forma_pago,detalle_paquete,monto,fk_idtercero_ciudad,fk_idliquidacion_proforma,fk_idtercero FROM encomienta WHERE idencomienta=";

    public void insertar_encomienta(Connection conn, encomienta enco) {
        enco.setC1idencomienta(eveconn.getInt_ultimoID_mas_uno(conn, enco.getTb_encomienta(), enco.getId_idencomienta()));
        String titulo = "insertar_encomienta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, enco.getC1idencomienta());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setDouble(3, enco.getC3numero());
            pst.setString(4, enco.getC4destinatario());
            pst.setString(5, enco.getC5telefono());
            pst.setString(6, enco.getC6direccion_destino());
            pst.setString(7, enco.getC7forma_entrega());
            pst.setString(8, enco.getC8forma_pago());
            pst.setString(9, enco.getC9detalle_paquete());
            pst.setDouble(10, enco.getC10monto());
            pst.setInt(11, enco.getC11fk_idtercero_ciudad());
            pst.setInt(12, enco.getC12fk_idliquidacion_proforma());
            pst.setInt(13, enco.getC13fk_idtercero());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + enco.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + enco.toString(), titulo);
        }
    }

    public void update_encomienta(Connection conn, encomienta enco) {
        String titulo = "update_encomienta";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setDouble(2, enco.getC3numero());
            pst.setString(3, enco.getC4destinatario());
            pst.setString(4, enco.getC5telefono());
            pst.setString(5, enco.getC6direccion_destino());
            pst.setString(6, enco.getC7forma_entrega());
            pst.setString(7, enco.getC8forma_pago());
            pst.setString(8, enco.getC9detalle_paquete());
            pst.setDouble(9, enco.getC10monto());
            pst.setInt(10, enco.getC11fk_idtercero_ciudad());
            pst.setInt(11, enco.getC12fk_idliquidacion_proforma());
            pst.setInt(12, enco.getC13fk_idtercero());
            pst.setInt(13, enco.getC1idencomienta());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + enco.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + enco.toString(), titulo);
        }
    }

    public void cargar_encomienta(Connection conn, encomienta enco, int id) {
        String titulo = "Cargar_encomienta";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                enco.setC1idencomienta(rs.getInt(1));
                enco.setC2fecha_creado(rs.getString(2));
                enco.setC3numero(rs.getDouble(3));
                enco.setC4destinatario(rs.getString(4));
                enco.setC5telefono(rs.getString(5));
                enco.setC6direccion_destino(rs.getString(6));
                enco.setC7forma_entrega(rs.getString(7));
                enco.setC8forma_pago(rs.getString(8));
                enco.setC9detalle_paquete(rs.getString(9));
                enco.setC10monto(rs.getDouble(10));
                enco.setC11fk_idtercero_ciudad(rs.getInt(11));
                enco.setC12fk_idliquidacion_proforma(rs.getInt(12));
                enco.setC13fk_idtercero(rs.getInt(13));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + enco.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + enco.toString(), titulo);
        }
    }

    public void actualizar_tabla_encomienta(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_encomienta(tbltabla);
    }

    public void ancho_tabla_encomienta(JTable tbltabla) {
        int Ancho[] = {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
