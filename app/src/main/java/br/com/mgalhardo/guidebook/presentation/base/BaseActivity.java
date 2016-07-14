package br.com.mgalhardo.guidebook.presentation.base;

import android.support.v7.app.AppCompatActivity;

import br.com.mgalhardo.guidebook.MainApplication;
import br.com.mgalhardo.guidebook.dagger.MainComponent;

public class BaseActivity extends AppCompatActivity {

    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    protected MainComponent getMainComponent() {
        return getMainApplication().getComponent();
    }
}
