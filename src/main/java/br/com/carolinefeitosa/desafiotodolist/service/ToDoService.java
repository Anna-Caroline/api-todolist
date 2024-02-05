package br.com.carolinefeitosa.desafiotodolist.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.carolinefeitosa.desafiotodolist.entity.ToDo;
import br.com.carolinefeitosa.desafiotodolist.exception.BadRequestException;
import br.com.carolinefeitosa.desafiotodolist.repository.ToDoRepository;



@Service
public class ToDoService {

    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> list() {
        Sort sort = Sort.by(Direction.DESC, "priority")
            .and(Sort.by(Direction.ASC, "id"));
    
        return toDoRepository.findAll(sort);
    }

    public List<ToDo> create(ToDo toDo) {
        toDoRepository.save(toDo);
        return list();
    }
   
    public List<ToDo> update(Long id, ToDo toDo) {
        toDoRepository.findById(id).ifPresentOrElse((existingTodo) -> {
          toDo.setId(id);
          toDoRepository.save(toDo);
        },  () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
          });
      

    return list();

    }

    public List<ToDo> delete(Long id) {
        toDoRepository.findById(id).ifPresentOrElse((existingTodo) -> toDoRepository.delete(existingTodo), () -> {
            throw new BadRequestException("Todo %d não existe! ".formatted(id));
          });
        return list();
      }
    }
