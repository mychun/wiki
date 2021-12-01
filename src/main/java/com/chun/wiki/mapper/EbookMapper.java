package com.chun.wiki.mapper;

import com.chun.wiki.domain.Ebook;

import java.util.List;

public interface EbookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ebook record);

    int insertSelective(Ebook record);

    Ebook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ebook record);

    int updateByPrimaryKey(Ebook record);

    List<Ebook> list();
}