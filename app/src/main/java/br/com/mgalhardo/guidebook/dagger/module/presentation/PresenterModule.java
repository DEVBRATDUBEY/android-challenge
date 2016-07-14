package br.com.mgalhardo.guidebook.dagger.module.presentation;

import br.com.mgalhardo.guidebook.domain.repository.GuideRepository;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListContract;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    GuideListContract.Presenter provideMainPresenter(GuideRepository guideRepository) {
        return new GuideListPresenter(guideRepository);
    }
}
