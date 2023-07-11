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
import com.teamanotador.gamesbreak.adapter.SearchGameAdapter
import com.teamanotador.gamesbreak.data.Game
import com.teamanotador.gamesbreak.data.User
import com.teamanotador.gamesbreak.databinding.ActivityMainBinding
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var imgUsuarioMenu: ImageView
    private lateinit var nombreUsuarioMenu: TextView
    private lateinit var saldoMenu: TextView
    private var listaJuegosFiltrados = mutableListOf<Game>()
    private lateinit var recyclerViewBusqueda: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idUser = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        val user: User = UserRepository.getById(idUser)

        initRecyclerViewBusquedaJuego(user)


        initRecyclerView(user)
        binding.tvMainUsuario.text = user.name
        Picasso.get()
            .load(user.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(binding.ivMainProfile)
        initNavBar(user)

        binding.ivMainProfile.setOnClickListener {
            val intent: Intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra(resources.getString(R.string.usuario_id), user.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val idUser = intent.getLongExtra(resources.getString(R.string.usuario_id), 0)
        val user: User = UserRepository.getById(idUser)
        //TODO mejorar
        saldoMenu.text = Utils.mostrarMoneyFormateada(user.money)
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

    private fun initRecyclerViewBusquedaJuego(user: User) {
        recyclerViewBusqueda = findViewById(R.id.recyclerView)
        val adapter =  SearchGameAdapter(listaJuegosFiltrados) { game -> onGameSelected(game, user) }
        recyclerViewBusqueda.adapter = adapter
        recyclerViewBusqueda.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        buscarJuego(binding.etMainSearch, adapter)
    }

    private fun initNavBar(usuario: User?) {
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
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
        supportActionBar?.title = ""

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val navHeaderView = navigationView.getHeaderView(0)
        nombreUsuarioMenu = navHeaderView.findViewById(R.id.menu_nombre_usuario)
        saldoMenu = navHeaderView.findViewById(R.id.menu_saldo_usuario)
        imgUsuarioMenu = navHeaderView.findViewById(R.id.iv_menu_profile)

        nombreUsuarioMenu.text = usuario?.name
        saldoMenu.text = Utils.mostrarMoneyFormateada(usuario?.money)
        Picasso.get()
            .load(usuario?.profilePicture)
            .placeholder(R.drawable.user_placeholder)
            .error(R.drawable.user_placeholder)
            .into(imgUsuarioMenu)

    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(item.itemId.toString(), "asdasd")
        return true
    }

    private fun buscarJuego(editText: EditText, adapter: SearchGameAdapter) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listaJuegosFiltrados.clear()
                if(s.toString() == ""){
                    listaJuegosFiltrados.addAll(mutableListOf<Game>())
                }else {
                    val listaTemp =
                        GameRepository.getGamesByNameContains(s.toString()) as MutableList<Game>
                    listaJuegosFiltrados.addAll(listaTemp)
                }
                adapter.actualizarDataList(listaJuegosFiltrados.size)
            }
        }
        editText.addTextChangedListener(textWatcher)
    }

}