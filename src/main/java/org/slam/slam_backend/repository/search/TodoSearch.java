package org.slam.slam_backend.repository.search;

import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1(PageRequestDTO pageRequestDTO);

}
