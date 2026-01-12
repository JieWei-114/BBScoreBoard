package com.ls.bbscoreboard;

public class CourseBadminton {

    private String teamAResult;
    private String teamBResult;
    private int id;

    // creating getter and setter methods
    public String getTeamAResult() {
        return teamAResult;
    }

    public void setTeamAResult(String teamA) {
        this.teamAResult = teamAResult;
    }

    public String getTeamBResult() {
        return teamBResult;
    }

    public void setTeamBResult(String duration) {
        this.teamBResult = teamBResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public CourseBadminton(String teamAResult, String teamBResult) {
        this.teamAResult = teamAResult;
        this.teamBResult = teamBResult;
    }
}