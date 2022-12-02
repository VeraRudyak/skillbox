package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired(required = false)
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task: taskIterable){
            tasks.add(task);
        }
        return tasks;
    }

    @PutMapping("/tasks/")
    public int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable int id) {
        taskRepository.deleteById(id);
    }

    @DeleteMapping("/tasks/")
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(taskOptional.get(), HttpStatus.OK);
    }
}
