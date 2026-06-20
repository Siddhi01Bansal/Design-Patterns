# Composite Pattern - File System Example

## Definition

The Composite Pattern is a Structural Design Pattern that allows individual objects and compositions of objects to be treated uniformly through a common interface.

It is particularly useful for representing hierarchical tree structures where both individual objects and groups of objects should be handled in the same way.

---

## Intent

Compose objects into tree structures to represent part-whole hierarchies and allow clients to treat individual objects and compositions uniformly.

---

## Problem

Consider a File System consisting of:

* Files
* Folders

A folder can contain:

* Files
* Other folders

which can themselves contain more files and folders.

Without a common abstraction, the client must constantly distinguish between:

```text
File
Folder
```

and write separate logic for each.

For example:

```text
If File → return its size

If Folder → recursively calculate the size of all children
```

This makes the client code complex and tightly coupled to concrete implementations.

---

## Solution

Create a common interface for both files and folders.

```text
FileSystemItem
```

Both File and Folder implement this interface.

The client interacts only with the common abstraction and does not need to know whether it is working with:

```text
File
```

or

```text
Folder
```

A Folder stores a collection of FileSystemItem objects, allowing recursive nesting to any depth.

---

## Participants

### Component

Defines the common interface for all objects in the hierarchy.

```text
FileSystemItem
```

### Leaf

Represents individual objects that cannot contain children.

```text
File
```

### Composite

Represents objects that can contain children.

```text
Folder
```

### Client

Works with the Component interface and treats files and folders uniformly.

```text
Client
```

---

## Class Diagram

```text
                    FileSystemItem (Interface)
 -------------------------------------------------
 + getSize() : int
 + delete() : void
 + printStructure(indent) : void
 -------------------------------------------------
                           ▲
                           │
           ---------------------------------
           │                               │
           │                               │
           ▼                               ▼

                         File
 -------------------------------------------------
 - name : String
 - size : int
 -------------------------------------------------
 + getSize()
 + delete()
 + printStructure()
 -------------------------------------------------


                        Folder
 -------------------------------------------------
 - name : String
 - children : List<FileSystemItem>
 -------------------------------------------------
 + add(item)
 + remove(item)
 + getSize()
 + delete()
 + printStructure()
 -------------------------------------------------
```

---

## Structure

Example hierarchy:

```text
Root
│
├── Resume.pdf
├── Photo.jpg
│
└── Documents
    ├── Notes.txt
    └── Java.pdf
```

Since Folder stores:

```text
List<FileSystemItem>
```

it can contain both:

```text
File
Folder
```

allowing recursive tree structures.

---

## How It Works

### Calculating Size

For a File:

```text
getSize()
    ↓
returns its own size
```

For a Folder:

```text
getSize()
    ↓
sum(child.getSize())
```

The Folder recursively calculates the total size of all its children.

---

### Printing Structure

The Folder delegates the operation to its children while increasing indentation.

This produces a tree-like representation of the file system.

Example:

```text
+ Root
    - Resume.pdf
    - Photo.jpg
    + Documents
        - Notes.txt
        - Java.pdf
```

---

## Sample Output

```text
+ Root
    - Resume.pdf (10 MB)
    - Photo.jpg (20 MB)
    + Documents
        - Notes.txt (5 MB)
        - Java.pdf (15 MB)

Total Size: 50 MB
```

---

## Advantages

* Treats individual objects and groups uniformly.
* Simplifies client code.
* Makes recursive tree structures easy to implement.
* Supports hierarchical object structures.
* Follows the Open/Closed Principle.
* New component types can be added without modifying existing code.

---

## Composite vs Decorator

| Composite                         | Decorator                         |
| --------------------------------- | --------------------------------- |
| Builds tree structures            | Adds responsibilities dynamically |
| Represents part-whole hierarchies | Enhances object behavior          |
| Focuses on hierarchy              | Focuses on functionality          |
| Contains child components         | Wraps another component           |

---

## Real-World Analogy

A company's organizational structure.

```text
CEO
│
├── Engineering Department
│   ├── Developer A
│   └── Developer B
│
└── HR Department
    └── Recruiter
```

A department can contain employees as well as other departments, forming a hierarchy.

The organization can be traversed uniformly regardless of whether a node represents a single employee or an entire department.

---

## Key Takeaway

The Composite Pattern allows clients to treat individual objects (Leafs) and groups of objects (Composites) uniformly through a common interface.

It is ideal for representing recursive tree-like structures such as file systems, organizational hierarchies, UI component trees, and menu systems.
