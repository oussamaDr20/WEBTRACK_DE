package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientImpl;
import dao.ClientmailImpl;
import dao.DossierImpl;
import dao.EndedImpl;
import dao.IClient;
import dao.IClientmail;
import dao.IDossier;
import dao.IEnded;
import dao.IMessage;
import dao.IResponsable;
import dao.IST;
import dao.ISaved;
import dao.ITache;
import dao.MessageImpl;
import dao.RespoImpl;
import dao.STIMPL;
import dao.SavedImpl;
import dao.TacheImpl;
import entities.Client;
import entities.Clientmail;
import entities.Dossier;
import entities.Messenger;
import entities.Ended;
import entities.Responsable;
import entities.Saved;
import entities.SousTache;
import entities.Tache;




@WebServlet({"/Acceuil","/Admin","/Respo","/tacheApprouver","/tacheEnCours","/tacheFinis",
	"/ajoutDoc","/ajoutRespo","/ajoutTache","/login","/deconnexion",
	"/progression","/deleteDoc","/deleteRespo","/editRespo","/editDoc" , 
	"/next","/approuve","/addST","/viewST","/rollback","/tacheVenir","/contact",
	"/viewTache","/deleteTache","/viewSTAdmin","/addSTAdmin","/editTache","/deleteSTdbsh",
	"/messenger","/deleteSTAdmin","/listMessenger","/deleteMsgDbl3ani","/loginClient", "/prog",
	"/progHeader","/registration","/premium","/editClient","/deleteClient","/acceuilClient",
	"/dossierClient","/profile","/editProfile","/endPremium","/saved","/deleteSaved","/addSaved",
	"/details","/CU","/Propos","/AdminDoc","/AdminRespo","/AdminClient"})

public class TrackingServlet extends HttpServlet { 
	
		
	
	private ITache tacheDAO;
	private IResponsable respoDAO;
	private IDossier dossierDAO;
	private IEnded endedDAO;
	private IST STDAO;
	private IClientmail clientmailDAO;
	private IClient clientDAO;
	private IMessage messageDAO;
	private ISaved savedDAO;
	HttpSession session;
	
