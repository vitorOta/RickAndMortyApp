package com.vitorota.rickandmorty.features.character.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseFragment
import com.vitorota.rickandmorty.utils.launchUI
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment() {

    private lateinit var adapter: CharacterDataAdapter

    private val mViewModel: CharacterDetailsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupToolbar()
        setupObservers()

        if (!mViewModel.triedLoadAtLeastOnce) {
            val args: CharacterDetailsFragmentArgs by navArgs()
            val characterId = args.CHARACTERID
            launchUI {
                mViewModel.loadData(characterId)
            }
        }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity)?.let {
            it.setSupportActionBar(characterDetails_toolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            NavigationUI.setupActionBarWithNavController(it, findNavController())
        }
    }

    private fun setupView() {
        adapter = CharacterDataAdapter()
        characterDetails_rvData.adapter = adapter
        characterDetails_rvData.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        mViewModel.observe(this, this::showProgress, this::hideProgress, this::showError, this::handleData)
    }

    private fun handleData(data: Character?) {
        if (data == null) {
            this.showError(R.string.character_not_found)

        } else {
            characterDetails_collapsingToolbar.title = "${data.name}"
            characterDetails_sdvImage.setImageURI(data.image)

            val list = mutableListOf<Pair<String, String>>()
            with(data) {
                list.add(Pair(getString(R.string.name), name))
                list.add(Pair(getString(R.string.status), status))
                list.add(Pair(getString(R.string.species), species))
                list.add(Pair(getString(R.string.gender), gender))
                list.add(Pair(getString(R.string.origin), origin?.name ?: getString(R.string.unknown)))
                list.add(Pair(getString(R.string.last_location), location?.name ?: getString(R.string.unknown)))
            }
            adapter.submitList(list)
        }
    }

    override fun showError(message: Int) {
        super.showError(message)
        findNavController().navigateUp()
    }
}