David Abrahams
May 20, 2013

Dance Dance Revolution

Description:

This program is a desktop emulation of the Dance Dance Revolution game.  It allows the user to input the pre-generated songs and step files from the DDR emulator StepMania. It is playable on the keyboard, but also through dance pads. The user can chose to play single and multiplayer, and can select the difficulty level of the song.

This program solves the problem of people wanting to play custom songs in Dance Dance Revolution with their computers, without the need of a game console or game. This will help solve problems such as boredom, weight gain, and general unhappiness.

People who like Dance Dance Revolution, but do not want to pay for a game console or pre-made dance pad would want to use this program. This program would also be useful for someone who made their own custom dance pad. I made a dance pad that is interfaced with the program through arduino. This is the one unique aspect of the program. The program takes the input from the dance pad as well as a keyboard.

Instructions:

The user uses the mouse to make selections through the menus, and uses the keyboard and/or custom input to play the game. If additional help is required, the student can press the help button within the program.

Main Class List:

DanceDaceRevolution: The class containing the main method.
MainFrame: A JFrame that represents the program?s window.
MainMenuPanel: A JPanel where the user can chose items from the main menu, such as to chose a song, play a game, import a song, or view the help screen.
SongSelectPanel: A JPanel where the user selects what song, difficulty, and amount of players they would like to play with.
SongImportPanel: A JPanel where the user inports a custom song and StepFile.
InGamePanel: A JPanel where the game is actually played.
HelpPanel: A JPanel where the user can view instructions for the program.
SongLibraryPanel: A JPanel where the user can import/export a song library, and add or delete new songs.
Song: Represents a song in this program.
StepFile: Each song has a StepFile, a series of steps the user must perform when playing the game.
Feedback: feedback the appears on the screen telling the user how well they hit/didn?t hit a step.
Receptor: A receiver for the steps.
Step: The moving arrows representing the steps the users must perform during the game. (LeftArrow, RightArrow, DownArray, and UpArrow extend this class).
SerialUtility: Takes input from an arduino and sends it into the game.

Responsibility List:

As I am the only person in this group, I will be coding the entire thing.

How many song choices are there going to be? Songs take a lot of memory so the quality of sound may be affected. Look into a library for MP3s to fix that problem?