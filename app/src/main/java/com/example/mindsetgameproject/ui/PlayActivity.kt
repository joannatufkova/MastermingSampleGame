package com.example.mindsetgameproject.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mindsetgameproject.databinding.ActivityPlayBinding
import com.example.mindsetgameproject.viewmodel.PlayViewModel

/**
 * Activity responsible for handling the logic and UI of the Mastermind-style guessing game.
 * It manages the user's guesses, provides feedback for each attempt, and tracks the number
 * of attempts remaining.
 */
class PlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayBinding
    private val viewModel: PlayViewModel by viewModels()
    private var currentAttempt = 1
    private val maxTries = 4
    private var remainingTries = maxTries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupSubmitButton()
    }

    private fun setupSubmitButton() {
        binding.checkBtn.setOnClickListener {
            handleAttempt(currentAttempt)
        }
    }

    /**
     * Handles the logic for processing the player's guess for a specific attempt.
     * It collects the guesses, evaluates them using the ViewModel, and updates the UI accordingly.
     *
     * @param attemptNumber The current attempt number (1 to 4).
     */
    private fun handleAttempt(attemptNumber: Int) {
        if (remainingTries == 0) {
            Toast.makeText(this, "No more attempts left!", Toast.LENGTH_SHORT).show()
            return
        }

        val (guesses, inputBoxes, feedbackViews) = when (attemptNumber) {
            1 -> Triple(
                listOf(
                    binding.guess1Attempt1.text.toString(),
                    binding.guess2Attempt1.text.toString(),
                    binding.guess3Attempt1.text.toString(),
                    binding.guess4Attempt1.text.toString()
                ),
                listOf(
                    binding.guess1Attempt1,
                    binding.guess2Attempt1,
                    binding.guess3Attempt1,
                    binding.guess4Attempt1
                ),
                Triple(
                    binding.feedbackAttempt1FullyCorrect,
                    binding.feedbackAttempt1PartiallyCorrect,
                    binding.feedbackAttempt1Incorrect
                )
            )
            2 -> Triple(
                listOf(
                    binding.guess1Attempt2.text.toString(),
                    binding.guess2Attempt2.text.toString(),
                    binding.guess3Attempt2.text.toString(),
                    binding.guess4Attempt2.text.toString()
                ),
                listOf(
                    binding.guess1Attempt2,
                    binding.guess2Attempt2,
                    binding.guess3Attempt2,
                    binding.guess4Attempt2
                ),
                Triple(
                    binding.feedbackAttempt2FullyCorrect,
                    binding.feedbackAttempt2PartiallyCorrect,
                    binding.feedbackAttempt2Incorrect
                )
            )
            3 -> Triple(
                listOf(
                    binding.guess1Attempt3.text.toString(),
                    binding.guess2Attempt3.text.toString(),
                    binding.guess3Attempt3.text.toString(),
                    binding.guess4Attempt3.text.toString()
                ),
                listOf(
                    binding.guess1Attempt3,
                    binding.guess2Attempt3,
                    binding.guess3Attempt3,
                    binding.guess4Attempt3
                ),
                Triple(
                    binding.feedbackAttempt3FullyCorrect,
                    binding.feedbackAttempt3PartiallyCorrect,
                    binding.feedbackAttempt3Incorrect
                )
            )
            4 -> Triple(
                listOf(
                    binding.guess1Attempt4.text.toString(),
                    binding.guess2Attempt4.text.toString(),
                    binding.guess3Attempt4.text.toString(),
                    binding.guess4Attempt4.text.toString()
                ),
                listOf(
                    binding.guess1Attempt4,
                    binding.guess2Attempt4,
                    binding.guess3Attempt4,
                    binding.guess4Attempt4
                ),
                Triple(
                    binding.feedbackAttempt4FullyCorrect,
                    binding.feedbackAttempt4PartiallyCorrect,
                    binding.feedbackAttempt4Incorrect
                )
            )
            else -> return
        }

        if (guesses.any { it.isEmpty() }) {
            Toast.makeText(this, "Please fill in all guesses", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.checkGuesses(guesses) { result ->
            updateInputBoxColors(result, inputBoxes)
            updateFeedbackTextViews(result, feedbackViews.first, feedbackViews.second, feedbackViews.third)

            currentAttempt++
            remainingTries--
            binding.remainingTriesText.text = "Remaining Tries: $remainingTries"

            if (result.all { it == "GREEN" }) {
                Toast.makeText(this, "Congratulations! You guessed correctly!", Toast.LENGTH_LONG).show()
                disableInputs()
            } else if (remainingTries == 0) {
                Toast.makeText(this, "Game Over! You've run out of tries.", Toast.LENGTH_LONG).show()
                disableInputs()
            }
        }
    }

    /**
     * Updates the background colors of the input boxes based on the result of the guess.
     * "GREEN" indicates a correct letter in the correct position, "ORANGE" indicates
     * a correct letter in the wrong position, and "RED" indicates an incorrect letter.
     *
     * @param result The result of the guesses (list of "GREEN", "ORANGE", or "RED").
     * @param inputBoxes The list of EditText views for the current attempt.
     */
    private fun updateInputBoxColors(result: List<String>, inputBoxes: List<EditText>) {
        for (i in result.indices) {
            when (result[i]) {
                "GREEN" -> inputBoxes[i].setBackgroundColor(Color.GREEN)
                "ORANGE" -> inputBoxes[i].setBackgroundColor(Color.parseColor("#FFA500")) // Orange
                "RED" -> inputBoxes[i].setBackgroundColor(Color.RED)
            }
        }
    }

    /**
     * Updates the feedback TextViews to display the number of fully correct, partially correct,
     * and incorrect guesses.
     *
     * @param result The result of the guesses.
     * @param fullyCorrectTextView The TextView for displaying the number of fully correct guesses.
     * @param partiallyCorrectTextView The TextView for displaying the number of partially correct guesses.
     * @param incorrectTextView The TextView for displaying the number of incorrect guesses.
     */
    private fun updateFeedbackTextViews(
        result: List<String>,
        fullyCorrectTextView: TextView,
        partiallyCorrectTextView: TextView,
        incorrectTextView: TextView
    ) {
        val fullyCorrect = result.count { it == "GREEN" }
        val partiallyCorrect = result.count { it == "ORANGE" }
        val incorrect = result.count { it == "RED" }

        fullyCorrectTextView.text = "Fully Correct: $fullyCorrect"
        partiallyCorrectTextView.text = "Partially Correct: $partiallyCorrect"
        incorrectTextView.text = "Incorrect: $incorrect"
    }

    /**
     * Disables the input fields and the "Check" button after the game is over or won.
     */
    private fun disableInputs() {
        binding.checkBtn.isEnabled = false
    }

    /**
     * Handles the back button press in the action bar.
     *
     * @param item The menu item selected (in this case, the back button).
     * @return True if the event was handled, otherwise passes to the superclass.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
