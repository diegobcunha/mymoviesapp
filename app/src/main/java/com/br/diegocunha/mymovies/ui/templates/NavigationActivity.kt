package com.br.diegocunha.mymovies.ui.templates

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.br.diegocunha.mymovies.R
import com.br.diegocunha.mymovies.extensions.find
import com.br.diegocunha.mymovies.extensions.getIntOrThrow
import com.br.diegocunha.mymovies.ui.templates.navigation.Navigable

open class NavigationActivity: BaseActivity() {


    open lateinit var navigationController: NavController
    protected open val navigationGraphId: Int by lazy { intent.getIntOrThrow(Navigable.GRAPH_ID_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
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

    companion object : Navigable<NavigationActivity> {
        override val classType: Class<NavigationActivity>
            get() = NavigationActivity::class.java
    }

}