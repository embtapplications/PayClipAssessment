package payclipassessment;

import Storage.*;
/**
 *
 *
 * @author elias
 */
public class PayClipAssessment {
    public static void main(String[] args) {
        
        if(args.length >= 2) {
            Storage storage = new FileSystem("data.txt");
            
            switch (args[1]) {
                case "sum":
                    storage.sum(args[0]);
                    break;
                case "list":
                    storage.list(args[0]);
                    break;
                case "add":
                    if(args.length > 2) {
                        storage.add(args[0], args[2]);
                    }
                    break;
                default:
                    storage.show(args[0], args[1]);
                    break;
            }
            storage.close();
        }
        else {
            System.out.println("Invalid Input ...");
        }
    }
}
