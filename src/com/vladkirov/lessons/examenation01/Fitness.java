package com.vladkirov.lessons.examenation01;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Fitness {
    private final int amountVisitorsInZone;
    private final Subscription[][] subscribers;
    private LocalDate nowDate;
    private LocalTime nowTime;

    public Fitness(int amountVisitorsInZone, LocalDateTime nowDateTime) {
        if (amountVisitorsInZone < 1)
            throw new IllegalArgumentException("amountVisitorsInZone < 1");
        this.amountVisitorsInZone = amountVisitorsInZone;

        Objects.requireNonNull(nowDateTime);
        setNowDateTime(nowDateTime);

        subscribers = new Subscription[Zone.values().length][amountVisitorsInZone];
    }

    public void setNowDateTime(LocalDateTime nowDateTime) {
        Objects.requireNonNull(nowDateTime, "nowDateTime is null.");
        this.nowDate = nowDateTime.toLocalDate();
        this.nowTime = nowDateTime.toLocalTime();
    }

    public Subscription[][] getSubscribers() {
        return subscribers;
    }

    public void close() {
        for (Zone zone : Zone.values())
            for (int i = 0; i < amountVisitorsInZone; i++)
                subscribers[zone.ordinal()][i] = null;
    }

    private boolean validInviteDate(Subscription subscription) {
        return (nowDate.isAfter(subscription.getRegistrationBegin()) && nowDate.isBefore(subscription.getRegistrationEnd()));
    }

    private boolean alreadyRegistered(Subscription subscription) {
        for (Zone zone : Zone.values())
            for (int i = 0; i < amountVisitorsInZone; i++)
                if (subscribers[zone.ordinal()][i] != null && subscribers[zone.ordinal()][i].equals(subscription))
                    return true;
        return false;
    }

    private int isFreeSpace(Zone zone) {
        for (int i = 0; i < amountVisitorsInZone; i++)
            if (subscribers[zone.ordinal()][i] == null) return i;
        return -1;
    }

    private boolean checkAuthority(Subscription subscription, Zone zone) {
        LocalTime timeStart = LocalTime.of(8, 0);
        LocalTime timeFinish = LocalTime.of(22, 0);
        if (subscription.getTypePass() == TypePass.DAY)
            timeFinish = LocalTime.of(16, 0);

        if (nowTime.isAfter(timeStart) && nowTime.isBefore(timeFinish)) {
            if (subscription.getTypePass() == TypePass.FULL) return true;
            if (subscription.getTypePass() == TypePass.SINGLE && (zone == Zone.SWIMMING_POOL || zone == Zone.GYM))
                return true;
            else if (subscription.getTypePass() == TypePass.DAY && (zone == Zone.GYM || zone == Zone.GROUP_HALL))
                return true;
            else {
                Logger.println("Access denied to zone " + zone.name());
                return false;
            }
        } else {
            Logger.println("Access allow only from " + timeStart.toString() + " to " + timeFinish.toString());
            return false;
        }
    }

    public void invite(Subscription subscription, Zone zone) {
        Objects.requireNonNull(subscription, "Subscription is null");

        if (!validInviteDate(subscription)) {
            Logger.println("Expired subscription.");
            return;
        }

        if (alreadyRegistered(subscription)) {
            Logger.println("Visitor already registered.");
            return;
        }

        int indexForInsertVisitor = isFreeSpace(zone);
        if (indexForInsertVisitor >= 0 && checkAuthority(subscription, zone)) {
            subscribers[zone.ordinal()][indexForInsertVisitor] = subscription;
            Logger.printInviteInfo(subscription, zone, LocalDateTime.of(nowDate, nowTime));
        }
    }

    public void listAllVisitors() {
        for (Zone zone : Zone.values()) {
            Logger.println("****************************");
            Logger.println("Zone: " + zone.name());
            for (int i = 0; i < subscribers.length; i++)
                if (subscribers[zone.ordinal()][i] != null)
                    Logger.printPerson(subscribers[zone.ordinal()][i].getVisitor());
        }
        Logger.println("****************************");
    }
}
