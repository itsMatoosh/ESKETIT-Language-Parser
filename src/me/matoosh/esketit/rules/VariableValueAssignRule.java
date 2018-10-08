package me.matoosh.esketit.rules;

import me.matoosh.esketit.ParserRule;

/**
 * Assigns value to a already declared variable.
 * Use the phrase "Yeah, yeah, {value}, {variable} if value is String
 * Use the phrase "Yeah, ooh, {value}, {variable} if value is other type
 * Use the phrase "Yeah, ouu, wow" as a filler.
 */
public class VariableValueAssignRule extends ParserRule {
    @Override
    public String getTriggerPhrase() {
        return "Yeah,";
    }

    @Override
    public String applyRule(String line) throws ParsingError {
        String[] args = line.split(",");
        if(args == null) {
            throw new ParsingError("Use the phrase \"Yeah, yeah, {value}, {variable} if value is String\n" +
                    " * Use the phrase \"Yeah, ooh, {value}, {variable} if value is other type\n" +
                    " * Use the phrase \"Yeah, ouu, wow\" as a filler.");
        } else if(args.length != 4) {
            return ""; //Filler line. Used for aesthetic purposes only.
        }

        String varName = args[3].substring(1);
        String value = args[2].substring(1);

        String statement = varName + " = ";
        //is string?
        if(args[1].substring(1).equals("yeah")) {
            //string
            statement = statement + "\"" + value + "\"";
        } else {
            //other type
            statement = statement + value;
        }
        return statement + ";";
    }
}
