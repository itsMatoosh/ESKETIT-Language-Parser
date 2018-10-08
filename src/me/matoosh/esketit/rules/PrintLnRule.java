package me.matoosh.esketit.rules;

import me.matoosh.esketit.ParserRule;

/**
 * Prints value of a variable.
 * Usage: Smashin' on your bitch, {variable-name}
 */
public class PrintLnRule extends ParserRule {
    @Override
    public String getTriggerPhrase() {
        return "Smashin' on your bitch,";
    }

    @Override
    public String applyRule(String line) throws ParsingError {
        String[] args = line.split(",");
        if(args == null || args.length != 2) {
            throw new ParsingError("Usage: Smashin' on your bitch, {variable-name}");
        }

        String varName = args[1].substring(1);
        return "System.out.println(\"\" + " + varName + ");";
    }
}
