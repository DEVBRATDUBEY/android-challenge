package br.com.mgalhardo.guidebook.dagger.module.infraestruture;

import javax.inject.Singleton;

import br.com.mgalhardo.guidebook.infraestruture.operator.WorkerOperator;
import dagger.Module;
import dagger.Provides;

@Module
public class RxJavaModule {

    @Singleton
    @Provides
    WorkerOperator providesWorkerOperator() {
        return new WorkerOperator();
    }
}
