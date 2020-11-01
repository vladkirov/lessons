package com.vladkirov.lessons.lesson15.homework01;

import java.util.*;

enum MessagePriority {
    LOW, MEDIUM, HIGH, URGENT;

    public static MessagePriority getPriority(int ord) {
        for (MessagePriority mp : values())
            if (ord == mp.ordinal()) return mp;

        throw new AssertionError("Wrong ordinal: " + ord);
    }
}


public class Message {
    private int code;
    private MessagePriority priority;

    public Message(int code, MessagePriority priority) {
        this.code = code;
        this.priority = priority;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (code != message.code) return false;
        return priority == message.priority;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", priority=" + priority +
                '}';
    }

    public static void main(String[] args) {
        List<Message> messages = MessageGenerator.generate(34);
        System.out.println(messages);
    }
}

class MessageGenerator {
    public static List<Message> generate(int num) {
        if (num <= 0) return Collections.emptyList();

        Random random = new Random();
        List<Message> messages = new ArrayList<>(num);

        int typesCount = MessagePriority.values().length;

        for (int i = 0; i < num; i++)
            messages.add(new Message(random.nextInt(10), MessagePriority.getPriority(random.nextInt(typesCount))));

        return messages;
    }
}

class CodeComparator implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        return Integer.compare(o1.getCode(), o2.getCode());
    }
}