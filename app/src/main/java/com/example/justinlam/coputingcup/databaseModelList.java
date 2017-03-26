package com.example.justinlam.coputingcup;


public class databaseModelList {
    private int activityNumber;
    private int priority;
    private String activityTitle;
    private int duration;
    private String prerequisite;
    private String availablePeriod;


    public Integer getActivityNumber(){
        return activityNumber;
    }

    public void setActivityNumber(Integer activityNumber){
        this.activityNumber = activityNumber;
    }

    public Integer getPriority(){
        return priority;
    }

    public void setPriority(Integer priority){
        this.priority = priority;
    }

    public String getActivityTitle(){
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle){
        this.activityTitle = activityTitle;
    }

    public Integer getDuration(){
        return duration;
    }

    public void setDuration(Integer duration){
        this.duration = duration;
    }

    public String getPrerequisite(){
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite){
        this.prerequisite = prerequisite;
    }

    public String getAvailablePeriod(){
        return availablePeriod;
    }

    public void setAvailablePeriod(String availablePeriod){this.availablePeriod = availablePeriod;}

}