import java.util.*;
import java.util.HashSet;
// class Trienode to creste and access nodes
class trienode
{
//variable for content
char content;
//to check if the current char is last letter in a word
boolean isend;
int count;
//Subnodes of given nodes
LinkedList <trienode> childlist;
// to count frequency of a word
int frequency;
//constructor for trienode
	public trienode(char c)
	{
	childlist=new LinkedList<trienode>();
	isend=false;
	content=c;
	count=0;
	}
//returns node with given char
	public trienode subnode(char c)
	{
	if(childlist!=null)
		for(trienode child:childlist)
			if(child.content==c)
				return child;
	return null;
	}
}	
// Trie class to access trienode			
class trie
{
private trienode root;
//constructor
	public trie()
	{
	root=new trienode(' ');
	}
//function to insert word
	public void insert(String word)
	{
		if(search(word)==true)
		return;
		trienode current=root;
		for(char ch:word.toCharArray())
			{
				trienode child=current.subnode(ch);
				if(child!=null)
					current=child;
				else
				{
				current.childlist.add(new trienode(ch));
				current=current.subnode(ch);
				}
				current.count++;
			}
			current.isend=true;
			current.frequency++;
	}
	//function to search a word
	public boolean search(String word)
	{
		trienode current=root;
		for(char ch:word.toCharArray())
		{
		if(current.subnode(ch)==null)
			return false;
		else
			current=current.subnode(ch);
		}
		if(current.isend==true)
		return true;
		return false;
	}
	// to return frequency
	public int frequencyword(String word)
	{
		trienode current=root;
		for(char ch:word.toCharArray())
		{
		if(current.subnode(ch)==null)
			return 0;
		else
			current=current.subnode(ch);
		}
		if(current.isend==true)
		return current.frequency;
		return 0;
	}
}
// string corrector
class corrector
{
	public trienode root;
	public trie triecls;
	public int i;
	public char j;
	public corrector()
		{
			triecls=new trie();
			root=new trienode(' ');
		}
		//function to compute words formed by deleting letters and returns a set of all words
		public Set delete(String word)
		{
			Set<String> del=new HashSet();
			String s1,s2,s3;
			for(i=1;i<word.length();i++)
			{
				s1=word.substring(0,i-1);
				s2=word.substring(i+1);
				s3=s1+s2;
				if(triecls.search(s3))
				del.add(s3);
			}
			return del;
		}
		//function to compute words formed by transposing letters and returns a set of all words
		public Set transpose(String word)
		{
			Set<String> trans=new HashSet();
			char[] word1=word.toCharArray();
			char temp;
			String s1;
			for(i=1;i<word1.length-2;i++)
			{
				
				s1=word.substring(0,i-1)+word.charAt(i+1)+word.charAt(i)+word.substring(i+2);
				if(triecls.search(s1))
					trans.add(s1);
			}
			return trans;
		}
		//function to compute words formed by replacing letters and returns a set of all words
		public Set replace(String word)
		{
			Set repl=new HashSet();
			String s1;
			char[] word1=word.toCharArray();
			for(i=1;i<word1.length;i++)
			{
				for(j='a';j<='z';j++)
				{
					s1=word.substring(0,i-1)+j+word.substring(i+1);
					if(triecls.search(s1))
						repl.add(s1);	
				}
			}
			return repl;
		}
		//function to compute words formed by inserting letters and returns a set of all words
		public Set insert(String word)
		{
			Set ins=new HashSet();
			String s1;
			char[] word1=word.toCharArray();
			for(i=1;i<word1.length;i++)
			{
				for(j='a';j<='z';j++)
				{
					s1=word.substring(0,i-1)+j+word.substring(i);
					if(triecls.search(s1))
						ins.add(s1);	
				}
			}
			return ins;
			
		}
		public void display(String word)
		{
			Set all=new HashSet();
			all.add(insert(word));
			all.add(delete(word));
			all.add(replace(word));
			all.add(transpose(word));
			Iterator it=all.iterator();
			while(it.hasNext())
				System.out.println(it.next());
		}
}
//main class
class triehandler
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		trie t=new trie();
		corrector ct=new corrector();
		int i;
		for(i=0;i<5;i++)
		t.insert(sc.next());
	for(i=0;i<5;i++)
		{String st=sc.next();
		System.out.println("Status "+t.search(st)+" freuency "+t.frequencyword(st));
		}
		for(i=0;i<5;i++)
			ct.display(sc.next());
}
}
