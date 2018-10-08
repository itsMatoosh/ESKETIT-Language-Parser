package me.matoosh.esketit.rules;

import me.matoosh.esketit.ParserRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the type of a variable. Needs to be followed with the variable name declaration.
 * Use the phrase "Only wear designer, {type}"
 * The language currently supports only 4 types, esketit(String), skrrt(int), ooh(double), yuh(boolean).
 */
public class VariableTypeRule extends ParserRule {

    /**
     * Types of variables and their java alternatives.
     */
    private static final Map<String, String> varTypes;
    static
    {
        varTypes = new HashMap<String, String>();
        varTypes.put("esketit", "String");
        varTypes.put("skrrt", "int");
        varTypes.put("ooh", "double");
        varTypes.put("yuh", "boolean");
    }

    @Override
    public String getTriggerPhrase() {
        return "Only wear designer,";
    }

    @Override
    public String applyRule(String line) throws ParsingError {
        String[] args = line.split(",");
        if(args == null || args.length != 2) {
            throw new ParsingError("Use the phrase \"Only wear designer, {type}\"");
        }
        String varType = line.split(",")[1].substring(1);

        String javaType = varTypes.get(varType);
        if(javaType == null) {
            throw new ParsingError("Java type not specified...");
        }
        return javaType + " ";
    }
}
