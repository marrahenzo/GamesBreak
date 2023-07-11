package com.teamanotador.gamesbreak

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import com.teamanotador.gamesbreak.adapter.GameAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.databinding.ActivityMainBinding
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var listaJuegosFiltrados = mutableListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idUser = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        val user: User = UserRepository.getById(idUser)

        initRecyclerViewBusquedaJuego()
        buscarJuego(binding.etMainSearch)
        initRecyclerView(user)
        binding.tvMainUsuario.text = user.name
        Picasso.get()
            .load(user.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(binding.ivMainProfile)
        initNavBar(user)
    }

    private fun onGameSelected(game: Game, user: User) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra(resources.getString(R.string.game_id), game.id)
        intent.putExtra(
            resources.getString(R.string.usuario_id),
            user.id
        )
        startActivity(intent)
    }

    private fun initRecyclerView(user: User) {
        binding.rvMainGames.layoutManager = LinearLayoutManager(this)
        binding.rvMainGames.adapter =
            GameAdapter(GameRepository.get()) { game -> onGameSelected(game, user) }
    }

    private fun initRecyclerViewBusquedaJuego() {
        recyclerViewBusqueda = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerViewBusqueda.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerViewBusqueda.adapter =
            GameAdapter(listaJuegosFiltrados) { game -> onGameSelected(game) }
        //buscarJuego(et_main_search, recyclerView.adapter as GameAdapter)
    }

    private fun buscarJuego(editText: EditText) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("Texto ingresado", s.toString())
                listaJuegosFiltrados =
                    GameRepository.getGamesByNameContains(s.toString()) as MutableList<Game>
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

    private fun initNavBar(usuario: User?) {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_open,
            R.string.navigation_close
        )

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