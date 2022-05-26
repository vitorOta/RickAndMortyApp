package com.vitorota.rickandmorty.features.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.material.shape.MaterialShapeDrawable
import com.vitorota.rickandmorty.R
import com.vitorota.rickandmorty.data.character.entity.Character
import com.vitorota.rickandmorty.features.BaseFragment
import com.vitorota.rickandmorty.utils.launchUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharactersFragment : BaseFragment() {

    private val mViewModel: ListCharacterViewModel by viewModel()

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
        val isRefreshing = mViewModel.isRefreshingFlow.collectAsState()
        val characters = mViewModel.data.observeAsState()

        AppCompatTheme {
            Surface {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing.value),
                    onRefresh = { retrieveData() }) {
                    //TODO put a conditional here to show a different screen if the list is empty or null
                    ListCharacters(characters.value ?: emptyList())
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ListCharacters(characters: List<Character>) {
        LazyVerticalGrid(cells = GridCells.Adaptive(120.dp)) {
            items(characters) {
                ItemCharacter(it)
            }
        }
    }

    @Composable
    fun ItemCharacter(character: Character) {
        Box(
            Modifier.clickable { showCharacterDetails(character) },
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.placeholder_white)
            )
            Text(
                text = character.name,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(6.dp)
                    .fillMaxWidth()

            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!mViewModel.triedLoadAtLeastOnce) {
            retrieveData()
        }
    }

    private fun retrieveData() {
        launchUI {
            mViewModel.loadCharacters()
        }
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

}