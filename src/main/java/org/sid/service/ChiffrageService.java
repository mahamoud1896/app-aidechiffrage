package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Lists;

import org.sid.dao.ActionRepository;
import org.sid.dao.ActiviteRepository;
import org.sid.dao.AdresseRepository;
import org.sid.dao.ClientRepository;
import org.sid.dao.DepensesFixesRepository;
import org.sid.dao.FicheProjetRepository;
import org.sid.dao.ObjectifAnnuelRepository;
import org.sid.dao.OffreRepository;
//import org.sid.dao.PaysClientRepository;
import org.sid.dao.ProfilRepository;
import org.sid.dao.RessActiviteRepository;
import org.sid.dao.RessourceRepository;
import org.sid.dao.RoleRepository;
import org.sid.dao.TypeActiviteRepository;
import org.sid.dao.TypeProjetRepository;
import org.sid.dao.UserRepository;
import org.sid.entities.Action;
import org.sid.entities.Activite;
import org.sid.entities.AdresseClient;
import org.sid.entities.Client;
import org.sid.entities.DepensesFixes;
import org.sid.entities.EtatProjet;
import org.sid.entities.FicheProjet;
import org.sid.entities.ObjectifAnnuel;
import org.sid.entities.Offre;
//import org.sid.entities.PaysClient;
import org.sid.entities.Profil;
import org.sid.entities.RessActivite;
import org.sid.entities.Ressource;
import org.sid.entities.Role;
import org.sid.entities.TypeActivite;
import org.sid.entities.TypeProjet;
import org.sid.entities.User;


@Service
public class ChiffrageService implements IchiffrageService{
	
	
	
	@Autowired
	private RessourceRepository ressourceRepository;
	@Autowired
	private ActiviteRepository activiteRepository;
	@Autowired
	private FicheProjetRepository ficheProjetRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private ActionRepository actionRepository;
	@Autowired
	private TypeActiviteRepository typeActiviteRepository;
	@Autowired
	private TypeProjetRepository typeProjetRepository;
	@Autowired
	private OffreRepository offreRepository;
	@Autowired
	private ObjectifAnnuelRepository objectifAnnuelRepository;
	@Autowired
	private RessActiviteRepository ressActiviteRepository;
	@Autowired
	private DepensesFixesRepository depensesFixesRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	//@Autowired
	//private PaysClientRepository paysClientRepository;
	
	EtatProjet[]	epss ;
	
	

	@Override
	public Ressource saveRess(Ressource ressource) {
		
		
		
		/*
		 * List<Profil> profils = listProfil(null);
		 * 
		 * for(Profil profil : profils) {
		 * 
		 * // ressource.setId(profil.getId());
		 * 
		 * ressource.setProfil(profil);
		 * 
		 * }
		 */		 
		
		
		return ressourceRepository.save(ressource);
	}

	
	@Override
	public Activite saveActivite(Activite activite, Long id) {
		
		
		
		List<TypeActivite> activ = listTypeActivite();
		
		
		//Long id = getFicheProjet(idFiche);
		//List<FicheProjet> ficheProj = listFicheProjet();
		
		
		FicheProjet fiche = ficheProjetRepository.findById(id).get();
		
		activite.setFicheProjet(fiche);
		
		/*for(FicheProjet ficheProjet : ficheProj) {
			
		
			//activite.setId(ficheProjet.getId());
			activite.setFicheProjet(ficheProjet);	
		 
		} */
		
		for(TypeActivite typeActivite : activ) {
			
			 typeActivite.setId(typeActivite.getId());
			 
			}
		
		return activiteRepository.save(activite);
	}
	
