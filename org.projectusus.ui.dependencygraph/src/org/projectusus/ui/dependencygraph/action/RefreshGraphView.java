package org.projectusus.ui.dependencygraph.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.projectusus.ui.dependencygraph.GraphModel;

/**
 * Refresh action for acd view.
 * 
 * @author Joachim Meyer - meyer.joachim@gmail.com
 * 
 */
public class RefreshGraphView implements IViewActionDelegate {

	public void run(IAction arg0) {
		GraphModel.getInstance().update();
	}

	public void init(IViewPart arg0) {
		// unused
	}

	public void selectionChanged(IAction arg0, ISelection arg1) {
		// unused
	}

}