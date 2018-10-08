package me.matoosh.esketit.rules;

import me.matoosh.esketit.ParserRule;

/**
 * Defines the name of a variable. Needs to follow the variable type declaration.
 * Use the phrase "Ouu, {name}"
 */
public class VariableNameRule extends ParserRule {
    @Override
    public String getTriggerPhrase() {
        return "Ouu,";
    }

    @Override
    public String applyRule(String line) throws ParsingError {
        String[] args = line.split(",");
        if(args == null || args.length != 2) {
            throw new ParsingError("Use the phrase \"Ouu, {name}\"");
        }
        String varName = line.split(",")[1].substring(1);
        return varName + ";";
    }
}
