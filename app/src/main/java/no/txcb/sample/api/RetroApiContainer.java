package no.txcb.sample.api;

import java.util.List;

import no.txcb.sample.comments.models.Comment;
import rx.Observable;

public interface RetroApiContainer {

    Observable<ProfileData> getProfileData();

    Observable<List<Comment>> getComments();
}
