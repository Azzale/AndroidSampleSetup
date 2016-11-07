package no.txcb.sample;


import javax.inject.Singleton;

import dagger.Component;
import no.txcb.sample.comments.CommentsPresenter;
import no.txcb.sample.login.LoginPresenter;
import no.txcb.sample.modules.RootModule;

@Component(
        modules = {
                RootModule.class
        }
)
@Singleton
public interface MainComponent {
        void inject(CommentsPresenter commentsPresenter);

        void inject(LoginPresenter loginPresenter);
}
