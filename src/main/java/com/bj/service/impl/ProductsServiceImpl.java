package com.bj.service.impl;

import com.bj.mapper.ProductsMapper;
import com.bj.pojo.Products;
import com.bj.service.ProductsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ProductsServiceImpl implements ProductsService {
    @Resource
    private ProductsMapper productsMapper;
    @Override
    public Products selectByPrimaryKey(Integer pid) {
        return productsMapper.selectByPrimaryKey(pid);
    }
}
