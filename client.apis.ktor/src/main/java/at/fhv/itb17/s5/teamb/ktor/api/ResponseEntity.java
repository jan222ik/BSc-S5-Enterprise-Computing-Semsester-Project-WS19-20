package at.fhv.itb17.s5.teamb.ktor.api;

public class ResponseEntity<T> {
    private T generic;
    private int status;

    public ResponseEntity(T generic, int status) {
        this.generic = generic;
        this.status = status;
    }

    public ResponseEntity(int status) {
        this(null, status);
    }

    public T getGeneric() {
        return generic;
    }

    public void setGeneric(T generic) {
        this.generic = generic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
