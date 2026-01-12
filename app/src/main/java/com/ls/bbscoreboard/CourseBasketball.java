package com.ls.bbscoreboard;

public class CourseBasketball {

    private String TeamAResult;
    private String TeamBResult;
    private int id;

    // creating getter and setter methods
    public String getTeamAResult() {
        return TeamAResult;
    }

    public void setTeamAResult(String teamA) {
        this.TeamAResult = TeamAResult;
    }

    public String getTeamBResult() {
        return TeamBResult;
    }

    public void setTeamBResult(String duration) {
        this.TeamBResult = TeamBResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public CourseBasketball(String teamAResult, String teamBResult) {
        this.TeamAResult = teamAResult;
        this.TeamBResult = teamBResult;
    }
}