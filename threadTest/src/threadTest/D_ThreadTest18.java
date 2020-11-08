package threadTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
  	Vector, Hash table 등의 예전부터 존재하던 Collection 객체들은 내부에 동기화 처리가 되어있다.
	그런데, 최근에 새로 구성된 Collection들은 동기화 처리가 되어있지 않다.
	그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.

 */

public class D_ThreadTest18 {

	public static void main(String[] args) {
		Vector<Integer> vec = new Vector<>();
		
		//동기화 처리가 되지 않은 list
		List<Integer> list1 = new ArrayList<>();

		//동기화 처리를 한 경우
		//synchronzedList를 사용하여 안에 배열을 넣어줌 
		List<Integer>list2 = Collections.synchronizedList(new ArrayList<>());
		
		// 익명 구현체로 스레드 구현
		Runnable r = new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
//					vec.add(i);
					list1.add(i);

				}
			}

		};

		Thread[] ths = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };
		
		for (Thread th : ths) {
			th.start();
		}
		
		for (Thread th : ths) {

			try {
				th.join();
			} catch (InterruptedException e) {

			}

		}
		
//		System.out.println("vec의 개수 : " + vec.size());
		System.out.println("list1의 개수 : " + list1.size());	//실제 만들어진 개수도 5만개가 아니라 에러가 발생 
		
		
	}
}
