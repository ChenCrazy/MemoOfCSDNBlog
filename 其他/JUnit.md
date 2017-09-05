Junit测试
1. 对一个类进行测试
2. 另建一个测试类，测试哪个方法则在测试类中新建命名为testxxx的子方法。在之前使用@Test注解，并导入Junit相关包，排除所有异常。使用RunAsJUnitTest测试运行。在新建方法内new出此类的对象，调用此类对象的被测试方法。
3. 对被测试的每个方法执行此过程
4. 进行测试时，点击被测的方法名后RunAs则测试此方法，点击测试类名则测试类中所有测试方法
5. 使用@Before,@After注解，进行初始化工作和测试后收尾工作。在一次测试中，每个测试方法运行前都会运行一次此注解下的方法。
6. 在测试方法之前运行的方法。一次测试只执行一次的方法。`public static void beforeClass(){System.out.print("beforeClass")}` `public static void afterClass(){System.out.print("afterClass")} `
7. Assert断言。例如Assert.assert***Equals(xxx,A.b);即断言A类的b方法返回结果一定是xxx。断言有很多方法。
