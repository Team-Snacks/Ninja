package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserWidget implements Serializable {

    @EmbeddedId
    UserWidgetId userWidgetId;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;

    @Column(name = "w", nullable = false)
    private int w;

    @Column(name = "h", nullable = false)
    private int h;

    public UserWidget(){};

    public UserWidget(UserWidgetId userWidgetId){
        this.userWidgetId = userWidgetId;
    }

    public Long getUserId(){
        return userWidgetId.getUserId();
    }

    public Long getWidgetId(){
        return userWidgetId.getWidgetId();
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
