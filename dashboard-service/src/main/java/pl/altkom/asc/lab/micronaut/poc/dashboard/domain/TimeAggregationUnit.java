package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

public enum TimeAggregationUnit {
    DAY,
    WEEK,
    MONTH,
    YEAR;

    public DateHistogramInterval toDateHistogramInterval(){
        return switch (this) {
            case DAY-> DateHistogramInterval.DAY;
            case WEEK-> DateHistogramInterval.WEEK;
            case MONTH-> DateHistogramInterval.MONTH;
            case YEAR-> DateHistogramInterval.YEAR;
            default->
                throw new IllegalArgumentException("Invalid unit value");
        };
    }
}
