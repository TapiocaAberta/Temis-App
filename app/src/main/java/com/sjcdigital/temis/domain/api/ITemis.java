package com.sjcdigital.temis.domain.api;

import com.sjcdigital.temis.domain.model.Alderman;
import com.sjcdigital.temis.domain.model.Law;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface ITemis {

    @GET("alderman")
    Observable<Alderman> findAldermans();

    @GET("alderman")
    Observable<Alderman> findNextAldermans(@Query("page") final int page, @Query("size") final int size);

    @GET("alderman/{name}/law")
    Observable<Law> findAldermanLaws(@Path(value = "name", encoded = true) final String name,@Query("page") final int page, @Query("size") final int size);

    @GET
    Observable<Alderman> findNextLaws(@Url String url);

    @PUT("laws/{code}/vote/yes")
    Call<Law> voteYes(@Path("code") final String code);


    @PUT("laws/{code}/vote/no")
    Call<Law> voteNo(@Path("code") final String code);
}
