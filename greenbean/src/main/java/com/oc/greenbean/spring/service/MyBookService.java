package com.oc.greenbean.spring.service;

import com.oc.greenbean.mybatis.mapper.MyBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {

    private MyBookMapper myBookMapper;


    @Autowired
    public MyBookService(MyBookMapper myBookMapper) {
        this.myBookMapper = myBookMapper;
    }

    public Integer getMyBookCount(Short type, Integer userId) {
        return myBookMapper.getMyBookCount(type, userId);
    }

    public List<String> getMyBookPictures(Short type, Integer userId) {
        return myBookMapper.getMyBookPictures(type, userId);
    }
}
