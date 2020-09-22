package edu.TD2.controllers;
import edu.TD2.models.Groupe;
import edu.TD2.models.Organization;
import edu.TD2.models.User;
import edu.TD2.models.WebConfig;
import edu.TD2.repositories.GroupRepository;
import edu.TD2.repositories.OrgaRepository;
import edu.TD2.repositories.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
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
	@Autowired
	private GroupRepository gRepo;
	
	

    
    
	@RequestMapping("/orgas/")
    public String index(Model model, @Param("keyword") String keyword) {

		if (keyword != null) {
			List<Organization> organizations = repo.search(keyword);
	        model.addAttribute("organizations", organizations);
	        return "index";
	        }
	    List<Organization> organizations = repo.findAll();

	    model.addAttribute("organizations", organizations);
        return "index";
       }
	
	
	@RequestMapping("orgas/new")
	public String displayNewOrga() {
		return "viewNewOrga";
	}
	
	@PostMapping("orgas/newOrga")
    public RedirectView newOrga(@ModelAttribute Organization orga) {
		Organization e = orga;
        repo.saveAndFlush(e);
        return new RedirectView("/orgas/");
    }

	@RequestMapping("orgas/delete/{id}")
    public RedirectView deleteOrga(@PathVariable int id) {
		repo.deleteById(id);
        return new RedirectView("/orgas/");
    }
	@RequestMapping("orgas/display/{id}")
    public String displayOrga(@PathVariable int id,Model model) {
		Organization opt = repo.findById(id);
		model.addAttribute("id", opt.getId());
        model.addAttribute("name", opt.getName());
        model.addAttribute("domain", opt.getDomain());
        model.addAttribute("aliases", opt.getAliases());
        return "viewDisplayOrga";
    }
	@RequestMapping("orgas/edit/{id}")
    public String displayEditOrga(@PathVariable int id,Model model) {
		Organization opt = repo.findById(id);
		model.addAttribute("id", opt.getId());
        model.addAttribute("name", opt.getName());
        model.addAttribute("domain", opt.getDomain());
        model.addAttribute("aliases", opt.getAliases());
        return "viewEditOrga";
    }
	@PostMapping("orgas/updateOrga/{id}")
    public RedirectView updateOrga(@PathVariable int id,@ModelAttribute Organization orga) {
		Organization orgaToUpdate = repo.findById(id);
		orgaToUpdate.setName(orga.getName());
		orgaToUpdate.setDomain(orga.getDomain());
		orgaToUpdate.setAliases(orga.getAliases());
		repo.save(orgaToUpdate);
        return new RedirectView("/orgas/");
    }
	@RequestMapping("orgas/details/{id}")
    public String displayDetailsOrga(@PathVariable int id,@Param("keyword") String keyword,Model models,Model model) {
		Organization opt = repo.findById(id);
		models.addAttribute("organization", opt);
		
		if (keyword != null) {
			
			List<Organization> organizations = repo.search(keyword);
	        model.addAttribute("organizations", organizations);
	        
	        return "viewDetailsOrga";
	        }
	    List<Organization> organizations = repo.findAll();

	    models.addAttribute("organizations", organizations);
	    model.addAttribute("organization",opt);
        return "viewDetailsOrga";
    }
	//@RequestMapping("orga/{id}")

	



	
	
	

}