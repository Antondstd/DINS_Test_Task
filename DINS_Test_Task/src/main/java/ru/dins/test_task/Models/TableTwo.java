package ru.dins.test_task.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "CustomTableTwo")
public class TableTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Timestamp")
    private ZonedDateTime timeStamp;

    public TableTwo(TableOne tableOne) {
        this.timeStamp = tableOne.getTimeStamp();
        this.name = tableOne.getName();
    }

    public TableTwo() {

    }

    public int getId() {
        return id;
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
