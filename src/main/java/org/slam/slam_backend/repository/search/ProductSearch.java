package org.slam.slam_backend.repository.search;

import org.slam.slam_backend.domain.Todo;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.PageResponseDTO;
import org.slam.slam_backend.dto.ProductDTO;
import org.springframework.data.domain.Page;

public interface ProductSearch {
    PageResponseDTO<ProductDTO> searchList (PageRequestDTO pageRequestDTO);

}
