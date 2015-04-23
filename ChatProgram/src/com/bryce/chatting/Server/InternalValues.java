package com.bryce.chatting.Server;
import java.util.HashMap;

import com.bryce.chatting.io.Logger;

public class InternalValues {
    private HashMap<String, String> values;

    public InternalValues() {
        values = new HashMap<>();
    }

    /**
     * Process the String supplied and update the value on the server
     * @param msg String to be processed
     */
    public void processString(String msg) {
        if (msg.substring(4, 7).equals("SET")) {
            setValue(msg);
        } else if (msg.substring(4, 7).equals("CON")) { 
        	setConflict(msg.substring(8, msg.length()));
    	} else if (msg.substring(4, 7).equals("REC")) {
            requestValue(msg);
        } else {
            Logger.warn("Unknown request: \'" + msg + "\'");
        }
    }

    /**
     * Set an internal value for the server based on the unprocessed String
     * @param msg The key and value seperated by an ':'
     */
    public void setValue(String msg) {
        int keyStop = 0;

        for (int i = 0; i < msg.length(); ++i) {
            if (msg.charAt(i) == ':') {
                keyStop = i;
                break;
            }
        }
        Logger.debug("Setting:\'" + msg.substring(8, keyStop) + "\':" + msg.substring(keyStop + 1, msg.length()));
        values.put(msg.substring(0, keyStop), msg.substring(keyStop + 1, msg.length()));
    }
    
    /**
     * This method will take in a long conflict from the master controller to allow the server to take actions such
     * as spawning enemies and shutting down certain stations... exc
     * @param msg the inputed message from the client
     */
    public void setConflict(String msg) {
    	int[] vals = new int[9];
    	int curval = 0;
    	String curthing = "";
    	
    	for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) != ';' && msg.charAt(i) != '@') {
				curthing += msg.charAt(i);
			}
			if (i + 1 != msg.length()) {
				if (msg.charAt(i + 1) == ';' || msg.charAt(i + 1) == '@') {
					vals[curval] = Integer.parseInt(curthing);
					curthing = "";
					curval++;
				}
			}
		}
    	//take actions here
    }

    /**
     * Returns a value on the server that matches the String
     * @param msg Value to be gotten
     * @return The value on the server, or null if that value hasn't been set
     */
    public String requestValue(String msg) {
        Logger.debug(msg.substring(4, msg.length()) + ":\'" + values.get(msg.substring(4, msg.length())) + "\'");
        return values.get(msg.substring(4, msg.length()));
    }

    public void print() {
        Logger.debug(values.toString());
    }
}
