package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.tercero_rubro;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_tercero_rubro {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "TERCERO_RUBRO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "TERCERO_RUBRO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO tercero_rubro(idtercero_rubro,nombre,sigla,descripcion) VALUES (?,?,?,?);";
    private String sql_update = "UPDATE tercero_rubro SET nombre=?,sigla=?,descripcion=? WHERE idtercero_rubro=?;";
    private String sql_select = "SELECT idtercero_rubro as idtr,nombre,sigla,descripcion FROM tercero_rubro order by 1 desc;";
    private String sql_cargar = "SELECT idtercero_rubro,nombre,sigla,descripcion FROM tercero_rubro WHERE idtercero_rubro=";

    public void insertar_tercero_rubro(Connection conn, tercero_rubro rub) {
        rub.setC1idtercero_rubro(eveconn.getInt_ultimoID_mas_uno(conn, rub.getTb_tercero_rubro(), rub.getId_idtercero_rubro()));
        String titulo = "insertar_tercero_rubro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, rub.getC1idtercero_rubro());
            pst.setString(2, rub.getC2nombre());
            pst.setString(3, rub.getC3sigla());
            pst.setString(4, rub.getC4descripcion());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + rub.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + rub.toString(), titulo);
        }
    }

    public void update_tercero_rubro(Connection conn, tercero_rubro rub) {
        String titulo = "update_tercero_rubro";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, rub.getC2nombre());
            pst.setString(2, rub.getC3sigla());
            pst.setString(3, rub.getC4descripcion());
            pst.setInt(4, rub.getC1idtercero_rubro());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + rub.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + rub.toString(), titulo);
        }
    }

    public void cargar_tercero_rubro(Connection conn, tercero_rubro rub, int id) {
        String titulo = "Cargar_tercero_rubro";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + id, titulo);
            if (rs.next()) {
                rub.setC1idtercero_rubro(rs.getInt(1));
                rub.setC2nombre(rs.getString(2));
                rub.setC3sigla(rs.getString(3));
                rub.setC4descripcion(rs.getString(4));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + rub.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + rub.toString(), titulo);
        }
    }

    public void actualizar_tabla_tercero_rubro(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_tercero_rubro(tbltabla);
    }

    public void ancho_tabla_tercero_rubro(JTable tbltabla) {
        int Ancho[] = {5, 40, 15, 40};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_tercero_rubro_liquidacion(Connection conn, JTable tbltabla, String filtro) {
        String sql = "select ter.fk_idtercero_rubro as idtr,\n"
                + "date_part('month',lf.fecha_despacho) as nmes,\n"
                + "tr.nombre as rubro,\n"
                + "case \n"
                + "when date_part('month',lf.fecha_despacho)=1 then date_part('year',lf.fecha_despacho)||'-ENERO'\n"
                + "when date_part('month',lf.fecha_despacho)=2 then date_part('year',lf.fecha_despacho)||'-FEBRERO'\n"
                + "when date_part('month',lf.fecha_despacho)=3 then date_part('year',lf.fecha_despacho)||'-MARZO'\n"
                + "when date_part('month',lf.fecha_despacho)=4 then date_part('year',lf.fecha_despacho)||'-ABRIL'\n"
                + "when date_part('month',lf.fecha_despacho)=5 then date_part('year',lf.fecha_despacho)||'-MAYO'\n"
                + "when date_part('month',lf.fecha_despacho)=6 then date_part('year',lf.fecha_despacho)||'-JUNIO'\n"
                + "when date_part('month',lf.fecha_despacho)=7 then date_part('year',lf.fecha_despacho)||'-JULIO'\n"
                + "when date_part('month',lf.fecha_despacho)=8 then date_part('year',lf.fecha_despacho)||'-AGOSTO'\n"
                + "when date_part('month',lf.fecha_despacho)=9 then date_part('year',lf.fecha_despacho)||'-SETIEMBRE'\n"
                + "when date_part('month',lf.fecha_despacho)=10 then date_part('year',lf.fecha_despacho)||'-OCTUBRE'\n"
                + "when date_part('month',lf.fecha_despacho)=11 then date_part('year',lf.fecha_despacho)||'-NOVIEMBRE'\n"
                + "when date_part('month',lf.fecha_despacho)=12 then date_part('year',lf.fecha_despacho)||'-DICIEMBRE'\n"
                + "else 'ERROR' end as mes,\n"
                + "count(*) as cant,trim(to_char(sum(lf.monto_pagar),'999G999G999G999')) as monto_pagar \n"
                + "from tercero ter,tercero_rubro tr,liquidacion_final lf  \n"
                + "where ter.fk_idtercero_rubro=tr.idtercero_rubro \n"
                + "and ter.idtercero=lf.fk_idtercero_importador \n"
                + "and lf.estado='PAGADO' \n"+filtro
                + " group by 1,2,3,4 order by 1 asc,2 asc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_tercero_rubro_liquidacion(tbltabla);
    }

    public void ancho_tabla_tercero_rubro_liquidacion(JTable tbltabla) {
        int Ancho[] = {5, 5, 50, 15,10,15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
