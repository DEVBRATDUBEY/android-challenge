package br.com.mgalhardo.guidebook.ui.guidelist;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import br.com.mgalhardo.guidebook.domain.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.domain.repository.GuideRepository;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListContract;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListPresenter;
import br.com.mgalhardo.guidebook.util.IOUtils;
import br.com.mgalhardo.guidebook.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class GuideListPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxJavaRule = new RxSchedulersOverrideRule();

    Gson gson = new Gson();

    GuideRepository guideRepository;
    GuideListContract.View view;

    GuideListPresenter presenter;

    @Before
    public void setup() {
        guideRepository = mock(GuideRepository.class);
        view = mock(GuideListContract.View.class);

        presenter = new GuideListPresenter(guideRepository);
        presenter.setView(view);
    }

    @Test
    public void loadGuidesFromRepositoryAndLoadIntoView() throws IOException {
        String json = IOUtils.readStringFromResourcePath("upcoming-guides.json");
        GuideAggregation guideAggregation = gson.fromJson(json, GuideAggregation.class);

        when(guideRepository.getCompanies()).thenReturn(Observable.just(guideAggregation));

        presenter.loadGuides();

        verify(guideRepository).getCompanies();

        verify(view).showLoadingLayout();
        verify(view).showSuccessLayout();
        verify(view).setupGuideList(guideAggregation.guides);

        verifyNoMoreInteractions(guideRepository);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void loadEmptyGuideListFromRepositoryAndPresentEmptyLayout() throws IOException {
        GuideAggregation guideAggregation = new GuideAggregation();
        guideAggregation.total = 0;
        guideAggregation.guides = new ArrayList<>();

        when(guideRepository.getCompanies()).thenReturn(Observable.just(guideAggregation));

        presenter.loadGuides();

        verify(guideRepository).getCompanies();

        verify(view).showLoadingLayout();
        verify(view).showEmptyLayout();

        verifyNoMoreInteractions(guideRepository);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void loadGuidesFromRepositoryFailsAndPresentErrorLayout() {
        when(guideRepository.getCompanies())
                .thenReturn(Observable.<GuideAggregation>error(new NullPointerException()));

        presenter.loadGuides();

        verify(guideRepository).getCompanies();

        verify(view).showLoadingLayout();
        verify(view).showErrorLayout();

        verifyNoMoreInteractions(guideRepository);
        verifyNoMoreInteractions(view);
    }
}
