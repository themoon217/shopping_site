package model;

import java.io.Serializable;

public class Review implements Serializable {
    private int evaluation; // 星の数
    private String comment; // レビュー内容
    private int itemId;
    private String userId;

    public Review(int evaluation, String comment, int itemId, String userId) {
        this.evaluation = evaluation;
        this.comment = comment;
        this.itemId = itemId;
        this.userId = userId;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public String getComment() {
        return comment;
    }

    public int getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }
}