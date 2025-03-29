package org.slam.slam_backend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.domain.Product;
import org.slam.slam_backend.domain.ProductImage;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.PageResponseDTO;
import org.slam.slam_backend.dto.ProductDTO;
import org.slam.slam_backend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("pno").descending());

        Page<Object[]> result= productRepository.selectList(pageable);

        List<ProductDTO> dtoList = result.get().map(arr-> {
            ProductDTO productDTO = null;

            Product product = (Product)arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            productDTO = ProductDTO.builder().pno(product.getPno()).pname(product.getPname()).pdesc(product.getPdesc()).price(product.getPrice()).build();

            String imageStr = productImage.getFileName();
            productDTO.setUplaodFileNames(List.of(imageStr));

            return productDTO;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<ProductDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).build();
    }

    @Override
    public Long register(ProductDTO productDTO) {

        Product product = dtoToEntity(productDTO);

        log.info("-----------------");
        log.info(product);
        long pno = productRepository.save(product).getPno();

        return pno;
    }

    @Override
    public ProductDTO get(Long pno) {

        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        return entityToDTO(product);
    }

    @Override
    public void modify(ProductDTO productDTO) {
        //조회
        Optional<Product> result =productRepository.findById(productDTO.getPno());

        Product product = result.orElseThrow();
        //변경 내용 반영
        product.changePrice(productDTO.getPrice());
        product.changeName(productDTO.getPname());
        product.changeDesc(productDTO.getPdesc());
        product.changeDel(productDTO.isDelFlag());

        //이미지 처리
        List<String> uploadFileNames = productDTO.getUplaodFileNames();

        product.clearList();

        if(uploadFileNames != null && uploadFileNames.isEmpty()) {
            uploadFileNames.forEach(uploadName -> {
                product.addImageString(uploadName);
            });
        }

        //저장
        productRepository.save(product);
    }

    @Override
    public void remove(Long pno) {


        productRepository.deleteById(pno);
    }

    private ProductDTO entityToDTO(Product product) {

        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .delFlag(product.isDelFlag())
                .price(product.getPrice())
                .build();

        List<ProductImage> imageList = product.getImageList();

        if(imageList ==null || imageList.isEmpty()) {
            return productDTO;
        }

        List<String> fileNameList = imageList.stream().map(productImage ->
                productImage.getFileName()).toList();

        productDTO.setUplaodFileNames(fileNameList);

        return productDTO;
    }

    private Product dtoToEntity(ProductDTO productDTO) {

        Product product = Product.builder().pno(productDTO.getPno()).pname(productDTO.getPname()).pdesc(productDTO.getPdesc()).price(productDTO.getPrice()).build();

        List<String> uploadFileNames = productDTO.getUplaodFileNames();

        if(uploadFileNames == null || uploadFileNames.size() ==0 ) {
            return product;
        }

        uploadFileNames.forEach(fileName -> {
            product.addImageString(fileName);
        });

        return product;

    }



}
