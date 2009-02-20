/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.logic;

import java.util.LinkedList;

/**
 *
 * @author lars
 */
public class CloseObservable {

    LinkedList<CloseListener> closeListener;

    public CloseObservable() {
        closeListener = new LinkedList<CloseListener>();
    }

    public void addCloseListener(CloseListener close) {
        closeListener.add(close);
    }

    /**
     * Close all CloseListener.
     * @return true if all CloseListener were closed
     * false if a CloseListener canceled the process
     */
    public boolean closeEvent() {
        boolean realyClose = true;
        for(CloseListener close : closeListener) {
            if(!close.isCloseOk()) {
                realyClose = false;
                break;
            }
        }

        if(realyClose) {
            for(CloseListener close : closeListener) {
                close.close();
            }
        }
        return realyClose;
    }
}
