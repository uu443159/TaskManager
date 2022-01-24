package com.stefanini.taskmanager.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "task", schema = "taskManager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String userName;

    @Basic
    private String title;

    @Basic
    private String description;

}
