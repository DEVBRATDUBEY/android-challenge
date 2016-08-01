package br.com.mgalhardo.guidebook.ui.guidelist;

import org.junit.Before;
import org.junit.Rule;

import br.com.mgalhardo.guidebook.domain.repository.GuideRepository;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListContract;
import br.com.mgalhardo.guidebook.presentation.ui.guidelist.GuideListPresenter;
import br.com.mgalhardo.guidebook.util.RxSchedulersOverrideRule;

import static org.mockito.Mockito.mock;

public class GuideListPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule rxJavaRule = new RxSchedulersOverrideRule();

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

}
