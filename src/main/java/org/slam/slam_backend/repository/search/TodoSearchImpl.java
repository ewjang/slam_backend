package org.slam.slam_backend.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.slam.slam_backend.entity.QTodo;
import org.slam.slam_backend.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


@Slf4j
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {
        log.info("search1 =======================================");

        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(1,10, Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        query.fetch(); //목록 데이터 불러올 때 사용
        query.fetchCount();

        return null;
    }

}
