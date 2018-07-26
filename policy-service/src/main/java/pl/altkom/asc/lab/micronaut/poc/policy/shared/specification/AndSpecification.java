package pl.altkom.asc.lab.micronaut.poc.policy.shared.specification;

public class AndSpecification<T> extends Specification<T> {
    private final Specification<T> leftSpec;
    private final Specification<T> rightSpec;

    public AndSpecification(Specification<T> leftSpec, Specification<T> rightSpec) {
        this.leftSpec = leftSpec;
        this.rightSpec = rightSpec;
    }


    @Override
    public boolean isSatisfiedBy(T objectToCheck) {
        if (!leftSpec.isSatisfiedBy(objectToCheck)){
            return failure(leftSpec.getErrorCode(), leftSpec.getErrorParams());
        }

        if (!rightSpec.isSatisfiedBy(objectToCheck)){
            return failure(rightSpec.getErrorCode(), rightSpec.getErrorParams());
        }

        return success();
    }
}
