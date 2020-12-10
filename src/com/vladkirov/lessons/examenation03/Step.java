package com.vladkirov.lessons.examenation03;

import java.util.HashMap;
import java.util.Map;

public class Step {
    private final String text;
    private final String linkFirst;
    private final String linkSecond;
    public static final Map<String, Step> STEPS = new HashMap<>();
    public static final String START_STATE = "Начало";
    public static final String FINISH_STATE = "Конец";
    public static final String GAME_OVER_STATE = "Игра окончена";

    public Step(String text, String linkFirst, String linkSecond) {
        this.text = text;
        this.linkFirst = linkFirst;
        this.linkSecond = linkSecond;
    }

    public String getText() {
        return text;
    }

    public String getLinkFirst() {
        return linkFirst;
    }

    public String getLinkSecond() {
        return linkSecond;
    }

    static {
        STEPS.put(START_STATE, new Step("Каждое утро Лисёнок просыпался, завтракал и шёл увидеться с Бельчонком. Это утро не было исключением. Лисёнок пришёл на их обычное место встречи, но Бельчонка там не было. Лисёнок ждал, ждал, но так и не смог дождаться своего друга. \"Бельчонок не пропустил еще ни одной встречи, вдруг он попал в беду.\" - подумал Лисёнок. Как поступить Лисенку?",
                "Вернуться домой", "Отправиться на поиски"));
        STEPS.put("Вернуться домой", new Step("Вернувшись домой, Лисёнок нашёл там Бельчонка. Оказалось, что Бельчонок пришёл на место встречи раньше и увидел там рой злобных пчел. Он поспешил предупредить об этом Лисёнка, но они разминулись. Наконец-то друзья нашли друг друга! Игра завершилась успехом.",
                FINISH_STATE, FINISH_STATE));
        STEPS.put("Отправиться на поиски", new Step("Все лесные жители были заняты своими делами и не обращали внимания на Лисёнка и его проблему. Но вдруг кто нибудь видел Бельчонка... Лисёнок не знал, что ему делать. Помогите ему.",
                "Попытаться разузнать о Бельчонке у лесных жителей", "Искать Бельчонка в одиночку"));
        STEPS.put("Попытаться разузнать о Бельчонке у лесных жителей", new Step("Пока Лисёнок принимал решение, лесные жители разошлись кто куда. Остались только Сова и Волк. Но у Совы бывают проблемы с памятью, а Волк может сильно разозлиться из-за расспросов. Кого выбрать?",
                "Расспросить Сову", "Расспросить Волка"));
        STEPS.put("Искать Бельчонка в одиночку", new Step("Лисёнок слишком долго плутал по лесу в поисках друга и сам не заметил, как заблудился. Теперь его самого нужно искать. Игра завершилась неудачей",
                GAME_OVER_STATE, GAME_OVER_STATE));
        STEPS.put("Расспросить Сову", new Step("Сова долго не хотела говорить, но в итоге сказала, что видела испуганного Бельчонка, бежавшего в глубь леса. Все лесные жители знают, что в глубине леса опасно, если Бельчонок там, ему срочно нужна помощь.",
                "Поверить Сове и отправиться в глубь леса", "Искать Бельчонка в одиночку"));
        STEPS.put("Расспросить Волка", new Step("Волк оказался вполне дружелюбным, но помочь не смог. Лишь сказал, что маленькому лисенку не стоит бродить по лесу одному. И как теперь поступить?",
                "Вернуться домой", "Искать Бельчонка в одиночку"));
        STEPS.put("Поверить Сове и отправиться в глубь леса", new Step("В глубине леса Лисёнок встретил Медвежонка. Ленивый Медвежонок был готов рассказать все, что знает, если Лисёнок принесёт ему мёда.",
                "Искать Бельчонка в одиночку", "Нужно воспользоваться шансом и раздобыть мёд"));
        STEPS.put("Нужно воспользоваться шансом и раздобыть мёд", new Step("Лисёнок быстро нашёл мёд, но вокруг летал рой злобных пчел. Лисёнок всегда боялся пчёл, но и не найти друга он тоже боялся.",
                "Подождать, вдруг пчёлы улетят", "Нужно попытаться выкрасть мёд немедленно"));
        STEPS.put("Подождать, вдруг пчёлы улетят", new Step("Лисёнок подождал немного, и пчёлы разлетелись. Лисёнок без проблем набрал мёда. Вдруг он понял, что очень голоден. Что же делать?",
                "Поесть немного и передохнуть", "Скорее отнести мёд Медвежонку"));
        STEPS.put("Нужно попытаться выкрасть мёд немедленно", new Step("Это была не лучшая идея. Пчёлы покусали Лисёнка, теперь ему самому нужна помощь. Игра закончилась неудачей",
                GAME_OVER_STATE, GAME_OVER_STATE));
        STEPS.put("Поесть немного и передохнуть", new Step("Пока Лисёнок ел, злобные пчёлы вернулись и покусали его. Лисёнку нужна помощь, он не сможет продолжить поиски. Игра завершилась неудачей",
                GAME_OVER_STATE, GAME_OVER_STATE));
        STEPS.put("Скорее отнести мёд Медвежонку", new Step("Довольный Медвежонок рассказал Лисёнку, что очень хорошо знает семью Белок и уверен, что Бельчонок никогда не пошёл бы в глубь леса. Он заверял Лисёнка, что Белки не попадают в неприятности, и что Совам нельзя верить, он также уговаривал Лисёнка вернуться домой.",
                "Искать Бельчонка в одиночку", "Вернуться домой"));
    }
}
