package springdata.mappingdtolab.exceptions;

public class NonExistingEntityException extends RuntimeException {

    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String message) {
        super(message);
    }

}
