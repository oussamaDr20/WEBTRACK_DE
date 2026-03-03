package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;










import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entities.Dossier;
import entities.Responsable;

public class RespoImpl implements IResponsable {


	

	@Override
	public boolean login(String mail, String mdp) {
		Connection conn=DBconnect.getConnection();
		boolean b=false;
		try {
			PreparedStatement st=conn.prepareStatement("select password from responsable where email=?");
			st.setString(1, mail);
		ResultSet rs=st.executeQuery();
		
		if(rs.next() && rs.getString("Password").equals(mdp)){
			b=true;
		}
		
	}catch (SQLException e) {
	}
		return b;
	}
	
	
	@Override
	public Responsable getRespo(String mail, String password) {
		Responsable c=new Responsable();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Responsable where email=? and  password=?");
			st.setString(1, mail);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c.setId_respo(rs.getInt("id_respo"));
				c.setNom(rs.getString("nom"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setPhoto(rs.getString("photo"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	
	@Override
	public void addRespo(Responsable d) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("insert into Responsable values (null,?,?,?,?)");

			st.setString(1, d.getNom());
			st.setString(2, d.getEmail());
			st.setString(3, d.getPassword());
			st.setString(4, d.getPhoto());
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public List<Responsable> ListRespo() {
		List<Responsable> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Responsable");  
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Responsable c=new Responsable();
				c.setId_respo(rs.getInt("id_respo"));
				c.setNom(rs.getString("nom"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setPhoto(rs.getString("photo"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}


	@Override
	public void deleteRespo(String idRespo) {
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("delete from Responsable where id_respo=? ");
			st.setInt(1,Integer.parseInt(idRespo));
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public Responsable getRespo(String idRespo) {
		Responsable c=new Responsable();
		Connection conn=DBconnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement("select * from Responsable where id_respo=?");
			int chercher = Integer.parseInt(idRespo);
			st.setInt(1, chercher);
			ResultSet rs=st.executeQuery();
			if(rs.next()){
				c.setId_respo(rs.getInt("id_respo"));
				c.setNom(rs.getString("nom"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setPhoto(rs.getString("photo"));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}


	@Override
	public void editRespo(Responsable nouveauRespo) {
		Connection conn=DBconnect.getConnection(); 
		try {
			PreparedStatement st=conn.prepareStatement("UPDATE Responsable SET nom = ? , email = ? , password = ? , photo = ? where id_respo = ?");
			st.setString(1, nouveauRespo.getNom());
			st.setString(2, nouveauRespo.getEmail());
			st.setString(3, nouveauRespo.getPassword());
			st.setString(4, nouveauRespo.getPhoto());
			st.setInt(5, nouveauRespo.getId_respo());
			st.executeUpdate();
			st.close();
			}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	@Override
	public void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }


}


	@Override
	public List<Responsable> ListRespoMessage(int idD) {
		List<Responsable> cmd = new ArrayList();
		Connection conn=DBconnect.getConnection();
		
		try {
			PreparedStatement st=conn.prepareStatement("select * from Responsable where id_respo IN (select id_respo from Tache where id_doc = ?)");  
			st.setInt(1, idD);
			ResultSet rs=st.executeQuery();
			while(rs.next()){
				Responsable c=new Responsable();
				c.setId_respo(rs.getInt("id_respo"));
				c.setNom(rs.getString("nom"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setPhoto(rs.getString("photo"));
				cmd.add(c);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmd;
	}



}
