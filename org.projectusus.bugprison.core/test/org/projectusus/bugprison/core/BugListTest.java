// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.bugprison.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.projectusus.bugprison.core.Bug;
import org.projectusus.bugprison.core.BugList;
import org.projectusus.bugprison.core.MethodLocation;

public class BugListTest {

    private BugList bugs;

    @Before
    public void setUp() {
        bugs = new BugList();
    }

    @Test
    public void testFilter() {
        addBug();
        BugList filteredBugs = bugs.filter( createLocation() );
        assertEquals( 1, filteredBugs.size() );
    }

    @Test
    public void testFilterWithEmptyResult() {
        addBug();
        MethodLocation location = createLocation();
        location.setClassName( "an other classname" ); //$NON-NLS-1$
        BugList filteredBugs = bugs.filter( location );
        assertTrue( filteredBugs.isEmpty() );
    }

    private void addBug() {
        Bug bug = new Bug();
        bug.setLocation( createLocation() );
        bugs.addBug( bug );
    }

    private MethodLocation createLocation() {
        MethodLocation result = new MethodLocation();

        result.setProject( "project" ); //$NON-NLS-1$
        result.setPackageName( "packageName" ); //$NON-NLS-1$
        result.setClassName( "className" ); //$NON-NLS-1$
        result.setMethodName( "methodName" ); //$NON-NLS-1$

        return result;
    }

}
