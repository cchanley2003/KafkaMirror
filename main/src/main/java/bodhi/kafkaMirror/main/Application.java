package bodhi.kafkaMirror.main;

import bodhi.kafkaMirror.common.KafkaConsumerInterface;
import bodhi.kafkaMirror.common.KafkaProducerInterface;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Application {

    public static void main(String[] args) throws Exception {
        ClassLoader newLoader = new URLClassLoader(new URL[] {new File("..\\kafka10\\build\\libs\\kafka10-1.0.jar").toURL()});
        ClassLoader oldLoader = new URLClassLoader(new URL[] {new File("..\\kafka09\\build\\libs\\kafka09-1.0.jar").toURL()});
        boolean oldToNew = Boolean.getBoolean(System.getenv("OLD_TO_NEW"));


        KafkaConsumerInterface consumer;
        KafkaProducerInterface producer;
        if(oldToNew) {
            producer = (KafkaProducerInterface) newLoader.loadClass("bodhi.kafkaMirror.kafka10.MirrorKafkaProducer").newInstance();
            consumer = (KafkaConsumerInterface) oldLoader.loadClass("bodhi.kafkaMirror.kafka09.MirrorKafkaConsumer").newInstance();
        } else {
            producer = (KafkaProducerInterface) oldLoader.loadClass("bodhi.kafkaMirror.kafka09.MirrorKafkaProducer").newInstance();
            consumer = (KafkaConsumerInterface) newLoader.loadClass("bodhi.kafkaMirror.kafka10.MirrorKafkaConsumer").newInstance();

        }

        String srcTopic = System.getenv("SOURCE_TOPIC");
        String destTopic = System.getenv("DEST_TOPIC");
        String destKafka = System.getenv("DEST_KAFKA");
        String srcKafka = System.getenv("SOURCE_KAFKA");

        producer.initConnection(destKafka);

        consumer.init(srcKafka, srcTopic, (id, data) -> {
            producer.sendData(id, data, destTopic);
        });
    }
}
