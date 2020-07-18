package ui;

import java.util.Observable;
import java.util.Observer;

public class ReviewsChart implements Observer {
    int counts;

    //EFFECTS: prints the lines of comments added if the program notifies "exit"
    // prints the argument otherwise
    @Override
    public void update(Observable o, Object arg) {
        if (arg.equals("exit")) {
            System.out.println(counts + " lines of comments have been added this time");
        } else {
            counts++;
            System.out.println(arg);
        }
    }
}
