package com.example.springtest.Compas.logic;

public class WorldSideRange {
    private String name;
    private int startDegree;
    private int endDegree;

    public WorldSideRange(String name, int startDegree, int endDegree) {
        this.name = name;
        this.startDegree = startDegree;
        this.endDegree = endDegree;
    }

    public String getName() {
        return name;
    }

    public int getStartDegree() {
        return startDegree;
    }

    public int getEndDegree() {
        return endDegree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDegree(int startDegree) {
        this.startDegree = startDegree;
    }

    public void setEndDegree(int endDegree) {
        this.endDegree = endDegree;
    }
}
