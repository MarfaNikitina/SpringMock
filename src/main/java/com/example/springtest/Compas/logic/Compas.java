package com.example.springtest.Compas.logic;

public class Compas{

    private WorldSideRange[] sideRanges = new WorldSideRange[8];

    /*
    {
        "North": "341-45",
            "North-East": "46-90",
            "East": "91-135",
            "South-East": "136-180",
            "South": "181-225",
            "South-West": "226-270",
            "West": "271-315",
            "North-West": "316-340"
    }
*/
    public Compas() {
        super();
    }

    public Compas(WorldSideRange[] sideRanges) {
        this.sideRanges = sideRanges;
    }

    public WorldSideRange[] getSideRanges() {
        return sideRanges;
    }

    public void setSideRanges(WorldSideRange[] sideRanges) {
        this.sideRanges = sideRanges;
    }

    public String getSide(int degree) {
        for (WorldSideRange sideRange : sideRanges) {
            int end = sideRange.getEndDegree();
            int start = sideRange.getStartDegree();
            if (start > end) {
                if ((degree <= end && degree >= 0) || (degree >= start && degree <= 359)) {
                    return sideRange.getName();
                }
            } else if (degree <= end && degree >= start) {
                return sideRange.getName();
            }
        }
        return "No such side of the world";
    }


}
