/********* ZplOverTcpIp.m Cordova Plugin Implementation *******/

#import "ZplOverTcpIp.h"
#import "TcpPrinterConnection.h"
#import "ZebraPrinterConnection.h"
#import "ZebraPrinterConnection.h"
#import <Cordova/CDV.h>
#import <UIKit/UIKit.h>



@implementation ZplOverTcpIp
- (void)print:(CDVInvokedUrlCommand*)command {
    
	CDVPluginResult *pluginResult = nil;
	NSArray*  arguments           = [command arguments];
    NSString *ipaddress = [arguments objectAtIndex:0];
	NSArray *bclabels=[arguments objectAtIndex:1];

    if (ipaddress != nil && [ipaddress length] > 0) {
	
	     // Instantiate connection for ZPL TCP port at given address. 
        id<ZebraPrinterConnection, NSObject> thePrinterConn = [[TcpPrinterConnection alloc] initWithAddress:ipaddress andWithPort:9100];
		
		 // Open the connection - physical connection is established here.
        BOOL success = [thePrinterConn open];
		
		// This example prints "This is a ZPL test." near the top of the label.
       //NSString *zplData = @"^XA^FO20,20^A0N,25,25^FDThis is a ZPL test.^FS^XZ";
       
        //NSString *zplData;
		NSString *successMsg = @"Print Success";
        NSError *error = nil;
		
        // Send the data to printer as a byte array.
		
        
        for (NSString *zplData in bclabels){
			success = success && [thePrinterConn write:[zplData dataUsingEncoding:NSUTF8StringEncoding] error:&error];
		}
        
        /*
        
        for (NSInteger charIdx=0; charIdx<bclabels.length; charIdx++) {
                // Do something with character at index charIdx, for example:
                    //zplData=[bclabels characterAtIndex:charIdx];
                    NSLog(@"%C",[bclabels characterAtIndex:charIdx] );
                success = success && [thePrinterConn write:[[bclabels characterAtIndex:charIdx] dataUsingEncoding:NSUTF8StringEncoding] error:&error];
                
                
            } */
    
    
		
		if (success == YES || error == nil) {
		   pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:successMsg];
		}else if (success != YES || error != nil) {
           pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
		// Close the connection to release resources.
        [thePrinterConn close];
        //[thePrinterConn release];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
