# Decorator Pattern - Coffee Shop Example

## Definition

The Decorator Pattern is a Structural Design Pattern that allows behavior or responsibilities to be added to objects dynamically without modifying their existing code.

It provides a flexible alternative to inheritance for extending functionality.

---

## Intent

Attach additional responsibilities to an object dynamically.

---

## Problem

Consider a coffee shop where customers can customize their coffee with add-ons such as:

* Milk
* Sugar
* Cream

If inheritance is used, we may end up creating classes for every possible combination:

```text
SimpleCoffee
MilkCoffee
SugarCoffee
CreamCoffee

MilkSugarCoffee
MilkCreamCoffee
SugarCreamCoffee

MilkSugarCreamCoffee
...
```

As new add-ons are introduced, the number of classes grows rapidly, leading to the **Class Explosion Problem**.

This approach becomes difficult to maintain and extend.

---

## Solution

Instead of creating subclasses for every combination, decorators wrap existing objects and add new behavior at runtime.

A coffee can be decorated with multiple add-ons by wrapping it with multiple decorators.

Example:

```text
Simple Coffee
      ↓
+ Milk
      ↓
+ Sugar
      ↓
+ Cream
```

Each decorator adds its own behavior while delegating existing behavior to the wrapped object.

This allows unlimited combinations without creating new classes for every possible configuration.

---

## Participants

### Component

Common interface shared by both concrete objects and decorators.

```text
Coffee
```

### Concrete Component

The base object being decorated.

```text
SimpleCoffee
```

### Decorator

Abstract class that stores a reference to another Coffee object.

```text
CoffeeDecorator
```

### Concrete Decorators

Add specific responsibilities.

```text
MilkDecorator
SugarDecorator
CreamDecorator
```

### Client

Creates customized coffee orders by combining decorators.

```text
Client
```

---

## Class Diagram

```text
                           Coffee (Interface)
 -------------------------------------------------
 + getDescription() : String
 + getCost() : int
 -------------------------------------------------
                  ▲
                  │ implements
                  │
         -------------------------
         │                       │
         │                       │
         ▼                       ▼

              SimpleCoffee

 -------------------------------------------------
 + getDescription()
 + getCost()
 -------------------------------------------------


                  ▲
                  │ extends
                  │

             CoffeeDecorator
 -------------------------------------------------
 # coffee : Coffee
 -------------------------------------------------
 + CoffeeDecorator(Coffee)
 -------------------------------------------------
                  ▲
                  │
      -----------------------------
      │             │             │
      │             │             │
      ▼             ▼             ▼

    MilkDecorator  SugarDecorator  CreamDecorator

 -------------------------------------------------
 + getDescription()
 + getCost()
 -------------------------------------------------
```

---

## Decoration Structure

```text
Coffee
   ▲
   │
SimpleCoffee

CoffeeDecorator
-----------------------
# coffee : Coffee
-----------------------

MilkDecorator
SugarDecorator
CreamDecorator

Each decorator HAS-A Coffee object

MilkDecorator
      │
      ▼
   Coffee

SugarDecorator
      │
      ▼
   Coffee

CreamDecorator
      │
      ▼
   Coffee
```

---

## How It Works

Suppose we create the following order:

```text
CreamDecorator
        ↓
SugarDecorator
        ↓
MilkDecorator
        ↓
SimpleCoffee
```

Execution flow:

1. The client calls a method on the outermost decorator.
2. The decorator delegates the call to the wrapped Coffee object.
3. The wrapped object performs its work.
4. The decorator adds its own behavior.
5. The result is returned back through the chain.

This recursive delegation allows decorators to be stacked dynamically.

---

## Sample Output

```text
Coffee Cost with order details:
Simple Coffee is: 100

Coffee Cost with order details:
Simple Coffee + Milk + Sugar is: 130

Coffee Cost with order details:
Simple Coffee + Milk + Milk + Sugar + Cream is: 160
```

---

## Advantages

* Avoids class explosion.
* Supports dynamic behavior addition.
* Follows the Open/Closed Principle.
* More flexible than inheritance.
* Decorators can be combined in any order.
* Existing code remains unchanged.
* New decorators can be added without modifying existing classes.

---

## Decorator vs Inheritance

| Inheritance                            | Decorator                                 |
| -------------------------------------- | ----------------------------------------- |
| Behavior added through subclasses      | Behavior added through object composition |
| Leads to many subclasses               | Reuses a small set of decorators          |
| Fixed at compile time                  | Can be changed at runtime                 |
| Difficult to support many combinations | Easy to combine multiple features         |

---

## Real-World Analogy

When ordering coffee, you start with a basic coffee and add extras such as milk, sugar, or cream.

```text
Simple Coffee
      ↓
+ Milk
      ↓
+ Sugar
      ↓
+ Cream
```

Each add-on enhances the same coffee rather than requiring a separate coffee type for every combination.

---

## Key Takeaway

The Decorator Pattern allows behavior to be added to objects dynamically by wrapping them with decorator objects that implement the same interface.

It solves the class explosion problem and provides a flexible alternative to inheritance by combining **composition** and **delegation**.
