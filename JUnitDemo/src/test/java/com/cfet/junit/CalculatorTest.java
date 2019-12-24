package com.cfet.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

/**
  * 普通的单元测试
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
@RunWith(Parameterized.class)
public class CalculatorTest {
	private int excepted;
	private int  one;
	private int  two;
	@Parameterized.Parameters
	public static Collection<?> data(){
		return Arrays.asList(new Object[][] {{2,4,2},{3,6,2},{6,8,2}});
	}
    @BeforeClass
	public static void init() {
    	// 使用@BeforeClass，必须静态方法，只会执行一次
    	// 适用对象数据库连接等耗时操作
		System.out.println("初始化操作");
	}
    @Before
    public void start() {
    	System.out.println("每一个@Test方法之前都要执行这个方法");
    }
	@Test
	public void testAdd() {
      assertEquals(5,Calculator.add(2, 3));
		//fail("Not yet implemented");
	}
	/**
	   * 构造函数执行顺序低于@beforeClass 
	   * 其后执行顺序 @before ,@test函数 
	 */
    public CalculatorTest(int excepted,int one,int two) {
    	this.excepted  = excepted;
    	this.one =one ;
    	this.two =two;
		System.out.println("我是一个构造函数");
	}
	@Test
	public void testSubtract() {
		//参数化测试
		assertEquals(this.excepted,Calculator.subtract(one, two));
		//fail("Not yet implemented");
	}
    /**
          * 异常测试
     */
	@Test(expected = ArithmeticException.class)
	public void testDivide() {
		//如果测试到了异常，会显示通过，没测到会报错
		assertEquals(2,Calculator.divide(2,0));
		//fail("Not yet implemented");
	}
	@After
	public  void end() {
		System.out.println("每一个@Test方法之后都会执行这个方法");
	}
	@AfterClass
	public  static void free() {
		// 使用AfterClass，必须静态方法，只会执行一次
		System.out.println("释放资源");
	}
   
}
