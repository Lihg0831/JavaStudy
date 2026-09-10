# JavaSE 面向对象高级篇知识点与 Java 示例

来源：
- 页面：https://www.itbaima.cn/zh-CN/document/48zphgkpjto8cath
- 页面数据接口：https://api.itbaima.cn/resource/document/query/48zphgkpjto8cath
- 原页面标题：JavaSE 笔记（四）面向对象高级篇
- 原页面最后更新时间：2025-07-18 00:54:42

## 1. 知识点总览

### 基本类型包装类

- Java 有 8 种基本类型，对应 8 个包装类：`byte/Byte`、`short/Short`、`int/Integer`、`long/Long`、`float/Float`、`double/Double`、`boolean/Boolean`、`char/Character`。
- 包装类让基本类型可以像对象一样使用，能调用方法，也能作为泛型、集合等只能接收对象的场景中的元素。
- 自动装箱：基本类型自动转成包装类，例如 `Integer i = 10;`。
- 自动拆箱：包装类自动转成基本类型，例如 `int n = i;`。
- 包装类提供常用转换方法，例如 `Integer.parseInt("123")`、`Integer.valueOf("123")`。
- `BigInteger` 用于表示超出 `long` 范围的大整数。
- `BigDecimal` 用于高精度小数计算，适合金额等不能接受浮点误差的场景。

### 数组

- 数组是一组相同类型数据的有序容器，长度一旦确定不能改变。
- 数组是引用类型，变量保存的是数组对象的引用。
- 一维数组用 `类型[] 数组名` 声明，用 `new 类型[长度]` 创建。
- 数组元素有默认值：数值为 `0`，`boolean` 为 `false`，引用类型为 `null`。
- `数组.length` 获取数组长度。
- 数组下标从 `0` 开始，访问越界会抛出 `ArrayIndexOutOfBoundsException`。
- 多维数组可以理解为“数组中存数组”，常见的是二维数组。
- 可变长参数使用 `类型... 参数名`，本质上是数组，调用时可以传 0 个或多个参数。

### 字符串

- `String` 是引用类型，但使用频率很高，语法上有特殊支持。
- `String` 对象不可变，每次修改都会产生新字符串或复用已有字符串。
- 字符串内容比较应使用 `equals()`，不要用 `==` 比内容。
- `String` 常用方法：`length()`、`charAt()`、`substring()`、`contains()`、`startsWith()`、`endsWith()`、`replace()`、`split()`、`trim()` 等。
- 大量字符串拼接建议使用 `StringBuilder`，它是可变字符序列。
- Java 11 增强：`isBlank()`、`strip()`、`repeat()`、`lines()` 等。
- Java 15 文本块使用三引号 `"""` 编写多行字符串。
- 正则表达式用于模式匹配、校验、查找、替换和切割字符串。
- switch 表达式和模式匹配可以更灵活地按值或类型进行分支判断。

### 内部类

- 成员内部类定义在类的成员位置，可以访问外部类成员。
- 静态内部类使用 `static` 修饰，不依赖外部类对象。
- 局部内部类定义在方法内部，作用范围只在方法中。
- 匿名内部类用于临时创建某个类的子类或接口实现类。
- Lambda 表达式是函数式接口匿名实现的简写。
- 函数式接口是有且只有一个抽象方法的接口，可用 `@FunctionalInterface` 标记。
- 方法引用把已经存在的方法作为函数式接口实现，常见形式有 `类名::静态方法`、`对象::实例方法`、`类名::实例方法`、`类名::new`。

### 异常机制

