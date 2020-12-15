package business.shipitnow.com;


import java.util.List;

import business.shipitnow.com.module.AddPakage;
import business.shipitnow.com.module.login;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class APIService {

    public static final String baseUrl = "https://shipitnowapi.com/api/Applications/";
    public static final String key = "";
    public static ApiService API_SERVICE;

    public static ApiService getApiService() {
        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    // .client(getUnsafeOkHttpClient())
                    .build();
            API_SERVICE = retrofit.create(ApiService.class);
        }
        return API_SERVICE;
    }


    public interface ApiService {

        @GET("GetLogin?")
        Call<login> getUserLogin(@Query("UserName") String user, @Query("Password") String pass);

        @GET("PostPackageDetails?")
        Call<AddPakage> AddPakge(@Query("PackageImage") String Image,
                                 @Query("PackageName") String Name,
                                 @Query("Description") String Des,
                                 @Query("PickUpLocation") String PickUp,
                                 @Query("DropOffLocation") String DropOffLoc,
                                 @Query("PackageType") String packageType,
                                 @Query("Weight") String weight,
                                 @Query("QTY") String Qty,
                                 @Query("PickUpDate") String pickUpDate,
                                 @Query("CourierService") String courierService,
                                 @Query("CourierPayment") String courierPayment,
                                 @Query("PickupLat") String PickLat,
                                 @Query("PickupLong") String PickLong,
                                 @Query("DropoffLat") String dropoffLat,
                                 @Query("DropoffLong") String dropoffLong,
                                 @Query("UOM") String Uom,
                                 @Query("PickUpTime") String pickUpTime,
                                 @Query("DropOffDate") String dropOffDate,
                                 @Query("DropOffTime") String dropOffTime);


        @GET("RetriveInProcessPackage?")
        Call<List<AddPakage>> GetPackage();

        @Multipart
        @POST("PrintonDemand")
        Call<ResponseBody> UploadFiles(@Part("FullName") RequestBody  f_Name, @Part("Email") RequestBody  f_Email, @Part("CompanyName") RequestBody f_num, @Part("Instruction") RequestBody  f_CName,
                                       @Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2,
                                       @Part MultipartBody.Part file3,
                                       @Part MultipartBody.Part file4,
                                       @Part MultipartBody.Part file5,
                                       @Part("NumofCopies1") RequestBody  N_coy1,
                                       @Part("NumofCopies2") RequestBody  N_coy2,
                                       @Part("NumofCopies3") RequestBody  N_coy3,
                                       @Part("NumofCopies4") RequestBody  N_coy4,
                                       @Part("NumofCopies5") RequestBody  N_coy5);

    }
}

