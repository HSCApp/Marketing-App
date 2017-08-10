package hsc.marketingmessager.support;

import hsc.marketingmessager.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hoang Ha on 8/3/2017.
 */

public interface MmInterface{
    //http://xehoihanoi.com.vn/api/auth/create?email=xuansang0509@gmail.com&password=123456&username=xuansang&phone=0986373770
    //1.
    @GET("api/auth/create")
    Call<Response> createUser(@Query("username") String username,@Query("email") String email,@Query("password") String password,@Query("phone") String phone);

    //2.api/group/create?name=hoang&text=noidung&status=1
    @GET("api/auth/create")
    Call<Response> createGroup(@Query("name") String groupName,@Query("text") String description,@Query("status") String status);
    //3. http://xehoihanoi.com.vn/api/auth/login?email=xuansang0509@gmail.com&password=123456
    Call<Response> login(@Query("email") String email,@Query("password") String password);


    //4. Load AdUnitId

}
