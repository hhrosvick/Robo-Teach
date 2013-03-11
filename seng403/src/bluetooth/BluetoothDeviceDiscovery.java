package bluetooth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
 
import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;

import com.ibm.oti.connection.btgoep.Connection;
 
 
/**
* Class that discovers all bluetooth devices in the neighbourhood
* and displays their name and bluetooth address.
*/
public class BluetoothDeviceDiscovery implements DiscoveryListener{
   
   
    //object used for waiting
    private static Object lock=new Object();
   
    //vector containing the devices discovered
    private static Vector vecDevices=new Vector();
    
    private static Vector vecServices = new Vector();
   
   
    //main method of the application
    public static void main(String[] args) throws IOException {
       
        //create an instance of this class
        BluetoothDeviceDiscovery bluetoothDeviceDiscovery=new BluetoothDeviceDiscovery();
       
        //display local device address and name
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());
       
        //find devices
        DiscoveryAgent agent = localDevice.getDiscoveryAgent();
      
        System.out.println("Starting device inquiry...");
        agent.startInquiry(DiscoveryAgent.GIAC, bluetoothDeviceDiscovery);
       
        try {
            synchronized(lock){
                lock.wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
       
       
        System.out.println("Device Inquiry Completed. ");
       
        //print all devices in vecDevices
        int deviceCount=vecDevices.size();
       
        
        
        if(deviceCount <= 0){
            System.out.println("No Devices Found .");
        }
        else{
            //print bluetooth device addresses and names in the format [ No. address (name) ]
            System.out.println("Bluetooth Devices: ");
            for (int i = 0; i <deviceCount; i++) {
                RemoteDevice remoteDevice=(RemoteDevice)vecDevices.elementAt(i);
                System.out.println((i+1)+". "+remoteDevice.getBluetoothAddress()+" ("+remoteDevice.getFriendlyName(true)+")");
            
                
            }
        }
       
       System.out.println("Custom phone connector here.");
        
       RemoteDevice phone = (RemoteDevice)vecDevices.elementAt(0);
       
       if(phone.getBluetoothAddress().compareTo("60A10A967361") == 0)
       {
    	   UUID[] uuidSet = new UUID[1];
    	   uuidSet[0]=new UUID(0x1105); //OBEX Object Push service

    	  int[] attrIDs =  new int[] {
    	          0x0100 // Service name
    	  };

    	  agent.searchServices(null,uuidSet,phone, bluetoothDeviceDiscovery);
    	      
	      
	      try {
	          synchronized(lock){
	              lock.wait();
	          }
	      }
	      catch (InterruptedException e) {
	          e.printStackTrace();
	          return;
	      }
	      
	      javax.microedition.io.Connection phoneCon = Connector.open((String)vecServices.elementAt(0));
	      
	      
	      
	      
       }
        
    }//end main
 
    //methods of DiscoveryListener
   
    /**
     * This call back method will be called for each discovered bluetooth devices.
     */
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        System.out.println("Device discovered: "+btDevice.getBluetoothAddress());
        //add the device to the vector
        if(!vecDevices.contains(btDevice)){
            vecDevices.addElement(btDevice);
        }
    }
 
    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
   public void servicesDiscovered(int arg0, ServiceRecord[] services) {
        for (int i = 0; i < services.length; i++) {
            String url = services[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            
            if (url == null) {
                continue;
            }
            
            DataElement serviceName = services[i].getAttributeValue(0x0100);
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);
            } else {
                System.out.println("service found " + url);
            }

             ////  if(serviceName.getValue().equals("OBEX Object Push")){
             //           sendMessageToDevice(url);                
             //       }
            
            vecServices.add(url);
        }
        
    }

    private static void sendMessageToDevice(String serverURL){
        try{
            System.out.println("Connecting to " + serverURL);
    
            ClientSession clientSession = (ClientSession) Connector.open(serverURL);
            HeaderSet hsConnectReply = clientSession.connect(null);
            if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("Failed to connect");
                return;
            }
    
            HeaderSet hsOperation = clientSession.createHeaderSet();
            hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
            hsOperation.setHeader(HeaderSet.TYPE, "text");
    
            //Create PUT Operation
            Operation putOperation = clientSession.put(hsOperation);
    
            // Sending the message
            byte data[] = "Hello World !!!".getBytes("iso-8859-1");
            OutputStream os = putOperation.openOutputStream();
            os.write(data);
            os.close();
    
            putOperation.close();
            clientSession.disconnect(null);
            clientSession.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    /**
     * This callback method will be called when the device discovery is
     * completed.
     */
    public void inquiryCompleted(int discType) {
        synchronized(lock){
            lock.notify();
        }
       
        switch (discType) {
            case DiscoveryListener.INQUIRY_COMPLETED :
                System.out.println("INQUIRY_COMPLETED");
                break;
               
            case DiscoveryListener.INQUIRY_TERMINATED :
                System.out.println("INQUIRY_TERMINATED");
                break;
               
            case DiscoveryListener.INQUIRY_ERROR :
                System.out.println("INQUIRY_ERROR");
                break;
 
            default :
                System.out.println("Unknown Response Code");
                break;
        }
    }//end method
}//end class

