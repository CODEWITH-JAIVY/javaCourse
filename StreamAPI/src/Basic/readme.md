# ☕ Java Stream API — filter() and map() — 9 Problems & Solutions

> All 9 hands-on problems covering `filter()`, `map()`, and their combinations — from beginner to advanced.

---

## 📚 Table of Contents

- [Beginner Problems](#-beginner-problems)
    - [P1 — Keep only positive numbers](#p1----keep-only-positive-numbers)
    - [P2 — Extract word lengths](#p2----extract-word-lengths)
    - [P3 — Filter evens then double them](#p3----filter-evens-then-double-them)
- [Intermediate Problems](#-intermediate-problems)
    - [P4 — Filter emails by domain](#p4----filter-emails-by-domain)
    - [P5 — Extract names from User objects](#p5----extract-names-from-user-objects)
    - [P6 — Get names of adult users](#p6----get-names-of-adult-users)
    - [P7 — Find the longest word](#p7----find-the-longest-word)
- [Advanced Problems](#-advanced-problems)
    - [P8 — Product catalogue discount price list](#p8----product-catalogue--discount-price-list)
    - [P9 — Chained predicates + joining](#p9----chained-predicates--joining)

---

## 🟢 Beginner Problems

---

### P1 — Keep only positive numbers

**Category:** `filter()`

**Task:**
Given a list of integers, keep only the numbers that are **strictly greater than zero**. Collect to a list.

**Input:**

```java
List<Integer> nums = List.of(4, -1, 7, -3, 0, 9, -5, 2);
```

**Expected Output:**

```
[4, 7, 9, 2]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

List<Integer> nums = List.of(4, -1, 7, -3, 0, 9, -5, 2);

List<Integer> result = nums.stream()
        .filter(n -> n > 0)
        .collect(Collectors.toList());

System.out.

println(result);
// Output: [4, 7, 9, 2]
```

**Key Learning:**

- `n > 0` is the `Predicate<Integer>` — zero is **not** positive so `0` is excluded.
- `filter()` type never changes — `Stream<Integer>` in → `Stream<Integer>` out.
- Count shrinks from 8 elements down to 4.

---

### P2 — Extract word lengths

**Category:** `map()`

**Task:**
Given a list of words, transform each word into its **character length**. Collect to a list of integers.

**Input:**

```java
List<String> words = List.of("Java", "Stream", "API", "is", "powerful");
```

**Expected Output:**

```
[4, 6, 3, 2, 8]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

List<String> words = List.of("Java", "Stream", "API", "is", "powerful");

List<Integer> lengths = words.stream()
        .map(String::length)           // String → Integer
        .collect(Collectors.toList());

System.out.

println(lengths);
// Output: [4, 6, 3, 2, 8]
```

**Key Learning:**

- The type changes — `Stream<String>` becomes `Stream<Integer>`. This is what `map()` does.
- Count stays the same — 5 words in → 5 lengths out.
- `String::length` is a method reference for `s -> s.length()`.

---

### P3 — Filter evens then double them

**Category:** `filter()` + `map()`

**Task:**
Given a list of integers, keep only the **even numbers**, then **multiply each by 2**. Collect to a list.

**Input:**

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
```

**Expected Output:**

```
[4, 8, 12, 16, 20]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

List<Integer> result = nums.stream()
        .filter(n -> n % 2 == 0)       // keep evens → [2, 4, 6, 8, 10]
        .map(n -> n * 2)               // double each → [4, 8, 12, 16, 20]
        .collect(Collectors.toList());

System.out.

println(result);
// Output: [4, 8, 12, 16, 20]
```

**Key Learning:**

- `filter()` runs first and reduces the stream, then `map()` transforms only the survivors.
- Pipeline type tracking: `Stream<Integer>` → filter → `Stream<Integer>` → map → `Stream<Integer>`.
- Elements are processed **one by one** through the whole pipeline, not stage by stage.

---

## 🔵 Intermediate Problems

---

### P4 — Filter emails by domain

**Category:** `filter()` + `count()`

**Task:**
Given a list of email addresses, keep only emails from the **gmail.com** domain and count how many there are.

**Input:**

```java
List<String> emails = List.of(
        "alice@gmail.com",
        "bob@yahoo.com",
        "carol@gmail.com",
        "dave@outlook.com",
        "eve@gmail.com"
);
```

**Expected Output:**

```
Count : 3
Emails: [alice@gmail.com, carol@gmail.com, eve@gmail.com]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

List<String> emails = List.of(
        "alice@gmail.com", "bob@yahoo.com",
        "carol@gmail.com", "dave@outlook.com", "eve@gmail.com"
);

// Count only
long gmailCount = emails.stream()
        .filter(e -> e.endsWith("@gmail.com"))
        .count();                          // terminal op — returns long

System.out.

println(gmailCount);
// Output: 3

// Collect the matching emails
List<String> gmails = emails.stream()
        .filter(e -> e.endsWith("@gmail.com"))
        .collect(Collectors.toList());

System.out.

println(gmails);
// Output: [alice@gmail.com, carol@gmail.com, eve@gmail.com]
```

**Key Learning:**

- `.count()` is a terminal operation that returns a `long` (not `int`).
- `endsWith()` is a clean and readable predicate for domain checks.
- You can use `count()` or `collect()` on the same filter — they are separate streams.

---

### P5 — Extract names from User objects

**Category:** `map()` — object to field extraction

**Task:**
Given a list of `User` objects, extract only the **name** of each user. Collect to a list of strings.

**Input:**

```java
record User(String name, int age) {
}

List<User> users = List.of(
        new User("Alice", 30),
        new User("Bob", 17),
        new User("Carol", 25)
);
```

**Expected Output:**

```
[Alice, Bob, Carol]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

record User(String name, int age) {
}

List<User> users = List.of(
        new User("Alice", 30),
        new User("Bob", 17),
        new User("Carol", 25)
);

List<String> names = users.stream()
        .map(User::name)               // User → String  (method reference)
        .collect(Collectors.toList());

System.out.

println(names);
// Output: [Alice, Bob, Carol]

// Equivalent lambda form:
List<String> names2 = users.stream()
        .map(u -> u.name())            // same result
        .collect(Collectors.toList());
```

**Key Learning:**

- This is the most common real-world `map()` pattern — extracting a single field from an object.
- Type changes from `Stream<User>` to `Stream<String>`.
- `User::name` (method reference) is cleaner than `u -> u.name()` when the lambda is just one call.

---

### P6 — Get names of adult users

**Category:** `filter()` + `map()`

**Task:**
Given a list of `User` objects, keep only users aged **18 or over**, then extract their **names**. Collect to a list of
strings.

**Input:**

```java
record User(String name, int age) {
}

List<User> users = List.of(
        new User("Alice", 30),
        new User("Bob", 17),
        new User("Carol", 25),
        new User("Dave", 15),
        new User("Eve", 22)
);
```

**Expected Output:**

```
[Alice, Carol, Eve]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

record User(String name, int age) {
}

List<User> users = List.of(
        new User("Alice", 30), new User("Bob", 17),
        new User("Carol", 25), new User("Dave", 15),
        new User("Eve", 22)
);

List<String> adults = users.stream()
        .filter(u -> u.age() >= 18)    // Stream<User>   → Stream<User>
        .map(User::name)               // Stream<User>   → Stream<String>
        .collect(Collectors.toList());

System.out.

println(adults);
// Output: [Alice, Carol, Eve]
```

**Key Learning:**

- Type change happens at `map()`, **never** at `filter()`.
- Pipeline type tracking: `Stream<User>` → filter → `Stream<User>` → map → `Stream<String>`.
- This filter-then-extract pattern is used constantly in production Java code.

---

### P7 — Find the longest word

**Category:** `mapToInt()` + `anyMatch()`

**Task:**
Given a list of fruit names:

- **Task A:** Find the **maximum word length**.
- **Task B:** Check if **any** fruit has more than 8 characters.

**Input:**

```java
List<String> fruits = List.of(
        "apple", "kiwi", "banana", "fig", "watermelon", "plum"
);
```

**Expected Output:**

```
Max length : 10
Has long   : true
```

**Solution:**

```java
import java.util.List;
import java.util.OptionalInt;

List<String> fruits = List.of(
        "apple", "kiwi", "banana", "fig", "watermelon", "plum"
);

// Task A — max length using primitive IntStream (no boxing)
OptionalInt maxLen = fruits.stream()
        .mapToInt(String::length)      // Stream<String> → IntStream
        .max();

System.out.

println(maxLen.getAsInt()); // 10

// Task B — anyMatch (short-circuit: stops at "watermelon")
boolean hasLong = fruits.stream()
        .anyMatch(s -> s.length() > 8);

System.out.

println(hasLong);           // true
```

**Key Learning:**

- `mapToInt()` gives an `IntStream` which has built-in `sum()`, `max()`, `min()`, `average()` — use it when mapping to
  `int` to avoid boxing overhead.
- `anyMatch()` is a **short-circuit** terminal op — it stops the moment it finds the first `true`, never processing the
  rest.
- For Task B, no `map()` is needed — `anyMatch()` tests the original element directly.

---

## 🔴 Advanced Problems

---

### P8 — Product catalogue — discount price list

**Category:** `filter()` + `map()` + `Collectors.toList()`

**Task:**
Given a list of products, keep only those that are **in stock** AND **priced above ₹2000**. Apply a **10% discount** to
each, and format the result as `"ProductName: ₹discountedPrice"`. Collect to a list of strings.

**Input:**

```java
record Product(String name, double price, boolean inStock) {
}

List<Product> products = List.of(
        new Product("Laptop", 85000, true),
        new Product("Mouse", 1200, true),
        new Product("Monitor", 32000, false),
        new Product("Keyboard", 4500, true),
        new Product("Webcam", 3800, false)
);
```

**Expected Output:**

```
[Laptop: ₹76500.0, Keyboard: ₹4050.0]
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

record Product(String name, double price, boolean inStock) {
}

List<Product> products = List.of(
        new Product("Laptop", 85000, true),
        new Product("Mouse", 1200, true),
        new Product("Monitor", 32000, false),
        new Product("Keyboard", 4500, true),
        new Product("Webcam", 3800, false)
);

// Concise form — filter + single map
List<String> discounted = products.stream()
        .filter(p -> p.inStock() && p.price() > 2000)       // two conditions, one filter
        .map(p -> p.name() + ": ₹" + (p.price() * 0.9))    // Product → String
        .collect(Collectors.toList());

System.out.

println(discounted);
// Output: [Laptop: ₹76500.0, Keyboard: ₹4050.0]

// Alternative — two separate map steps for clarity
List<String> discounted2 = products.stream()
        .filter(p -> p.inStock() && p.price() > 2000)
        .map(p -> new Product(p.name(), p.price() * 0.9, true)) // apply discount
        .map(p -> p.name() + ": ₹" + p.price())                 // format as String
        .collect(Collectors.toList());
```

**Key Learning:**

- Multiple conditions in one `filter()` using `&&` — no need for two separate filter calls.
- Two `map()` calls in a row is perfectly valid when each does a distinct transformation.
- Pipeline type: `Stream<Product>` → filter → `Stream<Product>` → map → `Stream<String>`.

---

### P9 — Chained predicates + joining

**Category:** `map()` + `filter()` + `Collectors.joining()`

**Task:**
Given a list of raw strings with mixed whitespace and empty values:

1. **Trim** each string.
2. **Remove** blank strings (empty or whitespace-only after trim).
3. **Keep** only strings longer than 3 characters.
4. Convert to **uppercase**.
5. **Join** all results with `" | "` as the separator.

**Input:**

```java
List<String> raw = List.of(
        "  Java  ", "  ", "Stream", "  API  ", "", "rocks", "  filter  "
);
```

**Expected Output:**

```
JAVA | STREAM | ROCKS | FILTER
```

**Solution:**

```java
import java.util.List;
import java.util.stream.Collectors;

List<String> raw = List.of(
        "  Java  ", "  ", "Stream", "  API  ", "", "rocks", "  filter  "
);

String result = raw.stream()
        .map(String::trim)                      // step 1 — clean whitespace
        .filter(s -> !s.isBlank())              // step 2 — remove empty strings
        .filter(s -> s.length() > 3)            // step 3 — length check
        .map(String::toUpperCase)               // step 4 — uppercase
        .collect(Collectors.joining(" | "));    // step 5 — join with separator

System.out.

println(result);
// Output: JAVA | STREAM | ROCKS | FILTER
```

**`Collectors.joining()` — all three forms:**

```java
.collect(Collectors.joining())                      // no separator   → "JAVASTREAM..."
        .

collect(Collectors.joining(" | "))                 // with separator → "JAVA | STREAM | ..."
        .

collect(Collectors.joining(", ", "[","]"))        // prefix + suffix → "[JAVA, STREAM, ...]"
```

**Key Learning:**

- `map()` can come **before** `filter()` — trimming first makes the blank-check meaningful. Order matters.
- `Collectors.joining()` is a terminal op that produces a single `String` from all stream elements.
- This pipeline is a realistic data-cleaning scenario — trim → validate → filter → transform → format.

---

## 📋 Problems Summary

| #  | Level           | Category                 | Terminal Op        | Key Concept                               |
|----|-----------------|--------------------------|--------------------|-------------------------------------------|
| P1 | 🟢 Beginner     | `filter`                 | `collect`          | Basic predicate `n > 0`                   |
| P2 | 🟢 Beginner     | `map`                    | `collect`          | Type change `String → Integer`            |
| P3 | 🟢 Beginner     | `filter` + `map`         | `collect`          | Chain filter then map                     |
| P4 | 🔵 Intermediate | `filter`                 | `count`            | `endsWith()` predicate + count terminal   |
| P5 | 🔵 Intermediate | `map`                    | `collect`          | Object → field extraction with method ref |
| P6 | 🔵 Intermediate | `filter` + `map`         | `collect`          | Filter object, map to field               |
| P7 | 🔵 Intermediate | `mapToInt` + `anyMatch`  | `max` / `anyMatch` | Primitive stream + short-circuit terminal |
| P8 | 🔴 Advanced     | `filter` + `map`         | `collect`          | Multi-condition filter + chained maps     |
| P9 | 🔴 Advanced     | `map` + `filter` + `map` | `joining`          | map-before-filter + Collectors.joining    |

---

## 📝 Imports Required

```java
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
```

---

> **Next topics to study:** `flatMap()` → `sorted()` + `distinct()` + `limit()` + `skip()` → Advanced Collectors (
`groupingBy`, `partitioningBy`) → `Optional` → Primitive Streams → Parallel Streams