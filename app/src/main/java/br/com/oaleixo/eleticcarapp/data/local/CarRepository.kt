package br.com.oaleixo.eleticcarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import br.com.oaleixo.eleticcarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import br.com.oaleixo.eleticcarapp.domain.Carro

class CarRepository (private val context: Context){

    fun save(carro: Carro) : Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase
            val values =  ContentValues().apply {
                put(COLUMN_NAME_CAR_ID, carro.id)
                put(COLUMN_NAME_PRECO, carro.preco)
                put(COLUMN_NAME_BATERIA, carro.bateria)
                put(COLUMN_NAME_POTENCIA, carro.potencia)
                put(COLUMN_NAME_RECARGA, carro.recarga)
                put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)

        }

           val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)

           if (inserted != null){
               isSaved = true
           }
        } catch (ex: Exception){
            ex.message?.let {
                Log.e("Erro ao inserir ->", it)
            }
        }
        return isSaved
    }

    fun findCarById(id: Int): Carro {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        //Listagem das colunas a serem exibidas no resultado da Query
        val columns = arrayOf(BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())


        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME, // Nome da tabela
            columns, // as colunas
            filter, // where (filtro)
            filterValues, // valor do where, subistituindo o parametro ?
            null,
            null,
            null
        )

        val itemCar = mutableListOf<Carro>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                Log.d("ID ->", itemId.toString())
            }
        }
        cursor.close()
    }
}