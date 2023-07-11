package sg.edu.nus.iss.pokemondemo.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PokemonRepository {
    
    @Autowired
    MongoTemplate mTemplate;

    /**
     * db.pokemon.aggregate([
            { $unwind: '$type'},
            { $group: { _id: '$type'} },
            { $sort: { _id: 1 }}
        ])
     */

    public List<Document> getAllTypes() {
        UnwindOperation uo = Aggregation.unwind("type");
        GroupOperation go = Aggregation.group("type");
        SortOperation so = Aggregation.sort(Sort.by(Direction.ASC, "_id"));

        Aggregation pipeline = Aggregation.newAggregation(uo, go, so);

        return mTemplate.aggregate(pipeline, "pokemon", Document.class).getMappedResults();
    }

    /*
     * db.pokemon.aggregate([
            {$match: {type: "$type"}},
            {$project: {id: 1, name: 1, img: 1, type: 1}},
            {$sort: {id: 1}}
        ])
     */

    public List<Document> getPokemonByType(String tp) {
        MatchOperation matchType = Aggregation.match(
                                        Criteria.where("type").is(tp)
                                    );
        ProjectionOperation projection = Aggregation.project("id", "name", "img", "type");
        SortOperation sortId = Aggregation.sort(Direction.ASC, "id");

        Aggregation pipeline = Aggregation.newAggregation(matchType, projection, sortId);

        return mTemplate.aggregate(pipeline, "pokemon", Document.class).getMappedResults();
    }
}
