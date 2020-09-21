package edu.TD2.controllers;
import edu.TD2.models.Organization;
import edu.TD2.models.User;
import edu.TD2.models.WebConfig;
import edu.TD2.repositories.OrgaRepository;
import edu.TD2.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Controller



 
public class OrgaController {
	@Autowired
    private OrgaRepository repo;
	@Autowired
	private UserRepository uRepo;
	
	@RequestMapping("/")
    public String index(Model model) {
		 List<Organization> organizations = repo.findAll();
	     model.addAttribute("organizations", organizations);
        return "index";
        
    }
	@RequestMapping("orga/new")
	public String addNewOrga() {
		return "viewNewOrga";
	}
	@RequestMapping("orga/newOrga")
    public RedirectView New(@RequestParam String name,@RequestParam String domain,@RequestParam String aliases,@ModelAttribute("organizations") Organization orga) {
		Organization e = orga;
		e.setName(name);
		e.setDomain(domain);
		e.setAliases(aliases);
        repo.saveAndFlush(e);
        return new RedirectView("/");
    }

	@RequestMapping("orga/delete/{id}")
    public RedirectView New(@PathVariable int id) {
		repo.deleteById(id);
        return new RedirectView("/");
    }
	@RequestMapping("orga/{id}")
    public @ResponseBody String getOrga(@PathVariable int id) {
		Optional<Organization> opt = repo.findById(id);
        if(opt.isPresent()) {
        	return opt.get()+"";
        }
        return "organisation non trouvée";
        
    }
	



	
	
	

}