package no.txcb.sample.modules;

import dagger.Module;
import no.txcb.sample.modules.AndroidModule;
import no.txcb.sample.modules.MainModule;

@Module
        (
                includes = {
                        MainModule.class
                        , AndroidModule.class
                }

        )
public class RootModule {
}
