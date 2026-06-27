# Iterator Pattern

## Overview
This example demonstrates the Iterator design pattern using a simple playlist. The goal is to separate the responsibility of storing songs from the responsibility of traversing them.

## What is the Iterator Pattern?
The Iterator pattern provides a standard way to access elements of a collection one by one without exposing the internal structure of that collection.

It is useful when:
- a collection should be traversed in a uniform way
- the internal storage format should remain hidden
- different traversal strategies may be needed later

## Why use it?
This pattern helps keep code clean and flexible because it gives you a simple way to move through a collection without tightly coupling the client to the collection's internal structure.

## Advantages
- Clear separation of responsibilities between collection and traversal logic
- Easier to support multiple traversal strategies
- Client code becomes simpler and more consistent
- Internal storage details can remain hidden

## Disadvantages
- Adds one more abstraction layer to the design
- Can feel overkill for very small or simple collections
- If the collection changes while iterating, behavior may become confusing
- Requires careful handling of edge cases such as reaching the end of iteration

## How it works
The design is built around a few clear roles:
- A collection object that can produce an iterator
- An iterator object that manages traversal state
- A client that uses the iterator to access items

In this example:
- the playlist acts as the collection
- the playlist iterator keeps track of the current position
- the client uses the iterator to read songs one by one

## Class Diagram

```text
                    Iterator<T>
 -------------------------------------------------
 + hasNext() : boolean
 + next() : T
 -------------------------------------------------
                ▲
                │
                │ implements
                │
           PlaylistIterator
 -------------------------------------------------
 - playlist : Playlist
 - currentIndex : int
 -------------------------------------------------
 + hasNext()
 + next()
 -------------------------------------------------

                     Playlist
 -------------------------------------------------
 - songs : List<Song>
 -------------------------------------------------
 + addSong()
 + createIterator()
 -------------------------------------------------

                      Song
 -------------------------------------------------
 - title : String
 -------------------------------------------------
 + toString()
 -------------------------------------------------
```

## Iterator vs Similar Patterns

### Iterator vs Factory
Although both patterns can result in object creation, they solve different problems.
- A Factory creates objects based on a need or a decision.
- An Iterator does not create new domain objects; it provides a way to move through an existing collection.

In short, Factory is about object creation, while Iterator is about traversal.

### Iterator vs Composite
These patterns can appear related because both may deal with groups of objects.
- A Composite organizes objects into a tree-like structure.
- An Iterator traverses a collection or structure and visits its elements one by one.

So, Composite is about structure, while Iterator is about access and navigation.

## Learning Flow
1. The playlist stores songs internally.
2. The playlist creates an iterator when requested.
3. The iterator keeps track of the current position.
4. The client asks whether more items exist.
5. The client retrieves the next item until traversal is complete.

## Edge Cases to Understand
### 1. Empty collection
If the playlist has no songs, iteration should stop immediately.

### 2. Reaching the end of iteration
Calling the next step after the last item should not produce an invalid result. A safe design should handle this explicitly.

### 3. Changing the collection during iteration
If items are added or removed while iterating, behavior may become inconsistent. This is a common design concern.

### 4. Null or invalid values
If a collection may contain empty or invalid entries, the iterator should not assume every value is usable.

### 5. Multiple traversal passes
A collection may need to be traversed more than once. The iterator pattern supports this by creating a fresh iterator when needed.

## Why this design is clean
This example keeps the collection logic and traversal logic separate. That makes the code easier to understand, maintain, and extend.

## Key takeaway
The Iterator pattern is about providing a simple and consistent way to walk through a collection while keeping the collection's internal details hidden.

## Real-world analogy
Think of a playlist as a box of songs. The iterator is like a helper that points to the current song and moves forward one step at a time without exposing the whole box structure.
