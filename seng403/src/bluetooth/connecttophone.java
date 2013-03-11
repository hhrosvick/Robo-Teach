package bluetooth;

import java.io.*;

import javax.microedition.io.*;
import javax.obex.*;

public class connecttophone {
	
	
	
	public static void main(String[] args) throws IOException{
	
		File f = new File("C:\\Users\\Henry\\Documents\\School\\SENG 403\\Project\\seng403\\Lessons\\Lesson I.png");
		
		FileInputStream fileStream = new FileInputStream(f);
		
		int size = (int)f.length();
		byte[] file = new byte[size];
		fileStream.read(file);
		
		
		String url = "btgoep://60A10A967361:3;authenticate=false;encrypt=false;master=false";
		
		Connection phone = Connector.open(url);
		
		ClientSession cs = (ClientSession)phone;
		HeaderSet hs = cs.createHeaderSet();
		
		cs.connect(hs);
		hs.setHeader(HeaderSet.NAME, f.getName());

		hs.setHeader(HeaderSet.LENGTH, new Long(file.length));
		
		Operation putOperation = cs.put(hs);
		OutputStream output = putOperation.openOutputStream();
		
		output.write(file);
		
		output.close();
		putOperation.close();
		
		fileStream.close();
		
		cs.disconnect(null);
		
		phone.close();
	}
	
}
