package com.example.cachorro

import retrofit2.http.GET
import retrofit2.Call

interface DogApi {

        @GET("breeds/image/random")
        fun getBreedsDog(): Call<DogResponse>

        @GET("breed/hound/images/random")
        fun listAllBreeds(): Call<BreedListResponse>

        @GET("breed/hound/afghan/images/random")
        fun listSubBreeds(): Call<SubBreedListResponse>
    }

