package com.crio.starter.repository;

import com.crio.starter.data.DatabaseSequenceEntity;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoTemplate mongoTemplate;

    public long generateSequence(String seqName) {

        long num;
        Query q = new Query(Criteria.where("id").is(seqName));
        DatabaseSequenceEntity dbscount =
                mongoTemplate.findAndModify(q, new Update().inc("SeqNum", 1),
                        FindAndModifyOptions.options().returnNew(true).upsert(true),
                        DatabaseSequenceEntity.class, "database_sequence");
        if (!Objects.isNull(dbscount)) {
            num = dbscount.getSeqNum();

        } else {
            num = 1;
        }
        return num;

    }

}
