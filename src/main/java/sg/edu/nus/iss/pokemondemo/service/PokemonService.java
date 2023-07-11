package sg.edu.nus.iss.pokemondemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.pokemondemo.model.Pokemon;
import sg.edu.nus.iss.pokemondemo.repository.PokemonRepository;


@Service
public class PokemonService {
    
    @Autowired
    PokemonRepository pRepo;

    public List<String> getAllTypes() {
        List<String> typeList = new ArrayList<String>();

        for (Document type: pRepo.getAllTypes()) {
            typeList.add(type.getString("_id"));
        }

        return typeList;
    }

    public void getPokemonByType(String tp) {
        System.out.println(pRepo.getPokemonByType(tp).toString());
    }
}
