package br.com.oaleixo.eleticcarapp.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.oaleixo.eleticcarapp.R
import br.com.oaleixo.eleticcarapp.data.CarFactory
import br.com.oaleixo.eleticcarapp.domain.Carro
import br.com.oaleixo.eleticcarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.LineNumberInputStream
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment(){

    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    var carrosArray : ArrayList<Carro> =  ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callService()
        setupView(view)
        setupListeners()

    }
    fun setupView(view: View) {
        view?.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }

    }

    fun setupList() {
        val adpater = CarAdapter(carrosArray)
        listaCarros.adapter = adpater
    }
    fun setupListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))
        }
    }

    fun callService(){
        val urlBase = "https://oaleixo.github.io/car-api/cars.json"
        MyTask().execute(urlBase)
    }

    inner class MyTask : AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTasck", "Iniciando ...")
        }
        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val responseCode = urlConnection.responseCode

                if(responseCode == HttpURLConnection.HTTP_OK ){
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                } else {
                    Log.e("Erro", " Serviço Indisponivel no momento...")
                }
            } catch (ex: Exception){
                Log.e("Erro", " Erro ao realizar processamento")

            } finally {
                urlConnection?.disconnect()
            }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for ( i in 0 until jsonArray.length()){
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto
                    )
                    carrosArray.add(model)
                }

                setupList()
            } catch (ex: Exception){
                Log.e("Erro ->", ex.message.toString())
            }
        }
    }
}


