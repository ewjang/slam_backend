package org.slam.slam_backend.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.slam.slam_backend.domain.Product;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.ProductDTO;
import org.slam.slam_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testInsert() {

        for(int i = 0 ; i < 10 ; i++) {
            Product product = Product.builder().pname("Test " + i).pdesc("Test Desc  " + i).price(1000).build();

            product.addImageString(UUID.randomUUID()+ "_" + "IMAGE1.jpg");
            product.addImageString(UUID.randomUUID()+ "_" + "IMAGE2.jpg");

            productRepository.save(product);
        }


    }

    @Test
    @Transactional
    public void testRead() {
        Long pno = 1L;
        Optional<Product> result = productRepository.findById(pno);

        Product product = result.orElseThrow();

        log.info(product);
        log.info(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        Long pno = 2L;
        productRepository.updatgeToDelete(2L, true);
    }

    @Test
    public void testUpdate() {
        Product product = productRepository.selectOne(1L).get();
        product.changePrice(3000);
        product.clearList();

        product.addImageString(UUID.randomUUID()+ "_" + "PIAMGE1.jpg");
        product.addImageString(UUID.randomUUID()+ "_" + "PIAMGE2.jpg");
        product.addImageString(UUID.randomUUID()+ "_" + "PIAMGE3.jpg");

        productRepository.save(product);
    }

    @Test
    public void testList() {
      Pageable pageable =  PageRequest.of(0,10, Sort.by("pno").descending());

      Page<Object[]> result = productRepository.selectList(pageable);

      result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        productRepository.searchList(pageRequestDTO);
    }

    @Test
    public void testRegister() {
        ProductDTO productDTO = ProductDTO.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품입니다.")
                .price(1000)
                .build();

        productDTO.setUplaodFileNames(
                java.util.List.of(UUID.randomUUID()+"_"+"test1.jpg", UUID.randomUUID()+"_"+"test2.jps")
        );

        productService.register(productDTO);

    }
}
