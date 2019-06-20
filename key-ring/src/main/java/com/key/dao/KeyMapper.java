package com.key.dao;

import com.key.pojo.Key;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Key record);

    int insertSelective(Key record);

    Key selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Key record);

    int updateByPrimaryKey(Key record);

    int batchDelete(@Param("idArr")List<Integer> idArr);

    List<Key> list(@Param("key")Key key);
}