- 异常本质也是对象，用于表示程序运行中的错误或异常情况。
- `Throwable` 是异常体系顶层，常见分支是 `Error` 和 `Exception`。
- `Error` 通常表示 JVM 或系统级严重问题，一般不主动处理。
- `RuntimeException` 及其子类是运行时异常，编译器不强制处理。
- 除运行时异常外的 `Exception` 是受检异常，编译器强制处理或声明抛出。
- 自定义异常通常继承 `Exception` 或 `RuntimeException`。
- `throw` 用于主动抛出异常对象。
- `throws` 用于在方法签名上声明该方法可能抛出的异常。
- `try-catch-finally` 用于捕获和处理异常，`finally` 通常用于收尾操作。
- `assert` 断言用于调试阶段检查假设，默认关闭，需要 JVM 参数 `-ea` 开启。

### 常用工具类

- `Math` 提供数学计算方法，例如乘方、开方、绝对值、四舍五入、三角函数等。
- `Arrays` 提供数组工具方法，例如排序、查找、填充、复制、比较、转字符串等。
- 旧日期类包括 `Date`、`Calendar`、`SimpleDateFormat`。
- Java 8 新日期时间 API 位于 `java.time`，常用类包括 `LocalDate`、`LocalTime`、`LocalDateTime`、`DateTimeFormatter`、`Duration`、`Period`。

### 实战练习

- 冒泡排序：相邻元素两两比较，把较大值逐步交换到后面。
- 二分搜索：在有序数组中每次排除一半范围。
- 青蛙跳台阶：递归关系类似斐波那契，`f(n) = f(n - 1) + f(n - 2)`。
- 回文串判断：首尾字符向中间比较。
- 汉诺塔：递归把 n 个盘子从起点柱移动到目标柱。

## 2. Java 示例

### 示例一：包装类、自动装箱/拆箱、BigInteger、BigDecimal

```java
import java.math.BigDecimal;
import java.math.BigInteger;

public class WrapperDemo {
    public static void main(String[] args) {
        Integer boxed = 10;      // 自动装箱：int -> Integer
        int unboxed = boxed;     // 自动拆箱：Integer -> int

        String text = "123";
        int n1 = Integer.parseInt(text);     // 字符串转 int 基本类型
        Integer n2 = Integer.valueOf(text);  // 字符串转 Integer 对象

        System.out.println(boxed + unboxed + n1 + n2);

        BigInteger huge = new BigInteger("999999999999999999999999999999");
        BigInteger bigger = huge.add(BigInteger.ONE); // BigInteger 不用 +，而是调用方法计算
        System.out.println(bigger);

        BigDecimal price = new BigDecimal("0.1");
        BigDecimal count = new BigDecimal("3");
        System.out.println(price.multiply(count)); // 精确输出 0.3，避免 double 误差
    }
}
```

### 示例二：一维数组、二维数组、可变长参数

```java
import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] scores = new int[3]; // 创建长度为 3 的 int 数组，默认值都是 0
        scores[0] = 90;
        scores[1] = 80;
        scores[2] = 70;

        for (int i = 0; i < scores.length; i++) {
            System.out.println("第 " + i + " 个元素是：" + scores[i]);
        }

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        }; // 二维数组：可以理解为数组里面存数组

        System.out.println(matrix[1][2]); // 第 2 行第 3 列，输出 6
        System.out.println(sum(1, 2, 3, 4)); // 可变长参数可以传多个值
        System.out.println(Arrays.toString(scores));
    }

    static int sum(int... numbers) {
        // 可变长参数在方法内部就是数组
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }
}
```

