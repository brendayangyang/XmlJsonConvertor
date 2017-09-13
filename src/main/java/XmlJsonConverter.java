import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yangyang on 6/7/17.
 */
public class XmlJsonConverter
{
    private String readFile(String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while(line != null)
        {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        return sb.toString();
    }

    private String convertJson(String xml) throws JSONException
    {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String json = xmlJSONObj.toString(4);
        return json;
    }

    private void write(String xmlFileName, String jsonFileName) throws IOException, JSONException
    {
        String buffer = readFile(xmlFileName);
        Path file = Paths.get(jsonFileName);
        Files.write(file, convertJson(buffer).getBytes());
    }

    public static void main(String[] args) throws IOException, JSONException
    {
        XmlJsonConverter app = new XmlJsonConverter();
        app.write(args[0],args[1]);
    }

}


