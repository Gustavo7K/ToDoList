package com.gustavohenrique.todolist.service;

import com.gustavohenrique.todolist.entity.Todo;
import com.gustavohenrique.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TodoService{
    /* Pensando em aprimorar o uso da lista de tarefas, após cada operação
    a aplicação irá retornar a lista completa */

    private TodoRepository todoRepository; //injeção de dependencias

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

    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(UUID id){
        todoRepository.deleteById(id);
        return list();
    }
}
