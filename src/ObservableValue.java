/**
 * Created by wahaba on 16/10/2015.
 */
import java.util.Observable;

public class ObservableValue extends Observable {
    private int n=0;

    //Constructor
    public ObservableValue(int n)
    {
        this.n = n;
    }

    //Getter , Setter
    public void setValue(int n)
    {
        this.n = n;
        //Update Observer
        setChanged();
        notifyObservers(n);
    }

    public int getValue(){
        return n;
    }
}
