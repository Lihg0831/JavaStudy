# JavaSE 泛型程序设计知识点与 Java 示例

来源：
- 页面：https://www.itbaima.cn/zh-CN/document/6r4llai92yc15j98
- 页面数据接口：https://api.itbaima.cn/resource/document/query/6r4llai92yc15j98
- 原页面标题：JavaSE 笔记（五）泛型程序设计
- 原页面最后更新时间：2025-07-18 22:42:02

## 1. 知识点总览

### 泛型

- 泛型把“类型”参数化，让类、接口、方法在定义时不写死具体类型。
- 泛型类在类名后声明类型参数，例如 `class Box<T>`。
- 泛型接口与泛型类类似，例如 `interface Repository<T>`。
- 泛型方法在返回值前声明类型参数，例如 `<T> T first(T[] arr)`。
- 泛型可以配合多态使用，例如 `List<String> list = new ArrayList<>();`。
- 泛型的上界用 `extends`，例如 `<T extends Number>` 表示 T 必须是 Number 或其子类。
- 通配符 `?` 表示未知类型。
- `? extends T` 表示上界通配符，适合读取，不能安全写入具体元素。
- `? super T` 表示下界通配符，适合写入 T 或 T 的子类。
- Java 泛型通过类型擦除实现，运行时通常拿不到泛型实参类型。
- 类型擦除后，类型参数会被擦除为上界类型；没有上界时擦除为 `Object`。
- 泛型不能直接创建泛型数组，例如 `new T[10]` 不允许。
- 泛型不能直接使用基本类型，必须使用包装类，例如 `List<Integer>`。

### 协变和逆变

- 数组是协变的：`String[]` 可以赋值给 `Object[]`，但可能引发运行时类型问题。
- 泛型默认不协变：`List<String>` 不能赋值给 `List<Object>`。
- `? extends Parent` 提供受限协变，主要用于安全读取。
- `? super Child` 提供受限逆变，主要用于安全写入。
- 记忆规则：PECS，Producer Extends，Consumer Super。

### Java 8 函数式接口

- 函数式接口只有一个抽象方法，可用 Lambda 表达式实现。
- `@FunctionalInterface` 用于让编译器检查接口是否满足函数式接口要求。
- 常用函数式接口包括：`Function<T,R>`、`Consumer<T>`、`Supplier<T>`、`Predicate<T>`、`UnaryOperator<T>`、`BinaryOperator<T>`。

### Optional 判空包装

- `Optional<T>` 用于表示一个值可能存在，也可能不存在。
- 常用方法：`of()`、`ofNullable()`、`empty()`、`isPresent()`、`ifPresent()`、`orElse()`、`orElseGet()`、`orElseThrow()`、`map()`、`flatMap()`、`filter()`。
- Java 9 增强：`ifPresentOrElse()`、`or()`、`stream()`。
- Java 10 增强：无参 `orElseThrow()`。

### 数据结构基础

- 顺序表：底层数组实现，支持随机访问，插入删除可能需要移动元素。
- 链表：节点通过引用连接，插入删除灵活，但随机访问效率低。
- 栈：后进先出，典型操作是 `push`、`pop`、`peek`。
- 队列：先进先出，典型操作是 `offer`、`poll`、`peek`。
- 二叉树：每个节点最多有两个孩子。
- 二叉查找树：左子树值小于根，右子树值大于根，便于查找。
- 平衡二叉树：通过旋转保持树高度平衡，避免退化成链表。
- 红黑树：一种自平衡二叉查找树，常用于有序集合和映射。
- 哈希表：通过哈希值定位桶，平均查找效率高，但要处理哈希冲突。

### 实战练习

- 反转链表：改变节点指向，让链表从尾到头。
- 括号匹配：用栈检查括号是否成对、顺序是否正确。
- 实现计算器：解析表达式，处理数字、运算符和优先级。

## 2. Java 示例

### 示例一：泛型类和泛型接口

```java
public class GenericClassDemo {
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Java");
        Box<Integer> intBox = new Box<>(100);

        System.out.println(stringBox.get());
        System.out.println(intBox.get());

        Repository<String> repo = new MemoryRepository<>();
        repo.save("泛型接口示例");
        System.out.println(repo.find());
    }
}

class Box<T> {
    private final T value; // T 是类型参数，创建对象时才确定具体类型

    Box(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}

interface Repository<T> {
    void save(T value);
    T find();
}

class MemoryRepository<T> implements Repository<T> {
    private T value;

    @Override
    public void save(T value) {
        this.value = value;
    }

    @Override
    public T find() {
        return value;
    }
}
```

### 示例二：泛型方法、上界、通配符

```java
import java.util.ArrayList;
import java.util.List;

public class GenericMethodDemo {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3};
        System.out.println(first(numbers));

        List<Integer> ints = List.of(1, 2, 3);
        System.out.println(sum(ints)); // ? extends Number 可以读取 Number

        List<Number> targets = new ArrayList<>();
        addIntegers(targets); // ? super Integer 可以写入 Integer
        System.out.println(targets);
    }

    static <T> T first(T[] arr) {
        return arr[0]; // 泛型方法在调用时推断 T 的具体类型
    }

    static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue(); // extends 适合读取
        }
        return total;
    }

    static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2); // super 适合写入 Integer 或其子类对象
    }
}
```

