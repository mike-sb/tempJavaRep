import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.io.IOException;



public class APIMain {

    private static String api_key = "trnsl.1.1.20191216T140322Z.6cdcf10b09026f49.91fd6c3897b1cf09e2cb91bfe5a2be4852fca446";

    public static void main(String[] args) throws IOException {

        try(CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            Scanner in = new Scanner(System.in);
            System.out.print("text to translate: ");
            String text_to_translate = in.nextLine();
            String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+api_key+"&text="+text_to_translate+"&lang=en-ru";
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);

            String txt = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();

            MyPojo obj = (MyPojo)gson.fromJson(txt, MyPojo.class);
            String out="";
for (String s: obj.getText())
    out+=s+" ";

            System.out.println(text_to_translate+"-"+out);
            System.out.println("Translated with http://translate.yandex.ru");
        }
    }

    public class MyPojo
    {
        private String code;

        private String[] text;

        private String lang;

        public String getCode ()
        {
            return code;
        }

        public void setCode (String code)
        {
            this.code = code;
        }

        public String[] getText ()
        {
            return text;
        }

        public void setText (String[] text)
        {


            this.text = text;
        }

        public String getLang ()
        {
            return lang;
        }

        public void setLang (String lang)
        {
            this.lang = lang;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [code = "+code+", text = "+text+", lang = "+lang+"]";
        }
    }


}

