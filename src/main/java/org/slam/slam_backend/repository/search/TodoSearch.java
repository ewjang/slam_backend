package org.slam.slam_backend.repository.search;

import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.entity.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoSearch {
    Page<Todo> search1(PageRequestDTO pageRequestDTO);

}
