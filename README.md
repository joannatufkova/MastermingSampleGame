# MastermindSampleGame in Kotlin

This is a simple **Mastermind-style guessing game** built in **Kotlin** for Android. The game involves guessing a randomly generated 4-character string, with feedback given based on the player's guesses:

- **GREEN**: The guessed character is correct and in the correct position.
- **ORANGE**: The guessed character is correct but in the wrong position.
- **RED**: The guessed character is incorrect.

## Features

- **Play the Game**: A user-friendly interface where the player can input guesses and get instant feedback.
- **How to Play**: An instructions screen that explains the rules of the game, with color formatting for feedback terms.
- **Multiple Attempts**: Players can attempt to guess the correct string up to a maximum number of tries (4 attempts by default).
- **Dynamic Feedback**: Each guess provides detailed feedback on how close the player is to the correct string.

## Project Structure

The project follows a **Model-View-ViewModel (MVVM)** architecture:

### Activities:

- **PlayActivity**: Manages the gameplay logic and UI.
- **HowToPlayActivity**: Displays instructions on how to play the game.
- **MainActivity**: The main screen with navigation options.

### ViewModel:

- **PlayViewModel**: Contains the game logic, including generating the random string and evaluating the player's guesses.

### Layouts:

Each activity has an associated layout file in the `res/layout/` directory:
- `activity_play.xml`: Layout for the gameplay screen.
- `activity_how_to_play.xml`: Layout for the instructions screen.
- `activity_main.xml`: Layout for the main screen.

---

## Game Logic

The game generates a random 4-character string consisting of uppercase letters (A-Z).

- Players input their guesses through an intuitive interface.
- After each guess, the game provides feedback based on the following rules:
  - **GREEN**: The guessed character is in the correct position.
  - **ORANGE**: The guessed character is part of the string but in the wrong position.
  - **RED**: The guessed character is not in the string.

### Example of Logic:

If the target string is `"ABCD"` and the player guesses `"A", "X", "B", "C"`, the feedback will be:
- `"A"` is in the correct position → **GREEN**.
- `"X"` is not in the string → **RED**.
- `"B"` is part of the string but in the wrong position → **ORANGE**.
- `"C"` is in the correct position → **GREEN**.

  ## Screenshots
  ![Main Screenshot](https://github.com/joannatufkova/MastermingSampleGame/blob/main/main_screen.png)
  ![How_To_Play Screenshot](https://github.com/joannatufkova/MastermingSampleGame/blob/main/how_to_play.png)
  ![Play Screenshot](https://github.com/joannatufkova/MastermingSampleGame/blob/main/play_screen.png)
  ![Final Screenshot](https://github.com/joannatufkova/MastermingSampleGame/blob/main/final_screen.png))