	@Override
	public void init() throws ServletException {
		tacheDAO = new TacheImpl();
		respoDAO = new RespoImpl();
		dossierDAO = new DossierImpl();
		endedDAO = new EndedImpl();
		STDAO = new STIMPL();
		clientmailDAO = new ClientmailImpl();
		clientDAO = new ClientImpl();
		messageDAO = new MessageImpl();
		savedDAO = new SavedImpl();
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		try{

		
		
		if(request.getServletPath().equals("/Admin")) {
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			
			request.setAttribute("dossier", dossiers);
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/AdminDoc")) {
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			
			request.setAttribute("dossier", dossiers);
			
			request.getRequestDispatcher("WEB-INF/adminDoc.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/AdminRespo")) {
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);
			
			request.getRequestDispatcher("WEB-INF/adminRespo.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/AdminClient")) {
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);
			
			request.getRequestDispatcher("WEB-INF/adminClient.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/registration")) {
			String id_doc = request.getParameter( "id_doc" );
			request.setAttribute("id_doc", id_doc);
			String email = clientmailDAO.getMail(Integer.parseInt(id_doc));
			request.setAttribute("email", email);
			Dossier d =dossierDAO.getDoc(id_doc);
			String nom = d.getNom_cl();
			request.setAttribute("nom", nom);
			request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
		}
		

		
		if(request.getServletPath().equals("/addSaved")) {
			String id_doc = request.getParameter( "id_doc" );
			String id_client = request.getParameter( "id_client" );
			int id=Integer.parseInt(id_client);
			int idD=Integer.parseInt(id_doc);
			Dossier dd=dossierDAO.getDosser(idD);
			String nomClient = dd.getNom_cl();
			String typa=dd.getType();
			savedDAO.addSaved(id_doc,id_client,nomClient,typa);
			//charger la liste
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			request.setAttribute("succes", "Ordner gut gespeichert");
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/deleteSaved")) {
			String id_doc = request.getParameter( "id_doc" );
			String id_client = request.getParameter( "id_client" );
			int id=Integer.parseInt(id_client);
			savedDAO.deleteSaved(id_doc,id_client);
			//charger la liste
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			request.setAttribute("succes", "Ordner gut geloscht");
			request.setAttribute("dossier", dossiers);
			int totalSaved = savedDAO.countSaved(id);
			request.setAttribute("totalSaved", totalSaved);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("/editProfile")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			request.setAttribute("client", clyan);
			request.getRequestDispatcher("WEB-INF/editProfile.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("/CU")) {
			request.getRequestDispatcher("WEB-INF/CU.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("Propos")) {
			request.getRequestDispatcher("WEB-INF/Propos.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/saved")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			int id = clyan.getId_client();
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			int totalSaved = savedDAO.countSaved(id);
			request.setAttribute("totalSaved", totalSaved);
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/Acceuil")) {
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
		

		if(request.getServletPath().equals("/endPremium")) {
			String id_client = request.getParameter( "id_client" );
			clientDAO.editPrem(id_client);
			Client clyan = clientDAO.getClient(id_client);
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			
			request.setAttribute("succes", "Sie sind kein PREMIUM-Kunde mehr lol");
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/profile")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			request.setAttribute("client", clyan);
			String email = clientmailDAO.getMailClient(clyan.getId_client());
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/acceuilClient")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/progHeader")) {
			request.getRequestDispatcher("WEB-INF/progHeader.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/Acceuil")) {
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/loginClient")) {
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deleteMsgDbl3ani")) {
			String id_mess = request.getParameter( "id_mess" );
			String msg = "Message Supprimé!";
			Messenger m = messageDAO.getMsg(id_mess);
			messageDAO.deleteDbl3ani(id_mess,msg);
			int idD = m.getId_doc();
			
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateur.getId_respo();
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", idD);
			request.setAttribute("id", id_respooo);
			
			
			
			//chargement de liste Dossier
			int idR = utilisateur.getId_respo();
			Responsable respoo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respoo);
			//Header listes
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
			
		}
		
		
		if(request.getServletPath().equals("/listMessenger")) {
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Dossier> doc =  dossierDAO.ListDossierRespo(id_respooo);
			request.setAttribute("dossier", doc);
			//chargement de liste Dossier
			
			Responsable respo = respoDAO.getRespo(String.valueOf(id_respooo));
			request.setAttribute("responsable", respo);
			//Header listes
			
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/listMessenger.jsp").forward(request, response);
		}
		
		
		
		if(request.getServletPath().equals("/messenger")) {
			String id_doc = request.getParameter( "id_doc" );
			int idD = Integer.parseInt(id_doc);
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateur.getId_respo();
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", id_doc);
			request.setAttribute("id", id_respooo);
			
			
			//chargement de liste Dossier
			int idR = utilisateur.getId_respo();
			Responsable respoo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respoo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
		}
		
		
		

		if(request.getServletPath().equals("/viewSTAdmin")) {
			String id_tache = request.getParameter( "idST" );
			List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
			int total = STDAO.NumST(Integer.parseInt(id_tache));
			request.setAttribute("total", total);
			request.setAttribute("tache", tachee);
			request.getRequestDispatcher("WEB-INF/viewSTAdmin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/addSTAdmin")) {
			String id_tache = request.getParameter( "idST" );
			Tache tachtacha = tacheDAO.getOneOneTache(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachtacha);
			request.getRequestDispatcher("WEB-INF/addSTAdmin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/viewST")) {
			String id_tache = request.getParameter( "idST" );
			List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachee);
			int total = STDAO.NumST(Integer.parseInt(id_tache));
			request.setAttribute("total", total);
			//chargement de liste Dossier
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/viewST.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/viewTache")) {
			String id_doc = request.getParameter( "idST" );
			List<Tache> tachee =  tacheDAO.getTache(id_doc);
			request.setAttribute("tache", tachee);
			request.setAttribute("id", id_doc);

			request.getRequestDispatcher("WEB-INF/viewTaches.jsp").forward(request, response);
		}
		

		if(request.getServletPath().equals("/deleteTache")) {
			String idTache = request.getParameter( "id_d" );
			Tache tacha = tacheDAO.getOneOneTache(Integer.parseInt(idTache));
			tacheDAO.deleteTache(idTache);
			request.setAttribute("succes", "Der Fleck ist gut entfernt worden");
			int id = tacha.getId_doc();
			List<Tache> tachee =  tacheDAO.getTache(String.valueOf(id));
			request.setAttribute("tache", tachee);
			request.setAttribute("id",  id );

			request.getRequestDispatcher("WEB-INF/viewTaches.jsp").forward(request, response);
			
		} 
		
		
		if(request.getServletPath().equals("/addST")) {
			String id_tache = request.getParameter( "idST" );
			Tache tachtacha = tacheDAO.getOneOneTache(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachtacha);
			//chargement de liste Dossier
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutDoc")) {
			request.getRequestDispatcher("WEB-INF/addDoc.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutRespo")) {
			request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutTache")) {
			String id_doc = request.getParameter( "id_doc" );
			List<Dossier> dossier =  dossierDAO.ListDossier();
			request.setAttribute("dossier", dossier);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			request.setAttribute("id_doc", id_doc);	
			request.getRequestDispatcher("WEB-INF/addTache.jsp").forward(request, response);
		}
		
		//-------------------------------------------
		
		if(request.getServletPath().equals("/Respo")) {
			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			session.setAttribute("session", utilisateurConnecte);
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/premium")) {
			String id_client = request.getParameter( "id_client" );
			
			request.setAttribute("id_client", id_client);
			request.getRequestDispatcher("WEB-INF/premium.jsp").forward(request, response);
		}
		

		
		
