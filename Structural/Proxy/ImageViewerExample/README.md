# Proxy Pattern - Image Viewer Example

## Definition

The Proxy Pattern is a Structural Design Pattern that provides a placeholder or surrogate object that controls access to another object.

The proxy acts as an intermediary between the client and the real object, allowing additional logic such as lazy loading, access control, caching, logging, or remote communication.

---

## Intent

Provide a substitute or placeholder for another object to control access to it.

---

## Problem

Consider an image viewer application that displays large images.

Loading an image from disk is an expensive operation because it may involve:

* Reading data from storage
* Allocating memory
* Decoding image content

If all images are loaded immediately when the application starts, startup time and memory usage increase significantly, even for images that the user may never open.

Example:

```text
Gallery
├── vacation.jpg
├── mountain.jpg
├── sunset.jpg
└── ...
```

Loading every image upfront is inefficient.

---

## Solution

Introduce a Proxy object that represents the image.

The proxy stores lightweight information such as:

* File name
* File size

and delays creation of the actual image object until it is truly needed.

When the client requests the image for the first time:

```text
display()
```

the proxy creates the real image, loads it from disk, and delegates the request.

Subsequent requests reuse the already loaded image.

This technique is known as **Lazy Loading**.

---

## Participants

### Subject

Defines the common interface used by both the real object and the proxy.

```text
Image
```

### Real Subject

The actual object that performs the expensive work.

```text
RealImage
```

Responsibilities:

* Load image from disk
* Display image

### Proxy

Controls access to the RealImage.

```text
ImageProxy
```

Responsibilities:

* Store lightweight metadata
* Create RealImage only when required
* Delegate requests to RealImage

### Client

Interacts only with the Subject interface.

```text
Client
```

---

## Class Diagram

```text
                          Image (Interface)
 -------------------------------------------------
 + display() : void
 + getFileSize() : String
 -------------------------------------------------
                   ▲
                   │
        -------------------------
        │                       │
        │                       │
        ▼                       ▼

                    RealImage
 -------------------------------------------------
 - fileName : String
 - fileSize : String
 -------------------------------------------------
 + display()
 + getFileSize()
 -------------------------------------------------


                    ImageProxy
 -------------------------------------------------
 - fileName : String
 - fileSize : String
 - realImage : RealImage
 -------------------------------------------------
 + display()
 + getFileSize()
 -------------------------------------------------
```

---

## Structure

```text
Client
   │
   ▼
Image (Interface)
   ▲
   │
ImageProxy
   │
   ▼
RealImage
```

The client communicates only with the Image interface.

The proxy decides whether the RealImage should be created.

---

## How It Works

### Step 1

The client creates a proxy.

```text
Image image = new ImageProxy(...)
```

At this point:

```text
RealImage = null
```

No image is loaded.

---

### Step 2

The client requests metadata.

```text
getFileSize()
```

The proxy returns the information directly without creating the RealImage.

---

### Step 3

The client requests the image.

```text
display()
```

The proxy checks:

```text
Is RealImage already created?
```

If not:

```text
Create RealImage
Load image from disk
Display image
```

---

### Step 4

Future requests reuse the already loaded image.

```text
display()
```

No additional loading occurs.

---

## Sample Output

```text
File Size: 10MB

First display request:
[Proxy] Image not loaded yet. Creating RealImage...

Loading image from disk: vacation.jpg
Image loaded successfully.

Displaying image: vacation.jpg

Second display request:
[Proxy] Reusing already loaded image.

Displaying image: vacation.jpg
```

---

## Advantages

* Supports lazy loading.
* Reduces startup time.
* Saves memory by creating expensive objects only when needed.
* Provides access control.
* Hides implementation details from the client.
* Can add caching, logging, validation, and security checks.
* Follows the Open/Closed Principle.

---

## Proxy vs Decorator

| Proxy                                          | Decorator                            |
| ---------------------------------------------- | ------------------------------------ |
| Controls access to an object                   | Adds new behavior to an object       |
| Focuses on object management                   | Focuses on functionality enhancement |
| Often used for lazy loading, security, caching | Often used for feature composition   |
| Client receives the same behavior              | Client receives enhanced behavior    |

---

## Types of Proxy

### Virtual Proxy

Creates expensive objects only when required.

Example:

```text
Image Viewer
```

### Protection Proxy

Controls access based on permissions.

Example:

```text
Admin Panel Access
```

### Remote Proxy

Represents an object located on another machine.

Example:

```text
RPC / Distributed Systems
```

### Caching Proxy

Stores previously computed results.

Example:

```text
API Response Cache
```

---

## Real-World Analogy

Consider a hotel receptionist.

The guest does not directly interact with every hotel service.

Instead, the receptionist acts as an intermediary and decides when to contact the appropriate service.

```text
Guest
   ↓
Receptionist (Proxy)
   ↓
Hotel Service
```

Similarly, a proxy controls access to the real object.

---

## Key Takeaway

The Proxy Pattern provides a placeholder object that controls access to another object.

In this example, ImageProxy delays creation of RealImage until the image is actually requested, improving performance through lazy loading while keeping the client unaware of the underlying complexity.
