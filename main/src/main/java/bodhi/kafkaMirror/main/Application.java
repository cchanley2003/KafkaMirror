package bodhi.kafkaMirror.main;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Application {

    public static void main(String[] args) throws Exception {
        ClassLoader loader1 = new URLClassLoader(new URL[] {new File("..\\kafka10\\build\\libs\\kafka10-1.0.jar").toURL()});
        ClassLoader loader2 = new URLClassLoader(new URL[] {new File("..\\kafka09\\build\\libs\\kafka09-1.0.jar").toURL()});
//
//        Class<?> c1 = loader1.loadClass("bodhi.kafkaMirror.kafka10.MirrorKafkaProducer");
//        Class<?> c2 = loader2.loadClass("bodhi.kafkaMirror.kafka09.MirrorKafkaProducer");
//
//        KafkaProducerInterface es1 = (KafkaProducerInterface) c1.newInstance();
//        KafkaProducerInterface es2 = (KafkaProducerInterface) c2.newInstance();



    }
}
