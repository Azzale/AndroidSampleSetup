package no.txcb.sample;


import javax.inject.Singleton;

import dagger.Component;
import no.txcb.sample.modules.RootModule;

@Component(
        modules = {
                RootModule.class
        }
)
@Singleton
public interface MainComponent {
        void inject(DataPresenter dataPresenter);
}
