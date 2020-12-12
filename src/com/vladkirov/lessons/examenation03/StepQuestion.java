package com.vladkirov.lessons.examenation03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation question steps
 */
public class StepQuestion extends Step {
    public StepQuestion(String state, String text) {
        super(state, text);
    }

    @Override
    public int handlerAction() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n" + getText());

            System.out.println("===== Actions: =====");
            System.out.println("1) - " + linkFirst.getState());
            System.out.println("2) - " + linkSecond.getState());
            System.out.println("3) - Menu");
            System.out.println("Input action number: ");

            int actionNumber = Integer.parseInt(reader.readLine());
            if (actionNumber == 1 || actionNumber == 2) return actionNumber;
            return 3;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 3;
    }
}
