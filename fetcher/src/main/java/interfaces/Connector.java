package interfaces;

import model.Preferences;
import javax.json.JsonArray;
import java.io.UnsupportedEncodingException;

public interface Connector {

    public JsonArray getData(Preferences preferences);

}
