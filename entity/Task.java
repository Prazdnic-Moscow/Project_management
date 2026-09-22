package com.company.projectmanagement.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "TASK_", indexes = {
        @Index(name = "IDX_TASK__ASSIGNEE", columnList = "ASSIGNEE_ID"),
        @Index(name = "IDX_TASK__PROJECT", columnList = "PROJECT_ID")
})
@Entity(name = "Task_")
public class Task {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ATTACHMENT", length = 1024)
    private FileRef attachment;

    @NotNull
    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @FutureOrPresent
    @Column(name = "DATE_")
    private LocalDate dueDate;

    @JoinColumn(name = "ASSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    @PositiveOrZero
    @NotNull
    @Column(name = "ESTIMATION", nullable = false)
    private Integer estimation;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "PRIORITY")
    private String priority;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PROJECT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Project project;

    public FileRef getAttachment() {
        return attachment;
    }

    public void setAttachment(FileRef attachment) {
        this.attachment = attachment;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority == null ? null : priority.getId();
    }

    public TaskPriority getPriority() {
        return priority == null ? null : TaskPriority.fromId(priority);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}