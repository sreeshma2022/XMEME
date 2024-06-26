package com.crio.starter.service;

import com.crio.starter.exceptions.InvalidPostException;
import com.crio.starter.exceptions.PostAlreadyExistException;
import com.crio.starter.exceptions.PostNotFoundException;
import com.crio.starter.exchange.XmemeRequestDto;
import java.util.List;
import java.util.Map;

public interface IXmemeService {

    List<XmemeRequestDto> getPosts();

    XmemeRequestDto getPost(long id) throws PostNotFoundException;

    long savePost(XmemeRequestDto request) throws InvalidPostException, PostAlreadyExistException;

    void updatePost(Map<String, Object> updates, long id)
            throws PostNotFoundException, InvalidPostException;



}
