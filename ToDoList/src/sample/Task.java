package sample;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    String title;
    String description;
    String priority;
    LocalDate date;


    public Task(String title, String description, String priority, LocalDate date){
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;

    }

    public String getTitle(){ return title;}

    public String getDescription(){ return description;}

    public String getPriority(){ return priority;}
    public  LocalDate getLocalDate(){ return date;}
}
