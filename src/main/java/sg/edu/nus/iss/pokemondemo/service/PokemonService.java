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

    public List<Pokemon> getPokemonByType(String tp) {
        List<Pokemon> pokeList = new ArrayList<Pokemon>();
        for (Document dPoke: pRepo.getPokemonByType(tp)) {
            pokeList.add(new Pokemon(
                        dPoke.getString("id"),
                        dPoke.getString("name"),
                        dPoke.getString("img"),
                        dPoke.getList("type", String.class)));
        }
        return pokeList;
    }
}
