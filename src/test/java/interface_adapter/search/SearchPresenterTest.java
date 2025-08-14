package interface_adapter.search;

import entity.SearchResult;
import org.junit.Test;
import use_case.search.SearchOutputData;

import java.util.Collections;

import static org.junit.Assert.*;

public class SearchPresenterTest {
    @Test
    public void successPublishesResults() {
        SearchViewModel vm = new SearchViewModel();
        SearchPresenter presenter = new SearchPresenter(vm);
        SearchResult result = new SearchResult();
        presenter.prepareSuccessView(new SearchOutputData(Collections.singletonList(result)));
        SearchState state = vm.getState();
        assertFalse(state.isLoading());
        assertNull(state.getErrorMessage());
        assertEquals(1, state.getResults().size());
    }

    @Test
    public void failurePublishesError() {
        SearchViewModel vm = new SearchViewModel();
        SearchPresenter presenter = new SearchPresenter(vm);
        presenter.prepareFailView("oops");
        SearchState state = vm.getState();
        assertEquals("oops", state.getErrorMessage());
        assertFalse(state.isLoading());
    }
}
