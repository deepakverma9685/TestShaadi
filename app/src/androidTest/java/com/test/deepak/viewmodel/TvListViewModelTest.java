package com.test.deepak.viewmodel;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import android.support.test.InstrumentationRegistry;

import com.test.deepak.data.Resource;
import com.test.deepak.data.local.entity.TvEntity;
import com.test.deepak.ui.main.viewmodel.TvListViewModel;
import com.test.deepak.util.MockTestUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.test.deepak.AppConstants.MOVIES_POPULAR;

@RunWith(MockitoJUnitRunner.class)
public class TvListViewModelTest {

    private TvListViewModel tvListViewModel;

    @Mock
    Observer<Resource<List<TvEntity>>> observer;

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        Application app =
                (Application) InstrumentationRegistry
                        .getTargetContext()
                        .getApplicationContext();
        tvListViewModel = new TvListViewModel(app);
    }

    @Test
    public void loadMovieList() {

        tvListViewModel.getTvsLiveData().observeForever(observer);
        tvListViewModel.fetchTvs(MOVIES_POPULAR);

        assert(tvListViewModel.getTvsLiveData().getValue().isLoading());
        assert(tvListViewModel.getTvsLiveData().getValue().data == MockTestUtil.mockTvList(MOVIES_POPULAR));
    }
}