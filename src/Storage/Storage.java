package Storage;

import java.util.ArrayList;

/**
 *
 * @author elias
 */
public interface Storage {
    public void add(String user_id, String transaction_json);
    public void show(String user_id, String transaction_id);
    public ArrayList<DAO> list(String user_id);
    public void sum(String user_id);
    public void close();
}
