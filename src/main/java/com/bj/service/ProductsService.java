package com.bj.service;

import com.bj.pojo.Products;

public interface ProductsService {
    Products selectByPrimaryKey(Integer pid);
}
