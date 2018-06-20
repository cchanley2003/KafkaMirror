package bodhi.kafkaMirror.common;

public interface KafkaProducerInterface {

    void initConnection(String bootstrapServer);

    void sendData(Long key, String data, String topic);

}
