# Flyweight Pattern - Tree Rendering Example

## Definition

The Flyweight Pattern is a Structural Design Pattern that minimizes memory usage by sharing common (intrinsic) state among multiple objects instead of storing duplicate data in every object.

It is particularly useful when an application needs to create a very large number of similar objects.

---

## Intent

Reduce memory consumption by sharing common object state while keeping unique state separate.

---

## Problem

Consider a game that renders millions of trees.

Each tree contains:

* Type
* Color
* Texture
* Height
* X Coordinate
* Y Coordinate

If every tree stores all of this information, then common data such as type, color, texture, and height is duplicated for every tree.

For example:

```text
Tree 1
--------
Oak
Green
OakTexture
10m
(10,20)

Tree 2
--------
Oak
Green
OakTexture
10m
(40,60)

Tree 3
--------
Oak
Green
OakTexture
10m
(80,120)
```

Although only the coordinates differ, the remaining data is stored repeatedly, resulting in unnecessary memory consumption.

---

## Solution

Separate the object's state into two categories:

### Intrinsic State (Shared)

Data that remains the same across multiple objects.

```text
Type
Color
Texture
Height
```

This state is stored inside a Flyweight object (`TreeType`) and shared among all similar trees.

---

### Extrinsic State (Unique)

Data that differs for every object.

```text
X Coordinate
Y Coordinate
```

This state is stored in the `Tree` object and supplied whenever the tree is rendered.

---

## Participants

### Flyweight

```text
TreeType
```

Stores the intrinsic state shared among multiple trees.

---

### Flyweight Factory

```text
TreeTypeFactory
```

Maintains a cache of existing `TreeType` objects.

Responsibilities:

* Return an existing TreeType if available.
* Create a new TreeType only when necessary.
* Reuse existing objects whenever possible.

---

### Context

```text
Tree
```

Stores the extrinsic state and maintains a reference to a shared `TreeType`.

---

### Client

Creates trees and requests shared `TreeType` objects from the factory.

---

## Class Diagram

```text
                    TreeType (Flyweight)
 -------------------------------------------------
 - type : String
 - color : String
 - texture : String
 - height : int
 -------------------------------------------------
 + draw(int x, int y)
 -------------------------------------------------
                     ▲
                     │
                     │ shared by
                     │
             TreeTypeFactory
 -------------------------------------------------
 - Map<String, TreeType>
 -------------------------------------------------
 + getTreeType(...)
 + getTypeCount()
 -------------------------------------------------


                        Tree (Context)
 -------------------------------------------------
 - x : int
 - y : int
 - treeType : TreeType
 -------------------------------------------------
 + draw()
 -------------------------------------------------
```

---

## Structure

```text
Client
   │
   ▼
TreeTypeFactory
   │
   ▼
TreeType (Shared Flyweight)
   ▲
   │
   │ referenced by
   │
Tree
```

The client requests a shared `TreeType` from the factory and creates `Tree` objects containing only the unique state.

---

## How It Works

### Step 1

The client requests a `TreeType`.

```text
Oak
Green
OakTexture
10
```

---

### Step 2

The factory checks its cache.

* If the TreeType already exists, it returns the existing object.
* Otherwise, it creates a new TreeType and stores it in the cache.

---

### Step 3

Each `Tree` stores:

```text
x
y
TreeType reference
```

instead of storing duplicate tree information.

---

### Step 4

When rendering,

```text
Tree.draw()
```

delegates to

```text
TreeType.draw(x, y)
```

where:

* Intrinsic state comes from `TreeType`.
* Extrinsic state comes from `Tree`.

---

## Advantages

* Significantly reduces memory usage.
* Eliminates duplicate object state.
* Improves application performance when handling large numbers of similar objects.
* Reduces pressure on the Garbage Collector by creating fewer objects.
* Promotes object sharing and reuse.
* Follows the Open/Closed Principle.

---

## Disadvantages

* Increases implementation complexity.
* Requires careful separation of intrinsic and extrinsic state.
* Managing shared objects through a factory adds additional design overhead.

---

## Flyweight vs Singleton

| Flyweight                            | Singleton                             |
| ------------------------------------ | ------------------------------------- |
| Shares many similar objects          | Ensures only one object exists        |
| Multiple Flyweight objects may exist | Exactly one instance                  |
| Focuses on memory optimization       | Focuses on controlled object creation |

---

## Flyweight vs Prototype

| Flyweight                      | Prototype                      |
| ------------------------------ | ------------------------------ |
| Reuses existing shared objects | Creates new objects by cloning |
| Optimizes memory               | Optimizes object creation      |
| Objects are shared             | Objects are duplicated         |

---

## Real-World Analogy

Imagine a word processor displaying a document.

Thousands of characters may use the same font:

```text
Arial
Size 12
Bold
Black
```

Instead of every character storing its own font information, all characters share a single font object.

Each character stores only its unique information:

```text
Position
Character value
```

The shared font acts as the Flyweight.

---

## Key Takeaway

The Flyweight Pattern reduces memory consumption by separating shared (intrinsic) state from unique (extrinsic) state.

In this example, all trees of the same type share a single `TreeType` object while each `Tree` stores only its coordinates, allowing millions of trees to be represented efficiently with minimal memory usage.
