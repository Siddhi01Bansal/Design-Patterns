# Adapter Pattern - Media Player Example

## Definition

The Adapter Pattern is a Structural Design Pattern that allows incompatible interfaces to work together by acting as a translator between them.

## Intent

Convert the interface of a class into another interface that the client expects.

## Problem

Suppose our application is designed to work with a common media player interface:

```java
public interface MediaPlayer {
    void playSong(String fileName);
}
```

However, we want to integrate an existing VLC player library that provides a different method:

```java
public class VLCPlayer {
    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}
```

The client expects:

```java
playSong(fileName)
```

But the VLC player provides:

```java
playVLC(fileName)
```

Since their interfaces are incompatible, they cannot directly communicate.

## Solution

Create an Adapter that implements the expected interface (`MediaPlayer`) and internally delegates the request to the incompatible class (`VLCPlayer`).

```text
playSong()
        ↓
    Adapter
        ↓
playVLC()
```

## Structure

### Target

Interface expected by the client.

```java
MediaPlayer
```

### Adaptee

Existing incompatible class.

```java
VLCPlayer
```

### Adapter

Translates requests from the Target interface to the Adaptee interface.

```java
VLCAdapter
```

### Client

Works only with the Target interface.

```java
Client
```

## Class Diagram

```text
                 Client
                    |
                    v
             +-------------+
             | MediaPlayer |
             +-------------+
                    ^
                    |
             +-------------+
             | VLCAdapter  |
             +-------------+
                    |
                    v
              +-----------+
              | VLCPlayer |
              +-----------+
```

## Implementation Flow

### Step 1: Create Target Interface

```java
public interface MediaPlayer {
    void playSong(String fileName);
}
```

### Step 2: Create Adaptee

```java
public class VLCPlayer {

    public void playVLC(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}
```

### Step 3: Create Adapter

```java
public class VLCAdapter implements MediaPlayer {

    private VLCPlayer vlcPlayer;

    public VLCAdapter(VLCPlayer vlcPlayer) {
        this.vlcPlayer = vlcPlayer;
    }

    @Override
    public void playSong(String fileName) {
        vlcPlayer.playVLC(fileName);
    }
}
```

### Step 4: Client Usage

```java
public class Client {

    public static void main(String[] args) {

        VLCPlayer vlcPlayer =
                new VLCPlayer();

        MediaPlayer mediaPlayer =
                new VLCAdapter(vlcPlayer);

        mediaPlayer.playSong("ShapeOfYou.vlc");
    }
}
```

## Output

```text
Playing VLC file: ShapeOfYou.vlc
```

## How It Works

1. The client requests:

```java
mediaPlayer.playSong("ShapeOfYou.vlc");
```

2. The Adapter receives the request.

3. The Adapter translates the request into:

```java
vlcPlayer.playVLC("ShapeOfYou.vlc");
```

4. The VLC player executes the operation.

The client remains unaware of the VLC player's actual interface.

## Advantages

* Enables integration of legacy or third-party code without modifying existing classes.
* Promotes loose coupling between client and implementation.
* Allows multiple incompatible classes to be used through a common interface.
* Improves code maintainability and extensibility.

## Real-World Analogy

A power adapter allows an Indian charger to work with a US power socket.

```text
Indian Charger
       ↓
   Adapter
       ↓
US Socket
```

The charger and socket remain unchanged; the adapter translates between them.

## Key Takeaway

The Adapter Pattern solves a compatibility problem by allowing two incompatible interfaces to work together through a translator called an Adapter.
