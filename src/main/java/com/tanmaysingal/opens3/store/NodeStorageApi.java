package com.tanmaysingal.opens3.store;

import com.tanmaysingal.opens3.OpenS3Application;
import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class NodeStorageApi {

  private static final Logger LOGGER = LogManager.getLogger(NodeStorageApi.class);

  private static Retrofit retrofit;
  private static NodeStorageApiInterface retrofitInterface;

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder().baseUrl(OpenS3Application.STORAGE_NODE_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
      retrofitInterface = retrofit.create(NodeStorageApiInterface.class);
    }
    return retrofit;
  }

  public static void store(MultipartFile file) {
    store(file, Optional.empty());
  }

  public static void store(MultipartFile file, Optional<Runnable> onResponse) {
    LOGGER.info("NodeStorageApi::store called");
    final Call<Void> call = retrofitInterface.store(file);
    call.enqueue(new Callback<>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        LOGGER.info("Uploaded file to storage node");
        LOGGER.info(response.message());
        onResponse.ifPresent(Runnable::run);
      }

      @Override
      public void onFailure(Call<Void> call, Throwable throwable) {
        LOGGER.error("Failed to upload file to storage node");
      }
    });
  }

  public static byte[] fetch(String key) throws IOException {
    final Call<byte[]> call = retrofitInterface.fetch(key);
    final Response<byte[]> response = call.execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      LOGGER.error("Failed to fetch object " + key);
    }
    return null;
  }

  public interface NodeStorageApiInterface {

    @POST("object")
    Call<Void> store(@Body MultipartFile file);

    @GET("object/{key}")
    Call<byte[]> fetch(@Path("key") String key);

  }


}
