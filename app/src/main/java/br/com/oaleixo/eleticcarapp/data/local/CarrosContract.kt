package br.com.oaleixo.eleticcarapp.data.local

import android.provider.BaseColumns

object CarrosContract {
    object CarEntry : BaseColumns
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_BATERIA = "bateria"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_RECARGA = "recarga"
        const val COLUMN_NAME_URL_PHOTO = "url_photo"

}