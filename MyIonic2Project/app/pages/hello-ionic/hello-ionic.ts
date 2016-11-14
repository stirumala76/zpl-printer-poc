import {Component} from '@angular/core';
import {Platform} from 'ionic-angular';
declare var cordova: Cordova;

@Component({
  templateUrl: 'build/pages/hello-ionic/hello-ionic.html'
})
export class HelloIonicPage {


  public toggleflag: boolean = false;
  public bclables = [];

  constructor(public platform: Platform) { }


  onPrint() {

    let prnfile = '^XA'
      + '^DFR:SSFMT000.ZPL^FS'
      + '^FO125,20^ADN,36,20^FDMERITS^FS'
      + '^FO240,70^ADN,18,20^FDOrder #^FS'
      + '^FO200,100^BCN,100,Y,N,N^FN1^FS'
      + '^FO240,230^ADN,18,20^FDDestination:^FS'
      + '^FO150,260^ADN,18,10^FN2^FS'
      + '^FO150,285^ADN,18,10^FN3^FS'
      + '^FO150,310^ADN,18,10^FN4^FS'
      + '^FO150,335^ADN,18,10^FN5^FS'
      + '^FO150,365^ADN,18,10^FDOrigination:^FS'
      + '^FO300,365^ADN,18,10^FN6^FS'
      + '^FO150,390^ADN,18,10^FN7^FS'
      + '^FO300,390^ADN,18,10^FN8^FS'
      + '^FO450,390^ADN,18,10^FD      of ^FS'
      + '^FO540,390^ADN,18,10^FD1^SFD,1^FS'
      + '^FO625,390^ADN,18,10^FN9^FS'
      + '^XZ'
      + '^XA'
      + '^XFR:SSFMT000.ZPL^FS'
      + '^PQ2,0,1,Y'
      + '^XZ';

    this.platform.ready().then(() => {
      this.bclables.push(JSON.stringify({ prnfile: prnfile }));
      cordova.plugins.ZPLPrinter.print('192.168.10', this.bclables,
        (success) => {
            console.log(success);
        }, (message) => {
            alert('fail');
        });

    });


  }

  onToggle(): void {

    if (this.toggleflag) {
      this.toggleflag = false;
    } else if (!this.toggleflag) {
      this.toggleflag = true;
    }
    console.log(this.toggleflag);
    // console.log(" Exit: getInvCtNumDetails::onToggle") ;
  }

}
