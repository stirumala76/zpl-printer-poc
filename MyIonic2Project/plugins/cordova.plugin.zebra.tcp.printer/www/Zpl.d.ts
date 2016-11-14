interface CordovaPlugins {
  ZPLPrinter: ZPLPrinter;
}

interface ZPLPrinter {

  print(
    ipaddress: string,
    bclabels: any,
    printSuccess: (ip: string, labels: string[]) => void,
    printError: (message: string) => void): void;

}
