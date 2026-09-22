package com.company.projectmanagement.app;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.ProjectStats;
import com.company.projectmanagement.entity.Task;
import com.company.projectmanagement.entity.TimeEntry;
import io.jmix.core.DataManager;
import io.jmix.data.QueryTransformerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectStatsService {

    private final DataManager dataManager;
    private final QueryTransformerFactory queryTransformerFactory;

    public ProjectStatsService(DataManager dataManager, QueryTransformerFactory queryTransformerFactory) {
        this.dataManager = dataManager;
        this.queryTransformerFactory = queryTransformerFactory;
    }

    public List<ProjectStats> fetchProjectStatistics(){
        List<Project> projects = dataManager.load(Project.class).all().list();

        List<ProjectStats> projectStats = projects.stream().map(project -> {
            ProjectStats stat = dataManager.create(ProjectStats.class);
            stat.setId(project.getId());
            stat.setProjectName(project.getName());
            stat.setTaskCount(project.getTasks().size());
            Integer plannedEffors = project.getTasks().stream().map(Task::getEstimation).reduce(0, Integer::sum);
            stat.setPlannedEfforts(plannedEffors);
            stat.setActualEfforts(getActionEfforts(stat.getId()));
            return stat;
        }).collect(Collectors.toList());
        return projectStats;
    }

    public Integer getActionEfforts (UUID projectId){
        return dataManager.loadValue("select SUM(t.timeSpent) from TimeEntry t " +
                "where t.task.project.id = :projectId", Integer.class).parameter("projectId", projectId)
                .optional()
                .orElse(0);
    }
}