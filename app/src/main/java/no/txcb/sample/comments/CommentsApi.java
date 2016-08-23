package no.txcb.sample.comments;

import java.util.List;

import no.txcb.sample.comments.models.Comment;
import rx.Observable;

public interface CommentsApi {
    Observable<List<Comment>> getComments();
}
