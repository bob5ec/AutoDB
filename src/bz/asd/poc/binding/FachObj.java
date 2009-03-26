/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.poc.binding;

/**
 *
 * @author lars
 */
public class FachObj {
    private String Name;
    
    private static int instCount = 0;
    private int myInst;

    public FachObj() {
        myInst = instCount++;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
        System.out.println(myInst+": "+Name);
    }
}
