package dao;

import java.util.List;

import entities.Dossier;

public interface IDossier {
	public void addDossier(Dossier d);
	public List<Dossier> ListDossier();
	public List<Dossier> ListDossierRespo(int id);
	public void deleteDoc(String idDossiere);
	public Dossier getDoc(String idRespo);
	public void editDossier(Dossier nouveauDossier);
	public String getNomCl(int id_dossier);
	public List<Dossier> ListDossierClient(String nom_cl);
	public Dossier getDosser(int idD);

}
