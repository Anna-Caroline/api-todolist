package br.com.carolinefeitosa.desafiotodolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carolinefeitosa.desafiotodolist.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long>{


}

