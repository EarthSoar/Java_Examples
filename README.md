## 后台

### 准备工作

- 创建domain，数据库的列封装成对象，命名为Product
- 创建DAO接口，包含定义数据库的增删改查的方法
- 创建DAO的实现类，先写结构。
- 创建测试类，可以用单元测试Junit4对接口自动生成

### 完整实现DAO的实现类

- 抽取工具类JdbcUtil

  因为每一个增删改查方法都要`加载注册驱动`，`获取连接对象`，`关闭资源`。所以把这些公共的部分抽取出来作为JdbcUtil，其中需要的值 driverClassName，url，username，password放在db.properties资源文件中。也可以使用阿里巴巴的线程池获取连接对象。

- 抽取模板JdbcTemplate

  - 每一个增删改的方法其实sql语句不相同，另外的内容就仅仅只是对sql语句传递参数，然后执行executeUpdate方法，所以把sql和参数作为参数传递到模板方法中，在模板中实现具体的设置参数值，然后在增删该方法调用模板方法即可。
  - 查询操作也是有很多的相同操作，但是查询操作需要返回一个结果集，对于模板来说讲究的是通用操作，所以在这里必须创建一个规范(结果集处理器接口)，各自的结果集处理必须实现这个接口，然而只需要在模板方法中传递一个接口类型的参数就行，实际调用的是自己的结果集实现类，这个结果集实现类放在DAO实现类中作为内部类就行。

- 在测试类中进行单元测试

### 高级查询

高级查询就是根据条件进行查询然后显示在网页上。把查询的条件和条件中需要的参数都封装到一个对象中，把这个对象就叫做查询对象，修改DAO中的list方法里面传入一个查询对象，接着就是拼接SQL重新查询，这样就实现了高级查询。

- 抽取查询对象的规范IQuery

  每一个DAO肯定都需要高级查询，所以把获取条件和获取条件中参数两个方法抽取出来放在IQuery接口中，每个查询对象类都需要实现此接口

- 抽取查询对象基类QueryObject

  其实各个查询对象中只有查询的名称不同，其他的获取条件的内容都相同，所以把相同的部分抽取出来，暴露给子类一个方法让子类去自己定义自己的查询内容字段，并且添加到查询基类中定义的集合中(两个封装了查询条件和查询条件需要的参数的集合)。

- 定义查询对象类

  只需要继承查询对象的基类

- 测试

### 分页查询

数据较多时在一个页面会看起来会比较困难，所以采用分页设计。

- 创建pageResult封装分页需要的所有信息

  ```java
  private List<?> listData;//结果集
  private Integer totalCount;//结果总数
  
  private Integer currentPage;//当前页，用户传入
  private Integer pageSize;//页面大小,用户传入
  
  private Integer beginPage = 1;//首页
  private Integer prevPage;//上一页  :需要计算
  private Integer nextPage;//下一页 ：需要计算
  private Integer totalPage;//总页数/末页   :需要计算
  ```

- 在DAO中改造查询的方法,此时返回的list的范围更加具体，也就是返回的是pageResult中的listData，另外需要用户传入currentPage和pageSize可以进一步封装到QueryObject中，让每一个查询对象都有这两个字段。

- 然后在DAO实现类中实现上面定义的方法，包含查询结果集和查询结果总数。

- 最后测试后台

## 前台

### 准备工作

创建ProductServlet

- 初始化，包含创建DAO实现类的对象，目的是调用后台增删改查的方法
- 在service方法中对增删改查实现分发操作，也就是在网页中接受一个cmd参数然后看调用增删改查的哪一个方法
- 创建页面edit.jsp 和 list.jsp

### 具体实现Servlet中的CRUD方法

![img](https://s2.ax1x.com/2019/01/08/FLEUw8.png) 

### 高级查询

只要把后台测试通过前台很简单

- 在list.jsp中写html代码，实现一个表单用来传递参数
- 在Servlet中接收表单中的参数，并且封装到后台的查询对象中。
- 调用DAO的list(qo)方法,(传入查询对象)共享到jsp中

### 分页查询

在jsp中首页,上一页，下一页前台需要展示的超链还有需要跳转到那一页等元素，把参数传给后台，让Servlet接收请求参数，把当前页和页面大小两个参数设置给查询对象，最后调用后台方法返回一个pageResult共享给jsp。

需要注意的是查询和分页在一起可能会使查询的条件丢失，这是因为这里使用的超链接都是一次新的请求，为了解决这一问题，把翻页的信息放在高级查询的表单中，然后调用js函数来再次提交查询的表单。