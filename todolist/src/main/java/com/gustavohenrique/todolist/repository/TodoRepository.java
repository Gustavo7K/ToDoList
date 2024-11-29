package com.gustavohenrique.todolist.repository;

import com.gustavohenrique.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID>{
    List<Todo> findByRealizado(boolean realizado); //personalizado para buscar tarefas realizadas
}
