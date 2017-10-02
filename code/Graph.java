package MyJava;

import java.io.*;
import java.util.*;
class Node  {
	String vertex=null;
	Node nextNode=null;
	int weight=0;
	Node(){}
	Node(String s){vertex=new String(s);}
	@Override
	public boolean equals(Object o) {
		Node n=(Node) o;
		if(this==o)
			return true;
		else if(this.vertex.equals(n.vertex))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return vertex.hashCode();
	}
}
public class Graph {	
	List<Node> adjacencyList=new LinkedList<Node>();
	Map<String,Integer> strIndex=new HashMap<String,Integer>();
	List<String>pathMark=new LinkedList<String>();
	int []visit=new int[10];
	List<String>path=new LinkedList<String>();
	public Graph(String fileName) throws IOException {
		Creat_adjacencyList(fileName);
	}
	private   List<String> readText (String fileName) throws IOException {
		File file=new File("/Users/joker/Desktop/"+fileName+".txt");
		FileReader fr=new FileReader(file);
		BufferedReader bufr=new BufferedReader(fr);
		String s=new String();
		String s1=new String();
		while((s1=bufr.readLine())!=null)
			s+=s1+"\n";
		StringBuilder builder=new StringBuilder();
		s=s.trim();
		s=s.toLowerCase();
		for(int i=0;i!=s.length();i++) {
			if((s.charAt(i)>='a'&&s.charAt(i)<='z')) 
				builder.append(s.charAt(i));
			else
				builder.append(" ");
		}
		s=builder.toString();
		List<String> list=new LinkedList<String>();
		String[] arrString=s.split(" ");
		for(int i=0;i!=arrString.length;i++) {
			if(!arrString[i].equals("")) {
				list.add(arrString[i]);
			}
		}
		bufr.close();
		return list;
	}
	private void Creat_adjacencyList(String fileName) throws IOException {
		List<String>list=readText(fileName);
		int count=0;
		for(int i=0;i!=list.size();i++) {
			if(!strIndex.containsKey(list.get(i)))
			{
				adjacencyList.add(new Node(list.get(i)));
				strIndex.put(list.get(i), count++);
			}
		}
		for(int i=1;i!=list.size();i++) {
			String pstr=list.get(i-1);
			String qstr=list.get(i);
			Node temp=adjacencyList.get(strIndex.get(pstr)).nextNode;
			while(temp!=null) {
				if(temp.vertex.equals(qstr))
					break;
				temp=temp.nextNode;
			}
			if(temp!=null)
				temp.weight++;
			else {
				temp=new Node(qstr);
				temp.nextNode=adjacencyList.get(strIndex.get(pstr)).nextNode;
				adjacencyList.get(strIndex.get(pstr)).nextNode=temp;
				temp.weight++;
				adjacencyList.get(strIndex.get(pstr)).weight++;
			}
		}
	}
	public void showDirectedGraph() {
		StringBuilder format =new StringBuilder() ;
		Iterator<Node> iter=adjacencyList.iterator();
		while(iter.hasNext()) {
			Node temp1=iter.next();
			if(temp1!=null)
			{
				Node temp2=temp1.nextNode;
				while(temp2!=null) {
					format.append(temp1.vertex+"->"+temp2.vertex+"[label="+temp2.weight+"];");	
					temp2=temp2.nextNode;
				}
			}
		}
		GraphViz.createDotGraph(format.toString(), "DotGraph");
	}
	public List<String> queryBridgeWords1(String word1,String word2) {
		List<String> list=new LinkedList<String>();
		if(!word1.equals(word2)) {
			Iterator<Node>iter=adjacencyList.iterator();
			while(iter.hasNext()) {
				Node temp1=iter.next();
				if(temp1.vertex.equals(word1)) {
					if(temp1.weight==1) {
						return list;
					}
					temp1.weight=1;
					Node temp2=temp1.nextNode;
					while(temp2!=null) {
						List<String> list2=new LinkedList<String>();
						list2=queryBridgeWords1(temp2.vertex, word2);
						if(list2.size()!=0) {
							list.addAll(list2);
							list.add(word1);
						}
						temp2=temp2.nextNode;
					}
					temp1.weight=0;
					break;
				}
			}
			return list;
		}
		else {
			list.add(word2);
		}
		return list;
	}
	public List<String> queryBridgeWords(String word1,String word2){
		List<String>list =new LinkedList<String>() ;
		String format=new String();
		Iterator<Node> iter=adjacencyList.iterator();
		Node temp=null;
		Node temp1=null;
		int flag1=0,flag2=0;
		while(iter.hasNext()) {
			temp=iter.next();
			if(temp.vertex.equals(word1)) {
				temp1=temp;
				flag1=1;
			}
			if(temp.vertex.endsWith(word2))
				flag2=1;
		}
		if(temp1!=null) temp1=temp1.nextNode;
		while(temp1!=null) {
			Node temp2=null;
			temp2=adjacencyList.get(strIndex.get(temp1.vertex));
			Node temp3=null;
			if(temp2!=null) temp3=temp2.nextNode;
			while(temp3!=null) {
				if(temp3.vertex.equals(word2)) {
					list.add(temp2.vertex);
					break;
				}
				temp3=temp3.nextNode;
			}
			temp1=temp1.nextNode;
		}
		if(list.size()==0) {
			if(flag1==0&&flag2==0) {
				format="No \""+word1+"\" and \""+word2+"\" in the graph!";
			}else if(flag1==0) {
				format="No \""+word1+"\" in the graph!";
			}else if(flag2==0) {
				format="No \""+word2+"\" in the graph!";
			}else {
				format="No bridge words from \""+word1+"\" to \""+word2+"\"!";
			}
		}else if(list.size()==1){
			format="The bridge word from \""+word1+"\""+" to \""+word2+"\" is: "+list.get(0);
		}else {
			format="The bridge words from \""+word1+"\""+" to \""+word2+"\" are: ";
			for(int i=0;i!=list.size();i++) {
				format+=list.get(i)+" ";
			}
		}
		list.add(0,format);
		return list;		
	}
	public String generateNewText(String inputText) {
		String[] words=inputText.split(" ");
		StringBuilder format=new StringBuilder();
		for(int i=1;i!=words.length;i++) {
			List<String> list=queryBridgeWords(words[i-1],words[i]);
			format.append(words[i-1]+" ");
			Random rand=new Random();
			if(list.size()>1)
			{
				int k=rand.nextInt(list.size()-1);
				format.append(list.get(k+1)+" ");
			}
		}
		format.append(words[words.length-1]);
		return format.toString();
	}
	public List<String>calcShortestPath1(String word1,String word2){
		List<String>list=new LinkedList<String>();
		int flag1=0,flag2=0;
		List<Integer> cost=new ArrayList<Integer>();
		List<Integer> flag=new ArrayList<Integer>();
		List<String> path=new ArrayList<String>();
		for(int i=0;i!=adjacencyList.size();i++) {
			cost.add(99999);
			flag.add(0);
			path.add("");
			if(adjacencyList.get(i).vertex.equals(word1))
				flag1=1;
			if(adjacencyList.get(i).vertex.equals(word2))
				flag2=1;
		}
		cost.set(strIndex.get(word1), 0);
		path.set(strIndex.get(word1), word1);
		String pathMark=word1;
		for(int count=adjacencyList.size()-1;count!=0;count--) {			
			int minCost=99999;
			for(int i=0;i!=adjacencyList.size();i++) {
				String str=adjacencyList.get(i).vertex;
				if(flag.get(strIndex.get(str))!=1&&cost.get(strIndex.get(str))<minCost) {
					minCost=cost.get(strIndex.get(str));
					pathMark=str;
				}
			}
			flag.set(strIndex.get(pathMark),1);
			Node temp=adjacencyList.get(strIndex.get(pathMark)).nextNode;
			while(temp!=null) {
				int costW=cost.get(strIndex.get(temp.vertex));
				if(flag.get(strIndex.get(temp.vertex))!=1&&minCost+temp.weight<costW) {
					cost.set(strIndex.get(temp.vertex), minCost+temp.weight);
					path.set(strIndex.get(temp.vertex),pathMark);
				}
				temp=temp.nextNode;
			}			
		}
		if(flag1==0&&flag2==0) {
			list.add("No \""+word1+"\" and \""+word2+"\" in the graph!");
		}else if(flag1==0) {
			list.add("No \""+word1+"\" in the graph!");
		}else if(flag2==0) {
			list.add("No \""+word2+"\" in the graph!");
		}else {
			if(word2.equals(""))
				list.add("");
			for(int i=0;i!=adjacencyList.size();i++) {
				String format="";
				String temp=path.get(strIndex.get(adjacencyList.get(i).vertex));
				int costW=cost.get(strIndex.get(adjacencyList.get(i).vertex));
				if(costW!=99999)
				{
						while(!temp.equals(word1)) {
						format=temp+"->"+format;
						temp=path.get(strIndex.get(temp));
					}
						format=word1+"->"+format+adjacencyList.get(i).vertex;
				}
				else
				{
					format="There is no path between "+word1+" and "+word2;
				}
				if(word2.equals(adjacencyList.get(i).vertex)) {
					list.add(0,format);
				}
				list.add(format);
			}
		}
		return list;
	}
	void Initialize_path() {
		pathMark.clear();
		for(int i=0;i!=visit.length;i++) visit[i]=0;
		path.clear();
	}
	String printPath(List<String> pathMark) {
		StringBuilder builder=new StringBuilder();
		int i=0;
		for(;i!=pathMark.size()-1;i++)
			builder.append(pathMark.get(i)+"->");
		builder.append(pathMark.get(i));
		return builder.toString();
	}
	void dfs(int shortestPathMartrix[][],String word1,String word2) {
		visit[strIndex.get(word1)]=1;
		pathMark.add(word1);
		if(word1==word2) {
			path.add(printPath(pathMark));
		}
		else {
			int i=strIndex.get(word1);
			for(int j=0;j!=adjacencyList.size();j++) {
				String temp=adjacencyList.get(j).vertex;
				if(shortestPathMartrix[i][strIndex.get(temp)]!=0&&visit[strIndex.get(temp)]!=1) {
					dfs(shortestPathMartrix,temp,word2);
				}
			}
		}
		visit[strIndex.get(word1)]=0;
		pathMark.remove(pathMark.size()-1);
	}
	public List<List<String>>calcShortestPath(String word1,String word2){
		List<List<String>> shortestPathInfo= new LinkedList<List<String>>();
		int flag1=0,flag2=0;
		List<Integer> cost=new ArrayList<Integer>();
		List<Integer> flag=new ArrayList<Integer>();
		List<String> path=new ArrayList<String>();
		int [][]shortestPathMartix=new int[15][15];
		for(int i=0;i!=adjacencyList.size();i++) {
			cost.add(99999);
			flag.add(0);
			path.add("");
			if(adjacencyList.get(i).vertex.equals(word1))
				flag1=1;
			if(adjacencyList.get(i).vertex.equals(word2))
				flag2=1;
		}
		if(word2.equals(""))
			flag2=1;
		List<String> list=new LinkedList<String>();
		if(flag1==0&&flag2==0) {
			list.add("No \""+word1+"\" and \""+word2+"\" in the graph!");
			shortestPathInfo.add(list);
		}else if(flag1==0) {
			list.add("No \""+word1+"\" in the graph!");
			shortestPathInfo.add(list);
		}else if(flag2==0) {
			list.add("No \""+word2+"\" in the graph!");
			shortestPathInfo.add(list);
		}else {
			cost.set(strIndex.get(word1), 0);
			path.set(strIndex.get(word1), word1);
			String pathMark=word1;
			for(int count=adjacencyList.size()-1;count!=0;count--) {			
				int minCost=99999;
				for(int i=0;i!=adjacencyList.size();i++) {
					String str=adjacencyList.get(i).vertex;
					if(flag.get(strIndex.get(str))!=1&&cost.get(strIndex.get(str))<minCost) {
						minCost=cost.get(strIndex.get(str));
						pathMark=str;
					}
				}
				flag.set(strIndex.get(pathMark),1);
				Node temp=adjacencyList.get(strIndex.get(pathMark)).nextNode;
				while(temp!=null) {
					int costW=cost.get(strIndex.get(temp.vertex));
					if(flag.get(strIndex.get(temp.vertex))!=1&&minCost+temp.weight<costW) {					
						shortestPathMartix[strIndex.get(pathMark)][strIndex.get(temp.vertex)]=temp.weight;
						String prePathMark=path.get(strIndex.get(temp.vertex));
						if(!prePathMark.equals(""))
							shortestPathMartix[strIndex.get(prePathMark)][strIndex.get(temp.vertex)]=0;
						cost.set(strIndex.get(temp.vertex), minCost+temp.weight);
						path.set(strIndex.get(temp.vertex),pathMark);
					}
					else if(flag.get(strIndex.get(temp.vertex))!=1&&minCost+temp.weight==costW) {
						shortestPathMartix[strIndex.get(pathMark)][strIndex.get(temp.vertex)]=temp.weight;
					}
					temp=temp.nextNode;
				}			
			}
			if(word2.equals(""))
			{
				list.add("The shortest paths from \""+word1+"\" to the other words are: ");
				shortestPathInfo.add(list);
			}
			for(int i=0;i!=adjacencyList.size();i++) {
				String temp=adjacencyList.get(i).vertex;
				if(!temp.equals(word1)) {
					Initialize_path();
					dfs(shortestPathMartix, word1, temp);
					List<String>list2=new LinkedList<String>();
					list2.addAll(this.path);
					if(list2.size()==0)
						list2.add("There is no shortest path from \""+word1+"\" to \""+temp+"\"");
					shortestPathInfo.add(list2);
					if(temp.equals(word2)) {
						list2.add(0,"The shortest paths from  \""+word1+"\" to \""+word2+"\" are: ");
						shortestPathInfo.add(0,list2);
					}
				}
			}
		}
		return shortestPathInfo;
	}
	private Node randStart=null;
	private int[][]randVisit=new int[15][15];
	public void randInit() {
		randStart=null;
		randVisit=new int[15][15];
	}
	public String randomWalk() {
		Random random=new Random();
		if(randStart==null) {
			int startPos=random.nextInt(adjacencyList.size());
			randStart=adjacencyList.get(startPos);
			return randStart.vertex;
		}else {
			Node temp=adjacencyList.get(strIndex.get(randStart.vertex));
			List<Node>list=new LinkedList<Node>();
			Node temp2=temp.nextNode;
			while(temp2!=null) {
				if(randVisit[strIndex.get(temp.vertex)][strIndex.get(temp2.vertex)]!=1) {
					list.add(temp2);
				}
				temp2=temp2.nextNode;
			}
			if(list.size()!=0)
			{
				int nextPos=random.nextInt(list.size());
				randStart=adjacencyList.get(strIndex.get(list.get(nextPos).vertex));
				randVisit[strIndex.get(temp.vertex)][strIndex.get(randStart.vertex)]=1;
				return randStart.vertex;
			}
			else {
				randStart=null;
				return "";
			}
		}
	}
	int flag=0;
	

}
