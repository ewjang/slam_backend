package org.slam.slam_backend.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.TodoDTO;
import org.slam.slam_backend.dto.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {
    @Autowired
    TodoService todoService;

    @Test
    public void testGet() {
        Long tno = 21L;
        log.info(todoService.get(tno));
    }

    @Test
    public void testRegister() {

        for(int i = 1 ; i < 200 ; i++) {
            TodoDTO todoDTO = TodoDTO.builder()
                    .title("Title ... " + i)
                    .content("Content ..." + i )
                    .dueDate(LocalDate.of(2024,12,07))
                    .build();

            log.info(todoService.register(todoDTO));
        }


    }


    @Test
    public void testGetList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        log.info(todoService.getList(pageRequestDTO));
    }

}
