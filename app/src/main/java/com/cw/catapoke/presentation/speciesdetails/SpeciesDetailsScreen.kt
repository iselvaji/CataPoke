package com.cw.catapoke.presentation.speciesdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cw.catapoke.R
import com.cw.catapoke.presentation.common.ComposeUtil.ContentText
import com.cw.catapoke.presentation.common.ComposeUtil.ContentTextWithColor
import com.cw.catapoke.presentation.common.ComposeUtil.ErrorText
import com.cw.catapoke.presentation.common.ComposeUtil.HeaderText
import com.cw.catapoke.presentation.common.ComposeUtil.LoadImage
import com.cw.catapoke.presentation.common.ComposeUtil.SubHeaderText
import com.cw.catapoke.utils.AppUtil
import com.cw.catapoke.utils.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun SpeciesDetailsScreen (
    selectedSpeciesId:Int,
    navigator: DestinationsNavigator,
    viewModel: SpeciesDetailsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navigator.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_desc_back_button)
                        )
                    }
                }
            )
        }
    ) {
        val state = viewModel.state
        if (state.error == null || state.error == R.string.err_unable_to_load_evolution) {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )) {
                state.currentSpeciesDetails?.let {
                    HeaderText(message = it.name, tag = Constants.TEST_TAG_SPECIES_NAME)
                    // LoadImage(AppUtil.getImageUrl(it.id))
                    ContentText(it.flavorEntries[0].text, Constants.TEST_TAG_FLAVOR_TEXT)
                    ContentText(stringResource(id = R.string.capture_rate) + it.captureRate.toString())
                }

                if(state.evolutionSpeciesDetails != null) {
                    state.evolutionSpeciesDetails.let {
                        SubHeaderText(stringResource(id = R.string.first_evolution))
                        ContentText(it.name, Constants.TEST_TAG_EVOLUTION)
                        LoadImage(AppUtil.getImageUrl(it.id))
                        ContentText(stringResource(id = R.string.capture_rate) + it.captureRate.toString())
                    }
                } else if(!state.isLoading){
                    SubHeaderText(stringResource(id = R.string.first_evolution))
                    ContentText(stringResource(R.string.evolution_details_not_present), Constants.TEST_TAG_EVOLUTION)
                }

                state.captureRateDiff?.let {
                    ContentText(stringResource(id = R.string.capture_rate_diff))
                    val color = if (it.second) Color.Green else Color.Red
                    ContentTextWithColor(
                        it.first.toString(),
                        tag = Constants.TEST_TAG_CAPTURE_RATE_DIFF,
                        color = color
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(Modifier.testTag(Constants.TEST_TAG_PROGRESS))
            } else if (state.error != null) {
                ErrorText(stringResource(state.error))
            }
        }
    }
}
