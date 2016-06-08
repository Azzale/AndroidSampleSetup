package no.txcb.sample;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    public DataApi provideDataApy() {
        if (BuildConfig.DUMMY) {
            return new FakeDataApi();
        }
        return new RealDataApi();
    }
}
