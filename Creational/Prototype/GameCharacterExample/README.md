# Prototype Design Pattern - Game Character Example

## Overview

The Prototype Pattern is a Creational Design Pattern that creates new objects by copying existing objects (prototypes) instead of creating them from scratch.

This pattern is useful when object creation is expensive, complex, or when many similar objects need to be created with slight modifications.

In this example, a game character can be cloned along with its weapon. The cloned character can then be customized independently without affecting the original object.

---

## Problem

Suppose a game contains multiple characters with similar configurations:

```java
Character warrior = new Character(
    "Warrior",
    100,
    new Weapon("Iron Sword", 50)
);
```

Creating every character from scratch can become repetitive and expensive, especially when the objects contain many nested objects and configuration details.

Example:

```java
Character warrior1 = new Character(...);
Character warrior2 = new Character(...);
Character warrior3 = new Character(...);
```

This leads to duplicated initialization logic and tightly couples clients to object creation details.

---

## Solution

Create a fully configured prototype object once and generate new objects by cloning it.

```java
Character warrior = new Character(
    "Warrior",
    100,
    new Weapon("Iron Sword", 50)
);

Character eliteWarrior = warrior.clone();
```

The cloned object can then be customized as needed.

```java
eliteWarrior.setName("Elite Warrior");
eliteWarrior.getWeapon().setDamage(100);
```

---

## Key Idea

Instead of asking:

```text
How do I create a new object?
```

Prototype asks:

```text
How do I copy an existing object?
```

The object itself knows how to create its copy.

---

## Structure

### Prototype Interface

Defines a cloning contract that all cloneable objects must implement.

```java
public interface Prototype<T> {
    T clone();
}
```

---

### Weapon

A concrete prototype responsible for cloning itself.

```java
public class Weapon implements Prototype<Weapon> {
    ...
}
```

---

### Character

A concrete prototype that contains a nested Weapon object.

```java
public class Character implements Prototype<Character> {
    ...
}
```

Character delegates cloning of its weapon to the Weapon class.

```java
this.weapon.clone();
```

---

### Prototype Registry

Stores pre-configured prototype objects and returns cloned copies on demand.

```java
PrototypeRegistry<Character> registry =
        new PrototypeRegistry<>();
```

---

## Deep Copy vs Shallow Copy

### Shallow Copy

Copies object references.

```text
Character A
      |
      +----> Weapon

Character B
      |
      +----> Same Weapon
```

Modifying the weapon through one character affects the other.

---

### Deep Copy

Creates independent copies of nested objects.

```text
Character A
      |
      +----> Weapon A

Character B
      |
      +----> Weapon B
```

Changes made to one weapon do not affect the other.

This implementation performs a deep copy by delegating cloning responsibilities to nested objects.

---

## Delegation of Responsibility

Character does not know how a Weapon should be cloned.

Instead of:

```java
new Weapon(
    weapon.getName(),
    weapon.getDamage()
);
```

Character delegates cloning:

```java
weapon.clone();
```

Benefits:

* Better encapsulation
* Lower coupling
* Easier maintenance
* Follows Single Responsibility Principle

Each object is responsible for cloning itself.

---

## Prototype Registry

The Prototype Registry stores ready-made prototype objects.

Registration:

```java
registry.register("warrior", warrior);
```

Retrieval:

```java
Character eliteWarrior =
        registry.get("warrior");
```

Internally:

```text
Registry
    ↓
Find Prototype
    ↓
Clone Prototype
    ↓
Return Copy
```

Clients do not need to know how cloning is performed.

---

## UML Representation

```text
                 +------------------+
                 |   Prototype<T>   |
                 +------------------+
                 | + clone(): T     |
                 +------------------+
                          ▲
                          |
          --------------------------------
          |                              |
          |                              |
+-------------------+      +----------------------+
|      Weapon       |      |      Character       |
+-------------------+      +----------------------+
| - name            |      | - name               |
| - damage          |      | - health             |
+-------------------+      | - weapon: Weapon     |
| + clone()         |      +----------------------+
+-------------------+      | + clone()            |
                           +----------------------+

                  +----------------------+
                  | PrototypeRegistry<T> |
                  +----------------------+
                  | register()           |
                  | get()                |
                  +----------------------+
```

---

## Example

```java
Character warrior = new Character(
    "Warrior",
    100,
    new Weapon("Iron Sword", 50)
);

registry.register("warrior", warrior);

Character eliteWarrior =
        registry.get("warrior");

eliteWarrior.setName("Elite Warrior");
eliteWarrior.getWeapon().setDamage(100);
```

Output:

```text
Original: Character{name='Warrior', health=100, weapon=Weapon{name='Iron Sword', damage=50}}

Clone: Character{name='Elite Warrior', health=100, weapon=Weapon{name='Iron Sword', damage=100}}
```

The original character remains unchanged, proving that a deep copy was created.

---

## Advantages

* Reduces expensive object creation.
* Eliminates repetitive initialization code.
* Hides cloning logic from clients.
* Supports deep copying through delegation.
* Easy to add new prototype types.
* Improves flexibility when creating similar objects.

---

## Disadvantages

* Deep cloning can become complex for large object graphs.
* Circular references require special handling.
* Every class must define proper cloning behavior.
* Incorrect cloning can lead to shared mutable state.

---

## When to Use

Use the Prototype Pattern when:

* Object creation is expensive.
* Multiple similar objects are required.
* Creating objects through constructors is cumbersome.
* The system should be independent of concrete object creation logic.
* Deep copying of configured objects is preferred over rebuilding them.

---

## Prototype vs Factory Method

| Factory Method                     | Prototype                     |
| ---------------------------------- | ----------------------------- |
| Creates objects using construction | Creates objects using cloning |
| Relies on object creation logic    | Relies on object duplication  |
| Uses `new` internally              | Uses `clone()` internally     |
| Focuses on object creation         | Focuses on object copying     |

### Summary

Factory Method:

```text
Create a new object.
```

Prototype:

```text
Copy an existing object.
```
