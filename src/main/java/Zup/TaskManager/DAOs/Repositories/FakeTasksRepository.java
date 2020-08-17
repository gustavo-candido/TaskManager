package Zup.TaskManager.DAOs.Repositories;

import Zup.TaskManager.DAOs.DAO.TasksDAO;
import Zup.TaskManager.DAOs.Models.Task;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository("FakeTaskRepository")
public class FakeTasksRepository implements TasksDAO {

  private static List<Task> DB = new ArrayList<Task>();

  FakeTasksRepository() {
    Date today = new Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(today);

    for (int i = 1; i <= 5; i++) {
      createTask(new Task(
          UUID.randomUUID(),
          "title " + String.valueOf(i),
          "description " + String.valueOf(i),
          false,
          cal.getTime()
      ));
      cal.add(Calendar.DATE, 1);
    }
  }

  @Override
  public int createTask(UUID id, Task task) {
    DB.add(new Task(
        id,
        task.getTitle(),
        task.getDescription(),
        task.isComplete(),
        task.getDate()
    ));
    return 1;
  }

  @Override
  public List<Task> selectAllTasks() {
    return DB;
  }

  @Override
  public List<Task> selectAllCompletedTasks() {

    return DB.stream()
        .filter(Task::isComplete)
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> selectAllUncompletedTasks() {

    return DB.stream()
        .filter(task -> !task.isComplete())
        .collect(Collectors.toList());
  }

  @Override
  public List<Task> selectTodayTasks() {
    final Date today = new Date();
    Calendar todayCal = Calendar.getInstance();
    todayCal.setTime(today);

    return DB.stream()
        .filter(task -> {
          Calendar taskCal = Calendar.getInstance();
          taskCal.setTime(task.getDate());

          return todayCal.get(Calendar.YEAR) == taskCal.get(Calendar.YEAR)
              && todayCal.get(Calendar.MONTH) == taskCal.get(Calendar.MONTH)
              && todayCal.get(Calendar.DAY_OF_MONTH) == taskCal.get(Calendar.DAY_OF_MONTH);
        })
        .collect(Collectors.toList());

  }

  @Override
  public Optional<Task> selectTaskById(UUID id) {
    return DB.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst();
  }

  @Override
  public int markTaskAsComplete(UUID id) {
    final Optional<Task> task = selectTaskById(id);
    if (!task.isPresent()) {
      return 0;
    }

    DB = DB.stream().peek(t -> {
      if (t.getId().equals(task.get().getId())) {
        t.setIsComplete(true);
      }
    }).collect(Collectors.toList());

    return 1;
  }

  @Override
  public int markTaskAsIncomplete(UUID id) {
    final Optional<Task> task = selectTaskById(id);

    if (!task.isPresent()) {
      return 0;
    }

    DB = DB.stream().peek(t -> {
      if (t.getId().equals(task.get().getId())) {
        t.setIsComplete(false);
      }
    }).collect(Collectors.toList());

    return 1;
  }

  @Override
  public int updateTaskById(UUID id, Task updatedTask) {
    return selectTaskById(id)
        .map(task -> {
          int index = DB.indexOf(task);

          if (index >= 0) {
            DB.set(
                index,
                new Task(
                    task.getId(),
                    updatedTask.getTitle(),
                    updatedTask.getDescription(),
                    task.isComplete(),
                    updatedTask.getDate()
                )
            );
            return 1;
          }
          return 0;
        })
        .orElse(0);
  }

  @Override
  public int deleteTaskById(UUID id) {
    Optional<Task> task = selectTaskById(id);

    if (!task.isPresent()) {
      return 0;
    }

    DB.remove(task.get());
    return 1;
  }
}