	@Override
	public Activite editActivite(Activite activite) {
		
		
		List<TypeActivite> activ = listTypeActivite();
		
		
		
		
		
		//FicheProjet fiche = ficheProjetRepository.findById(id).get();
		
		//activite.setFicheProjet(fiche);
		
		
		
		for(TypeActivite typeActivite : activ) {
			
			 typeActivite.setId(typeActivite.getId());
			 
			}
		
		return activiteRepository.save(activite);
		
	}
	
	
	@Override
	public RessActivite saveRessActivite(RessActivite ressActivite, Long id) {
		
		
		
		Activite activite = activiteRepository.findById(id).get();
		
		ressActivite.setActivite(activite);
		
		
		return ressActiviteRepository.save(ressActivite);
		
	}
	
	

	@Transactional
	public FicheProjet saveFicheProjet(FicheProjet ficheProjet) {
		
		
		//List<Ressource> ress = listRessource();
		
		/*
		 * List<TypeProjet> typeProj = listTypeProj();
		 * 
		 * for(TypeProjet typeProjet : typeProj) {
		 * 
		 * ficheProjet.setId(typeProjet.getId());
		 * 
		 * }
		 */
		 
		
		/*
		 * Ressource rss = new Ressource();
		 * 
		 * 
		 * 
		 * for(FicheProjet fp : rss.getFicheProjets()) {
		 * 
		 * 
		 * FicheProjet fpp = getFicheProjet(fp.getId()); fpp.getRessources().add(rss);
		 * saveFicheProjet(ficheProjet); }
		 */
		
		
		/*
		 * List<Ressource> ressources = listRessource(); for(Ressource ressource
		 * :ressources) {
		 * 
		 * ficheProjet.setId(ressource.getId());
		 * 
		 * }
		 */
		 
		
		//epss = (EtatProjet[]) epss.getClass().getEnumConstants();
		
		  //EtatProjet ep = ficheProjet.getEtatProjet(); 
		 // ficheProjet.setEtatProjet(ep);
		 
		
		
		return ficheProjetRepository.save(ficheProjet);
		 
		
		
		
		//return ficheProjetRepository.save(ficheProjet);
	}

	@Override
	public Role saveRole(Role role) {
		
		
		List<Action> actions = listAction();
		for(Action action : actions) {
		 // User user = new User(); <-- This would work, you get a new one each time
		 role.setId(action.getId());
		 
		}
		
		return roleRepository.save(role);
	}

	

	@Override
	public Action saveAction(Action action) {
		
		return actionRepository.save(action);
	}

	@Override
	public Profil saveProfil(Profil profil) {
		
		return profilRepository.save(profil);
	}
	
	@Override
	public Client saveClient(Client client) {
		
		return clientRepository.save(client);
		
	}
	
	@Override
	public AdresseClient saveAdresseClient(AdresseClient adresseClient) {
		
		return adresseRepository.save(adresseClient);
	}
	
	/*
	 * @Override public PaysClient savePaysClient(PaysClient paysClient) {
	 * 
	 * return paysClientRepository.save(paysClient); }
	 */
	
	@Override
	public ObjectifAnnuel saveObjectifAnnuel(ObjectifAnnuel objAnnuel) {
		
		return objectifAnnuelRepository.save(objAnnuel);
	}

	@Override
	public TypeProjet saveTypeProjet(TypeProjet typeProjet) {
		
		/*
		 * List<Offre> offres = listOffre(null); for(Offre offre : offres) { // User
		 * user = new User(); <-- This would work, you get a new one each time
		 * typeProjet.setId(offre.getId());
		 * 
		 * }
		 */

		
		return typeProjetRepository.save(typeProjet);
	}

	@Override
	public TypeActivite saveTypeActivite(TypeActivite typeActivite) {
		
		return typeActiviteRepository.save(typeActivite);
	}
	
	@Override
	public DepensesFixes saveDepensesFixe(DepensesFixes depensesFixes) {
		
		//FicheProjet fiche = ficheProjetRepository.findById(id).get();
		
		//depensesFixes.setFicheProjet(fiche);
		
		return depensesFixesRepository.save(depensesFixes);
	}

	@Override
	public List<Ressource> listRessource() {
		
		return ressourceRepository.findAll();
	}

