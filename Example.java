import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
 
public class Example {
 
    private final String USER_AGENT = "Mozilla/5.0";
 
    public static void main(String[] args) throws Exception {
 
        Example http = new Example();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();
 
    }
 
    // HTTP-запрос GET
    private void sendGet() throws Exception {
 		int g = (int) (1000 * Math.random());
        String url = "http://numbersapi.com/"+g+"/trivia";
 
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
 
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
		String inputLine;
		
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        StringBuffer response = new StringBuffer();
 
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
			//System.out.println(inputLine.length());
        }
        in.close();
 
                 // Распечатываем результат
        System.out.println(response.toString());
		System.out.println(response.length());
		int[] chars = new int[255];
		char[] chArray = new char[1000];
		chArray = response.toString().toCharArray();
		System.out.println("Частоты:");
		for (int i = 0; i < response.length(); i++) {
			//System.out.print(inputLine.charAt(i));
			chars[(int)(chArray[i])]++;
		}
		int kol_chars = 0;
		for (int i = 0; i < 255; i++) {
			if ((chars[i] != 0) && (i != 32) && (i != 46)) 
			{
				System.out.printf("%c - ", i);
				System.out.println(chars[i] + " раз");
				kol_chars++;
			}
			//chars[(int)(chArray[i])]++;
		}
		float res = 0;
		if (kol_chars > 0)
			res = (float)(response.length()/kol_chars);
		System.out.printf("Среднее значение частоты: %d / %d = %f\n",  
			response.length(), kol_chars, res);
		System.out.printf("Символы, которые соответствуют условию наиболее близкого значения частоты к среднему значанию: ");	
		for (int i = 0; i < 255; i++) {
			if ((chars[i] != 0) && (i != 32) && (i != 46) && (chars[i] >= res - 0.9) && (chars[i] <= res + 0.9)) 
			{
				System.out.printf("%c(%d) ", i, i);
			}
			//chars[(int)(chArray[i])]++;
		}		
		//
		System.out.printf("\n");
		
	}
}
