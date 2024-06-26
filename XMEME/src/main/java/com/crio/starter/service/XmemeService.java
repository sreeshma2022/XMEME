package com.crio.starter.service;

import com.crio.starter.data.XmemeEntity;
import com.crio.starter.exceptions.InvalidPostException;
import com.crio.starter.exceptions.PostAlreadyExistException;
import com.crio.starter.exceptions.PostNotFoundException;
import com.crio.starter.exchange.XmemeRequestDto;
import com.crio.starter.repository.IXmemeRepository;
import com.crio.starter.repository.SequenceGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class XmemeService implements IXmemeService {

    @Autowired
    private IXmemeRepository xmemeRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    private ModelMapper mapper = new ModelMapper();



    // private Seq

    @Override
    public List<XmemeRequestDto> getPosts() {
        List<XmemeEntity> memeEntities = xmemeRepository.getPosts();
        List<XmemeRequestDto> posts = new ArrayList<>();
        Collections.reverse(memeEntities);
        for (XmemeEntity e : memeEntities) {
            posts.add(mapper.map(e, XmemeRequestDto.class));
        }
        return posts;
    }

    @Override
    public XmemeRequestDto getPost(long id) throws PostNotFoundException {
        XmemeEntity memeEntity = xmemeRepository.getPost(id);
        return mapper.map(memeEntity, XmemeRequestDto.class);
    }

    @Override
    public long savePost(XmemeRequestDto request) throws InvalidPostException , PostAlreadyExistException {
      
       
        validatePost(request);      
       
        // filter condition to check duplicates
        if(checkIfPostAlreadyExists(request)){
            throw new PostAlreadyExistException("The post you are trying to post already exist");
        }

        XmemeEntity memeEntity = XmemeEntity.builder()
                .id(sequenceGenerator.generateSequence(XmemeEntity.SEQUENCE_NAME))
                .name(request.getName()).url(request.getUrl()).caption(request.getCaption())
                .build();
        long postId = xmemeRepository.savePost(memeEntity);
        return postId;
    }

    @Override
    public void updatePost(Map<String, Object> updates, long id)
            throws PostNotFoundException, InvalidPostException {

        Object urlNew = updates.get("url");
        Object captionNew = updates.get("caption");

        if (urlNew == null) {
            throw new InvalidPostException("Update failed because given url is null");
        }
        if (captionNew == null) {
            throw new InvalidPostException("Update failed because given url is null");
        }

        xmemeRepository.updatePost(updates, id);

    }

    

    // validate the post

    private void validatePost(XmemeRequestDto request) throws InvalidPostException {
        String name = request.getName();
        String url = request.getUrl();
        String caption = request.getCaption();

        if (name == null || name.isEmpty()) {
            throw new InvalidPostException("Name cannot be null or empty");
        }
        if (url == null || url.isEmpty()) {
            throw new InvalidPostException("url cannot be null or empty");
        }
        if (caption == null || caption.isEmpty()) {
            throw new InvalidPostException("caption cannot be null or empty");
        }


    }
    // check if meme already exist
    private boolean checkIfPostAlreadyExists(XmemeRequestDto request){
        String name = request.getName();
        String url = request.getUrl();
        String caption = request.getCaption();

     
     
        List<XmemeEntity> memeEntities = xmemeRepository.getPosts();
        for(XmemeEntity memesFromDb : memeEntities){
            if(memesFromDb != null && memesFromDb.getName().equalsIgnoreCase(name) && 
             memesFromDb.getUrl().equalsIgnoreCase(url) && memesFromDb.getCaption().equalsIgnoreCase(caption)){
                return true;
             }
        }

        return false;
    }

}
