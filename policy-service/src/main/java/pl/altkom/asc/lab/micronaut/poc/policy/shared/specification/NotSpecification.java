package pl.altkom.asc.lab.micronaut.poc.policy.shared.specification;

public class NotSpecification<T> extends Specification<T> {
    private final Specification<T> spec;

    public NotSpecification(Specification<T> spec) {
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(T objectToCheck) {
        if (spec.isSatisfiedBy(objectToCheck)){
            return failure(spec.getErrorCode(), spec.getErrorParams());
        }

        return success();
    }
}
