package com.vladkirov.lessons.examenation03;

/**
 * Abstract family "Steps"
 */
public abstract class Step implements IStep {
    /**
     * Key field for identity object
     */
    protected final String state;
    /**
     * Text of current step
     */
    protected final String text;
    /**
     * Link to next first step
     */
    protected IStep linkFirst;
    /**
     * Link to next second step
     */
    protected IStep linkSecond;

    public Step(String state, String text) {
        this.state = state;
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public String getText() {
        return text;
    }

    @Override
    public IStep setFirstLink(IStep step) {
        linkFirst = step;
        return this;
    }

    @Override
    public IStep setSecondLink(IStep step) {
        linkSecond = step;
        return this;
    }

    @Override
    public String handleRequest() {
        if (linkFirst != null || linkSecond != null) {
            int action = handlerAction();
            if (action == 1) return linkFirst.handleRequest();
            if (action == 2) return linkSecond.handleRequest();
        }
        return state;
    }

    /**
     * Action for each extended class
     * @return Number of next step (or 3 for Menu)
     */
    abstract public int handlerAction();
}