		if(request.getServletPath().equals("/tacheApprouver")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			request.setAttribute("responsable", utilisateur);
			List<Tache> tachee =  tacheDAO.ListTacheRespoApprouver(id_respo);
			request.setAttribute("tache", tachee);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/approuver.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/tacheEnCours")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Tache> tach =  tacheDAO.ListTacheRespoEnCours(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/enCours.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/tacheVenir")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Tache> tach =  tacheDAO.ListVenir(id_respo);
			request.setAttribute("tache", tach);
			
			
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			int totalVenir = tacheDAO.TotalVenir(id_respooo);
			request.setAttribute("totalVenir", totalVenir);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/venir.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/tacheFinis")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Ended> tach =  endedDAO.ListTachat(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			int totalEnded = endedDAO.countEnded(id_respo);
			request.setAttribute("totalEnded", totalEnded);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/finis.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deconnexion")) {
			session = request.getSession();
			session.invalidate();
			request.setAttribute("succes", "Sie haben sich ausgeloggt ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/login")) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/deleteSTAdmin")) {
			String idST = request.getParameter("idST");
			STDAO.deleteSTdbsh(Integer.parseInt(idST));
			request.setAttribute("succes", "Die Bemerkung wurde gelöscht");
			//charger les listes
			

			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			
			request.setAttribute("dossier", dossiers);
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		
		}
		
		
		if(request.getServletPath().equals("/deleteSTdbsh")) {
			String idST = request.getParameter("idST");
			STDAO.deleteSTdbsh(Integer.parseInt(idST));
			request.setAttribute("succes", "Die Bemerkung wurde gelöscht.");
			//charger les listes

			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deleteDoc")) {
			String idDossiere = request.getParameter( "id_d" );
			//supprimer le dossier
			dossierDAO.deleteDoc(idDossiere);
			//supprimer les taches du dossier
			tacheDAO.deleteDocTache(idDossiere);
			//supprimer les sous taches du dossier
			STDAO.deleteDocST(idDossiere);
			
			request.setAttribute("succes", "Die Datei wurde gelöscht");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/details")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			
			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
//			int totalTime=left+elapsed;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved); 
			request.getRequestDispatcher("WEB-INF/details.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/details.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		
		
		if(request.getServletPath().equals("/progression")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			int id_cm=	clientmailDAO.getIdCMDoc(id_doc);

			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			boolean member = clientDAO.membre(id_cm);
			request.setAttribute("member", member);
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved);  
			request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		

		if(request.getServletPath().equals("/deleteRespo")) {
			String idRespo = request.getParameter( "id_r" );
			respoDAO.deleteRespo(idRespo);
			Responsable resp = respoDAO.getRespo(idRespo);
			request.setAttribute("succes", "Die verantwortliche Person wurde entfernt.");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
}
		
		if(request.getServletPath().equals("/editRespo")) {
			String idRespo = request.getParameter( "id_r" );
			Responsable respo = respoDAO.getRespo(idRespo);
			request.setAttribute("responsable", respo);
			request.getRequestDispatcher("WEB-INF/editRespo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editClient")) {
			String idClient = request.getParameter( "id_r" );
			Client clyan = clientDAO.getClient(idClient);
			request.setAttribute("client", clyan);
			String email = clientmailDAO.getMailClient(Integer.parseInt(idClient));
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/editClient.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/deleteClient")) {
			String idClient = request.getParameter( "id_r" );
			//supprimer le client
			clientDAO.deleteClient(idClient);
			//supprimer l'email de client
			clientmailDAO.deleteEmail(idClient);
			
			request.setAttribute("succes", "Der Kunde wurde gelöscht");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editTache")) {
			String idTache = request.getParameter( "id_d" );
			Tache tachtacha = tacheDAO.getOneOneTache(idTache);
			request.setAttribute("tache", tachtacha);
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			request.getRequestDispatcher("WEB-INF/editTache.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editDoc")) {
			String idDoc = request.getParameter( "id_d" );
			Dossier doss = dossierDAO.getDoc(idDoc);
			request.setAttribute("dossier", doss);
			request.getRequestDispatcher("WEB-INF/editDoc.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/next")) {
		
			String idDoc = request.getParameter( "id_doc" );
			String idTache = request.getParameter( "id_tache" );
			String date_Debut = request.getParameter( "date_debut" );
			
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			int idT=Integer.parseInt(idTache);
			int idD=Integer.parseInt(idDoc);
			
			//methode responsible bach tji la tache li mor hadi ( selon date de debut )
			List<Tache> taches =  tacheDAO.ListTachesAAprouveDossier(idT,idD, date_Debut);
			// Incrementation du numTacheEnCours de la tache
			Tache tachtacha = tacheDAO.getOneOneTache(idT);
			// turn = false
			tacheDAO.UpdateTurn(idT,false,true);
			int NTEC = tachtacha.getD_numTacheEnCour();
			int x = ++ NTEC;
			if(taches.isEmpty()) {
				request.setAttribute("succes", "Die letzte Aufgabe des Dossiers "+idD+" ist erfolgreich abgeschlossen worden.");
				tacheDAO.UpdateNTEC(idT,x);
				String RECIPIENT = clientmailDAO.getMail(idD);
				String USER_NAME = "Tracking.GmbH"; 
				 String PASSWORD = "Bachelor."; 

				        String from = USER_NAME;
				        String pass = PASSWORD;
				        String[] to = { RECIPIENT };
				        String subject = "Bekanntmachung . ";
				        String body = "Guten Tag Herr. '"+tachtacha.getD_nom_cl()+"'. Ihre Akte ist fertig!.     Team WEBTRACK";
				        
				respoDAO.sendFromGMail(from, pass, to, subject, body);
				
				String message = "Le traitement du Dossier de Mr."+tachtacha.getD_nom_cl()+" est Terminé";

				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(idD,0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
			} else {
			Tache tachtachatacha = taches.get(0);
			int idT2 = tachtachatacha.getId_tache();
			tacheDAO.UpdateNTEC(idT2,x); 
			//flux du tache entre les respos
			tacheDAO.UpdateTurn(idT,false,true);
			tacheDAO.UpdateTurn(idT2,true,false);
			request.setAttribute("succes", "Verarbeitung der Datei ("+idD+") ausgefüllt");
			//Bekanntmachung de reception
			Responsable nextRespo = respoDAO.getRespo(Integer.toString(tachtachatacha.getId_respo()));
			String RECIPIENT = nextRespo.getEmail();
			String USER_NAME = "Tracking.GmbH"; 
			 String PASSWORD = "Bachelor."; 

			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT };
			        String subject = "Bekanntmachung";
			        String body = "Sie haben einen neuen Fleck vom Typ '"+tachtachatacha.getD_type()+"' die genehmigt und abgeschlossen werden müssen. Letzter Termin am '"+tachtachatacha.getDateFin()+"' .";
			        
			respoDAO.sendFromGMail(from, pass, to, subject, body);
			String message = "Der Verantworliche "+ utilisateur.getNom() +"hat den Fleck "+idT+" beendet ";

			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(idD,0,message, null,Uhr);
			messageDAO.addMsgRespo(msg);
			}
			//stocker cette tache dans une table specifier pour stocker les taches terminé pour chaque 
			// respo (pour les afficher comme taches finis dans fnins.jsp) 
			Tache tacha = tacheDAO.getOneTache(idR,idT);
			endedDAO.add(tacha); 
			
			
			//chargement de liste Dossier
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(idR);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
			
	//		System.out.println("id tache = " + idTache);
		}
		
		if(request.getServletPath().equals("/approuve")) {
			String idTache = request.getParameter( "id_tache" );
			int idT=Integer.parseInt(idTache);
			Tache tachtacha = tacheDAO.getOneOneTache(idT);
			int idD=tachtacha.getId_doc();
			tacheDAO.UpdateValide(idT);
			request.setAttribute("succes", "Vous avez approuvé le Dossier ("+tachtacha.getId_doc()+")");
			request.setAttribute("erreur", "Rappel: Dernier délai: ("+tachtacha.getDateFin()+")");
			//chargement du responsable de la session et le envoyer 
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(idR);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			String message = "Der Verantwortliche "+ utilisateur.getNom() +" hat die Aufgabe : "+idT+" genehmigt";

			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(idD,0,message, null,Uhr);
			messageDAO.addMsgRespo(msg);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/rollback")) {
			String idDoc = request.getParameter( "id_doc" );
			String idTache = request.getParameter( "id_tache" );
			String date_Debut = request.getParameter( "date_debut" );
			
			request.setAttribute("idDoc", idDoc);
			request.setAttribute("idTache",idTache);
			request.setAttribute("date_Debut",date_Debut);

			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			request.setAttribute("responsable", utilisateurrrr);
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER

			
			request.getRequestDispatcher("WEB-INF/reclamer.jsp").forward(request, response);	
	}
		
//	} catch (Exception e) {
//		request.setAttribute("erreur", "Es wurde ein Fehler gemacht.");
//		request.getRequestDispatcher("WEB-INF/404.jsp").forward(request, response);
//	}
		
		}
	
//-----------------------------------------------------------------------------------------------------------------------------------------
 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try{
		
		if(request.getServletPath().equals("/loginClient")) {
			String exceptionContent = "Bei der Authentifizierung ist ein Fehler aufgetreten.";
			try{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				boolean status = false;
				status=clientDAO.login(email, password);
				if(status){
					session = request.getSession();
					int idC=clientmailDAO.getIdCM(email);
					String id =String.valueOf(idC);
					Client utilisateur = clientDAO.getClient(id, password);
					session.setAttribute("session", utilisateur);
					String nom_cl = utilisateur.getNom();
					List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
					for (Dossier dossier : dossiers) {
						String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
						String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
						int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
						int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
						int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
						String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
						dossier.setDateDebut(dateDebut);
						dossier.setDateFin(dateFin);
						dossier.setTotalTache(totalTache);
						dossier.setMaxTache(maxTache);
						dossier.setDureeTotal(dureeTotal);
						dossier.setRespo(respo);
						
					}
					request.setAttribute("client", utilisateur);
					request.setAttribute("dossier", dossiers);
					request.setAttribute("succes", "Willkommen "+ utilisateur.getNom() + "! Sie haben sich authentifiziert"); 
					request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);

				}
				else{
				exceptionContent = "login / mdp incorrect";
				throw new Exception(exceptionContent);
				}
			
			} catch (Exception e) {
				request.setAttribute("erreur", exceptionContent);	
				request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
			
			}
		}
		
		if(request.getServletPath().equals("/messenger")) {
			String envoye = request.getParameter( "msg" );
			String id_respo = request.getParameter( "id_respo" );
			String id_doc = request.getParameter( "id_doc" );
			Blob a = null;
//			System.out.println(envoye+"/"+ id_respo+"/"+id_doc);
			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(Integer.parseInt(id_doc),Integer.parseInt(id_respo),envoye, a,Uhr);
			messageDAO.addMsgRespo(msg);
			int idD = Integer.parseInt(id_doc);
			//---------------------------------------------------------------------------
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}

			int id_respooo = Integer.parseInt(id_respo);
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", id_doc);
			request.setAttribute("id", id_respooo);
			
			//chargement de liste Dossier
			Responsable respoo = respoDAO.getRespo(String.valueOf(id_respooo));
			request.setAttribute("responsable", respoo);
			//Header listes
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
		}
	
		
		if(request.getServletPath().equals("/rollback")) {
			String idDoc = request.getParameter( "idDoc" );
			String idTache = request.getParameter( "idTache" );
			String date_Debut = request.getParameter( "date_Debut" );
			String motif = request.getParameter("motif");
			int idT=Integer.parseInt(idTache);
			int idD=Integer.parseInt(idDoc);


			//methode responsible bach tji la tache li 9bel  hadi ( selon date de debut )
			List<Tache> taches =  tacheDAO.ListTachesRollback(idT,idD, date_Debut);
			if(taches.isEmpty()) {
				request.setAttribute("erreur", "Kein früherer Verantwortlicher für diese Aufgabe");
			} else {
			tacheDAO.UpdateRollback(idT);
			Tache tacha = taches.get(0);
			int idT2 = tacha.getId_tache();
			//Enregistrer le motif comme Remarque/Sous-tache de la tache precedente
			SousTache ST = new SousTache(idT2,motif);
			
			STDAO.addST(ST);
			STDAO.deleteST(idT2);
			//flux du tache entre les respos
			tacheDAO.UpdateTurn(idT,false,false);
			tacheDAO.UpdateTurn(idT2,true,false);
			request.setAttribute("succes", "Behauptung aufgestellt!");
			//ajout de 2 jours
			String dateFin = tacheDAO.getDateDerniereTache(idD);
			Tache akhirTache = tacheDAO.getLastTache(dateFin);
			int idAkhirTache = akhirTache.getId_tache();
			String dt = akhirTache.getDateFin();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 2);  
			dt = sdf.format(c.getTime()); 
			tacheDAO.editDateFin(idAkhirTache,dt);
			//Bekanntmachung de retour
			Responsable previousRespo = respoDAO.getRespo(Integer.toString(tacha.getId_respo()));
			String RECIPIENT = previousRespo.getEmail();
			String USER_NAME = "Tracking.GmbH"; 
			 String PASSWORD = "Bachelor."; 
			 
			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT };
			        String subject = "Bekanntmachung . ";
			        String body = "Bonjour Mr. "+previousRespo.getNom()+".Der Verantwortliche für die folgende Aufgabe '"+tacha.getLibelle()+"' ließ sie zurückkehren  "
			        		+ " aus dem Grund: '"+motif+"'. Letzter Termin am '"+tacha.getDateFin()+"'.";
			        
			respoDAO.sendFromGMail(from, pass, to, subject, body);
			//------  Client
			int idD2 = tacha.getId_doc();
			String email2 = clientmailDAO.getMail(idD2);
			String[] to2 = { email2 };
			String subject2 = "Bekanntmachung";
	        String body2 = "Guten Tag Herr. "+tacha.getD_nom_cl()+". Es wird eine Verzögerung bei der Verarbeitung Ihrer Datei vom Typ '"+tacha.getD_type()+
	        		"'. Bitte nehmen Sie sich einen Zeitraum von 2 Tagen, der zum Enddatum der erwarteten Behandlung hinzukommt. '"+tacha.getDateFin()+"'"
	        				+ ". Wir danken Ihnen für Ihr Verständnis.           Team WEBTRACK";
	        

	Tache tachtacha = tacheDAO.getOneOneTache(idTache);
	int id_resp = tachtacha.getId_respo();
	Responsable respo = respoDAO.getRespo(String.valueOf(id_resp));
	String message = "Der Manager "+ respo.getNom() +" drehte den Fleck dreht den Fleck von Herr."+tacha.getD_nom_cl()+"  aus dem Grund: '"+motif+"'";
	Calendar rightNow = Calendar.getInstance();
	int hour = rightNow.get(Calendar.HOUR_OF_DAY);
	int minute = rightNow.get(Calendar.MINUTE);
	String Uhr= "h";
	String hr = String.valueOf(hour);
	String min = String.valueOf(minute);
	if(minute<10){
		Uhr= hr+":0"+min;
	}else{
		Uhr= hr+":"+min;
	}
	Messenger msg = new Messenger(idD2,0,message, null,Uhr);
	messageDAO.addMsgRespo(msg);
			}
			
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			request.setAttribute("responsable", utilisateurrrr);
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tache", tach);
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);	
	}
		
		if(request.getServletPath().equals("/progression")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			int id_cm=	clientmailDAO.getIdCMDoc(id_doc);

			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			boolean member = clientDAO.membre(id_cm);
			request.setAttribute("member", member);
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved);  
			request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		

		
		if(request.getServletPath().equals("/editProfile")) {
			try {
				String id_client = request.getParameter("id_client");	
				String adresse = request.getParameter("adresse");	
				String tel = request.getParameter("tel");
				String password = request.getParameter("password");
				int id = Integer.parseInt(id_client);
				clientDAO.editProfile(id,adresse,tel,password);
				Client clyan=clientDAO.getClient(id_client);
				request.setAttribute("client", clyan);
				String email = clientmailDAO.getMailClient(clyan.getId_client());
				request.setAttribute("email", email);
				request.setAttribute("succes", "Ihre Daten wurden geändert.");
				request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/editProfile.jsp").forward(request, response);
			} 	
		}
		 
		
		if(request.getServletPath().equals("/ajoutDoc")) {
			try {
				String idDoc = request.getParameter("id_doc");	
				String nomCl = request.getParameter("nom_cl");
				String emailCl = request.getParameter("email_cl");
				String type = request.getParameter("type"); 
				
				Dossier nouveauDossier = new Dossier(Integer.parseInt(idDoc),nomCl,type,0);
				dossierDAO.addDossier(nouveauDossier);
				
				Clientmail cm = new Clientmail(Integer.parseInt(idDoc),emailCl);
				clientmailDAO.addCm(cm);
				 


				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String email = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(email);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", " Die Ordner nummer "+idDoc+" wurde hinzugefügt." );
				String message = " Der Ordner Nummer "+idDoc+" wird erstellt";
				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(Integer.parseInt(idDoc),0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addDoc.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/addSTAdmin")) {
			try{
				String id_tache = request.getParameter("id_tache");
				String libelle = request.getParameter("libelle"); 
				
				SousTache nouvelleST = new SousTache(Integer.parseInt(id_tache),libelle);
				STDAO.addST(nouvelleST);
				
				List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
				request.setAttribute("tache", tachee);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/viewSTAdmin.jsp").forward(request, response);
			}
	}
		
		if(request.getServletPath().equals("/addST")) {
		try{
			String id_tache = request.getParameter("id_tache");
			String libelle = request.getParameter("libelle"); 
			
			SousTache nouvelleST = new SousTache(Integer.parseInt(id_tache),libelle);
			STDAO.addST(nouvelleST);
			
			//affichage des listes
			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			request.setAttribute("succes", "Ihre Bemerkung wurde gut ergänzt");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
		} finally {
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}	
		}
		
		if(request.getServletPath().equals("/ajoutRespo")) {
			try {
				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");	
				String photo = request.getParameter("photo");
				
				Responsable nouveauRespo = new Responsable(nom, email,password,photo );
				respoDAO.addRespo(nouveauRespo);

				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String emaill = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(emaill);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", "Le Responsable "+nouveauRespo.getNom()+" a été bien ajouté");	
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/ajoutTache")) {
			try {
				String id_respofull = request.getParameter("id_respo");
				String id_doc = request.getParameter("id_doc");
				String libelle = request.getParameter("libelle");	
				String dateDebut_ = request.getParameter("dateDebut");
				String dateFin_ = request.getParameter("dateFin");
				Dossier d1 = dossierDAO.getDoc(id_doc);
				String d_nom_cl = d1.getNom_cl();
				String d_type = d1.getType();
				Boolean b = false;
				
				//date difference
				int duree=tacheDAO.DateDifference(dateDebut_, dateFin_);  
				
				int i = id_respofull.indexOf(' ');
				String id_respo = id_respofull.substring(0, i);
				
				
				boolean a = false;
				int id = Integer.parseInt(id_doc);
				int deutschland = tacheDAO.exist(id);
				if(deutschland == 0){
					a=true;
					
				    Responsable respo = respoDAO.getRespo(id_respo);
					String RECIPIENT = respo.getEmail();
					String USER_NAME = "Tracking.GmbH";
					 String PASSWORD = "Bachelor."; 
					
					        String from = USER_NAME;
					        String pass = PASSWORD;
					        String[] to = { RECIPIENT };
					        String subject = "Bekanntmachung . ";
					        String body = "Guten Tag, Herr. "+respo.getNom()+". Sie haben die erste Aufgabe der Datei Nummer '"+id_doc+" vom Typ '"+d_type+"' "
					        		+ "' zu genehmigen und zu vervollständigen. Letzter Termin die '"+dateFin_+"' .";
					respoDAO.sendFromGMail(from, pass, to, subject, body);
					String message = "Die erste Aufgabe der Datei "+id+" ist hinzugefügt. (Verantwortlich: "+respo.getNom()+") ";

					Calendar rightNow = Calendar.getInstance();
					int hour = rightNow.get(Calendar.HOUR_OF_DAY);
					int minute = rightNow.get(Calendar.MINUTE);
					String Uhr= "h";
					String hr = String.valueOf(hour);
					String min = String.valueOf(minute);
					if(minute<10){
						Uhr= hr+":0"+min;
					}else{
						Uhr= hr+":"+min;
					}
					Messenger msg = new Messenger(id,0,message, null,Uhr);
					messageDAO.addMsgRespo(msg);
				}
				
				Tache nouvelleTache = new Tache(Integer.parseInt(id_respo) ,id,b
							,libelle,dateDebut_,dateFin_,d_nom_cl,d_type,0,a,duree);
				tacheDAO.addTache(nouvelleTache);
				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String email = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(email);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", " Der Fleck "+libelle+" ist gut hinzugefügt worden.");
				Responsable respoo = respoDAO.getRespo(id_respo);
				if(deutschland != 0){
				String message = "Die Datei Nummer "+id+" hat eine neue Aufgabe. (Verantwortlich: "+respoo.getNom()+") ";

				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(id,0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
				}
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addTache.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/login")) {
			String exceptionContent = "Bei der Authentifizierung ist ein Fehler aufgetreten.";
			try{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				if(email.equals("aymen@bachelor.bgi") && password.equals("000")){
					// dossiers
					List<Dossier> dossiers =  dossierDAO.ListDossier();
					for (Dossier dossier : dossiers) {
						String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
						String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
						int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
						int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
						int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
						String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
						dossier.setDateDebut(dateDebut);
						dossier.setDateFin(dateFin);
						dossier.setTotalTache(totalTache);
						dossier.setMaxTache(maxTache);
						dossier.setDureeTotal(dureeTotal);
						dossier.setRespo(respo);
						
					}
					
					request.setAttribute("dossier", dossiers);	
					
					// responsables
					List<Responsable> respo =  respoDAO.ListRespo();
					request.setAttribute("responsable", respo);
					List<Client> clyan =  clientDAO.ListClient();
					for (Client client : clyan) {
						String emaill = clientmailDAO.getMailClient(client.getId_client());
						client.setEmail(emaill);
					}
					request.setAttribute("client", clyan);	
					request.setAttribute("succes", "Willkommen Aymen!");
					request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
				}else{
					boolean status = false;
					status=respoDAO.login(email, password);
					if(status){
						session = request.getSession();
						Responsable utilisateur = respoDAO.getRespo(email, password);
						session.setAttribute("session", utilisateur);
						List<Tache> tach =  tacheDAO.ListTacheRespo(utilisateur.getId_respo());
						request.setAttribute("responsable", utilisateur);
						request.setAttribute("tache", tach);
						request.setAttribute("succes", "Willkommen "+ utilisateur.getNom() + "! Sie haben sich authentifiziert");
						if(session != null){
						Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
						int id_respoo = utilisateurConnecte.getId_respo();
						List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respoo);
						request.setAttribute("tachApr", tachApr);
						List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respoo);
						request.setAttribute("tachEC", tachEC);
						List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respoo);
						request.setAttribute("tachProg", tachProg);
						int totalAp = tacheDAO.TotalApprouve(id_respoo);
						int totalEn = tacheDAO.TotalEnCours(id_respoo);
						//---
						for (Tache tache : tachProg) {
							int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
							int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
							tache.setTotalTache(totalTache);
							tache.setMaxTache(maxTache);
						}
						
						request.setAttribute("totalAp", totalAp);
						request.setAttribute("totalEn", totalEn);
						//fin liste HEADER
						}
						request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);

					}
					else{
						
						
					
					exceptionContent = "falsches Login oder Passwort";
					throw new Exception(exceptionContent);
					}
				}
			} catch (Exception e) {
				request.setAttribute("erreur", exceptionContent);	
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			
			}
		}
		
		if(request.getServletPath().equals("/editTache")) {
			try {
				int idTache=Integer.parseInt(request.getParameter("id_tache"));
				int id_respo=Integer.parseInt(request.getParameter("id_respo"));
				String libelle = request.getParameter("libelle");
				String type = request.getParameter("type"); 
				String nomCl = request.getParameter("nom_cl");
				String dateDebut_ = request.getParameter("dateDebut"); 
				String dateFin_ = request.getParameter("dateFin"); 
				
				Tache tachtacha = tacheDAO.getOneOneTache(idTache);
				int id_doc =tachtacha.getId_doc();
				boolean b = tachtacha.isValide();
				int ntec = tachtacha.getD_numTacheEnCour();
				boolean a = tachtacha.isTurn();
				int duree = tachtacha.getDuree();
				request.setAttribute("succes", "Fleck "+idTache +" ist gut verändert worden");	
				Tache nouvelleTache = new Tache(idTache,id_respo ,id_doc,b
						,libelle,dateDebut_,dateFin_,nomCl,type,ntec,a,duree);
			tacheDAO.editTache(nouvelleTache);
				
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editTache.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
	} 
		
		if(request.getServletPath().equals("/editClient")) {
			try {
			String emailOriginal = request.getParameter("emailOriginal");
			String email = request.getParameter("email");
			int idM=clientmailDAO.getIdCM(emailOriginal);
			clientmailDAO.editMail(idM,email); 
			String idC = request.getParameter("id_client");
			int id_client=Integer.parseInt(idC);
			String nom = request.getParameter("nom"); 
			String password = request.getParameter("password"); 
			String adresse = request.getParameter("adresse"); 
			String tel = request.getParameter("tel"); 
//			String carte = request.getParameter("carte"); 
//			String ccv = request.getParameter("ccv"); 
//			int cc=Integer.parseInt(ccv);
//			String expiry = request.getParameter("expiry"); 
//			String premium = request.getParameter("premium"); 
//			boolean b = Boolean. valueOf(premium);
			Client nouveauClient = clientDAO.getClient(idC);
			String mr=nouveauClient.getNom();
			clientDAO.editClient(id_client,nom ,password ,adresse , tel);

			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);		
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String emaill = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(emaill);
			}
			request.setAttribute("client", clyan);	
			request.setAttribute("succes", "Herr "+mr+" wurde geändert.");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editClient.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}	
			}
		
		if(request.getServletPath().equals("/editDoc")) {
			try {
			int id_doc=Integer.parseInt(request.getParameter("id_doc"));
			String nomCl = request.getParameter("nom_cl");
			String type = request.getParameter("type"); 
			String numTache = "1";
			
			Dossier nouveauDossier = new Dossier(nomCl,type,Integer.parseInt(numTache));
			nouveauDossier.setId_doc(id_doc);
			String mr=nouveauDossier.getNom_cl();
			dossierDAO.editDossier(nouveauDossier);


			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);		
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.setAttribute("succes", "Die Datei von Herrn "+mr+" wurde bearbeitet.");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editDoc.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}	
			}
		
		if(request.getServletPath().equals("/editRespo")) {
			try {
				int id_respo=Integer.parseInt(request.getParameter("id_respo"));
				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");	
				String photo = request.getParameter("photo");
				
				
				Responsable nouveauRespo = new Responsable(id_respo,nom, email,password,photo );
				respoDAO.editRespo(nouveauRespo);

				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String emaill = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(emaill);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", "Die verantwortliche Person "+nom+" wurde geändert.");	
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/contact")) {
			String RECIPIENT = request.getParameter("email");
			String USER_NAME = "Kargo.GmbH";  // GMail user name (just the part before "@gmail.com")
			 String PASSWORD = "Bachelor."; // GMail password

			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT }; // list of recipient email addresses
			        String subject = "Confirmation du réception";
			        String body = "Votre Email a été bien recu. Nous allons vous contacter une fois le LIVE CHAT est disponible!";

			        respoDAO.sendFromGMail(from, pass, to, subject, body);
			        request.setAttribute("succes", "Email Envoyé avec succes!");
					request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);

		}
		
		if(request.getServletPath().equals("/registration")) {
			try{
			String email = request.getParameter( "email" );
			String nom = request.getParameter( "nom" );
			String password = request.getParameter( "password" );
			String tel = request.getParameter( "tel" );
			String id_doc = request.getParameter( "id_doc" );
			int id_clientmail=clientmailDAO.getIdCM(email);
			boolean prem = false;
			Client c = new Client(id_clientmail,nom,password,tel,prem);
			clientDAO.addClient(c);
			session = request.getSession();
			int id=clientmailDAO.getIdCM(email);
			Client client = clientDAO.getClient(String.valueOf(id), password);
			session.setAttribute("session", client);
			request.setAttribute("succes", "Sie haben sich erfolgreich registriert");
			request.setAttribute("id_doc", id_doc);
			String nom_cl = client.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", client);
			request.setAttribute("dossier", dossiers);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);
			}	
		}
		
		if(request.getServletPath().equals("/premium")) {
			try{
				String id_client = request.getParameter( "id_client" );
			String adresse = request.getParameter( "adresse" );
			String zip = request.getParameter( "zip" );
			String numcarte = request.getParameter( "numcarte" );
			String ccv = request.getParameter( "ccv" );
			String expiry = request.getParameter( "expiry" );
			String ad=adresse+" "+zip;
			boolean prem=true;
			int id=Integer.parseInt(id_client);
			int cc=Integer.parseInt(ccv);
			clientDAO.updateClient(id,ad,numcarte,cc,expiry,prem);
			Client clyan = clientDAO.getClient(id_client);
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.setAttribute("succes", "Sie sind ein Premium-Kunde!");
			request.setAttribute("id_client", id_client);
			
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("WEB-INF/premium.jsp").forward(request, response);
			}	
		}
		
		
//	} catch (Exception e) {
//		request.setAttribute("erreur", "Es wurde ein Fehler gemacht.");
//		request.getRequestDispatcher("WEB-INF/404.jsp").forward(request, response);
//	}
//---------------------	
	}

}


