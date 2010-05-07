// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.core.internal.coverage;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mountainminds.eclemma.core.analysis.ICounter;

public class CoverageTest {

    @Test
    public void addCoverage() {
        TestCoverage coverage = new TestCoverage( 23, 100 ).add( new TestCoverage( 17, 50 ) );
        assertEquals( 40, coverage.getCoveredCount() );
        assertEquals( 150, coverage.getTotalCount() );
    }

    @Test
    public void getCoverageInPercentDisplayString() {
        assertEquals( "0.0 %", new TestCoverage( 0, 0 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
        assertEquals( "0.0 %", new TestCoverage( 0, 100 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
        assertEquals( "43.0 %", new TestCoverage( 43, 100 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
        assertEquals( "33.3 %", new TestCoverage( 3, 9 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
        assertEquals( "4.0 %", new TestCoverage( 16, 400 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
        assertEquals( "1.5 %", new TestCoverage( 3, 200 ).getCoverageInPercentDisplayString() ); //$NON-NLS-1$
    }

    @Test
    public void from() {
        TestCoverage coverage = new TestCoverage( createCounter( 1, 2 ) );
        assertEquals( new TestCoverage( 1, 2 ), coverage );
    }

    private ICounter createCounter( final int coveredCount, final int totalCount ) {
        return new TestCounter( coveredCount, totalCount );
    }

    private final class TestCounter implements ICounter {
        private final int coveredCount;
        private final int totalCount;

        private TestCounter( int coveredCount, int totalCount ) {
            this.coveredCount = coveredCount;
            this.totalCount = totalCount;
        }

        public long getCoveredCount() {
            return coveredCount;
        }

        public double getRatio() {
            return 0;
        }

        public long getTotalCount() {
            return totalCount;
        }

        /**
         * @param arg0
         *            is not used
         */
        public int compareTo( Object arg0 ) {
            return 0;
        }
    }
}
