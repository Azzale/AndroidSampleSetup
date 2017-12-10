package no.txcb.sample.comments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FakeCommentsApi implements CommentsApi {

    ArrayList<Comment> comments = new ArrayList<>();
    @Inject
    public FakeCommentsApi() {
        Comment e = new Comment("First!");
        e.id = 0;
        comments.add(e);
        Comment e1 = new Comment("aww..");
        e1.id = 1;
        comments.add(e1);
        Comment lame = new Comment("lame");
        lame.id = 2;
        comments.add(lame);
    }

    @Override
    public Observable<List<Comment>> getComments() {
        return Observable.just(comments);
    }

    @Override
    public Observable<Comment> postComment(String comment) {
        Comment newComment = new Comment(comment);
        newComment.id = comments.size();
        comments.add(newComment);
        return  Observable.just(newComment);
    }

    @Override
    public Call<Comment> deleteComment(long id) {
        Comment toBeDeleted = null;
        for(Comment c : comments) {
            if(c.id == id) {
                toBeDeleted = c;
                break;
            }
        }
        if(toBeDeleted != null){
            comments.remove(toBeDeleted);
        }

        return new FakeDeleteCommentCall(toBeDeleted);
    }

    /**
    This class is only here so that we can keep the same retrofit + rxjava spirit in both RealCommentsApi and FakeCommentsApi
     */
    private class FakeDeleteCommentCall implements Call<Comment> {
        private final Comment comment;

        FakeDeleteCommentCall(Comment comment) {
            this.comment = comment;
        }

        @Override
        public Response<Comment> execute() throws IOException {
            return Response.success(comment);
        }

        @Override
        public void enqueue(Callback<Comment> callback) {
            // This is not a real call, so lets just simulate a fake http answer
            callback.onResponse(this, Response.success(comment));
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {}

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<Comment> clone() {
            return null;
        }

        @Override
        public Request request() {
            return null;
        }
    }
}
