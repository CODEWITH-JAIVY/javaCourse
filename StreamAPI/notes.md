# ☕ Java Stream API — Complete Beginner Notes

> A comprehensive reference guide covering Stream basics, pipeline stages, operations, and hands-on examples.

---

## 📚 Table of Contents

1. [What is a Stream?](#1-what-is-a-stream)
2. [Stream vs Collection](#2-stream-vs-collection)
3. [Stream Pipeline Structure](#3-stream-pipeline-structure)
4. [Step 1 — Creating a Stream (Source)](#4-step-1--creating-a-stream-source)
5. [Step 2 — filter() Operation](#5-step-2--filter-operation)
6. [Step 3 — map() Operation](#6-step-3--map-operation)
7. [Step 4 — Terminal Operations](#7-step-4--terminal-operations)
8. [Lazy Evaluation — The Golden Rule](#8-lazy-evaluation--the-golden-rule)
9. [Functional Interfaces Cheat Sheet](#9-functional-interfaces-cheat-sheet)
10. [Complete Pipeline Examples](#10-complete-pipeline-examples)
11. [Common Mistakes to Avoid](#11-common-mistakes-to-avoid)
12. [Quick Reference Card](#12-quick-reference-card)

---

## 1. What is a Stream?

A **Stream** is a sequence of elements that supports a pipeline of operations to process data in a functional style.

### Key Definition

> A stream is **not a data structure**. It is a **computation pipeline** — a recipe for processing data. It does not store any elements.

### Think of it like an assembly line:

```
Raw Material (Collection)
        ↓
   [Filter Station]     ← removes unwanted items
        ↓
   [Transform Station]  ← changes items into something new
        ↓
   Finished Product (Result)
```

### What a Stream IS:
- A view over data from a source (List, Set, Array, etc.)
- A pipeline of operations (filter, map, sort, etc.)
- Lazy — does nothing until a terminal operation is called
- Single-use — once consumed, it cannot be reused

### What a Stream is NOT:
- NOT a data structure (does not store data)
- NOT a replacement for Collections
- NOT reusable after a terminal operation

---

## 2. Stream vs Collection

| Feature | Collection (List, Set) | Stream |
|---|---|---|
| Stores data | ✅ Yes — data lives in memory | ❌ No — just a pipeline |
| Can iterate multiple times | ✅ Yes | ❌ No — single use |
| Can add / remove elements | ✅ Yes | ❌ No — cannot modify source |
| Pipeline operations | ❌ No built-in chaining | ✅ Yes — filter, map, reduce |
| Lazy evaluation | ❌ No | ✅ Yes — runs only when terminal called |
| Parallel processing | ❌ Manual | ✅ Built-in with `.parallelStream()` |

### Code Comparison — Same task, two approaches:

**Old way (Collection + for loop):**
```java
List<String> result = new ArrayList<>();
for (String name : names) {
    if (name.length() > 4) {
        result.add(name.toUpperCase());
    }
}
```

**New way (Stream API):**
```java
List<String> result = names.stream()
    .filter(name -> name.length() > 4)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

Both produce the same result. The stream version is more readable, composable, and can be parallelized with one word change.

---

## 3. Stream Pipeline Structure

Every stream pipeline has exactly **three parts**:

```
SOURCE  →  INTERMEDIATE OPERATIONS  →  TERMINAL OPERATION
```

```java
names                           // data source (Collection)
    .stream()                   // open the pipeline (source)
    .filter(n -> n.length() > 4) // intermediate — lazy, not run yet
    .map(String::toUpperCase)   // intermediate — lazy, not run yet
    .collect(Collectors.toList()) // terminal — THIS triggers everything
```

### The three parts explained:

| Part | Role | Examples | Lazy or Eager? |
|---|---|---|---|
| **Source** | Where data comes from | `.stream()`, `Stream.of()`, `Arrays.stream()` | — |
| **Intermediate** | Transform / filter the pipeline | `filter()`, `map()`, `sorted()`, `distinct()`, `limit()` | **Lazy** |
| **Terminal** | Trigger execution, produce a result | `collect()`, `count()`, `findFirst()`, `forEach()`, `reduce()` | **Eager** |

> ⚠️ **Critical Rule:** Without a terminal operation, **nothing runs**. Intermediate operations are just a description, not an execution.

---

## 4. Step 1 — Creating a Stream (Source)

### Method Signature:
```java
Stream<T>  collection.stream()
// Returns a sequential Stream of type T (same as the collection's element type)
```

### 4 Common Ways to Create a Stream:

```java
// 1. From a List (most common)
List<String> names = List.of("Ali", "Bob", "Priya", "James");
Stream<String> s1 = names.stream();

// 2. From a Set
Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
Stream<Integer> s2 = numbers.stream();

// 3. Directly with Stream.of()
Stream<String> s3 = Stream.of("x", "y", "z");

// 4. From an array
String[] arr = {"a", "b", "c"};
Stream<String> s4 = Arrays.stream(arr);

// 5. Primitive streams (avoid boxing, better performance)
IntStream    s5 = IntStream.range(1, 10);      // 1 to 9
IntStream    s6 = IntStream.rangeClosed(1, 10); // 1 to 10
LongStream   s7 = LongStream.of(100L, 200L);
DoubleStream s8 = DoubleStream.of(1.1, 2.2);

// 6. Infinite streams
Stream<Integer> evens = Stream.iterate(0, n -> n + 2); // 0, 2, 4, 6 ...
Stream<Double>  rands = Stream.generate(Math::random);  // random numbers
```

### Type Rule:
```
List<String>  → .stream() → Stream<String>
List<Integer> → .stream() → Stream<Integer>
List<User>    → .stream() → Stream<User>
```

The stream's type always matches the collection's element type.

### Important Notes:
- `.stream()` does NOT copy data — it creates a view over the existing collection
- The source collection is never modified by stream operations
- Calling `.stream()` again on the same collection gives a brand-new pipeline
- Use `IntStream` / `LongStream` / `DoubleStream` for primitive numbers to avoid unnecessary boxing overhead

---

## 5. Step 2 — filter() Operation

### Method Signature:
```java
Stream<T>  filter(Predicate<T> predicate)
```

### What it does:
Passes each element through a **YES/NO test**. Elements where the test returns `true` pass through. Elements where it returns `false` are dropped.

```
Stream<T> in  →  filter(Predicate<T>)  →  Stream<T> out
              (same type, fewer elements)
```

### The Predicate Contract:
```
element (T) comes in  →  test runs  →  boolean comes out
                           true  = element PASSES (kept)
                           false = element FAILS (dropped)
```

### Examples:

```java
// Filter numbers
List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

nums.stream()
    .filter(n -> n > 5)           // keep only numbers > 5
    .collect(Collectors.toList()); // → [6, 7, 8, 9, 10]

nums.stream()
    .filter(n -> n % 2 == 0)      // keep only even numbers
    .collect(Collectors.toList()); // → [2, 4, 6, 8, 10]

// Filter strings
List<String> names = List.of("Ali", "Bob", "Priya", "James", "Zoe");

names.stream()
    .filter(s -> s.length() > 3)         // → ["Priya", "James"]
    .collect(Collectors.toList());

names.stream()
    .filter(s -> s.startsWith("A"))      // → ["Ali"]
    .collect(Collectors.toList());

names.stream()
    .filter(String::isEmpty)             // method reference style
    .collect(Collectors.toList());       // → []

// Filter objects
List<User> users = getUserList();

users.stream()
    .filter(u -> u.getAge() >= 18)       // keep adults only
    .collect(Collectors.toList());

users.stream()
    .filter(u -> u.isActive())           // keep active users
    .collect(Collectors.toList());
```

### Combining Predicates:

```java
Predicate<String> longerThan3 = s -> s.length() > 3;
Predicate<String> startsWithA = s -> s.startsWith("A");

// AND — both conditions must be true
stream.filter(longerThan3.and(startsWithA));

// OR — either condition must be true
stream.filter(longerThan3.or(startsWithA));

// NEGATE — flip the condition
stream.filter(longerThan3.negate()); // length <= 3

// Chain multiple filters — equivalent to using .and()
stream.filter(longerThan3).filter(startsWithA);
```

### Key Rules for filter():
1. **Type never changes** — `Stream<String>` in → `Stream<String>` out
2. **Count can only decrease or stay the same** — filter never adds elements
3. **Predicate must be stateless** — do NOT mutate any variable inside the lambda
4. **No side effects** — do not print or write inside a filter predicate
5. **Lazy** — filter does not execute until a terminal operation is called

---

## 6. Step 3 — map() Operation

### Method Signature:
```java
Stream<R>  map(Function<T, R> mapper)
```

### What it does:
Transforms **every element** into a new value. Every element in → exactly one element out. Count never changes. But the **type** can change completely.

```
Stream<T> in  →  map(Function<T, R>)  →  Stream<R> out
              (same count, possibly different type)
```

### The Function Contract:
```
element (T) comes in  →  transformation runs  →  new value (R) comes out
                          R can be same type as T
                          R can be a completely different type
```

### Examples — Same type transformations:

```java
List<String> names = List.of("ali", "bob", "priya");

// String → String (toUpperCase)
names.stream()
    .map(String::toUpperCase)            // method reference
    .collect(Collectors.toList());       // → ["ALI", "BOB", "PRIYA"]

// String → String (reverse)
names.stream()
    .map(s -> new StringBuilder(s).reverse().toString())
    .collect(Collectors.toList());       // → ["ila", "bob", "ayirp"]
```

### Examples — Type-changing transformations:

```java
List<String> names = List.of("Ali", "Bob", "Priya");

// String → Integer (get length)
names.stream()
    .map(s -> s.length())                // or: .map(String::length)
    .collect(Collectors.toList());       // → [3, 3, 5]

// String → Boolean (check condition)
names.stream()
    .map(s -> s.startsWith("A"))
    .collect(Collectors.toList());       // → [true, false, false]

// Object → String (extract field)
List<User> users = getUserList();

users.stream()
    .map(user -> user.getName())         // or: .map(User::getName)
    .collect(Collectors.toList());       // → ["Alice", "Bob", ...]

users.stream()
    .map(User::getEmail)                 // method reference style
    .collect(Collectors.toList());
```

### filter() vs map() — The key difference:

| | filter() | map() |
|---|---|---|
| Purpose | Keep or drop elements | Transform elements |
| Count after | Same or fewer | Always the same |
| Type after | Same as before | Can be different |
| Argument | `Predicate<T>` — returns `boolean` | `Function<T,R>` — returns a new value |

### Specialty map versions (avoid boxing):

```java
// Use these when mapping TO a primitive type
stream.mapToInt(String::length)        // Stream<String> → IntStream
stream.mapToLong(User::getId)          // Stream<User>   → LongStream
stream.mapToDouble(Product::getPrice)  // Stream<Product> → DoubleStream

// And back (boxing):
intStream.boxed()                      // IntStream → Stream<Integer>
intStream.mapToObj(i -> "item " + i)  // IntStream → Stream<String>
```

### Key Rules for map():
1. **One in, one out** — every element is transformed, nothing is dropped
2. **Type can change** — `Stream<String>` can become `Stream<Integer>`
3. **Mapper must be stateless and side-effect free**
4. **Lazy** — map does not execute until a terminal operation is called

---

## 7. Step 4 — Terminal Operations

Terminal operations **trigger the entire pipeline to execute**. They are eager — once called, stream processing runs from source to terminal in a single pass.

> ⚠️ After a terminal operation, the stream is **consumed and closed**. Calling another terminal op throws `IllegalStateException`.

### 7.1 collect() — most powerful terminal op

```java
// Collect to List (most common)
List<String> list = stream.collect(Collectors.toList());

// Collect to Set (removes duplicates)
Set<String> set = stream.collect(Collectors.toSet());

// Collect to specific list implementation
List<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));

// Join strings
String joined = stream.collect(Collectors.joining());          // "AliBobPriya"
String joined2 = stream.collect(Collectors.joining(", "));     // "Ali, Bob, Priya"
String joined3 = stream.collect(Collectors.joining(", ", "[", "]")); // "[Ali, Bob, Priya]"

// Collect to Map
Map<String, Integer> nameLengths = stream
    .collect(Collectors.toMap(
        name -> name,          // key
        name -> name.length()  // value
    ));
```

### 7.2 count() — returns long

```java
long count = names.stream()
    .filter(s -> s.length() > 3)
    .count();
// Returns: 2L  (a long, not int)
```

### 7.3 findFirst() and findAny() — returns Optional

```java
// findFirst — first element that survives the pipeline
Optional<String> first = names.stream()
    .filter(s -> s.startsWith("P"))
    .findFirst();

first.isPresent();        // true or false
first.get();              // get the value (throws if empty)
first.orElse("default"); // safe — returns default if empty

// findAny — any element (useful in parallel streams)
Optional<String> any = names.parallelStream()
    .filter(s -> s.length() > 3)
    .findAny();
```

### 7.4 forEach() — side effects only

```java
// forEach — run an action on each element, returns void
names.stream()
    .filter(s -> s.length() > 3)
    .forEach(System.out::println); // prints each name

// ⚠️ forEach is for side effects (printing, saving)
// Do NOT use forEach to build a result — use collect() instead
```

### 7.5 anyMatch / allMatch / noneMatch — boolean checks

```java
List<Integer> nums = List.of(1, 5, 3, 7, 2, 9);

// anyMatch — true if AT LEAST ONE element passes
boolean anyOver5 = nums.stream().anyMatch(n -> n > 5);    // true

// allMatch — true if ALL elements pass
boolean allPositive = nums.stream().allMatch(n -> n > 0); // true

// noneMatch — true if NO elements pass
boolean noneNeg = nums.stream().noneMatch(n -> n < 0);    // true

// These are SHORT-CIRCUIT — stop as soon as result is determined
// anyMatch stops at the FIRST true
// allMatch stops at the FIRST false
// noneMatch stops at the FIRST true
```

### 7.6 reduce() — fold into one value

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);

// Sum all elements
Optional<Integer> sum = nums.stream()
    .reduce((a, b) -> a + b);      // → Optional[15]

// With identity (starting value) — returns T, not Optional
int sum2 = nums.stream()
    .reduce(0, (a, b) -> a + b);   // → 15

// Multiply all
int product = nums.stream()
    .reduce(1, (a, b) -> a * b);   // → 120

// Find max
Optional<Integer> max = nums.stream()
    .reduce((a, b) -> a > b ? a : b); // → Optional[5]
```

### 7.7 min() and max() — with Comparator

```java
Optional<String> shortest = names.stream()
    .min(Comparator.comparingInt(String::length));

Optional<String> longest = names.stream()
    .max(Comparator.comparingInt(String::length));

Optional<Integer> maxNum = nums.stream()
    .max(Comparator.naturalOrder());

Optional<Integer> minNum = nums.stream()
    .min(Integer::compareTo);
```

### Terminal Operations — Quick Reference:

| Operation | Returns | Short-circuit? | Use for |
|---|---|---|---|
| `collect(Collectors.toList())` | `List<T>` | No | Building a result collection |
| `collect(Collectors.toSet())` | `Set<T>` | No | Building a unique result set |
| `count()` | `long` | No | Counting surviving elements |
| `findFirst()` | `Optional<T>` | ✅ Yes | Getting first matching element |
| `findAny()` | `Optional<T>` | ✅ Yes | Getting any matching element |
| `forEach(action)` | `void` | No | Side effects (print, save) |
| `anyMatch(pred)` | `boolean` | ✅ Yes | Does anything match? |
| `allMatch(pred)` | `boolean` | ✅ Yes | Does everything match? |
| `noneMatch(pred)` | `boolean` | ✅ Yes | Does nothing match? |
| `reduce(BinaryOp)` | `Optional<T>` | No | Folding to a single value |
| `reduce(identity, BinaryOp)` | `T` | No | Folding with a starting value |
| `min(Comparator)` | `Optional<T>` | No | Smallest element |
| `max(Comparator)` | `Optional<T>` | No | Largest element |
| `toArray()` | `Object[]` | No | When you need an array |

---

## 8. Lazy Evaluation — The Golden Rule

**Intermediate operations are lazy. They do not run until a terminal operation is called.**

### Proof — run this in JShell:

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);

Stream<Integer> pipeline = nums.stream()
    .filter(n -> {
        System.out.println("Filtering: " + n);   // does this print?
        return n > 3;
    });

System.out.println("Pipeline created — nothing printed yet!");
// Output: "Pipeline created — nothing printed yet!"
// The filter has NOT run at all.

pipeline.collect(Collectors.toList());
// NOW the filter runs — you see "Filtering: 1", "Filtering: 2", etc.
```

### Why laziness matters:

```java
// SHORT-CIRCUIT EXAMPLE
// Without laziness, this would filter ALL 1 million elements,
// then take the first 5. With laziness, it stops after finding 5.

List<Integer> result = IntStream.rangeClosed(1, 1_000_000)
    .filter(n -> n % 2 == 0)   // lazy — not run yet
    .limit(5)                   // lazy — not run yet
    .boxed()
    .collect(Collectors.toList()); // runs — but stops after 5 matches!
// → [2, 4, 6, 8, 10]  (only processed ~10 elements, not 1 million)
```

### Rules of lazy evaluation:
1. Intermediate ops (`filter`, `map`, `sorted`, etc.) are **always lazy**
2. Terminal ops (`collect`, `count`, `findFirst`, etc.) are **always eager**
3. Short-circuit terminal ops (`findFirst`, `anyMatch`) can stop the pipeline early
4. `limit()` and `findFirst()` together make streams very efficient on large/infinite data

---

## 9. Functional Interfaces Cheat Sheet

Streams use these four functional interfaces everywhere. Knowing them removes all mystery.

| Interface | Method | In → Out | Used by |
|---|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | `T → boolean` | `filter()` |
| `Function<T, R>` | `R apply(T t)` | `T → R` | `map()` |
| `Consumer<T>` | `void accept(T t)` | `T → nothing` | `forEach()` |
| `Supplier<T>` | `T get()` | `nothing → T` | `Stream.generate()` |
| `BinaryOperator<T>` | `T apply(T a, T b)` | `(T, T) → T` | `reduce()` |
| `Comparator<T>` | `int compare(T a, T b)` | `(T, T) → int` | `sorted()`, `min()`, `max()` |

### Lambda vs Method Reference:

```java
// Lambda and method reference — exactly equivalent

// Predicate
.filter(s -> s.isEmpty())       ←→   .filter(String::isEmpty)

// Function
.map(s -> s.toUpperCase())      ←→   .map(String::toUpperCase)
.map(u -> u.getName())          ←→   .map(User::getName)

// Consumer
.forEach(s -> System.out.println(s)) ←→  .forEach(System.out::println)

// Rule: use method reference when the lambda body is just one method call.
// Use lambda when you have logic (n -> n * 2 + 1 stays as a lambda).
```

---

## 10. Complete Pipeline Examples

### Example 1 — Filter + Map + Collect

```java
// Problem: From a list of names, keep names longer than 3 chars,
// convert to uppercase, and collect to a list.

List<String> names = List.of("Ali", "Bob", "Priya", "James", "Zoe", "Rohan");

List<String> result = names.stream()
    .filter(name -> name.length() > 3)    // ["Priya", "James", "Rohan"]
    .map(String::toUpperCase)             // ["PRIYA", "JAMES", "ROHAN"]
    .collect(Collectors.toList());        // trigger + collect

System.out.println(result); // [PRIYA, JAMES, ROHAN]
```

### Example 2 — Map to different type + Count

```java
// Problem: Count how many names have more than 4 characters.

List<String> names = List.of("Ali", "Bob", "Priya", "James", "Zoe");

long count = names.stream()
    .filter(name -> name.length() > 4)   // keep names with length > 4
    .count();                             // count and trigger

System.out.println(count); // 2
```

### Example 3 — Filter + findFirst (short-circuit)

```java
// Problem: Find the first name starting with "S" (stop early).

List<String> names = List.of("Riya", "Sameer", "Tom", "Sneha", "Arjun");

Optional<String> first = names.stream()
    .filter(name -> name.startsWith("S"))  // filter
    .findFirst();                          // stop at first match

first.ifPresent(System.out::println);     // Sameer
// Note: "Sneha" was never even processed
```

### Example 4 — Numbers pipeline

```java
// Problem: From 1 to 10, keep only even numbers, square them, sum all.

int sum = IntStream.rangeClosed(1, 10)
    .filter(n -> n % 2 == 0)   // 2, 4, 6, 8, 10
    .map(n -> n * n)            // 4, 16, 36, 64, 100
    .sum();                     // 220 (terminal op built into IntStream)

System.out.println(sum); // 220
```

### Example 5 — Object list pipeline

```java
// Setup — simple User record
record User(String name, int age, boolean active) {}

List<User> users = List.of(
    new User("Alice", 25, true),
    new User("Bob",   17, true),
    new User("Carol", 30, false),
    new User("Dave",  22, true)
);

// Problem: Get names of active adult users, sorted alphabetically.
List<String> names = users.stream()
    .filter(u -> u.active())          // keep active
    .filter(u -> u.age() >= 18)       // keep adults
    .map(User::name)                  // extract name
    .sorted()                         // alphabetical sort
    .collect(Collectors.toList());

System.out.println(names); // [Alice, Dave]
```

### Example 6 — Joining strings

```java
List<String> words = List.of("Java", "Stream", "API", "is", "cool");

String sentence = words.stream()
    .filter(w -> w.length() > 2)
    .map(String::toLowerCase)
    .collect(Collectors.joining(" "));

System.out.println(sentence); // "java stream api cool"
```

---

## 11. Common Mistakes to Avoid

### ❌ Mistake 1 — Reusing a stream

```java
Stream<String> stream = names.stream().filter(s -> s.length() > 3);

stream.collect(Collectors.toList()); // ✅ works
stream.count();                      // ❌ IllegalStateException: stream already operated upon

// Fix: create a new stream
names.stream().filter(s -> s.length() > 3).count(); // ✅
```

### ❌ Mistake 2 — No terminal operation

```java
// This does NOTHING — filter and map are lazy, no terminal = no execution
names.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase);
// No output, no result, no error — just a pipeline description with no trigger
```

### ❌ Mistake 3 — Mutating state in a lambda

```java
List<String> result = new ArrayList<>();

names.stream()
    .filter(s -> s.length() > 3)
    .forEach(s -> result.add(s));    // ❌ mutating external state — unsafe in parallel

// Fix: use collect()
List<String> result = names.stream()
    .filter(s -> s.length() > 3)
    .collect(Collectors.toList());   // ✅
```

### ❌ Mistake 4 — Using forEach to build a result

```java
// ❌ Wrong — forEach is for side effects, not for building results
List<String> upper = new ArrayList<>();
stream.forEach(s -> upper.add(s.toUpperCase()));

// ✅ Right — use map + collect
List<String> upper = stream.map(String::toUpperCase).collect(Collectors.toList());
```

### ❌ Mistake 5 — Calling .get() on Optional without checking

```java
Optional<String> first = stream.findFirst();
String value = first.get(); // ❌ throws NoSuchElementException if empty

// Fix options:
String safe1 = first.orElse("default");              // ✅ return default
String safe2 = first.orElseThrow();                  // ✅ explicit throw
first.ifPresent(v -> System.out.println(v));          // ✅ only run if present
```

### ❌ Mistake 6 — Boxing overhead with primitive streams

```java
// ❌ Boxes each int into Integer — wasteful
List<Integer> nums = List.of(1, 2, 3, 4, 5);
int sum = nums.stream()
    .mapToInt(Integer::intValue) // unboxing manually
    .sum();

// ✅ Use IntStream directly for numeric ranges
int sum = IntStream.rangeClosed(1, 5).sum();

// ✅ Use mapToInt when mapping objects to numbers
int totalLen = names.stream().mapToInt(String::length).sum();
```

---

## 12. Quick Reference Card

### Creating a Stream

```java
list.stream()                          // from Collection
Arrays.stream(array)                   // from array
Stream.of(a, b, c)                    // from values
IntStream.range(start, end)           // int range (exclusive end)
IntStream.rangeClosed(start, end)     // int range (inclusive end)
Stream.generate(Supplier)             // infinite stream
Stream.iterate(seed, UnaryOperator)   // infinite sequence
```

### Intermediate Operations (Lazy)

```java
.filter(Predicate)       // keep elements where predicate is true
.map(Function)           // transform each element (type can change)
.mapToInt(Function)      // transform to IntStream (no boxing)
.flatMap(Function)       // flatten stream-of-streams into one stream
.sorted()                // natural order sort
.sorted(Comparator)      // custom sort
.distinct()              // remove duplicates
.limit(n)                // take first n elements
.skip(n)                 // skip first n elements
.peek(Consumer)          // debug — look at elements without changing them
```

### Terminal Operations (Eager)

```java
.collect(Collectors.toList())        // → List<T>
.collect(Collectors.toSet())         // → Set<T>
.collect(Collectors.joining(", "))   // → String
.collect(Collectors.toMap(k, v))     // → Map<K,V>
.count()                             // → long
.findFirst()                         // → Optional<T>
.findAny()                           // → Optional<T>
.forEach(Consumer)                   // → void
.anyMatch(Predicate)                 // → boolean
.allMatch(Predicate)                 // → boolean
.noneMatch(Predicate)                // → boolean
.reduce(BinaryOperator)              // → Optional<T>
.reduce(identity, BinaryOperator)    // → T
.min(Comparator)                     // → Optional<T>
.max(Comparator)                     // → Optional<T>
.toArray()                           // → Object[]
```

### Mental Model Sentence Template

```
"Take [source data]
 → keep only [filter condition]
 → transform each into [map transformation]
 → give me the [collect/count/find/etc.]"
```

---

## 📝 Imports Needed

```java
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.DoubleStream;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.Comparator;
```

---

> **Next topics to study:** `flatMap()` → `sorted()` + `distinct()` → Advanced Collectors (`groupingBy`, `partitioningBy`) → `Optional` in depth → Primitive Streams → Parallel Streams → Custom Collectors