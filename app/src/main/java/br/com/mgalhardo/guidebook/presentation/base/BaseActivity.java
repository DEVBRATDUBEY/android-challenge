package br.com.mgalhardo.guidebook.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.mgalhardo.guidebook.MainApplication;
import br.com.mgalhardo.guidebook.dagger.UiComponent;

public class BaseActivity extends AppCompatActivity {

    private UiComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiComponent();
    }

    private void initUiComponent() {
        component = getMainApplication().getComponent()
                .uiComponent();
    }

    protected MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    protected UiComponent getUiComponent() {
        return component;
    }
}
