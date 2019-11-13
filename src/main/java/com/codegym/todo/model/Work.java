package com.codegym.todo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String workName;

    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    public Work(){}

    public Work(String workName, Date startDate, Date endDate) {
        this.workName = workName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
