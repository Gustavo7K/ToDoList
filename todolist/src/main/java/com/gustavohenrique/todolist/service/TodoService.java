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
    /* Pensando em aprimorar o uso da lista de tarefas, após algumas operações especificas
    a aplicação irá retornar a lista completa */

    private final TodoRepository todoRepository; //injeção de dependencias

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);//salvar a tarefa
        return list();
    }

    // TODO: 02/12/2024 arrumar ordenação
    public List<Todo> list(){
        //ordenação por prioridade (3 maior prioridade e 1 menor prioridade) .and
        //ordenação por tarefas não realizadas e realizadas
        Sort sort = Sort.by("prioridade").descending().and(
                Sort.by("realizado").ascending()
        );
        return todoRepository.findAll(sort);
    }

    public Optional<Todo> searchById(UUID id){
        return todoRepository.findById(id);
    }

    public List<Todo> searchByRealizado(){
        return todoRepository.findByRealizado(true);
    }

    public Optional<Todo> update(UUID id, Todo todo){
        Optional<Todo> todoUpdate = todoRepository.findById(id);

        if (todoUpdate.isPresent()){
        Todo todoToUpdate = todoUpdate.get();
        todoToUpdate.setId(id);
        todoToUpdate.setNome(todo.getNome());
        todoToUpdate.setDescricao(todo.getDescricao());
        todoToUpdate.setPrioridade(todo.getPrioridade());
        todoToUpdate.setRealizado(todo.isRealizado());
        todoRepository.save(todoToUpdate);
            return Optional.of(todoToUpdate); // Retorna o recurso atualizado
        }
        return Optional.empty(); // Retorna vazio caso o recurso não seja encontrado
    }

    public Optional<Todo> delete(UUID id){
        Optional<Todo> todoDelete = todoRepository.findById(id);
        if (todoDelete.isPresent()){
            //Se o recurso for encontrado (isPresent()), ele será excluído com deleteById(id)
            todoRepository.deleteById(id);
        } return todoDelete;
    }
}
