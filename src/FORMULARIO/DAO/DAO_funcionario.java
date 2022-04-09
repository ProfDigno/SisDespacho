package FORMULARIO.DAO;

import BASEDATO.EvenConexion;
import FORMULARIO.ENTIDAD.funcionario;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Fecha.EvenFecha;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTable;

public class DAO_funcionario {

    EvenConexion eveconn = new EvenConexion();
    EvenJtable evejt = new EvenJtable();
    EvenJasperReport rep = new EvenJasperReport();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenFecha evefec = new EvenFecha();
    private String mensaje_insert = "FUNCIONARIO GUARDADO CORRECTAMENTE";
    private String mensaje_update = "FUNCIONARIO MODIFICADO CORECTAMENTE";
    private String sql_insert = "INSERT INTO funcionario(idfuncionario,fecha_creado,creado_por,nombre,cedula,telefono,direccion,cargo,salario) VALUES (?,?,?,?,?,?,?,?,?);";
    private String sql_update = "UPDATE funcionario SET fecha_creado=?,creado_por=?,nombre=?,cedula=?,telefono=?,direccion=?,cargo=?,salario=? WHERE idfuncionario=?;";
    private String sql_select = "SELECT idfuncionario as idf,nombre,cedula,cargo,to_char(salario,'999G999G999') as salario FROM funcionario order by 1 desc;";
    private String sql_cargar = "SELECT idfuncionario,fecha_creado,creado_por,nombre,cedula,telefono,direccion,cargo,salario FROM funcionario WHERE idfuncionario=";

    public void insertar_funcionario(Connection conn, funcionario fun) {
        fun.setC1idfuncionario(eveconn.getInt_ultimoID_mas_uno(conn, fun.getTb_funcionario(), fun.getId_idfuncionario()));
        String titulo = "insertar_funcionario";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_insert);
            pst.setInt(1, fun.getC1idfuncionario());
            pst.setTimestamp(2, evefec.getTimestamp_sistema());
            pst.setString(3, fun.getC3creado_por());
            pst.setString(4, fun.getC4nombre());
            pst.setString(5, fun.getC5cedula());
            pst.setString(6, fun.getC6telefono());
            pst.setString(7, fun.getC7direccion());
            pst.setString(8, fun.getC8cargo());
            pst.setDouble(9, fun.getC9salario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_insert + "\n" + fun.toString(), titulo);
            evemen.guardado_correcto(mensaje_insert, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_insert + "\n" + fun.toString(), titulo);
        }
    }

    public void update_funcionario(Connection conn, funcionario fun) {
        String titulo = "update_funcionario";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql_update);
            pst.setTimestamp(1, evefec.getTimestamp_sistema());
            pst.setString(2, fun.getC3creado_por());
            pst.setString(3, fun.getC4nombre());
            pst.setString(4, fun.getC5cedula());
            pst.setString(5, fun.getC6telefono());
            pst.setString(6, fun.getC7direccion());
            pst.setString(7, fun.getC8cargo());
            pst.setDouble(8, fun.getC9salario());
            pst.setInt(9, fun.getC1idfuncionario());
            pst.execute();
            pst.close();
            evemen.Imprimir_serial_sql(sql_update + "\n" + fun.toString(), titulo);
            evemen.modificado_correcto(mensaje_update, true);
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_update + "\n" + fun.toString(), titulo);
        }
    }

    public void cargar_funcionario(Connection conn, funcionario fun, int idfuncionario) {
        String titulo = "Cargar_funcionario";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar + idfuncionario, titulo);
            if (rs.next()) {
                fun.setC1idfuncionario(rs.getInt(1));
                fun.setC2fecha_creado(rs.getString(2));
                fun.setC3creado_por(rs.getString(3));
                fun.setC4nombre(rs.getString(4));
                fun.setC5cedula(rs.getString(5));
                fun.setC6telefono(rs.getString(6));
                fun.setC7direccion(rs.getString(7));
                fun.setC8cargo(rs.getString(8));
                fun.setC9salario(rs.getDouble(9));
                evemen.Imprimir_serial_sql(sql_cargar + "\n" + fun.toString(), titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n" + fun.toString(), titulo);
        }
    }

    public void actualizar_tabla_funcionario(Connection conn, JTable tbltabla) {
        eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
        ancho_tabla_funcionario(tbltabla);
    }

    public void ancho_tabla_funcionario(JTable tbltabla) {
        int Ancho[] = {5, 50, 15, 15, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }

    public void actualizar_tabla_funcionario_vale(Connection conn, JTable tbltabla, int fk_idfuncionario,String filtro) {
        String sql = "select v.idvale as idv,to_char(v.fecha_creado,'yyyy-MM-dd HH24:MI') fecha,\n"
                + "f.nombre as funcio, v.descripcion,to_char(v.monto_vale,'999G999G999') as monto \n"
                + "from vale v,funcionario f \n"
                + "where v.fk_idfuncionario=f.idfuncionario \n"
                + "and v.estado='EMITIDO'\n"
                + "and v.fk_idfuncionario="+fk_idfuncionario+filtro
                + " order by 1 desc;";
        eveconn.Select_cargar_jtable(conn, sql, tbltabla);
        ancho_tabla_funcionario_vale(tbltabla);
    }

    public void ancho_tabla_funcionario_vale(JTable tbltabla) {
        int Ancho[] = {5, 15, 40, 25, 15};
        evejt.setAnchoColumnaJtable(tbltabla, Ancho);
    }
}
