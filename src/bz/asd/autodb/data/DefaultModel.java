/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bz.asd.autodb.data;

/**
 *
 * @author lars
 */
public class DefaultModel extends Model {

    protected boolean hasChanged;

    public DefaultModel() {
        hasChanged = false;
    }
    
    @Override
	public void setHasChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}

    public boolean hasChanged() {
		return hasChanged;
	}

}
