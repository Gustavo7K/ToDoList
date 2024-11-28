package com.gustavohenrique.todolist.repository;

import com.gustavohenrique.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID>{
}
