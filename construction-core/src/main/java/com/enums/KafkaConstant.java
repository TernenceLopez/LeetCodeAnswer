package com.enums;

public enum KafkaConstant {
    /**
     * kafka topic
     */
    TOPIC_TEST("topic-test"),
    CONSUMER_GROUP_NAME("test"),
    THREAD_POINT_FACTORY_NAME("RoadStatusSaveConsumer"),
    TOPIC_VEHICLE_ALARM("topic-vehicle-alarm"),
    TOPIC_VEHICLE_ALARM_STATUS("topic-vehicle-alarm-status"),
    TOPIC_VEHICLE_ALARM_STATUS_SAVE("topic-vehicle-alarm-status-save");

    private final String itemName;

    KafkaConstant(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName(){
        return itemName;
    }
}
