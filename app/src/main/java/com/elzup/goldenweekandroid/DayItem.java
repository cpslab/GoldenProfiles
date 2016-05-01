package com.elzup.goldenweekandroid;

public class DayItem {
    private String dateText;
    private String dayText;
    private String text;

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DayItem(String dateText, String dayText, String text) {
        this.dateText = dateText;
        this.dayText = dayText;
        this.text = text;
    }

}
