// Copyright (c) 2009 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.core.internal.proportions.modelupdate;

import static org.eclipse.osgi.util.NLS.bind;
import static org.projectusus.core.internal.util.CoreTexts.ususModelStatus_info;
import static org.projectusus.core.internal.util.CoreTexts.ususModelStatus_ok;
import static org.projectusus.core.internal.util.CoreTexts.ususModelStatus_stale;

import org.joda.time.DateTime;

class UsusModelStatus implements IUsusModelStatus {

    private final IUsusModelUpdate lastComputationRun;
    private final IUsusModelUpdate lastTestRun;

    UsusModelStatus( IUsusModelUpdate lastComputationRun, IUsusModelUpdate lastTestRun ) {
        this.lastComputationRun = lastComputationRun;
        this.lastTestRun = lastTestRun;
    }

    UsusModelStatus() {
        this( null, null );
    }

    @Override
    public String toString() {
        return (isStale() ? ususModelStatus_stale : ususModelStatus_ok) + " - " + renderInfo(); //$NON-NLS-1$
    }

    // interface methods
    // //////////////////

    public boolean isLastComputationRunSuccessful() {
        return lastComputationRun != null && lastComputationRun.isSuccessful();
    }

    public boolean isStale() {
        boolean result = true;
        if( getLastTestTime() != null ) {
            result = isLastComputationRunSuccessful() && getLastTestTime().isBefore( getLastComputationTime() );
        }
        return result;
    }

    // internal
    // /////////

    private String renderInfo() {
        return bind( ususModelStatus_info, getLastComputationTime(), getLastTestTime() );
    }

    private DateTime getLastComputationTime() {
        return nullSafeGetTime( lastComputationRun );
    }

    private DateTime getLastTestTime() {
        return nullSafeGetTime( lastTestRun );
    }

    private DateTime nullSafeGetTime( IUsusModelUpdate modelUpdate ) {
        return modelUpdate == null ? null : modelUpdate.getTime();
    }
}
