package org.slam.slam_backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.slam_backend.dto.PageRequestDTO;
import org.slam.slam_backend.dto.PageResponseDTO;
import org.slam.slam_backend.dto.TodoDTO;
import org.slam.slam_backend.dto.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno) {
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("list .... " + pageRequestDTO);

        return todoService.getList(pageRequestDTO);
    }

}
