package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

public enum TimeAggregationUnit {
    DAY,
    WEEK,
    MONTH,
    YEAR;

    public DateHistogramInterval toDateHistogramInterval(){
        switch (this) {
            case DAY:
                return DateHistogramInterval.DAY;
            case WEEK:
                return DateHistogramInterval.WEEK;
            case MONTH:
                return DateHistogramInterval.MONTH;
            case YEAR:
                return DateHistogramInterval.YEAR;
            default:
                throw new IllegalArgumentException("Invalid unit value");
        }
    }
}
