package SpringBootJDBCTemplate.exception;

public class LocaleNotSupportedException extends ServiceException {
    public LocaleNotSupportedException(String code, Object... params) {
        super(code, params);
    }
}

