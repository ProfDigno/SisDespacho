package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tercero;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTable;

public class DAO_tercero {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TERCERO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TERCERO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tercero(idtercero,fecha_creacion,creado_por,nombre,ruc,telefono,direccion,"
            + "representante_nombre,representante_cedula,importador,despachante,colaborador,proveedor,transportadora,"
            + "fk_idtercero_pais,fk_idtercero_ciudad,saldo_credito,fk_idtercero_rubro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE tercero SET fecha_creacion=?,creado_por=?,nombre=?,ruc=?,telefono=?,direccion=?,"
            + "representante_nombre=?,representante_cedula=?,importador=?,despachante=?,colaborador=?,proveedor=?,transportadora=?,"
            + "fk_idtercero_pais=?,fk_idtercero_ciudad=?,saldo_credito=?,fk_idtercero_rubro=? WHERE idtercero=?;";
    private String sql_select = "SELECT t.idtercero,t.nombre,t.ruc,t.telefono,t.direccion,tr.nombre as rubro,\n"
            + "TRIM(to_char(t.saldo_credito,'999G999G999')) as saldo \n"
            + "FROM tercero t, tercero_rubro tr \n"
            + "where t.fk_idtercero_rubro=tr.idtercero_rubro \n"
            + "order by 1 desc;";
    private String sql_cargar = "SELECT idtercero,fecha_creacion,creado_por,nombre,ruc,telefono,direccion,"
            + "representante_nombre,representante_cedula,importador,despachante,colaborador,proveedor,transportadora,"
            + "fk_idtercero_pais,fk_idtercero_ciudad,saldo_credito,fk_idtercero_rubro FROM tercero WHERE idtercero=";
    private String sql_update_saldo = "update tercero set saldo_credito=\n"
            + "(select sum(cc.monto_contado - cc.monto_credito) as saldo\n"
            + "from grupo_credito_tercero gcc,credito_tercero cc\n"
            + "where gcc.idgrupo_credito_tercero=cc.fk_idgrupo_credito_tercero\n"
            + "and gcc.estado='ABIERTO'\n"
            + "and (cc.estado='EMITIDO')\n"
            + "and gcc.fk_idtercero=tercero.idtercero) where tercero.idtercero=?;";

    public void insertar_tercero(Connection conn, tercero ter) {
        ter.setC1idtercero(eveconn.getInt_ultimoID_mas_uno(conn, ter.getTb_tercero(), ter.getId_idtercero()));
        String titulo = "insertar_tercero";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, ter.getC1idtercero());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, ter.getC3creado_por());
            pst.setString(4, ter.getC4nombre());
            pst.setString(5, ter.getC5ruc());
            pst.setString(6, ter.getC6telefono());
            pst.setString(7, ter.getC7direccion());
            pst.setString(8, ter.getC8representante_nombre());
            pst.setString(9, ter.getC9representante_cedula());
            pst.setBoolean(10, ter.getC10importador());
            pst.setBoolean(11, ter.getC11despachante());
            pst.setBoolean(12, ter.getC12colaborador());
            pst.setBoolean(13, ter.getC13proveedor());
            pst.setBoolean(14, ter.getC14transportadora());
            pst.setInt(15, ter.getC15fk_idtercero_pais());
            pst.setInt(16, ter.getC16fk_idtercero_ciudad());
            pst.setDouble(17, ter.getC17saldo_credito());
            pst.setInt(18, ter.getC18fk_idtercero_rubro());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + ter.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + ter.toString(), titulo);
        }
    }

    public void update_tercero(Connection conn, tercero ter) {
        String titulo = "update_tercero";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, ter.getC3creado_por());
            pst.setString(3, ter.getC4nombre());
            pst.setString(4, ter.getC5ruc());
            pst.setString(5, ter.getC6telefono());
            pst.setString(6, ter.getC7direccion());
            pst.setString(7, ter.getC8representante_nombre());
            pst.setString(8, ter.getC9representante_cedula());
            pst.setBoolean(9, ter.getC10importador());
            pst.setBoolean(10, ter.getC11despachante());
            pst.setBoolean(11, ter.getC12colaborador());
            pst.setBoolean(12, ter.getC13proveedor());
            pst.setBoolean(13, ter.getC14transportadora());
            pst.setInt(14, ter.getC15fk_idtercero_pais());
            pst.setInt(15, ter.getC16fk_idtercero_ciudad());
            pst.setDouble(16, ter.getC17saldo_credito());
            pst.setInt(17, ter.getC18fk_idtercero_rubro());
            pst.setInt(18, ter.getC1idtercero());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + ter.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + ter.toString(), titulo);
        }
    }

    public void cargar_tercero(Connection conn, tercero ter, int id) {
        String titulo = "Cargar_tercero";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                ter.setC1idtercero(rs.getInt(1));
                ter.setC1idtercero_global(rs.getInt(1));
                ter.setC2fecha_creacion(rs.getString(2));
                ter.setC3creado_por(rs.getString(3));
                ter.setC4nombre(rs.getString(4));
                ter.setC5ruc(rs.getString(5));
                ter.setC6telefono(rs.getString(6));
                ter.setC7direccion(rs.getString(7));
                ter.setC8representante_nombre(rs.getString(8));
                ter.setC9representante_cedula(rs.getString(9));
                ter.setC10importador(rs.getBoolean(10));
                ter.setC11despachante(rs.getBoolean(11));
                ter.setC12colaborador(rs.getBoolean(12));
                ter.setC13proveedor(rs.getBoolean(13));
                ter.setC14transportadora(rs.getBoolean(14));
                ter.setC15fk_idtercero_pais(rs.getInt(15));
                ter.setC16fk_idtercero_ciudad(rs.getInt(16));
                ter.setC17saldo_credito(rs.getDouble(17));
                ter.setC18fk_idtercero_rubro(rs.getInt(18));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + ter.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + ter.toString(), titulo);
        }
    }

    public void actualizar_tabla_tercero(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tercero(tbltabla);
    }

    public void ancho_tabla_tercero(JTable tbltabla) {
        int Ancho[] = {5, 22, 12, 12, 23,16, 10};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void update_cliente_saldo_credito(Connection conn, tercero cli) {
        String titulo = "update_tercero_saldo_credito";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update_saldo);
            pst.setInt(1, cli.getC1idtercero());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update_saldo + "\n" + cli.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, false);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update_saldo + "\n" + cli.toString(), titulo);
        }
    }
     public double getDouble_sumar_monto_credito_cliente(Connection conn) {
         double monto=0;
        String titulo = "sumar_monto_credito_cliente";
        String sql = "select sum(saldo_credito) as monto from tercero; ";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                 monto = rs.getDouble("monto");
            }
        } catch (SQLException e) {
            evemen.Imprimir_serial_sql_error(e, sql, titulo);
        }
        return monto;
    }
}
