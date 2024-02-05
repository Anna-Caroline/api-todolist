package br.com.carolinefeitosa.desafiotodolist.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carolinefeitosa.desafiotodolist.entity.ToDo;
import br.com.carolinefeitosa.desafiotodolist.service.ToDoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/todos")
public class ToDoController {
  @Autowired
  private ToDoService todoService;

  @PostMapping
  ResponseEntity<List<ToDo>> create(@Valid @RequestBody ToDo toDo) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(todoService.create(toDo));
  }

  @GetMapping
  List<ToDo> list() {
    return todoService.list();
  }

  @PutMapping("{id}")
  List<ToDo> update(@PathVariable Long id, @RequestBody ToDo toDo) {
    return todoService.update(id, toDo);
  }

  @DeleteMapping("{id}")
  List<ToDo> delete(@PathVariable Long id) {
    return todoService.delete(id);
  }
}