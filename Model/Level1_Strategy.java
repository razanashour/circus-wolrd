package Model;

public class Level1_Strategy implements IStrategy {

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public int getTimeout() {
        //return 3;
        return 3 * 60 * 1000;
    }

    @Override
    public int getmaxnumofplates() {
        return 5;
    }

    @Override
    public int getnumberofbomb() {
        return 1;
    }

}
