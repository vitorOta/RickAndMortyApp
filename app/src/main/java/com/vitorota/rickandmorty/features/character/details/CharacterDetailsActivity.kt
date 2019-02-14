package com.vitorota.rickandmorty.features.character.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseActivity
import com.vitorota.rickandmorty.utils.launchUI
import kotlinx.android.synthetic.main.activity_character_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsActivity : BaseActivity() {

    private lateinit var adapter: CharacterDataAdapter

    private val mViewModel: CharacterDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        val characterId = intent.extras.getInt(KEY_CHARACTER_ID)
        //TODO threat when extra is not available

        setupView()
        setupObservers()

        launchUI {
            mViewModel.loadData(characterId)
        }

    }

    private fun setupView() {
        setSupportActionBar(characterDetails_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adapter = CharacterDataAdapter()
        characterDetails_rvData.adapter = adapter
        characterDetails_rvData.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        mViewModel.observe(this, this::showProgress, this::hideProgress, this::showError, this::handleData)
    }

    private fun handleData(data: Character?) {
        if (data == null) {
            this.showError(R.string.character_not_found)
            onSupportNavigateUp()
        } else {
            characterDetails_collapsingToolbar.title = "${data.name} (#${data.id})"
            characterDetails_sdvImage.setImageURI(data.image)

            val list = mutableListOf<Pair<String, String>>()
            with(data) {
                list.add(Pair(getString(R.string.status), status))
                list.add(Pair(getString(R.string.species), species))
                list.add(Pair(getString(R.string.gender), gender))
                list.add(Pair(getString(R.string.origin), origin?.name ?: getString(R.string.unknown)))
                list.add(Pair(getString(R.string.last_location), location?.name ?: getString(R.string.unknown)))
            }
            adapter.submitList(list)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {

        private const val KEY_CHARACTER_ID = "characterIdKey"

        fun createIntent(context: Context, characterId: Int): Intent =
            Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(KEY_CHARACTER_ID, characterId)
            }
    }

}

