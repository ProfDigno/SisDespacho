	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.despacho_zona;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_despacho_zona {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "DESPACHO_ZONA GUARDADO CORRECTAMENTE";
	private String mensaje_update = "DESPACHO_ZONA MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO despacho_zona(iddespacho_zona,nombre) VALUES (?,?);";
	private String sql_update = "UPDATE despacho_zona SET nombre=? WHERE iddespacho_zona=?;";
	private String sql_select = "SELECT iddespacho_zona,nombre FROM despacho_zona order by 1 desc;";
	private String sql_cargar = "SELECT iddespacho_zona,nombre FROM despacho_zona WHERE iddespacho_zona=";
	public void insertar_despacho_zona(Connection conn, despacho_zona dzon){
		dzon.setC1iddespacho_zona(eveconn.getInt_ultimoID_mas_uno(conn, dzon.getTb_despacho_zona(), dzon.getId_iddespacho_zona()));
		String titulo = "insertar_despacho_zona";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,dzon.getC1iddespacho_zona());
			pst.setString(2,dzon.getC2nombre());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + dzon.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + dzon.toString(), titulo);
		}
	}
	public void update_despacho_zona(Connection conn, despacho_zona dzon){
		String titulo = "update_despacho_zona";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setString(1,dzon.getC2nombre());
			pst.setInt(2,dzon.getC1iddespacho_zona());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + dzon.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + dzon.toString(), titulo);
		}
	}
	public void cargar_despacho_zona(Connection conn, despacho_zona dzon,int id){
		String titulo = "Cargar_despacho_zona";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				dzon.setC1iddespacho_zona(rs.getInt(1));
				dzon.setC2nombre(rs.getString(2));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + dzon.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + dzon.toString(), titulo);
		}
	}
	public void actualizar_tabla_despacho_zona(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_despacho_zona(tbltabla);
	}
	public void ancho_tabla_despacho_zona(JTable tbltabla){
		int Ancho[]={50,50};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
