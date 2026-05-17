# 🗂️ Java Stream `groupingBy` — Complete Guide

> A complete, level-by-level guide to mastering `Collectors.groupingBy()` in Java Streams — from basics to advanced
> patterns.

---

## 📑 Table of Contents

- [What is groupingBy?](#what-is-groupingby)
- [How it Works Internally](#how-it-works-internally)
- [Data Setup](#data-setup)
- [🟢 Level 1 — Basic groupingBy](#-level-1--basic-groupingby)
- [🟡 Level 2 — Downstream Collectors](#-level-2--downstream-collectors)
- [🟠 Level 3 — Multi-level & Filtering](#-level-3--multi-level--filtering)
- [🔴 Level 4 — Advanced Patterns](#-level-4--advanced-patterns)
- [🔴 Level 4 — Advanced Patterns](#-level-4--advanced-patterns)
- [🎯 Interview Problems](#-interview-problems--most-asked-questions)
    - [Category 1 — String / Word Problems](#-category-1--string--word-problems)
    - [Category 2 — Number / List Problems](#-category-2--number--list-problems)
    - [Category 3 — Employee / Object Problems](#-category-3--employee--object-problems)
    - [Category 4 — Tricky / Edge Case Problems](#-category-4--tricky--edge-case-problems)
    - [Interview Cheat Sheet](#-interview-cheat-sheet)
- [Quick Reference Table](#quick-reference-table)
- [Complete Roadmap](#complete-roadmap)

---

## What is `groupingBy`?

`Collectors.groupingBy()` is a terminal collector in Java Streams that **groups elements into a `Map`** based on a
classification function.

```
Stream<T>  ──►  groupingBy(classifier, downstream)  ──►  Map<K, V>
```

### Simple Mental Model

```
groupingBy(
    WHAT se group karo?   →  यही MAP की KEY   बनती है  (1st argument)
    GROUP ka kya karo?    →  यही MAP की VALUE बनती है  (2nd argument)
)
```

### Method Signatures

```java
// Signature 1 — 1 argument (default VALUE = List<T>)
Collectors.groupingBy(Function<T, K> classifier)

// Signature 2 — 2 arguments (custom downstream collector)
Collectors.

groupingBy(Function<T, K> classifier,
           Collector<T, A, D> downstream)

// Signature 3 — 3 arguments (custom Map type + downstream)
Collectors.

groupingBy(Function<T, K> classifier,
           Supplier<Map<K, D>> mapFactory,
           Collector<T, A, D> downstream)
```

---

## How it Works Internally

`groupingBy` internally works in **4 phases**:

```
┌─────────────────────────────────────────────┐
│           COLLECTOR ENGINE                  │
│                                             │
│  Phase 1: supplier()   → Map<> result = {}  │
│                                             │
│  Phase 2: accumulator()→ har element ke     │
│     a) key = classifier.apply(element)      │
│     b) computeIfAbsent(key, new List())     │
│     c) downstream.accumulate(key, element)  │
│                                             │
│  Phase 3: combiner()   → parallel merge     │
│                                             │
│  Phase 4: finisher()   → final Map return   │
└──────────────────┬──────────────────────────┘
                   │
                   ▼
         Map<String, Long>
         { Eng=3, HR=2, Mkt=2 }
```

### Step-by-step Accumulation Example

```
DATA: [Alice/Eng, Bob/Eng, Charlie/HR, Diana/HR, Frank/Eng]

Step 1: Alice(Eng)   → key="Engineering" → map={"Engineering":[Alice]}
Step 2: Bob(Eng)     → key="Engineering" → map={"Engineering":[Alice,Bob]}
Step 3: Charlie(HR)  → key="HR"          → map={"Engineering":[Alice,Bob], "HR":[Charlie]}
Step 4: Diana(HR)    → key="HR"          → map={"Engineering":[Alice,Bob], "HR":[Charlie,Diana]}
Step 5: Frank(Eng)   → key="Engineering" → map={"Engineering":[Alice,Bob,Frank], "HR":[Charlie,Diana]}
```

### The Key Internal Method — `computeIfAbsent`

```java
// यह एक line 2 काम करती है:
map.computeIfAbsent(key, k ->new ArrayList<>()).

add(element);

// CASE 1 — Key already exists:
//   → existing List return करो → .add(element)

// CASE 2 — Key does not exist:
//   → new ArrayList() बनाओ → map में डालो → .add(element)
```

---

## Data Setup

> ⚠️ **सभी Problems के लिए यही Data और Employee class use करो।**

```java
import java.util.*;
import java.util.stream.*;

class Employee {
    String name, department, city, gender;
    int age;
    double salary;
    boolean active;

    Employee(String name, String department, String city,
             String gender, int age, double salary, boolean active) {
        this.name = name;
        this.department = department;
        this.city = city;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return name;
    }
}

List<Employee> employees = Arrays.asList(
        new Employee("Alice", "Engineering", "Delhi", "F", 28, 90000, true),
        new Employee("Bob", "Engineering", "Mumbai", "M", 35, 85000, true),
        new Employee("Charlie", "HR", "Delhi", "M", 40, 60000, false),
        new Employee("Diana", "HR", "Mumbai", "F", 32, 62000, true),
        new Employee("Eve", "Marketing", "Delhi", "F", 27, 70000, true),
        new Employee("Frank", "Engineering", "Chennai", "M", 45, 95000, true),
        new Employee("Grace", "Marketing", "Mumbai", "F", 30, 72000, false),
        new Employee("Hank", "HR", "Chennai", "M", 38, 58000, true),
        new Employee("Ivy", "Engineering", "Delhi", "F", 26, 88000, true),
        new Employee("Jack", "Marketing", "Chennai", "M", 33, 75000, true)
);
```

### Data Overview

| Name    | Department  | City    | Gender | Age | Salary | Active |
|---------|-------------|---------|--------|-----|--------|--------|
| Alice   | Engineering | Delhi   | F      | 28  | 90000  | ✅      |
| Bob     | Engineering | Mumbai  | M      | 35  | 85000  | ✅      |
| Charlie | HR          | Delhi   | M      | 40  | 60000  | ❌      |
| Diana   | HR          | Mumbai  | F      | 32  | 62000  | ✅      |
| Eve     | Marketing   | Delhi   | F      | 27  | 70000  | ✅      |
| Frank   | Engineering | Chennai | M      | 45  | 95000  | ✅      |
| Grace   | Marketing   | Mumbai  | F      | 30  | 72000  | ❌      |
| Hank    | HR          | Chennai | M      | 38  | 58000  | ✅      |
| Ivy     | Engineering | Delhi   | F      | 26  | 88000  | ✅      |
| Jack    | Marketing   | Chennai | M      | 33  | 75000  | ✅      |

---

---

# 🟢 Level 1 — Basic `groupingBy`

> **Goal:** किसी भी field पर group करना सीखो। Default VALUE हमेशा `List<T>` होती है।

---

### Problem 1 — Department wise Employees की List बनाओ

**Concept:** Basic `groupingBy` — 1 argument, default VALUE = `List<T>`

```java
Map<String, List<Employee>> byDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment    // ← KEY = department name
        ));                            // ← VALUE = List<Employee> (default)

byDept.

forEach((dept, emps) ->
        System.out.

println(dept +" → "+emps));
```

**Output:**

```
Engineering → [Alice, Bob, Frank, Ivy]
HR          → [Charlie, Diana, Hank]
Marketing   → [Eve, Grace, Jack]
```

**Explanation:**

```
Map<  String       ,  List<Employee>        >
      ↑ KEY              ↑ VALUE
      getDepartment()    (default = toList)

"Engineering" → [Alice, Bob, Frank, Ivy]   // 4 employees
"HR"          → [Charlie, Diana, Hank]     // 3 employees
"Marketing"   → [Eve, Grace, Jack]         // 3 employees
```

---

### Problem 2 — Department wise Employee COUNT करो

**Concept:** `counting()` downstream — VALUE `Long` बन जाती है

```java
Map<String, Long> countByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,   // ← 1st arg = KEY  (String)
                Collectors.counting()      // ← 2nd arg = VALUE (Long)
        ));

System.out.

println(countByDept);
```

**Output:**

```
{Engineering=4, HR=3, Marketing=3}
```

**Explanation:**

```
Map<  String       ,  Long         >
      ↑ KEY              ↑ VALUE
      getDepartment()    counting()

Internally:
  "Engineering" group → 4 employees → count = 4
  "HR"          group → 3 employees → count = 3
  "Marketing"   group → 3 employees → count = 3
```

---

### Problem 3 — City wise Group करो

**Concept:** किसी भी field पर group कर सकते हो, सिर्फ classifier बदलो

```java
Map<String, List<Employee>> byCity = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getCity    // ← KEY = city name
        ));

byCity.

forEach((city, emps) ->
        System.out.

println(city +" → "+emps));
```

**Output:**

```
Delhi   → [Alice, Charlie, Eve, Ivy]
Mumbai  → [Bob, Diana, Grace]
Chennai → [Frank, Hank, Jack]
```

---

### Problem 4 — Gender wise Group करो

**Concept:** Small category (Boolean/Enum-like) fields पर grouping

```java
Map<String, List<Employee>> byGender = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getGender    // ← KEY = "M" or "F"
        ));

byGender.

forEach((gender, emps) ->
        System.out.

println(gender +" → "+emps));
```

**Output:**

```
F → [Alice, Diana, Eve, Grace, Ivy]
M → [Bob, Charlie, Frank, Hank, Jack]
```

---

---

# 🟡 Level 2 — Downstream Collectors

> **Goal:** Group बनाने के बाद उस group के साथ कुछ करो — sum, avg, max, join आदि।
> `groupingBy(classifier, downstreamCollector)` — 2nd argument = downstream

---

### Problem 5 — Department wise Total Salary निकालो

**Concept:** `summingDouble()` — numbers का sum

```java
Map<String, Double> totalSalary = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summingDouble(Employee::getSalary)   // ← sum of salaries
        ));

totalSalary.

forEach((dept, total) ->
        System.out.

printf("%-15s → ₹%.0f%n",dept, total));
```

**Output:**

```
Engineering     → ₹358000
HR              → ₹180000
Marketing       → ₹217000
```

**Explanation:**

```
"Engineering" group → [90000, 85000, 95000, 88000] → sum = 358000
"HR"          group → [60000, 62000, 58000]         → sum = 180000
"Marketing"   group → [70000, 72000, 75000]         → sum = 217000
```

---

### Problem 6 — Department wise Average Salary निकालो

**Concept:** `averagingDouble()` — average value

```java
Map<String, Double> avgSalary = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)   // ← average salary
        ));

avgSalary.

forEach((dept, avg) ->
        System.out.

printf("%-15s → ₹%.2f%n",dept, avg));
```

**Output:**

```
Engineering     → ₹89500.00
HR              → ₹60000.00
Marketing       → ₹72333.33
```

---

### Problem 7 — Department wise सिर्फ Names की List चाहिए

**Concept:** `mapping()` — पहले object को transform करो, फिर collect करो

```java
Map<String, List<String>> namesByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                        Employee::getName,       // ← Employee  →  String (name extract)
                        Collectors.toList()      // ← collect names as List<String>
                )
        ));

namesByDept.

forEach((dept, names) ->
        System.out.

println(dept +" → "+names));
```

**Output:**

```
Engineering → [Alice, Bob, Frank, Ivy]
HR          → [Charlie, Diana, Hank]
Marketing   → [Eve, Grace, Jack]
```

**Explanation:**

```
Without mapping:   Map<String, List<Employee>>
With mapping:      Map<String, List<String>>    ← only names

mapping() = 2 steps:
  Step 1: Employee::getName  →  transform each Employee to its name (String)
  Step 2: Collectors.toList() →  collect those Strings into a List
```

---

### Problem 8 — Department wise Max Salary Employee निकालो

**Concept:** `maxBy()` — group में सबसे बड़ा element निकालो

```java
Map<String, Optional<Employee>> maxSalary = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(
                        Comparator.comparingDouble(Employee::getSalary)  // ← compare by salary
                )
        ));

maxSalary.

forEach((dept, emp) ->
        System.out.

println(dept +" → "
        +emp.get().

getName()
        +" (₹"+emp.

get().

getSalary() +")"));
```

**Output:**

```
Engineering → Frank (₹95000.0)
HR          → Diana (₹62000.0)
Marketing   → Jack  (₹75000.0)
```

> **Note:** `maxBy()` returns `Optional<Employee>` क्योंकि group empty भी हो सकता है।

---

### Problem 9 — Department wise Min Salary Employee निकालो

**Concept:** `minBy()` — group में सबसे छोटा element निकालो

```java
Map<String, Optional<Employee>> minSalary = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.minBy(
                        Comparator.comparingDouble(Employee::getSalary)
                )
        ));

minSalary.

forEach((dept, emp) ->
        System.out.

println(dept +" → "
        +emp.get().

getName()
        +" (₹"+emp.

get().

getSalary() +")"));
```

**Output:**

```
Engineering → Bob  (₹85000.0)
HR          → Hank (₹58000.0)
Marketing   → Eve  (₹70000.0)
```

---

### Problem 10 — Department wise Names को Comma Join करो

**Concept:** `mapping()` + `joining()` — names को एक String में जोड़ो

```java
Map<String, String> joinedNames = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                        Employee::getName,
                        Collectors.joining(", ")    // ← join with comma separator
                )
        ));

joinedNames.

forEach((dept, names) ->
        System.out.

println(dept +" → "+names));
```

**Output:**

```
Engineering → Alice, Bob, Frank, Ivy
HR          → Charlie, Diana, Hank
Marketing   → Eve, Grace, Jack
```

**Explanation:**

```
joining(", ")         →  "Alice, Bob, Frank, Ivy"
joining(", ", "[", "]") →  "[Alice, Bob, Frank, Ivy]"   // with prefix & suffix
```

---

---

# 🟠 Level 3 — Multi-level & Filtering

> **Goal:** Nested grouping, filtering inside groups, custom keys, और complete statistics।

---

### Problem 11 — Department → City wise Nested Group करो

**Concept:** `groupingBy` के अंदर `groupingBy` — `Map<K, Map<K, List<T>>>`

```java
Map<String, Map<String, List<Employee>>> deptCity = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,          // ← OUTER KEY = department
                Collectors.groupingBy(            // ← VALUE = another groupBy
                        Employee::getCity             // ← INNER KEY = city
                )
        ));

deptCity.

forEach((dept, cityMap) ->{
        System.out.

println("📁 "+dept);
    cityMap.

forEach((city, emps) ->
        System.out.

println("   └─ "+city +" → "+emps));
        });
```

**Output:**

```
📁 Engineering
   └─ Delhi   → [Alice, Ivy]
   └─ Mumbai  → [Bob]
   └─ Chennai → [Frank]
📁 HR
   └─ Delhi   → [Charlie]
   └─ Mumbai  → [Diana]
   └─ Chennai → [Hank]
📁 Marketing
   └─ Delhi   → [Eve]
   └─ Mumbai  → [Grace]
   └─ Chennai → [Jack]
```

**Map Structure:**

```
Map<String, Map<String, List<Employee>>>
     ↑ dept       ↑ city    ↑ employees

{
  "Engineering" → {
      "Delhi"   → [Alice, Ivy],
      "Mumbai"  → [Bob],
      "Chennai" → [Frank]
  },
  "HR" → {
      "Delhi"   → [Charlie],
      ...
  }
}
```

---

### Problem 12 — Department → City wise Employee COUNT करो

**Concept:** 3 levels — groupBy → groupBy → counting

```java
Map<String, Map<String, Long>> deptCityCount = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.groupingBy(
                        Employee::getCity,
                        Collectors.counting()         // ← innermost = count
                )
        ));

deptCityCount.

forEach((dept, cityMap) ->
        System.out.

println(dept +" → "+cityMap));
```

**Output:**

```
Engineering → {Delhi=2, Mumbai=1, Chennai=1}
HR          → {Delhi=1, Mumbai=1, Chennai=1}
Marketing   → {Delhi=1, Mumbai=1, Chennai=1}
```

---

### Problem 13 — Active Employees को Department wise Group करो

**Concept:** `filter()` + `groupingBy()` — पहले filter, फिर group

```java
Map<String, List<Employee>> activeByDept = employees.stream()
        .filter(Employee::isActive)           // ← Step 1: filter active only
        .collect(Collectors.groupingBy(
                Employee::getDepartment           // ← Step 2: group filtered result
        ));

activeByDept.

forEach((dept, emps) ->
        System.out.

println(dept +" → "+emps));
```

**Output:**

```
Engineering → [Alice, Bob, Frank, Ivy]
HR          → [Diana, Hank]
Marketing   → [Eve, Jack]
```

**Why Charlie and Grace are missing?**

```
Charlie → active=false → filtered out ❌
Grace   → active=false → filtered out ❌
```

---

### Problem 14 — Age Range wise Custom Group बनाओ

**Concept:** Lambda से Custom KEY — classifier कोई भी logic हो सकता है

```java
Map<String, List<Employee>> byAgeGroup = employees.stream()
        .collect(Collectors.groupingBy(emp -> {
            // ← Custom classifier lambda
            if (emp.getAge() < 30) return "Junior (< 30)";
            else if (emp.getAge() < 40) return "Mid (30-39)";
            else return "Senior (40+)";
        }));

byAgeGroup.

forEach((group, emps) ->
        System.out.

println(group +" → "+emps));
```

**Output:**

```
Junior (< 30)  → [Alice, Eve, Ivy]
Mid (30-39)    → [Bob, Diana, Grace, Jack]
Senior (40+)   → [Charlie, Frank, Hank]
```

**Explanation:**

```
Age grouping logic:
  Alice(28)   → < 30   → "Junior"
  Bob(35)     → 30-39  → "Mid"
  Charlie(40) → >= 40  → "Senior"
  Diana(32)   → 30-39  → "Mid"
  Eve(27)     → < 30   → "Junior"
  Frank(45)   → >= 40  → "Senior"
  ...
```

---

### Problem 15 — Department wise Complete Statistics (Count+Sum+Avg+Min+Max)

**Concept:** `summarizingDouble()` — एक call में सारी statistics

```java
Map<String, DoubleSummaryStatistics> stats = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summarizingDouble(Employee::getSalary)   // ← all stats at once
        ));

stats.

forEach((dept, s) ->
        System.out.

printf(
        "%-15s → Count=%d | Sum=₹%.0f | Avg=₹%.0f | Min=₹%.0f | Max=₹%.0f%n",
        dept, s.getCount(),s.

getSum(),
        s.

getAverage(),s.

getMin(),s.

getMax()
    ));
```

**Output:**

```
Engineering     → Count=4 | Sum=₹358000 | Avg=₹89500 | Min=₹85000 | Max=₹95000
HR              → Count=3 | Sum=₹180000 | Avg=₹60000 | Min=₹58000 | Max=₹62000
Marketing       → Count=3 | Sum=₹217000 | Avg=₹72333 | Min=₹70000 | Max=₹75000
```

**`DoubleSummaryStatistics` methods:**

```java
s.getCount()    // → long   — total elements
s.

getSum()      // → double — total sum
s.

getAverage()  // → double — average
s.

getMin()      // → double — minimum value
s.

getMax()      // → double — maximum value
```

---

---

# 🔴 Level 4 — Advanced Patterns

> **Goal:** Real-world complex use cases — `partitioningBy`, `collectingAndThen`, `filtering`, custom Map types।

---

### Problem 16 — `partitioningBy` — Active vs Inactive split करो

**Concept:** `partitioningBy` = `groupingBy` का special case — सिर्फ 2 groups (true / false)

```java
Map<Boolean, List<Employee>> partitioned = employees.stream()
        .collect(Collectors.partitioningBy(
                Employee::isActive    // ← predicate: true or false
        ));

System.out.

println("✅ Active   → "+partitioned.get(true));
        System.out.

println("❌ Inactive → "+partitioned.get(false));
```

**Output:**

```
✅ Active   → [Alice, Bob, Diana, Eve, Frank, Hank, Ivy, Jack]
❌ Inactive → [Charlie, Grace]
```

**groupingBy vs partitioningBy:**

```
groupingBy     → Map<K, List<T>>        K can be any type
                                         Can have N groups

partitioningBy → Map<Boolean, List<T>>  KEY is always Boolean
                                         Always exactly 2 groups: true & false
```

---

### Problem 17 — Department wise Top 2 Highest Paid निकालो

**Concept:** `collectingAndThen()` — collect के बाद extra transformation करो

```java
Map<String, List<Employee>> top2ByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.toList(),                              // ← Step 1: collect all
                        list -> list.stream()
                                .sorted(Comparator.comparingDouble(
                                        Employee::getSalary).reversed())          // ← Step 2: sort desc
                                .limit(2)                                     // ← Step 3: top 2 only
                                .collect(Collectors.toList())
                )
        ));

top2ByDept.

forEach((dept, emps) ->
        System.out.

println(dept +" → "+emps));
```

**Output:**

```
Engineering → [Frank, Alice]
HR          → [Diana, Charlie]
Marketing   → [Jack, Grace]
```

**How `collectingAndThen` works:**

```
collectingAndThen(
    downstream,    // ← पहले यह collect करो
    finisher       // ← फिर result को transform करो
)

Example:
  Step 1: toList()    → [Alice(90k), Bob(85k), Frank(95k), Ivy(88k)]
  Step 2: sort desc   → [Frank(95k), Alice(90k), Ivy(88k), Bob(85k)]
  Step 3: limit(2)    → [Frank(95k), Alice(90k)]
```

---

### Problem 18 — Department wise Salary 70k+ वाले Count करो

**Concept:** `filtering()` — downstream के अंदर filter (Java 9+)

```java
Map<String, Long> highEarners = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.filtering(                        // ← filter inside group
                        emp -> emp.getSalary() > 70000,          // ← predicate
                        Collectors.counting()                    // ← then count
                )
        ));

System.out.

println(highEarners);
```

**Output:**

```
{Engineering=4, HR=0, Marketing=2}
```

**`filter()` before vs `filtering()` inside:**

```java
// APPROACH 1 — filter() BEFORE groupingBy
// ⚠️ Groups with 0 matches won't appear in result!
employees.stream()
    .

filter(e ->e.

getSalary() >70000)
        .

collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
// Result: {Engineering=4, Marketing=2}   ← "HR" missing! ❌

// APPROACH 2 — filtering() INSIDE groupingBy
// ✅ All groups appear, even if count = 0
        employees.

stream()
    .

collect(Collectors.groupingBy(
                Employee::getDepartment,
        Collectors.filtering(e ->e.

getSalary() >70000,Collectors.

counting())
        ));
// Result: {Engineering=4, HR=0, Marketing=2}  ← "HR" = 0 ✅
```

---

### Problem 19 — Department wise Names Sorted Alphabetically

**Concept:** `mapping()` + `collectingAndThen()` combo — transform फिर sort

```java
Map<String, List<String>> sortedNames = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                        Employee::getName,                               // ← transform to name
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> {
                                    Collections.sort(list);
                                    return list;
                                }  // ← sort names
                        )
                )
        ));

sortedNames.

forEach((dept, names) ->
        System.out.

println(dept +" → "+names));
```

**Output:**

```
Engineering → [Alice, Bob, Frank, Ivy]
HR          → [Charlie, Diana, Hank]
Marketing   → [Eve, Grace, Jack]
```

---

### Problem 20 — Result को `TreeMap` में चाहिए (Sorted Keys)

**Concept:** 3-argument `groupingBy` — Map का type खुद specify करो

```java
// DEFAULT: HashMap (keys unordered)
Map<String, Long> hashMap = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
// {Marketing=3, Engineering=4, HR=3}  ← unordered ❌

// CUSTOM: TreeMap (keys alphabetically sorted)
Map<String, Long> treeMap = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,    // ← 1st: classifier
                TreeMap::new,               // ← 2nd: Map factory (TreeMap instead of HashMap)
                Collectors.counting()       // ← 3rd: downstream
        ));

System.out.

println(treeMap);
```

**Output:**

```
{Engineering=4, HR=3, Marketing=3}   ← alphabetical order ✅
```

**Different Map types:**

```java
TreeMap::new       →
Sorted by

natural order(A → Z)

LinkedHashMap::new →
Insertion order
preserved
HashMap::new       →Default,
no guaranteed
order
```

---

---

---

---

# 🎯 Interview Problems — Most Asked Questions

> ये वो problems हैं जो top companies (Amazon, Google, Flipkart, TCS, Infosys) के interviews में पूछी जाती हैं। हर
> problem के साथ **interviewer का असली intent** और **follow-up questions** भी दिए हैं।

---

## 🔥 Category 1 — String / Word Problems

---

### Interview Problem 1 — Most Frequent Character in a String

**❓ Question:** Given a String, find the character that appears most frequently.

**💡 Interviewer checks:** `groupingBy` + `counting` + `max` combo

```java
String str = "aabbcccddddee";

Optional<Map.Entry<Character, Long>> result = str.chars()
        .mapToObj(c -> (char) c)                          // IntStream → Stream<Character>
        .collect(Collectors.groupingBy(
                c -> c,                                        // KEY = character itself
                Collectors.counting()                          // VALUE = frequency count
        ))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue());                // pick max frequency entry

System.out.

println("Char: "+result.get().

getKey()
                 +" | Count: "+result.

get().

getValue());
```

**Output:**

```
Char: d | Count: 4
```

**Step-by-step breakdown:**

```
"aabbcccddddee"
  ↓ chars() + mapToObj
  Stream<Character>: [a,a,b,b,c,c,c,d,d,d,d,e,e]
  ↓ groupingBy(c->c, counting())
  Map: {a=2, b=2, c=3, d=4, e=2}
  ↓ entrySet().stream().max(comparingByValue)
  d=4  ← winner
```

**🔁 Follow-up Questions:**

- Top 3 frequent characters निकालो → `.sorted().limit(3)`
- Least frequent character निकालो → `.min(comparingByValue())`
- Ignore spaces/special chars → `.filter(Character::isLetter)` पहले add करो

---

### Interview Problem 2 — Word Frequency Count in a Sentence

**❓ Question:** Count how many times each word appears in a sentence.

**💡 Interviewer checks:** String split + groupingBy + sorting

```java
String sentence = "the cat sat on the mat the cat sat";

Map<String, Long> wordFreq = Arrays.stream(sentence.split(" "))
        .collect(Collectors.groupingBy(
                w -> w,                       // KEY = word itself
                Collectors.counting()         // VALUE = count
        ));

// Print sorted by frequency (highest first)
wordFreq.

entrySet().

stream()
    .

sorted(Map.Entry .<String, Long>comparingByValue().

reversed())
        .

forEach(e ->System.out.

println(e.getKey() +" → "+e.

getValue()));
```

**Output:**

```
the → 3
cat → 2
sat → 2
on  → 1
mat → 1
```

**🔁 Follow-up Questions:**

- Case-insensitive count करो → `.toLowerCase()` add करो classifier में
- Ignore stop words ("the", "on", "a") → `.filter()` पहले add करो
- First word with frequency > 2 निकालो → `.filter(e -> e.getValue() > 2).findFirst()`

---

### Interview Problem 3 — Group Strings by their Length

**❓ Question:** Group a list of strings by their length.

**💡 Interviewer checks:** Custom classifier (non-field method)

```java
List<String> words = Arrays.asList(
        "hi", "hey", "hello", "bye", "ok", "see", "world", "java"
);

Map<Integer, List<String>> byLength = words.stream()
        .collect(Collectors.groupingBy(String::length));

byLength.

forEach((len, ws) ->
        System.out.

println("Length "+len +" → "+ws));
```

**Output:**

```
Length 2 → [hi, ok]
Length 3 → [hey, bye, see]
Length 4 → [java]
Length 5 → [hello, world]
```

**🔁 Follow-up Questions:**

- Longest group (most words with same length) कौन सी है?
  ```java
  byLength.entrySet().stream()
      .max(Comparator.comparingInt(e -> e.getValue().size()))
      .ifPresent(e -> System.out.println("Length " + e.getKey() + ": " + e.getValue()));
  ```
- Lengths जिनमें सिर्फ 1 word है → `.filter(e -> e.getValue().size() == 1)`

---

### Interview Problem 4 — Find Anagram Groups

**❓ Question:** Group words that are anagrams of each other.

**💡 Interviewer checks:** Custom classifier with sorting logic

```java
List<String> words = Arrays.asList(
        "eat", "tea", "tan", "ate", "nat", "bat", "tab"
);

Map<String, List<String>> anagrams = words.stream()
        .collect(Collectors.groupingBy(word -> {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);                 // sort chars → same for all anagrams
            return new String(chars);           // KEY = sorted char string
        }));

anagrams.

forEach((key, group) ->
        System.out.

println(key +" → "+group));
```

**Output:**

```
aet → [eat, tea, ate]
ant → [tan, nat]
abt → [bat, tab]
```

**Key Insight:**

```
"eat" → sort → "aet"   ─┐
"tea" → sort → "aet"   ─┤  same KEY → same group
"ate" → sort → "aet"   ─┘
```

**🔁 Follow-up Questions:**

- Largest anagram group निकालो
- Groups जिनमें 3+ anagrams हों → `.filter(e -> e.getValue().size() >= 3)`

---

## 🔥 Category 2 — Number / List Problems

---

### Interview Problem 5 — Numbers को Even/Odd में Partition करो

**❓ Question:** Given a list of numbers, separate even and odd numbers.

**💡 Interviewer checks:** `partitioningBy` usage

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

Map<Boolean, List<Integer>> evenOdd = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

System.out.

println("Even → "+evenOdd.get(true));
        System.out.

println("Odd  → "+evenOdd.get(false));
```

**Output:**

```
Even → [2, 4, 6, 8, 10]
Odd  → [1, 3, 5, 7, 9]
```

**🔁 Follow-up Questions:**

- Even numbers का sum निकालो → `partitioningBy(pred, summingInt(i->i))`
- Prime numbers को separate करो → custom isPrime() method use करो

---

### Interview Problem 6 — Numbers को Range-wise Group करो

**❓ Question:** Group numbers into ranges: 1-10, 11-20, 21-30 etc.

**💡 Interviewer checks:** Custom lambda classifier with math

```java
List<Integer> numbers = Arrays.asList(
        3, 7, 12, 15, 22, 25, 8, 19, 28, 5, 11, 30
);

Map<String, List<Integer>> byRange = numbers.stream()
        .collect(Collectors.groupingBy(n -> {
            int rangeStart = ((n - 1) / 10) * 10 + 1;   // formula for range start
            int rangeEnd = rangeStart + 9;
            return rangeStart + "-" + rangeEnd;
        }));

// Sort by range key and print
new TreeMap<>(byRange).

forEach((range, nums) ->
        System.out.

println(range +" → "+nums));
```

**Output:**

```
1-10  → [3, 7, 8, 5]
11-20 → [12, 15, 19, 11]
21-30 → [22, 25, 28, 30]
```

---

### Interview Problem 7 — Duplicate Elements निकालो

**❓ Question:** Find all elements that appear more than once in a list.

**💡 Interviewer checks:** groupingBy + counting + filter combo

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 3, 5, 1, 6, 1);

List<Integer> duplicates = numbers.stream()
        .collect(Collectors.groupingBy(
                n -> n,                      // KEY = number itself
                Collectors.counting()        // VALUE = how many times
        ))
        .entrySet().stream()
        .filter(e -> e.getValue() > 1)   // keep only duplicates
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());

System.out.

println("Duplicates: "+duplicates);
```

**Output:**

```
Duplicates: [1, 2, 3]
```

**🔁 Follow-up Questions:**

- सबसे ज़्यादा duplicate element कौन सा है?
  ```java
  numbers.stream()
      .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
      .entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .ifPresent(e -> System.out.println("Most duplicate: " + e.getKey()
                                       + " (" + e.getValue() + " times)"));
  ```

---

## 🔥 Category 3 — Employee / Object Problems

---

### Interview Problem 8 — Department wise Salary Bucket बनाओ

**❓ Question:** In each department, classify employees as Low/Mid/High salary.

**💡 Interviewer checks:** Nested groupBy with custom lambda KEY

```java
Map<String, Map<String, List<Employee>>> salaryBucket = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,           // OUTER KEY = department
                Collectors.groupingBy(emp -> {     // INNER KEY = salary category
                    if (emp.getSalary() < 65000) return "🔴 Low";
                    else if (emp.getSalary() < 85000) return "🟡 Mid";
                    else return "🟢 High";
                })
        ));

salaryBucket.

forEach((dept, buckets) ->{
        System.out.

println("\n📁 "+dept);
    buckets.

forEach((bucket, emps) ->
        System.out.

println("   "+bucket +" → "+emps));
        });
```

**Output:**

```
📁 Engineering
   🟢 High → [Alice, Frank, Ivy]
   🟡 Mid  → [Bob]

📁 HR
   🔴 Low  → [Charlie, Hank]
   🟡 Mid  → [Diana]

📁 Marketing
   🟡 Mid  → [Eve, Grace, Jack]
```

---

### Interview Problem 9 — 2nd Highest Salary Employee प्रत्येक Department में

**❓ Question:** Find the employee with 2nd highest salary in each department.

**💡 Interviewer checks:** collectingAndThen + sort + skip

```java
Map<String, Optional<Employee>> secondHighest = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                                .sorted(Comparator.comparingDouble(
                                        Employee::getSalary).reversed())   // sort high to low
                                .skip(1)                               // skip the 1st (highest)
                                .findFirst()                           // take 2nd
                )
        ));

secondHighest.

forEach((dept, emp) ->
        emp.

ifPresent(e ->
        System.out.

println(dept +" → "+e.getName()
                         +" (₹"+e.

getSalary() +")")));
```

**Output:**

```
Engineering → Alice (₹90000.0)
HR          → Diana (₹62000.0)
Marketing   → Grace (₹72000.0)
```

---

### Interview Problem 10 — Department wise Average Age निकालो (only Active)

**❓ Question:** Find average age of active employees per department.

**💡 Interviewer checks:** filter + groupBy + averagingInt combo

```java
Map<String, Double> avgAgeActive = employees.stream()
        .filter(Employee::isActive)                          // only active
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingInt(Employee::getAge)        // average age
        ));

avgAgeActive.

forEach((dept, avg) ->
        System.out.

printf("%-15s → Avg Age: %.1f%n",dept, avg));
```

**Output:**

```
Engineering     → Avg Age: 31.0
HR              → Avg Age: 35.0
Marketing       → Avg Age: 30.0
```

---

### Interview Problem 11 — Department wise Highest & Lowest Salary एक साथ

**❓ Question:** For each department, get both min and max salary in one pass.

**💡 Interviewer checks:** `summarizingDouble` या `toMap` + reduce

```java
Map<String, IntSummaryStatistics> salaryStats = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summarizingInt(e -> (int) e.getSalary())
        ));

salaryStats.

forEach((dept, stats) ->
        System.out.

printf("%-15s → Min: ₹%d | Max: ₹%d | Gap: ₹%d%n",
       dept,
        (int) stats.

getMin(),
        (int)stats.

getMax(),
        (int)(stats.

getMax() -stats.

getMin())));   // salary gap
```

**Output:**

```
Engineering     → Min: ₹85000 | Max: ₹95000 | Gap: ₹10000
HR              → Min: ₹58000 | Max: ₹62000 | Gap: ₹4000
Marketing       → Min: ₹70000 | Max: ₹75000 | Gap: ₹5000
```

---

### Interview Problem 12 — City + Department wise Employee Count (2-level count)

**❓ Question:** How many employees are in each city for each department?

**💡 Interviewer checks:** 3-level stream — groupBy → groupBy → counting

```java
Map<String, Map<String, Long>> cityDeptCount = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getCity,                      // OUTER = city
                TreeMap::new,                           // sorted city keys
                Collectors.groupingBy(
                        Employee::getDepartment,            // INNER = department
                        TreeMap::new,                       // sorted dept keys
                        Collectors.counting()               // count
                )
        ));

cityDeptCount.

forEach((city, deptMap) ->{
        System.out.

println("🏙️  "+city);
    deptMap.

forEach((dept, count) ->
        System.out.

println("    ├─ "+dept +": "+count));
        });
```

**Output:**

```
🏙️  Chennai
    ├─ Engineering: 1
    ├─ HR: 1
    ├─ Marketing: 1
🏙️  Delhi
    ├─ Engineering: 2
    ├─ HR: 1
    ├─ Marketing: 1
🏙️  Mumbai
    ├─ Engineering: 1
    ├─ HR: 1
    ├─ Marketing: 1
```

---

### Interview Problem 13 — Department wise Employee Names as a Formatted String

**❓ Question:** For each department, return names in format: `[Alice | Bob | Frank]`

**💡 Interviewer checks:** `mapping` + `joining` with prefix/suffix

```java
Map<String, String> formattedNames = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                        Employee::getName,
                        Collectors.joining(" | ", "[", "]")   // delimiter, prefix, suffix
                )
        ));

formattedNames.

forEach((dept, names) ->
        System.out.

println(dept +"  →  "+names));
```

**Output:**

```
Engineering  →  [Alice | Bob | Frank | Ivy]
HR           →  [Charlie | Diana | Hank]
Marketing    →  [Eve | Grace | Jack]
```

---

### Interview Problem 14 — Employees को Experience Band में Group करो

**❓ Question:** Group employees into Fresher / Mid-level / Senior based on age.

**💡 Interviewer checks:** Enum/Custom type as KEY

```java
enum Band {FRESHER, MID_LEVEL, SENIOR}

Map<Band, List<Employee>> byBand = employees.stream()
        .collect(Collectors.groupingBy(emp -> {
            if (emp.getAge() < 28) return Band.FRESHER;
            else if (emp.getAge() < 38) return Band.MID_LEVEL;
            else return Band.SENIOR;
        }));

byBand.

forEach((band, emps) ->
        System.out.

println(band +" → "+emps));
```

**Output:**

```
FRESHER   → [Eve, Ivy]
MID_LEVEL → [Alice, Bob, Diana, Grace, Jack]
SENIOR    → [Charlie, Frank, Hank]
```

**Why Enum as KEY is better:**

```
String KEY  →  typo-prone ("Sennior" vs "Senior") ❌
Enum KEY    →  compile-time safe, IDE autocomplete  ✅
```

---

### Interview Problem 15 — Department wise Active % निकालो

**❓ Question:** What percentage of employees are active in each department?

**💡 Interviewer checks:** Two-pass approach or `collectingAndThen` math

```java
Map<String, Double> activePercent = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            long total = list.size();
                            long active = list.stream().filter(Employee::isActive).count();
                            return (active * 100.0) / total;    // percentage formula
                        }
                )
        ));

activePercent.

forEach((dept, pct) ->
        System.out.

printf("%-15s → %.1f%% active%n",dept, pct));
```

**Output:**

```
Engineering     → 100.0% active
HR              → 66.7% active
Marketing       → 66.7% active
```

---

## 🔥 Category 4 — Tricky / Edge Case Problems

---

### Interview Problem 16 — groupingBy vs partitioningBy — Difference बताओ + Code

**❓ Question:** groupingBy और partitioningBy में क्या फर्क है?

**💡 Interviewer checks:** Conceptual clarity

```java
// groupingBy — N groups, KEY = any type
Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
// Result: {"Engineering":[...], "HR":[...], "Marketing":[...]}  ← 3 groups

// partitioningBy — ALWAYS 2 groups, KEY = Boolean only
Map<Boolean, List<Employee>> partitioned = employees.stream()
        .collect(Collectors.partitioningBy(Employee::isActive));
// Result: {true:[...], false:[...]}  ← exactly 2 groups
```

**Key Differences:**

| Feature     | `groupingBy`                        | `partitioningBy`                 |
|-------------|-------------------------------------|----------------------------------|
| Groups      | N (any number)                      | Always exactly 2                 |
| KEY type    | Any type (String, Integer, Enum...) | Boolean only (true/false)        |
| Use case    | Categories, departments, ranges     | Pass/Fail, Active/Inactive       |
| Empty group | Not included in result              | Both true & false always present |

---

### Interview Problem 17 — `filter()` before vs `filtering()` inside — Difference

**❓ Question:** इन दोनों में क्या फर्क है?

```java
// APPROACH 1: filter() BEFORE groupingBy
Map<String, Long> before = employees.stream()
                .filter(e -> e.getSalary() > 70000)        // filter first
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
System.out.

println("Before: "+before);
// {Engineering=4, Marketing=2}  ← ⚠️ "HR" missing! (0 employees matched)

// APPROACH 2: filtering() INSIDE groupingBy (Java 9+)
Map<String, Long> inside = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.filtering(
                        e -> e.getSalary() > 70000,        // filter inside
                        Collectors.counting()
                )
        ));
System.out.

println("Inside: "+inside);
// {Engineering=4, HR=0, Marketing=2}  ← ✅ "HR"=0 correctly shown
```

**Rule:**

```
filter() BEFORE  →  0-count groups गायब हो जाते हैं  ← use when you don't need 0-groups
filtering() INSIDE → 0-count groups भी दिखते हैं   ← use when ALL groups must appear
```

---

### Interview Problem 18 — Immutable Map में result collect करो

**❓ Question:** groupingBy का result एक unmodifiable Map में कैसे collect करें?

**💡 Interviewer checks:** `collectingAndThen` + `Collections.unmodifiableMap`

```java
Map<String, List<Employee>> immutableMap = employees.stream()
        .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Employee::getDepartment),
                Collections::unmodifiableMap                    // wrap in unmodifiable
        ));

// Try to modify → throws UnsupportedOperationException
try{
        immutableMap.

put("Finance",new ArrayList<>());
        }catch(
UnsupportedOperationException e){
        System.out.

println("❌ Cannot modify! Map is immutable.");
}
```

---

### Interview Problem 19 — Custom Object को Map में Transform करो

**❓ Question:** Group employees by department और result में सिर्फ name+salary का pair चाहिए।

**💡 Interviewer checks:** `mapping()` with custom object / record

```java
// Simple Pair record (Java 16+) or use AbstractMap.SimpleEntry
record NameSalary(String name, double salary) {
}

Map<String, List<NameSalary>> result = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(
                        e -> new NameSalary(e.getName(), e.getSalary()),  // transform
                        Collectors.toList()
                )
        ));

result.

forEach((dept, list) ->{
        System.out.

println(dept +":");
    list.

forEach(ns ->
        System.out.

println("  "+ns.name() +" → ₹"+ns.

salary()));
        });
```

**Output:**

```
Engineering:
  Alice → ₹90000.0
  Bob   → ₹85000.0
  Frank → ₹95000.0
  Ivy   → ₹88000.0
```

---

### Interview Problem 20 — Multi-field पर Group करो (Composite KEY)

**❓ Question:** Department AND Gender दोनों पर एक साथ group करो।

**💡 Interviewer checks:** Composite KEY using String concat or custom class

```java
// METHOD 1: String concatenation as composite key
Map<String, List<Employee>> byDeptGender = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDepartment() + "_" + e.getGender()    // composite KEY
                ));

byDeptGender.

forEach((key, emps) ->
        System.out.

println(key +" → "+emps));
```

**Output:**

```
Engineering_F → [Alice, Ivy]
Engineering_M → [Bob, Frank]
HR_M          → [Charlie, Hank]
HR_F          → [Diana]
Marketing_F   → [Eve, Grace]
Marketing_M   → [Jack]
```

```java
// METHOD 2: List as composite key (cleaner)
Map<List<String>, List<Employee>> byDeptGenderClean = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> Arrays.asList(e.getDepartment(), e.getGender())
                ));

byDeptGenderClean.

forEach((key, emps) ->
        System.out.

println(key +" → "+emps));
```

**Output:**

```
[Engineering, F] → [Alice, Ivy]
[Engineering, M] → [Bob, Frank]
[HR, F]          → [Diana]
[HR, M]          → [Charlie, Hank]
[Marketing, F]   → [Eve, Grace]
[Marketing, M]   → [Jack]
```

---

## 📋 Interview Cheat Sheet

### Top 10 Patterns Interviewers Check

```
Pattern 1:  groupingBy + counting()          → "count elements per group"
Pattern 2:  groupingBy + maxBy/minBy()       → "find max/min per group"
Pattern 3:  groupingBy + averaging/summing() → "aggregate per group"
Pattern 4:  nested groupingBy                → "group within group"
Pattern 5:  filter + groupingBy              → "conditional grouping"
Pattern 6:  groupingBy + collectingAndThen() → "post-process each group"
Pattern 7:  groupingBy + mapping()           → "transform then group"
Pattern 8:  partitioningBy                   → "binary split"
Pattern 9:  groupingBy + custom lambda KEY   → "group by expression"
Pattern 10: groupingBy + TreeMap::new        → "sorted result"
```

### Common Mistakes in Interviews

```java
// ❌ MISTAKE 1: NPE when calling .get() on Optional
maxSalary.get(dept).

getName();            // ← can throw NPE

// ✅ CORRECT: use .ifPresent() or .map()
maxSalary.

get(dept).

ifPresent(e ->System.out.

println(e.getName()));

// ❌ MISTAKE 2: Modifying result of groupingBy
        result.

get("HR").

add(newEmployee);        // ← may throw UnsupportedOperationException

// ✅ CORRECT: collect to mutable list explicitly
Collectors.

groupingBy(dept, Collectors.toCollection(ArrayList::new))

// ❌ MISTAKE 3: Forgetting filter() removes 0-count groups
        .

filter(cond).

collect(groupingBy(..., counting()))   // ← missing groups

// ✅ CORRECT: use filtering() inside for complete result
        .

collect(groupingBy(..., filtering(cond, counting())))
```

### Interview Answer Template

When asked *"How does groupingBy work?"* — answer in 3 parts:

```
1. WHAT:   groupingBy एक Collector है जो Stream को Map में convert करता है।
           1st arg → KEY, 2nd arg → VALUE (default = List<T>)

2. HOW:    Internally HashMap बनाता है → har element का KEY निकालता है →
           computeIfAbsent से list में डालता है → downstream collect करता है।

3. WHEN:   Jab bhi data ko categories/groups में organize karna ho —
           count, sum, avg, max, min per group ke liye use karo।
```

---

## Quick Reference Table

| Downstream Collector         | VALUE Type                | Use Case                       |
|------------------------------|---------------------------|--------------------------------|
| *(none — 1 arg only)*        | `List<T>`                 | सभी elements की list           |
| `counting()`                 | `Long`                    | elements की count              |
| `summingDouble(fn)`          | `Double`                  | numeric sum                    |
| `averagingDouble(fn)`        | `Double`                  | numeric average                |
| `maxBy(comparator)`          | `Optional<T>`             | max element                    |
| `minBy(comparator)`          | `Optional<T>`             | min element                    |
| `mapping(fn, col)`           | `List<R>`                 | transform then collect         |
| `joining(sep)`               | `String`                  | strings को join करो            |
| `summarizingDouble(fn)`      | `DoubleSummaryStatistics` | count+sum+avg+min+max एक साथ   |
| `filtering(pred, col)`       | depends                   | group के अंदर filter (Java 9+) |
| `collectingAndThen(col, fn)` | depends                   | collect फिर transform          |
| `groupingBy(fn)`             | `Map<K, List<T>>`         | nested grouping                |
| `partitioningBy(pred)`       | `Map<Boolean, List<T>>`   | true/false split               |

---

## Complete Roadmap

```
groupingBy
│
├── 🟢 LEVEL 1 — Basic (1 argument)
│   ├── P1  groupBy dept            → Map<String, List<Employee>>
│   ├── P2  groupBy dept + count    → Map<String, Long>
│   ├── P3  groupBy city            → Map<String, List<Employee>>
│   └── P4  groupBy gender          → Map<String, List<Employee>>
│
├── 🟡 LEVEL 2 — Downstream Collectors (2 arguments)
│   ├── P5  summingDouble()         → total salary
│   ├── P6  averagingDouble()       → average salary
│   ├── P7  mapping() + toList()    → only names list
│   ├── P8  maxBy()                 → highest paid employee
│   ├── P9  minBy()                 → lowest paid employee
│   └── P10 mapping() + joining()   → names as comma string
│
├── 🟠 LEVEL 3 — Multi-level & Filtering (nested / custom)
│   ├── P11 nested groupBy          → Map<K, Map<K, List<T>>>
│   ├── P12 nested + counting       → Map<K, Map<K, Long>>
│   ├── P13 filter + groupBy        → pre-filtering
│   ├── P14 custom lambda KEY       → age range grouping
│   └── P15 summarizingDouble()     → all stats at once
│
└── 🔴 LEVEL 4 — Advanced Patterns
    ├── P16 partitioningBy()        → true/false 2 groups
    ├── P17 collectingAndThen()     → post-process (top N)
    ├── P18 filtering() downstream  → filter inside group (Java 9+)
    ├── P19 mapping + andThen combo → transform + sort
    └── P20 TreeMap::new (3-arg)    → custom Map type
```

---

> **Practice Tip 🎯**
> Level 1 → 2 → 3 → 4 इसी order में करो।
> हर level पिछले की foundation पर build होता है।
> हर problem को **बिना solution देखे** solve करने की कोशिश करो — यही सबसे तेज़ तरीका है! 🚀