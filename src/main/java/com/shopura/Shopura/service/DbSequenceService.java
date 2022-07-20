package com.shopura.Shopura.service;

import com.shopura.Shopura.entity.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Service
@EnableAutoConfiguration

public class DbSequenceService {


    @Autowired
    private MongoOperations mongoOperations;


    @Autowired
    public DbSequenceService(MongoOperations mongoOperations) {
        this.mongoOperations= mongoOperations;
    }


    public int generateSequence(String seqName) {
        DbSequence counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq(): 1;
    }
}
