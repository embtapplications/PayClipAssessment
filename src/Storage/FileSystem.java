package Storage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author elias
 */
public class FileSystem implements Storage {
    private RandomAccessFile dataFile;
    private UUID uuid;
    private final int USER_ID = 0;
    private final int TRANSACION_ID = 1;
        
    public FileSystem(String path) {
        try {
            this.uuid = UUID.randomUUID();
            this.dataFile = new RandomAccessFile(path, "rw");
        }
        catch(IOException excep) { System.out.println(excep.toString()); }
    }
    
    @Override
    public void add(String user_id, String transaction_json){
        DAO dao = new DAO(transaction_json);
        dao.setTransactionId(uuid.toString());
        try {
            dataFile.seek(dataFile.length());
            dataFile.writeBytes(dao.toString());
            dataFile.writeBytes(System.getProperty("line.separator"));
        }
        catch(IOException excep) { System.out.println(excep.toString()); }
        System.out.println(dao.toJson());
    }
    
    @Override
    public void show(String user_id, String transaction_id) {
        String transactionLine;
        DAO dao = null;
        try {
            dataFile.seek(0);
            while((transactionLine = dataFile.readLine()) != null) {
                String[] transactionData = transactionLine.split(" ");
                if(transactionData[TRANSACION_ID].equals(transaction_id)) {
                    if(transactionData[USER_ID].equals(user_id)) {
                        dao = new DAO(transactionData);
                        System.out.println(dao.toJson());
                    }
                }
            }
            if(dao == null) {
                System.out.println("Transaction not found");
            }
        }
        catch(IOException excep) { System.out.println(excep.toString()); }
    }
    
    @Override
    public ArrayList<DAO> list(String user_id) {
        ArrayList<DAO> transactionList;
        transactionList = this.getTransactionsList(user_id);
        Collections.sort(transactionList);
        
        for(DAO dao: transactionList) {
            System.out.println(dao.toJson());
        }
        
        return transactionList;
    }
    
    @Override
    public void sum(String user_id) {
        ArrayList<DAO> transactionList;
        transactionList = this.getTransactionsList(user_id);
        Double sum;
        sum = 0.0;
        
        for(DAO dao : transactionList){
            sum += dao.getAmount();
        }
        
        sum = (double)Math.round(sum * 100000d)/100000d;
        
        System.out.println(new SumDAO(Long.parseLong(user_id), sum).toJson());
    }
    
    @Override
    public void close() {
        try {
            dataFile.close();
        }
        catch(IOException excep) { System.out.println(excep.toString()); }
    }
    
    private ArrayList<DAO> getTransactionsList(String user_id) {
        ArrayList<DAO> transactionList = new ArrayList<>();
        String transactionLine;
        try {
            dataFile.seek(0);
            while((transactionLine = dataFile.readLine()) != null) {
                String[] transactionData = transactionLine.split(" ");
                if(transactionData[USER_ID].equals(user_id)) {
                    transactionList.add(new DAO(transactionData));
                }
            }
        }
        catch(IOException excep) { System.out.println(excep.toString()); }
        
        return transactionList;
    }
}
