package sg.edu.nus.iss.pokemondemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.pokemondemo.service.PokemonService;

@Controller
@RequestMapping
public class PokemonController {
    
    @Autowired
    PokemonService pService;

    @GetMapping
    public String getLanding(Model m) {
        m.addAttribute("types", pService.getAllTypes());
        return "index";
    }

    @GetMapping("/types/{tp}")
    public String getPokemonByType(@PathVariable String tp, Model m) {
        m.addAttribute("type", tp);
        m.addAttribute("pokelist", pService.getPokemonByType(tp));
        return "pokemonbytype";
    }
}
