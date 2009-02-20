package bz.asd.autodb.logic;

/**
 *
 * @author lars
 */
public interface CloseListener {

    /**
     * Called before a close is send, to check if the CloseListener is willing
     * to close. Every CloseListener can cancle the close process.
     * @return true = I am ok with closing now
     * false = cancle closing. The cancle process is stopped here.
     */
    public boolean isCloseOk();
    public void close();

}
