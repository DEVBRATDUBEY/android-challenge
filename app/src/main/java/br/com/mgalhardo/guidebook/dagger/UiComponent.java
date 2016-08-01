package br.com.mgalhardo.guidebook.dagger;

import br.com.mgalhardo.guidebook.dagger.module.presentation.PresenterModule;
import br.com.mgalhardo.guidebook.dagger.scope.PerActivity;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListActivity;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = {PresenterModule.class})
public interface UiComponent {
    void inject(GuideListActivity activity);
}
