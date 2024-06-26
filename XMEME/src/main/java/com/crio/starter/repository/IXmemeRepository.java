package com.crio.starter.repository;

import com.crio.starter.data.XmemeEntity;
import com.crio.starter.exceptions.PostNotFoundException;
import java.util.List;
import java.util.Map;


public interface IXmemeRepository {

    List<XmemeEntity> getPosts();

    XmemeEntity getPost(long id) throws PostNotFoundException;

    long savePost(XmemeEntity entity);

    void updatePost(Map<String, Object> updates, long id) throws PostNotFoundException;

}
