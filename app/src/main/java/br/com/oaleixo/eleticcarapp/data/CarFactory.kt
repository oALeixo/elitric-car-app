package br.com.oaleixo.eleticcarapp.data

import br.com.oaleixo.eleticcarapp.domain.Carro

object CarFactory {
    val list = listOf<Carro>(
        Carro(
            id = 1,
            preco = "R$ 300.000,00",
            bateria = " 300 kWh",
            potencia = "200cv",
            recarga = "30min",
            urlPhoto = "example.com.br",
            isFavorite = false
        ),
        Carro(
        id = 2,
        preco = "R$ 390.000,00",
        bateria = " 350 kWh",
        potencia = "300cv",
        recarga = "30min",
        urlPhoto = "example.com.br",
        isFavorite = false
       ),
       Carro(
            id = 3,
            preco = "R$ 500.000,00",
            bateria = " 600 kWh",
            potencia = "400cv",
            recarga = "45min",
            urlPhoto = "example.com.br",
            isFavorite = false
        )

    )
}