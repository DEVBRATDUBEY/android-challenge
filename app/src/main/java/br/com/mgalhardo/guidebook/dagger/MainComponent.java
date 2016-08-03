package br.com.mgalhardo.guidebook.dagger;

import javax.inject.Singleton;

import br.com.mgalhardo.guidebook.dagger.module.ApplicationModule;
import br.com.mgalhardo.guidebook.dagger.module.PreferenceModule;
import br.com.mgalhardo.guidebook.dagger.module.SettingModule;
import br.com.mgalhardo.guidebook.dagger.module.domain.RepositoryModule;
import br.com.mgalhardo.guidebook.dagger.module.infraestruture.ManagerModule;
import br.com.mgalhardo.guidebook.dagger.module.infraestruture.NetworkModule;
import br.com.mgalhardo.guidebook.dagger.module.infraestruture.RxJavaModule;
import br.com.mgalhardo.guidebook.dagger.module.infraestruture.ServiceModule;
import br.com.mgalhardo.guidebook.dagger.module.presentation.PresenterModule;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListActivity;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class, NetworkModule.class, PreferenceModule.class,
        RepositoryModule.class, SettingModule.class, RxJavaModule.class, ServiceModule.class,
        ManagerModule.class
})
public interface MainComponent {
    UiComponent uiComponent();
}