	@Override
	public List<Activite> listActivite() {
		
		return activiteRepository.findAll();
	}

	@Override
	public List<FicheProjet> listFicheProjet() {
		
		return ficheProjetRepository.findAll();
	}
	
	@Override
	public List<RessActivite> listRessActivite(){
		
		return ressActiviteRepository.findAll();
	}
	
	@Override
	public Optional<RessActivite> listRessActiviteById(Long id){
		
		return ressActiviteRepository.findById(id);
	}
	
	@Override
	public Optional<FicheProjet> listFicheProjetById(Long id) {
		
		return ficheProjetRepository.findById(id);
	}

	@Override
	public List<Role> listRole() {
		
		return roleRepository.findAll();
	}

	@Override
	public List<User> listUser() {
		
		return userRepository.findAll();
	}

	@Override
	public List<Action> listAction() {
		
		return actionRepository.findAll();
	}

	@Override
	public List<Profil> listProfil(String keyword) {
		
		if (keyword != null) {
            return profilRepository.searchProfil(keyword);
        }
		
		return profilRepository.findAll();
	}
	
	public List<Client> listClient(){
		
		return clientRepository.findAll();
	}
	
	@Override
	public List<AdresseClient> listAdresseClient(){
		
		return adresseRepository.findAll();
	}
	
	/*
	 * @Override public List<PaysClient> listPaysClient(){
	 * 
	 * return paysClientRepository.findAll(); }
	 */

	@Override
	public List<TypeProjet> listTypeProj() {
		
		return typeProjetRepository.findAll();
	}

	@Override
	public List<TypeActivite> listTypeActivite() {
		
		return typeActiviteRepository.findAll();
	}
	
	@Override
	public List<ObjectifAnnuel> listObjectif(){
		
		return objectifAnnuelRepository.findAll();
	}
	
	
	public List<DepensesFixes> listDepensesFixes(String keyword){
		
		if (keyword != null) {
            return depensesFixesRepository.searchDepense(keyword);
        }
		
		return depensesFixesRepository.findAll();
		
	}

	/*
	 * @Override public double getCout(Long idRess) {
	 * 
	 * List<FicheProjet> lfp = listFicheProjet();
	 * 
	 * for (FicheProjet ficheProjet : lfp) { Collection<Ressource> listRess =
	 * ficheProjet.getRessources();
	 * 
	 * for (int i=0; i< listRess.size(); i++) {
	 * 
	 * if(listRess.)
	 * 
	 * } }
	 * 
	 * }
	 */
	
	
	@Override
	public Ressource getRess(Long id) {
		
		return ressourceRepository.findById(id).get();
	}

	@Override
	public Activite getActivite(Long id) {
		
		return activiteRepository.findById(id).get();
	}
	
	@Override
	public RessActivite getRessActivite(Long id) {
		
		return ressActiviteRepository.findById(id).get();
	}

	@Override
	public FicheProjet getFicheProjet(Long id) {
		
		return ficheProjetRepository.findById(id).get();
	}

	@Override
	public Role getRole(Long id) {
		
		return roleRepository.findById(id).get();
	}

	@Override
	public User getUser(Long id) {
	
		return userRepository.findById(id).get();
	}

	@Override
	public Action getAction(Long id) {
		
		return actionRepository.findById(id).get();
	}

	@Override
	public Profil getProfil(Long id) {
		
		return profilRepository.findById(id).get();
	}
	
	@Override
	public Client getClient(Long id) {
		
		return clientRepository.findById(id).get()
				;
	}
	
	@Override
	public AdresseClient getAdresseClient(Long id) {
		
		return adresseRepository.findById(id).get();
	}
	
	/*
	 * @Override public PaysClient getPaysClient(Long id) {
	 * 
	 * return paysClientRepository.findById(id).get(); }
	 */

	@Override
	public TypeProjet getTypeProjet(Long id) {
		
		return typeProjetRepository.findById(id).get();
	}

