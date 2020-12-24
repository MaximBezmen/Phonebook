package exception;

public class SQLExceptionDao extends Exception {
    private String massage;
    public SQLExceptionDao(String massage){
        this.massage = massage;
    }
}
