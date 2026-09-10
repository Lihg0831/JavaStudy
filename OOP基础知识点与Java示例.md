# JavaSE 面向对象基础知识点与 Java 示例

来源：
- 页面：https://www.itbaima.cn/zh-CN/document/eldst1fgrbdkmfs7
- 页面数据接口：https://api.itbaima.cn/resource/document/query/eldst1fgrbdkmfs7
- 原页面标题：JavaSE 笔记（三）面向对象基础
- 原页面最后更新时间：2025-07-09 22:20:22

## 1. 知识点总览

### 类与对象

- 类是对一类事物的抽象描述；对象是类创建出来的具体实例。
- Java 中用 `class` 定义类，类名通常首字母大写。
- 类中可以定义成员变量，表示对象拥有的属性。
- 用 `new 类名()` 创建对象。
- 引用类型变量保存的是对象引用，不是对象本体。
- `==` 用于判断两个引用是否指向同一个对象。
- 不同对象各自拥有独立的成员变量空间。
- `null` 表示没有引用任何对象，对 `null` 调用属性或方法会出现空指针异常。
- 成员变量有默认值：整数为 `0`，浮点数为 `0.0`，`boolean` 为 `false`，引用类型为 `null`。

### 方法

- 方法表示对象的行为，是一段可重复调用的代码。
- 方法可以有返回值，也可以用 `void` 表示无返回值。
- 方法参数分为形式参数和实际参数。
- `return` 可以返回结果，也会立即结束方法。
- 有返回值的方法必须保证所有执行路径都有返回值。
- Java 参数传递本质是值传递。
- 基本类型传递的是值的副本，方法内修改不会影响外部变量。
- 引用类型传递的是引用的副本，虽然引用变量是副本，但仍指向同一个对象，所以能修改对象内部状态。

### 方法进阶

- 当局部变量、参数和成员变量重名时，默认使用最近作用域的变量。
- `this` 表示当前对象，可用于区分成员变量和参数。
- 方法可以重载：同一个类中方法名相同，但参数列表不同。
- 方法可以递归调用，但必须有结束条件。

### 构造方法

- 构造方法用于对象创建时初始化对象。
- 构造方法没有返回值，方法名必须和类名相同。
- 如果没有手动定义构造方法，Java 会提供默认无参构造。
- 一旦手动定义了构造方法，默认无参构造就不会自动生成。
- 构造方法也可以重载。
- 成员变量可以在声明时初始化。
- 对象初始化顺序大致为：成员变量默认值 -> 成员变量显式赋值/实例代码块 -> 构造方法。

### static 静态成员

- `static` 修饰的变量属于类，不属于某个对象。
- 静态变量在所有对象之间共享。
- `static` 方法属于类，通常用 `类名.方法()` 调用。
- 静态方法中不能直接使用非静态成员，因为它不依赖具体对象。
- 静态代码块会在类加载时执行，常用于类级初始化。

### 包和访问控制

- `package` 用于声明类所在的包。
- `import` 用于导入其他包中的类。
- 同包类通常不需要导入。
- `java.lang` 包下的常用类默认可用。
- 访问权限从小到大：`private`、默认包访问权限、`protected`、`public`。
- 顶层类通常只能使用 `public` 或默认访问权限。

### 封装、继承、多态

- 封装是隐藏对象内部实现，通常把成员变量设为 `private`，通过 getter/setter 控制访问。
- 继承用 `extends`，子类可以复用、扩展父类能力。
- Java 类只能单继承。
- `super` 表示父类，可调用父类构造方法、字段或方法。
- 所有类最终都继承自 `Object`。
- `Object` 常见方法包括 `toString()`、`equals()`、`hashCode()`、`clone()` 等。
- 方法重写是子类重新实现父类方法，常配合 `@Override`。
- 多态是父类引用指向子类对象，实际调用时执行子类重写后的方法。
- `instanceof` 用于判断对象真实类型。
- Java 16 支持 `instanceof` 模式匹配，可在判断时直接声明转换后的变量。

### 抽象类和接口

