package com.stefanini.taskmanager.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", schema = "taskManager")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String firstName;
    private String lastName;

    @NaturalId
    private String userName;

    @OneToMany(mappedBy = "userName")
    private List<Task> taskList = new ArrayList<>();

}
