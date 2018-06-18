package bodhi.kafkaMirror.kafka09;

import org.apache.kafka.clients.producer.*;

import bodhi.kafkaMirror.common.KafkaProducerInterface;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class MirrorKafkaProducer implements KafkaProducerInterface {

    private org.apache.kafka.clients.producer.KafkaProducer<Long, String> producer;


    public void initConnection(String bootstrapServer) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServer);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "kafkaMirrorProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        producer =  new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    public void sendData(String data, String topic) {

    }
}
