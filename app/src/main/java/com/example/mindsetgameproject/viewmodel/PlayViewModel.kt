package com.example.mindsetgameproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel class responsible for managing the game logic for a Mastermind-style guessing game.
 * It holds the game state and processes guesses made by the player.
 */
class PlayViewModel : ViewModel() {

    private val _gameState = MutableLiveData<List<String>>()
    val gameState: LiveData<List<String>> = _gameState

    private val targetString = generateRandomString()

    /**
     * Generates a random 4-character string consisting of uppercase letters (A-Z).
     * This string represents the target that the player is trying to guess.
     *
     * @return A 4-character string with random uppercase letters.
     */
    private fun generateRandomString(): String {
        val chars = ('A'..'Z').toList()
        return (1..4).map { chars.random() }.joinToString("")
    }

    /**
     * Compares the player's guesses to the target string and provides feedback on each guess.
     * Each guess is evaluated to determine if it's:
     * - "GREEN": The guessed character is correct and in the correct position.
     * - "ORANGE": The guessed character is correct but in the wrong position.
     * - "RED": The guessed character is not in the target string.
     *
     * The result of each guess is passed back through a callback.
     *
     * @param guesses A list of 4 guessed characters as strings.
     * @param callback A callback function that receives a list of feedback strings ("GREEN", "ORANGE", "RED") for each guess.
     */
    fun checkGuesses(guesses: List<String>, callback: (List<String>) -> Unit) {
        val result = mutableListOf<String>()
        val targetCharMap = mutableMapOf<Char, Int>()

        for (i in targetString.indices) {
            targetCharMap[targetString[i]] = i
        }

        for (i in guesses.indices) {
            val guessChar = guesses[i].firstOrNull() ?: continue
            when {
                guessChar == targetString[i] -> result.add("GREEN")
                guessChar in targetCharMap -> result.add("ORANGE")
                else -> result.add("RED")
            }
        }

        callback(result)
    }
}
