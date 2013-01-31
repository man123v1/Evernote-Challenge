import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class TopFourElements {
	private static Integer[] topFourElements = null;
	private static BufferedReader input = null;
	private static BufferedWriter output = null;
	
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new InputStreamReader(
					System.in));
		int inputSize = Integer.parseInt(input.readLine());
		topFourElements = new Integer[inputSize>4?4:inputSize];
		
		findTopFourElements(inputSize);
		input.close();
		
		output = new BufferedWriter(new OutputStreamWriter(
				System.out));
		
		Arrays.sort(topFourElements);
		int i = (inputSize>4?4:inputSize)-1;
		while (i >= 0) {
			if(topFourElements[i]!=null)
			{
				output.write(topFourElements[i]+"\n");
			}
			i--;
		}
		output.flush();
		output.close();
	}
	
	private static void findTopFourElements(int inputSize) throws IOException {
		int i=0;
		while (i<inputSize) {
			if(i< (inputSize>4?4:inputSize))
			{
				topFourElements[i]=Integer.parseInt(input
						.readLine());
			}
			else
			{
				compareWithTopFourElementsAndStore(Integer.parseInt(input
					.readLine()),inputSize);
			}
			i++;
		}
		input.close();
	}

	private static void compareWithTopFourElementsAndStore(int input,int inputSize) {
		Arrays.sort(topFourElements);
		int j=0;
		while(j<(inputSize>4?4:inputSize))
		{
			if(topFourElements[j]<input)
			{
				topFourElements[j]=input;
				break;
			}
			j++;
		}
	}
}
