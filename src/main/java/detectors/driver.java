
package detectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import com.github.javaparser.ast.visitor.VoidVisitor;

/**
 * @author benjohnston
 * @Number 2432411j
 * @Description Driver class to initialise detectors and and call Overridden
 *              toString on breakpoints elements
 */
public class driver {

	public static void main(String[] args) throws FileNotFoundException {
		String fp = args[0];
		File sourceFile = new File(fp);
//		Creating compilation units and detectors then calling visit 
//		on each detector with the breakpoints ArrayList and compilation unit
		CompilationUnit cU = JavaParser.parse(sourceFile);
		VoidVisitor<ArrayList<String>> UselessControlFlow = new UselessControlFlowDetector();
		ArrayList<String> bp = new Breakpoints().Breakpoints;
		UselessControlFlow.visit(cU, bp);
		CompilationUnit cURec = JavaParser.parse(sourceFile);
		VoidVisitor<ArrayList<String>> RecursionDetector = new RecursionDetector();
		ArrayList<String> bpRec = new Breakpoints().Breakpoints;
		RecursionDetector.visit(cURec, bpRec);
//		Output of both detectors
		System.out.println("\nUseless Control Flows:");
		for (String x : bp) {
			Breakpoints.toString(x);
		}
		System.out.println("\nRecursions:");
		for (String y : bpRec) {
			Breakpoints.toString(y);
		}
	}
}
