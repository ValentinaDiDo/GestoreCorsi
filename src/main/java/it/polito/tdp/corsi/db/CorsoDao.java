package it.polito.tdp.corsi.db;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public class CorsoDao {

	public List<Corso> getCorsiByPeriodo(int periodo) {
	/*	String sql = "select * "
				+ "from corso "
				+ "where pd = ?";//RICORDA SPAZIO A FINE RIGA PERCHE' SENNO' LE PAROLE VENGONO ATTACCATE
				
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, periodo); //indicizzazione parte da 1 e NON da 0
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) { //finché rimane true, sto leggendo una riga
				Corso c = new Corso(rs.getString("codins"), rs.getString("crediti"),rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
			rs.close();
			st.close();
			*/
		String sql = "select * "
				+ "from corso "
				+ "where pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,periodo);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
			}
			
			st.close();
			rs.close();
			conn.close();
			return result;
		}catch (SQLException s) {
			s.printStackTrace();
			System.out.println("Errore DAO");
		}
		return null;
	}
	
	public Map<Corso, Integer> getIscritti(int periodo){
		String sql = "select c.codins, c.crediti, c.nome, c.pd, count(*) as n "+
					"from corso c, iscrizione i "+"where c.codins = i.codins and c.pd = 1 "
				+"group by c.codins, c.crediti, c.nome, c.pd";
		Map<Corso,Integer> result = new HashMap<Corso,Integer>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, periodo); //1 perché i parametri non partono da zero ma partono da 1
			ResultSet rs = st.executeQuery();
			
			while(rs.next()==true) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.put(c, rs.getInt("n")); //perché ho rideifnito il count con la n!
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DAO");
		}
		return null;
	}
}
