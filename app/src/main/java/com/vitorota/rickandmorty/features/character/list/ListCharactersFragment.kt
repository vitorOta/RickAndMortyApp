package com.vitorota.rickandmorty.features.character.list

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseFragment
import com.vitorota.rickandmorty.utils.launchUI
import kotlinx.android.synthetic.main.fragment_list_characters.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharactersFragment : BaseFragment() {

    private lateinit var adapter: ListCharactersAdapter

    private val mViewModel: ListCharacterViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupObservers()
        if (!mViewModel.triedLoadAtLeastOnce) {
            retrieveData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_characters_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
                retrieveData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

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
        listCharacters_rvCharacters.layoutManager = GridLayoutManager(context, 3)
        adapter = ListCharactersAdapter(this::showCharacterDetails)
        listCharacters_rvCharacters.adapter = adapter

        listCharacters_swipeRefresh.setOnRefreshListener { retrieveData() }
    }

    private fun handleData(data: List<Character>) {
        adapter.submitList(data)
    }

    private fun showCharacterDetails(character: Character) {
        val direction =
            ListCharactersFragmentDirections.actionListCharactersFragmentToCharacterDetailsFragment(
                character.id,
                character.name,
                character.image
            )
        findNavController().navigate(direction)
    }

    override fun showProgress() {
        listCharacters_swipeRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        listCharacters_swipeRefresh.isRefreshing = false
    }

    //TODO give a better to the user when occur an error while loading data
}