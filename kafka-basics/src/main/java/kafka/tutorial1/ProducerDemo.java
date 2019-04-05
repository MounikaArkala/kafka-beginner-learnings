package kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {

    public static void main(String[] args) {

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        
        //same as properties.setProperty("bootstrap.servers", bootstrapServers);
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        
        /*Key serializer and value serializer basically help the producer note what type of value you're sending to Kafka, 
        and how this should be serialized to bytes, because Kafka will convert whatever we send. 
        The Kafka client will convert whatever we send to Kafka into bytes, zeroes and ones. 
        And so for our case, we're going to send strings just like we did in the console producer, 
        so we need a string serializer for the key and a string serializer for the value.
        So how do we find this? Well, Kafka has a bunch of serializers ready for us. 
        So we type StringSerializer and as you can see it is being hinted at me. 
        If it's not hinted at you, it's because you haven't imported Kafka correctly, so get back to the previous lecture.
        So StringSerializer, I press Tab, .class.getName. So basically, we're saying the value of key serializer is 
        going to be a StringSerializer, get me the class, get me the name, so this is it, and then for value serializer, 
        we'll do the same thing. StringSerializer, Tab, .class.getname. So here we go, we have our three properties. 
        Now there's something you can do better.*/
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // create a producer record
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("first_topic", "hello world");

        // send data - asynchronous
        producer.send(record);

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }
}
