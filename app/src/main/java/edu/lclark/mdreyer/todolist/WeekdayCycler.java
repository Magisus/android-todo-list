package edu.lclark.mdreyer.todolist;

/**
 * Created by Magisus on 2/5/2016.
 */
public class WeekdayCycler {

    private String[] weekdays;
    private String previousDay;
    private String currentDay;
    private String nextDay;
    private int currentDayIndex;

    public WeekdayCycler(String[] weekdays) {
        this.weekdays = weekdays;
        currentDayIndex = 0;
        previousDay = weekdays[currentDayIndex + 6];
        currentDay = weekdays[currentDayIndex];
        nextDay = weekdays[currentDayIndex + 1];

    }

    public void goToNextDay() {
        currentDayIndex = (currentDayIndex + 1) % weekdays.length;
        previousDay = currentDay;
        currentDay = weekdays[currentDayIndex];
        nextDay = weekdays[(currentDayIndex + 1) % weekdays.length];
    }

    public void goToPreviousDay() {
        currentDayIndex = (currentDayIndex + weekdays.length - 1) % weekdays.length;
        previousDay = weekdays[(currentDayIndex + weekdays.length - 1) % weekdays.length];
        nextDay = currentDay;
        currentDay = weekdays[currentDayIndex];

    }

    public String getCurrentDay() {
        return currentDay;
    }

    public String getPreviousDay() {
        return previousDay;
    }

    public String getNextDay() {
        return nextDay;
    }

    public int getCurrentDayIndex() {
        return currentDayIndex;
    }

    public void goToDay(int index) {
        currentDayIndex = index;
        currentDay = weekdays[currentDayIndex];
        previousDay = weekdays[(currentDayIndex + weekdays.length - 1) % weekdays.length];
        nextDay = weekdays[(currentDayIndex + 1) % weekdays.length];
    }
}
