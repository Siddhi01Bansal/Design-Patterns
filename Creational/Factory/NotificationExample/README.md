# Factory Method Design Pattern - Notification System

## Overview

This project demonstrates the **Factory Method Design Pattern** using a Notification System.

The application supports multiple notification channels:

* Email Notification
* SMS Notification
* Push Notification

Instead of allowing the client to directly create concrete notification objects, object creation is delegated to specialized creator classes.

This helps reduce the client's dependency on concrete implementations and follows the **Open/Closed Principle (OCP)**.

---

## Problem Statement

Consider a notification service where the client directly creates notification objects.

### Naive Design

```java
Notification notification = new EmailNotification();
notification.sendNotification("Welcome!");
```

As the number of notification channels grows, the client becomes tightly coupled to concrete classes:

```java
new EmailNotification();
new SMSNotification();
new PushNotification();
```

Adding a new notification type requires modifying client code.

---

## Solution: Factory Method Pattern

The Factory Method Pattern moves object creation responsibility from the client to specialized creator classes.

The client interacts with abstractions instead of concrete implementations.

### Product Hierarchy

```text
Notification
   ↑
   |
-----------------------
|       |       |             
Email   SMS    Push      
```

### Creator Hierarchy

```text
NotificationCreator
        ↑
        |
----------------------------
|         |          |                     
Email   SMS       Push              
Creator Creator   Creator           
```

Each concrete creator knows how to create its corresponding concrete product.

---

## Class Diagram

```text
                        <<interface>>
                        Notification
                  + sendNotification(msg)
                               ▲
                               │
        ┌──────────────────────┼──────────────────────┐
        │                      │                      │
        │                      │                      │
EmailNotification    SMSNotification     PushNotification
                                              


                       <<abstract>>
                    NotificationCreator
      + sendNotification(message)
      # createNotification()

                               ▲
                               │
       ┌───────────────────────┼────────────────────────┐
       │                       │                        │
       │                       │                        │
EmailNotificationCreator   SMSNotificationCreator   PushNotificationCreator


```

### Dependencies

```text
NotificationCreator ---------> Notification

EmailNotificationCreator ----> EmailNotification

SMSNotificationCreator ------> SMSNotification

PushNotificationCreator -----> PushNotification
```

---

## Key Idea

The abstract creator defines the workflow:

```java
sendNotification(message)
```

while delegating object creation to:

```java
createNotification()
```

This allows subclasses to decide which notification object should be created.

---

## Open/Closed Principle

Suppose a new notification type is introduced:

```text
SlackNotification
SlackNotificationCreator
```

Existing classes remain unchanged.

We only add new classes:

```text
+ SlackNotification
+ SlackNotificationCreator
```

This makes the system open for extension but closed for modification.

---

## Advantages

* Reduces client dependency on concrete classes.
* Encapsulates object creation logic.
* Promotes extensibility.
* Supports Open/Closed Principle.
* Improves maintainability.

---

## Drawback

The pattern introduces additional creator classes.

For N notification types:

```text
N Concrete Products
+
N Concrete Creators
```

This can increase the total number of classes significantly.

For example:

```text
100 Notification Types
=
100 Product Classes
+
100 Creator Classes
+
1 Product Interface
+
1 Abstract Creator
=
202 Classes
```

In such cases, alternative approaches such as Simple Factory, Abstract Factory, Dependency Injection, or Registries may be considered.

---

## Learning Outcome

While Factory Method reduces coupling, it does not eliminate it completely.

The coupling is shifted from:

```java
new EmailNotification()
```

to:

```java
new EmailNotificationCreator()
```

Some part of the application must still decide which creator to instantiate.

The pattern localizes object creation and makes the system easier to extend without modifying existing code.

---

## Pattern Category

**Creational Design Pattern**

Factory Method focuses on:

```text
Object Creation
```

while allowing subclasses to decide which concrete object should be instantiated.