	@Override
	public TypeActivite getTypeActivite(Long id) {
		
		return typeActiviteRepository.findById(id).get();
	}
	
	@Override
	public ObjectifAnnuel getObjectif(Long id) {
		
		return objectifAnnuelRepository.findById(id).get();
	}
	
	@Override
	public DepensesFixes getDepensesFixe(Long idDepFixe) {
		
		return depensesFixesRepository.findById(idDepFixe).get();
	}

	@Override
	public void deleteRess(Long id) {
		
		
		
		ressourceRepository.deleteById(id);
		
		
	}

	@Override
	public void deleteActivite(Long id) {
		
		activiteRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteRessActivite(Long id) {
		
		ressActiviteRepository.deleteById(id);
	}
	
	@Override
	public void deleteAction(Long id) {
		
		actionRepository.deleteById(id);
		
	}

	@Override
	public void deleteFicheProjet(Long id) {
		
		ficheProjetRepository.deleteById(id);
		
	}

	@Override
	public void deleteRoles(Long id) {
		roleRepository.deleteById(id);
		
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public void deleteProfil(Long id) {
		
		profilRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteClient(Long id) {
		
		clientRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteAdresseClient(Long id) {
		
		adresseRepository.deleteById(id);
	}
	
	/*
	 * @Override public void deletePaysClient(Long id) {
	 * 
	 * paysClientRepository.deleteById(id); }
	 */

	@Override
	public void deleteTypeActivite(Long id) {
		
		typeActiviteRepository.deleteById(id);
		
	}

	@Override
	public void deleteTypeProjet(Long id) {
	
		typeProjetRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteObjectif(Long id) {
		
		objectifAnnuelRepository.deleteById(id);
	}
	
	@Override
	public void deleteDepensesFixes(Long id) {
		
		 depensesFixesRepository.deleteById(id);
		
	}

	@Override
	public List<Activite> getFicheProjetActivite(Long idFicheProjet) {
		
		
		return  activiteRepository.getAllFicheProjActivities(idFicheProjet);

	}

	@Override
	public List<Ressource> getFicheProjetRessource(Long idFicheProjet) {
		
		return ressourceRepository.getAllFicheProjRess(idFicheProjet);
	}
	
	@Override
	public Ressource getRessProjet(Long idFicheProj) {
		
		
		return ressourceRepository.ressFicheProj(idFicheProj);
	}

	@Override
	public List<Action> getUserAction(Long idUser) {
		
		return actionRepository.findAll();
	}

	@Override
	public Page<Ressource> findPaginatedRessource(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortDir.equals("asc")? Sort.by(sortField).ascending():
			Sort.by(sortField).descending()); 
		
		
		return this.ressourceRepository.findAll(pageable);
	}
	
	@Override
	public Page<Profil> findPaginatedProfil(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortDir.equals("asc")? Sort.by(sortField).ascending():
			Sort.by(sortField).descending()); 
		
		
		return this.profilRepository.findAll(pageable);
	}
	
	@Override
	public Page<FicheProjet> findPaginatedFicheProj(int pageNo, int pageSize, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortDir.equals("asc")? Sort.by(sortField).ascending():
			Sort.by(sortField).descending());
		
		
		return this.ficheProjetRepository.findAll(pageable);
	}
	

	@Override
	public Page<TypeActivite> findPaginatedTypeAct(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
Pageable pageable = PageRequest.of(pageNo - 1, pageSize); 
		
		
		return this.typeActiviteRepository.findAll(pageable);
	}

	@Override
	public List<Offre> listOffre(String keyword) {
		// TODO Auto-generated method stub
		
		if (keyword != null) {
            return offreRepository.searchOffre(keyword);
        }
		return offreRepository.findAll();
	}
	

	@Override
	public Offre saveOffre(Offre offre) {
		// TODO Auto-generated method stub
		
		
		return offreRepository.save(offre);
	}

	@Override
	public Offre getOffre(Long id) {
		// TODO Auto-generated method stub
		return offreRepository.findById(id).get();
	}

	@Override
	public void deleteOffre(Long id) {
		// TODO Auto-generated method stub
		offreRepository.deleteById(id);
		
	}

	@Override
	public void ressProjet(FicheProjet ficheProjet) {
		// TODO Auto-generated method stub
		
		//FicheProjet fp = getFicheProjet(idProjet);
		
		//Ressource resso = getRess(idRess);
		
		Ressource rss = new Ressource();
		
		for(FicheProjet fp : rss.getFicheProjets()) {
			
			
			FicheProjet fpp = getFicheProjet(fp.getId());
			fpp.getRessources().add(rss);
			saveFicheProjet(ficheProjet);
		
		//List<Ressource> ress = listRessource();
			
		
			
			
		//ressource.setFicheProjets(null);
		
		//fp.setId(resso.getId());
		
		 // ressourceRepository.save(ressource);
		
		
		
		
		
		ficheProjetRepository.save(fp);
		
	}
	}
	
	@Override
	public User saveUser(User user) {
		
		/*
		 * List<Role> roles = listRole(); //Role role =
		 * roleRepository.findById(roleId).get(); for(Role role : roles) { // User user
		 * = new User(); <-- This would work, you get a new one each time
		 * Collection<Role> userRoles = user.getRoles(); userRoles.add(role);
		 * user.setRoles(userRoles);
		 * 
		 * 
		 * // user.setUserId(role.getId());
		 * 
		 * }
		 */
		
		/*
		 * List<Role> roles = listRole(); for(Role role : roles) { // User user = new
		 * User(); <-- This would work, you get a new one each time
		 * user.setUserId(role.getId());
		 * 
		 * }
		 */
		
		return userRepository.save(user);
	}


	@Override
	public void assignUserRole(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(userId).get();
		Role role = roleRepository.findById(roleId).get();
		
		Collection<Role> userRoles = user.getRoles();
		userRoles.add(role);
		user.setRoles(userRoles);
		userRepository.save(user);
		
		
	}
	
	@Override
	public void updateEtatFiche(Long id) {
		
		FicheProjet fp = ficheProjetRepository.findById(id).get();
		
		EtatProjet ep = fp.getEtatProjet();
		fp = ficheProjetRepository.findByEtatProjet(ep);
		fp.setEtatProjet(ep);
		ficheProjetRepository.save(fp);
		
		
	}
	
	

	@Override
	public void unassignUserRole(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(userId).get();
		user.getRoles().removeIf(x -> x.getId()==roleId);
		userRepository.save(user);
		
	}
	
	@Override
	public Collection<Role> getUserRoles(User user){
	    return user.getRoles();
	}


	@Override
	public void deletePaysClient(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * @Override public double pourcentageAtteintObjct(FicheProjet fiche) {
	 * 
	 * return (fiche.getChiffreAffaireRealise() / fiche.getChiffreAffairePrevu()) *
	 * 100;
	 * 
	 * 
	 * }
	 * 
	 * @Override public double tauxRentaMoyen(FicheProjet fiche) {
	 * 
	 * 
	 * return fiche.getRevenuNetAnnuel() / fiche.getCoutInvestissmtInitial(); }
	 * 
	 * @Override public long delaiMoyenLivraison(FicheProjet fiche) {
	 * 
	 * long dateBeforeInMs = fiche.getDateLivraisonClient().getTime(); long
	 * dateAfterInMs = fiche.getDateCommandeClient().getTime();
	 * 
	 * long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
	 * 
	 * long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
	 * 
	 * return daysDiff;
	 * 
	 * 
	 * }
	 */
		
	

	


/*	@Override
	public void affecterRessAFicheProjet(Long numProjet, List<Ressource> nomRess) {
		// TODO Auto-generated method stub
		
		
		FicheProjet fp = getFicheProjet(numProjet);
		
	
		
	}*/
	
	
	
	
	

	
	
	
}
