package org.sid;

import java.io.IOException;
import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.security.core.Authentication;

import org.sid.dao.ActionRepository;
import org.sid.dao.ActiviteRepository;
import org.sid.dao.FicheProjetRepository;
import org.sid.dao.ObjectifAnnuelRepository;
import org.sid.dao.OffreRepository;
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
//import com.chiffrageTest.entities.PaysClient;
import org.sid.entities.Profil;
import org.sid.entities.RessActivite;
import org.sid.entities.Ressource;
import org.sid.entities.Role;
import org.sid.entities.TypeActivite;
import org.sid.entities.TypeProjet;
import org.sid.entities.User;
import org.sid.entities.UserPDFExporter;
import org.sid.service.IchiffrageService;
//import org.sid.userInfo.UserService;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
import com.lowagie.text.DocumentException;




@Controller
//@RequestMapping(value="/chiffrage")
public class ChiffrageTestController {
	
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
	private IchiffrageService ichiffrageService;
	
	//@Autowired
	//private UserService userService;
	
	
	EtatProjet[] epss;
	
	
	
	
	@RequestMapping(value="/listProfil")
	public String viewHomePage(Model model) {
		
		
		//List<Profil> profil = ichiffrageService.listProfil(keyword);
		//model.addAttribute("profil", profil); 
		// model.addAttribute("keyword", keyword);
		  //return "fragments/listProfile";
		 
		
		return viewHlistPageProfilPage(1,"fonction", "asc", model);
	}
	
	@RequestMapping(value="/pageProfil/{pageNop}")
	public String viewHlistPageProfilPage(@PathVariable(value="pageNop") int pageNop,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			Model model) {
		
		
		int pageSize = 5;
		
		Page<Profil> page = ichiffrageService.findPaginatedProfil(pageNop, pageSize, sortField, sortDir);
		
		List<Profil> listProfil = page.getContent();
		
		model.addAttribute("currentPage", pageNop);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" : "asc");
		
		
		Profil profil = new Profil();
		
		model.addAttribute("profils", profil);
		
		
		model.addAttribute("profil", listProfil);
		return "fragments/listProfile";
	}
	
	
	@RequestMapping(value="/listClients")
	public String viewListClient(Model model) {
		
		List<Client> clientList = ichiffrageService.listClient();	
		
		model.addAttribute("clients", clientList);
		
		Client client = new Client();
		
		
		model.addAttribute("client", client);
		
		return "fragments/listClient";
	}
	
	@RequestMapping(value="/listDepenses")
	public String viewDepensePage(Model model,@Param("keyword") String keyword) {
		List<DepensesFixes> dep = ichiffrageService.listDepensesFixes(keyword);
		
		
		
		model.addAttribute("depense", dep);
		model.addAttribute("keyword", keyword);
		return "fragments/listDepense";
	}
	
	@RequestMapping(value="/listDepensess")
	public String viewDepensesPage(Model model,@Param("keyword") String keyword) {
		List<DepensesFixes> dep = ichiffrageService.listDepensesFixes(keyword);
		
		
		
		model.addAttribute("depense", dep);
		model.addAttribute("keyword", keyword);
		return "fragments/listTotal";
	}
	

	
	@RequestMapping(value="/listTypeAct")
	public String viewlistTypeActPage(Model model) {
		
		return viewHlistPageTypeAct(1,model);
	}
	
	@RequestMapping(value="/listAct")
	public String viewlistActPage(Model model) {
		
		
		//ModelAndView mav = new ModelAndView("fragments/listActivite");
		List<Activite> activite = ichiffrageService.listActivite();
		//FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		
		List<FicheProjet> ficheProj = ichiffrageService.listFicheProjet();
		//mav.addObject("ficheProj", ficheProjet);
		model.addAttribute("ficheProj", ficheProj);
		//mav.addObject("activite", activite);
		model.addAttribute("activite", activite);
		
		return "fragments/listActivite";
	}
	
	@RequestMapping(value="/listTypeProj")
	public String viewlistTypeProjPage(Model model) {
		List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
		
		model.addAttribute("typeProj", typeProj);
		
		TypeProjet typeProjet = new TypeProjet();
		model.addAttribute("typeProjet", typeProjet);
		
		List<Offre> offre = ichiffrageService.listOffre(null);
		model.addAttribute("offre", offre);
		
		return "fragments/listTypeProjet";
	}
	
	@RequestMapping(value="/listRess")
	public String viewHlistRessPage(Model model) {
		
		
		return viewHlistPageRessPage(1, "prenom", "asc",model);
		
		//List<Ressource> ress = ichiffrageService.listRessource();
		
		//model.addAttribute("ress", ress);
		//return "fragments/listRessource";
	}
	
