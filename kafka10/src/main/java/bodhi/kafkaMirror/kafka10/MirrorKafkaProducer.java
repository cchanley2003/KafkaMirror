package bodhi.kafkaMirror.kafka10;

import bodhi.kafkaMirror.common.KafkaProducerInterface;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MirrorKafkaProducer implements KafkaProducerInterface {

    private KafkaProducer<Long, String> producer;


    @Override
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

    @Override
    public void sendData(Long key, String data, String topic) {
        long time = System.currentTimeMillis();

        try {
            final ProducerRecord<Long, String> record =
                    new ProducerRecord<>(topic, key, data);

            RecordMetadata metadata = producer.send(record).get();

            long elapsedTime = System.currentTimeMillis() - time;
            System.out.printf("sent record(key=%s value=%s) " +
                            "meta(partition=%d, offset=%d) time=%d\n",
                    record.key(), record.value(), metadata.partition(),
                    metadata.offset(), elapsedTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