### 示例三：String、StringBuilder、Java 11 字符串增强、文本块、正则

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDemo {
    public static void main(String[] args) {
        String a = "hello";
        String b = new String("hello");

        System.out.println(a == b);      // false：判断是否同一个对象
        System.out.println(a.equals(b)); // true：判断字符串内容是否相同

        String info = " Java is good ";
        System.out.println(info.length());        // 字符串长度
        System.out.println(info.trim());          // 去掉首尾空白
        System.out.println(info.contains("Java")); // 是否包含子串

        StringBuilder builder = new StringBuilder();
        builder.append("Java").append(" ").append("SE");
        System.out.println(builder.toString()); // 大量拼接时建议使用 StringBuilder

        System.out.println("   ".isBlank()); // Java 11：判断是否为空白字符串
        System.out.println("abc".repeat(3)); // Java 11：重复字符串

        String json = """
                {
                  "name": "小明",
                  "age": 18
                }
                """; // Java 15 文本块，适合多行字符串
        System.out.println(json);

        Pattern pattern = Pattern.compile("\\d+"); // 正则：匹配连续数字
        Matcher matcher = pattern.matcher("订单号：A10086");
        if (matcher.find()) {
            System.out.println(matcher.group()); // 输出匹配到的数字 10086
        }
    }
}
```

### 示例四：switch 表达式与模式匹配

```java
public class SwitchPatternDemo {
    public static void main(String[] args) {
        System.out.println(level("A"));
        System.out.println(describe(12));
        System.out.println(describe("hello"));
    }

    static int level(String grade) {
        return switch (grade) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            default -> 0;
        }; // switch 表达式可以直接返回结果
    }

    static String describe(Object value) {
        return switch (value) {
            case Integer i -> "整数：" + i;  // 按类型匹配，并得到变量 i
            case String s -> "字符串长度：" + s.length();
            case null -> "空值";
            default -> "其他类型";
        };
    }
}
```

### 示例五：成员内部类、静态内部类、局部内部类、匿名内部类

```java
public class InnerClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer("外部对象");

        Outer.MemberInner memberInner = outer.new MemberInner();
        memberInner.print(); // 成员内部类依赖外部类对象

        Outer.StaticInner staticInner = new Outer.StaticInner();
        staticInner.print(); // 静态内部类不依赖外部类对象

        outer.localInnerDemo();

        Task task = new Task() {
            @Override
            public void run() {
                // 匿名内部类：临时实现接口
                System.out.println("匿名内部类正在执行任务");
            }
        };
        task.run();
    }
}

class Outer {
    private final String name;

    Outer(String name) {
        this.name = name;
    }

    class MemberInner {
        void print() {
            System.out.println("成员内部类访问外部类字段：" + name);
        }
    }

    static class StaticInner {
        void print() {
            System.out.println("静态内部类不需要外部类对象");
        }
    }

    void localInnerDemo() {
        class LocalInner {
            void print() {
                System.out.println("局部内部类只能在当前方法中使用");
            }
        }
        new LocalInner().print();
    }
}

interface Task {
    void run();
}
```

### 示例六：Lambda 表达式和方法引用

```java
public class LambdaDemo {
    public static void main(String[] args) {
        Action action = () -> System.out.println("Lambda 执行动作");
        action.run();

        Calculator add = (a, b) -> a + b; // Lambda 实现有参数、有返回值的抽象方法
        System.out.println(add.calculate(10, 20));

        Calculator ref = Integer::sum; // 方法引用：直接引用已有的静态方法
        System.out.println(ref.calculate(30, 40));

        Printer printer = System.out::println; // 对象::实例方法
        printer.print("方法引用输出文本");
    }
}

@FunctionalInterface
interface Action {
    void run(); // 函数式接口只能有一个抽象方法
}

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

@FunctionalInterface
interface Printer {
    void print(String message);
}
```

### 示例七：异常类型、自定义异常、throw、throws、try-catch-finally

```java
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            register("admin");
        } catch (InvalidUsernameException e) {
            // catch 捕获异常后，可以记录日志或给用户提示
            System.out.println("注册失败：" + e.getMessage());
        } finally {
            // finally 无论是否出现异常通常都会执行，常用于释放资源
            System.out.println("注册流程结束");
        }
    }

    static void register(String username) throws InvalidUsernameException {
        if ("admin".equals(username)) {
            // throw 主动抛出异常对象
            throw new InvalidUsernameException("用户名已被占用");
        }
        System.out.println("注册成功");
    }
}

