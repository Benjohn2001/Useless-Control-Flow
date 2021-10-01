package detectors;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author benjohnston
 * @number 2432411j
 * @description initialises breakpoints ArrayList to store detected code and
 *              contains the toString method to give desired output
 *
 */
public class Breakpoints {
	ArrayList<String> Breakpoints;

	public Breakpoints() {
		Breakpoints = new ArrayList<String>();

	}

	public static void toString(String x) {
		ArrayList<String> splitList = new ArrayList<String>(Arrays.asList(x.split(",")));
		String cN = splitList.get(0);
		String mN = splitList.get(1);
		String sL = splitList.get(2);
		String eL = splitList.get(3);
		System.out.print("className=" + cN + ",methodName=" + mN + ",startLine=" + sL + ",endLine=" + eL + "\n");
	}

}
