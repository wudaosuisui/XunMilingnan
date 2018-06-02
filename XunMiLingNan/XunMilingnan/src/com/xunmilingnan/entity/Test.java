package com.xunmilingnan.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Test {
	public static void main(String[] ages) {
		System.out.println("get main success");
		String encoding = "UTF-8";
		File file = new File("F:\\Documents\\File\\Radio1.txt");
		File newFile = new File("F:\\Documents\\File\\"+ "Radio2.txt");
		try {
			FileOutputStream file3 =new FileOutputStream("F:\\Documents\\File\\Radio3.txt",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
