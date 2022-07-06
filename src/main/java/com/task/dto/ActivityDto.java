package com.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {

    private String id;
    private LocalDateTime start;
    private LocalDateTime finish;
    private String description;
    private Long timeElapsed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimeElapsed() {
        if (start != null && finish != null) {
            return start.until(finish, ChronoUnit.MINUTES);
        }
        return 0L;
    }
}
