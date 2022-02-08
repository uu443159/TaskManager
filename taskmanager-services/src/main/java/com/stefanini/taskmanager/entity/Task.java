package com.stefanini.taskmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "task", schema = "taskManager")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task_generator")
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "userName", nullable = false, length = 100)
    private String userName;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
