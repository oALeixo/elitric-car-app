package br.com.oaleixo.eleticcarapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.oaleixo.eleticcarapp.R
import br.com.oaleixo.eleticcarapp.data.CarFactory
import br.com.oaleixo.eleticcarapp.ui.adapter.CarAdapter
import br.com.oaleixo.eleticcarapp.ui.adapter.TabsAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcular: Button
    lateinit var listaCarros: RecyclerView
    lateinit var tabLayout : TabLayout
    lateinit var viewPager : ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        setupList()
        setupTabs()

    }


    fun setupView() {
        tabLayout = findViewById(R.id.tab_layout)
        btnCalcular = findViewById(R.id.btn_calcular)
        listaCarros = findViewById(R.id.rv_lista_carros)
        viewPager = findViewById(R.id.vp_view_pager)
    }

    fun setupList() {
        val adpater = CarAdapter(CarFactory.list)
        //listaCarros.layoutManager = LinearLayoutManager(this)
        listaCarros.adapter = adpater
    }

    fun setupTabs (){
        val tabsAdapter = TabsAdapter(this)
        viewPager.adapter = tabsAdapter
        tabLayout.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
            
        })
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))

        }

    }

}
