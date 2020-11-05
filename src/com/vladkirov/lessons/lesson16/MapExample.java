package com.vladkirov.lessons.lesson16;

import java.lang.ref.WeakReference;
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        User cbf = new User("cbf", "12443", Role.USER);
        User asd = new User("asd", "2625", Role.ADMIN);
        User rty = new User("rty", "8734", Role.USER);
        User bnm = new User("bnm", "2688", Role.ADMIN);
        // Мапы хранят данные в парах ключ - значение
        // Особенности map:
        // 1. ключи мапы должны быть уникальны
        // 2. каждому ключу соответствует только одно значение
        // 3. Мапы не являются коллекциями

        // наиболее популярные HashMap<K, V> и TreeMap<K, V>

        // Особенности HashMap:
        // 1. хранит ключи в hash таблице (на основе hash кода)
        // 2. обладает хорошей производительностью
        // 3. в качестве ключа можно использовать null
        // 4. порядок хранения элементов может отличаться от порядка
        // добавления

        // в <> сначала указываем типа данных ключей (в данном случае String),
        // затем тип данных значений (в данном случае User)
        HashMap<String, User> userHashMap = new HashMap<>();
        // добавление элементов
        userHashMap.put(asd.getLogin(), asd);
        userHashMap.put(cbf.getLogin(), cbf);
        userHashMap.put(rty.getLogin(), rty);
        userHashMap.put(null, null);

        System.out.println(userHashMap);

        // удаление
        userHashMap.remove("asd"); // по ключу
        userHashMap.remove("rty", cbf); // по ключу и значению

        // замена
        userHashMap.replace("rty", null); // по ключу
        userHashMap.replace("rty", null, rty); // по ключу и значению
        System.out.println(userHashMap);

        // получение
        System.out.println(userHashMap.get("rty"));
        System.out.println(userHashMap.get("asd"));
        // если ключ (в данном случае uuu) не найден, вернет значение
        // по умолчанию (в данном случае cbf)
        System.out.println(userHashMap.getOrDefault("uuu", cbf));

        // проверить, содержится ли в мапе ключ
        System.out.println(userHashMap.containsKey("uuu"));
        // проверить, содержится ли в мапе значение
        System.out.println(userHashMap.containsValue(cbf));

        System.out.println("---Перебор мап---");
        for (Map.Entry<String, User> pair: userHashMap.entrySet()){
            System.out.println("ключ: " + pair.getKey());
            System.out.println("значение: " + pair.getValue());
        }

        // LinkedHashMap аналогична HashMap, но позволяет сохранять
        //порядок элементов (из-за чего менее производительна, чем HashMap)

        // Особенности EnumMap:
        // 1. использует перечисления (enum) в качестве ключей
        // 2. нельзя использовать null в качестве ключа
        // 3. все ключи должны быть одного типа перечисления
        // 4. все значения содержит в массиве
        // (размер массива - количество элементов перечисления)
        // 5. порядок хранения элементов соответствует порядку
        // элементов enum
        // 6. для извлечения значений использует индекс ключа:
        // vals[key.ordinal()]

        // при создании объекта в конструктор необходимо передать ссылку
        // на класс перечисления
        EnumMap<Role, ArrayList<User>> enumMap = new EnumMap<>(Role.class);
        // Arrays.asList(obj1, obj2) - вернет List с obj1 и obj2
        enumMap.put(Role.USER, new ArrayList<>(Arrays.asList(cbf, rty)));
        enumMap.put(Role.ADMIN, new ArrayList<>(Arrays.asList(asd, bnm)));

        System.out.println(enumMap.get(Role.USER));

        // вывести в консоль логины! всех пользователей с ролью ADMIN
        for (User user: enumMap.get(Role.ADMIN)) {
            System.out.println(user.getLogin());
        }

        User newUser = new User("newUser", "111", Role.USER);
        // добавить объект в enumMap, учитывая, что роль объекта может
        // быть не известна
        enumMap.get(newUser.getRole()).add(newUser);

        User someUser = new User("some", "9090", Role.USER);
        // если на объект остались только слабые ссылки,
        // сборщик мусора может удалить такой объект (при нехватке памяти)
        WeakReference<User> weakUser = new WeakReference<>(someUser);
        someUser = null;

        WeakHashMap<Object, String> weakMap = new WeakHashMap<>();
        Object weakKey = new Object();
        String weakVal = "String";
        weakMap.put(weakKey, weakVal);

        System.out.println(weakMap);
        System.out.println(weakMap.size());

        weakKey = null;
        weakVal = null;
        System.gc();

        System.out.println(weakMap);
        System.out.println(weakMap.size());

        System.out.println("---TreeMap---");
        // Особенности TreeMap:
        // 1. хранит элементы в отсортированном по ключам порядке
        // 2. основан на красно-черном дереве
        // 3. null нельзя использовать в качестве ключей
        // 4. класс, объекты которого будут использоваться в качестве
        // ключей должен реализовывать интерфейс Comparable
        // или в конструктор мапы необходимо передеть Comparator

        userHashMap.remove(null);

        TreeMap<String, User> userTreeMap = new TreeMap<>(userHashMap);
        userTreeMap.put("asd", asd);

    }
}