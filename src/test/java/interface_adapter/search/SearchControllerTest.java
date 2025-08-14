package interface_adapter.search;

import entity.FilterOptions;
import org.junit.Test;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import static org.junit.Assert.*;

public class SearchControllerTest {
    private static class MockInteractor implements SearchInputBoundary {
        SearchInputData last;
        @Override
        public void execute(SearchInputData inputData) {
            this.last = inputData;
        }
    }

    @Test
    public void handleSearchPassesInputToInteractor() {
        MockInteractor interactor = new MockInteractor();
        SearchController controller = new SearchController(interactor);
        FilterOptions opts = new FilterOptions();
        controller.handleSearch("q", opts);
        assertEquals("q", interactor.last.getQuery());
        assertSame(opts, interactor.last.getFilters());
    }
}
