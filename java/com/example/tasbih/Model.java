package com.example.tasbih;

public class Model {
    String title;
    String source;
    int counter;

    public Model(int counter,String title, String source) {
        this.counter = counter;
        this.title = title;
        this.source = source;
    }

    public int getCounter() {
        return counter;
    }
    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }
}
