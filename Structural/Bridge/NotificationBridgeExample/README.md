# Bridge Pattern - Notification System Example

## Definition

The Bridge Pattern is a Structural Design Pattern that separates an abstraction from its implementation so that both can vary independently.

Instead of creating subclasses for every possible combination, the pattern uses composition to connect two independent hierarchies.

---

## Intent

Decouple an abstraction from its implementation so that both can evolve independently.

---

## Problem

Suppose we are building a notification system.

We have different notification types:

```text
Email Notification
SMS Notification
Push Notification
```

and different notification providers:

```text
Twilio
AWS SNS
Firebase
```

A naive inheritance-based design may create classes such as:

```text
EmailTwilioNotification
EmailSNSNotification
EmailFirebaseNotification

SMSTwilioNotification
SMSSNSNotification
SMSFirebaseNotification

PushTwilioNotification
PushSNSNotification
PushFirebaseNotification
```

As both dimensions grow, the number of classes grows rapidly.

This leads to class explosion and makes the system difficult to maintain.

---

## Solution

Separate the two dimensions into independent hierarchies:

### Abstraction Hierarchy

```text
Notification
├── EmailNotification
└── SMSNotification
```

### Implementation Hierarchy

```text
NotificationSender
├── TwilioSender
└── SNSSender
```

The abstraction maintains a reference to the implementation.

```text
Notification
    HAS-A
NotificationSender
```

This allows any notification type to work with any sender implementation without creating additional classes.

---

## Participants

### Abstraction

```text
Notification
```

Defines the high-level abstraction and contains a reference to a NotificationSender.

### Refined Abstractions

```text
EmailNotification
SMSNotification
```

Provide specialized notification behavior.

### Implementor

```text
NotificationSender
```

Defines the interface for sending notifications.

### Concrete Implementors

```text
TwilioSender
SNSSender
```

Provide concrete implementations for sending messages.

### Client

Creates notification objects and chooses the sender implementation at runtime.

---

## Class Diagram

```text
                          Notification
 -------------------------------------------------
 # notificationSender : NotificationSender
 -------------------------------------------------
 + send(String message)
 -------------------------------------------------
                           ▲
                           │
          --------------------------------
          │                              │
          ▼                              ▼

   EmailNotification           SMSNotification


                HAS-A
                   │
                   ▼

                 NotificationSender
 -------------------------------------------------
 + sendMessage(String message)
 -------------------------------------------------
                           ▲
                           │
          --------------------------------
          │                              │
          ▼                              ▼

            TwilioSender          SNSSender
```

---

## Structure

```text
Client
   │
   ▼
Notification
   │
   ▼
NotificationSender
```

The client works with the abstraction while the abstraction delegates work to the implementation.

---

## How It Works

### Step 1

The client chooses a sender implementation.

```text
TwilioSender
```

or

```text
SNSSender
```

---

### Step 2

The client creates a notification object.

```text
EmailNotification
```

or

```text
SMSNotification
```

---

### Step 3

The notification delegates the actual sending operation to the sender.

```text
notification.send()
        ↓
notificationSender.sendMessage()
```

---

### Step 4

The sender implementation performs the actual work.

---

## Sample Output

```text
[Email Notification]
AWS SNS Sender -> Sending message: Hello! Welcome to CarrerGrow

[SMS Notification]
Twilio Sender -> Sending message: Your OTP to login is 2311

Switching provider...

[Email Notification]
Twilio Sender -> Sending message: Your account has been verified.
```

---

## Advantages

* Prevents class explosion.
* Separates abstraction from implementation.
* Both hierarchies can evolve independently.
* Promotes composition over inheritance.
* Improves maintainability and flexibility.
* Follows the Open/Closed Principle.

---

## Bridge vs Adapter

| Bridge                                    | Adapter                                     |
| ----------------------------------------- | ------------------------------------------- |
| Designed upfront                          | Usually introduced later                    |
| Separates two independent hierarchies     | Makes incompatible interfaces work together |
| Uses composition to avoid class explosion | Uses translation between interfaces         |
| Focuses on extensibility                  | Focuses on compatibility                    |

---

## Real-World Analogy

Consider a TV and a remote control.

```text
Remote Control
    HAS-A
TV
```

Different remotes can work with different TVs.

```text
Basic Remote + Sony TV
Basic Remote + Samsung TV
Advanced Remote + Sony TV
Advanced Remote + Samsung TV
```

The remote hierarchy and TV hierarchy evolve independently.

This is a classic example of the Bridge Pattern.

---

## Key Takeaway

The Bridge Pattern separates an abstraction from its implementation using composition.

In this example, notification types and notification providers evolve independently, avoiding class explosion and making the system flexible and maintainable.
