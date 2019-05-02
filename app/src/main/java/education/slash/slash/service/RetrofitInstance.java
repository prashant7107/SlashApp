package education.slash.slash.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prashabt on 7/16/2018.
 */

public class RetrofitInstance {

    private static Retrofit retrofit=null;
    private static String Base_URL="url to the server";


    public static GetAllDataService getService(){

        if(retrofit==null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit.create(GetAllDataService.class);
    }
}
