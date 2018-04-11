package Storage;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author elias
 */
public class DAO implements Comparable<DAO> {
    private String transaction_id;
    private Double amount;
    private String description;
    private String date;
    private Long user_id;
    private JSONObject jsonObject;
    
    public DAO(String data) {
        try {
            this.jsonObject = (JSONObject) new JSONParser().parse(data);
            this.amount = (Double) jsonObject.get("amount");
            this.description = (String) jsonObject.get("description");
            this.date = (String) jsonObject.get("date");
            this.user_id = (Long) jsonObject.get("user_id");
        }
        catch(ParseException excep) { System.out.println(excep.toString()); }
    }
    
    public DAO(String[] data) {
        this.jsonObject = new JSONObject();
        this.user_id = (Long) Long.parseLong(data[0]);
        this.transaction_id = data[1];
        this.amount = (Double) Double.parseDouble(data[2]);
        this.description = data[3];
        this.date = data[4];
    }
    
    @Override
    public String toString() {
        return this.user_id + " " + this.transaction_id + " " + this.amount + 
               " " + this.description.replace(" ", "_") + " " + this.date;
    }
    
    @Override
    public int compareTo(DAO dao) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = formatter.parse(this.date);
            date2 = formatter.parse(dao.getDate());
        } 
        catch (java.text.ParseException excep) { System.out.println(excep.toString()); }
        
        return date2.compareTo(date1);
    }
    
    public String toJson() {
        jsonObject.clear();
        jsonObject.put("transaction_id", this.transaction_id);
        jsonObject.put("amount", this.amount);
        jsonObject.put("description", this.description.replace("_", " "));
        jsonObject.put("date", this.date);
        jsonObject.put("user_id", this.user_id);
        
        return jsonObject.toJSONString();
    }
    
    public void setTransactionId(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    public Double getAmount() {
        return this.amount;
    }
    
    public String getDate() {
        return this.date;
    }
}