	@RequestMapping(value="/page/{pageNo}")
	public String viewHlistPageRessPage(@PathVariable(value="pageNo") int pageNo,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			Model model) {
		
		int pageSize = 5;
		
		Page<Ressource> page = ichiffrageService.findPaginatedRessource(pageNo, pageSize, sortField, sortDir);
		
		List<Ressource> listRess = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" : "asc");
		
		model.addAttribute("resss", listRess);
		
		
		Ressource ress = new Ressource();
		model.addAttribute("ress", ress);
		
		List<Profil> profil = ichiffrageService.listProfil(null);
		model.addAttribute("profil", profil);
		
		return "fragments/listRessource";
	}
	
	
	@RequestMapping(value="/pagee/{pageNa}")
	public String viewHlistFicheProjPage(@PathVariable(value="pageNa") int pageNa,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			Model model) {
		
		int pageSize = 5;
		
		Page<FicheProjet> pagee = ichiffrageService.findPaginatedFicheProj(pageNa, pageSize,sortField, sortDir);
		
		List<FicheProjet> listRess = pagee.getContent();
		
		model.addAttribute("currentPage", pageNa);
		model.addAttribute("totalPages", pagee.getTotalPages());
		model.addAttribute("totalItems", pagee.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" : "asc");
		
		FicheProjet ficheProjet = new FicheProjet();
		
		
		model.addAttribute("ficheProjj", ficheProjet);
		
		
		List<Ressource> ress = ichiffrageService.listRessource();
		model.addAttribute("listRessource", ress);
		
		List<Client> client = ichiffrageService.listClient();
		model.addAttribute("client", client);
		
		List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
		model.addAttribute("typeProjet", typeProj);
		
		
		model.addAttribute("ficheProj", listRess);
		return "fragments/listFicheProjet";
	}
	
	@RequestMapping(value="/pageTypeAct/{pageNoa}")
	public String viewHlistPageTypeAct(@PathVariable(value="pageNoa") int pageNoa,Model model) {
		
		int pageSize = 5;
		
		Page<TypeActivite> page = ichiffrageService.findPaginatedTypeAct(pageNoa, pageSize);
		
		List<TypeActivite> listTypeAct = page.getContent();
		
		model.addAttribute("currentPage", pageNoa);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		TypeActivite typeAct = new TypeActivite();
		
		
		model.addAttribute("typeActivite", typeAct);
		
		model.addAttribute("typeActivites", listTypeAct);
		return "fragments/listTypeActivite";
	}
	
	
	
	
	@RequestMapping(value="/listUsers")
	public String viewHlistUserPage(Model model) {
		
		
		List<User> user = ichiffrageService.listUser();
		
		
		
		
		
		model.addAttribute("user", user);
		return "fragments/listUser";
	}
	
	@RequestMapping(value="/listRoles")
	public String viewHlistRolePage(Model model) {
		
		
		List<Role> role = ichiffrageService.listRole();
		
		model.addAttribute("role", role);
		return "fragments/listRole";
	}
	
	@RequestMapping(value="/listOffres")
	public String viewHlistOffrePage(Model model,@Param("keyword") String keyword) {
		
		
		List<Offre> offre = ichiffrageService.listOffre(keyword);
		Offre offres = new Offre();
		
		model.addAttribute("offre", offres);
		model.addAttribute("offres", offre);
		model.addAttribute("keyword", keyword);
		
		
		return "fragments/listOffre";
	}
	
	@RequestMapping(value="/listObjectifs")
	public String viewHlistObjectifPage(Model model) {
		
		
		List<ObjectifAnnuel> obj = ichiffrageService.listObjectif();
		
		model.addAttribute("objs", obj);
		ObjectifAnnuel objAnnuel = new ObjectifAnnuel();
		
		model.addAttribute("obj", objAnnuel);
		//model.addAttribute("keyword", keyword);
		return "fragments/listObjectif";
	}
	
	@RequestMapping(value="/listActions")
	public String viewHlistActionPage(Model model) {
		
		
		List<Action> action = ichiffrageService.listAction();
		
		model.addAttribute("action", action);
		return "fragments/listAction";
	}
	
	
	@RequestMapping(value="/listProj")
	public String viewHlistFicheProjjjPage(Model model) {
		
		return viewHlistFicheProjPage(1, "nomProjet", "asc",model);
		
		
		/*
		 * List<FicheProjet> ficheProj = ichiffrageService.listFicheProjet();
		 * 
		 * List<Ressource> ress = ichiffrageService.getFicheProjetRessource(null);
		 * model.addAttribute("listRessource", ress);
		 * 
		 * 
		 * model.addAttribute("ficheProj", ficheProj); return
		 * "fragments/listFicheProjet";
		 */
	}
	
	 @RequestMapping("/loggin")
	    public String loginPage(){
	        return "auth-login";
	    }

		/*
		 * @RequestMapping("/homePage") public String loginSubmit(Model model) {
		 * 
		 * )
		 * 
		 * //model.addAttribute("delaiprojet",
		 * ficheProjetRepository.getDelaiByProjet());
		 * 
		 * return "/pages/landing_page"; }
		 */
	    
	
	
	
	
	    
	
	   // @RequestMapping("/homePage")
	    
	   // public ModelAndView loginSub(Model model) {
	    	
	    	
	    	//Map<String, Object> data = new LinkedHashMap<String, Object>();
			//data.put("delaiprojet", ficheProjetRepository.getDelaiByProjet());
			//model.addAttribute("keySet", data.keySet());
		//	model.addAttribute("delaiprojet", ficheProjetRepository.getDelaiByProjet());
			//data.put("number", 1234);
			
			/*
			 * List<FicheProjet> dataList = ficheProjetRepository.findAll();
			 * 
			 * JsonArray jsonArrayCategory = new JsonArray(); JsonArray jsonArraySeries =
			 * new JsonArray(); JsonObject jsonObject = new JsonObject();
			 * dataList.forEach(data->{ jsonArrayCategory.add(data.getNomProjet());
			 * jsonArraySeries.add(ficheProjetRepository.getDelaiByProjet()); });
			 * jsonObject.add("categories", jsonArrayCategory); jsonObject.add("series",
			 * jsonArraySeries); return jsonObject.toString();
			 */
	    	
	       // return new ModelAndView("/pages/landing_page");
	  //  }
	    
	  
	    @RequestMapping("/homePage")
		public String getDelai(Model model) {
			
	    	List<String> projetListt = ichiffrageService.listFicheProjet().stream().map(x-> x.getNomProjet()).collect(Collectors.toList());
	    	//List<Double> delaiListt = ichiffrageService.listObjectif().stream().map(x-> x.getObjectifAnuuel()).collect(Collectors.toList());
	    	List<String> projetList = ichiffrageService.listOffre(null).stream().map(x-> x.getNomOffre()).collect(Collectors.toList());
	    	
	    	List<String> projetLists = ichiffrageService.listFicheProjet().stream().map(x-> x.getTypeProjet().getOffre().getNomOffre()).collect(Collectors.toList());
	    	
	    	
	    	List<String> anneeObj = objectifAnnuelRepository.objAnneeObjectif().stream().map(x-> x.getAnnee()).collect(Collectors.toList());
	    	List<Double> montantObj = objectifAnnuelRepository.objAnneeObjectif().stream().map(x-> x.getObjectifAnuuel()).collect(Collectors.toList());
	    	//List<Long> pourcObj = ficheProjetRepository.getDelaiByProject().stream().map(x-> x.g).collect(Collectors.toList());
	    	model.addAttribute("anneeObj", anneeObj);
	    	model.addAttribute("montantObj", montantObj);
	    	
	    	//Map<String, Object> data = new LinkedHashMap<String, Object>();
	    	//data.put("delaiprojets", ficheProjetRepository.getDelaiByProject());
	    	//model.addAttribute("keySet", data.values());
	    	//model.addAttribute("keySet", data.values());
	    	 
	    	//Double cout = ichiffrageService.getRessProjet(null).getProfil().getCoutHommeJour();
	    	
	    	model.addAttribute("projett", projetListt);
	    	model.addAttribute("projet", projetList);
	    	model.addAttribute("delaiprojet", ficheProjetRepository.offreProgress());
	    	model.addAttribute("delaiprojetss", projetLists);
	    	model.addAttribute("delaiprojets", ficheProjetRepository.getMontantByOffres());
	    	
	    	//model.addAttribute("annee", objectifAnnuelRepository.anneeObjectif());
	    	//model.addAttribute("pourcent", ressActiviteRepository.dureeMoyen());
	    	model.addAttribute("pourcent", (ficheProjetRepository.pourcentageObjectif()/objectifAnnuelRepository.objectifAnn()*100));
	    	model.addAttribute("pourcentRestant", (100-(ficheProjetRepository.pourcentageObjectif()/objectifAnnuelRepository.objectifAnn())*100));
	    	
	    	model.addAttribute("delaiprojett", ficheProjetRepository.getDelaiByProjet());
	    	model.addAttribute("delaiprojetts", ficheProjetRepository.getDelaiByProject());
	    	model.addAttribute("delaiprojettt", ficheProjetRepository.getDelaiMoyen());
	    	
	    	
	    	
			 
	    	
	    	
	    	
	    	
	    	
	    	
	    	
			/*
			 * List<FicheProjet> dataList = ficheProjetRepository.findAll();
			 * 
			 * 
			 * JsonArray jsonArrayCategory = new JsonArray(); JsonArray jsonArraySeries =new
			 * JsonArray(); JsonObject jsonObject = new JsonObject();
			 * dataList.forEach(data->{ jsonArrayCategory.add(data.getNomProjet());
			 * jsonArraySeries.add(data.getDelai()); }); jsonObject.add("categories",
			 * jsonArrayCategory); jsonObject.add("series", jsonArraySeries);
			 * 
			 * jsonObject.toString();
			 */
				 
			 
			  
			  
	    	
	    	
	    	
	    	//model.addAttribute("delai", delaiList);
	    	//model.addAttribute("delaiprojet", delaiList);
	    	
	    	
	    	
			return "/pages/landing_pag";
		}
	    
	    
		/*
		 * @RequestMapping("/multiplelinechartdata") public ResponseEntity<?>
		 * getDataForMultipleLine() { List<FicheProjet> dataList =
		 * ficheProjetRepository.findAll(); //List<MultipleData> dataList =
		 * multipleDataDAO.findAll(); Map<String, List<FicheProjet>> mappedData = new
		 * HashMap<>(); for(FicheProjet data : dataList) {
		 * 
		 * if(mappedData.containsKey(data.getNomProjet())) {
		 * mappedData.get(data.getNomProjet()).add(data); }else { List<FicheProjet>
		 * tempList = new ArrayList<FicheProjet>(); tempList.add(data);
		 * mappedData.put(data.getNomProjet(), tempList); }
		 * 
		 * } return new ResponseEntity<>(mappedData, HttpStatus.OK); }
		 */
	    
	    @RequestMapping("/homePagesss")
		public String showHome(){
			return "/pages/landing_pageSS";
		}
	    
	
	
	@GetMapping("/403")
	public String error403() {
		
		return "403";
	}
	
	
	@RequestMapping(value="/newRess")
	public String showNewRessourcePage(Model model) {
		
		
		//model.addAttribute("delaiprojet", ficheProjetRepository.getDelaiByProjet());
		Ressource ress = new Ressource();
		model.addAttribute("ress", ress);
		
		List<Profil> profil = ichiffrageService.listProfil(null);
		model.addAttribute("profil", profil);
		
		return "/fragments/new_ress";
	}
	
	
	
	
	@RequestMapping(value="/editRess/{id}")
	private ModelAndView showEditRessPage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_ress");
		
		Ressource ress = ichiffrageService.getRess(id);
		List<Profil> profil = ichiffrageService.listProfil(null);
		
		//model.addAttribute("profil", profil);
		
		
		mav.addObject("profil", profil);
		
		mav.addObject("ressource", ress);
		
		
		return mav;
	}
	
