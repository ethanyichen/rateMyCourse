package model;

public class Comment extends Review {
    private String comment = "";


    public Comment(String content) {
        super("");
        this.comment = content;
    }


    //EFFECTS: returns the comment;
    @Override
    public String getReview() {
        return comment;
    }

}
