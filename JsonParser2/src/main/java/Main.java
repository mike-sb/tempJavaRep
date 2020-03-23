import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault())
        {
            HttpGet httpGet = new HttpGet("http://www.mocky.io/v2/5bed52fd3300004c00a2959d");
            CloseableHttpResponse response = httpClient.execute(httpGet);

            String text = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();
            MyPojo obj = (MyPojo)gson.fromJson(text, MyPojo.class);
//            for (Food f : obj.getBreakfast_menu().getFood())
//            {
//                System.out.println(f.toString());
//            }
            //System.out.println(obj.getBreakfast_menu().getFood().toString());
        //    System.out.println(text);

        }
    }
}

class MyPojo
{
    private Breakfast_menu breakfast_menu;

    public Breakfast_menu getBreakfast_menu ()
    {
        return breakfast_menu;
    }

    public void setBreakfast_menu (Breakfast_menu breakfast_menu)
    {
        this.breakfast_menu = breakfast_menu;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [breakfast_menu = "+breakfast_menu+"]";
    }
}
class Breakfast_menu
{
    private String[] numbers;

    private Food[] food;

    public String[] getNumbers ()
    {
        return numbers;
    }

    public void setNumbers (String[] numbers)
    {
        this.numbers = numbers;
    }

    public Food[] getFood ()
    {
        return food;
    }

    public void setFood (Food[] food)
    {
        this.food = food;
    }

    @Override
    public String toString()
    {
        return "Breakfast_menu [numbers = "+numbers+", food = "+food+"]";
    }
}

class Food
{
    private String price;

    private String name;

    private String description;

    private String calories;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getCalories ()
    {
        return calories;
    }

    public void setCalories (String calories)
    {
        this.calories = calories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", name = "+name+", description = "+description+", calories = "+calories+"]";
    }
}
