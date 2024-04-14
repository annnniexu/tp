package seedu.omnitravel.exchangerateapi;

import seedu.omnitravel.errorhandlers.OmniException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Represents the currency exchange api
 * It contains methods that uses the currency exchange api to exchange one currency to another
 */
public class CurrencyRate {
    //@@author daryltay415
    /**
     * Converts the local currency value to the foreign currency value
     * @param localCurrency The local currency the user wants to convert
     * @param foreignCurrency The foreign currency the user want to convert to
     * @param amount The amount of local currency the user wants to change
     * @throws IOException Throws an exception when there is a problem accessing the web
     * @throws InterruptedException Throws an exception when the program is interrupted while accessing the web
     * @throws OmniException Throws an exception when the currencies are invalid
     */
    public static void convertCurrency(String localCurrency, String foreignCurrency, float amount) throws
            OmniException, IOException, InterruptedException{
        String[] urls = {"https://latest.currency-api.pages.dev/v1/currencies/" + localCurrency + ".json",
            "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/"
                + localCurrency + ".json"};
        int errorCounter = 0;
        for(String url:urls){
            try{
                // Access the website
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String jsonResponse = response.body();
                if(!jsonResponse.contains("\"" + localCurrency + "\"")){
                    throw new OmniException(localCurrency + " is an invalid currency!");
                } else if(!jsonResponse.contains("\"" + foreignCurrency + "\"")){
                    throw new OmniException(foreignCurrency + " is an invalid currency!");
                }
                // Parses the data
                int index = jsonResponse.indexOf("\"" + foreignCurrency + "\"");
                String subResponse = jsonResponse.substring(index);
                int indexOfComma = subResponse.indexOf(",");
                int indexOfColon = subResponse.indexOf(":");
                String conversionRate = subResponse.substring(indexOfColon+1, indexOfComma).trim();
                float foreignAmount = amount * Float.parseFloat(conversionRate);
                // Print the response
                System.out.println(amount + localCurrency + " = " + foreignAmount + foreignCurrency);
                break;
            } catch(IOException | InterruptedException exception){
                System.out.println("Error while accessing URL, trying another URL...");
                errorCounter++;
                if(errorCounter == 2){
                    System.out.println("Website might be Down!");
                }
            }
        }
    }
}
