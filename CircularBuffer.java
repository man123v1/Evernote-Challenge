import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class CircularBuffer {

	private String[] buffer;
	private int head;
	private int tail;
	private BufferedReader input = null;
	private BufferedWriter output = null;

	public static void main(String args[]) throws Exception {
		new CircularBuffer().run();
	}
	
	private void initialize() {
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new BufferedWriter(new OutputStreamWriter(System.out));
		head = 0;
		tail = 0;
	}

	void run() throws IOException {
		initialize();
		try {
			String bufferSize = input.readLine();
			buffer = new String[Integer.parseInt(bufferSize)];

			while (true) {
				String command = input.readLine();
				if (command.startsWith("A")) {
					append(command);
				} else if (command.startsWith("R")) {
					remove(command);
				} else if (command.startsWith("L")) {
					list(command);
				} else if (command.startsWith("Q")) {
					return;
				}
				//debug();
			}
		} finally {
			input.close();
		}

	}

	private void debug() throws IOException {
		for (int i = 0; i < buffer.length; i++) {
			output.write(buffer[i] + " - ");
		}
		output.write("\n");
		output.write("head->" + head + "," + "tail->" + tail);
		output.write("\n");
		output.flush();
	}

	private void list(String command) throws IOException {
		if (head == tail && buffer[tail] != null) {
			// Exactly one element in buffer
			output.write(buffer[tail] + "\n");
			output.flush();
		} else if (head == tail && buffer[tail] == null) {
			// No Elements - do nothing

		} else if (head < tail) {
			for (int i = head; i <= tail; i++) {
				output.write(buffer[i] + "\n");
				output.flush();
			}
		} else if (tail < head) {
			for (int i = head; i < buffer.length; i++) {
				output.write(buffer[i] + "\n");
				output.flush();
			}
			for (int i = 0; i <= tail; i++) {
				output.write(buffer[i] + "\n");
				output.flush();
			}
		}
	}

	private void remove(String command) {
		String[] params = command.split(" ");
		int noOfElementsToRemove = Integer.parseInt(params[1]);
		while (noOfElementsToRemove > 0) {
			if (head == tail && buffer[head] != null) {
				// This is when only one element in buffer
				buffer[head] = null;
				return;
			} else if (head == tail && buffer[head] == null) {
				// Do nothing as buffer is empty
				return;
			} else {
				buffer[head] = null;
				head = (head + 1) % buffer.length;
				noOfElementsToRemove--;
			}
		}
	}

	private void append(String command) throws IOException {

		String[] params = command.split(" ");
		int noOfElementsToAppend = Integer.parseInt(params[1]);
		while (noOfElementsToAppend > 0) {
			if (head == tail && buffer[tail] == null) {
				// when buffer is empty
				buffer[tail] = input.readLine();
				noOfElementsToAppend--;
				continue;
			}
			tail = (tail + 1) % buffer.length;
			buffer[tail] = input.readLine();

			if (tail == head) {
				// when buffer is full
				head = (head + 1) % buffer.length;
			}
			noOfElementsToAppend--;
		}
	}
}