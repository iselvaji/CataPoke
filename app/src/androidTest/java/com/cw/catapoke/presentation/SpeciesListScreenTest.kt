package com.cw.catapoke.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.cw.catapoke.R
import com.cw.catapoke.di.AppModule
import com.cw.catapoke.ui.theme.CataPokeTheme
import com.cw.catapoke.util.TestUtil.disableNetwork
import com.cw.catapoke.util.TestUtil.enableNetwork
import com.cw.catapoke.util.TestUtil.waitUntilDoesNotExist
import com.cw.catapoke.utils.Constants
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@HiltAndroidTest
@UninstallModules(AppModule::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SpeciesListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            CataPokeTheme{
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }

    @Test
    fun test1_isSpeciesList_Visible() {
        composeRule.waitUntilDoesNotExist(hasTestTag(Constants.TEST_TAG_PROGRESS))
        composeRule.onNodeWithTag(Constants.TEST_TAG_GRID).assertIsDisplayed()
    }

    @Test
    fun test2_isSpeciesImage_Visible() {
        composeRule.waitUntilDoesNotExist(hasTestTag(Constants.TEST_TAG_PROGRESS))
        composeRule.onAllNodesWithContentDescription(composeRule.activity.getString(R.string.content_desc_image_desc))
            .onFirst().assertExists()
    }

    @Test
    fun test3_selectedListItem_navigation() {
        composeRule.waitUntilDoesNotExist(hasTestTag(Constants.TEST_TAG_PROGRESS))
        composeRule.onNodeWithTag(Constants.TEST_TAG_GRID).assertIsDisplayed()
        composeRule.onAllNodesWithContentDescription(composeRule.activity.getString(R.string.content_desc_image_desc))
            .onFirst().performClick()
        composeRule.onNodeWithContentDescription(composeRule.activity.getString(R.string.content_desc_back_button)).assertIsDisplayed()
    }

    @Test
    fun test4_isNetworkErrorVisible() {
        disableNetwork()
        composeRule.waitUntilDoesNotExist(hasTestTag(Constants.TEST_TAG_PROGRESS))
        val message = composeRule.activity.getString(R.string.err_connectivity)
        composeRule.onNodeWithText(message).assertIsDisplayed()
        enableNetwork()
    }
}