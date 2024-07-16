import com.enums.KafkaConstant;

public class TestEnum {
    public static void main(String[] args) {
        String str = KafkaConstant.TOPIC_TEST.getItemName();
        System.out.println("str:" + str);
    }
}
