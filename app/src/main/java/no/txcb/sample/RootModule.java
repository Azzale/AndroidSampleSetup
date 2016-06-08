package no.txcb.sample;

import dagger.Module;
import no.txcb.sample.modules.AndroidModule;

@Module
        (
                includes = {
                        MainModule.class
                        , AndroidModule.class
                }

        )
public class RootModule {
}
