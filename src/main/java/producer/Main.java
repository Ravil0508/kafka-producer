package producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers", "0.0.0.0:9092, 0.0.0.0:9093, 0.0.0.0:9094");
        kaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        try(KafkaProducer<String, String> producer = new KafkaProducer<>(kaProperties)) {
            StringBuilder s = new StringBuilder("VLADIVOSTOK");
            for(int i = 0; i < 20; i++) {
                s.append(i);
                ProducerRecord<String, String> record = new ProducerRecord("kinaction_helloworld", i+"", s.toString());
                producer.send(record);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
