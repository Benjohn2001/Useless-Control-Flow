package detectors;

import java.util.ArrayList;

import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * @author benjohnston
 * @number 2432411j
 * @description Overridden visit methods for different types of control flow
 *              used to detect useless control flow
 *
 */
public class UselessControlFlowDetector extends VoidVisitorAdapter<ArrayList<String>> {

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
	public void visit(IfStmt n, ArrayList<String> arg) {
		if (n.hasElseBranch()) {
			Optional<Statement> e = n.getElseStmt();
			java.util.List<Node> eStmts = e.get().getChildNodes();
			Statement t = n.getThenStmt();
			NodeList<Statement> tStmts = t.asBlockStmt().getStatements();
			if (tStmts.size() == 0) {
				int beginLine = t.getRange().get().begin.line;
				int endLine = t.getRange().get().end.line;
				String out = className + "," + methodName + "," + beginLine + "," + endLine;
				arg.add(out);
			}
			if (eStmts.size() == 0) {
				int beginLine = e.get().asBlockStmt().getRange().get().begin.line;
				int endLine = e.get().asBlockStmt().getRange().get().end.line;
				String out = className + "," + methodName + "," + beginLine + "," + endLine;
				arg.add(out);
			}
			super.visit(n, arg);

		} else {
			Statement t = n.getThenStmt();
			NodeList<Statement> tStmts = t.asBlockStmt().getStatements();
			if (tStmts.size() == 0) {
				int beginLine = t.getRange().get().begin.line;
				int endLine = t.getRange().get().end.line;
				String out = className + "," + methodName + "," + beginLine + "," + endLine;
				arg.add(out);
			}
			super.visit(n, arg);

		}

	}

	@Override
	public void visit(ForStmt n, ArrayList<String> arg) {
		super.visit(n, arg);
		if (n.getBody().asBlockStmt().getStatements().size() == 0) {
			int beginLine = n.getRange().get().begin.line;
			int endLine = n.getRange().get().end.line;
			String out = className + "," + methodName + "," + beginLine + "," + endLine;
			arg.add(out);
		}
	}

	@Override
	public void visit(WhileStmt n, ArrayList<String> arg) {
		super.visit(n, arg);
		if (n.getBody().asBlockStmt().getStatements().size() == 0) {
			int beginLine = n.getRange().get().begin.line;
			int endLine = n.getRange().get().end.line;
			String out = className + "," + methodName + "," + beginLine + "," + endLine;
			arg.add(out);
		}
	}

	@Override
	public void visit(SwitchStmt n, ArrayList<String> arg) {
		super.visit(n, arg);
		if (n.getEntries().size() == 0) {
			int beginLine = n.getRange().get().begin.line;
			int endLine = n.getRange().get().end.line;
			String out = className + "," + methodName + "," + beginLine + "," + endLine;
			arg.add(out);
		} else {
			for (SwitchEntry temp : n.getEntries()) {
				if (temp.getStatements().size() == 0) {
					int beginLine = temp.getRange().get().begin.line;
					int endLine = temp.getRange().get().end.line;
					String out = className + "," + methodName + "," + beginLine + "," + endLine;
					arg.add(out);
				}
			}
		}
		super.visit(n, arg);
	}

	@Override
	public void visit(DoStmt n, ArrayList<String> arg) {
		super.visit(n, arg);
		if (n.getBody().asBlockStmt().getStatements().size() == 0) {
			int beginLine = n.getRange().get().begin.line;
			int endLine = n.getRange().get().end.line;
			String out = className + "," + methodName + "," + beginLine + "," + endLine;
			arg.add(out);
		}
	}

}
