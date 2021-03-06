// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.bugprison.core.internal;

import static org.projectusus.bugprison.core.SourceCodeLocation.getMethodLocation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IMethod;
import org.projectusus.bugprison.core.Bug;
import org.projectusus.bugprison.core.BugList;
import org.projectusus.bugprison.core.IBuggyProject;

class BuggyProject implements IBuggyProject {

    private static final String BUGS_FILENAME = "usus_bugs.xml"; //$NON-NLS-1$
    private final IProject project;

    BuggyProject( IProject project ) {
        this.project = project;
    }

    public void saveBug( Bug bug ) {
        IFile file = project.getFile( BUGS_FILENAME );
        BugList bugs = loadFromFile( file );
        bugs.addBug( bug );
        SaveBugsJob saveBugsJob = new SaveBugsJob( file, bugs );
        saveBugsJob.schedule();
    }

    private BugList loadFromFile( IFile file ) {
        BugList result = new BugList();
        if( file.exists() ) {
            LoadBugs loadBugs = new LoadBugs( file.getLocation().toOSString() );
            result.addBugs( loadBugs.load() );
        }
        return result;
    }

    public BugList getBugs() {
        IFile file = project.getFile( BUGS_FILENAME );
        BugList result = loadFromFile( file );
        result.setProjectName( project.getName() );
        return result;
    }

    public BugList getBugsFor( IMethod method ) {
        BugList bugs = getBugs();
        return bugs.filter( getMethodLocation( method ) );
    }
}
