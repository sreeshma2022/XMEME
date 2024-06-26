package com.crio.starter.repository;

import com.crio.starter.data.XmemeEntity;
import com.crio.starter.exceptions.PostNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class XmemeRepository implements IXmemeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<XmemeEntity> getPosts() {

        Query q = new Query().with(Sort.by(Direction.DESC, "id")).limit(100);
        List<XmemeEntity> posts = mongoTemplate.find(q, XmemeEntity.class, "usersPosts");
        Collections.reverse(posts);
        return posts;
    }

    @Override
    public XmemeEntity getPost(long id) throws PostNotFoundException {
        Query q = new Query(Criteria.where("id").is(id));
        XmemeEntity post = mongoTemplate.findOne(q, XmemeEntity.class, "usersPosts");
        if (post == null) {
            throw new PostNotFoundException();
        }

        return post;
    }

    @Override
    public long savePost(XmemeEntity entity) {
        
        XmemeEntity savedPost = mongoTemplate.save(entity, "usersPosts");
        return savedPost.getId();
    }

    @Override
    public void updatePost(Map<String, Object> updates, long id) throws PostNotFoundException {
        Query q = new Query(Criteria.where("id").is(id));
        XmemeEntity post = mongoTemplate.findOne(q, XmemeEntity.class, "usersPosts");
        if (post == null) {
            throw new PostNotFoundException();
        }
        Object urlNew = updates.get("url");
        Object captionNew = updates.get("caption");

        if (urlNew != null)
            post.setUrl(urlNew.toString());

        if (captionNew != null)
            post.setCaption(captionNew.toString());

        mongoTemplate.save(post);

    }


}
