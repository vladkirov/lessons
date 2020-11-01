package com.vladkirov.lessons.lesson15.homework01;

import java.util.*;

public class MessageTask {
    public static void countEachPriority(List<Message> messageList) {
        int[] countEachPriority = new int[MessagePriority.values().length];
        // TODO:  Подсчитать количество сообщений для каждого приоритела
        for (Message message : messageList)
            countEachPriority[message.getPriority().ordinal()] += 1;

        System.out.println("********* countEachPriority **********");
        System.out.println(messageList);
        for (MessagePriority priority : MessagePriority.values())
            System.out.println(priority.name() + " = " + countEachPriority[priority.ordinal()]);
    }

    public static void countEachCode(List<Message> messageList) {
        // TODO: Подсчитать количество сообщений для каждого кода сообщения
        System.out.println("************ countEachCode *************");
        System.out.println("Original List:");
        System.out.println(messageList);

        messageList.sort(new CodeComparator());
        System.out.println("Sorted List: ");
        System.out.println(messageList);

        System.out.println("************ Calculated *************");
        for (int count = 1, i = 1; i <= messageList.size(); i++) {
            if (i == messageList.size()) {
                if (messageList.get(i - 1).getCode() == messageList.get(i - 2).getCode())
                    System.out.println("code: " + messageList.get(i - 2).getCode() + " = " + count);
                else {
                    count = 1;
                    System.out.println("code: " + messageList.get(i - 1).getCode() + " = " + count);
                }
            } else if (messageList.get(i).getCode() != messageList.get(i - 1).getCode()) {
                System.out.println("code: " + messageList.get(i - 1).getCode() + " = " + count);
                count = 0;
            }
            count++;
        }
    }

    public static void uniqueMessageCount(List<Message> messageList) {
        // TODO: Подсчитать количество уникальных сообщений
        System.out.println(messageList);
        System.out.println("uniqueMessageCount = " + new HashSet<>(messageList).size());
    }

    public static List<Message> uniqueMessagesInOriginalOrder(List<Message> messageList) {
        // TODO: вернуть только неповторяющиеся сообщения и в том порядке,
        //  в котором они встретились в первоначальном списке
        //  Например, было: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}]
        //  на выходе: [{URGENT, 4}, {HIGH, 9}, {LOW, 3}]
        return new ArrayList<>(new LinkedHashSet<>(messageList));
    }

    public static void removeEach(List<Message> messageList, MessagePriority priority) {
        // TODO: удалить из коллекции каждое сообщение с заданным приоритетом
        System.out.println("**** removeEach : " + priority + " ***");
        System.out.println(messageList);

        Iterator<Message> messageIterator = messageList.listIterator();
        while (messageIterator.hasNext())
            if (messageIterator.next().getPriority() == priority)
                messageIterator.remove();

        System.out.println(messageList);
    }

    public static void removeOther(List<Message> messageList, MessagePriority priority) {
        // TODO: удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет
        System.out.println("**** removeOther : " + priority + " ***");
        System.out.println(messageList);

        Iterator<Message> messageIterator = messageList.listIterator();
        while (messageIterator.hasNext())
            if (messageIterator.next().getPriority() != priority)
                messageIterator.remove();

        System.out.println(messageList);
    }

    public static void main(String[] args) {
        System.out.println("******** MessageTask **********");

        List<Message> messages = MessageGenerator.generate(10);
        uniqueMessageCount(messages);

        messages = MessageGenerator.generate(35);
        System.out.println("****** uniqueMessagesInOriginalOrder *******");
        System.out.println(messages);
        System.out.println(uniqueMessagesInOriginalOrder(messages));

        messages = MessageGenerator.generate(15);
        removeEach(messages, MessagePriority.HIGH);

        messages = MessageGenerator.generate(15);
        removeOther(messages, MessagePriority.HIGH);

        messages = MessageGenerator.generate(25);
        countEachPriority(messages);

        countEachCode(messages);
    }
}