- 抽象类用 `abstract class` 声明，不能直接创建对象。
- 抽象方法没有方法体，子类必须实现，除非子类也是抽象类。
- 抽象类可以有成员变量、普通方法、构造方法。
- 接口用 `interface` 声明，强调行为能力的抽象。
- 类用 `implements` 实现接口，一个类可以实现多个接口。
- 接口也可以继承接口，并且接口支持多继承。
- 接口类型变量只能调用接口中声明的方法和 `Object` 方法。
- `Cloneable` 是标记接口，配合重写 `clone()` 可实现对象克隆。
- `Object.clone()` 默认是浅拷贝：基本类型复制值，引用类型复制引用。

### Java 8/9 接口增强

- Java 8 开始，接口可以有 `default` 默认方法。
- Java 8 开始，接口可以有 `public static final` 常量和 `public static` 静态方法。
- Java 9 开始，接口可以有 `private` 方法，供默认方法或静态方法内部复用。

### 其他类型

- 枚举 `enum` 用于定义固定范围的常量。
- 枚举本质也是类，可以有字段、构造方法和普通方法；枚举构造方法默认私有。
- 枚举自带 `values()` 和 `valueOf()` 等方法。
- Java 16 正式引入 `record`，适合保存不可变数据。
- `record` 自动生成构造方法、访问器、`equals()`、`hashCode()`、`toString()`。
- `record` 默认是 `final`，不能继承其他类，但可以实现接口。
- Java 17 引入密封类型 `sealed`，用于限制哪些类可以继承当前类或实现当前接口。
- 密封类型使用 `permits` 指定允许的子类。
- 密封类型的直接子类必须声明为 `final`、`sealed` 或 `non-sealed`。

## 2. Java 示例

下面的示例按知识点拆分，可以分别放入同一个项目中测试。为了阅读方便，没有使用 `package` 声明。

### 示例一：类、对象、引用、null、默认值

```java
public class ObjectBasicsDemo {
    public static void main(String[] args) {
        Person p1 = new Person(); // 使用 new 创建 Person 对象
        Person p2 = p1;           // 复制的是对象引用，不是复制对象本身
        Person p3 = new Person(); // 创建另一个独立对象

        System.out.println(p1 == p2); // true：p1 和 p2 指向同一个对象
        System.out.println(p1 == p3); // false：p1 和 p3 指向不同对象

        // 成员变量有默认值：String 默认 null，int 默认 0，boolean 默认 false
        System.out.println(p1.name);
        System.out.println(p1.age);
        System.out.println(p1.active);

        p1.name = "小明"; // 修改 p1 指向对象的 name
        p2.age = 18;      // p2 和 p1 指向同一个对象，所以这里也影响 p1
        p3.name = "小红"; // p3 是另一个对象，不会影响 p1

        System.out.println(p1.name + ", " + p1.age);
        System.out.println(p3.name + ", " + p3.age);

        Person nobody = null; // null 表示没有引用任何对象
        // nobody.name = "错误"; // 运行时会抛出 NullPointerException
    }
}

class Person {
    String name;      // 引用类型默认值是 null
    int age;          // int 默认值是 0
    boolean active;   // boolean 默认值是 false
}
```

### 示例二：方法、return、值传递、引用传递效果

```java
public class MethodDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int result = calculator.sum(10, 20); // 10 和 20 是实际参数
        System.out.println(result);

        int a = 5;
        int b = 9;
        calculator.swap(a, b); // Java 是值传递，这里不会改变 main 中的 a 和 b
        System.out.println("a = " + a + ", b = " + b);

        Person person = new Person();
        person.name = "小明";
        calculator.rename(person); // 复制的是引用的值，仍然指向同一个对象
        System.out.println(person.name); // 输出“小王”
    }
}

class Calculator {
    int sum(int a, int b) { // a 和 b 是形式参数
        return a + b;      // return 返回结果，并结束方法
    }

    void printIfPositive(int n) {
        if (n <= 0) {
            return; // void 方法也可以用 return 提前结束
        }
        System.out.println(n);
    }

    void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp; // 这里只交换了参数副本，不影响外部变量
    }

    void rename(Person person) {
        person.name = "小王"; // 修改的是同一个对象的内部状态
    }
}
```

