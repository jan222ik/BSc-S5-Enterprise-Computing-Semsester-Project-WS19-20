package at.fhv.itb17.s5.teamb.fxapp.viewmodel;

@Deprecated
public class ViewModelImpl implements ViewModel {
    private String textDemoString;

    public void saveTextDemo(String textDemoString) {
        this.textDemoString = textDemoString;
    }

    public String getTextDemoString() {
        return textDemoString;
    }
}
