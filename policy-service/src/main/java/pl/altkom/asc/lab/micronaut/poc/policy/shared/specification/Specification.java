package pl.altkom.asc.lab.micronaut.poc.policy.shared.specification;

import lombok.Getter;

@Getter
public abstract class Specification<T> {
    private static final Object[] EMPTY_PARAMS = new Object[0];

    private String errorCode;
    private Object[] errorParams = EMPTY_PARAMS;
    private String errorMessage;

    public abstract boolean isSatisfiedBy(T objectToCheck);

    public void ensureIsSatisfiedBy(T objectToCheck) {
        if (!isSatisfiedBy(objectToCheck)) {
            //checkNotNull(getErrorCode(), "Error Code is required. Use empty(code, params) method");
            throw new SpecificationNotSatisfiedException(getErrorCode(), getErrorParams());
        }
    }

    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    public Specification<T> not() {
        return new NotSpecification<>(this);
    }

    protected boolean failure(String errorCode, Object... errorParams) {
        this.errorCode = errorCode;
        this.errorParams = errorParams;
        return false;
    }

    protected boolean failure(String errorCode) {
        return failure(errorCode, EMPTY_PARAMS);
    }

    protected boolean failureWithMessage(String errorCode, String errorMessage, Object... errorParams) {
        this.errorMessage = errorMessage;
        return failure(errorCode, errorParams);
    }

    protected boolean success() {
        this.errorParams = EMPTY_PARAMS;
        return true;
    }
}
