package com.vladkirov.lessons.examenation03;

/**
 * Pattern Chain of Responsibility
 */
public interface IStep {
    /**
     * Get state of current step
     * @return State as String
     */
    String getState();

    /**
     * Set link to first next objects in net-chain
     * @param step Next IStep objet
     * @return Current IStep object for simple creating bundles
     */
    IStep setFirstLink(IStep step);

    /**
     * Set link to second next objects in net-chain
     * @param step Next IStep objet
     * @return Current IStep object for simple creating bundles
     */
    IStep setSecondLink(IStep step);

    /**
     * Run request in next step
     * @return current state
     */
    String handleRequest();
}
