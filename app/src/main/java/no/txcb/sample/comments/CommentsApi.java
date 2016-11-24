package no.txcb.sample.comments;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;

public interface CommentsApi {
    Observable<List<Comment>> getComments();
}
