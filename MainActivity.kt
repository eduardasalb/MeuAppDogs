package com.example.cachorro

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var dogImageViewRandom: ImageView
    private lateinit var dogImageViewBreeds: ImageView
    private lateinit var dogImageViewSubBreeds: ImageView
    private lateinit var loadDogButton: Button
    private lateinit var listBreedsButton: Button
    private lateinit var listSubBreedsButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImageViewRandom = findViewById(R.id.dogImageViewRandom)
        dogImageViewBreeds = findViewById(R.id.dogImageViewBreeds)
        dogImageViewSubBreeds = findViewById(R.id.dogImageViewSubBreeds)
        loadDogButton = findViewById(R.id.loadDogButton)
        listBreedsButton = findViewById(R.id.listBreedsButton)
        listSubBreedsButton = findViewById(R.id.listSubBreedsButton)

        loadDogButton.setOnClickListener {
            loadRandomDog()
        }

        listBreedsButton.setOnClickListener {
            listBreeds()
        }

        listSubBreedsButton.setOnClickListener {
            listSubBreeds()
        }

        loadRandomDog()
    }

    private fun loadRandomDog() {
        val call = ApiCliente.instance.getBreedsDog()
        call.enqueue(object : Callback<DogResponse> {
            override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.message

                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder) // imagem temporária
                        .error(R.drawable.error_image)              // imagem de erro
                        .into(dogImageViewRandom)
                } else {
                    Toast.makeText(this@MainActivity, "Erro na resposta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun listBreeds() {
        val call = ApiCliente.instance.listAllBreeds()
        call.enqueue(object : Callback<BreedListResponse> {
            override fun onResponse(
                call: Call<BreedListResponse>,
                response: Response<BreedListResponse>
            ) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.message
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder) // imagem temporária
                        .error(R.drawable.error_image)              // imagem de erro
                        .into(dogImageViewBreeds)
                } else {
                    Toast.makeText(this@MainActivity, "Erro na resposta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BreedListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun listSubBreeds() {
        val call = ApiCliente.instance.listSubBreeds()
        call.enqueue(object : Callback<SubBreedListResponse> {
            override fun onResponse(
                call: Call<SubBreedListResponse>,
                response: Response<SubBreedListResponse>
            ) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.message
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_placeholder) // imagem temporária
                        .error(R.drawable.error_image)              // imagem de erro
                        .into(dogImageViewSubBreeds)
                } else {
                    Toast.makeText(this@MainActivity, "Erro na resposta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SubBreedListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}