package no.txcb.sample.comments.models;

public class Comment {
    public int postId;
    public String body;

    public Comment(int postId, String body) {
        this.postId = postId;
        this.body = body;
    }
}