### 示例三：this、方法重载、递归

```java
public class AdvancedMethodDemo {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("小明"); // this.name 才能修改成员变量

        System.out.println(student.sayHello());
        System.out.println(student.sayHello("老师"));
        System.out.println(student.sumTo(5)); // 1 + 2 + 3 + 4 + 5
    }
}

class Student {
    String name;

    void setName(String name) {
        // 左边 this.name 是成员变量；右边 name 是方法参数
        this.name = name;
    }

    String sayHello() {
        return "你好，我是" + name;
    }

    String sayHello(String target) {
        // 方法重载：方法名相同，但参数列表不同
        return target + "你好，我是" + name;
    }

    int sumTo(int n) {
        if (n == 0) {
            return 0; // 递归必须有结束条件
        }
        return sumTo(n - 1) + n; // 方法自己调用自己
    }
}
```

### 示例四：构造方法、初始化顺序、实例代码块

```java
public class ConstructorDemo {
    public static void main(String[] args) {
        User user1 = new User(); // 调用无参构造
        User user2 = new User("小明", 18); // 调用有参构造

        System.out.println(user1.name + ", " + user1.age);
        System.out.println(user2.name + ", " + user2.age);
    }
}

class User {
    String name = "未知"; // 成员变量显式初始化早于构造方法执行
    int age = 1;

    {
        // 实例代码块在每次创建对象时执行，早于构造方法
        System.out.println("实例代码块执行，此时 name = " + name);
    }

    User() {
        // 手动写出无参构造，避免被有参构造覆盖后无法无参创建对象
        this.name = "默认用户";
    }

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

### 示例五：static 静态变量、静态方法、静态代码块

```java
public class StaticDemo {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        c1.add();
        c2.add();

        System.out.println(Counter.count); // static 变量属于类，两个对象共享
        Counter.reset();                   // static 方法推荐用类名调用
    }
}

class Counter {
    static int count;

    static {
        // 静态代码块在类加载时执行一次
        count = 0;
    }

    void add() {
        count++;
    }

    static void reset() {
        count = 0;
        // add(); // 错误：静态方法中不能直接调用非静态方法
    }
}
```

### 示例六：包、导入和访问权限

```java
// 文件路径示例：src/com/example/model/Account.java
package com.example.model; // package 声明必须放在文件开头

public class Account {
    private String password;   // 只能在当前类中访问
    String owner;              // 默认权限：同一个包中可以访问
    protected double balance;  // 同包或子类可以访问
    public String id;          // 任意位置都可以访问
}
```

```java
// 文件路径示例：src/com/example/app/Main.java
package com.example.app;

import com.example.model.Account; // 导入其他包中的类

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.id = "A001"; // public 成员可访问
        // account.password = "123"; // 错误：private 成员不可直接访问
    }
}
```

### 示例七：封装、继承、super、重写、多态、instanceof

```java
public class OopCoreDemo {
    public static void main(String[] args) {
        Animal animal = new Dog("旺财", 3);
        animal.speak(); // 多态：编译看 Animal，运行执行 Dog 的 speak()

        System.out.println(animal); // 调用 Dog 重写后的 toString()

        if (animal instanceof Dog dog) {
            // Java 16 模式匹配：判断成功后直接得到 dog 变量
            dog.fetch();
        }
    }
}

class Animal {
    private String name; // 封装：字段私有化
    private int age;

    Animal(String name, int age) {
        this.name = name;
        setAge(age); // 构造方法中也可以使用 setter 做校验
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        this.age = age;
    }

    public void speak() {
        System.out.println("动物发出声音");
    }

    @Override
    public String toString() {
        // Object 中的 toString 通常会被重写，让对象打印更有意义
        return "Animal{name='" + name + "', age=" + age + "}";
    }
}

class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age); // 调用父类构造方法
    }

    @Override
    public void speak() {
        System.out.println(getName() + "：汪汪");
    }

    public void fetch() {
        System.out.println(getName() + " 去捡球");
    }
}
```

### 示例八：抽象类和接口

```java
public class AbstractInterfaceDemo {
    public static void main(String[] args) {
        Worker worker = new Programmer("小李");
        worker.work(); // 抽象父类引用指向子类对象

        Learner learner = new Programmer("小张");
        learner.study();       // 调用接口抽象方法的实现
        learner.preview();     // 调用接口默认方法
        Learner.printVersion(); // 调用接口静态方法
    }
}

