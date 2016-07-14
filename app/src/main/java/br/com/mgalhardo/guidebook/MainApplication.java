package br.com.mgalhardo.guidebook;

import android.app.Application;

import br.com.mgalhardo.guidebook.dagger.DaggerMainComponent;
import br.com.mgalhardo.guidebook.dagger.MainComponent;
import br.com.mgalhardo.guidebook.dagger.module.ApplicationModule;
import io.paperdb.Paper;

public class MainApplication extends Application {

    private MainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initDagger();
        initPapper();
    }

    private void initDagger() {
        component = DaggerMainComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initPapper() {
        Paper.init(this);
    }

    public MainComponent getComponent() {
        return component;
    }
}