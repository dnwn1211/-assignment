import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.ArrayList;


public class module2{
	//메모리 DR 스택을 전역변수로 선언 
	static String[][] mem = {
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
	};
	static String DR;
	static Stack<String> stack = new Stack<>();


	//입력한 값을 메모리에 저장함 
	public static void writeMemory(String name,String value) {

		for (int i = 0; i < mem.length; i++) {
		    if(mem[i][0].equals("null")) { //배열이 비어있다면 해당 배열에 새로운값 저장 
		    	mem[i][0] = name;
		    	mem[i][1] = value;
		    	break;
		    }else if(mem[i][0].equals(name)) {//배열이 name과 같다면 값을 새로 넣어줌 
		    	mem[i][1] = value;
		    	break;
		    }
		}

	}
	
	//저장된 메모리 값을 가져옴 
	public static String readMemory(String param1) {
		for(int i = 0; i < mem.length; i++) {
			if(mem[i][0].equals(param1)) {
				return mem[i][1];
			}
		}
		return "readMemory fail";
	}
	
	//입력값이 4개일경우 
	public static String opcode(String opcode,String param1,String param2,String param3) {
		String result;
		
		if(opcode.equals("WRITE")) {
			//스택에 param3값 푸시 
			result = "0011 " + param3 + "\n";
			stack.push(param3);
			
			//메모리주소 param1에 스택값 저장
			writeMemory(param1,stack.peek());
			result +="1000 " + param2 + "\n";
			
			//스택 제거
			stack.pop();
			result +="0101\n";
			
			return result;
		}
		return "fail";
	}
	
	//입력값이 3개일경우 
	public static String opcode(String opcode,String param1,String param2) {
		String result;
		
		//param1값을 DR에 저장
		DR = readMemory(param1);
		result = "0001 " + DR + "\n"; 
		
		//param1값을 스택에 저장
		stack.push(DR);
		result += "0100\n";
		
		//param2값을 DR에 저장
		DR = readMemory(param2);
		result += "0001 " + DR + "\n";
		
		//문자열을 정수형으로 변환 
		String A = stack.pop();
		int num1 = Integer.parseInt(A,2);
		int num2 = Integer.parseInt(DR,2);
		
		//더하기 또는 빼기 
		if(opcode.equals("ADD")) {
			//A+B를 스택에 저장
			int num3 = num1+num2;
			//System.out.println(num1 + "+" + num2 + "=" + num3  );
			stack.push("00"+Integer.toBinaryString(num3)); //정수형을 이진수 문자열로 변환하여 스택에 저장
			result += "0110\n";
			
			//DR에 저장
			
		}else if(opcode.equals("SUB")){
			//A-B를 스택에 저장 
			int num3 = num1-num2;
			stack.push(Integer.toBinaryString(num3)); //정수형을 이진수 문자열로 변환하여 스택에 저장
			result += "0111\n";
		}
		
		DR = stack.pop();
		result +="0101\n";
		
		return result;
	}
	
	//입력값이 2개일경우 
	public static String opcode(String opcode,String param1) {
		String result = null;
		
		if(opcode.equals("STORE")) {
			//DR을 param1에 저장 
			writeMemory(param1,DR);
			
			result = "0010 0001\n";
		}else if(opcode.equals("PRINT")) {
			//C결과 프린트 (두자리만 표현되어서 00을 추가)
			System.out.println(readMemory(param1));
			result = "1001 0001\n";
		}
		
		return result;
	}

	public static void main(String[] args) {
		//Scanner객체 생성
		Scanner sc = new Scanner(System.in);
		Queue<String> queue = new LinkedList<>();
		
		//while문 반복을 위한 bool 변수와 반복횟수를 알기위한 tryNum 변수 생성
		String bool = "yes";
		int tryNum = 0;
		
		//bool값이 false가 될때까지 명령어 입력을 반복
		while(bool.equalsIgnoreCase("yes")){
			//초기화
			String  v1 = null, v2 = null, v3 = null, v4 = null;
			
			//명렁어 입력받기
			String inputCode = sc.nextLine();
			String[] inputs = inputCode.split(" ");
			
			v1 = inputs[0];
			v2 = inputs[1];
			if (inputs.length > 2) {
			    v3 = inputs[2];
			}
			if (inputs.length > 3) {
			    v4 = inputs[3];
			}
			
			//opcode함수에서 연산진행후 그 값을 스택에 저장
		    if (v4 != null) {
		        queue.add(opcode(v1, v2, v3, v4));
		    } else if (v3 != null) {
		        queue.add(opcode(v1, v2, v3));
		    } else {
		        queue.add(opcode(v1, v2));
		    }
			
			System.out.println("more? yes or no");
			bool = sc.nextLine();
			tryNum++;
		}
		
		//스택에 담겨져있는 결과값을 result에 모두 pop
		String result = "";
		for(int i=0; i<tryNum; i++) {
			result = result + queue.poll();
		}
	
		try {
            // 1. 파일 객체 생성
			File file = new File("module2.pl");
			
            // 2. 파일 존재여부 체크 및 생성
            if (!file.exists()) {
                file.createNewFile();
            }else {
            	//파일 존재시 삭제후 새로 만듦
            	file.delete();
            	file.createNewFile();
            }
 
            // 3. Buffer를 사용해서 File에 write할 수 있는 BufferedWriter 생성
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
 
            // 4. 파일에 쓰기
            writer.write(result);

            // 5. BufferedWriter close
            writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}