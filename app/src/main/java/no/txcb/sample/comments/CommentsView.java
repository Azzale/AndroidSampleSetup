package no.txcb.sample.comments;

import android.content.Context;

import java.util.List;

import no.txcb.sample.comments.models.Comment;

interface CommentsView {

    Context getContext();

    void showComments(List<Comment> comments);

    void postComment(String comment);

    void setError(String error);

    void onCommentAdded(Comment comment);

    void onDeleteCommentRequested(long id);
}
