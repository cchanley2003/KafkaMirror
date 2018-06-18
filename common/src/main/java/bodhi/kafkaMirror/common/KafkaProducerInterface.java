package bodhi.kafkaMirror.common;

public interface KafkaProducerInterface {

    void initConnection(String bootstrapServer);

    void sendData(String data, String topic);

}
