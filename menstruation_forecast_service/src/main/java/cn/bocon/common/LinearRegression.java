package cn.bocon.common;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * File        : DataPoint.java
 * Author      : zhouyujie
 * Date        : 2012-01-11 16:00:00
 * Description : Java实现一元线性回归的算法，线性回归测试类，(可实现统计指标的预测)
 */
 
/**
 * <p>
 * <b>Linear Regression</b> <br>
 * Demonstrate linear regression by constructing the regression line for a set
 * of data points.
 * 
 * <p>
 * require DataPoint.java,RegressionLine.java
 * 
 * <p>
 * 为了计算对于给定数据点的最小方差回线，需要计算SumX,SumY,SumXX,SumXY; (注：SumXX = Sum (X^2))
 * <p>
 * <b>回归直线方程如下： f(x)=a1x+a0 </b>
 * <p>
 * <b>斜率和截距的计算公式如下：</b> <br>
 * n: 数据点个数
 * <p>
 * a1=(n(SumXY)-SumX*SumY)/(n*SumXX-(SumX)^2) <br>
 * a0=(SumY - SumY * a1)/n <br>
 * (也可表达为a0=averageY-a1*averageX)
 * 
 * <p>
 * <b>画线的原理：两点成一直线，只要能确定两个点即可</b><br>
 * 第一点：(0,a0) 再随意取一个x1值代入方程，取得y1，连结(0,a0)和(x1,y1)两点即可。
 * 为了让线穿过整个图,x1可以取横坐标的最大值Xmax，即两点为(0,a0),(Xmax,Y)。如果y=a1*Xmax+a0,y大于
 * 纵坐标最大值Ymax，则不用这个点。改用y取最大值Ymax，算得此时x的值，使用(X,Ymax)， 即两点为(0,a0),(X,Ymax)
 * 
 * <p>
 * <b>拟合度计算：(即Excel中的R^2)</b>
 * <p>
 * *R2 = 1 - E
 * <p>
 * 误差E的计算：E = SSE/SST
 * <p>
 * SSE=sum((Yi-Y)^2) SST=sumYY - (sumY*sumY)/n;
 * <p>
 * https://blog.csdn.net/zyujie/article/details/9245089
 */
public class LinearRegression {
 
	private static final int MAX_POINTS = 10;
 
	private double E;
 
	/**
	 * Main program.
	 * 
	 * @param args
	 *            the array of runtime arguments
	 */
	public static void main(String args[]) {
		RegressionLine line = new RegressionLine();
 
		List<String> list = Lists.newArrayList();
		List<Long> dateList = Lists.newArrayList();
		list.add("2018-8-18");
		list.add("2018-9-15");
		list.add("2018-10-17");
		list.add("2018-12-16");
		list.add("2019-1-15");
		list.add("2019-2-16");
		list.add("2019-3-21");
		list.add("2019-4-25");
		//list.add("2019-5-25");
		
		for (String temp : list) {
			dateList.add(DateUtil.stringToDate(temp, DateUtil.DATE_FORMAT).getTime()/100000);
		}
		for (int i = 0; i < dateList.size(); i++) {
			line.addDataPoint(new DataPoint(i+1, dateList.get(i)));
		}
		//printSums(line);
		//printLine(line);
		int size = dateList.size() + 1;
		//System.out.println(size);
		Float i = (line.getA1()*size + line.getA0());
		
		System.out.println(DateUtil.dateToCompactString(new Date(i.longValue()*100000)));
	}
 
	/**
	 * Print the computed sums.
	 * 
	 * @param line
	 *            the regression line
	 */
	private static void printSums(RegressionLine line) {
		System.out.println("\n数据点个数 n = " + line.getDataPointCount());
		System.out.println("\nSum x  = " + line.getSumX());
		System.out.println("Sum y  = " + line.getSumY());
		System.out.println("Sum xx = " + line.getSumXX());
		System.out.println("Sum xy = " + line.getSumXY());
		System.out.println("Sum yy = " + line.getSumYY());
 
	}
 
	/**
	 * Print the regression line function.
	 * 
	 * @param line
	 *            the regression line
	 */
	private static void printLine(RegressionLine line) {
		System.out.println("\n回归线公式:  y = " + line.getA1() + "x + "
				+ line.getA0());
		System.out.println("误差：     R^2 = " + line.getR());
	}
	
	//y = 2.1x + 133.7   2.1 * 6 + 133.7 = 12.6 + 133.7 = 146.3
	//y = 2.1x + 133.7   2.1 * 7 + 133.7 = 14.7 + 133.7 = 148.4
 
}
