// Copyright (c) 2009-2010 by the projectusus.org contributors
// This software is released under the terms and conditions
// of the Eclipse Public License (EPL) 1.0.
// See http://www.eclipse.org/legal/epl-v10.html for details.
package org.projectusus.core.basis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.PlatformObject;
import org.projectusus.core.internal.UsusCorePlugin;

public class CodeProportion extends PlatformObject {

    private final CodeProportionKind metric;
    private final int violations;
    private final CodeStatistic basis;
    private final double sqi;
    private final List<IHotspot> hotspots;

    public CodeProportion( CodeProportionKind metric ) {
        this( metric, 0, new CodeStatistic( CodeProportionUnit.CLASS ), new ArrayList<IHotspot>() );
    }

    public CodeProportion( CodeProportionKind metric, int violations, CodeStatistic basis, List<IHotspot> hotspots ) {
        this( metric, violations, basis, computeSQI( metric, violations, basis ), hotspots );
    }

    public CodeProportion( CodeProportionKind metric, int violations, CodeStatistic basis, double sqiValue, List<IHotspot> hotspots ) {
        this.metric = metric;
        this.violations = violations;
        this.basis = basis;
        this.hotspots = sort( hotspots );
        this.sqi = sqiValue;
    }

    private static double computeSQI( CodeProportionKind metric, int violations, CodeStatistic basis ) {
        if( metric == CodeProportionKind.ACD ) {
            return 100.0 - 100.0 * UsusCorePlugin.getUsusModel().getRelativeACD();
        }
        return new CodeProportionsRatio( violations, basis.getValue() ).computeInverseIndicator();
    }

    public Double getSQIValue() {
        return new Double( sqi );
    }

    public int getViolations() {
        return violations;
    }

    public CodeStatistic getBasis() {
        return basis;
    }

    @Override
    public String toString() {
        return metric.toString() + ": " + violations + " / " + basis; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public CodeProportionKind getMetric() {
        return metric;
    }

    public List<IHotspot> getHotspots() {
        return hotspots;
    }

    private List<IHotspot> sort( List<IHotspot> passedHotspots ) {
        List<IHotspot> result = new ArrayList<IHotspot>();
        result.addAll( passedHotspots );
        Collections.sort( result, new ByHotnessComparator() );
        return result;
    }
}