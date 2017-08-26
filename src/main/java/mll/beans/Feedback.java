package mll.beans;

/**
 * Created by nehas on 7/8/2017.
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class Feedback {

    private Integer id;
    private String feedbackMsg;
    private Integer userId;
    private Timestamp time;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }



}
