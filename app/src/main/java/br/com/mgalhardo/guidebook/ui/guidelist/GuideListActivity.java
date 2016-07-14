package br.com.mgalhardo.guidebook.ui.guidelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import br.com.mgalhardo.guidebook.R;
import br.com.mgalhardo.guidebook.core.aggregation.GuideAggregation;
import br.com.mgalhardo.guidebook.core.entity.Guide;
import br.com.mgalhardo.guidebook.data.local.GuideCache;
import br.com.mgalhardo.guidebook.data.remote.GuideService;
import br.com.mgalhardo.guidebook.data.repository.GuideRepository;
import br.com.mgalhardo.guidebook.data.repository.impl.GuideRepositoryImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideListActivity extends AppCompatActivity implements GuideListContract.View {

    @BindView(R.id.recyclerview)
    RecyclerView guideList;

    @BindView(R.id.successContainer)
    LinearLayout successContainer;

    @BindView(R.id.errorContainer)
    LinearLayout errorContainer;

    @BindView(R.id.loadingContainer)
    LinearLayout loadingContainer;

    @BindView(R.id.emptyContainer)
    LinearLayout emptyContainer;

    private GuideListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dependencyInjection();
        initialize(savedInstanceState);
    }

    private void dependencyInjection() {
        GuideService service = GuideService.Builder.build();
        GuideCache cache = new GuideCache();
        GuideRepository repository = new GuideRepositoryImpl(service, cache);
        presenter = new GuideListPresenter(this, repository);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(GuideAggregation.KEY, presenter.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private void initialize(Bundle bundle) {
        if (bundle != null && bundle.containsKey(GuideAggregation.KEY)) {
            GuideAggregation aggregation = (GuideAggregation) bundle.getSerializable(GuideAggregation.KEY);
            presenter.onLoadInstanceState(aggregation);
            presenter.refreshUi();
        } else {
            presenter.loadGuides();
        }
    }

    @OnClick(R.id.retryButton)
    void retryGetCompanies() {
        presenter.retryButtonClick();
    }

    @Override
    public void setupGuideList(List<Guide> guides) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        GuideListAdapter companyListAdapter = new GuideListAdapter(this, guides);
        guideList.setLayoutManager(manager);
        guideList.setAdapter(companyListAdapter);
    }

    @Override
    public void showLoadingLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.VISIBLE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.VISIBLE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLayout() {
        successContainer.setVisibility(View.VISIBLE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyLayout() {
        successContainer.setVisibility(View.GONE);
        errorContainer.setVisibility(View.GONE);
        loadingContainer.setVisibility(View.GONE);
        emptyContainer.setVisibility(View.VISIBLE);
    }

}
