package com.raywenderlich.android.creaturemon.view.allcreatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.raywenderlich.android.creaturemon.R
import com.raywenderlich.android.creaturemon.presenter.allcreatures.AllCreaturesContract
import com.raywenderlich.android.creaturemon.presenter.allcreatures.AllCreaturesPresenter
import com.raywenderlich.android.creaturemon.view.creature.CreatureActivity
import kotlinx.android.synthetic.main.activity_all_creatures.*
import kotlinx.android.synthetic.main.content_all_creatures.*

class AllCreaturesActivity : AppCompatActivity(), AllCreaturesContract.View {

  private val presenter = AllCreaturesPresenter()
  private val adapter = CreatureAdapter(mutableListOf())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_all_creatures)
    setSupportActionBar(toolbar)

    presenter.setView(this)

    creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
    creaturesRecyclerView.adapter = adapter

    presenter.getAllCreatures().observe(this, { creaturesList ->
      adapter.updateCreatures(creaturesList)
    })

    fab.setOnClickListener {
      startActivity(Intent(this, CreatureActivity::class.java))
    }
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_clear_all -> {
        presenter.clearAllCreatures()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun showCreaturesCleared() {
    Toast.makeText(this, getString(R.string.creatures_cleared), Toast.LENGTH_SHORT).show()
  }
}
