package com.sjcdigital.temis.model.api;

import com.sjcdigital.temis.model.domain.Laws;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITemis {

    @GET("laws")
    Call<Laws> getLaws();

    @GET("laws")
    Call<Laws> getPageLaw(@Query("page") final Integer page);

    @GET("laws")
    Call<Laws> getPageSizeLaw(@Query("page") final Integer page, @Query("size") final Integer size);

    @GET("alderman")
    Call<Laws> getAllAlderman();

    @GET("laws/alderman/{name}")
    Call<Laws> getAldermanLaws(@Field("name") final String name);

    @GET("laws/alderman/{name}")
    Call<Laws> getAldermanLawsPage(@Field("name") final String name, @Query("page") final Integer page);

    @GET("laws/alderman/{name}")
    Call<Laws> getAldermanLawsPageSize(@Field("name") final String name, @Query("page") final Integer page, @Query("size") final Integer size);


}
