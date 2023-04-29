import java.util.Scanner;
import java.util.Stack;
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Os_module2_1 {
	static Queue<String> queue = new LinkedList<String>();
	static String[] memory_address=new String[16];
	static String DR;
	static String[][] m_A= {
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
			{"null","null"},
	};
	
	public static void memory(String a,String v) {
		for(int i=0;i<5;i++)
		{
			if(m_A[i][0].equals(a))
			{
				m_A[i][1]=v;
			}
			else if(m_A[i][0].equals("null"))
			{
				m_A[i][0]=a;
				m_A[i][1]=v;
				break;
			}
			
		}
	}
	
	public static String find(String a) {
		for(int i=0;i<5;i++)
		{
			if(m_A[i][0].equals(a))
			{
				return m_A[i][1];
			}
		}
		return "not";
	}

    public static void main(String[] args){
        Scanner prm=new Scanner(System.in);
        System.out.println("진행하시겠습니까? (y/n)");
        String yn=prm.nextLine();
        while(yn.equals("y")) {
        	String opcode=null;
            String p1=null;
            String p2=null;
            String p3=null;
           
            String address=null;
           
            System.out.println("input");
            address=prm.nextLine();
            String[] arr=address.split(" ");

           // String opcode,p1,p2,p3;//write a 0111 0001
       
            opcode=arr[0];
            p1=arr[1];
            if (arr.length > 2) {
                p2=arr[2];
            }
            if (arr.length > 3) {
                p3=arr[3];
            }
           
            Stack<String> stack = new Stack<String>();
           
            String result1=null,result2=null,result3=null;
            String result4=null,result5=null,result6=null;
            if(opcode.equals("Write"))
            {
                //stack p3 값을 넣습니다
            	result1="0011"+" "+p3+"\n";
            	queue.add(result1);
            	stack.push(p3);
               
                //String[] memory_address=new String[16];
                //int i= Integer.parseInt(p2,2);
                //메모리 주소에 스택값 넣습니다.
                memory(p1,stack.pop());
                //memory_address[i]=stack.pop();
                result2="1000"+" "+p2+"\n";
                queue.add(result2);
               
                result3="0101"+"\n";
                queue.add(result3);
                while(!stack.empty()){
                }
                //System.out.println(result1+result2+result3);
            }
            else if(opcode.equals("Add")) {
            	    String mem1 = find(p1);
            	    String mem2 = find(p2);
            	    if(mem1 == null || mem2 == null) {
            	        System.out.println("Invalid memory address(es). Please input again.");
            	        continue;
            	    }
            	    DR = mem1;
            	    result1="0001"+" "+DR+"\n";
            	    queue.add(result1);
            	    
            	    stack.push(DR);
            	    result2="0100"+"\n";
            	    queue.add(result2);
            	    
            	    DR = mem2;
            	    result3="0001"+" "+DR+"\n";
            	    queue.add(result3);
            	    
            	    int DR_v= Integer.parseInt(mem1,2);//0001->1
            	    System.out.println(DR_v);
            	    
            	    int stack_v= Integer.parseInt(mem2,2);//0010->2
            	    System.out.println(stack_v);
            	    
            	    int sum=DR_v+stack_v;//->3
            	    result4="0110"+"\n";
            	    queue.add(result4);
            	    
            	    System.out.println(sum);//3
            	    String sum_v="0"+Integer.toBinaryString(sum);//0011
            	    System.out.println("dd  "+sum_v);
            	    stack.push(sum_v);
            	    DR=stack.pop();
            	    result5="0101"+"\n";
            	    queue.add(result5);
            	    
            	    result6="0010"+" "+DR+"\n";
            	    queue.add(result6);
            }
            else if(opcode.equals("Sub")) {
            	stack.push(DR); //stack<-DR
            	result1="0100"+"\n";
            	queue.add(result1);
            	
            	int sub_1 = Integer.parseInt(stack.pop(),2);  
            	int sub_2 = Integer.parseInt(p1);//3
            	String binaryStr=Integer.toBinaryString(sub_2);
            	sub_2=Integer.parseInt(binaryStr,2);
            	int result=sub_1-sub_2;//stack-3
            	
            	
            	String sub="00"+Integer.toBinaryString(result);
            	stack.push(sub);
            	result2="0111"+"\n";
            	queue.add(result2);
            	
            	
            	DR=stack.pop();
            	result3="0101"+"\n";
            	queue.add(result3);
            	System.out.println(DR);
            	
            }
            else if(p1.equals("1")) {
            	stack.push(DR);
            	result1="0100"+"\n";
            	queue.add(result1);
            	
            	int add_1=Integer.parseInt(stack.pop(),2);
            	int add_2=Integer.parseInt(p1);
            	String binaryStr=Integer.toBinaryString(add_2);
            	add_2=Integer.parseInt(binaryStr,2);
            	int result=add_1+add_2;
            	
            	String add="00"+Integer.toBinaryString(result);
            	stack.push(add);
            	result2="0110"+"\n";
            	queue.add(result2);
            	
            	DR=stack.pop();
            	result3="0101"+"\n";
            	queue.add(result3);
            	System.out.println(DR);
            }
            else if(opcode.equals("Store")){
            	memory(p1,DR);
            	result1="1001"+" "+DR+"\n";
            	queue.add(result1);
            }
            else if(opcode.equals("Print")){
            	find(p1);
            	System.out.println("00"+find(p1));
            	result1="00"+find(p1)+"\n";
            	queue.add(result1);
            }

            System.out.println("more input?? (y/n)");
            yn=prm.nextLine();
            if(yn.equals("y")) {
            	
            	continue;
            }
            else{
            	try {
        		    // FileWriter 객체를 생성합니다.
        		    FileWriter fw = new FileWriter("C:\\OS\\module2_1.pl");

        		    while (!queue.isEmpty()) {
        		        String element = queue.poll();
        		        fw.write(element);
        		    }
        		    // FileWriter 객체를 닫습니다.
        		    fw.close();

        		    System.out.println("파일에 문자열을 성공적으로 썼습니다.");
        		} catch(IOException e) {
        		    e.printStackTrace();
        		}
            	break;
            }
        }
           
        prm.close();
       
    }
}