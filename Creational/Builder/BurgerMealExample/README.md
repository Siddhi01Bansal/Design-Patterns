# Builder Design Pattern

## Problem

Suppose we are building a burger ordering system.

A `BurgerMeal` contains:

### Mandatory Field

```java
PattyType patty
```

### Optional Fields

```java
boolean cheese
boolean fries
ColdDrink coldDrink
```

A traditional constructor-based approach would look like:

```java
new BurgerMeal(
    PattyType.VEG,
    true,
    false,
    ColdDrink.PEPSI
);
```

As the number of optional fields grows, constructors become difficult to read, maintain, and extend.

---

## Problems with Constructors

### 1. Poor Readability

```java
new BurgerMeal(
    PattyType.VEG,
    true,
    false,
    null
);
```

It is difficult to understand what each value represents.

---

### 2. Constructor Explosion

As new optional fields are added:

```java
extraSauce
dessert
mealSize
bunType
```

constructors become increasingly large and difficult to maintain.

---

### 3. Invalid Object States

Using setters:

```java
BurgerMeal meal = new BurgerMeal();
meal.addCheese();
meal.addFries();
```

allows an object to exist in an incomplete state before all required fields are provided.

---

## Why Builder?

Builder separates:

```text
Object Configuration
```

from

```text
Object Creation
```

The object is only created after all required configuration has been completed.

```text
Create Builder
        ↓
Configure Builder
        ↓
Validate Configuration
        ↓
Build Final Object
```

This ensures the final object is always fully initialized.

---

## Design

### Product

```java
BurgerMeal
```

Contains:

```java
private final PattyType patty;
private final boolean cheese;
private final boolean fries;
private final ColdDrink coldDrink;
```

The object is immutable after creation.

---

### Builder

```java
BurgerMeal.Builder
```

Responsible for:

* Collecting configuration
* Storing temporary state
* Performing validation
* Creating the final object

---

## Class Diagram

```text
                    BurgerMeal
 -------------------------------------------------
 - patty : PattyType
 - cheese : boolean
 - fries : boolean
 - coldDrink : ColdDrink
 -------------------------------------------------
 - BurgerMeal(Builder)
 -------------------------------------------------

                         ▲
                         │ creates

                 Builder (static)
 -------------------------------------------------
 - patty : PattyType
 - cheese : boolean
 - fries : boolean
 - coldDrink : ColdDrink
 -------------------------------------------------
 + addCheese()
 + addFries()
 + addColdDrink()
 + build()
 -------------------------------------------------
```

---

## Relationships

### Composition

```text
BurgerMeal ◼── Builder
```

The final object is created using data stored inside the Builder.

---

### Dependency

```text
Builder ──► BurgerMeal
```

The Builder depends on BurgerMeal because it creates instances of it.

---

## Implementation Flow

### Create Builder

```java
BurgerMeal.Builder builder =
        new BurgerMeal.Builder(PattyType.VEG);
```

### Configure

```java
builder.addCheese()
       .addColdDrink(ColdDrink.PEPSI);
```

### Build

```java
BurgerMeal meal = builder.build();
```

---

## Method Chaining

Builder methods return the Builder object itself.

Example:

```java
public Builder addCheese() {
    this.cheese = true;
    return this;
}
```

This enables:

```java
new BurgerMeal.Builder(PattyType.VEG)
        .addCheese()
        .addFries()
        .build();
```

---

## Why Nested Static Builder?

Instead of:

```java
BurgerBuilder
```

we use:

```java
BurgerMeal.Builder
```

because:

* Builder belongs exclusively to BurgerMeal.
* Better encapsulation.
* Clear ownership.
* Cleaner API.
* Can access BurgerMeal's private constructor.

Example:

```java
new BurgerMeal.Builder(PattyType.VEG)
```

---

## Why Static?

If Builder were not static:

```java
BurgerMeal.Builder builder =
        meal.new Builder();
```

A BurgerMeal instance would be required before creating the Builder.

This defeats the purpose because the Builder is responsible for creating the BurgerMeal.

Making the Builder static allows:

```java
new BurgerMeal.Builder(...)
```

without first creating a BurgerMeal object.

---

## Validation

Business rules should be validated inside:

```java
build()
```

Example:

```java
public BurgerMeal build() {
    if (!fries && coldDrink == null) {
        throw new IllegalStateException(
            "Meal must contain either Fries or a Cold Drink"
        );
    }

    return new BurgerMeal(this);
}
```

The final object is only created if all rules are satisfied.

---

## Advantages

* Improves readability.
* Eliminates telescoping constructors.
* Supports immutable objects.
* Provides step-by-step object creation.
* Centralizes validation logic.
* Easy to extend with new optional fields.

---

## Disadvantages

* More code than a simple constructor.
* Additional Builder class increases complexity.
* Overkill for small objects with very few fields.

---

## When to Use

Use Builder when:

* Objects contain many fields.
* Some fields are mandatory and others optional.
* Constructors become difficult to read.
* Immutable objects are preferred.
* Complex validation is required before object creation.

---

## When Not to Use

Avoid Builder when:

* Objects contain very few fields.
* All fields are mandatory.
* A simple constructor remains readable.

Example:

```java
Employee employee =
        new Employee(id, name);
```

Builder would add unnecessary complexity here.

---

## Example Usage

```java
BurgerMeal meal =
        new BurgerMeal.Builder(PattyType.VEG)
                .addCheese()
                .addColdDrink(ColdDrink.PEPSI)
                .build();

System.out.println(meal);
```

### Output

```text
BurgerMeal{
    patty=VEG,
    cheese=true,
    fries=false,
    coldDrink=PEPSI
}
```

---

## Key Learning

The Builder Pattern is not about method chaining.

The core idea is:

> Construct a complex object step-by-step while ensuring the final object is created only after all required configuration and validation are complete.
