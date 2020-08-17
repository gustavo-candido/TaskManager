package Zup.TaskManager.Services;

import Zup.TaskManager.DAOs.DAO.TasksDAO;
import Zup.TaskManager.DAOs.Models.Task;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

  private final TasksDAO tasksDAO;

  @Autowired
  public TasksService(@Qualifier("FakeTaskRepository") TasksDAO tasksDAO) {
    this.tasksDAO = tasksDAO;
  }

  public int createTask(Task task) {
    tasksDAO.createTask(task);
    return 1;
  }

  public List<Task> getAllTasks() {
    return tasksDAO.selectAllTasks();
  }

  public List<Task> getCompletedTasks() {
    return tasksDAO.selectAllCompletedTasks();
  }

  public List<Task> getUncompletedTasks() {
    return tasksDAO.selectAllUncompletedTasks();
  }

  public List<Task> getTodayTasks() {
    return tasksDAO.selectTodayTasks();
  }

  public Optional<Task> getTaskById(UUID id) {
    return tasksDAO.selectTaskById(id);
  }

  public int markTaskAsComplete(UUID id) {
    return tasksDAO.markTaskAsComplete(id);
  }

  public int markTaskAsIncomplete(UUID id) {
    return tasksDAO.markTaskAsIncomplete(id);
  }

  public int updateTask(UUID id, Task updatedTask) {
    return tasksDAO.updateTaskById(id, updatedTask);
  }

  public int deleteTask(UUID id) {
    return tasksDAO.deleteTaskById(id);
  }
}
