package com.project.taskmanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(columnDefinition = "boolean default false")
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  // Getters and Setters for all properties
  // (code omitted for brevity)

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  // Optional no-argument constructor
  public Task() {
  }

  // Optional constructor with all arguments (excluding id)
  public Task(String title, boolean completed) {
    this.title = title;
    this.completed = completed;
  }
}