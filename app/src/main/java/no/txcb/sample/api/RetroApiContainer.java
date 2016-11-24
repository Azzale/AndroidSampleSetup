package no.txcb.sample.api;

import java.util.List;

import io.reactivex.Observable;
import no.txcb.sample.comments.models.Comment;

public interface RetroApiContainer {

    Observable<ProfileData> getProfileData();

    Observable<List<Comment>> getComments();
}
