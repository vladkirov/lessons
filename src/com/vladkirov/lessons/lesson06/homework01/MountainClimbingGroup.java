package com.vladkirov.lessons.lesson06.homework01;

import java.util.Arrays;

public class MountainClimbingGroup {
    private boolean isOpen;
    private Climber[] climbers;
    private Mountain mountain;
    private int countClimbers;

    public MountainClimbingGroup(int size, Mountain mountain) {
        if (size < 1)
            throw new IllegalArgumentException("A value greater than 0 is required.");
        climbers = new Climber[size];
        setMountain(mountain);
        setOpen();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen() {
        isOpen = true;
    }

    public void setClosed() {
        isOpen = false;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        if (mountain == null)
            throw new IllegalArgumentException("Object Mountain is null.");
        this.mountain = mountain;
    }

    public int getCountClimbers() {
        return countClimbers;
    }

    public void addClimber(Climber climber) {
        if (!isOpen()) System.out.println("Набор закрыт!");
        else {
            if (climber == null)
                throw new IllegalArgumentException("Object Climber is null.");

            climbers[countClimbers++] = climber;
            if (countClimbers == climbers.length) setClosed();
        }
    }

    public void addClimber(Climber... climbers) {
        if ((this.climbers.length - countClimbers) >= (climbers.length))
            for (Climber climber : climbers)
                addClimber(climber);
        else {
            System.out.println("Not enough places in the group of climbers");
        }
    }

    @Override
    public String toString() {
        return "MountainClimbingGroup{" +
                "isOpen=" + isOpen +
                ", climbers=" + Arrays.toString(climbers) +
                ", mountain=" + mountain +
                ", countClimbers=" + countClimbers +
                '}';
    }
}
