package pl.poiw.kalkulatorkaloriiapi;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {

    @FXML
    private TextField kcalTextField;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Button getButton;

    @FXML
    private TextField productTextField;

    @FXML
    void getProduct(ActionEvent event) {

        OkHttpClient client = new OkHttpClient();


        String query = productTextField.getText();
        Request request = new Request.Builder()
                .url("https://api.calorieninjas.com/v1/nutrition?query="+query)
                .get()
                .addHeader("X-Api-Key", "Y0gboJMx0d3q8EvasAnmTg==yjCO2a4uVcOoVaFc")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            if (response.isSuccessful()) {

                Gson gson = new Gson();

                Product product = gson.fromJson(responseBody, Product.class);

                Items[] items = product.getItems();
                for (Items i : items) {
                    System.out.println(i);
                    kcalTextField.setText(i.getCalories() + " kcal/100g");
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//
//        try {
//
//            URL nbpEndpoint = new URL("https://api.calorieninjas.com/v1/nutrition?query=bread");
//
//            HttpURLConnection nbpConnection = (HttpURLConnection) nbpEndpoint.openConnection();
//
//            nbpConnection.setRequestProperty("x_api_key", "Y0gboJMx0d3q8EvasAnmTg==yjCO2a4uVcOoVaFc");
//
//
//            System.out.println(nbpConnection.getResponseCode());
//            if (nbpConnection.getResponseCode() == 200) {
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(nbpConnection.getInputStream()));
//
//                Gson gson = new Gson();
//
//
//                //NBP API odsyła jednoelementową tablicę
//                Product product = gson.fromJson(br, Product.class);
//
//                Items[] items = product.getItems();
//                for (Items i: items) {
//                    System.out.println(i.getName());
//                }
//
//                nbpConnection.disconnect();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


}