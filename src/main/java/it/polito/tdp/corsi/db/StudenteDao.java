package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDao {

	public List<Studente> getStudentiByCorso(String codins) {
		String sql = "select s.matricola, s.cognome, s.nome, s.CDS "
				+ "from studente s, iscrizione i "
				+ "where s.matricola = i.matricola AND i.codins = ?";
		List<Studente> result = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				result.add(new Studente(rs.getInt("matricola"),
						rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Divisione> getDivisioneStudenti(String codins){
		String sql = "select s.CDS, COUNT(*) as n "
				+ "from studente s, iscrizione i "
				+ "where s.matricola = i.matricola AND i.codins = ? AND s.cds <> '' "
				+ "group by s.CDS";
		List<Divisione> result = new ArrayList<Divisione>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				result.add(new Divisione(rs.getString("CDS"),
						rs.getInt("n")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return result;
		} catch(SQLException e) {
			System.err.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}
}


/*package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.corsi.model.Divisione;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDao {

	public List<Studente> getStudentiByCorso(String codins) {
		String sql = "select s.matricola, s.cognome, s.nome, s.CDS "
		+ "from studente s, iscrizione i "
		+"where s.matricola = i.matricola and i.codins = ?";
		
		List<Studente> result = new ArrayList<Studente> ();		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins); //1 perché i parametri non partono da zero ma partono da 1
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			st.close();
			rs.close();
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DAO");
		}
		return null;
	}
	
	public List<Divisione> getDivisioneStudente(String codins){
		List<Divisione> result = new ArrayList<Divisione>();
		
		String sql ="select s.CDS, count(*) as n "
				+ "from studente s, iscrizione i "
				+ "where s.matricola = i.matricola and i.codins = ? ans s.CDS <> ''  "
				+ "group by s.CDS";
			
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codins); //1 perché i parametri non partono da zero ma partono da 1
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.add(new Divisione(rs.getString("CDS"), rs.getInt("n")));
			}
			st.close();
			rs.close();
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Errore DAO");
		}
		return null ;
	}
}*/
