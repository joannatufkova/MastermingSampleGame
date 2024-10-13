package com.example.mindsetgameproject.viewmodel

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class PlayViewModelTest {

    private lateinit var viewModel: PlayViewModel

    @Before
    fun setup() {
        viewModel = PlayViewModel()
        //viewModel.setTargetString("ABCD")
    }

    @Test
    fun `checkGuesses returns GREEN for fully correct guesses`() {
        val guesses = listOf("A", "B", "C", "D")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("GREEN", "GREEN", "GREEN", "GREEN"), result)
        }
    }

    @Test
    fun `checkGuesses returns ORANGE for correct letters in wrong positions`() {
        val guesses = listOf("B", "A", "D", "C")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("ORANGE", "ORANGE", "ORANGE", "ORANGE"), result)
        }
    }

    @Test
    fun `checkGuesses returns RED for incorrect guesses`() {
        val guesses = listOf("X", "Y", "Z", "W")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("RED", "RED", "RED", "RED"), result)
        }
    }

    @Test
    fun `checkGuesses returns mixed result for mixed guesses`() {
        val guesses = listOf("A", "Y", "C", "W")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("GREEN", "RED", "GREEN", "RED"), result)
        }
    }

    @Test
    fun `checkGuesses returns mixed result for partially correct guesses`() {
        val guesses = listOf("A", "B", "D", "C")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("GREEN", "GREEN", "ORANGE", "ORANGE"), result)
        }
    }

    @Test
    fun `checkGuesses handles duplicate letters in target string`() {
        //viewModel.setTargetString("AABC")

        val guesses = listOf("A", "A", "B", "D")
        viewModel.checkGuesses(guesses) { result ->
            assertEquals(listOf("GREEN", "GREEN", "GREEN", "RED"), result)
        }
    }
}