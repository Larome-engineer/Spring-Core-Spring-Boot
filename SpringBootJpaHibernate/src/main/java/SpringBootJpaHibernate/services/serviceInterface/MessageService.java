package SpringBootJpaHibernate.services.serviceInterface;

public interface MessageService {
    String localize(String code, Object ... params);
}
