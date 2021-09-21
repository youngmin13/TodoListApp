package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.Date;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	static Date time;
	
	public static void saveList(TodoList l, String filename)
	{
		try {
			Writer w = new FileWriter("todolist.txt");
			
			for (TodoItem item : l.getList())
			{
				w.write(item.toSaveString());
			}
			
			w.close();
			
			System.out.println("모든 데이터가 저장되었습니다.");
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename)
	{
		
	}
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가]");
		System.out.print("제목 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복되었습니다.");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("삭제할 항목의 제목 입력 > ");
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]");
		System.out.print("수정할 항목의 제목 입력 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정 완료");
			}
		}

	}

	public static void listAll(TodoList l) {

		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
			System.out.print("[" + item.getTitle());
			System.out.println( "] " + item.getDesc() + " - " + item.getCurrent_date());
		}
	}
}
