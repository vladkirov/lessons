package com.vladkirov.lessons.lesson06.homework01;

public class Alpine {
    public static void main(String[] args) {
        Mountain fuji = new Mountain("Fudji", "Japan", 3500);
        Mountain everest = new Mountain("Everest", "Nepal", 8800);
        Mountain elbrus = new Mountain("Elbrus", "Russia", 5000);

        Climber ivan = new Climber("Ivan", "Russia, Moscow");
        Climber petr = new Climber("Petr", "Russia, Krasnodar");
        Climber elena = new Climber("Elena", "Russia, Moscow");

        Climber mike = new Climber("Mike", "USA, NY");
        Climber james = new Climber("James", "USA, NY");

        Climber jung = new Climber("Jung", "Korea, Seul");
        Climber cha = new Climber("Cha", "Korea, Seul");

        MountainClimbingGroup groupFirst = new MountainClimbingGroup(3, fuji);
        MountainClimbingGroup groupSecond = new MountainClimbingGroup(2, everest);
        MountainClimbingGroup groupThird = new MountainClimbingGroup(2, elbrus);

        groupFirst.addClimber(ivan, petr, elena);
        groupSecond.addClimber(mike, james);
        groupThird.addClimber(jung, cha);

        System.out.println(groupFirst.toString());
        System.out.println(groupSecond.toString());
        System.out.println(groupThird.toString());
    }
}
