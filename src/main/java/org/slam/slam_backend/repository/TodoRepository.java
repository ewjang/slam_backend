package org.slam.slam_backend.repository;

import org.slam.slam_backend.entity.Todo;
import org.slam.slam_backend.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>  {



}
