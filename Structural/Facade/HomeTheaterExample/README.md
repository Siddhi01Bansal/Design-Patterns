# Facade Pattern - Home Theater Example

## Definition

The Facade Pattern is a Structural Design Pattern that provides a simplified interface to a complex subsystem.

It hides the complexity of multiple interacting classes and exposes a single high-level interface that clients can use.

## Intent

Provide a unified and simplified interface to a set of interfaces in a subsystem.

## Problem

Consider a Home Theater System consisting of multiple components:

* DVD Player
* Projector
* Sound System
* Lights

To watch a movie, the client has to perform multiple operations in the correct order:

```java
lights.dim();

projector.on();

soundSystem.on();
soundSystem.setVolume(50);

dvdPlayer.playMovie("Interstellar");
```

The client must know:

* Which subsystem classes exist
* The correct sequence of operations
* How the subsystems interact

As the subsystem grows, client code becomes more complex and harder to maintain.

## Solution

Create a Facade that hides the complexity of the subsystem and provides simple high-level operations.

Instead of:

```java
lights.dim();

projector.on();

soundSystem.on();
soundSystem.setVolume(50);

dvdPlayer.playMovie("Interstellar");
```

The client can simply write:

```java
homeTheaterFacade.watchMovie("Interstellar");
```

The Facade internally coordinates all subsystem interactions.

## Structure

### Facade

Provides a simplified interface to the subsystem.

```java
HomeTheaterFacade
```

### Subsystems

Classes that perform the actual work.

```java
DVDPlayer
Projector
SoundSystem
Lights
```

### Client

Uses the Facade instead of interacting with subsystem classes directly.

```java
Client
```

## Class Diagram

```text
                     Client
                        |
                        v
             +--------------------+
             | HomeTheaterFacade  |
             +--------------------+
                 |     |     |
                 |     |     |
                 v     v     v
           +------+ +------+ +------+
           | DVD  | |Proj. | |Sound |
           +------+ +------+ +------+
                    |
                    v
                +--------+
                | Lights |
                +--------+
```

## Implementation Flow

### Step 1: Create Subsystem Classes

#### DVDPlayer

```java
public class DVDPlayer {

    public void playMovie(String movieName) {
        System.out.println(
            "Playing the movie: " + movieName
        );
    }

    public void stopMovie() {
        System.out.println("Movie stopped");
    }
}
```

#### Projector

```java
public class Projector {

    public void on() {
        System.out.println(
            "Projector turned on"
        );
    }

    public void off() {
        System.out.println(
            "Projector turned off"
        );
    }
}
```

#### SoundSystem

```java
public class SoundSystem {

    private int volume;

    public void on() {
        System.out.println(
            "Sound System turned on"
        );
    }

    public void off() {
        System.out.println(
            "Sound System turned off"
        );
    }

    public void setVolume(int volume) {
        this.volume = volume;

        System.out.println(
            "Volume set to " + volume
        );
    }
}
```

#### Lights

```java
public class Lights {

    public void dim() {
        System.out.println(
            "Lights dimmed"
        );
    }

    public void normal() {
        System.out.println(
            "Lights restored"
        );
    }
}
```

### Step 2: Create Facade

```java
public class HomeTheaterFacade {

    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Lights lights;

    public HomeTheaterFacade(
            DVDPlayer dvdPlayer,
            Projector projector,
            SoundSystem soundSystem,
            Lights lights) {

        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.lights = lights;
    }

    public void watchMovie(String movieName) {

        lights.dim();

        projector.on();

        soundSystem.on();
        soundSystem.setVolume(50);

        dvdPlayer.playMovie(movieName);
    }

    public void endMovie() {

        dvdPlayer.stopMovie();

        projector.off();

        soundSystem.off();

        lights.normal();
    }
}
```

### Step 3: Client Usage

```java
public class Client {

    public static void main(String[] args) {

        DVDPlayer dvdPlayer =
                new DVDPlayer();

        Projector projector =
                new Projector();

        SoundSystem soundSystem =
                new SoundSystem();

        Lights lights =
                new Lights();

        HomeTheaterFacade facade =
                new HomeTheaterFacade(
                        dvdPlayer,
                        projector,
                        soundSystem,
                        lights
                );

        facade.watchMovie(
                "Interstellar"
        );

        facade.endMovie();
    }
}
```

## Output

```text
Lights dimmed
Projector turned on
Sound System turned on
Volume set to 50
Playing the movie: Interstellar

Movie stopped
Projector turned off
Sound System turned off
Lights restored
```

## How It Works

1. Client calls:

```java
facade.watchMovie("Interstellar");
```

2. Facade coordinates the subsystem classes.

3. Lights are dimmed.

4. Projector is turned on.

5. Sound system is configured.

6. Movie starts playing.

The client never needs to know these internal details.

## Advantages

* Simplifies client code.
* Reduces dependency on subsystem classes.
* Improves readability and maintainability.
* Encapsulates subsystem workflow.
* Promotes loose coupling between client and subsystem.

## Real-World Analogy

A remote control acts as a Facade for a television system.

Instead of interacting directly with:

* Display hardware
* Audio hardware
* Input controller
* Signal processor

The user simply presses:

```text
Power On
```

The remote internally triggers all required operations.

## Key Takeaway

The Facade Pattern provides a simplified interface to a complex subsystem.

It does not remove access to subsystem classes; it simply offers a convenient way to use them through a single entry point.
