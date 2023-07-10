package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawer:DrawerLayout
    private lateinit var toggle:ActionBarDrawerToggle

    private lateinit var tv_main_usuario: TextView
    private lateinit var iv_main_profile: ImageView
    private lateinit var imgUsuarioMenu: ImageView
    private lateinit var nombreUsuarioMenu:TextView
    private lateinit var saldoMenu: TextView
    private lateinit var et_main_search: EditText
    private var listaJuegosFiltrados = mutableListOf<Game>()
    private lateinit var recyclerViewBusqueda:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        //initRecyclerViewGenre()
        tv_main_usuario = findViewById(R.id.tv_main_usuario)
        iv_main_profile = findViewById(R.id.iv_image_profile2)


        initRecyclerViewBusquedaJuego()
        et_main_search = findViewById(R.id.et_main_search)
        buscarJuego(et_main_search)

        var idUsuario = intent.getLongExtra("idUsuario", 0)
        var usuario: User? = UserRepository.getById(idUsuario)
        tv_main_usuario.text = usuario?.name
        Picasso.get()
            .load(usuario?.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(iv_main_profile)

            initNavBar(usuario)

    }

    private fun onGameSelected(game: Game) {
        val intent: Intent = Intent(this, GameActivity::class.java)
        intent.putExtra("gameId", game.id)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_main_games)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameAdapter(GameRepository.get()) { game -> onGameSelected(game) }
    }


    private fun initRecyclerViewBusquedaJuego() {
        recyclerViewBusqueda = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewBusqueda.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerViewBusqueda.adapter = GameAdapter(listaJuegosFiltrados) { game -> onGameSelected(game) }
        //buscarJuego(et_main_search, recyclerView.adapter as GameAdapter)
    }
    private fun buscarJuego(editText:EditText){
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("Texto ingresado", s.toString())
                listaJuegosFiltrados = GameRepository.getGamesByNameContains(s.toString()) as MutableList<Game>
                recyclerViewBusqueda.adapter?.notifyDataSetChanged()
            }

        }

        editText.addTextChangedListener(textWatcher)
    }

    /*private fun initRecyclerViewGenre() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_main_generos)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = (GenreAdapter(GenreRepository.get()))
    }*/

    private fun initNavBar(usuario:User?){
        val toolbar:androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_open, R.string.navigation_close)

        drawer.addDrawerListener(toggle)
        supportActionBar?.setTitle("")

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val navHeaderView = navigationView.getHeaderView(0)
        nombreUsuarioMenu = navHeaderView.findViewById(R.id.menu_nombre_usuario)
        saldoMenu = navHeaderView.findViewById(R.id.menu_saldo_usuario)
        imgUsuarioMenu = navHeaderView.findViewById(R.id.iv_menu_profile)

        nombreUsuarioMenu.text = usuario?.name
        saldoMenu.text = usuario?.mostrarMoneyFormateada()
        Picasso.get()
                .load(usuario?.profilePicture)
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder)
                .into(imgUsuarioMenu)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}