class InvalidUsernameException extends Exception {
    InvalidUsernameException(String message) {
        super(message); // 把错误信息交给父类 Exception 保存
    }
}
```

### 示例八：运行时异常和断言

```java
public class RuntimeAndAssertDemo {
    public static void main(String[] args) {
        int age = -1;

        // 断言默认关闭，需要使用 JVM 参数 -ea 开启
        assert age >= 0 : "年龄不应该为负数";

        try {
            int result = 10 / 0; // ArithmeticException 是运行时异常
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("除数不能为 0");
        }
    }
}
```

### 示例九：Math、Arrays、旧日期类、新日期类

```java
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class ToolClassDemo {
    public static void main(String[] args) {
        System.out.println(Math.pow(2, 10));  // 2 的 10 次方
        System.out.println(Math.sqrt(16));    // 平方根
        System.out.println(Math.max(3, 9));   // 最大值

        int[] arr = {5, 1, 4, 2, 3};
        Arrays.sort(arr); // 数组排序
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr, 4)); // 在有序数组中二分查找

        Date now = new Date(); // 旧日期类
        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(oldFormatter.format(now));

        LocalDate today = LocalDate.now(); // Java 8 新日期 API
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(today);
        System.out.println(current.format(formatter));
    }
}
```

### 示例十：冒泡排序

```java
import java.util.Arrays;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 2, 9, 0, 6, 1, 8, 4};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 相邻元素逆序时交换，把较大的数逐步冒到后面
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
```

### 示例十一：二分搜索

```java
public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        System.out.println(binarySearch(arr, 7));
        System.out.println(binarySearch(arr, 8));
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免 left + right 过大溢出

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1; // 目标在右半部分
            } else {
                right = mid - 1; // 目标在左半部分
            }
        }

        return -1; // 没找到
    }
}
```

### 示例十二：青蛙跳台阶

```java
public class FrogJumpDemo {
    public static void main(String[] args) {
        System.out.println(ways(5));
    }

    static int ways(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1; // 只有一种：跳 1 阶
        }
        if (n == 2) {
            return 2; // 两种：1+1 或 2
        }
        // 最后一步可能跳 1 阶，也可能跳 2 阶
        return ways(n - 1) + ways(n - 2);
    }
}
```

### 示例十三：回文串判断

```java
public class PalindromeDemo {
    public static void main(String[] args) {
        System.out.println(isPalindrome("上海自来水来自海上"));
        System.out.println(isPalindrome("Java"));
    }

    static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false; // 首尾字符不同，不是回文
            }
            left++;
            right--;
        }

        return true;
    }
}
```

### 示例十四：汉诺塔

```java
public class HanoiDemo {
    public static void main(String[] args) {
        hanoi(3, "A", "B", "C");
    }

    static void hanoi(int n, String from, String helper, String to) {
        if (n == 1) {
            System.out.println("移动盘子 1：" + from + " -> " + to);
            return;
        }

        // 先把上面 n-1 个盘子从 from 借助 to 移到 helper
        hanoi(n - 1, from, to, helper);

        // 再把最大的第 n 个盘子移动到目标柱
        System.out.println("移动盘子 " + n + "：" + from + " -> " + to);

        // 最后把 n-1 个盘子从 helper 借助 from 移到 to
        hanoi(n - 1, helper, from, to);
    }
}
```

## 3. 复习主线

1. 先把包装类、数组、字符串掌握好，它们是后续 Java 编程中最常用的数据结构和 API。
2. 再理解内部类、Lambda 和方法引用，它们是 Java 面向对象语法向函数式写法扩展的关键。
3. 然后学习异常机制，重点区分运行时异常、受检异常、`throw` 和 `throws`。
4. 接着熟悉 `Math`、`Arrays` 和日期时间 API，减少重复造轮子。
5. 最后用排序、搜索、递归类练习把语法转化为解决问题的能力。
