package sg.edu.nus.iss.pokemondemo.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    private String id;
    private String name;
    private String img;
    private List<String> type;
}
