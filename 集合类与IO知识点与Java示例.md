# JavaSE 集合类与 IO 知识点与 Java 示例

来源：
- 页面：https://www.itbaima.cn/zh-CN/document/k6fmxd6qabgkwm9i
- 页面数据接口：https://api.itbaima.cn/resource/document/query/k6fmxd6qabgkwm9i
- 原页面标题：JavaSE 笔记（六）集合类与IO
- 原页面最后更新时间：2025-07-22 21:56:25

## 1. 知识点总览

### 集合类

- `Collection` 是单列集合根接口，常见子接口有 `List`、`Set`、`Queue`。
- `List` 有序、可重复、可按索引访问，常见实现有 `ArrayList`、`LinkedList`。
- `Iterator` 迭代器用于遍历集合，遍历过程中删除元素应使用 `iterator.remove()`。
- `Queue` 表示队列，常见操作有 `offer()`、`poll()`、`peek()`。
- `Deque` 表示双端队列，也可当栈使用，常用实现是 `ArrayDeque`。
- `Set` 不允许重复元素，常见实现有 `HashSet`、`LinkedHashSet`、`TreeSet`。
- `Map` 是键值对映射，不属于 `Collection`，常见实现有 `HashMap`、`LinkedHashMap`、`TreeMap`。
- `Comparable` 是对象自身的自然排序规则。
- `Comparator` 是外部比较器，可为同一类型提供多种排序规则。
- `Collections` 是集合工具类，提供排序、查找、反转、打乱、不可变集合等方法。
- Java 9 提供集合工厂方法：`List.of()`、`Set.of()`、`Map.of()`，创建不可变集合。
- Java 21 提供有序集合相关规范，例如 `SequencedCollection`、`SequencedSet`、`SequencedMap`，统一首尾访问能力。

### Stream 流

- Stream 用于声明式处理集合数据。
- Stream 不保存数据，只描述处理流水线。
- 常用中间操作：`filter()`、`map()`、`flatMap()`、`distinct()`、`sorted()`、`limit()`、`skip()`。
- 常用终结操作：`forEach()`、`collect()`、`toList()`、`count()`、`anyMatch()`、`allMatch()`、`findFirst()`、`reduce()`。
- Java 9 增强：`takeWhile()`、`dropWhile()`、`ofNullable()`、`iterate()` 新重载。
- Java 16 增强：`stream.toList()`。
- Java 24 引入流聚集器 Gatherer，用于自定义更复杂的流处理阶段。

### Java I/O

- I/O 用于输入输出，常见目标包括文件、内存、网络等。
- 字节流处理原始字节，基类是 `InputStream` 和 `OutputStream`。
- 字符流处理字符文本，基类是 `Reader` 和 `Writer`。
- 文件字节流：`FileInputStream`、`FileOutputStream`。
- 文件字符流：`FileReader`、`FileWriter`。
- 文件工具类：`Files`、`Path`、`Paths`，提供读写、复制、移动、删除等快捷操作。
- 缓冲流：`BufferedInputStream`、`BufferedOutputStream`、`BufferedReader`、`BufferedWriter`，通过缓冲区提升效率。
- 转换流：`InputStreamReader`、`OutputStreamWriter`，负责字节流和字符流之间转换，并可指定字符集。
- 打印流：`PrintStream`、`PrintWriter`，适合格式化输出。
- 数据流：`DataInputStream`、`DataOutputStream`，可按基本类型读写二进制数据。
- 对象流：`ObjectInputStream`、`ObjectOutputStream`，用于对象序列化和反序列化，对象类需实现 `Serializable`。
- 字节数组流：`ByteArrayInputStream`、`ByteArrayOutputStream`，以内存字节数组作为数据源或目标。
- Java 9/11/12 为输入流提供快捷操作，如 `readAllBytes()`、`transferTo()`，文件工具类也提供 `Files.readString()`、`Files.writeString()` 等方法。

### 实战：图书管理系统

- 图书管理系统通常需要实体类、集合存储、增删改查、排序或搜索、文件持久化。
- 可用 `List<Book>` 保存图书，用 `Map` 按编号加速查询。
- 可用对象流或文本文件保存数据。

## 2. Java 示例

### 示例一：Collection、List、Iterator

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIteratorDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("小明");
        names.add("小红");
        names.add("小明"); // List 允许重复元素

        System.out.println(names.get(0)); // List 支持按索引访问

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if ("小红".equals(name)) {
                iterator.remove(); // 遍历时删除元素应使用迭代器删除
            }
        }

        System.out.println(names);
    }
}
```

### 示例二：Queue、Deque、Set

```java
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class QueueSetDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("A");
        queue.offer("B");
        System.out.println(queue.poll()); // 队列先进先出，输出 A

        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("A");
        stack.push("B");
        System.out.println(stack.pop()); // 当栈使用，后进先出，输出 B

        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Java");
        System.out.println(set.size()); // Set 不允许重复元素，输出 1
    }
}
```

### 示例三：Map、Comparable、Comparator、Collections

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSortDemo {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("小明", 90);
        scores.put("小红", 95);
        System.out.println(scores.get("小明")); // 通过 key 查 value

        List<Student> students = new ArrayList<>();
        students.add(new Student("小明", 18));
        students.add(new Student("小红", 16));

        Collections.sort(students); // 使用 Student 自己的 Comparable 规则
        System.out.println(students);

        students.sort(Comparator.comparing(Student::name)); // 使用外部 Comparator 按姓名排序
        System.out.println(students);
    }
}

record Student(String name, int age) implements Comparable<Student> {
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.age, other.age); // 自然排序：按年龄升序
    }
}
```

