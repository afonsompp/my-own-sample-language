package sample.language;

import antler.output.sampleBaseListener;
import antler.output.sampleParser;

import java.util.HashMap;
import java.util.Map;

public class SampleLanguageListener extends sampleBaseListener {
	
	private Map<String, Integer> variables = new HashMap<>();
	
	@Override public void exitAssign(sampleParser.AssignContext ctx) {
		
		String varName = ctx.ID(0).getText();
		
		String varValue = ctx.ID().size() > 1 ? ctx.ID(1).getText() : ctx.NUMBER().getText();
		
		if (ctx.ID().size() > 1)
			variables.put(varName, variables.get(varValue));
		else
			variables.put(varName, Integer.parseInt(varValue));
	}
	
	@Override public void exitAdd(sampleParser.AddContext ctx) {
		String variableName = ctx.ID().size() > 1 ? ctx.ID(1).getText() : ctx.ID(0).getText();
		
		int value = ctx.ID().size() > 1 ? variables.get(ctx.ID(0).getText())
							: Integer.parseInt(ctx.NUMBER().getText());
		
		variables.put(variableName, variables.get(variableName) + value);
	}
	
	@Override public void exitPrint(sampleParser.PrintContext ctx) {
		String output = ctx.ID() == null ? ctx.NUMBER().getText()
								: variables.get(ctx.ID().getText()).toString();
		System.out.println(output);
	}
}
