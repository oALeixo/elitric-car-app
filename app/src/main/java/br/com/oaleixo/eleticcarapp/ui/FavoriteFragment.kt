package br.com.oaleixo.eleticcarapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.RecyclerView
import br.com.oaleixo.eleticcarapp.R
import br.com.oaleixo.eleticcarapp.data.local.CarRepository
import br.com.oaleixo.eleticcarapp.domain.Carro
import br.com.oaleixo.eleticcarapp.ui.adapter.CarAdapter

class FavoriteFragment : Fragment(){
    lateinit var listaCarrosFavoritos: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()

    }

    private fun getCarsOnLocalDb(): List<Carro> {
        val repository = CarRepository(requireContext())
        val carList = repository.getAll()
        return carList
    }

    fun setupList() {
        val cars = getCarsOnLocalDb()
        val carroAdpater = CarAdapter(cars, isFavoriteScreen = true)
        listaCarrosFavoritos.apply {
            isVisible = true
            adapter = carroAdpater
        }
        carroAdpater.carItemLister = { carro ->
           // val isSaved = CarRepository(requireContext()).saveIfNotExist(carro)
        }
    }
    fun setupView(view: View) {
        view?.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)

        }

    }



}