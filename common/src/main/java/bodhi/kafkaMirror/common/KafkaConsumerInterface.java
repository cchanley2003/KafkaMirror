package bodhi.kafkaMirror.common;

public interface KafkaConsumerInterface {
    void start();
    void init(String bootstrapServers, String topic, ConsumerEventHandler handler);
}
