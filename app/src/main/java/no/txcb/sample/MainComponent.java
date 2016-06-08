package no.txcb.sample;


import javax.inject.Singleton;

import dagger.Component;

@Component(
        modules = {
                RootModule.class
        }
)
@Singleton
public interface MainComponent {
        void inject(DataPresenter dataPresenter);
}