### 示例四：Java 9 集合工厂方法和 Java 21 有序集合概念

```java
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionFactoryDemo {
    public static void main(String[] args) {
        List<String> immutable = List.of("A", "B", "C");
        // immutable.add("D"); // 错误：工厂方法创建的是不可变集合

        Map<String, Integer> map = Map.of("A", 1, "B", 2);
        System.out.println(map);

        LinkedHashMap<String, Integer> ordered = new LinkedHashMap<>();
        ordered.put("first", 1);
        ordered.put("second", 2);
        // LinkedHashMap 保持插入顺序，Java 21 的 SequencedMap 进一步统一了首尾访问规范
        System.out.println(ordered.keySet());
    }
}
```

### 示例五：Stream 基础操作

```java
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> names = List.of("Java", "Spring", "IO", "Stream");

        List<String> result = names.stream()
                .filter(name -> name.length() > 2) // 过滤
                .map(String::toUpperCase)          // 映射
                .sorted()                          // 排序
                .toList();                         // Java 16：直接转 List

        System.out.println(result);

        int totalLength = names.stream()
                .map(String::length)
                .reduce(0, Integer::sum); // reduce 聚合

        System.out.println(totalLength);
    }
}
```

### 示例六：Stream 增强方法

```java
import java.util.List;
import java.util.stream.Stream;

public class StreamEnhanceDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 0, 4, 5);

        numbers.stream()
                .takeWhile(n -> n > 0) // Java 9：从头获取，直到条件不满足
                .forEach(System.out::println);

        Stream.ofNullable(null) // Java 9：null 会变成空流，避免空指针
                .forEach(System.out::println);
    }
}
```

### 示例七：文件字节流和字符流

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStreamDemo {
    public static void main(String[] args) throws IOException {
        try (FileOutputStream out = new FileOutputStream("byte.txt")) {
            out.write("hello".getBytes()); // 字节流写出原始字节
        }

        try (FileInputStream in = new FileInputStream("byte.txt")) {
            byte[] data = in.readAllBytes(); // Java 9：一次读取所有字节
            System.out.println(new String(data));
        }

        try (FileWriter writer = new FileWriter("char.txt")) {
            writer.write("你好 Java"); // 字符流适合写文本
        }

        try (FileReader reader = new FileReader("char.txt")) {
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
        }
    }
}
```

### 示例八：Files 工具类

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesDemo {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("note.txt");

        Files.writeString(path, "Java 文件工具类"); // Java 11：直接写字符串
        String content = Files.readString(path);   // Java 11：直接读字符串
        System.out.println(content);

        Path copy = Path.of("note-copy.txt");
        Files.copy(path, copy, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        Files.deleteIfExists(copy);
    }
}
```

### 示例九：缓冲流、转换流、打印流

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class BufferedConvertPrintDemo {
    public static void main(String[] args) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("utf8.txt"), StandardCharsets.UTF_8))) {
            // OutputStreamWriter 是转换流，把字符按指定字符集转成字节
            writer.write("第一行");
            writer.newLine();
            writer.write("第二行");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("utf8.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // BufferedReader 适合按行读取
            }
        }

        try (PrintWriter writer = new PrintWriter("print.txt", StandardCharsets.UTF_8)) {
            writer.printf("姓名：%s，年龄：%d%n", "小明", 18); // 打印流适合格式化输出
        }
    }
}
```

### 示例十：数据流、对象流、字节数组流

```java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataObjectByteArrayDemo {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        try (DataOutputStream dataOut = new DataOutputStream(byteOut)) {
            dataOut.writeInt(18);        // 按 int 二进制格式写入
            dataOut.writeUTF("小明");    // 按 UTF 字符串格式写入
        }

        try (DataInputStream dataIn = new DataInputStream(
                new ByteArrayInputStream(byteOut.toByteArray()))) {
            System.out.println(dataIn.readInt());
            System.out.println(dataIn.readUTF());
        }

        ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(objectBytes)) {
            out.writeObject(new Book("JavaSE", 99.0)); // 对象序列化
        }

        try (ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(objectBytes.toByteArray()))) {
            Book book = (Book) in.readObject(); // 对象反序列化
            System.out.println(book);
        }
    }
}

record Book(String name, double price) implements Serializable {
}
```

### 示例十一：简化版图书管理系统

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookManagerDemo {
    public static void main(String[] args) throws IOException {
        BookService service = new BookService();
        service.add(new ManagedBook("B001", "JavaSE", 99));
        service.add(new ManagedBook("B002", "IO 入门", 59));

        service.findById("B001").ifPresent(System.out::println);
        service.sortByPrice();
        service.save(Path.of("books.txt"));
    }
}

record ManagedBook(String id, String name, double price) {
}

class BookService {
    private final List<ManagedBook> books = new ArrayList<>();

    void add(ManagedBook book) {
        books.add(book);
    }

    Optional<ManagedBook> findById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst();
    }

    void sortByPrice() {
        books.sort(Comparator.comparingDouble(ManagedBook::price));
    }

    void save(Path path) throws IOException {
        List<String> lines = books.stream()
                .map(book -> book.id() + "," + book.name() + "," + book.price())
                .toList();
        Files.write(path, lines); // 使用 Files 工具类持久化到文本文件
    }
}
```
