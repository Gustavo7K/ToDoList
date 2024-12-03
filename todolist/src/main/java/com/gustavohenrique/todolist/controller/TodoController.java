package com.gustavohenrique.todolist.controller;

import com.gustavohenrique.todolist.entity.Todo;
import com.gustavohenrique.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    ResponseEntity<List<Todo>> create(@Valid @RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.create(todo));
    }

    @GetMapping
    List<Todo> list() {
        return todoService.list();
    }

    @GetMapping("{id}")
    ResponseEntity<Todo> searchById(@PathVariable UUID id) {
        Optional<Todo> todoSearchById = todoService.searchById(id);
        if (todoSearchById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            //Caso o Optional não encontrar a tarefa para o ID fornecido,
            // é retornada uma resposta com o status HTTP 404 (Not Found).
        } else {
            return new ResponseEntity<>(todoSearchById.get(), HttpStatus.OK);
            //a tarefa será retornada no corpo da resposta com o status HTTP 200 (OK).
        }
    }

    @GetMapping("/realizadas")
    ResponseEntity<List<Todo>> searchByBoolean() {
        List<Todo> todoSearchByBoolean = todoService.searchByRealizado();
        if(todoSearchByBoolean.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //NO_CONTENT Indica que a requisição foi bem-sucedida,
            // mas não há nenhum dado no corpo da resposta.
        } else {
            return new ResponseEntity<>(todoSearchByBoolean,HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    List<Todo> update(@PathVariable UUID id,
                      @RequestBody @Valid Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Todo> delete(@PathVariable UUID id) {
        Optional<Todo> todoDelete = todoService.delete(id);
        if(todoDelete.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
