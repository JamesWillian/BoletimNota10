package com.jammes.boletimnota10

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

//        EncryptedSharedPreferencesUtil.clearSessionToken(this)

        // Passa cada menu ID como um conjunto de Ids porque cada
        // menu deve ser considerado como destino de nível superior.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_disciplinas,
                R.id.nav_aluno,
            ),
        )

        navView.setupWithNavController(navController)

        // Verifica se está chamando a tela de login e esconde o menu
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.usuarioFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()

        checkUserLogin()
    }

    /*
    * Verifica se o usuário está logado e se não estiver, chama a tela de login
    * */
    private fun checkUserLogin() {
        val token = EncryptedSharedPreferencesUtil.getSessionToken(this)

        if (token == null)
            navController.navigate(R.id.usuarioFragment)
    }

    /*
    * Define a ação dos botões do toolbar
    * action_nova_turma -> Tela para Inserir Nova Turma
    *
    * Removi o Toolbar, deixei o código apenas de exemplo
    * */
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_nova_turma -> {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.nav_form_turma)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}