// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.bugprison.internal;

import static org.eclipse.core.runtime.jobs.Job.getJobManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.projectusus.bugprison.core.Bug;
import org.projectusus.bugprison.core.BugList;
import org.projectusus.bugprison.core.IBuggyProject;
import org.projectusus.bugprison.core.internal.SaveBugsJob;
import org.projectusus.core.internal.JavaProject;

public class ReportBugPDETest {

    private static final DateTime TODAY = new DateMidnight().toDateTime();

    @Rule
    public JavaProject project = new JavaProject();

    @Test
    public void testGetBugsWithNoBugs() throws Exception {
        BugList bugs = project.as( IBuggyProject.class ).getBugs();
        assertTrue( bugs.isEmpty() );
    }

    @Test
    public void testCreateBug() throws Exception {
        Bug bug = createBug();
        project.as( IBuggyProject.class ).saveBug( bug );
        getJobManager().join( SaveBugsJob.FAMILY, new NullProgressMonitor() );
        BugList bugs = project.as( IBuggyProject.class ).getBugs();
        assertEquals( 1, bugs.size() );
        testBugContent( bugs.getBugs()[0] );
    }

    private void testBugContent( Bug bug ) {
        assertEquals( TODAY, bug.getCreationTime() );
        assertEquals( TODAY, bug.getReportTime() );
        assertEquals( "pde test title", bug.getTitle() );
        assertEquals( 4, bug.getBugMetrics().getCyclomaticComplexity() );
        assertEquals( 8, bug.getBugMetrics().getMethodLength() );
        assertEquals( 7, bug.getBugMetrics().getNumberOfMethods() );

        assertEquals( project.get().getName(), bug.getLocation().getProject() );
        assertEquals( "pde.test.package", bug.getLocation().getPackageName() );
        assertEquals( "TestClassName", bug.getLocation().getClassName() );
        assertEquals( "testMethodName", bug.getLocation().getMethodName() );
    }

    private Bug createBug() {
        Bug bug = new Bug();
        bug.setCreationTime( TODAY );
        bug.setReportTime( TODAY );
        bug.setTitle( "pde test title" );
        bug.getBugMetrics().setCyclomaticComplexity( 4 );
        bug.getBugMetrics().setMethodLength( 8 );
        bug.getBugMetrics().setNumberOfMethods( 7 );

        bug.getLocation().setPackageName( "pde.test.package" );
        bug.getLocation().setClassName( "TestClassName" );
        bug.getLocation().setMethodName( "testMethodName" );
        return bug;
    }

}
