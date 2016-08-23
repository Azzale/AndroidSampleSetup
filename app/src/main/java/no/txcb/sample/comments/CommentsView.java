package no.txcb.sample.comments;

import android.content.Context;

import java.util.List;

public interface CommentsView {
    Context getContext();
    void showComments(List<String> comments);

    void setError(String error);
}
