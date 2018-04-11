package Storage;

import org.json.simple.JSONObject;

/**
 *
 * @author elias
 */
public class SumDAO {
    private Long user_id;
    private Double sum;
    private JSONObject jsonObject;
    
    public SumDAO(Long user_id, Double sum) {
        this.jsonObject = new JSONObject();
        this.user_id = user_id;
        this.sum = sum;
    }
    
    public String toJson() {
        jsonObject.clear();
        jsonObject.put("user_id", this.user_id);
        jsonObject.put("sum", this.sum);
        
        return jsonObject.toJSONString();
    }
}
