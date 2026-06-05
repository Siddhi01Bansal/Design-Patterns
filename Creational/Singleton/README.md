# Singleton Design Pattern

## Definition

Singleton is a creational design pattern that ensures:

1. Only one instance of a class exists throughout the application
2. A global access point is provided to access that instance

# Why Do We Need Singleton?

Singleton is used when multiple objects managing the same shared resource can lead to inconsistent behavior, unnecessary resource usage, or synchronization problems.

Examples:

* Logger
* Configuration Manager
* Database Connection Manager
* Cache Manager
* Thread Pool
* Printer Spooler

# Logger Example — Problem Statement

Suppose we create multiple logger objects:

```java
Logger l1 = new Logger();
Logger l2 = new Logger();
Logger l3 = new Logger();
```

Possible issues:

* Multiple objects writing to same log file
* Inconsistent log configurations
* Multiple unnecessary file handles
* Synchronization problems during concurrent writes
* Increased memory/resource usage

Since logging should behave consistently across the application, a single shared logger object is preferred.


# Core Idea Behind Singleton

Instead of allowing external classes to freely create objects, the class itself becomes responsible for:

* Creating its object
* Managing its lifecycle
* Providing controlled access


```text
+--------------------------------------------------+
|                     Logger                       |
+--------------------------------------------------+
| - instance : Logger                              |
+--------------------------------------------------+
| - Logger()                                       |
| + getInstance() : Logger                         |
| + log(message : String) : void                   |
+--------------------------------------------------+
```

# Steps To Implement Singleton

## 1. Make Constructor Private

```java
private Logger() {}
```

This prevents external object creation using:

```java
new Logger();
```


## 2. Create Static Instance Variable

```java
private static Logger instance;
```

Static members belong to the class itself rather than individual objects.

---

## 3. Provide Global Access Method

```java
public static Logger getInstance()
```

Since no object exists initially, access must happen through a static method.


# Eager Initialization

## Implementation

```java
class Logger {

    private static final Logger instance = new Logger();

    private Logger() {}

    public static Logger getInstance() {
        return instance;
    }
}
```

## Advantages

* Simple implementation
* Thread-safe because object is created during class loading

## Disadvantages

* Object created even if never used
* Resource wastage in large applications


# Lazy Initialization

## Motivation

Create object only when actually needed.

## Implementation

```java
class Logger {

    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {

        if(instance == null) {
            instance = new Logger();
        }

        return instance;
    }
}
```

## Advantage

* Object created only on first use

## Problem

Not thread-safe.

Two threads may simultaneously enter:

```java
if(instance == null)
```

and create multiple objects.


# Thread-Safe Singleton Using Synchronization

```java
class Logger {

    private static Logger instance;

    private Logger() {}

    public static synchronized Logger getInstance() {

        if(instance == null) {
            instance = new Logger();
        }

        return instance;
    }
}
```

## Advantage

* Thread-safe

## Disadvantage

* Synchronization overhead on every call
* Even after object creation, threads still wait unnecessarily


# Double-Checked Locking (DCL)

## Motivation

Locking is needed only during first object creation.

After object creation, synchronization becomes unnecessary overhead.

## Implementation

```java
class Logger {

    private static volatile Logger instance;

    private Logger() {}

    public static Logger getInstance() {

        if(instance == null) {

            synchronized(Logger.class) {

                if(instance == null) {
                    instance = new Logger();
                }
            }
        }

        return instance;
    }
}
```


# Why Double Checking?

## First Check

```java
if(instance == null)
```

Avoids unnecessary locking after object creation.

## Second Check

Inside synchronized block:

```java
if(instance == null)
```

Prevents multiple object creation when multiple threads are waiting.


# Why `volatile` Is Required?

The statement:

```java
instance = new Logger();
```

is not an atomic operation.

Internally it roughly involves:

1. Allocate memory
2. Initialize object
3. Assign reference to `instance`

Due to instruction reordering, steps may execute as:

1. Allocate memory
2. Assign reference
3. Initialize object

Now another thread may observe a non-null reference pointing to a partially initialized object.

`volatile` prevents this reordering and guarantees visibility across threads.

Thus, other threads can only access a fully initialized Singleton object.


# Advantages Of Singleton

* Controlled access to shared resource
* Saves memory/resources
* Consistent application-wide behavior
* Lazy initialization possible
* Useful for centralized management systems


# Disadvantages Of Singleton

* Introduces global state
* Harder to unit test
* Violates Single Responsibility Principle in some cases
* Tight coupling possible
* Concurrency handling can become complex
* Often overused unnecessarily


# Real World Examples

* Logger
* Runtime class in Java
* Spring Beans (Singleton Scope)
* Configuration Managers
* Cache Managers
* Database Connection Pools


# Interview Discussion Flow

A strong interview explanation should progress like:

1. Why multiple objects are problematic
2. Private constructor
3. Static instance
4. Global access method
5. Eager vs Lazy initialization
6. Race condition in lazy initialization
7. Synchronization
8. Performance issue with synchronized method
9. Double-checked locking
10. Why `volatile` is required


# Interview One-Liner

Singleton ensures that only one instance of a class exists and provides a global access point to that instance.

