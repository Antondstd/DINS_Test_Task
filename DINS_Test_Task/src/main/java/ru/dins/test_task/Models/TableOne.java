package ru.dins.test_task.Models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "CustomTableOne")

public class TableOne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Timestamp")
    private ZonedDateTime timeStamp;

    public TableOne(String name){
        this.timeStamp = ZonedDateTime.now();
        this.name = name;
    }

    public TableOne() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
