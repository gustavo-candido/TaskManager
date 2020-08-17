package Zup.TaskManager.DAOs.DAO;

import Zup.TaskManager.DAOs.Models.Task;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TasksDAO {
  int createTask(UUID id, Task task);

  default int createTask(Task task) {
    UUID id = UUID.randomUUID();
    return createTask(id, task);
  }

  List<Task> selectAllTasks();

  List<Task> selectAllCompletedTasks();

  List<Task> selectAllUncompletedTasks();

  List<Task> selectTodayTasks();

  // método implícito dos requisítos
  Optional<Task> selectTaskById(UUID id);

  int markTaskAsComplete(UUID id);

  int markTaskAsIncomplete(UUID id);

  int updateTaskById(UUID id, Task updatedTask);

  int deleteTaskById(UUID id);
}
