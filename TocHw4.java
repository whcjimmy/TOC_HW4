import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class TocHw4
{
    public static void main(String[] args) throws JSONException, IOException
    {
	Integer max_distinct_month;
	String str;
	Info info;
	LinkedHashMap<String, Info> road = new LinkedHashMap<String, Info>();
	URL url = new URL(args[0]);
	InputStream reader = url.openStream();
	JSONArray add = new JSONArray(new JSONTokener(reader));

	Pattern pattern  = Pattern.compile("(.*(路|大道|街))");
	Pattern pattern2 = Pattern.compile("((.(?!^路$|^大道$|^街$))*巷)");
	Matcher matcher;
	Matcher matcher2;

	max_distinct_month = 0;

	for(int i = 0; i < add.length(); i++)
	{
	    JSONObject obj = add.getJSONObject(i);
	    str = obj.getString("土地區段位置或建物區門牌");
	    matcher  = pattern.matcher(str);
	    matcher2 = pattern2.matcher(str);

	    if(matcher.find() && matcher.group() != "高雄市路" && matcher.group() != "嘉義縣蕃路")
		str = matcher.group();
	    else if(!matcher.find() && matcher2.find())
		str = matcher2.group();
	    else
		str = "";

	    if(str != "")
	    { 
		if(road.containsKey(str))
		{
		    info = road.get(str);
		    info.search(obj.getInt("交易年月"),obj.getInt("總價元"));
		    road.put(str, info);
		}
		else
		{
		    Info newinfo = new Info();
		    newinfo.init(obj.getInt("交易年月"),obj.getInt("總價元"));
		    road.put(str, newinfo);
		}
	    }
	}
	for(Map.Entry<String, Info>entry:road.entrySet())
	{
	    if(entry.getValue().getSize() > max_distinct_month)
		max_distinct_month = entry.getValue().getSize();
	}
	for(Map.Entry<String, Info>entry:road.entrySet())
	{
	    if(entry.getValue().getSize() == max_distinct_month)
		System.out.println(entry.getKey()+", 最高成交價:"+entry.getValue().getMax()+",最低成交價:"+entry.getValue().getMin());
	}
    }
}
