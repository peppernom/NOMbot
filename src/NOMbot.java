import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class NOMbot {

	final static String server = "irc.uwcs.co.uk";
	final static String botName = "NOMbot";
	final static String initialChannel = "#pepper";
	
	// Create connection to server. Read any inputs.
	public static void main(String args[]) throws Exception {
	
		Socket socket = new Socket(server, 6667);
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		writer.write("NICK " + botName + "\r\n");
		writer.write("USER " + botName + " pepper.uwcs.co.uk irc.uwcs.co.uk : NOMbot\r\n");
		writer.write("JOIN " + initialChannel + "\r\n");
		writer.flush();

		String inputLine = null;
		
		// Read lines from the server until a message from Nickserv is received, then join the initial channel
		while((inputLine = reader.readLine()) != null) {
			System.out.println(inputLine);
			if (inputLine.startsWith(":NickServ!NickServ@services.warwickcompsoc.co.uk")) {
				writer.write("JOIN " + initialChannel + "\r\n");
				writer.flush();
			}
			break;
		}
		
		// Keep reading lines from the server
		while((inputLine = reader.readLine()) != null) {
			// do stuff	
		}
		
		// CLOSE ALL THE THINGS
		reader.close();
		writer.close();
		socket.close();
		
	}
}