	@RequestMapping(value="/saveRess")
	private String saveRessource(@Valid @ModelAttribute("ressource") Ressource ressource, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
					
					return "/fragments/new_ress";
				}
		
		else {
			
			ichiffrageService.saveRess(ressource);
			
			
			
			return "redirect:/listRess";
		}
		
	}
	
	
	//@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value="/deleteRess/{id}")
	private String deleteRess(@PathVariable(name="id") Long id) {
		
		
		//Ressource ress = ressourceRepository.findById(id).get();
		
		//Long idFich = ficheProjetRepository.getById(id).getId();
		
		Ressource ress = ressourceRepository.findById(id).get();
		
		List<FicheProjet> fp = ficheProjetRepository.findAll();
		
		
		
		for (FicheProjet ficheProjet : fp) {
			
			Collection<Ressource> cr = ficheProjet.getRessources();
			
			
			for (Ressource rs : cr) {
				
				while(rs.equals(ress)) {
					
					//return "/delete";
					
					System.out.println("PAS DE SUPPRESSION !!!!");
					return "/deleteRess";
				}
				
			}
			
			
			
			  
			 
			
		}
		
		//ichiffrageService.deleteRess(id);
		
		//List<Ressource> listRess = ressourceRepository.getAllFicheProjRess(id);
		
		
		//return "/delete";
		return "redirect:/listRess";
		
		
		
		
		
		
	}
	
	
	//CLIENT PROJET CONTROLLER
	
	
		@RequestMapping(value="/newClient")
		public String showNewClientProjetPage(Model model) {
			
			Client client = new Client();
			
			
			model.addAttribute("client", client);
			
			
			AdresseClient adress = new AdresseClient();
			
			//List<AdresseClient> adress = ichiffrageService.listAdresseClient();
			model.addAttribute("adress", adress);
			
			//List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
			//model.addAttribute("typeProjet", typeProj);
			
			return "/fragments/new_client";
		}
	
		
		@RequestMapping(value="/saveClient")
		private String saveClientt(@ModelAttribute("client") Client client) {
			
			
			
				
				ichiffrageService.saveClient(client);
				
				return "redirect:/listClients";
			
			
			
		}
		
		
		@RequestMapping(value="/editClient/{id}")
		private ModelAndView showEditClientProjPage(@PathVariable(name="id") Long id) {
			
			ModelAndView mav = new ModelAndView("fragments/edit_client");
			
			Client client = ichiffrageService.getClient(id);
			
			
			//List<AdresseClient> ress = ichiffrageService.listRessource();
			
			//model.addAttribute("ressources", ress);
			//mav.addObject("ressources", ress);
			
			
			
			mav.addObject("client", client);
			
			
			return mav;
		}
	
	
	//FICHE PROJET CONTROLLER
	
	
	@RequestMapping(value="/newFP")
	public String showNewFicheProjetPage(Model model) {
		
		FicheProjet ficheProjet = new FicheProjet();
		
		
		model.addAttribute("ficheProj", ficheProjet);
		
		
		List<Ressource> ress = ichiffrageService.listRessource();
		model.addAttribute("listRessource", ress);
		
		List<Client> client = ichiffrageService.listClient();
		model.addAttribute("client", client);
		
		List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
		model.addAttribute("typeProjet", typeProj);
		
		return "/fragments/new_ficheProjet";
	}
	
	
	@RequestMapping(value="/saveFP")
	private String saveFicheProj(@ModelAttribute("ficheProjet") FicheProjet ficheProjet) {
		
		
		
			//Client cl = new Client();
			//ficheProjet.setClient(cl);
			//ichiffrageService.saveClient(cl);
			ichiffrageService.saveFicheProjet(ficheProjet);
			
			return "redirect:/listProj";
		
		
		
	}
	
	@RequestMapping(value="/editFP/{id}")
	private ModelAndView showEditFicheProjPage(@PathVariable(name="id")Long id, Model model) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_ficheProj");
		
		FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		
		
		
		List<Ressource> ress = ichiffrageService.listRessource();
		
	
		model.addAttribute("ressources", ress);
		
		List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
		
		List<Client> lc = ichiffrageService.listClient();
		
		model.addAttribute("client", lc);
		model.addAttribute("typeProjet", typeProj);
		
		model.addAttribute("ficheProjet", ficheProjet); 
		
		
		return mav;
	}
	
	@RequestMapping(value="/editEtat/{id}")
	private ModelAndView showEditEttaFicheProjPage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/editEtat");
		
		FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		
		 //epss = (EtatProjet[]) epss.getClass().getEnumConstants();
		 //mav.addObject("etatProjet", epss);
		
		 
		List<Ressource> ress = ichiffrageService.listRessource();
		
		//model.addAttribute("ressources", ress);
		mav.addObject("ressources", ress);
		
		List<TypeProjet> typeProj = ichiffrageService.listTypeProj();
		//model.addAttribute("typeProjet", typeProj);
		mav.addObject("typeProjet", typeProj);
		
		mav.addObject("ficheProjet", ficheProjet);
		
		
		return mav;
	}
	
	@RequestMapping("/listProj/etatProjet/update/{id}")
	public String etatProj(@PathVariable Long id 
	                        ){
		ichiffrageService.updateEtatFiche(id);
	    return "redirect:/listProj/"+id;
	}
	
	
	@RequestMapping(value="/deleteFP/{id}")
	private String deleteFicheProj(@PathVariable(name="id") Long id) {
		
		ichiffrageService.deleteFicheProjet(id);
		
		
		return "redirect:/listProj";
	}
	
	
	
	
	//ACTIVITE CONTROLLER
	
	@RequestMapping(value="/newAct/{id}")
	private ModelAndView showActivitePage(@PathVariable(name="id")Long id, @Param("keyword") String keyword) {
		
		ModelAndView mav = new ModelAndView("fragments/new_activite");
		//List<FicheProjet> ficheProjet = ichiffrageService.listFicheProjet();
		FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		//List<Activite> listactivite = ichiffrageService.listActivite();
		//model.addAttribute("lactivite", listactivite);
		
		//Activite activite = new Activite();
		//model.addAttribute("ficheProj", ficheProjet);
		
		List<Ressource> ress = ichiffrageService.getFicheProjetRessource(id);
		mav.addObject("listRessource", ress);
		
		//mav.addObject("delai", ichiffrageService.delaiMoyenLivraison(ficheProjet));
		//mav.addObject("tauxRenta", ichiffrageService.tauxRentaMoyen(ficheProjet));
		//mav.addObject("pourAttObj", ichiffrageService.pourcentageAtteintObjct(ficheProjet));
		
		List<DepensesFixes> dep = ichiffrageService.listDepensesFixes(keyword);
		
		
		
		mav.addObject("depenses", dep);
		//mav.addObject("keyword", keyword);
		
		DepensesFixes deps = new DepensesFixes();
		mav.addObject("depense", deps);
		
		List<FicheProjet> ficheProjets = ichiffrageService.listFicheProjet();
		mav.addObject("listficheProj", ficheProjets);
		
		
		Activite activites = new Activite();
		mav.addObject("activites", activites);
		
		List<TypeActivite> typeActivite = ichiffrageService.listTypeActivite();
		//model.addAttribute("typeActivite", typeActivite);
		mav.addObject("typeActivite", typeActivite);
		
		mav.addObject("ficheProj", ficheProjet);
		
		List<Activite> activite = ichiffrageService.getFicheProjetActivite(id);
		mav.addObject("activite", activite);
		//model.addAttribute("activite", activite);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/ficheEvaluation/{id}")
	private ModelAndView viewFicheEval(@ModelAttribute("activite") Activite activite,@PathVariable(name="id") Long id, Model model) {
		
		ModelAndView mav = new ModelAndView("fragments/ficheEval");
		
		FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		
		//List<Activite> la = ichiffrageService.listActivite();
		
		
		
		
		//model.addAttribute("lra", activite);
		
		model.addAttribute("ficheProj", ficheProjet);
		
		
		return mav;
		
	}
	
	
	
	
	
	@RequestMapping(value="/newActiv/{id}")
	private ModelAndView showNewActivitePage(@PathVariable(name="id") Long id, Model model) {
		
		ModelAndView mav = new ModelAndView("fragments/new_act");
		//Optional<FicheProjet> ficheProjet = ichiffrageService.listFicheProjetById(id);
		
		FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		//List<FicheProjet> ficheProjet = ichiffrageService.listFicheProjet();
		//model.addAttribute("lactivite", listactivite);
		
		//RessActivite ressActivite = new RessActivite();
		//model.addAttribute("ressActiv", ressActivite);
		
		
		Long idProjet = ficheProjet.getId();
		System.out.println("id projet: "+ idProjet);
		model.addAttribute("idProj", idProjet);
		
		//List<Ressource> ress = ichiffrageService.getFicheProjetRessource(id);
		//model.addAttribute("listRessource", ress);
		
		Activite activite = new Activite();
		model.addAttribute("ficheProj", ficheProjet);
		//mav.addObject("ficheProj", ficheProjet);
		
		
		
		List<TypeActivite> typeActivite = ichiffrageService.listTypeActivite();
		model.addAttribute("typeActivite", typeActivite);
		//mav.addObject("typeActivite", typeActivite);
		
		
		model.addAttribute("activite", activite);
		//mav.addObject("activite", activite);
		return mav;
	}
	
	
	@RequestMapping(value="/saveAct/{id}")
	private String saveActiv(@Valid @ModelAttribute("activite") Activite activite, @PathVariable(name="id")Long id, BindingResult bindingResult) {
		
		//ModelAndView mav = new ModelAndView("fragments/new_activite");
		
		if(bindingResult.hasErrors()) {
			
			//return mav = new ModelAndView("fragments/new_activite");
					return "/fragments/new_activite";
		}
		
		
		//Long idFicheProj = activite.getFicheprojetId();
		
		ichiffrageService.saveActivite(activite, id);
		
		return "redirect:/newAct/"+id;
		//return "redirect:/listProj";
	}
	
	@RequestMapping(value="/saveEdictAct/{id}")
	private String saveEditActiv(@Valid @ModelAttribute("activite") Activite activite, @PathVariable(name="id")Long id, BindingResult bindingResult) {
		
		//ModelAndView mav = new ModelAndView("fragments/new_activite");
		
		
		
		if(bindingResult.hasErrors()) {
			
			//return mav = new ModelAndView("fragments/new_activite");
					return "/fragments/new_activite";
		}
		
		Activite idAc = activiteRepository.findById(id).get();
		
		Long idFicheProj = idAc.getFicheProjet().getId();
		
		
		//System.out.println("id ficheProj:"+idFicheProj);
		ichiffrageService.saveActivite(activite, idFicheProj);
		
		//Long idFicheProj = activite.getFicheProjet().getId();
		
		
		
		return "redirect:/newAct/"+idFicheProj;
		//return "redirect:/listProj";
	}
	
	@RequestMapping(value="/saveResAct/{id}")
	private String saveRessActiv(@ModelAttribute("ressAct") RessActivite ressActivite, @PathVariable(name="id")Long id) {
		
		//ModelAndView mav = new ModelAndView("fragments/new_activite");
		
		
		ichiffrageService.saveRessActivite(ressActivite, id);
		
		return "redirect:/editAct/"+id;
		//return "redirect:/listProj";
	}
	
	@RequestMapping(value="/editAct/{id}")
	private ModelAndView showEditActPage(@PathVariable(name="id") Long id ) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_activite");
		//FicheProjet ficheProjet = ichiffrageService.getFicheProjet(id);
		double coutRessource = 0;
		Long idR = 0L;
		RessActivite ressActivite = new RessActivite();
		mav.addObject("ressActiv", ressActivite);
		
		Activite activite = ichiffrageService.getActivite(id);
		
		
		Long idFicheProj = activite.getFicheProjet().getId();
		FicheProjet fpp = ficheProjetRepository.getById(idFicheProj);
		mav.addObject("fpp", fpp);
		//Long idRessAct = ressActiviteRepository.getById(null).getId();
		
		//Long idFicheProj = activite.getFicheProjet().getId();
		
		//Ressource res = ichiffrageService.getRess(idRess);
		
		List<Ressource> ress = ichiffrageService.getFicheProjetRessource(idFicheProj);
		//ress.get(0).getId()
		//double cout = ichiffrageService.getActivite(id).getRessActivites().forEach(Ress);
		List<Long> idRs = ichiffrageService.getActivite(id).getRessActivites().stream().map(x-> x.getRessAct().getId()).collect(Collectors.toList());
		
		/*
		 * idR = ichiffrageService.getRessActivite(id).getDuree();
		 * //mav.addObject("coutRess", cout); for(int i = 0;i<ress.size();i++) {
		 * if(ress.get(i).getId()== idR) { coutRessource =
		 * ress.get(i).getProfil().getCoutHommeHeure(); } }
		 */
			
		/*
		 * List<FicheProjet> lfp = ichiffrageService.listFicheProjet();
		 * 
		 * 
		 * Long idR = ichiffrageService.getRessActivite(id).getDuree(); for (FicheProjet
		 * ficheProjet : lfp) { Collection<Ressource> listRess =
		 * ficheProjet.getRessources(); List<Ressource> ressProj =
		 * ichiffrageService.getFicheProjetRessource(idFicheProj);
		 * 
		 * for (int i=0; i< ressProj.size(); i++) {
		 * 
		 * if(ressProj.get(0).getId() == ) {
		 * 
		 * }
		 * 
		 * } }
		 */
		 
		
		//Optional<RessActivite> ract = ichiffrageService.listRessActiviteById(id);
		//RessActivite ractt = ichiffrageService.getRessActivite(id);
		
		
		mav.addObject("coutRess", coutRessource);
		
		mav.addObject("listRessource", ress);
		//mav.addObject("ficheProj", ficheProjet);
		
		
		
		List<TypeActivite> typeActivite = ichiffrageService.listTypeActivite();
		//model.addAttribute("typeActivite", typeActivite);
		mav.addObject("typeActivite", typeActivite);
		
		
		mav.addObject("activite", activite);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteAct/{id}")
	private String deleteActiv(@PathVariable(name="id") Long id) {
		
		Activite activite = ichiffrageService.getActivite(id);	
		Long idFicheProj = activite.getFicheProjet().getId();
		//FicheProjet fpp = ficheProjetRepository.getById(idFicheProj);
		//model.addAttribute("fpp", fpp);
		
		ichiffrageService.deleteActivite(id);
		
		
		return "redirect:/newAct/"+idFicheProj;
	}
	
	@RequestMapping(value="/deleteReAct/{id}")
	private String deleteRessActiv(@PathVariable(name="id") Long id) {
		RessActivite activite = ichiffrageService.getRessActivite(id);	
		Long idFicheProj = activite.getActivite().getId();
		ichiffrageService.deleteRessActivite(id);
		
		
		return "redirect:/edictAct/"+ idFicheProj;
	}

	
	
	//PROFIL CONTROLLER
	
	/*
	 * @RequestMapping(value="/newProfil") public String showNewProfilPage(Model
	 * model) {
	 * 
	 * Profil profil = new Profil();
	 * 
	 * model.addAttribute("profil", profil); return "index"; }
	 */
	
	
	@RequestMapping(value="/listProj/exportProfil")
	public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<FicheProjet> listUsers = ichiffrageService.listFicheProjet();
         
        UserPDFExporter exporter = new UserPDFExporter(listUsers);
        exporter.export(response);
         
    }
	
	
	@RequestMapping(value="/testPopup")
	public String showNewTest(Model model) {
		
		Profil profil = new Profil();
		
		model.addAttribute("profil", profil);
		return "/fragments/test";
	}
	
	
	@RequestMapping(value="/saveProfil", method = RequestMethod.POST)
	private String saveProfile(@Valid @ModelAttribute("profil") Profil profil, BindingResult bindingResult) {
		
		/*
		if(bindingResult.hasErrors()) {
			
			return "/fragments/index";
		}*/
		
			
			ichiffrageService.saveProfil(profil);
			
			return "redirect:/listProfil";
		
		
		
	}
	
	@RequestMapping(value="/editProfil/{id}")
	private ModelAndView showEditProfilPage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_profil");
		
		Profil profil = ichiffrageService.getProfil(id);
		
		
		mav.addObject("profil", profil);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteProfil/{id}")
	private String deleteProfile(@PathVariable(name="id") Long id) {
		
		
		
		List<Ressource> lr = ichiffrageService.listRessource();
		
		for (Ressource ressource : lr) {
			
			while (ressource.getProfil().getId().equals(id)) {
				
				System.out.println("PROFIL DEJA ATTRIBUE!!!! DONC PAS DE SUPPRESSION");
				
				return "/deleteProfil";
			}
		}
		
		//ichiffrageService.deleteProfil(id);
		
		
		return "redirect:/listProfil";
	}
	
	
	
	
	
	
	
	//DEPENSES FIXES CONTROLLER
	
	
	@RequestMapping(value="/newDepense")
	public String showNewDepensePage(Model model) {
		
		
		
		//FicheProjet ficheProj = ichiffrageService.getFicheProjet(id);
		List<FicheProjet> ficheProjet = ichiffrageService.listFicheProjet();
		model.addAttribute("ficheProj", ficheProjet);
		
		
		
		//Long idf = dep.getFicheProjet().getId();
		
		//FicheProjet fp = ichiffrageService.getFicheProjet(idf);
		//model.addAttribute("fpp", fp);
		DepensesFixes dep = new DepensesFixes();
		model.addAttribute("depense", dep);
		return "/fragments/new_depense";
	}
	
	
	
	@RequestMapping(value="/saveDepense", method = RequestMethod.POST)
	private String saveDepenses(@Valid @ModelAttribute("depense") DepensesFixes depense, BindingResult bindingResult) {
		//FicheProjet fiche = ficheProjetRepository.findById(id).get();
		//ModelAndView mav = new ModelAndView("fragments/listDepense");
		
		//Long id = ficheProjetRepository.getById(null).getId();
		
		Long id = depense.getFicheProjet().getId();
		
		if(bindingResult.hasErrors()) {
			
			return "/fragments/new_depense";
			//return mavv;
		}
		else {
			
			ichiffrageService.saveDepensesFixe(depense);
			
			//return "redirect:/listDepenses";
			return "redirect:/newAct/"+id;
		}
		
		
	}
	
	@RequestMapping(value="/editDepense/{id}")
	private ModelAndView showEditDepensePage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_depense");
		List<FicheProjet> ficheProjet = ichiffrageService.listFicheProjet();
		
		DepensesFixes dep = ichiffrageService.getDepensesFixe(id);
		
		Long idf = dep.getFicheProjet().getId();
		
		FicheProjet fp = ichiffrageService.getFicheProjet(idf);
		mav.addObject("fpp", fp);
		
		mav.addObject("ficheProjet", ficheProjet);
		
		mav.addObject("depense", dep);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteDepense/{id}")
	private String deleteDepenses(@PathVariable(name="id") Long id) {
		
		ichiffrageService.deleteDepensesFixes(id);
		
		
		return "redirect:/listDepenses";
	}
	
	
	
	//OFFRE CONTROLLER
	
		@RequestMapping(value="/newOffre")
		public String showNewOffrePage(Model model) {
			
			Offre offre = new Offre();
			
			model.addAttribute("offre", offre);
			return "/fragments/new_offre";
		}
		
		
		
		@RequestMapping(value="/saveOffre")
		private String saveOffree(@Valid @ModelAttribute("offre") Offre offre, BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				
				return "/fragments/new_offre";
			}
			
			else {
				
				ichiffrageService.saveOffre(offre);
				
				return "redirect:/listOffres";
			}
			
		}
		
		@RequestMapping(value="/editOffre/{id}")
		private ModelAndView showEditOffrePage(@PathVariable(name="id") Long id) {
			
			ModelAndView mav = new ModelAndView("fragments/edit_offre");
			
			Offre offre = ichiffrageService.getOffre(id);
			
			
			mav.addObject("offre", offre);
			
			
			return mav;
		}
		
		
		@RequestMapping(value="/deleteOffre/{id}")
		private String deleteOffres(@PathVariable(name="id") Long id) {
			
			
			
			List<TypeProjet> ltp = ichiffrageService.listTypeProj();
			
			
			for (TypeProjet typeProjet : ltp) {
				
				while (typeProjet.getOffre().getId().equals(id)) {
					
					System.out.println("OFFRE DEJA AFFECTEE POUR UN PROJET!! PAS DE SUPPRESSION");
					
					return "/deleteOffre";
				}
			}
			
			ichiffrageService.deleteOffre(id);
			
			
			return "redirect:/listOffres";
		}
	
	
		
	//OBJECTIF CONTROLLER
		
		
		
		@RequestMapping(value="/newObjectifAnnuel")
		public String showNewObjectifPage(Model model) {
			
			ObjectifAnnuel objAnnuel = new ObjectifAnnuel();
			
			model.addAttribute("obj", objAnnuel);
			return "/fragments/new_objectif";
		}
		
		
		
		@RequestMapping(value="/saveObj")
		private String saveObjectifAnn(@ModelAttribute("obj") @Valid ObjectifAnnuel obj, BindingResult bindingResult) {
			
			
				if (bindingResult.hasErrors()) {
					return "/fragments/new_objectif";
				}
						
				
				ichiffrageService.saveObjectifAnnuel(obj);
				
				return "redirect:/listObjectifs";
			
			
		}
		
		@RequestMapping(value="/editObjectif/{id}")
		private ModelAndView showEditObjectifPage(@PathVariable(name="id") Long id) {
			
			ModelAndView mav = new ModelAndView("fragments/edit_objectif");
			
			ObjectifAnnuel obj = ichiffrageService.getObjectif(id);
			
			
			mav.addObject("obj", obj);
			
			
			return mav;
		}
		
		
		@RequestMapping(value="/deleteObjectif/{id}")
		private String deleteObjectifs(@PathVariable(name="id") Long id) {
			
			ichiffrageService.deleteObjectif(id);
			
			
			return "redirect:/listObjectifs";
		}
		
	
	//ROLE CONTROLLER
	
	@RequestMapping(value="/newRole")
	public String showNewRolePage(Model model) {
		
		Role role = new Role();
		
		
		model.addAttribute("role", role);
		
		List<Action> action = ichiffrageService.listAction();
		model.addAttribute("listAction", action);
		
		
		
		return "/fragments/new_role";
	}
	
	
	@RequestMapping(value="/saveRole")
	private String saveRol(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			return "/fragments/new_role";
		}
		
		ichiffrageService.saveRole(role);
		
		return "redirect:/listRoles";
	}
	
	@RequestMapping(value="/editRole/{id}")
	private ModelAndView showEditRolePage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_role");
		
		Role role = ichiffrageService.getRole(id);
		
		List<Action> action = ichiffrageService.listAction();
		
		//model.addAttribute("listAction", action);
	
		mav.addObject("listAction", action);
		
		mav.addObject("role", role);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteRole/{id}")
	private String deleteRol(@PathVariable(name="id") Long id) {
		
		ichiffrageService.deleteRoles(id);
		
		
		return "redirect:/";
	}
	
	//USER CONTROLLER
	
		@RequestMapping(value="/newUser")
		public String showNewUserPage(Model model) {
			
			User user = new User();
			
			
			model.addAttribute("user", user);
			
			List<Role> role = ichiffrageService.listRole();
			model.addAttribute("listRole", role);
			
			
			
			return "/fragments/new_user";
		}
		
		
		@RequestMapping(value="/saveUser")
		private String saveUsers(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
			
			
			/*
			 * Profil profil = ichiffrageService.getProfil(16L); Ressource ress = new
			 * Ressource(); ress.setNom(user.getUsername().substring(8, 13));
			 * ress.setPrenom(user.getUsername().substring(0, 8)); ress.setProfil(profil);
			 * 
			 * ressourceRepository.save(ress);
			 */
			
			if(bindingResult.hasErrors()) {
				
				return "/fragments/new_user";
			}
			
			//ichiffrageService.
			ichiffrageService.saveUser(user);
			
			
			return "redirect:/listUsers";
		}
		
		@RequestMapping(value="/editUser/{id}")
		private String editUserPage(@PathVariable(name="id") Long id, Model model) {
			
			User user = ichiffrageService.getUser(id);
			
			
			
			List<Role> role = ichiffrageService.listRole();
		    model.addAttribute("user", user);
		    model.addAttribute("role", role);
		   // model.addAttribute("userRoles", ichiffrageService.getUserRoles(user));
		    //model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
		    //return "/fragments/userEdit";
			
			
			return "/fragments/edit_user";
		}
		
		@RequestMapping("/role/assign/{userId}/{roleId}")
		public String assignRole(@PathVariable Long userId, 
		                         @PathVariable Long roleId){
			ichiffrageService.assignUserRole(userId, roleId);
		    return "redirect:/listUsers/"+userId;
		}
		
		@RequestMapping("/role/unassign/{userId}/{roleId}")
		public String unassignRole(@PathVariable Long userId,
		                           @PathVariable Long roleId){
			ichiffrageService.unassignUserRole(userId, roleId);
		    return "redirect:/listUsers/"+userId;
		}
		
		@RequestMapping(value="/editUsers/{id}")
		private ModelAndView showEditUserPage(@PathVariable(name="id") Long id) {
			
			ModelAndView mav = new ModelAndView("fragments/edit_userRole");
			
			User user = ichiffrageService.getUser(id);
			mav.addObject("user", user);
		    //mav.addObject("userRoles", ichiffrageService.getUserRoles(user));
		    
		    mav.addObject("userRoles", ichiffrageService.listRole());
			
			//List<Role> role = ichiffrageService.listRole();
			
			//model.addAttribute("listRole", role);
		
			//mav.addObject("listRole", role);
			
			//mav.addObject("user", user);
			
			
			return mav;
		}
		
		
		@RequestMapping(value="/deleteUser/{id}")
		private String deleteUsers(@PathVariable(name="id") Long id) {
			
			ichiffrageService.deleteUser(id);
			
			
			return "redirect:/listUsers";
		}
	
	
	
	
	
	
	//Action CONTROLLER
	
		@RequestMapping(value="/newAction")
		public String showNewActionPage(Model model) {
			
			
			Action action = new Action();
			
			
			model.addAttribute("action", action);
			return "/fragments/new_action";
		}
		
		
		@RequestMapping(value="/saveAction")
		private String saveAct(@Valid @ModelAttribute("action") Action action, BindingResult bindingResult) {
			
			
			if(bindingResult.hasErrors()) {
				
				return "/fragments/new_action";
			}
			
			ichiffrageService.saveAction(action);
			
			return "redirect:/listActions";
		}
		
		@RequestMapping(value="/editAction/{id}")
		private ModelAndView showEditActionPage(@PathVariable(name="id") Long id) {
			
			ModelAndView mav = new ModelAndView("fragments/edit_action");
			
			Action action = ichiffrageService.getAction(id);
			
		
			
			mav.addObject("action", action);
			
			
			return mav;
		}
		
		
		@RequestMapping(value="/deleteAction/{id}")
		private String deleteAction(@PathVariable(name="id") Long id) {
			
			ichiffrageService.deleteAction(id);
			
			
			return "redirect:/listActions";
		}
	
	
	
	
	
	//TYPE ACTIVITE CONTROLLER
	
	
	@RequestMapping(value="/newTypeAct")
	public String showNewTypeActivitePage(Model model) {
		
		TypeActivite typeAct = new TypeActivite();
		
		
		model.addAttribute("typeActivite", typeAct);
		
		

		
		return "/fragments/new_typeActiv";
	}
	
	
	@RequestMapping(value="/saveTypeAct")
	private String saveTypActiv(@Valid @ModelAttribute("activite") TypeActivite typeAct, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "/fragments/new_typeActiv";
		}
		
		ichiffrageService.saveTypeActivite(typeAct);
		
		return "redirect:/listTypeAct";
	}
	
	@RequestMapping(value="/editTypeAct/{id}")
	private ModelAndView showEditTypeActPage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_TypeAct");
		
		TypeActivite typeAct = ichiffrageService.getTypeActivite(id);
		
		
		
		
		mav.addObject("typeActivite", typeAct);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteTypeAct/{id}")
	private String deleteTypActiv(@PathVariable(name="id") Long id) {
		
		
		List<Activite> la = ichiffrageService.listActivite();
		
		for (Activite activite : la) {
			
			while (activite.getTypeActivite().getId().equals(id)) {
				
				System.out.println("CE TYPE D'ACTIVITE CONCERNE UNE ACTIVITE");
				return "/delete";
			}
		}
		
		ichiffrageService.deleteTypeActivite(id);
		
		
		return "redirect:/listTypeAct";
	}

	
	
	//TYPE PROJET CONTROLLER

	@RequestMapping(value="/newTypeFP")
	public String showNewTypeFicheProjetPage(Model model) {
		
		
		TypeProjet typeProjet = new TypeProjet();
		model.addAttribute("typeProjet", typeProjet);
		
		List<Offre> offre = ichiffrageService.listOffre(null);
		model.addAttribute("offre", offre);
		
		
		
		return "/fragments/new_typeFicheProj";
	}
	
	
	@RequestMapping(value="/saveTypeFP")
	private String saveTypFicheProj(@Valid @ModelAttribute("typeProjet") TypeProjet typeProjet, BindingResult bindingResult) {
		
		
		if(bindingResult.hasErrors()) {
			
			return "/fragments/new_typeFicheProj";
		}
		
		else {
			
			ichiffrageService.saveTypeProjet(typeProjet);
			
			return "redirect:/listTypeProj";
		}
		
		
	}
	
	@RequestMapping(value="/editTypeFP/{id}")
	private ModelAndView showEditTypeFicheProjPage(@PathVariable(name="id") Long id) {
		
		ModelAndView mav = new ModelAndView("fragments/edit_typeFicheProj");
		
		TypeProjet typeProjet = ichiffrageService.getTypeProjet(id);
		
		List<Offre> offre = ichiffrageService.listOffre(null);
		
		

		
		mav.addObject("offre", offre);
		
		
		mav.addObject("typeProjet", typeProjet);
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/deleteTypeFP/{id}")
	private String deleteTypFicheProj(@PathVariable(name="id") Long id) {
		
		
		List<FicheProjet> lfp = ichiffrageService.listFicheProjet();
		
		for (FicheProjet ficheProjet : lfp) {
			
			
			
			while (ficheProjet.getTypeProjet().getId().equals(id)) {
				
				System.out.println("YOU CANT DELETE THIS!!!!");
				return "/delete";
			}
			
			/*if(ficheProjet.getTypeProjet().getId().equals(id)) {
				
				System.out.println("YOU CANT DELETE THIS!!!!");
				
				return "/delete";
			}*/
			
			
			
		}
		
		ichiffrageService.deleteTypeProjet(id);
		
		
		return "redirect:/listTypeProj";
	}
	
}
