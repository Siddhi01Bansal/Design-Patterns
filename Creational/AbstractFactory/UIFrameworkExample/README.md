# Abstract Factory Design Pattern

## Problem

Suppose we are building a cross-platform UI framework.

We need to create multiple related UI components:

* Button
* CheckBox
* TextBox

for different operating systems:

* Windows
* Mac

The challenge is to ensure that all UI components belong to the same family.

For example:

```text
WindowsButton + WindowsCheckBox + WindowsTextBox
```

or

```text
MacButton + MacCheckBox + MacTextBox
```

but never a mixture of both.

---

## Why Abstract Factory?

Factory Method works well when we have a single product hierarchy.

Example:

```text
Notification
├── EmailNotification
├── SMSNotification
└── PushNotification
```

In our UI framework, we have multiple related product hierarchies:

```text
Button
CheckBox
TextBox
```

and these products must be created together as a family.

This is exactly the problem solved by the Abstract Factory Pattern.

---

## Design

### Abstract Products

```text
Button
CheckBox
TextBox
```

### Concrete Products

```text
Button
├── WindowsButton
└── MacButton

CheckBox
├── WindowsCheckBox
└── MacCheckBox

TextBox
├── WindowsTextBox
└── MacTextBox
```

### Abstract Factory

```text
UIFactory
```

### Concrete Factories

```text
WindowsUIFactory
MacUIFactory
```

Each factory is responsible for creating an entire product family.

---

## Class Diagram

```text
                    UIFactory
                         ▲
            ┌────────────┴────────────┐
            │                         │
   WindowsUIFactory          MacUIFactory


Button                    CheckBox                 TextBox
  ▲                           ▲                        ▲
  │                           │                        │
WindowsButton          WindowsCheckBox         WindowsTextBox
MacButton              MacCheckBox             MacTextBox
```

---

## Relationships

### Realization

Concrete products implement their abstractions.

```text
WindowsButton  ──▷ Button
MacButton      ──▷ Button

WindowsCheckBox ──▷ CheckBox
MacCheckBox     ──▷ CheckBox

WindowsTextBox ──▷ TextBox
MacTextBox     ──▷ TextBox
```

Concrete factories implement the abstract factory.

```text
WindowsUIFactory ──▷ UIFactory
MacUIFactory     ──▷ UIFactory
```

### Dependency

Factories depend on concrete products because they instantiate them.

```text
WindowsUIFactory ──► WindowsButton
WindowsUIFactory ──► WindowsCheckBox
WindowsUIFactory ──► WindowsTextBox

MacUIFactory ──────► MacButton
MacUIFactory ──────► MacCheckBox
MacUIFactory ──────► MacTextBox
```

---

## Client Usage

```java
UIFactory factory = new WindowsUIFactory();

Button button = factory.createButton();
CheckBox checkBox = factory.createCheckBox();
TextBox textBox = factory.createTextBox();
```

Switching to Mac:

```java
factory = new MacUIFactory();
```

No changes are required in the client code.

---

## Key Learning

The client depends only on abstractions:

```text
UIFactory
Button
CheckBox
TextBox
```

and remains completely unaware of:

```text
WindowsButton
MacButton
WindowsCheckBox
MacCheckBox
WindowsTextBox
MacTextBox
```

This reduces coupling and keeps object creation centralized.

---

## Open/Closed Principle

### Easy to Add New Families

Adding Linux support:

```text
LinuxButton
LinuxCheckBox
LinuxTextBox
LinuxUIFactory
```

No existing code needs to be modified.

### Difficult to Add New Product Types

Adding:

```text
Dropdown
```

requires:

```text
Modify UIFactory
Modify WindowsUIFactory
Modify MacUIFactory
Add WindowsDropdown
Add MacDropdown
```

This is the primary tradeoff of the Abstract Factory Pattern.

---

## When to Use

Use Abstract Factory when:

* Multiple related product hierarchies exist.
* Products must be created as a family.
* Client code should not depend on concrete implementations.
* Consistency between related products must be enforced.

```
```
