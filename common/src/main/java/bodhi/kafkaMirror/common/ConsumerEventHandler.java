package bodhi.kafkaMirror.common;

public interface ConsumerEventHandler {

    void handleEvent(Long id, String data);
}
