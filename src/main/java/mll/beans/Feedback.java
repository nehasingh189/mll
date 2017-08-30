package mll.beans;

/**
 * Created by nehas on 7/8/2017.
 */

import java.io.Serializable;
import java.sql.Timestamp;

public class Feedback implements Serializable{

    private Integer id;
    private String feedbackmsg;
    private String emailId;
    private Integer userid;
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackmsg() {
        return feedbackmsg;
    }

    public void setFeedbackmsg(String feedbackmsg) {
        this.feedbackmsg = feedbackmsg;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }








}
