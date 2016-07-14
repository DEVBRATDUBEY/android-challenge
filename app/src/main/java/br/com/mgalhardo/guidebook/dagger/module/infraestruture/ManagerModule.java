package br.com.mgalhardo.guidebook.dagger.module.infraestruture;

import javax.inject.Singleton;

import br.com.mgalhardo.guidebook.domain.repository.GuideRepository;
import br.com.mgalhardo.guidebook.infraestruture.operator.WorkerOperator;
import br.com.mgalhardo.guidebook.infraestruture.storage.client.GuideService;
import br.com.mgalhardo.guidebook.infraestruture.storage.manager.GuideManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    GuideRepository providesGuideRepository(GuideService guideService, WorkerOperator workerOperator) {
        return new GuideManager(guideService, workerOperator);
    }

}
