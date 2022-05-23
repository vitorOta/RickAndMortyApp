package com.vitorota.rickandmorty.features.character.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.vitorota.rickandmorty.features.BaseFragment
import com.vitorota.rickandmorty.utils.launchUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment() {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val mViewModel: CharacterDetailsViewModel by viewModel()

    private lateinit var adapter: CharacterDataAdapter

    private lateinit var binding: FragmentCharacterDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
        setupToolbar()
        setupObservers()

        if (!mViewModel.triedLoadAtLeastOnce) {
            val characterId = args.characterId
            launchUI {
                mViewModel.loadData(characterId)
            }
        }
    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.let {
            it.setSupportActionBar(binding.characterDetailsToolbar)
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            NavigationUI.setupActionBarWithNavController(it, findNavController())
        }
    }

    private fun setupView() {
        adapter = CharacterDataAdapter()

        binding.characterDetailsRvData.adapter = adapter
        binding.characterDetailsSdvImage.setImageURI(args.pictureUri)
        args.characterName?.let {
            binding.characterDetailsCollapsingToolbar.title = it
        }
    }

    private fun setupObservers() {
        mViewModel.observe(
            this,
            this::showProgress,
            this::hideProgress,
            this::showError,
            this::handleData
        )
    }

    private fun handleData(data: Character?) {
        if (data == null) {
            this.showError(R.string.character_not_found)

        } else {
            binding.characterDetailsCollapsingToolbar.title = data.name
            binding.characterDetailsSdvImage.setImageURI(data.image)

            val list = mutableListOf<Pair<String, String>>()
            with(data) {
                list.add(Pair(getString(R.string.name), name))
                list.add(Pair(getString(R.string.status), status))
                list.add(Pair(getString(R.string.species), species))
                list.add(Pair(getString(R.string.gender), gender))
                list.add(
                    Pair(
                        getString(R.string.origin),
                        origin?.name ?: getString(R.string.unknown)
                    )
                )
                list.add(
                    Pair(
                        getString(R.string.last_location),
                        location?.name ?: getString(R.string.unknown)
                    )
                )
            }
            adapter.submitList(list)
        }
    }

    override fun showError(message: Int) {
        super.showError(message)
        findNavController().navigateUp()
    }
}