import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.Iterator;
import java.lang.*;
import java.io.*;

class Puzzle extends Pqueue {
	static HashMap<String,Node> graph=new HashMap<String,Node>();

	private static class Node implements Comparable<Node> {
		int visit;
		private int distance;
		private int moves;
		private String path;
		private String val;
		ArrayList<edge> edge=new ArrayList();
		Node(String s){
			this.val = s;
			this.visit=0;
			this.distance = (int)1e9;
		}
		@Override
		public int compareTo(Node node)
		{
			if(this.distance < node.distance)
				return  1;
			else if(this.distance == node.distance)
			{
				if(this.moves <= node.moves)
					return 1;

			}
			return 0;
		}
	}

	static class edge{
		String str;
		int type;
		int weig;
		edge(String str,int w,int type){
			this.str=str;
			this.weig=w;
			this.type = type;
		}
	}
	public static int swap[][] = new int[][]{      
        { 0,3,0,4,0,0,0,0,0}, //1                   
        { 2,0,3,0,4,0,0,0,0}, //2                  
        { 0,2,0,0,0,4,0,0,0}, //3                 
        { 1,0,0,0,3,0,4,0,0}, //4                
        { 0,1,0,2,0,3,0,4,0}, //5              
        { 0,0,1,0,2,0,0,0,4}, //6
		{ 0,0,0,1,0,0,0,3,0}, //           
        { 0,0,0,0,1,0,2,0,3},
 		{ 0,0,0,0,0,1,0,2,0}
	 }; 

	static void createGraph(String str,String str1,Scanner input,PrintWriter out,int query){
		int i;
		int len[] = new int[8];
		for(i=0;i<8;i++)
			len[i] = input.nextInt();
		Pqueue q = new Pqueue();
		q.add(graph.get(str));
		int pr=0;
		while(q.size() != 0)
		{
	//		System.out.println(q.size());
			Node node= (Node)q.remove();		
			if(node.visit>0)
				continue;	
		
			node.visit= query;
			if(node.val.equals(str1))
			{
				pr=1;
		//		System.out.println(node.val+"   tou");
				break;
			}
			str = node.val;
			char ar[]=str.toCharArray();
			for(i=0;i<9;i++)
				if(ar[i]=='0') break;
	//		System.out.println("ori- "+str);
			for(int j=0;j<str.length();j++)
			{
				if(swap[i][j] > 0)
				{
					int val = (int)(ar[j] - '0');
					char swa = ar[i];
					ar[i] = ar[j];
					ar[j] = swa;
					String s = new String(ar);
				//	graph.get(str).edge.add(new edge(s,val,swap[i][j]));
					ar[j] = ar[i];
					ar[i] = swa;
	//				System.out.println("Swap "+s);
					if(graph.containsKey(s))
					{
	/*					if(!(graph.get(s).visit ==query||graph.get(s).visit == 0))
						{
							graph.get(s).visit =0;
							graph.get(s).distance = (int)1e9;
						//	graph.get(s).path = "";
						}*/
					if(node.distance + len[val-1] < graph.get(s).distance)	
						{
							Node n1 = graph.get(s);
							n1.distance = node.distance + len[val-1];
							n1.moves = node.moves +1;
							n1.path =  node.path +val;
							if(swap[i][j] == 1)
								n1.path +="D";
							else if(swap[i][j] ==2)
								n1.path += "R";
							else if(swap[i][j] == 3)
								n1.path += "L";
							else 
								n1.path += "U";

							n1.path += " ";
	//						System.out.println(s);
							q.add(graph.get(s));
						}
					}
					else
					{
						Node n = new Node(s);	
						graph.put(s,n);
						if(node.distance + len[val-1] < n.distance)
						{
							n.distance = node.distance + len[val-1];
							n.moves = node.moves +1;
							n.path = node.path + val;
							if(swap[i][j] == 1)
								n.path +="D";
							else if(swap[i][j] ==2)
								n.path +="R";
							else if(swap[i][j] == 3)
								n.path +="L";
							else
								n.path +="U";
							n.path +=" ";
	//						System.out.println(s);
							q.add(graph.get(s));
                                        		                   
						}
					}
					
				}
			}	
		}
		if(pr==1)
		{
			out.write(graph.get(str1).moves +" "+graph.get(str1).distance+'\n');
			out.write(graph.get(str1).path+'\n');
		}
		else
		{
			out.write("-1 -1\n");
			out.write('\n');
		}
		graph.clear();
	}

	public static boolean issolv(String s)
	{
		char ar[] = s.toCharArray();
		int inv=0;
		for(int i=0;i<8;i++)
		{
			for(int j=i+1;j<9;j++)
			{
				if(((ar[j]-'0')>0) && ((ar[i]-'0')>0) &&( (ar[i]-'0') > (ar[j]-'0')))
					inv++;
			}
		}
		return inv%2==0;
	}
	public static void main(String[] args) throws Exception{
	try{	
		long start=System.currentTimeMillis();
		/*	Thread t = new Thread(null,new Puzzle(),"Puzzle",1<<28);
			t.start();
			t.join();*/
		Scanner input = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1],false),true);	
	
			
	
		int t= input.nextInt();
		String a="",b="";
		for(int i=0;i<t;i++)
		{		
			//String str="023564781";
			a = input.next();
			b = input.next();
			char chary[] = a.toCharArray();
			for(int j=0;j<9;j++)
			{
				if(chary[j]=='G')
				{
					chary[j] = '0';
					break;
				}
			}
			a= new String(chary);
			chary = b.toCharArray();
			for(int j=0;j<9;j++)
			{
				if(chary[j] == 'G')
				{
					chary[j] = '0';
					break;
				}
			}
			b = new String(chary);
			if((issolv(a)^issolv(b)))
			{
			//	out.write(issolv(a)+" \n");
			//	out.write(issolv(b)+" \n");
				out.write("-1 -1\n\n");
				int ar[] = new int[8];
				for(int j=0;j<8;j++)
					ar[j] = input.nextInt();
				continue;
			}
			

			// dikstras
			Node n = new Node(a);
			n.distance = 0;
			n.moves = 0;
			n.path = "";
			n.visit = 0;
			//String str1 = "265471830";
			graph.put(a,n);
			createGraph(a,b,input,out,i+1);
		}
			/*for(int i=0;i<9580440;i++)
				continue;	
			*/
		//System.out.println(issolv("012345687"));	
		long end=System.currentTimeMillis();
	//	System.out.println("code ends here");
	//	System.out.println(end-start);    //n.edge.add(new Edge("102345678",1));
	//	System.out.println(graph.size()); //Node n1=new Node();
			//String str1="123468570";
			//graph.put(str1,n1);
			//createGraph(str1);
			//graph.put("012345678",n);
	//		print();
			out.close();
	}	
	catch(FileNotFoundException e){
		System.out.println("file not found");}
	}
}
