package org.slam.slam_backend.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.domain.*;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.PageResponseDTO;
import org.slam.slam_backend.dto.ProductDTO;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.*;


@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO) {

        log.info("---------searchList ------------");
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1, pageRequestDTO.getSize(), Sort.by("pno").descending());
        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.imageList, productImage); //ElementCollection 으로 dsl 사용방법

        query.where(productImage.ord.eq(0));


        getQuerydsl().applyPagination(pageable, query);

        List<Tuple> productList = query.select(product, productImage).fetch();

        long count = query.fetchCount();

        log.info("==========");
        log.info(productList);

        return null;

    }
}
