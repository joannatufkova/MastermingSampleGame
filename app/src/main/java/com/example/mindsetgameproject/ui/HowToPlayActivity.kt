package com.example.mindsetgameproject.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mindsetgameproject.databinding.ActivityHowToPlayBinding

/**
 * Activity that displays the instructions on how to play the Mastermind-style guessing game.
 * It provides the game rules and color codes for the feedback ("GREEN", "ORANGE", "RED").
 */
class HowToPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHowToPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHowToPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val rulesText = """
            How to Play:
            - Guess the 4-character string.
             GREEN: Correct letter in the correct position.
             ORANGE: Correct letter, wrong position.
             RED: Incorrect letter.
        """.trimIndent()

        val spannableString = SpannableString(rulesText)

        val greenStart = rulesText.indexOf("GREEN")
        val orangeStart = rulesText.indexOf("ORANGE")
        val redStart = rulesText.indexOf("RED")

        spannableString.setSpan(
            ForegroundColorSpan(Color.GREEN),
            greenStart,
            greenStart + "GREEN".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FFA500")),  // Orange color
            orangeStart,
            orangeStart + "ORANGE".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            redStart,
            redStart + "RED".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.rulesText.text = spannableString
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