### 示例三：类型擦除和泛型限制

```java
import java.util.ArrayList;
import java.util.List;

public class TypeErasureDemo {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        // 运行时看到的都是 ArrayList，泛型实参 String/Integer 已经被擦除
        System.out.println(strings.getClass() == integers.getClass());

        // List<int> wrong; // 错误：泛型不能使用基本类型
        List<Integer> right = new ArrayList<>(); // 正确：使用包装类
        right.add(10);

        // String[] arr = new T[10]; // 错误：不能直接创建泛型数组
    }
}
```

### 示例四：函数式接口和常用 Java 8 函数接口

```java
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        Converter<String, Integer> converter = Integer::parseInt;
        System.out.println(converter.convert("123"));

        Function<String, Integer> length = String::length; // 输入 String，输出 Integer
        Predicate<Integer> positive = n -> n > 0;          // 判断条件
        Consumer<String> printer = System.out::println;    // 消费数据，不返回
        Supplier<Double> random = Math::random;            // 不接收参数，提供结果

        printer.accept("长度：" + length.apply("Java"));
        printer.accept("是否为正数：" + positive.test(10));
        printer.accept("随机数：" + random.get());
    }
}

@FunctionalInterface
interface Converter<T, R> {
    R convert(T value); // 只有一个抽象方法，所以可以用 Lambda 或方法引用实现
}
```

### 示例五：Optional 判空包装

```java
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable(findName(1));

        name.ifPresentOrElse(
                value -> System.out.println("找到：" + value),
                () -> System.out.println("没找到")
        ); // Java 9：存在时执行第一个动作，不存在时执行第二个动作

        String display = name
                .filter(s -> s.length() > 1) // 满足条件才继续保留
                .map(String::toUpperCase)     // 对内部值做转换
                .orElse("DEFAULT");           // 不存在时给默认值

        System.out.println(display);
    }

    static String findName(int id) {
        return id == 1 ? "java" : null;
    }
}
```

### 示例六：顺序表的简化实现

```java
import java.util.Arrays;

public class SimpleArrayListDemo {
    public static void main(String[] args) {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println(list.get(1));
    }
}

class SimpleArrayList<E> {
    private Object[] data = new Object[2];
    private int size;

    void add(E value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2); // 容量不够时扩容
        }
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        return (E) data[index]; // 由于类型擦除，取出时需要强制转换
    }
}
```

### 示例七：链表、栈、队列

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class LinearStructureDemo {
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.next = b;
        b.next = c; // a -> b -> c 形成单向链表

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop()); // 栈：后进先出，输出 2

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        System.out.println(queue.poll()); // 队列：先进先出，输出 1
    }
}

class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
    }
}
```

### 示例八：二叉查找树和哈希表

```java
import java.util.HashMap;
import java.util.Map;

public class TreeHashDemo {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        insert(root, 3);
        insert(root, 7);
        System.out.println(search(root, 7));

        Map<String, Integer> ages = new HashMap<>();
        ages.put("小明", 18); // HashMap 底层基于哈希表思想
        System.out.println(ages.get("小明"));
    }

    static void insert(TreeNode node, int value) {
        if (value < node.value) {
            if (node.left == null) node.left = new TreeNode(value);
            else insert(node.left, value);
        } else {
            if (node.right == null) node.right = new TreeNode(value);
            else insert(node.right, value);
        }
    }

    static boolean search(TreeNode node, int target) {
        if (node == null) return false;
        if (node.value == target) return true;
        return target < node.value
                ? search(node.left, target)
                : search(node.right, target);
    }
}

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}
```

### 示例九：反转链表

```java
public class ReverseListDemo {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);

        Node reversed = reverse(head);
        while (reversed != null) {
            System.out.println(reversed.value);
            reversed = reversed.next;
        }
    }

    static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next; // 先保存下一个节点
            current.next = prev;      // 反转当前节点指向
            prev = current;           // prev 向前移动
            current = next;           // current 向前移动
        }

        return prev; // prev 最终就是新头节点
    }
}
```

### 示例十：括号匹配

```java
import java.util.ArrayDeque;

public class BracketMatchDemo {
    public static void main(String[] args) {
        System.out.println(match("([]{})"));
        System.out.println(match("([)]"));
    }

    static boolean match(String text) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char left = stack.pop();
                if (!pair(left, c)) return false;
            }
        }

        return stack.isEmpty(); // 最后栈为空，说明全部匹配
    }

    static boolean pair(char left, char right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }
}
```

### 示例十一：简易计算器

```java
import java.util.ArrayDeque;

public class CalculatorPracticeDemo {
    public static void main(String[] args) {
        System.out.println(calculate("3+2*5-4"));
    }

    static int calculate(String expr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int number = 0;
        char op = '+';

        for (int i = 0; i <= expr.length(); i++) {
            char c = i < expr.length() ? expr.charAt(i) : '+'; // 末尾补一个运算符，触发最后一次入栈

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (op == '+') stack.push(number);
                if (op == '-') stack.push(-number);
                if (op == '*') stack.push(stack.pop() * number);
                if (op == '/') stack.push(stack.pop() / number);

                op = c;
                number = 0;
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop(); // 栈中已经把乘除处理完，最后只求和
        }
        return result;
    }
}
```
