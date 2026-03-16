package ExcelDataDriven;

import java.io.IOException;
import java.util.List;

public class testSample {

	public static void main(String[] args) throws IOException {
		dataDriven d = new dataDriven();

		List<String> data = d.getData("testcases","Add Profile");

		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));

	}

}
