package com.example.demo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserWidgetId implements Serializable {
    private Long userId;
    private Long widgetId;

    public UserWidgetId(){}
    public UserWidgetId(Long userId, Long widgetId){
        this.userId = userId;
        this.widgetId = widgetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(Long widgetId) {
        this.widgetId = widgetId;
    }
}
