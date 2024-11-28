package com.gustavohenrique.todolist.service;

import com.gustavohenrique.todolist.entity.Todo;
import com.gustavohenrique.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TodoService{
    /* Pensando em aprimorar o uso da lista de tarefas, após cada operação
    a aplicação irá retornar a lista completa */

    private final TodoRepository todoRepository; //injeção de dependencias

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);//salvar a tarefa
        return list();
    }

    public List<Todo> list(){
        Sort sort = Sort.by("prioridade").descending().and( //ordenação por prioridade
                Sort.by("nome").ascending() //ordenação por nome
        );
        return todoRepository.findAll(sort);
    }

    /* TODO: 28/11/2024
    public List<Todo> searchById(UUID id){
        Optional<Todo> todoO = todoRepository.findById(id);
        return list();
    }*/

    public List<Todo> update(UUID id, Todo todo){
        Optional<Todo> todoO = todoRepository.findById(id);
        todo.setId(id);
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(UUID id){
        todoRepository.deleteById(id);
        return list();
    }
}
