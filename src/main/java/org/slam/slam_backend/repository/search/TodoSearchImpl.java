package org.slam.slam_backend.repository.search;

import lombok.extern.slf4j.Slf4j;

import org.slam.slam_backend.entity.Todo;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TodoSearchImpl implements TodoSearch {

    //@Autowired
    //private TodoRepository todoRepositoryl;

    //@Autowired
    //private DSLContext dsl;


    public TodoSearchImpl() {
        //super(Todo.class);
    }

    @Override
    public java.util.List<Todo> search1() {
        log.info("search1 =======================================");
        //Todos todos = new Todos();

        //return dsl.selectFrom("tbl_Todo").where(todos.TNO.eq(2L)).fetchInto(Todo.class);
        return null;
    }

}
