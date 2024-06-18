package org.slam.slam_backend.repository.search;

import org.slam.slam_backend.entity.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1();
}
