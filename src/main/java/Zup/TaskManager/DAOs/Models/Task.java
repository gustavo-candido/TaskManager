package Zup.TaskManager.DAOs.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Task {

  private final UUID id;
  @NotBlank()
  private final String title;
  @NotBlank()
  private final String description;
  private boolean isComplete;
  @NotNull
  private final Date date;

  public Task(
      @JsonProperty("id") UUID id,
      @JsonProperty("title") String title,
      @JsonProperty("description") String description,
      @JsonProperty("isComplete") boolean isComplete,
      @JsonProperty("date")
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
          Date date
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.isComplete = isComplete;
    this.date = date;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public Date getDate() {
    return date;
  }

  public void setIsComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }
}
