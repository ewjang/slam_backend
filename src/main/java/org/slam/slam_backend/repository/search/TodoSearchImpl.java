package org.slam.slam_backend.repository.search;

import lombok.extern.log4j.Log4j2;

import com.querydsl.jpa.JPQLQuery;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.domain.QTodo;
import org.slam.slam_backend.domain.Todo;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    //@Autowired
    //private TodoRepository todoRepositoryl;

    //@Autowired
    //private DSLContext dsl;

    public TodoSearchImpl() {
        super(Todo.class);
    }
    @Override
    public Page<Todo> search1(PageRequestDTO pageRequestDTO) {

        log.info("search1............");
        log.info(pageRequestDTO);

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        //query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1, pageRequestDTO.getSize(), Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

}
