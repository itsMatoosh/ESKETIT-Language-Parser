package me.matoosh.esketit;

import me.matoosh.esketit.rules.ParsingError;

/**
 * A rule for the parser.
 */
public abstract class ParserRule {
    /**
     * Gets the trigger phrase.
     * @return
     */
    public abstract String getTriggerPhrase();

    /**
     * Applies the rule on the line and returns the changed line.
     * @param line
     * @return
     */
    public abstract String applyRule(String line) throws ParsingError;
}
