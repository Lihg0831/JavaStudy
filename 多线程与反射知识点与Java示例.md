# JavaSE 多线程与反射知识点与 Java 示例

来源：
- 页面：https://www.itbaima.cn/zh-CN/document/qrd0xfttsz32gpqg
- 页面数据接口：https://api.itbaima.cn/resource/document/query/qrd0xfttsz32gpqg
- 原页面标题：JavaSE 笔记（七）多线程与反射
- 原页面最后更新时间：2025-07-26 22:11:29

## 1. 知识点总览

### 多线程

- 线程是程序执行的基本单位之一，一个进程中可以有多个线程并发执行。
- 创建线程常见方式：继承 `Thread`、实现 `Runnable`、实现 `Callable` 配合 `FutureTask`。
- `start()` 用于启动新线程，直接调用 `run()` 不会创建新线程。
- `Thread.sleep()` 让当前线程休眠指定时间。
- `interrupt()` 用于中断线程，通常通过中断标记配合业务逻辑停止线程。
- 线程优先级可通过 `setPriority()` 设置，但只是调度建议，不保证执行顺序。
- `Thread.yield()` 表示当前线程主动礼让 CPU。
- `join()` 表示等待另一个线程执行结束。
- 多线程共享数据会出现线程安全问题。
- `synchronized` 可用于同步代码块或同步方法，保证同一时刻只有一个线程进入临界区。
- 死锁是多个线程互相持有对方需要的锁，导致都无法继续执行。
- `wait()` 会释放锁并等待通知；`notify()`/`notifyAll()` 用于唤醒等待线程，必须在同步代码块中使用。
- `ThreadLocal<T>` 为每个线程提供独立变量副本。
- 定时任务可用 `Timer`，更推荐 `ScheduledExecutorService`。
- 守护线程是后台服务线程，当只剩守护线程时 JVM 可以退出。
- 普通集合多数不是线程安全的，多线程下可使用同步集合或并发集合，如 `ConcurrentHashMap`、`CopyOnWriteArrayList`。
- Java 8 并行流可用多线程处理集合，但要避免共享可变状态。
- 生产者消费者问题是线程通信和同步的典型练习。
- Java 21 提供线程生成器 `Thread.ofPlatform()`、`Thread.ofVirtual()`。
- Java 21 虚拟线程适合大量 I/O 阻塞任务，创建成本低，但不等于 CPU 计算会自动变快。

### 反射

- 类加载过程大致包括加载、链接、初始化。
- `Class` 对象表示运行时的类信息。
- 获取 `Class` 的常见方式：`类名.class`、`对象.getClass()`、`Class.forName("全限定类名")`。
- 反射可以获取构造方法、方法、字段、注解等元信息。
- 反射可以创建对象：`getConstructor()`/`getDeclaredConstructor()` 后调用 `newInstance()`。
- 反射可以调用方法：`Method.invoke()`。
- 反射可以读取或修改字段：`Field.get()`、`Field.set()`。
- 私有成员可通过 `setAccessible(true)` 尝试突破访问检查，但在模块化环境下可能受到更严格限制。
- 反射和多态结合时，运行时类型决定真实 `Class` 对象。
- Java 9 模块化机制用 `module-info.java` 声明模块依赖与导出包。
- 类加载器 `ClassLoader` 负责加载类，常见有启动类加载器、平台类加载器、应用类加载器。

### 注解

- 注解是给代码附加元数据的机制。
- 预设注解包括 `@Override`、`@Deprecated`、`@SuppressWarnings`、`@FunctionalInterface` 等。
- 元注解用于修饰注解，例如 `@Target`、`@Retention`、`@Documented`、`@Inherited`、`@Repeatable`。
- `@Retention(RetentionPolicy.RUNTIME)` 表示注解保留到运行时，反射才能读取。
- `@Target` 限制注解可以标记的位置。
- 反射可以通过 `getAnnotation()`、`isAnnotationPresent()` 等方法读取注解。

## 2. Java 示例

### 示例一：创建和启动线程

```java
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadCreateDemo {
    public static void main(String[] args) throws Exception {
        Thread t1 = new MyThread();
        t1.start(); // start 会启动新线程

        Thread t2 = new Thread(new MyRunnable());
        t2.start();

        FutureTask<Integer> task = new FutureTask<>(new MyCallable());
        Thread t3 = new Thread(task);
        t3.start();
        System.out.println("Callable 返回值：" + task.get());

        // t1.run(); // 直接调用 run 只是普通方法调用，不会创建新线程
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("继承 Thread 创建线程：" + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("实现 Runnable 创建线程：" + Thread.currentThread().getName());
    }
}

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        return 100; // Callable 可以返回结果
    }
}
```

### 示例二：sleep、interrupt、priority、yield、join

```java
public class ThreadControlDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500); // 让当前线程休眠
                    System.out.println("工作中");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 恢复中断标记
                }
            }
            System.out.println("收到中断，线程结束");
        });

        worker.setPriority(Thread.MAX_PRIORITY); // 优先级只是调度建议
        worker.start();

        Thread.yield(); // 当前线程礼让一次 CPU，不保证效果
        Thread.sleep(1200);
        worker.interrupt(); // 请求中断 worker
        worker.join();      // 等待 worker 执行结束
    }
}
```

### 示例三：synchronized 解决线程安全问题

```java
public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> addMany(counter));
        Thread t2 = new Thread(() -> addMany(counter));
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.get());
    }

    static void addMany(Counter counter) {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}

class Counter {
    private int value;

    synchronized void increment() {
        value++; // synchronized 保证同一时刻只有一个线程执行这里
    }

    int get() {
        return value;
    }
}
```

