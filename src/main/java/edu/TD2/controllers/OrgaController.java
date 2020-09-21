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
	public String displayNewOrga() {
		return "viewNewOrga";
	}
	
	@PostMapping("orga/newOrga")
    public RedirectView newOrga(@ModelAttribute Organization orga) {
		Organization e = orga;
        repo.saveAndFlush(e);
        return new RedirectView("/");
    }

	@RequestMapping("orga/delete/{id}")
    public RedirectView deleteOrga(@PathVariable int id) {
		repo.deleteById(id);
        return new RedirectView("/");
    }
	@RequestMapping("orga/display/{id}")
    public String displayOrga(@PathVariable int id,Model model) {
		Organization opt = repo.findById(id);
		model.addAttribute("id", opt.getId());
        model.addAttribute("name", opt.getName());
        model.addAttribute("domain", opt.getDomain());
        model.addAttribute("aliases", opt.getAliases());
        return "viewDisplayOrga";
    }
	@RequestMapping("orga/edit/{id}")
    public String displayEditOrga(@PathVariable int id,Model model) {
		Organization opt = repo.findById(id);
		model.addAttribute("id", opt.getId());
        model.addAttribute("name", opt.getName());
        model.addAttribute("domain", opt.getDomain());
        model.addAttribute("aliases", opt.getAliases());
        return "viewEditOrga";
    }
	@PostMapping("orga/updateOrga/{id}")
    public RedirectView updateOrga(@PathVariable int id,@ModelAttribute Organization orga) {
		Organization orgaToUpdate = repo.findById(id);
		orgaToUpdate.setName(orga.getName());
		orgaToUpdate.setDomain(orga.getDomain());
		orgaToUpdate.setAliases(orga.getAliases());
		repo.save(orgaToUpdate);
        return new RedirectView("/");
    }
	//@RequestMapping("orga/{id}")
  //  public @ResponseBody String getOrga(@PathVariable int id) {
//		Optional<Organization> opt = repo.findById(id);
 //       if(opt.isPresent()) {
  //      	return opt.get()+"";
 //       }
 //       return "organisation non trouvée";
        
  //  }
	



	
	
	

}