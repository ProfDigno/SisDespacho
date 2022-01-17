	package FORMULARIO.DAO;
	import BASEDATO.EvenConexion;
	import FORMULARIO.ENTIDAD.item_pais_ciudad;
	import Evento.JasperReport.EvenJasperReport;
	import Evento.Jtable.EvenJtable;
	import Evento.Mensaje.EvenMensajeJoptionpane;
	import Evento.Fecha.EvenFecha;
	import java.sql.ResultSet;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import javax.swing.JTable;
public class DAO_item_pais_ciudad {
	EvenConexion eveconn = new EvenConexion();
	EvenJtable evejt = new EvenJtable();
	EvenJasperReport rep = new EvenJasperReport();
	EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
	EvenFecha evefec = new EvenFecha();
	private String mensaje_insert = "ITEM_PAIS_CIUDAD GUARDADO CORRECTAMENTE";
	private String mensaje_update = "ITEM_PAIS_CIUDAD MODIFICADO CORECTAMENTE";
	private String sql_insert = "INSERT INTO item_pais_ciudad(iditem_pais_ciudad,fk_idtercero_pais,fk_idtercero_ciudad) VALUES (?,?,?);";
	private String sql_update = "UPDATE item_pais_ciudad SET fk_idtercero_pais=?,fk_idtercero_ciudad=? WHERE iditem_pais_ciudad=?;";
	private String sql_select = "SELECT iditem_pais_ciudad,fk_idtercero_pais,fk_idtercero_ciudad FROM item_pais_ciudad order by 1 desc;";
	private String sql_cargar = "SELECT iditem_pais_ciudad,fk_idtercero_pais,fk_idtercero_ciudad FROM item_pais_ciudad WHERE iditem_pais_ciudad=";
	public void insertar_item_pais_ciudad(Connection conn, item_pais_ciudad ipaciu){
		ipaciu.setC1iditem_pais_ciudad(eveconn.getInt_ultimoID_mas_uno(conn, ipaciu.getTb_item_pais_ciudad(), ipaciu.getId_iditem_pais_ciudad()));
		String titulo = "insertar_item_pais_ciudad";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_insert);
			pst.setInt(1,ipaciu.getC1iditem_pais_ciudad());
			pst.setInt(2,ipaciu.getC2fk_idtercero_pais());
			pst.setInt(3,ipaciu.getC3fk_idtercero_ciudad());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_insert + "\n" + ipaciu.toString(), titulo);
			evemen.guardado_correcto(mensaje_insert, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_insert + "\n" + ipaciu.toString(), titulo);
		}
	}
	public void update_item_pais_ciudad(Connection conn, item_pais_ciudad ipaciu){
		String titulo = "update_item_pais_ciudad";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql_update);
			pst.setInt(1,ipaciu.getC2fk_idtercero_pais());
			pst.setInt(2,ipaciu.getC3fk_idtercero_ciudad());
			pst.setInt(3,ipaciu.getC1iditem_pais_ciudad());
			pst.execute();
			pst.close();
			evemen.Imprimir_serial_sql(sql_update + "\n" + ipaciu.toString(), titulo);
			evemen.modificado_correcto(mensaje_update, true);
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_update + "\n" + ipaciu.toString(), titulo);
		}
	}
	public void cargar_item_pais_ciudad(Connection conn, item_pais_ciudad ipaciu,int id){
		String titulo = "Cargar_item_pais_ciudad";
		try {
			ResultSet rs=eveconn.getResulsetSQL(conn,sql_cargar+id, titulo);
			if(rs.next()){
				ipaciu.setC1iditem_pais_ciudad(rs.getInt(1));
				ipaciu.setC2fk_idtercero_pais(rs.getInt(2));
				ipaciu.setC3fk_idtercero_ciudad(rs.getInt(3));
				evemen.Imprimir_serial_sql(sql_cargar + "\n" + ipaciu.toString(), titulo);
			}
		} catch (Exception e) {
			evemen.mensaje_error(e, sql_cargar + "\n" + ipaciu.toString(), titulo);
		}
	}
	public void actualizar_tabla_item_pais_ciudad(Connection conn,JTable tbltabla){
		eveconn.Select_cargar_jtable(conn, sql_select, tbltabla);
		ancho_tabla_item_pais_ciudad(tbltabla);
	}
	public void ancho_tabla_item_pais_ciudad(JTable tbltabla){
		int Ancho[]={33,33,33};
		evejt.setAnchoColumnaJtable(tbltabla, Ancho);
	}
}
