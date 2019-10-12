package FakePlayers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Player1 {
	private BufferedReader reader;

	private int maxRound;

	private String name = "";

	private ClientSideConnection csc;

	private int playerID;

	public Player1() {

		reader = new BufferedReader(new InputStreamReader(System.in));

	}

	public static void main(String[] args) {

		Player1 app = new Player1();

		try {

			app.name = args[0];

		} catch (Exception e) {

			System.out.println("Erroe in Main");
		}

		app.connectToServer();

	}

	private void connectToServer() {

		csc = new ClientSideConnection();
	}

	private class ClientSideConnection {

		private Socket socket;

		private DataInputStream dataIn;

		private DataOutputStream dataOut;

		public ClientSideConnection() {

			try {

				socket = new Socket("localhost", 51731);

				dataIn = new DataInputStream(socket.getInputStream());

				dataOut = new DataOutputStream(socket.getOutputStream());

				playerID = dataIn.readInt();

				sendToServer(name);

			} catch (IOException ex) {

				System.out.println("IO Exception from CSC construction");
			}
		}

		public void sendToServer(String res) {

			try {

				dataOut.writeUTF(res);

				dataOut.flush();

			} catch (IOException e) {

				System.out.println("IOException from sendToServer() CSC");
			}
		}

	}
}
