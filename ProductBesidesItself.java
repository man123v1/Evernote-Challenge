import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ProductBesidesItself {
  private static long[] inputs = null;
	private static long[] outputs = null;

	public static void main(String[] args) throws Exception {
		readInputs();
		productBesidesItself();
		printOutput();
	}

	private static void printOutput() throws IOException {
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
				System.out));
		int i = 0;
		while (i < outputs.length) {
			output.write(outputs[i] + "\n");
			i++;
		}
		output.flush();
		output.close();
	}

	private static void productBesidesItself() {
		int p =1;
		for (int i = 0; i < inputs.length; i++) {
			outputs[i] = p;
			p *= inputs[i];
		}

		p=1;
		for(int i=inputs.length-1;i>=0;i--)
		{
		  outputs[i]*=p;
		  p*=inputs[i];
		}
	}

	private static void debugArray(long[] array) {
		System.out.println("---------");
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println("---------");
	}

	private static void readInputs() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		int bufferSize = Integer.parseInt(input.readLine());
		inputs = new long[bufferSize];
		outputs = new long[bufferSize];
		
		int i = 0;
		while (i < bufferSize) {
			inputs[i] = Long.parseLong(input.readLine());
			i++;
		}
		input.close();
	}
}
