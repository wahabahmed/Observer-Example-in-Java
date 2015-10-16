import java.util.Observer;
import java.util.Observable;

/**
 * Created by wahaba on 16/10/2015.
 */


public class TextObserver implements Observer {
    private ObservableValue ov = null;

    public TextObserver(ObservableValue ov){
        this.ov = ov;
    }

    public void update(Observable obs, Object obj){
        if(obs == ov){
            System.out.println("Changed Value is " + ov.getValue());
        }
    }
}
