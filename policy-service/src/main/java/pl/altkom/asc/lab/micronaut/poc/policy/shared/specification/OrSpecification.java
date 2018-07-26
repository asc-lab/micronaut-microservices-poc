package pl.altkom.asc.lab.micronaut.poc.policy.shared.specification;

public class OrSpecification<T> extends Specification<T> {
    private final Specification<T> leftSpec;
    private final Specification<T> rightSpec;

    public OrSpecification(Specification<T> leftSpec, Specification<T> rightSpec) {
        this.leftSpec = leftSpec;
        this.rightSpec = rightSpec;
    }

    @Override
    public boolean isSatisfiedBy(T objectToCheck) {
        if (!leftSpec.isSatisfiedBy(objectToCheck) && !rightSpec.isSatisfiedBy(objectToCheck)){
            return failure(leftSpec.getErrorCode(), leftSpec.getErrorParams());
        }

        return success();
    }
}
