interface ZPLPrinter {
    
print(
         printSuccess:(ip: string,labels:string[]) => void,
         printError: (message: string) => void): void;
    
}