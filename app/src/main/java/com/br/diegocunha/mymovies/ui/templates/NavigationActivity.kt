package com.br.diegocunha.mymovies.ui.templates

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.br.diegocunha.mymovies.R
import com.br.diegocunha.mymovies.databinding.ActivityNavigationBinding
import com.br.diegocunha.mymovies.extensions.find
import com.br.diegocunha.mymovies.extensions.getIntOrThrow
import com.br.diegocunha.mymovies.ui.templates.navigation.Navigable

open class NavigationActivity : BaseActivity(), AppBarConfiguration.OnNavigateUpListener {


    open lateinit var navigationController: NavController
    protected open val navigationGraphId: Int by lazy { intent.getIntOrThrow(Navigable.GRAPH_ID_KEY) }

    protected open val showArrowBack = false
    private val binding: ActivityNavigationBinding by lazy {
        ActivityNavigationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(showArrowBack)
        supportFragmentManager.find<NavHostFragment>(R.id.fragmentContainer)?.let {
            setupNavigation(it)
        }
    }

    open fun setupNavigation(navHostFragment: NavHostFragment) {
        navigationController = navHostFragment.navController.apply {
            val graph = navInflater.inflate(navigationGraphId)
            getStartDestination()?.let { startDestination ->
                graph.setStartDestination(startDestId = startDestination)
            }

            setGraph(graph, getInitialArguments())
        }

        if (showArrowBack) {
            NavigationUI.setupActionBarWithNavController(
                this,
                navigationController,
                configureAppBarConfiguration()
            )
        } else {
            NavigationUI.setupWithNavController(binding.toolbar, navigationController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(navigationController.navigateUp() || super.onSupportNavigateUp())) {
            onBackPressed()
        }
        return true
    }

    @IdRes
    open fun getStartDestination(): Int? = null

    open fun getInitialArguments(): Bundle? = intent.extras

    open fun configureAppBarConfiguration(): AppBarConfiguration {
        return AppBarConfiguration.Builder()
            .setFallbackOnNavigateUpListener(this)
            .build()
    }

    companion object : Navigable<NavigationActivity> {
        override val classType: Class<NavigationActivity>
            get() = NavigationActivity::class.java
    }

}