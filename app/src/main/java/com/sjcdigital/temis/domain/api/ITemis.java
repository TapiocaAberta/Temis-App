package com.sjcdigital.temis.domain.api;

import com.sjcdigital.temis.domain.model.Author;
import com.sjcdigital.temis.domain.model.Laws;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ITemis {

    @GET("laws")
    Call<Laws> findLaws();

    @GET("laws")
    Call<Laws> findPageLaw(@Query("page") final Integer page);

    @GET("laws")
    Call<Laws> findPageSizeLaw(@Query("page") final Integer page, @Query("size") final Integer size);

    @GET("alderman")
    Observable<List<Author>> findAldermans();

    @GET("laws/alderman/{name}")
    Call<Laws> findAldermanLaws(@Path("name") final String name);

    @GET("laws/alderman/{name}")
    Call<Laws> findAldermanLawsPage(@Path("name") final String name, @Query("page") final Integer page);

    @GET("laws/alderman/{name}")
    Call<Laws> findldermanLawsPageSize(@Path("name") final String name, @Query("page") final Integer page, @Query("size") final Integer size);
}
