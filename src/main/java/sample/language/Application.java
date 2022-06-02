package sample.language;

import antler.output.sampleLexer;
import antler.output.sampleParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;

public class Application {
	
	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("src/sampleScript/script.sample");
			ANTLRInputStream input = new ANTLRInputStream(inputStream);
			
			sampleLexer sampleLexer = new sampleLexer(input);
			
			sampleParser parser = new sampleParser(new CommonTokenStream(sampleLexer));
			parser.addParseListener(new SampleLanguageListener());
			
			parser.program();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
