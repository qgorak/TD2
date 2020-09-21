package edu.TD2.controllers;
import edu.TD2.models.Organization;
import edu.TD2.models.WebConfig;
import edu.TD2.repositories.OrgaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
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
    
	@RequestMapping("orga/new/{nom}")
    public @ResponseBody String newOrga(@PathVariable String nom) {
		Organization e = new Organization("nom");
        repo.saveAndFlush(e);
        return e+" ajoutée.";
        
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



