package com.bj.mapper;

import com.bj.pojo.Products;

public interface ProductsMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Products record);

    int insertSelective(Products record);

    Products selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Products record);

    int updateByPrimaryKeyWithBLOBs(Products record);

    int updateByPrimaryKey(Products record);
}