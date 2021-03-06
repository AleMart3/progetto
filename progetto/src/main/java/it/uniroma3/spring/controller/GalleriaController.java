package it.uniroma3.spring.controller;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.model.Quadro;
import it.uniroma3.spring.service.AutoreService;
import it.uniroma3.spring.service.QuadroService;




@Controller
public class GalleriaController  {

	@Autowired
	private QuadroService quadroservice; 
	@Autowired
	private AutoreService autoreservice; 



	@RequestMapping("/mostraGalleria")
	public String mostraGalleria(){
		return "metodiDiVisualizzazione";
	}

	@GetMapping("/ricercaAutoreId")
	public String ricercaAutoriPerId(@RequestParam("autoreId") Long id, Model model){
		model.addAttribute("autori", autoreservice.findbyId(id));
		return "MostraAutori";
	}

	@PostMapping("/ricercaAutoriCognome")
	public String ricercaAutoriPerCognome(@RequestParam("cognome") String cognome, Model model){
		List<Autore> autori = autoreservice.fingByCognome((cognome.substring(0,1).toUpperCase() + cognome.substring(1).toLowerCase()));
		model.addAttribute("autori", autori);
		return "MostraAutori";
	}



	@GetMapping("/mostraNazionalita")
	public String mostraNazionalita(Model model){
		Iterable<Autore> autori = autoreservice.findAll();
		Set<String> nazionalita = new TreeSet<>();
		for(Autore autore:autori){
			nazionalita.add(autore.getNazionalita());
		}
		model.addAttribute("nazionalita", nazionalita);
		return "mostraNazionalitaAutori";

	}

	@GetMapping("/ricercaAutoriNazionalita")
	public String ricercaAutoriPerNazionalita(@RequestParam("nazionalita") String nazionalita, Model model){
		List<Autore> autori = autoreservice.fingByNazionalita(nazionalita);
		model.addAttribute("autori", autori);
		return "MostraAutori";
	}

	@GetMapping("/ricercaQuadriAutore")
	public String ricercaQuadriPerAutore(@RequestParam("autoreId") Long id, Model model){
		Autore autore = autoreservice.findbyId(id);
		List<Quadro> quadri = autore.getQuadri();
		model.addAttribute("quadri",quadri);
		return "MostraQuadri";
	}

	@GetMapping("/galleriaautori")
	public String galleriaAutori(Model model) {
		model.addAttribute("autori",autoreservice.findAll());
		return "MostraAutori";
	}

	@GetMapping("/mostraAutori")
	public String mostraAutori(Model model) {
		model.addAttribute("autori",autoreservice.findAll());
		return "mostraAutoriQuadri";
	}

	@GetMapping("/mostraTecniche")
	public String mostraTecniche(Model model){
		Iterable<Quadro> quadri = quadroservice.findAll();
		Set<String> tecniche= new TreeSet<>();
		for(Quadro quadro:quadri){
			tecniche.add(quadro.getTecnica());
		}
		model.addAttribute("tecniche", tecniche);
		return "mostraTecnicheQuadri";

	}


	@GetMapping("/ricercaQuadriTecnica")
	public String ricercaTecnica(@RequestParam("tecnica") String tecnica, Model model){
		List<Quadro> quadri = quadroservice.findByTecnica(tecnica);
		model.addAttribute("quadri",quadri);
		return "MostraQuadri";
	}

	@GetMapping("/mostraAnni")
	public String mostraAnni(Model model){
		Iterable<Quadro> quadri = quadroservice.findAll();
		Set<Integer> anni= new TreeSet<>();
		for(Quadro quadro:quadri){
			anni.add(quadro.getAnnoRealizzazione());
		}
		model.addAttribute("anni", anni);
		return "mostraAnniDeiQuadri";

	}

	@GetMapping("/ricercaQuadriAnno")
	public String ricercaAnno(@RequestParam("anno") Integer anno, Model model){
		List<Quadro> quadri = quadroservice.findByAnnoRealizzazione(anno);
		model.addAttribute("quadri",quadri);
		return "MostraQuadri"; //era quadriFiltrati
	}




	@GetMapping("/galleriaquadri")
	public String mostraQuadri(Model model) {
		model.addAttribute("quadri",quadroservice.findAll());
		return "MostraQuadri";
	}


}
