package com.example.emergency;

public class FeedbackHelperClass {
    String Name123;
    String FeedbackMessage;

    public FeedbackHelperClass(String name123, String feedbackMessage) {
        Name123 = name123;
        FeedbackMessage = feedbackMessage;
    }

    public String getName123() {
        return Name123;
    }

    public void setName123(String name123) {
        Name123 = name123;
    }

    public String getFeedbackMessage() {
        return FeedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        FeedbackMessage = feedbackMessage;
    }
}
