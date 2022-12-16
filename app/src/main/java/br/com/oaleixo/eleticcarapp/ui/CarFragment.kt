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
import br.com.oaleixo.eleticcarapp.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.LineNumberInputStream
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment(){

    lateinit var fabCalcular: FloatingActionButton
    lateinit var listaCarros: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListeners()

    }
    fun setupView(view: View) {
        view?.apply {
            fabCalcular = findViewById(R.id.fab_calcular)
            listaCarros = findViewById(R.id.rv_lista_carros)
        }

    }

    fun setupList() {
        val adpater = CarAdapter(CarFactory.list)
        listaCarros.adapter = adpater
    }
    fun setupListeners() {
        fabCalcular.setOnClickListener {
            startActivity(Intent(context, CalcularAutonomiaActivity::class.java))

        }

    }

    inner class MyTask : AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTasck", "Iniciando ...")
        }
        override fun doInBackground(vararg url: String?): String {
            TODO("Not yet implemented")
            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000

                var inString = streamToString(urlConnection.inputStream)
                publishProgress(inString)
            } catch (ex: Exception){
                Log.e("Erro", " Erro ao realizar processamento")

            } finally {
                urlConnection?.disconnect()
            }

            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json: JSONObject
                values[0]?.let {
                    json = JSONObject(it)
                }
                val


            } catch (ex: Exception){

            }
        }

        fun streamToString(inputStream: InputStream) : String{
            val bufferReader = BufferedReader(InputStreamReader(inputStream))
            var line: String
            var result = ""

            try {
                do{
                    line = bufferReader.readLine()
                    line?.let {
                        result += line
                    }
                }while ( line!= null )
            }catch (ex: Exception){
                Log.e("Erro", "Erro ao parcelar Stream")
            }
            return result
        }

    }
}