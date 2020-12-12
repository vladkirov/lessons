package com.vladkirov.lessons.examenation03;

import java.util.ArrayList;

/**
 * Factory steps
 */
public class Chain {
    public static final String START_STATE = "Начало";
    /**
     * Get all steps connected by links to next entities by scenary
     * @param state state for find step for resume game
     * @return Start step for new game and actual step for resume game
     */
    public static IStep factoryChainSteps(String state) {
        ArrayList<IStep> steps = new ArrayList<>();

        IStep start = new StepQuestion("Начало", "Каждое утро Лисёнок просыпался, завтракал и шёл увидеться с Бельчонком. Это утро не было исключением. Лисёнок пришёл на их обычное место встречи, но Бельчонка там не было. Лисёнок ждал, ждал, но так и не смог дождаться своего друга. \"Бельчонок не пропустил еще ни одной встречи, вдруг он попал в беду.\" - подумал Лисёнок. Как поступить Лисенку?");
        IStep comeBackHome = new StepQuestion("Вернуться домой", "Вернувшись домой, Лисёнок нашёл там Бельчонка. Оказалось, что Бельчонок пришёл на место встречи раньше и увидел там рой злобных пчел. Он поспешил предупредить об этом Лисёнка, но они разминулись. Наконец-то друзья нашли друг друга! Игра завершилась успехом.");
        IStep goInSearch = new StepQuestion("Отправиться на поиски", "Все лесные жители были заняты своими делами и не обращали внимания на Лисёнка и его проблему. Но вдруг кто нибудь видел Бельчонка... Лисёнок не знал, что ему делать. Помогите ему.");
        IStep tryFindFromAnimals = new StepQuestion("Попытаться разузнать о Бельчонке у лесных жителей", "Пока Лисёнок принимал решение, лесные жители разошлись кто куда. Остались только Сова и Волк. Но у Совы бывают проблемы с памятью, а Волк может сильно разозлиться из-за расспросов. Кого выбрать?");
        IStep searchAlone = new StepQuestion("Искать Бельчонка в одиночку", "Лисёнок слишком долго плутал по лесу в поисках друга и сам не заметил, как заблудился. Теперь его самого нужно искать. Игра завершилась неудачей");
        IStep askOwl = new StepQuestion("Расспросить Сову", "Сова долго не хотела говорить, но в итоге сказала, что видела испуганного Бельчонка, бежавшего в глубь леса. Все лесные жители знают, что в глубине леса опасно, если Бельчонок там, ему срочно нужна помощь.");
        IStep askWolf = new StepQuestion("Расспросить Волка", "Волк оказался вполне дружелюбным, но помочь не смог. Лишь сказал, что маленькому лисенку не стоит бродить по лесу одному. И как теперь поступить?");
        IStep believeOwlAndGoDeepForest = new StepQuestion("Поверить Сове и отправиться в глубь леса", "В глубине леса Лисёнок встретил Медвежонка. Ленивый Медвежонок был готов рассказать все, что знает, если Лисёнок принесёт ему мёда.");
        IStep useChanceAndGetHoney = new StepQuestion("Нужно воспользоваться шансом и раздобыть мёд", "Лисёнок быстро нашёл мёд, но вокруг летал рой злобных пчел. Лисёнок всегда боялся пчёл, но и не найти друга он тоже боялся.");
        IStep waitForBeesFlyAway = new StepQuestion("Подождать, вдруг пчёлы улетят", "Лисёнок подождал немного, и пчёлы разлетелись. Лисёнок без проблем набрал мёда. Вдруг он понял, что очень голоден. Что же делать?");
        IStep tryStoleHoneyNow = new StepQuestion("Нужно попытаться выкрасть мёд немедленно", "Это была не лучшая идея. Пчёлы покусали Лисёнка, теперь ему самому нужна помощь. Игра закончилась неудачей");
        IStep eatAndRest = new StepQuestion("Поесть немного и передохнуть", "Пока Лисёнок ел, злобные пчёлы вернулись и покусали его. Лисёнку нужна помощь, он не сможет продолжить поиски. Игра завершилась неудачей");
        IStep takeHoneyToBearNow = new StepQuestion("Скорее отнести мёд Медвежонку", "Довольный Медвежонок рассказал Лисёнку, что очень хорошо знает семью Белок и уверен, что Бельчонок никогда не пошёл бы в глубь леса. Он заверял Лисёнка, что Белки не попадают в неприятности, и что Совам нельзя верить, он также уговаривал Лисёнка вернуться домой.");

        start.setFirstLink(comeBackHome).setSecondLink(goInSearch);
        goInSearch.setFirstLink(tryFindFromAnimals).setSecondLink(searchAlone);
        tryFindFromAnimals.setFirstLink(askOwl).setSecondLink(askWolf);
        askOwl.setFirstLink(believeOwlAndGoDeepForest).setSecondLink(searchAlone);
        askWolf.setFirstLink(comeBackHome).setSecondLink(searchAlone);
        believeOwlAndGoDeepForest.setFirstLink(searchAlone).setSecondLink(useChanceAndGetHoney);
        useChanceAndGetHoney.setFirstLink(waitForBeesFlyAway).setSecondLink(tryStoleHoneyNow);
        waitForBeesFlyAway.setFirstLink(eatAndRest).setSecondLink(takeHoneyToBearNow);
        takeHoneyToBearNow.setFirstLink(searchAlone).setSecondLink(comeBackHome);

        steps.add(start);
        steps.add(comeBackHome);
        steps.add(goInSearch);
        steps.add(tryFindFromAnimals);
        steps.add(searchAlone);
        steps.add(askOwl);
        steps.add(askWolf);
        steps.add(believeOwlAndGoDeepForest);
        steps.add(useChanceAndGetHoney);
        steps.add(waitForBeesFlyAway);
        steps.add(tryStoleHoneyNow);
        steps.add(eatAndRest);
        steps.add(takeHoneyToBearNow);

        for (IStep step : steps)
            if (step.getState().equals(state)) return step;

        return null;
    }
}
