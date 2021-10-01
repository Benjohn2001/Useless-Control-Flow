package detectors;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.MethodCallExpr;

/**
 * @author benjohnston
 * @number 24324iij
 * @description Detector to find recursive calls by overriding the
 *              MethodCallExpr visit method and finding the expressions that
 *              match the methodName
 *
 */
public class RecursionDetector extends VoidVisitorAdapter<ArrayList<String>> {

//	Class and method name static so that in respective visit methods
//	the strings can be updated and accessed
	public static String methodName;
	public static String className;

	public void visit(MethodDeclaration md, ArrayList<String> arg) {
		methodName = md.getName().toString();
		super.visit(md, arg);
	}

	public void visit(ClassOrInterfaceDeclaration cd, ArrayList<String> arg) {
		className = cd.getName().toString();
		super.visit(cd, arg);
	}

	@Override
	public void visit(MethodCallExpr mx, ArrayList<String> arg) {
		if ((mx.getName().toString()).equals(methodName)) {
			int beginLine = mx.getRange().get().begin.line;
			int endLine = mx.getRange().get().end.line;
			String out = className + "," + methodName + "," + beginLine + "," + endLine;
			arg.add(out);
		}
		super.visit(mx, arg);
	}
}
