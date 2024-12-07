package org.slam.slam_backend.repository;


import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.logging.annotations.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slam.slam_backend.entity.Todo;
import org.slam.slam_backend.repository.search.TodoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoSearch todoSearch;

    @Test
    public void test1() {
        Assertions.assertNotNull(todoRepository);
        log.info("test1 : " + todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        final Todo todo = Todo.builder().title("title test").content("Content..").dueDate(LocalDate.of(2024,12,30)).build();
        Todo result = todoRepository.save(todo);

        log.info("testInfsert : "+ result);

    }

    @Test
    public void testRead() {
        Long tno = 2L;

        Optional<Todo> result =  todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info("testRead : " + todo);

    }

    @Test
    public void testUpdate() {
        Long tno = 2L;
        Optional<Todo> result =  todoRepository.findById(tno);
        Todo todo = result.orElseThrow();

        todo.changeTitle("update Title");
        todo.changeContent("update Content");
        todo.changeComplete(true);
        todoRepository.save(todo);
        //먼저 로딩하고 엔티티 객체를 변경 /setter
    }

    @Test
    public void testPaging() {
        //페이지 번호는 0부터 시작
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        log.info("AA : " + result.getTotalElements());
        log.info("AA : " + result.getContent());
    }

    /*
    @Test
    public void testSearch1() {
        todoSearch.search1();
    }

     */
}
