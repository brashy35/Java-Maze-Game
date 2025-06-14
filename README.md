# Maze of Haze

Welcome to **Maze of Haze** — a chill little game I made using java where you move around a tile-based world in a maze that I also designed. The game is pretty simple, but solving the maze is not...

## What??

This is a 2d maze game built using Java Swing. You control a little sprite guy and walk around using your keyboard. There’s basic collision detection so you can’t just walk through obstacles like a ghost or something.

## How?

1. Clone/download this repository
2. Make sure you have java installed
3. Compile the game like this:
   ```bash
   javac Main.java GamePanel.java KeyHandler.java CollisionCheck.java
   ```
4. Then run it with:
   ```bash
   java Main
   ```

## Controls:

- `W` — Up
- `A` — Left
- `S` — Down
- `D` — Right

As basic as you.

## What’s in this code?

- **Main.java** — Just sets up the game window and gets things rolling
- **GamePanel.java** — Handles the actual game loop and screen drawing
- **KeyHandler.java** — Watches your keyboard so the player knows where to go
- **CollisionCheck.java** — Keeps you from walking through obstacles

## Why??

I made this mostly cuz I was bored, and also to practice coding with graphics, input, and basic game loops in Java. I made this when I first learned java, so it's not perfect, but it works.

## Might add some stuff...

- Add actual mazes and levels
- Put in enemies or traps
- Maybe some sound?
---

If you try it out, i hope you enjoy wandering through the Maze of Haze
