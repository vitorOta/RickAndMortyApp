package com.vitorota.rickandmorty.features.character.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseActivity
import com.vitorota.rickandmorty.features.character.details.CharacterDetailsActivity
import com.vitorota.rickandmorty.utils.launchUI
import kotlinx.android.synthetic.main.activity_list_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharactersActivity : BaseActivity() {

    private lateinit var adapter: ListCharactersAdapter

    private val mViewModel: ListCharacterViewModel by viewModel()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_characters_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.refresh -> {
                retrieveData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_characters)

        setupView()
        setupObservers()
        retrieveData()
    }

    private fun retrieveData() {
        launchUI {
            mViewModel.loadCharacters()
        }
    }

    private fun setupObservers() {
        mViewModel.observe(this, ::showProgress, ::hideProgress, ::showError, ::handleData)
    }

    private fun setupView() {
        listCharacters_rvCharacters.layoutManager = GridLayoutManager(this, 3)
        adapter = ListCharactersAdapter(this::showCharacterDetails)
        listCharacters_rvCharacters.adapter = adapter
    }

    fun handleData(data: List<Character>) {
        adapter.submitList(data)
    }

    fun showCharacterDetails(character: Character) {
        val intent = CharacterDetailsActivity.createIntent(this, character.id)
        startActivity(intent)
    }
}