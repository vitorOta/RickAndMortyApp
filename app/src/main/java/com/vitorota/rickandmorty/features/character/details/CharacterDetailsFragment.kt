package com.vitorota.rickandmorty.features.character.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseFragment
import com.vitorota.rickandmorty.utils.launchUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment() {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val mViewModel: CharacterDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MainComponent()
            }
        }
    }

    @Composable
    fun MainComponent() {
        val character = mViewModel.data.observeAsState()

        val scrollState = rememberScrollState()

        AppCompatTheme {
            Surface(modifier = Modifier.scrollable(scrollState,Orientation.Vertical)) {
                character.value?.let {
                    CharacterDetail(it)
                }
            }
        }
    }

    @Composable
    fun CharacterDetail(character: Character) {
        val icon = if (character.isFavorite) R.drawable.ic_star_yellow else R.drawable.ic_star_white
        Column {
            Box(contentAlignment = Alignment.BottomEnd) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                FloatingActionButton(
                    onClick = { /*TODO implement onClick*/ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = null
                    )
                }
            }
            LazyColumn {
                items(character.infoAsList()) { info ->
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Label(text = info.first)
                        Content(text = info.second)
                    }
                }
            }
        }
    }

    @Composable
    fun Label(text: String) {
        Text(text = "$text:", fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }

    @Composable
    fun Content(text: String) {
        Text(
            text = text, fontSize = 20.sp, modifier = Modifier
                .padding(start = 16.dp)
                .background(
                    Color.LightGray.copy(alpha = 10.9f)
                )
        )
    }


    private fun Character.infoAsList() = mutableListOf<Pair<String, String>>().also { list ->
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!mViewModel.triedLoadAtLeastOnce) {
            val characterId = args.characterId
            launchUI {
                mViewModel.loadData(characterId)
            }
        }
    }
}