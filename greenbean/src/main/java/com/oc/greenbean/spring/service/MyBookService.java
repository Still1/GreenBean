package com.oc.greenbean.spring.service;

import com.oc.greenbean.mybatis.mapper.MyBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyBookService {

    private MyBookMapper myBookMapper;

    @Value("${bookPicturesPath}")
    private String bookPicturesPath;

    @Autowired
    public MyBookService(MyBookMapper myBookMapper) {
        this.myBookMapper = myBookMapper;
    }

    public Integer getMyBookCount(Short type, Integer userId) {
        return myBookMapper.getMyBookCount(type, userId);
    }

    public List<String> getMyBookPictures(Short type, Integer userId) {
        List<String> myBookPictures = myBookMapper.getMyBookPictures(type, userId);
        String userHomePath = System.getProperty("user.home").replaceAll("\\\\", "/");
        String bookPicturesPath = userHomePath + this.bookPicturesPath;
        List<String> myBookPicturesRealPath = new ArrayList<>();
        for(String myBookPicture : myBookPictures) {
            myBookPicturesRealPath.add(bookPicturesPath + myBookPicture);
        }
        return myBookPicturesRealPath;
    }
}
