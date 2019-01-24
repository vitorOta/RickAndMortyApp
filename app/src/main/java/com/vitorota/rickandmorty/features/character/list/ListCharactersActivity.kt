package com.vitorota.rickandmorty.features.character.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.utils.creteLoadingDialog
import com.vitorota.rickandmorty.utils.showAlert
import kotlinx.android.synthetic.main.activity_list_characters.*

class ListCharactersActivity : AppCompatActivity() {


    private lateinit var adapter: ListCharactersAdapter
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_characters)

        setupView()

        loadingDialog = creteLoadingDialog()
        val viewModel = ViewModelProviders.of(this).get(ListCharacterViewModel::class.java)

        viewModel.observe(this, ::showProgress, ::hideProgress, ::showError, ::handleData)
    }

    private fun setupView() {
        rvCharacters.layoutManager = GridLayoutManager(this, 3)
        adapter = ListCharactersAdapter { }
        rvCharacters.adapter = adapter
    }

    fun handleData(data: List<Character>) {
        adapter.submitList(data)
    }

    fun showProgress() {
        loadingDialog.show()
    }

    fun hideProgress() {
        loadingDialog.hide()
    }

    fun showError(message: Int) {
        showAlert(message)
    }

}
