import java.util.Scanner; // Import Scanner class
import java.io.*;

public class MyShell {
	
	/* Simple bash shell
	 * This program will attempt to create an interactive
	 * shell with the OS
	 */

	public void shell() {

		System.out.println ("Creating Shell");
		String line;
		Scanner scan = new Scanner(System.in);
	
		try {
			//System.out.println ("Creating Process");
			ProcessBuilder builder = new ProcessBuilder ("/bin/bash");
			builder.redirectErrorStream(true);
			Process process = builder.start();
			//System.out.println ("Creating Streams");
			OutputStream stdin = process.getOutputStream ();
			InputStream stderr = process.getErrorStream ();
			InputStream stdout = process.getInputStream ();
		
			//System.out.println ("Creating Read and Write Buffers");
			BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
	
			//System.out.println ("Read through lines");

			try {
				System.out.println ("Start While Loop");

				while (scan.hasNext()) {
					//System.out.println ("HasNext Successfull");
    					String input = scan.nextLine();
					//System.out.println ("NextLine Successfull");
    					if (input.trim().equals("exit")) {
        					// Putting 'exit' amongst the echo --EOF--s below doesn't work.
						//System.out.println ("Received Exit Request");
        					writer.write("exit\n");
    					} else {
						//System.out.println ("Writing from Input Buffer");
        					writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
						//System.out.println ("Input: " + input);
    					}
    					writer.flush();
					//System.out.println ("Flushed Write Buffer");
					
    					line = reader.readLine();

					//System.out.println ("Read from Read Buffer");

    					while (line != null && ! line.trim().equals("--EOF--")) {
						//System.out.println ("Line is not NULL");
        					System.out.println ("Stdout: " + line);
        					line = reader.readLine();
    					}
    					if (line == null) {
        					break;
    					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String []args) {
		MyShell myShell = new MyShell();
		myShell.shell();
	}
}
