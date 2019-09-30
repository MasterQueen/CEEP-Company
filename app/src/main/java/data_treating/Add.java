package data_treating;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by WANGBIN on 2018/7/24.
 */

public class Add {

    String is_add;

    public Add(String company, String postion, String request, String address, String time, String people, String mark, String sendtime){
        HttpURLConnection connection;
        try {
            //用GET方法向服务器传送数据，在链接里面传值
            String mcompany = URLEncoder.encode(company, "UTF-8");
            String mpostion = URLEncoder.encode(postion, "UTF-8");
            String mrequest = URLEncoder.encode(request, "UTF-8");
            String maddress = URLEncoder.encode(address, "UTF-8");
            String mtime = URLEncoder.encode(time, "UTF-8");
            String mpeople = URLEncoder.encode(people, "UTF-8");
            String mmark = URLEncoder.encode(mark, "UTF-8");
            String msendtime = URLEncoder.encode(sendtime,"UTF-8");
            URL url = new URL("http://47.104.94.221:8080/JWebDemoByIDE/CompanyServlet?company=" + mcompany + "&postion=" + mpostion + "&request=" + mrequest + "&address=" + maddress + "&time=" + mtime + "&people=" + mpeople + "&mark=" + mmark + "&sendtime=" + msendtime);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "UTF-8");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder s = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    s.append(line);
                }
                reader.close();
                is_add = s.toString();
            }
        }catch(Exception e){
            Log.i("ok", "有错误！");
            e.printStackTrace();
        }
    }

    public String getIs_add() {
        return is_add;
    }
}
