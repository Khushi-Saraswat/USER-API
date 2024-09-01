package com.pro.demo.Service;

import java.util.List;


import com.pro.demo.dto.ProductDto;
import com.pro.demo.dto.ProductResponse;

public interface ProductService {
     public Boolean saveProduct(ProductDto product);
     public List<ProductDto> getAllProducts();
   //  public Product updateProduct(Product product);
    public Boolean deleteProduct(Integer id);
    public ProductDto getProductById(Integer id);
    public ProductResponse getProductsWithPagination(int pageNo,int PageSize,String sortBy,String sortDir);
   
}
