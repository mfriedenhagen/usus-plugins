package org.projectusus.core.internal.proportions.rawdata;

import org.projectusus.core.internal.proportions.model.IHotspot;
import org.projectusus.core.internal.proportions.model.MetricCWHotspot;

public class YellowCount {

    private int count;

    public void setYellowCount( int count ) {
        this.count = count;
    }

    public int getViolationCount( CodeProportionKind metric ) {
        return metric.operatesOnNonJavaFiles() && (count > 0) ? 1 : 0;
    }

    public IHotspot createHotspot() {
        return new MetricCWHotspot( count );
    }

    public int getOverallMetric( CodeProportionKind metric ) {
        if( metric == CodeProportionKind.CW ) {
            return count;
        }
        return 0;
    }
}
