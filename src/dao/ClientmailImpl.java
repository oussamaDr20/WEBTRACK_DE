package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Clientmail;

public class ClientmailImpl implements IClientmail{

	@Override
	public void addCm(Clientmail cm) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into clientmail values (null,?,?)");

			st.setInt(1, cm.getId_doc());
			st.setString(2, cm.getEmail());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public String getMail(int id) {
		String df=null;
		Connection conn=DBconnect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("select email as mail FROM clientmail where id_doc = "+id);
			while(rs.next()){
				df = rs.getString("mail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

	@Override
	public int getIdCM(String email) {
		int df=0;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select id_cm as idmail FROM clientmail where email = ?");
			st.setString(1, email);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				df = rs.getInt("idmail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

	@Override
	public void deleteEmail(String idClient) {
		Connection conn=DBconnect.getConnection();
		int id = Integer.parseInt(idClient);
		try {
			PreparedStatement st=conn.prepareStatement("delete from clientmail where id_cm=(select id_clientmail from client where id_client = ?) ");
			st.setInt(1,id);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateMail(int id_cm, String email) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE clientmail SET email = ? where id_cm = ?");
			st.setString(1, email);
			st.setInt(2, id_cm);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String getMailClient(int id) {
		String df=null;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select email as mail FROM clientmail where id_cm = (select id_clientmail from client where id_client =?)");
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				df = rs.getString("mail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

	@Override
	public void editMail(int idM, String email) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE clientmail SET email = ? where id_cm = ?");
			st.setString(1, email);
			st.setInt(2, idM);
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String getMailClient(String nom_cl) {
		String df=null;
		Connection conn=DBconnect.getConnection();
		try {  
			PreparedStatement st=conn.prepareStatement("select email as mail FROM clientmail where id_doc in (select id_doc from dossier where nom_cl ="+nom_cl+")");
			st.setString(1, nom_cl);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				df = rs.getString("mail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

	@Override
	public int getIdCMDoc(int id_doc) {
		int df=0;
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select id_cm as idmail FROM clientmail where id_doc = ?");
			st.setInt(1, id_doc);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				df = rs.getInt("idmail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}return df;
		
	}

}
