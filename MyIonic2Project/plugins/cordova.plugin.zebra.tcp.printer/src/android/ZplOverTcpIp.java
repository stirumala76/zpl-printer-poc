package cordova.plugin.zebra.tcp.printer;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;


/**
 * This class echoes a string called from JavaScript.
 */
public  final class ZplOverTcpIp extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
       
    	if (action.equals("print")) {
    		
            String ipaddress = args.getString(0);
            ArrayList<String> bcLables =this.json2ArrayList(args.getJSONArray(1));
            
            System.out.println("ipaddress "+ipaddress);
            System.out.println(" bclablels "+bcLables);
            
            if((ipaddress.trim() != null && ipaddress.trim().length() > 0)){
	            this.print(ipaddress, bcLables,callbackContext);
	            return true;
            } 
            
        }
        return false;
    }

    private void print(String ipaddress, ArrayList<String> bcLables ,CallbackContext callbackContext) {
        if ((ipaddress.trim() != null && ipaddress.trim().length() > 0) && !bcLables.isEmpty()) {
        	try {
				this.sendZplOverTcp(ipaddress, bcLables);
				if(callbackContext!=null){
					callbackContext.success("Print Success");
				}
			} catch (ConnectionException e) {
				e.printStackTrace();
			}
        } else {
        	if(callbackContext!=null){
        		callbackContext.error("Expected one non-empty string argument.");
        	}
        }
    }
    
    
    
    private  void sendZplOverTcp(String ipaddress, List<String> bcLabels) throws ConnectionException {
        // Instantiate connection for ZPL TCP port at given address
        Connection thePrinterConn = new TcpConnection(ipaddress, TcpConnection.DEFAULT_ZPL_TCP_PORT);

        try {
            // Open the connection - physical connection is established here.
            thePrinterConn.open();
            // Send the data to printer as a byte array.
            for (String bcLabel : bcLabels) {
            	System.out.println(bcLabel);
            	thePrinterConn.write(bcLabel.getBytes());
            }
            
        } catch (ConnectionException e) {
            // Handle communications error here.
             e.printStackTrace();
        } finally {
            // Close the connection to release resources.
            thePrinterConn.close();
        }
    }
    
    private ArrayList<String> json2ArrayList(JSONArray jArray){
    	
    	ArrayList<String> listdata = new ArrayList<String>();     
        try {
	    	if (jArray != null) { 
	    		for (int i=0;i<jArray.length();i++){ 
					listdata.add(jArray.get(i).toString());
	    	   } 
	    	}
    	} catch (JSONException e) {
				e.printStackTrace();
    	}
    	return listdata;
    	
    }
    
  
    public static void main(String[] args) throws Exception {
      	
    	/* 	
    	  JSONArray bcArray = new JSONArray();
    	  bcArray.put(0, "^XA^FO20,20^A0N,25,25^FDThis is a ZPL SDK test 1.^FS^XZ");
    	//bcArray.put(1, "^XA^FO20,20^A0N,25,25^FDThis is a ZPL SDK test 2.^FS^XZ");
    	//bcArray.put(2, "^XA^FO20,20^A0N,25,25^FDThis is a ZPL SDK test 3.^FS^XZ");
    	
    	JSONArray jPrintDetails = new JSONArray();
    	jPrintDetails.put(0,"192.168.2.20");
    	jPrintDetails.put(1,bcArray);
    	
    	new ZplOverTcpIp().execute("print",jPrintDetails,null);
   
   
    	ArrayList <String> bcLabels= new ArrayList<String>(); 
    	bcLabels.add("^XA^FO20,20^A0N,25,25^FDThis is a ZPL test 1.^FS^XZ");
    	//bcLabels.add("^XA^FO20,20^A0N,25,25^FDThis is a ZPL test 2.^FS^XZ ");
    	//bcLabels.add("^XA^FO20,20^A0N,25,25^FDThis is a ZPL test 3.^FS^XZ ");
        new ZplOverTcpIp().sendZplOverTcp("192.168.2.20",bcLabels);
     */
       
    }
  
    
}

