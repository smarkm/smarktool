package org.smark.tool;

import java.util.Arrays;

/**
 * st
 * @author smark
 *
 */
public class SmarkTool {

	public static void main(String[] args) {
		if (args==null||args.length<1) {
			System.out.println("SmarkTool.main()");
			return ;
		}
		String cmd = args[0] ;
		System.out.println(cmd);
		switch (cmd) {
		case "orb":
			NamingSearch.execute(Arrays.copyOfRange(args, 1, args.length));
			break;

		default:
			break;
		}
		
	}
}
