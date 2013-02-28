package api;

import java.io.IOException;

import javax.microedition.io.Connection;
import javax.microedition.io.Connector;

public class connecttophone {

	
	public static void main(String[] args) throws IOException{
	
		String url = "btgoep://60A10A967361:3;authenticate=false;encrypt=false;master=false";
		
		Connection phoneCon = Connector.open(url);
	     
	}
	
}
