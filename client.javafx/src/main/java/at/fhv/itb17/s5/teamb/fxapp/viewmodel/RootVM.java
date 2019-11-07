package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

public class RootVM implements ViewModel {
    private SearchVM searchVM;
    private ResultVM resultVM;

    public SearchVM getSearchVM() {
        return searchVM;
    }

    public void setSearchVM(SearchVM searchVM) {
        this.searchVM = searchVM;
    }

    public ResultVM getResultVM() {
        return resultVM;
    }

    public void setResultVM(ResultVM resultVM) {
        this.resultVM = resultVM;
    }

    public String getGetSearchQuery() {
        return searchVM.getLatestSearchViewState().toQueryString();
    }
}
