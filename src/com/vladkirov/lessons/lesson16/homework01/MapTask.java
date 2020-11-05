package com.vladkirov.lessons.lesson16.homework01;

import com.sun.source.tree.Tree;
import com.vladkirov.lessons.lesson16.Role;
import com.vladkirov.lessons.lesson16.User;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;

public class MapTask {
    public static List<String> getLoginsByCity(HashMap<String, String> map, String city) {
        Objects.requireNonNull(map, "map can't be null");
        Objects.requireNonNull(city, "city can't be null");
        if (map.isEmpty()) throw new IllegalArgumentException("map can't be empty!");

        System.out.println("************* buildLoginsByCity *************");

        List<String> logins = new LinkedList<>();
        for (Map.Entry<String, String> task : map.entrySet())
            if (task.getValue().equals(city)) logins.add(task.getKey());
        return logins;
    }

    public static Map<String, Integer> getNumberOccurrences(List<String> words) {
        Objects.requireNonNull(words, "words can't be null");
        if (words.isEmpty()) throw new IllegalArgumentException("words can't be empty");

        System.out.println("************* getNumberOccurrences *************");

        HashMap<String, Integer> cityNumberMap = new HashMap<>();
        for (String word : words)
            cityNumberMap.put(word, cityNumberMap.getOrDefault(word, 0) + 1);
        return cityNumberMap;
    }

    public static HashMap<String, Customer> getByAge(HashMap<String, Customer> map, int from, int to) {
        System.out.println("************* getByAge *************");

        HashMap<String, Customer> newMap = new HashMap<>();
        for (Map.Entry<String, Customer> entry : map.entrySet())
            if (entry.getValue().getAge() >= from && entry.getValue().getAge() < to)
                newMap.put(entry.getKey(), entry.getValue());
        return newMap;
    }

    public static int getFrequencySingleWord(String text, String example) {
        System.out.println("*********** getFrequencySingleWord ***************");
        System.out.println("Word: " + example);

        int count = 0;
        String[] words = text.split(" ");

        for (String word : words)
            if (word.equals(example)) count++;

        return count;
    }

    public static Map<String, Integer> getTop10FrequencyWords(String text) {
        System.out.println("********** getTop10FrequencyWords ************");

        Map<String, Integer> wordFreqMap = new HashMap<>();
        String[] words = text.split(" ");
        for (String word : words)
            wordFreqMap.put(word, wordFreqMap.getOrDefault(word, 0) + 1);

        Map<String, Integer> tempMap = sortByValue(wordFreqMap);

        Map<String, Integer> wordTop10Map = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            if (count >= 10) break;

            count++;
            wordTop10Map.put(entry.getKey(), entry.getValue());
        }

        return wordTop10Map;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortedMap) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(unsortedMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
            result.put(entry.getKey(), entry.getValue());

        return result;
    }

    public static Map<Integer, HashSet<String>> getWordsByCountChars(String text) {
        System.out.println("******** getWordsByCountChars ***********");
        Map<Integer, HashSet<String>> wordFreqMap = new TreeMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            HashSet<String> arrayWords = wordFreqMap.getOrDefault(word.length(), new HashSet<>());
            arrayWords.add(word);
            wordFreqMap.put(word.length(), arrayWords);
        }
        return wordFreqMap;
    }

    public static Map<String, Integer> getPercentFreq(String text) {
        System.out.println("********** getPercentFreq ************");
        Map<String, Integer> uniqMap = new TreeMap<>();

        text = text.replaceAll("\\s+","");
        text = text.replaceAll("-","");
        String[] letters = text.split("");
        for (String symbol : letters) {
            uniqMap.put(symbol, uniqMap.getOrDefault(symbol, 0) + 1);
        }

        int sumSymbols = 0;
        for (Integer value : uniqMap.values()) {
            sumSymbols += value;
        }
        for (Map.Entry<String, Integer> entry : uniqMap.entrySet()) {
            double tmp = (double) entry.getValue() / (double) sumSymbols * 100.0;
            entry.setValue((int)tmp);
        }

        return uniqMap;
    }

    public static void main(String[] args) {
        // TODO:: написать статический метод, который приннимает на вход мапу (firstTaskMap) и город (city)
        //  и формирует список (List) логинов, которые соответствуют переданному городу

        HashMap<String, String> firstTaskMap = new HashMap<>();
        firstTaskMap.put("qwe", "Тверь");
        firstTaskMap.put("asd", "Тверь");
        firstTaskMap.put("zxc", "Москва");
        firstTaskMap.put("rty", "Тверь");
        firstTaskMap.put("fgh", "Магадан");

        String city = "Тверь";

        System.out.println(getLoginsByCity(firstTaskMap, city));

        // TODO:: дан список слов (words).
        //  Написать метод, который будет возвращать количество
        //  одинаковых слов с списке
        //  в виде Map<String, Integer>,
        //  где String - слово и Integer - количество повторений

        List<String> words = new ArrayList<>();
        words.add("may");
        words.add("august");
        words.add("june");
        words.add("may");
        words.add("may");
        words.add("july");
        words.add("may");
        words.add("august");
        words.add("august");

        System.out.println(getNumberOccurrences(words));

        // TODO:: дана мапа (customerMap).
        //  Написать метод, который принимает на вход агрументы int from и int to и возвращает
        //  новую мапу, в которую войдут все покупатели,
        //  возраст которых находится в диапазоне [from, to)

        HashMap<String, Customer> customerMap = new HashMap<>();
        customerMap.put("1", new Customer("Павел", "1", 23));
        customerMap.put("2", new Customer("Олег", "2", 17));
        customerMap.put("3", new Customer("Максим", "3", 48));
        customerMap.put("4", new Customer("Евгения", "4", 67));

        System.out.println(getByAge(customerMap, 18, 50));

        // TODO:: Задания по тексту (text). На каждый пункт - минимум один метод:
        //  1. написать метод, принимающий на вход слово и возвращающий частоту встречаемости данного слова в тексте
        //  2. написать метод, который собирает все слова в группы по количеству букв:
        //  например, в одну группу попадут слова: 3 - [the, war, jar, get, met...], в другую 2 - [on, up, no, of...]
        //  3. написать метод, который выводит в консоль топ 10 самых частых слов
        //  4. Вывести частоту встречаемости букв анг алфавита в данном тексте. Вывести в процентах для каждой буквы

        // в тексте содержатся только буквы и пробельные символы
        String text = "It is a uncover long established fact that a reader will be distracted uncover by the readable content of a page " +
                "when looking at its layout The point of using uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters" +
                "as uncover opposed to still using Content here humour uncover content here making it look like readable English Many desktop publishing " +
                "packages and web page editors now use Lorem Ipsum as their default model text and a search for lorem ipsum will " +
                "uncover many web sites still uncover in their infancy Various versions uncover have evolved over the years uncover sometimes by accident" +
                " sometimes on purpose injected humour and the like";

        System.out.println("Count = " + getFrequencySingleWord(text, "a"));

        System.out.println(getTop10FrequencyWords(text));

        System.out.println(getWordsByCountChars(text));

        System.out.println(getPercentFreq(text));
    }
}

//class valComp implements Comparator<Integer> {
//
//    @Override
//    public int compare(Integer o1, Integer o2) {
//        return Integer.compare(o1, o2);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//}
