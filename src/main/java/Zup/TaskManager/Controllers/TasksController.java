package Zup.TaskManager.Controllers;

import Zup.TaskManager.DAOs.Models.Task;
import Zup.TaskManager.Services.TasksService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("tasks")
@RestController
public class TasksController {
  private final TasksService tasksService;

  @Autowired
  public TasksController(TasksService tasksService) {
    this.tasksService = tasksService;
  }

  @PostMapping
  public void createTask(@Valid @NonNull @RequestBody Task task) {
    tasksService.createTask(task);
  }

  @GetMapping
  public List<Task> getAllTasks() {
    return tasksService.getAllTasks();
  }

  @GetMapping(path = "completed")
  public List<Task> getCompletedTasks() {
    return tasksService.getCompletedTasks();
  }

  @GetMapping(path = "uncompleted")
  public List<Task> getUncompletedTasks() {
    return tasksService.getUncompletedTasks();
  }

  @GetMapping(path = "today")
  public List<Task> getTodayTasks() {
    return tasksService.getTodayTasks();
  }

  @GetMapping(path = "{id}")
  public Task getTaskById(@PathVariable("id") UUID id) {
    return tasksService.getTaskById(id).orElse(null);
  }

  @PatchMapping(path = "check/{id}")
  public void markTaskAsComplete(@PathVariable("id") UUID id) {
    tasksService.markTaskAsComplete(id);
  }

  @PatchMapping(path = "uncheck/{id}")
  public void markTaskAsIncomplete(@PathVariable("id") UUID id) {
    tasksService.markTaskAsIncomplete(id);
  }

  @PutMapping(path = "{id}")
  public int updateTask(@PathVariable("id") UUID id, @RequestBody Task updatedTask) {
    return tasksService.updateTask(id, updatedTask);
  }

  @DeleteMapping(path = "{id}")
  public int deleteTask(@PathVariable("id") UUID id) {
    return tasksService.deleteTask(id);
  }
}

