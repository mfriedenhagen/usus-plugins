// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.core.internal.proportions.model;

public class MetricKGHotspot extends Hotspot {

    public MetricKGHotspot( String className, int classSize, int sourcePosition, int lineNumber ) {
        super( className, classSize, sourcePosition, lineNumber );
    }

    @Override
    public String toString() {
        return getName() + " (KG = " + getMetricsValue() + ")"; //$NON-NLS-1$//$NON-NLS-2$ 
    }
}