### 示例四：死锁示意和规避思路

```java
public class DeadlockDemo {
    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        // 不建议运行故意制造死锁的代码。死锁通常来自多个线程获取锁的顺序不一致。
        // 规避方式：所有线程都按相同顺序获取锁。
        Thread t1 = new Thread(() -> safeWork("线程1"));
        Thread t2 = new Thread(() -> safeWork("线程2"));
        t1.start();
        t2.start();
    }

    static void safeWork(String name) {
        synchronized (LOCK_A) {
            synchronized (LOCK_B) {
                System.out.println(name + " 按固定顺序获取锁，避免死锁");
            }
        }
    }
}
```

### 示例五：wait/notify 生产者消费者

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Store store = new Store();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) store.put(i);
        }, "生产者").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) System.out.println(store.take());
        }, "消费者").start();
    }
}

class Store {
    private final Queue<Integer> queue = new ArrayDeque<>();
    private final int capacity = 2;

    synchronized void put(int value) {
        while (queue.size() == capacity) {
            try {
                wait(); // 队列满了，释放锁并等待
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        queue.offer(value);
        notifyAll(); // 通知等待的消费者
    }

    synchronized int take() {
        while (queue.isEmpty()) {
            try {
                wait(); // 队列空了，释放锁并等待
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
        int value = queue.poll();
        notifyAll(); // 通知等待的生产者
        return value;
    }
}
```

### 示例六：ThreadLocal、定时器、守护线程、并发集合、并行流

```java
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadToolDemo {
    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("后台守护任务");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        daemon.setDaemon(true); // 守护线程不会阻止 JVM 退出
        daemon.start();

        USER.set("小明");
        System.out.println(USER.get()); // 每个线程有自己的变量副本
        USER.remove();                  // 使用后清理，避免在线程复用场景中泄漏

        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1); // 并发集合适合多线程读写

        List.of(1, 2, 3, 4)
                .parallelStream()
                .map(n -> n * n)
                .forEach(System.out::println); // 并行流会使用多线程处理

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> System.out.println("延迟执行"), 1, TimeUnit.SECONDS);
        service.shutdown();
    }
}
```

### 示例七：Java 21 线程生成器和虚拟线程

```java
public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread platform = Thread.ofPlatform()
                .name("platform-worker")
                .start(() -> System.out.println("平台线程"));

        Thread virtual = Thread.ofVirtual()
                .name("virtual-worker")
                .start(() -> {
                    // 虚拟线程适合大量阻塞式 I/O 任务
                    System.out.println("虚拟线程：" + Thread.currentThread());
                });

        platform.join();
        virtual.join();
    }
}
```

### 示例八：获取 Class 对象和创建对象

```java
public class ReflectionClassDemo {
    public static void main(String[] args) throws Exception {
        Class<User> c1 = User.class;
        Class<?> c2 = new User("小明").getClass();
        Class<?> c3 = Class.forName("User");

        System.out.println(c1 == c2);
        System.out.println(c2 == c3);

        User user = c1.getDeclaredConstructor(String.class)
                .newInstance("小红"); // 反射调用构造方法创建对象
        System.out.println(user.getName());
    }
}

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### 示例九：反射调用方法和修改字段

```java
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionMemberDemo {
    public static void main(String[] args) throws Exception {
        ReflectPerson person = new ReflectPerson("小明");
        Class<?> clazz = person.getClass();

        Method say = clazz.getDeclaredMethod("say", String.class);
        Object result = say.invoke(person, "你好"); // 反射调用方法
        System.out.println(result);

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true); // 允许访问 private 字段
        name.set(person, "小红");  // 反射修改字段值
        System.out.println(person);
    }
}

class ReflectPerson {
    private String name;

    ReflectPerson(String name) {
        this.name = name;
    }

    public String say(String message) {
        return name + "说：" + message;
    }

    @Override
    public String toString() {
        return "ReflectPerson{name='" + name + "'}";
    }
}
```

### 示例十：模块化机制和类加载器

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(loader); // 应用类加载器通常负责加载项目中的类
        System.out.println(String.class.getClassLoader()); // 核心类可能由启动类加载器加载，显示为 null
    }
}
```

```java
// module-info.java 示例
// module my.app {
//     requires java.base;          // 声明依赖模块，java.base 默认依赖
//     exports com.example.api;     // 导出包给其他模块使用
// }
```

### 示例十一：预设注解和自定义注解

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationDemo {
    public static void main(String[] args) {
        Service service = new Service();
        service.oldMethod();
        service.run();
    }
}

@FunctionalInterface
interface Runner {
    void run(); // @FunctionalInterface 会检查是否只有一个抽象方法
}

class Service implements Runner {
    @Override
    public void run() {
        System.out.println("运行服务");
    }

    @Deprecated
    public void oldMethod() {
        System.out.println("旧方法，不推荐继续使用");
    }
}

@Target(ElementType.TYPE) // 只能标记在类、接口等类型上
@Retention(RetentionPolicy.RUNTIME) // 保留到运行时，反射才能读取
@interface Table {
    String value();
}
```

### 示例十二：反射获取注解

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ReadAnnotationDemo {
    public static void main(String[] args) {
        Class<?> clazz = Product.class;

        if (clazz.isAnnotationPresent(Entity.class)) {
            Entity entity = clazz.getAnnotation(Entity.class);
            System.out.println("表名：" + entity.table());
        }
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Entity {
    String table();
}

@Entity(table = "t_product")
class Product {
    private String name;
}
```
