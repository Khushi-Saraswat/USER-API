package com.pro.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.pro.demo.Model.Product;
import com.pro.demo.dto.ProductDto;
import com.pro.demo.dto.ProductResponse;
import com.pro.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ModelMapper mapper;

  @Override
  public Boolean saveProduct(ProductDto productdto) {
    /*
     * Product product=new Product();
     * product.setId(productdto.getId());
     * product.setName(productdto.getName());
     * product.setDescription(productdto.getDescription());
     * product.setPrice(productdto.getPrice());
     * product.setQuantity(productdto.getQuantity());
     * Product save=productRepository.save(product);
     * if(ObjectUtils.isEmpty(save)){
     * return false;
     * }
     * return true;
     */
    Product product = mapper.map(productdto, Product.class);
    Product save = productRepository.save(product);
    if (ObjectUtils.isEmpty(save)) {
      return false;
    }
    return true;
  }

  @Override
  public List<ProductDto> getAllProducts() {
    List<Product> productList = productRepository.findAll();
    List<ProductDto> dto = productList.stream().map(product -> mapper.map(product, ProductDto.class))
        .collect(Collectors.toList());
    return dto;
  }

  @Override
  public ProductDto getProductById(Integer id) {
    Optional<Product> findByProduct = productRepository.findById(id);
    if (findByProduct.isPresent()) {
      Product product = findByProduct.get();
      ProductDto productDto = mapper.map(product, ProductDto.class);
      return productDto;
    }
    return null;
  }

  @Override
  public Boolean deleteProduct(Integer id) {
    Optional<Product> findByProduct = productRepository.findById(id);
    if (findByProduct.isPresent()) {
      Product product = findByProduct.get();
      productRepository.delete(product);
      return true;
    }
    return false;
  }

  @Override
  public ProductResponse getProductsWithPagination(int pageNo, int PageSize, String sortBy, String sortDir) {

    // Sort sort=Sort.by(sortBy).ascending();
    // Sort sort2=Sort.by(sortBy).descending();
    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, PageSize, sort);
    Page<Product> page = productRepository.findAll(pageable);
    List<Product> products = page.getContent();
    List<ProductDto> productdto = products.stream().map(product -> mapper.map(product, ProductDto.class))
        .toList();
    long TotalElements = page.getTotalElements();
    int totalPages = page.getTotalPages();
    boolean first = page.isFirst();
    boolean last = page.isLast();
    ProductResponse productResponse = ProductResponse.builder().products(productdto).totalElements(TotalElements)
        .totalPages(totalPages)
        .pageNo(pageNo).isFirst(first).isLast(last).pageNo(pageNo).PageSize(PageSize).build();
    return productResponse;

  }

}
