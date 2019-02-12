package com.vitorota.rickandmorty.features.character.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.features.BaseActivity
import kotlinx.android.synthetic.main.activity_character_details.*

class CharacterDetailsActivity : BaseActivity() {

    private lateinit var adapter: CharacterDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        setupView()

    }

    private fun setupView() {
        setSupportActionBar(characterDetails_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        adapter = CharacterDataAdapter()
        characterDetails_rvData.adapter = adapter
        characterDetails_rvData.layoutManager = LinearLayoutManager(this)
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {

        private const val _characterIdKey = "characterIdKey"

        fun createIntent(context: Context, characterId: Int): Intent =
            Intent(context, CharacterDetailsActivity::class.java).apply {
                putExtra(_characterIdKey, characterId)
            }
    }

}