abstract class Worker {
    protected String name;

    Worker(String name) {
        this.name = name;
    }

    abstract void work(); // 抽象方法没有方法体，子类必须实现

    void rest() {
        System.out.println(name + " 正在休息"); // 抽象类可以有普通方法
    }
}

interface Learner {
    int VERSION = 1; // 接口字段默认是 public static final

    void study(); // 接口方法默认是 public abstract

    default void preview() {
        log("预习中"); // 默认方法可以调用接口私有方法
    }

    static void printVersion() {
        System.out.println("接口版本：" + VERSION);
    }

    private void log(String message) {
        // Java 9 开始支持接口 private 方法，仅供接口内部复用
        System.out.println(message);
    }
}

class Programmer extends Worker implements Learner {
    Programmer(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " 正在写代码");
    }

    @Override
    public void study() {
        System.out.println(name + " 正在学习 Java");
    }
}
```

### 示例九：Cloneable、浅拷贝和深拷贝思路

```java
public class CloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("成都");
        CloneStudent s1 = new CloneStudent("小明", address);
        CloneStudent s2 = s1.clone();

        System.out.println(s1 == s2);               // false：克隆出了新对象
        System.out.println(s1.address == s2.address); // true：默认 clone 是浅拷贝
    }
}

class Address {
    String city;

    Address(String city) {
        this.city = city;
    }
}

class CloneStudent implements Cloneable {
    String name;
    Address address;

    CloneStudent(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public CloneStudent clone() throws CloneNotSupportedException {
        // super.clone() 会复制当前对象的字段，但引用类型字段只复制引用地址
        return (CloneStudent) super.clone();
    }
}
```

### 示例十：枚举 enum

```java
public class EnumDemo {
    public static void main(String[] args) {
        StudentWithStatus student = new StudentWithStatus();
        student.setStatus(Status.STUDY);

        System.out.println(student.getStatus().getText());

        for (Status status : Status.values()) {
            // values() 可以获取所有枚举常量
            System.out.println(status.name() + " = " + status.getText());
        }
    }
}

enum Status {
    RUNNING("跑步"),
    STUDY("学习"),
    SLEEP("睡觉");

    private final String text;

    Status(String text) {
        // 枚举构造方法默认是 private，只能在枚举内部使用
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

class StudentWithStatus {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        // 使用枚举避免外部传入任意字符串
        this.status = status;
    }
}
```

### 示例十一：record 记录类型

```java
public class RecordDemo {
    public static void main(String[] args) {
        Point p1 = new Point(10, 20);
        Point p2 = new Point(10, 20);

        System.out.println(p1.x());       // record 自动生成同名访问器
        System.out.println(p1);           // 自动生成 toString()
        System.out.println(p1.equals(p2)); // 自动按字段比较
    }
}

record Point(int x, int y) {
    // record 可以写普通方法
    public int distanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }
}
```

### 示例十二：sealed 密封类型

```java
public class SealedDemo {
    public static void main(String[] args) {
        Shape shape = new Circle(3);
        System.out.println(shape.area());
    }
}

sealed abstract class Shape permits Circle, Rectangle {
    // sealed 限制只有 permits 列出的类可以继承 Shape
    abstract double area();
}

final class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

non-sealed class Rectangle extends Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

// 因为 Rectangle 是 non-sealed，所以它重新开放继承
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}
```

## 3. 复习主线

学习这篇内容可以按这条线理解：

1. 先理解“类是模板，对象是实例”。
2. 再掌握对象的属性、方法、构造方法和 `this`。
3. 然后理解引用、`null`、参数值传递，这是后续很多问题的根。
4. 接着学习 `static`、包、访问控制，为组织代码做准备。
5. 再进入封装、继承、多态，这是面向对象的核心。
6. 最后学习抽象类、接口，以及 enum、record、sealed 这些特殊类型。
